module raul.JogoDaVelha {
    requires javafx.controls;
    requires javafx.fxml;

    opens raul.JogoDaVelha to javafx.fxml;
    opens raul.JogoDaVelha.gui to javafx.fxml;
    exports raul.JogoDaVelha;
}