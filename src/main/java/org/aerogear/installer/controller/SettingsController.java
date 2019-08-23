package org.aerogear.installer.controller;

import com.jfoenix.controls.JFXTextField;
import com.openshift.restclient.ClientBuilder;
import com.openshift.restclient.IClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.aerogear.installer.event.OpenShiftEnabledListener;

import java.util.Optional;

public class SettingsController {
    @FXML
    private JFXTextField url;

    @FXML
    private JFXTextField userName;

    @FXML
    private JFXTextField password;

    private IClient client;
    private Optional<OpenShiftEnabledListener> listener = Optional.empty();

    @FXML
    public void initialize() {
        try {
        this.client = new ClientBuilder("https://192.168.42.31:8443")
                .withUserName("developer")
                .withPassword("developer")
                .build();
            listener.ifPresent((listener)->{listener.onOpenShiftEnabled();});
        } catch (Exception e) {
            System.out.println(e.getMessage());
            listener.ifPresent((listener)->{listener.onOpenShiftDisabled();});
        }
    }

    public void configureOpenShift(ActionEvent actionEvent) {
        try {
            this.client = new ClientBuilder(url.getText())
                    .withUserName(userName.getText())
                    .withPassword(password.getText())
                    .build();
            listener.ifPresent((listener)->{listener.onOpenShiftEnabled();});
        } catch (Exception e) {
            System.out.println(e.getMessage());
            listener.ifPresent((listener)->{listener.onOpenShiftDisabled();});
        }
    }

    public void setOpenShiftEnableListener(OpenShiftEnabledListener possibleListener) {
        this.listener = Optional.ofNullable(possibleListener);
        this.listener.ifPresent((listener)-> {
            if (this.client != null) {
                listener.onOpenShiftEnabled();
            } else {
                listener.onOpenShiftDisabled();
            }
        });
    }

}
