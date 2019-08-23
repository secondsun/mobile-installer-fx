package org.aerogear.installer.controller;

import javafx.fxml.FXML;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;

public class MainController {


    @FXML
    private StackPane root;

    @FXML
    private TabPane tabPane;

    @FXML
    private SettingsController settingsController;

    @FXML
    private ServicesController servicesController;

    @FXML
    public void initialize() {
        //initialize tab bar
        initializeTabBar();
        //initialize settings
        //initialize services

        settingsController.setOpenShiftEnableListener(servicesController);

    }

    private void initializeTabBar() {

        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(0);

    }
}
