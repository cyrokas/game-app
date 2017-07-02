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

    private Socket clientSocket;
    private PrintWriter output;
    private BufferedReader input;

    public Client() {

        try {
            clientSocket = new Socket(InetAddress.getLoopbackAddress(), 1234);

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

    //Receiving message from server
    public String receive() {
        String receivedMessage = null;

        System.out.println("I'm in client / receive method");

        try {
            receivedMessage = input.readLine();

            System.out.println("recebi esta mensagem: " + receivedMessage);

            if (receivedMessage.equals("null")){
                input.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return receivedMessage;
    }

    //Sending Message from client to server
    public void send(String sendMessage){

        if (sendMessage != null) {
            output.write(sendMessage);
            output.flush();
        }

        System.out.println("Message sent: " + sendMessage);
    }

    public void closeClient(){
        try {
            input.close();
            output.close();
            clientSocket.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Socket getClientSocket(){
        return clientSocket;
    }
}
