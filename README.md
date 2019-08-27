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

Installing Mobile Developer Console Operator
===================================
 * Download 
   https://github.com/aerogear/mobile-developer-console-operator/archive/0.2.3.zip
 * Installation
   ```
   make cluster/prepare install-operator install-mdc
   ```

Installing Enmasse


Installing UPS
 * Enmasse

Installing DataSync?
Installing Mobile Sercurity Service

Installing ???

