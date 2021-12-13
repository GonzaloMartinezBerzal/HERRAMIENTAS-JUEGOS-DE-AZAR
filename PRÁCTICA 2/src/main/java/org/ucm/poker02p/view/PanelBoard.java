/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ucm.poker02p.view;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import org.ucm.poker02p.control.Controller;
import org.ucm.poker02p.model.Board;
import org.ucm.poker02p.model.Carta;
import org.ucm.poker02p.model.CuadritoBoard;
import org.ucm.poker02p.model.Mano;
import org.ucm.poker02p.model.Observer;

public class PanelBoard extends JPanel implements Observer {

    private CuadritoBoard[][] matrizC;
    private Controller ctrl;
    private Board board;

    public PanelBoard(Controller ctrl) {
        board = new Board();
        this.ctrl = ctrl;
        ctrl.addObserver(this);
        setSize(new Dimension(160, 520));
        iniMatriz();
        setBackground(Color.WHITE);
        this.setVisible(true);
        setLocation(5, 5);

    }

    public Board getBoard() {
        return board;
    }

    void iniMatriz() {

        matrizC = new CuadritoBoard[13][4];
        for (int i = 13; i > 0; i--) {
            for (int j = 0; j < 4; j++) {
                if (j == 0) {
                    matrizC[i - 1][j] = new CuadritoBoard(new Carta(i + 1, 'h'), "rojo", ctrl);
                } else if (j == 1) {
                    matrizC[i - 1][j] = new CuadritoBoard(new Carta(i + 1, 'c'), "verde", ctrl);
                } else if (j == 2) {
                    matrizC[i - 1][j] = new CuadritoBoard(new Carta(i + 1, 'd'), "azul", ctrl);
                } else if (j == 3) {
                    matrizC[i - 1][j] = new CuadritoBoard(new Carta(i + 1, 's'), "gris", ctrl);
                }
                add(matrizC[i - 1][j]);
                matrizC[i - 1][j].setLocation(40 * j, 520 - 40 * i);
                matrizC[i - 1][j].setEnabled(false);
            }
        }
    }

    @Override
    public void onRegister() {

    }

    @Override
    public void onReset() {
        if (matrizC[0][0].isEnabled()) {
            reset();
            for (int i = 13; i > 0; i--) {
                for (int j = 0; j < 4; j++) {
                    matrizC[i - 1][j].setEnabled(true);
                }
            }
        }
    }

    @Override
    public void activaRanking(boolean rankingActivado) {

    }

    @Override
    public void onRankingChanged(List<Mano> lista) {

    }

    @Override
    public void onRangeChanged(ArrayList<Mano> lista) {

    }

    @Override
    public void onAdvance() {

    }

    @Override
    public void onCuadritoChanged(Mano mano, boolean seleccionado) {

    }

    @Override
    public void activaJugadas(boolean jugadasActivado) {
        reset();
        if (jugadasActivado) {
            for (int i = 13; i > 0; i--) {
                for (int j = 0; j < 4; j++) {
                    matrizC[i - 1][j].setEnabled(true);
                }
            }
        } else {
            for (int i = 13; i > 0; i--) {
                for (int j = 0; j < 4; j++) {
                    matrizC[i - 1][j].setEnabled(false);
                }
            }
        }
    }

    @Override
    public void onCuadritoBoardChanged(Carta carta, boolean seleccionado) {
        if (seleccionado) {
            board.addCarta(carta);
        } else {
            board.removeCarta(carta);
        }

    }

    private void reset() {
        board.reset();
        for (int i = 13; i > 0; i--) {
            for (int j = 0; j < 4; j++) {
                this.remove(matrizC[i - 1][j]);
            }
        }

        matrizC = new CuadritoBoard[13][4];
        for (int i = 13; i > 0; i--) {
            for (int j = 0; j < 4; j++) {
                if (j == 0) {
                    matrizC[i - 1][j] = new CuadritoBoard(new Carta(i + 1, 'h'), "rojo", ctrl);
                } else if (j == 1) {
                    matrizC[i - 1][j] = new CuadritoBoard(new Carta(i + 1, 'c'), "verde", ctrl);
                } else if (j == 2) {
                    matrizC[i - 1][j] = new CuadritoBoard(new Carta(i + 1, 'd'), "azul", ctrl);
                } else if (j == 3) {
                    matrizC[i - 1][j] = new CuadritoBoard(new Carta(i + 1, 's'), "gris", ctrl);
                }
                add(matrizC[i - 1][j]);
                matrizC[i - 1][j].setLocation(40 * j, 520 - 40 * i);
                matrizC[i - 1][j].setEnabled(false);
            }
        }
    }

    @Override
    public void onBoardChanged(ArrayList<Carta> lista) {
        reset();
        for (Carta c : lista) {
            board.addCarta(c);
            switch (c.getPalo()) {
                case 'h':
                    matrizC[c.getNum() - 2][0].actualizaCuadrito();
                    break;
                case 'c':
                    matrizC[c.getNum() - 2][1].actualizaCuadrito();
                    break;
                case 'd':
                    matrizC[c.getNum() - 2][2].actualizaCuadrito();
                    break;
                case 's':
                    matrizC[c.getNum() - 2][3].actualizaCuadrito();
                    break;
                default:
                    break;
            }

        }
        for (int i = 13; i > 0; i--) {
            for (int j = 0; j < 4; j++) {
                matrizC[i - 1][j].setEnabled(true);
            }
        }
    }

}
