package org.aerogear.installer.task;

import com.openshift.restclient.ClientBuilder;
import com.openshift.restclient.IClient;
import javafx.beans.property.*;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectToOpenShiftService extends Service<Boolean> {

    private StringProperty url = new SimpleStringProperty("https://192.168.42.31:8443");
    private StringProperty username = new SimpleStringProperty("developer");
    private StringProperty password = new SimpleStringProperty("password");
    private BooleanProperty connected = new SimpleBooleanProperty(false);
    private ObjectProperty<IClient> client = new SimpleObjectProperty<>();

    public IClient getClient() {
        return client.get();
    }

    public ObjectProperty<IClient> clientProperty() {
        return client;
    }

    private void setClient(IClient client) {
        this.client.set(client);
    }

    public String getUrl() {
        return url.get();
    }

    public StringProperty urlProperty() {
        return url;
    }

    public void setUrl(String url) {
        this.url.set(url);
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public boolean isConnected() {
        return connected.get();
    }

    public BooleanProperty connectedProperty() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected.set(connected);
    }

    @Override
    protected Task<Boolean> createTask() {

        final var url = getUrl();
        final var username = getUsername();
        final var password = getPassword();


        return new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                var client = new ClientBuilder(url)
                        .withUserName(username)
                        .withPassword(password)
                        .build();

                Logger.getAnonymousLogger().log(Level.INFO, client.getAuthorizationContext().getToken());


                try {
                    if (client.getAuthorizationContext().isAuthorized()) {
                        setConnected(true);
                        setClient(client);
                        return true;
                    } else {
                        setConnected(false);
                        setClient(client);
                        return false;
                    }
                } catch (Exception ex) {
                    Logger.getAnonymousLogger().log(Level.SEVERE, ex.getMessage(), ex);
                    setConnected(false);
                    setClient(client);
                    return false;
                }

            }
        };
    }
}
