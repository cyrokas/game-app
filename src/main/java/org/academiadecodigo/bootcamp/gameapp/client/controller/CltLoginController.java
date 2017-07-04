package org.academiadecodigo.bootcamp.gameapp.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import org.academiadecodigo.bootcamp.gameapp.client.*;
import org.academiadecodigo.bootcamp.gameapp.utilities.ProtocolConfig;
import org.academiadecodigo.bootcamp.gameapp.utilities.Verification;

import java.net.URL;
import java.util.ResourceBundle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A/C: Bootcamp8
 * 2nd group project - Game App Platform
 * Authors: Cyrille Feijó, João Fernandes, Hélder Matos, Nelson Pereira, Tiago Santos
 */

public class CltLoginController implements Initializable, Controller {

    private final String NAME = "Login";

    private Client client;
    private ClientHandler clientHandler;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button btnLogin;

    @FXML
    private Hyperlink linkRegister;

    @FXML
    private Label lblLoginInfo;

    @FXML
    private Label lblUsernameError;

    @FXML
    private Label lblPasswordError;

    // TODO: 01/07/2017 use isEmpty() to btnLogin.setDisable() and when you press setDisable() to
    @FXML
    public void onLogin(ActionEvent event) {
        Verification.cleanErrorMsg(lblUsernameError, lblPasswordError, lblPasswordError, lblPasswordError);

        if (!emptyField()) {
            String sendMessage = ProtocolConfig.SERVER_LOGIN + " " +  username.getText() +
                    " " + password.getText() + "\n";

            client.send(sendMessage);

            btnLogin.setDisable(true);
            lblLoginInfo.setVisible(false);
        }
    }

    public void authFailure(){
        setText(lblLoginInfo, "Authentication Failed");
        btnLogin.setDisable(false);
    }

    @FXML
    void onRegister(ActionEvent event) {
        Navigation.getInstance().loadScreen(ProtocolConfig.REGISTER_VIEW);
    }

    public void successfullyAuth(String message){
        Navigation.getInstance().loadScreen(message);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        client = ClientRegistry.getInstance().getClient();
        clientHandler = new ClientHandler();

        clientHandler.setInitializable(this);
        ClientRegistry.getInstance().setHandler(clientHandler);

        ExecutorService newThread = Executors.newSingleThreadExecutor();
        newThread.submit(clientHandler);
    }

    private boolean emptyField() {
        boolean fieldEmpty = false;

        if (username.getText().length() == 0) {

            setText(lblUsernameError, "(*Required Field)");
            fieldEmpty = true;
        }
        if (password.getText().length() == 0) {

            setText(lblPasswordError, "(*Required Field)");
            fieldEmpty = true;
        }
        return fieldEmpty;
    }

    private <T extends Labeled> void setText(T type, String message){
        type.setText(message);
        type.setVisible(true);
    }

    public String getName(){
        return NAME;
    }
}
