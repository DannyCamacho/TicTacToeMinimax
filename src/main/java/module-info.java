module com.tictactoe.tictactoeminimax {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.tictactoe.tictactoeminimax to javafx.fxml;
    exports com.tictactoe.tictactoeminimax;
}