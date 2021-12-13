
package org.ucm.poker3.model.cartas;

public class JugadorOmaha {
    private Carta carta1;
    private Carta carta2;
    private Carta carta3;
    private Carta carta4;
    private boolean activo;
    private int numJugador;
    
    public JugadorOmaha(Carta carta1, Carta carta2, Carta carta3, Carta carta4, int numJugador){
        this.carta1 = carta1;
        this.carta2 = carta2;
        this.carta3 = carta3;
        this.carta4 = carta4;
        
        activo = true;
        this.numJugador = numJugador;
    }
    
    public void setActivo(boolean activo){
        this.activo = activo;
    }

    public Carta getCarta1() {
        return carta1;
    }

    public Carta getCarta2() {
        return carta2;
    }

    public Carta getCarta3() {
        return carta3;
    }

    public Carta getCarta4() {
        return carta4;
    }
    
    public int getNumJugador() {
        return numJugador;
    }
    
    public boolean getActivo(){
        return activo;
    }    
}
