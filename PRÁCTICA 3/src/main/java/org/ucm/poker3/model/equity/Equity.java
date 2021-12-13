package org.ucm.poker3.model.equity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import java.util.HashSet;

import java.util.Map;
import java.util.TreeMap;
import org.ucm.poker3.model.cartas.Board;
import org.ucm.poker3.model.cartas.Carta;
import org.ucm.poker3.model.cartas.Jugador;
import org.ucm.poker3.model.cartas.JugadorOmaha;
import org.ucm.poker3.model.cartas.MazoCartas;
import org.ucm.poker3.utils.Util;
import org.ucm.poker3.view.porcentajeFrame.PorcentajeFrame;

public class Equity {

    private ArrayList<Double> porcentajes;
    private double totales = 0;
    private ArrayList<Double> numGanados;
    private ArrayList<Jugador> jugadores;
    private ArrayList<JugadorOmaha> jugadoresOmaha;
    private Board boardActual;
    int cont;
    private Boolean[][] mazoE;
    private HashSet<Board> calculado;
    private PorcentajeFrame pF;
    private int combTotales;

    public Equity() {

    }

    public ArrayList<Double> getPorcentajes() {
        return porcentajes;
    }

    public void calculateEquity(ArrayList<Jugador> jugadores, MazoCartas mazo, Board board, PorcentajeFrame pF, boolean modoNormal, ArrayList<JugadorOmaha> jugadoresOmaha) {
        if (modoNormal) {
            combTotales = Util.getNumJug(jugadores.size(), board.getNumCartas(), modoNormal);
        } else {
            combTotales = Util.getNumJug(jugadoresOmaha.size(), board.getNumCartas(), modoNormal);
        }

        this.pF = pF;
        if (modoNormal) {
            this.jugadores = jugadores;
        } else {
            this.jugadoresOmaha = jugadoresOmaha;
        }
        totales = 0;
        cont = board.size();
        porcentajes = new ArrayList<Double>();
        numGanados = new ArrayList<Double>();
        if (modoNormal) {
            for (int i = 0; i < jugadores.size(); i++) {
                numGanados.add(0.0);
            }
        } else {
            for (int i = 0; i < jugadoresOmaha.size(); i++) {
                numGanados.add(0.0);
            }
        }

        boardActual = board;

        mazoE = new Boolean[13][4];
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 4; j++) {
                if (mazo.isCarta(i, j)) {
                    mazoE[i][j] = true;
                } else {
                    mazoE[i][j] = false;
                }
            }
        }
        calculado = new HashSet<>();

        if (modoNormal) {
            calcularModoNormal();
        } else {
            calcularModoOmaha();
        }
        if (modoNormal) {
            for (int i = 0; i < jugadores.size(); i++) {
                Double auxD = (numGanados.get(i) / totales) * 100.0;
                Double truncatedDouble = BigDecimal.valueOf(auxD)
                        .setScale(3, RoundingMode.HALF_UP).doubleValue();
                porcentajes.add(truncatedDouble);
            }
        } else {
            for (int i = 0; i < jugadoresOmaha.size(); i++) {
                Double auxD = (numGanados.get(i) / totales) * 100.0;
                Double truncatedDouble = BigDecimal.valueOf(auxD)
                        .setScale(3, RoundingMode.HALF_UP).doubleValue();
                porcentajes.add(truncatedDouble);
            }
        }
        System.out.println(totales);
        pF.dispose();
    }

    private void calcularModoNormal() {

        if (boardActual.size() == 5) {
            TreeMap<Solucion, Integer> mapa = new TreeMap<>();
            for (Jugador j : jugadores) {
                if (j.getActivo()) {
                    Mano aux = new Mano();
                    mapa.put(aux.calcula(j, boardActual), j.getNumJugador() - 1);
                }
            }
            sumaPuntos(mapa);
            totales++;
            double p = (totales / combTotales) * 100.00;
            if ((p - (int) p) <= 0.001) {
                pF.actualizaPorcentaje((int) p);
            }
        } else {
            llamaCombinacionesNormal();
        }
    }

    private void calcularModoOmaha() {
        if (boardActual.size() == 5) {
            TreeMap<Solucion, Integer> mapa = new TreeMap<>();
            for (JugadorOmaha j : jugadoresOmaha) {
                if (j.getActivo()) {
                    Mano aux = new Mano();
                    mapa.put(aux.calculaO(j, boardActual), j.getNumJugador() - 1);
                }
            }
            sumaPuntos(mapa);
            totales++;
            double p = (totales / combTotales) * 100.00;
            if ((p - (int) p) <= 0.001) {
                pF.actualizaPorcentaje((int) p);
            }
        } else {
            llamaCombinacionesOmaha();
        }
    }

    private void llamaCombinacionesNormal() {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 4; j++) {
                if (!mazoE[i][j]) {
                    cont++;
                    mazoE[i][j] = true;
                    boardActual.addCarta(cont - 1, new Carta(i + 2, Util.numAPalo(j), false));
                    if (cont == 5) {
                        Board auxB = copiaBoard(boardActual);
                        if (!calculado.contains(auxB)) {
                            calculaLugadores(auxB);
                            calculado.add(auxB);
                        }
                    } else {
                        llamaCombinacionesNormal();
                    }
                    boardActual.deleteCarta(cont - 1);
                    mazoE[i][j] = false;
                    cont--;
                }
            }
        }
    }

    private void llamaCombinacionesOmaha() {
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 4; j++) {
                if (!mazoE[i][j]) {
                    cont++;
                    mazoE[i][j] = true;
                    boardActual.addCarta(cont - 1, new Carta(i + 2, Util.numAPalo(j), false));
                    if (cont == 5) {
                        Board auxB = copiaBoard(boardActual);
                        if (!calculado.contains(auxB)) {
                            calculaLugadoresOmaha(auxB);
                            calculado.add(auxB);
                        }
                    } else {
                        llamaCombinacionesOmaha();
                    }
                    boardActual.deleteCarta(cont - 1);
                    mazoE[i][j] = false;
                    cont--;
                }
            }
        }
    }

    private void calculaLugadores(Board board) {
        TreeMap<Solucion, Integer> mapa = new TreeMap<>();
        for (Jugador j : jugadores) {
            if (j.getActivo()) {
                Mano aux = new Mano();
                mapa.put(aux.calcula(j, board), j.getNumJugador() - 1);
            }
        }
        sumaPuntos(mapa);
        totales++;
        double p = (totales / combTotales) * 100.00;
        if ((p - (int) p) <= 0.001) {
            pF.actualizaPorcentaje((int) p);
        }
    }

    private void calculaLugadoresOmaha(Board board) {
        TreeMap<Solucion, Integer> mapa = new TreeMap<>();
        for (JugadorOmaha j : jugadoresOmaha) {
            if (j.getActivo()) {
                Mano aux = new Mano();
                mapa.put(aux.calculaO(j, board), j.getNumJugador() - 1);
            }
        }
        sumaPuntos(mapa);
        totales++;
        double p = (totales / combTotales) * 100.00;
        if ((p - (int) p) <= 0.001) {
            pF.actualizaPorcentaje((int) p);
        }
    }

    private Board copiaBoard(Board boardActual) {
        Board aux = new Board();
        for (Carta c : boardActual.getListaCartas()) {
            aux.addCarta(c);
        }
        aux.ordenar();
        return aux;
    }

    private void sumaPuntos(TreeMap<Solucion, Integer> mapa) {
        ComparadorSoluciones comp = new ComparadorSoluciones();
        int contA = 0;ArrayList<Integer> listaJugGanadores = new ArrayList();
        Map.Entry<Solucion, Integer> entryPrimero = mapa.firstEntry();
        listaJugGanadores.add(entryPrimero.getValue());      
        for (Map.Entry<Solucion, Integer> entry : mapa.entrySet()) {
            if(contA != 0){
                if(comp.sonIguales(entryPrimero.getKey(),entry.getKey())){
                    listaJugGanadores.add(entry.getValue());
                }
            }
            contA++;
        }
        for(Integer i : listaJugGanadores){
            Double auxI = numGanados.get(i);
            auxI = auxI  + (1.0/listaJugGanadores.size());
            numGanados.set(i, auxI);
        }
    }
}
