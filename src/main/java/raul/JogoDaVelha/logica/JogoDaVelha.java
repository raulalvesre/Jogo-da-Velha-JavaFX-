package raul.JogoDaVelha.logica;

public class JogoDaVelha {

    private String[][] campoDoJogo;
    public String turnoDeQuem;
    private int turno;

    public JogoDaVelha () {
        turno = 1;
        this.campoDoJogo = new String[][] {{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}};
    }
        
    public void marcarNaCoordenada(int y, int x) {
        if (turno % 2 != 0) {
            turnoDeQuem = "X";
        } else {
            turnoDeQuem = "O";
        }
        campoDoJogo[y][x] = turnoDeQuem;
    }

    public boolean isCoordenadaVazia(int y, int x) {
        return campoDoJogo[y][x].isBlank();
    }

    public boolean jogoAcabou() {
        if (quemGanhou("X")) {
            return true;
        } else if (quemGanhou("O")) {
            return true;
        } else if (empate()) {
            return true;
        }
        return false;
    }

    public boolean quemGanhou(String quem) {
        return (quemGanhouNasLinhas(quem) || quemGanhouColunas(quem) || quemGanhouDiagonais(quem));
    }

    private boolean quemGanhouNasLinhas(String quem) {
        int quantosNaLinha;

        for (int y = 0; y < 3; y++) {
            quantosNaLinha = 0;
            for (int x = 0; x < 3; x++) {
                if (campoDoJogo[y][x].equals(quem)) {
                    quantosNaLinha++;
                }
            }

            if (quantosNaLinha == 3) {
                return true;
            }
        }
        return false;
    }

    private boolean quemGanhouColunas (String quem) {
        int quantosNaColuna;

        for (int x = 0; x < 3; x++) {
            quantosNaColuna = 0;
            for (int y = 0; y < 3; y++) {
                if (campoDoJogo[y][x].equals(quem)) {
                    quantosNaColuna++;
                }
            }

            if (quantosNaColuna == 3) {
                return true;
            }
        }

        return false;
    }

    private boolean quemGanhouDiagonais(String quem) {
        int quantosNaDiag;

        for (int i = 0; i < 2; i++) {
            quantosNaDiag = 0;
            for (int j = 0; j < 3; j++) {
                if (campoDoJogo[Math.abs((i * 2) - j)][j].equals(quem)) {
                    quantosNaDiag++;
                }
            }

            if (quantosNaDiag == 3) {
                return true;
            }

        }
        return false;
    }

    public boolean empate() {
        boolean temCelulaVazia = false;
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (campoDoJogo[y][x].equals(" ")) {
                    temCelulaVazia = true;
                }
            }
        }

        return !temCelulaVazia;
    }
    
    public void addTurno() {
        this.turno++;
    }

}

