package org.aerogear.installer.control;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class ServiceItem extends HBox {

    @FXML
    private JFXTextField serviceName;

    @FXML
    private JFXToggleButton enabled;

    public ServiceItem() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fxml/ServiceItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void setServiceName(String text) {
        serviceName.setText(text);
    }

    public void disable() {
        enabled.setDisable(true);
    }

    public void enable() {
        enabled.setDisable(false);
    }
}
