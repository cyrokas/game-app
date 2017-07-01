package org.academiadecodigo.bootcamp.gameapp.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * A/C: Bootcamp8
 * 2nd group project - Game App Platform
 * Authors: Cyrille Feijó, João Fernandes, Hélder Matos, Nelson Pereira, Tiago Santos
 */

public class Client {

    private final int PORT_NUMBER = 1234;
    private final InetAddress ip = InetAddress.getLoopbackAddress();
    private Socket clientSocket;
    private PrintWriter output;
    private BufferedReader input;

    public Client() {

        try {
            clientSocket = new Socket(ip, PORT_NUMBER);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        settingStreams();
    }

    private void settingStreams() {

        try {
            output = new PrintWriter(clientSocket.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Recieving message from server
    public String receive() {
        System.out.println("Pronto para receber");
        String receivedMessage = null;

        try {
            receivedMessage = input.readLine();

            if (receivedMessage.equals("null")){
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("recebi mensagem");
        return receivedMessage;
    }

    //Sending Message from client to server
    public void send(String sendMessage){
        output.write(sendMessage);
        output.flush();
    }
}