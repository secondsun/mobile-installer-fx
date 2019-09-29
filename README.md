Notes
=====

Prerequisites
=============
 Minishift running 32 GiB and 80 Gib storage and 8 CPUs (certainly overkill)
 * oc login as system admin or give your used admin privilidges
 golang (for operator development)
 java (for UPS development)

Setup
=====
 * Create MDC namespace for mobile apps
  ```bash
    oc new-project mobile-developer-console
  ```

Installing KeyCloak
===================
  * Download operator
  `wget https://github.com/integr8ly/keycloak-operator/archive/v1.8.3.zip`
  * Install
  ```bash
   oc create -f deploy/crds/Keycloak_crd.yaml 
   oc create -f deploy/crds/KeycloakRealm_crd.yaml 
   kubectl apply -f deploy/rbac.yaml -n mobile-developer-console
   kubectl apply -f deploy/operator.yaml -n mobile-developer-console
   kubectl apply -f deploy/examples/keycloak_min.json -n mobile-developer-console
   echo "{\"apiVersion\": \"aerogear.org\/v1alpha1\",\"kind\": \"Keycloak\",\"metadata\": {\"name\": \"example-provision\"},\"spec\":{\"adminCredentials\": \"\",\"plugins\": [\"keycloak-metrics-spi\"],\"provision\": true}}" | oc create -f -      
  ```

Installing UPS
==============
 * Download operator
  `wget https://github.com/aerogear/unifiedpush-operator/archive/0.2.0.zip`
 * Install
 ```bash
         kubectl create namespace unifiedpush && \
         kubectl label namespace unifiedpush monitoring-key=middleware && \ 
         kubectl create -n unifiedpush -f deploy/service_account.yaml && \
         kubectl create -f deploy/role.yaml && \
         kubectl create -n unifiedpush -f deploy/role_binding.yaml && \
         kubectl apply -f deploy/crds/push_v1alpha1_pushapplication_crd.yaml && \
         kubectl apply -f deploy/crds/push_v1alpha1_androidvariant_crd.yaml && \
         kubectl apply -f deploy/crds/push_v1alpha1_iosvariant_crd.yaml && \
         kubectl apply -f deploy/crds/push_v1alpha1_unifiedpushserver_crd.yaml && \ 
         kubectl apply -n unifiedpush -f deploy/operator.yaml && \
         kubectl apply -n unifiedpush -f deploy/crds/push_v1alpha1_unifiedpushserver_cr.yaml
         oc set env deployment unifiedpush-operator -e  APP_NAMESPACES=mobile-developer-console
 ```


Installing Mobile Developer Console Operator
===================================
 * Download 
   `wget https://github.com/aerogear/mobile-developer-console-operator/archive/0.2.3.zip`
 * Installation
   ```
        kubectl create namespace mobile-developer-console && \
        kubectl label namespace mobile-developer-console monitoring-key=middleware && \
        kubectl create -n mobile-developer-console -f deploy/service_account.yaml && \
        kubectl create -n mobile-developer-console -f deploy/role.yaml && \
        kubectl create -n mobile-developer-console -f deploy/role_binding.yaml && \
        kubectl create -n mobile-developer-console -f deploy/mobileclient_admin_role.yaml && \
        kubectl create -n mobile-developer-console -f deploy/mobiledeveloper_role.yaml && \
        kubectl create -n mobile-developer-console -f deploy/mobiledeveloper_rolebinding.yaml && \
        kubectl apply  -n mobile-developer-console -f deploy/crds/mdc_v1alpha1_mobiledeveloperconsole_crd.yaml && \
        kubectl apply  -n mobile-developer-console -f deploy/mdc_v1alpha1_mobileclient_crd.yaml && \
        kubectl apply -n mobile-developer-console -f deploy/operator.yaml && \
        kubectl set env -n mobile-developer-console -f deploy/operator.yaml OPENSHIFT_HOST=$(minishift ip):8443 && \
        kubectl apply -n mobile-developer-console -f deploy/crds/mdc_v1alpha1_mobiledeveloperconsole_cr.yaml

   ```

TODO: enable cors, enable oauth

Installing Mobile Security Services
===================================
  * Download
    `wget https://github.com/aerogear/mobile-security-service-operator/archive/0.4.0.zip`
  * Installation
    ```bash
	    echo '....... Applying Mobile Security Service CRDS and Operator .......' && \
	    kubectl apply -f deploy/crds/mobile-security-service_v1alpha1_mobilesecurityservice_crd.yaml -n mobile-security-service && \
	    kubectl apply -f deploy/crds/mobile-security-service_v1alpha1_mobilesecurityservicedb_crd.yaml  -n mobile-security-service && \
	    kubectl apply -f deploy/crds/mobile-security-service_v1alpha1_mobilesecurityserviceapp_crd.yaml  -n mobile-security-service && \
	    kubectl apply -f deploy/crds/mobile-security-service_v1alpha1_mobilesecurityservicebackup_crd.yaml  -n mobile-security-service && \
      echo '....... Applying Rules and Service Account .......' && \
      kubectl apply -f deploy/cluster_role.yaml  -n mobile-security-service && \
      kubectl apply -f deploy/cluster_role_binding.yaml  -n mobile-security-service && \
      kubectl apply -f deploy/service_account.yaml  -n mobile-security-service && \
      echo '....... Applying Mobile Security Service Operator .......' && \
      kubectl apply -f deploy/operator.yaml  -n mobile-security-service && \
      echo '....... Creating the Mobile Security Service and Database .......' && \
      kubectl apply -f deploy/crds/mobile-security-service_v1alpha1_mobilesecurityservice_cr.yaml  -n mobile-security-service && \
      kubectl apply -f deploy/crds/mobile-security-service_v1alpha1_mobilesecurityservicedb_cr.yaml  -n mobile-security-service && \
      echo '....... Creating namespace mobile-security-service-apps .......' && \
    	oc new-project mobile-security-service-apps
    ```

Installing Sync Template
===================================



Optional
========

Installing Enmasse
==================
  * Download
  `wget https://github.com/EnMasseProject/enmasse/releases/download/0.29.0/enmasse-0.29.0.tgz`
  * Install
  ```bash
   oc new-project enmasse-infra
   oc apply -f install/bundles/enmasse
   oc apply -f install/components/example-plans
   oc apply -f install/components/example-roles
   oc apply -f install/components/example-authservices/standard-authservice.yaml
  ```


Installing DataSync?
Installing Mobile Sercurity Service

Installing ???

