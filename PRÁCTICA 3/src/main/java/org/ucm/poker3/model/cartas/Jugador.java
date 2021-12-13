
package org.ucm.poker3.model.cartas;


public class Jugador {
    private Carta carta1;
    private Carta carta2;
    private Double equity;
    private boolean activo;
    private int numJugador;
    
    public Jugador(Carta carta1, Carta carta2, int numJugador){
        this.carta1 = carta1;
        this.carta2 = carta2;
        equity = 0.0;
        activo = true;
        this.numJugador = numJugador;
    }
    
    public void setActivo(boolean activo){
        this.activo = activo;
    }
    public boolean getActivo(){
        return activo;
    }

    public Carta getCarta1() {
        return carta1;
    }

    public Carta getCarta2() {
        return carta2;
    }

    public Double getEquity() {
        return equity;
    }

    public void setEquity(Double equity) {
        this.equity = equity;
    }

    public int getNumJugador() {
        return numJugador;
    }
    
}
