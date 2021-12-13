package org.ucm.poker3.model.observer;

import java.util.ArrayList;
import org.ucm.poker3.model.cartas.Board;
import org.ucm.poker3.model.cartas.Jugador;
import org.ucm.poker3.model.cartas.JugadorOmaha;

public interface Observer {
    public void actualizaEquity(ArrayList<Double> porcentajes);
    public void hacerFold(int numJug);
    public void meterCartaJug(Jugador j);
    public void meterCartaJugOmaha(JugadorOmaha j);
    public void meterBoard(Board board);
    public void reset();
}
