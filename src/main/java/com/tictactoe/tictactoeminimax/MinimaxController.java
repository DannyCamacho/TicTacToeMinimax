package com.tictactoe.tictactoeminimax;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class MinimaxController {
    @FXML
    public Button startButton;
    @FXML
    public TextArea ta;

    public void update(String message) {
        ta.appendText(message);
    }

    public void onStartClicked() {
        MinimaxClient client = new MinimaxClient("localhost", 8000, this);
        client.execute();
        startButton.setVisible(false);
    }
}