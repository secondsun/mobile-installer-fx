package org.aerogear.installer.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.aerogear.installer.control.ServiceItem;
import org.aerogear.installer.event.OpenShiftEnabledListener;

public class ServicesController implements OpenShiftEnabledListener {

    @FXML
    private VBox servicesRoot;

    public ServiceItem keyCloakItem;
    public ServiceItem enmasseItem;

    @FXML
    public void initialize() {
        this.keyCloakItem = new ServiceItem();
        this.keyCloakItem.setServiceName("KeyCloak");

        this.enmasseItem = new ServiceItem();
        this.enmasseItem.setServiceName("Enmasse");

        servicesRoot.getChildren().add(keyCloakItem);
        servicesRoot.getChildren().add(enmasseItem);
    }

    @Override
    public void onOpenShiftEnabled() {
        keyCloakItem.enable();
        enmasseItem.enable();
    }

    @Override
    public void onOpenShiftDisabled() {
        keyCloakItem.disable();
        enmasseItem.disable();
    }
}
