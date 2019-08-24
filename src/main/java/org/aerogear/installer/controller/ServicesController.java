package org.aerogear.installer.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.aerogear.installer.Main;
import org.aerogear.installer.control.ServiceItem;
import org.aerogear.installer.event.OpenShiftEnabledListener;

public class ServicesController {

    @FXML
    private VBox servicesRoot;

    public ServiceItem keyCloakItem;
    public ServiceItem enmasseItem;

    @FXML
    public void initialize() {

        var connectToOpenShift = Main.OPENSHIFT_SERVICE;

        this.keyCloakItem = new ServiceItem();
        this.keyCloakItem.setServiceName("KeyCloak");

        this.enmasseItem = new ServiceItem();
        this.enmasseItem.setServiceName("Enmasse");

        servicesRoot.getChildren().add(keyCloakItem);
        servicesRoot.getChildren().add(enmasseItem);
    }

}
