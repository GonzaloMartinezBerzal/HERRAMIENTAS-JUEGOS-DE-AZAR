/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ucm.poker02p.model;


public class Solucion implements Comparable<Solucion> {
    private int jugada;
    private int numComb;
    private String rango;
    
    
    public Solucion(int jugada, int numComb, String rango) {
        this.jugada = jugada;
        this.numComb = numComb;
        this.rango = rango;
    }
    public int getJugada(){
        return jugada;
    }

    public int getNumComb() {
        return numComb;
    }

    public String getRango() {
        return rango;
    }
    

    @Override
    public int compareTo(Solucion o) {
        
        if(this.jugada > o.getJugada()) return 1;
        else if(this.jugada == o.getJugada())
            return 0;
        else
            return -1;
    }
}
