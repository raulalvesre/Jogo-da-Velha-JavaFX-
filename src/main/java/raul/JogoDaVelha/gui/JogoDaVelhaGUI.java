package raul.JogoDaVelha.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import raul.JogoDaVelha.logica.JogoDaVelha;

public class JogoDaVelhaGUI {

    private JogoDaVelha jogoDaVelha;

    @FXML
    private Label lblInfo;
    @FXML
    private GridPane boardLayout;
    @FXML
    private Button btJogarNovamente;

    @FXML
    private void initialize() {
        btJogarNovamente.setVisible(false);
        this.jogoDaVelha = new JogoDaVelha();
    }

    @FXML
    private void handleButtonsGridpane(ActionEvent event) {
        Button botaoClicado = (Button) event.getSource();
        int y = GridPane.getRowIndex(botaoClicado);
        int x = GridPane.getColumnIndex(botaoClicado);

        while (true) {
            if (jogoDaVelha.isCoordenadaVazia(y, x)) {
                jogoDaVelha.marcarNaCoordenada(y, x);
                botaoClicado.setText(jogoDaVelha.turnoDeQuem);
                lblInfo.setText("");
                break;
            } else {
                lblInfo.setText("JÁ PREENCHIDO");
                return;
            }
        }

        if (jogoDaVelha.jogoAcabou()) {
            if (jogoDaVelha.quemGanhou("X")) {
                lblInfo.setText("X Ganhou!!!");
            } else if (jogoDaVelha.quemGanhou("O")) {
                lblInfo.setText("O Ganhou!!!");
            } else {
                lblInfo.setText("EMPATE");
            }
            btJogarNovamente.setVisible(true);
            desativarCampoDeJogo();
        } else {
            jogoDaVelha.addTurno();
        }
    }

    private void desativarCampoDeJogo() {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                getButton(x, y).setDisable(true);
            }
        }
    }

    private Button getButton(int col, int row) {
        for (Node node : boardLayout.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                if (node instanceof Button) {
                    final Button button = (Button) node;
                    return button;
                }
            }
        }
        return null;
    }

    @FXML
    private void handleBtJogarNovamente() {
        this.jogoDaVelha = new JogoDaVelha();
        lblInfo.setText("");
        limparTextoBotoes();
        ativarCampoDeJogo();
        btJogarNovamente.setVisible(false);

    }

    private void limparTextoBotoes() {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                getButton(x, y).setText("");
            }
        }
    }

    private void ativarCampoDeJogo() {
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                getButton(x, y).setDisable(false);
            }
        }
    }

    @FXML
    private void handleBtSair(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }


}
