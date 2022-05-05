package com.tictactoe.tictactoeminimax;

import javafx.application.Platform;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public record MinimaxClient(String hostname, int port, MinimaxController controller) {
    public void execute() {
        try {
            Socket socket = new Socket(hostname, port);
            print("Connected to the Tic-Tac-Toe Server");
            new ReadThread(socket, this).start();
        } catch (UnknownHostException ex) {
            print("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            print("I/O Error: " + ex.getMessage());
        }
    }

    void print(String message) {
        Platform.runLater(() -> controller.update(message));
    }
}