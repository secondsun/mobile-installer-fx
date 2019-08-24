package org.aerogear.installer;

import com.jfoenix.controls.JFXDecorator;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.aerogear.installer.task.ConnectToOpenShiftService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author summerspittman
 */
public final class Main extends Application{

    private Stage window;

    public static final ConnectToOpenShiftService OPENSHIFT_SERVICE = new ConnectToOpenShiftService();

    @Override
    public void start(Stage stage) throws Exception {
        OPENSHIFT_SERVICE.start();

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));

        this.window = stage;
        window.setTitle("AeroGear Installer");
        window.setWidth(getVisualScreenWidth() * 0.50);
        window.setHeight(getVisualScreenHeight() * 0.50);
        window.centerOnScreen();

        JFXDecorator decorator = new JFXDecorator(stage, root);
        Scene scene = new Scene(decorator);
        scene.getStylesheets().add(getClass().getResource("/styles/main.css").toExternalForm());

        window.setScene(scene);
        window.show();

        window.setOnCloseRequest(exit -> {
            Platform.exit();
            System.exit(0);
        });



    }

    public static double getVisualScreenHeight() {
        return Screen.getPrimary().getVisualBounds().getHeight();
    }

    public static double getVisualScreenWidth() {
        return Screen.getPrimary().getVisualBounds().getWidth();
    }
    
}
