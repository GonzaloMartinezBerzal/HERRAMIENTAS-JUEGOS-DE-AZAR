/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ucm.poker02p.control;

import java.util.ArrayList;
import org.ucm.poker02p.model.Carta;
import org.ucm.poker02p.model.Mano;
import org.ucm.poker02p.model.Observer;
import org.ucm.poker02p.model.Simulator;


public class Controller {
    private Simulator sim;

    public Controller(Simulator sim) {
        this.sim = sim;
    }
    
    public void reset(){
        sim.reset();
    }
    public void rangeChanged(ArrayList<Mano> list){
        sim.rangeChanged(list);
    }
    public void addObserver(Observer o) {
	sim.addObserver(o);
    }

    public void cuadritoChanged(Mano mano, boolean seleccionado) {
        sim.cuadritoChanged(mano, seleccionado);
    }

    public void activaRanking(boolean rankingActivado) {
        sim.activaRanking(rankingActivado);
    }
    public void rankingChanged(java.util.List<Mano> lista){
        sim.rankingChanged(lista);
    }

    public void activaJugadas(boolean jugadasActivado) {
        sim.activaJugadas(jugadasActivado);
    }

    public void cuadritoBoardChanged(Carta carta, boolean seleccionado) {
        sim.cuadritoBoardChanged(carta, seleccionado);
    }

    public void boardChanged(ArrayList<Carta> list) {
        sim.boardChanged(list);
    }
}
