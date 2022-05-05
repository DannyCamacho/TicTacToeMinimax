package com.tictactoe.tictactoeminimax;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class MinimaxController {
    @FXML
    public Button startButton;
    @FXML
    public TextArea ta;

    public void update(String message) {
        ta.appendText(message);
    }

    public void onStartClicked(MouseEvent mouseEvent) {
        MinimaxClient client = new MinimaxClient("localhost", 8000, this);
        client.execute();
        startButton.setVisible(false);
    }
}