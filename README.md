Notes
=====

Prerequisites
=============
 Minishift running 32 GiB and 80 Gib storage and 8 CPUs (certainly overkill)
 * oc login as system admin or give your used admin privilidges
 golang (for operator development)
 java (for UPS development)

Installing KeyCloak
===================
  * Download operator
  `wget https://github.com/integr8ly/keycloak-operator/archive/v1.8.3.zip`
  * Install
  ```bash
   oc new-project keycloak-infra
   oc create -f deploy/crds/Keycloak_crd.yaml 
   oc create -f deploy/crds/KeycloakRealm_crd.yaml 
   echo "{\"apiVersion\": \"aerogear.org\/v1alpha1\",\"kind\": \"Keycloak\",\"metadata\": {\"name\": \"example-provision\"},\"spec\":{\"adminCredentials\": \"\",\"plugins\": [\"keycloak-metrics-spi\"],\"provision\": true}}" | oc create -f -      
  ```

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

Installing Mobile Developer Console
===================================
 * Download 
 

 Notes to myself : ¡HEY YOU LOOK HERE!  You need to update the MDC docs/operators/scripts to use the i8ly KC operator instead of service bindings.

 Download
    CRDs and friends are in github, source is for development, most things are in quay.
    Only necessary for development, otherwise use resources in quay
 Create Namespace
    mobile-developer-console
    `oc new-project mobile-developer-console`
 CRDs
    `oc create -f deploy/mobile-client-crd.yaml`
 Configure permissions
    `oc create clusterrole mobileclient-admin --verb=create,delete,get,list,patch,update,watch --resource=mobileclients`
    `oc adm policy add-cluster-role-to-group mobileclient-admin system:authenticated`
 Configure security
    `oc create -f deploy/openshift-oauthclient.yaml`    
 Monitoring
 Operator

Installing UPS
 * Enmasse

Installing DataSync?
Installing Mobile Sercurity Service

Installing ???

