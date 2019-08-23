Notes
=====

Prerequisites
=============
 Minishift running 32 GiB and 80 Gib storage and 8 CPUs (certainly overkill)
 golang (for operator development)
 java (for UPS development)

Installing KeyCloak
===================
  Download operator
  * https://github.com/integr8ly/keycloak-operator
  ???

Installing Enmasse
  Download
  Install

Installing Mobile Developer Console
===================================

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

