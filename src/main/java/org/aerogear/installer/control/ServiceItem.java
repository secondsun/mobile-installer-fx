package org.aerogear.installer.control;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import org.aerogear.installer.Main;

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
            Main.OPENSHIFT_SERVICE.connectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue) {
                    enabled.setDisable(!newValue);
                }
            });
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void setServiceName(String text) {
        serviceName.setText(text);
    }

}
