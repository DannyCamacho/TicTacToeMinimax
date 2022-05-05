package com.tictactoe.tictactoeminimax;

import com.tictactoe.message.*;
import java.io.*;
import java.net.*;
import java.util.Arrays;

public class ReadThread extends Thread {
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private final MinimaxClient client;
    private final Minimax minimax;

    public ReadThread(Socket socket, MinimaxClient client) {
        this.client = client;
        minimax = new Minimax();
        try {
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());
            output.writeObject(new ServerConnection("Minimax", "Minimax", true));
            output.flush();
        } catch (IOException ex) {
            client.print("\nError getting input stream: " + ex.getMessage() + "\n");
        }
    }

    public void run() {
        while (true) {
            try {
                MinimaxMoveSend move = (MinimaxMoveSend)input.readObject();
                client.print("\nGame: " + move.gameName() +
                        " Move: " + move.move() +
                        " Token: " + move.playerToken() +
                        " Board: " + Arrays.toString(move.boardState()));

                PlayerMoveSend result = minimax.getMove(move);
                client.print("\nGame: " + result.gameName() +
                        " Move: " + result.move() +
                        " Token: " + result.playerToken() +
                        " Board: " + Arrays.toString(result.boardState()));
                output.writeObject(result);
                output.flush();
            } catch (IOException | ClassNotFoundException ex) {
                client.print("\nError reading from server: " + ex.getMessage()+ "\n");
                break;
            }
        }
    }
}