package org.academiadecodigo.bootcamp.gameapp.server;

/**
 * Created by Cyrille on 06/07/17.
 */
public interface Workable {

    void process(ClientHandler ClientHandler, String message);
}