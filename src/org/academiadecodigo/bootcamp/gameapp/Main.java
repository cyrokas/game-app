package org.academiadecodigo.bootcamp.gameapp;

import javafx.application.Application;
import javafx.stage.Stage;
import org.academiadecodigo.bootcamp.gameapp.client.Client;
import org.academiadecodigo.bootcamp.gameapp.client.ClientHandler;
import org.academiadecodigo.bootcamp.gameapp.client.ClientRegistry;
import org.academiadecodigo.bootcamp.gameapp.client.Navigation;
import org.academiadecodigo.bootcamp.gameapp.server.Server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A/C: Bootcamp8
 * 2nd group project - Game App Platform
 * Authors: Cyrille Feijó, João Fernandes, Hélder Matos, Nelson Pereira, Tiago Santos
 */


public class Main extends Application {

    private Client client;

    /**
     * Tests if the uses passes the key 'server' to start in server mode.
     * Otherwise it starts as a client.
     *
     * @param args
     */
    public static void main(String[] args) {

        // TODO: 01/07/17 create service resgistry for server
        if (args[0].equals("server")) {
            Server server = new Server();
            server.init();
            return;
        }

        launch(args);
    }

    /**
     * Shows in the server console the stream messages received.
     */
    @Override
    public void init() {
    }

    /**
     * Loading the first view.
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        client = new Client();
        ClientHandler clientHandler = new ClientHandler();

        ClientRegistry.getInstance().setClient(client);
        ClientRegistry.getInstance().setHandler(clientHandler);

        Navigation.getInstance().setStage(primaryStage);
        Navigation.getInstance().loadScreen("login");

        ExecutorService newThread = Executors.newSingleThreadExecutor();
        newThread.submit(clientHandler);
    }

    @Override
    public void stop (){
        client.closeClient();
    }
}