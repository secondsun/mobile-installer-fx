package org.aerogear.installer.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.aerogear.installer.Main;
import org.aerogear.installer.task.ConnectToOpenShiftService;

public class SettingsController {
    @FXML
    private JFXTextField url;

    @FXML
    private JFXTextField userName;

    @FXML
    private JFXTextField password;

    private final ConnectToOpenShiftService service = Main.OPENSHIFT_SERVICE;

    @FXML
    public void initialize() {
        url.setText(Main.OPENSHIFT_SERVICE.getUrl());
        userName.setText(Main.OPENSHIFT_SERVICE.getUsername());
        password.setText(Main.OPENSHIFT_SERVICE.getPassword());
    }

    public void configureOpenShift(ActionEvent actionEvent) {
        service.setUrl(url.getText());
        service.setUsername(userName.getText());
        service.setPassword(password.getText());
        service.restart();
    }

}
