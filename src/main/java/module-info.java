module AeroGearInstaller {
    exports org.aerogear.installer;

    opens org.aerogear.installer to javafx.fxml;
    opens org.aerogear.installer.control to javafx.fxml;
    opens org.aerogear.installer.controller to javafx.fxml;

    requires java.base;
    requires com.jfoenix;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires openshift.restclient.java;

}