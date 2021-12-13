/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ucm.poker3.model.cartas;

import org.ucm.poker3.utils.Util;

public class Carta  implements Comparable<Carta>{

    private int carta;
    private char palo;
    private boolean Jugador;

    public Carta(int carta, char palo, boolean Jugador) {
        this.carta = carta;
        this.palo = palo;
        this.Jugador = Jugador;
    }

    public int getNum() {
        return carta;
    }

    public char getPalo() {
        return palo;
    }

    public String getCartaImagen() {
        String salida = "";
        salida += "images/cartas/" + Util.cartaAString(carta) + "_of_" + paloAString() + ".png";
        return salida;
    }

    private String paloAString() {
        switch (palo) {
            case 'h':
                return "hearts";
            case 'c':
                return "clubs";
            case 'd':
                return "diamonds";
            case 's':
                return "spades";
            default:
                return "";
        }
    }
    @Override
    public String toString(){
        
        return carta +""+ palo;
    }

    @Override
    public int compareTo(Carta o) {
        if(this.carta > o.carta)
            return 1;
        else if(this.carta == o.carta)
            if(Util.paloANum(this.palo) <= Util.paloANum(o.palo))
                return 1;
            else if(Util.paloANum(this.palo) == Util.paloANum(o.palo))
                return 0;
            else
                return -1;
        else
            return -1;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.carta;
        hash = 17 * hash + this.palo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Carta other = (Carta) obj;
        if (this.carta != other.carta) {
            return false;
        }
        if (this.palo != other.palo) {
            return false;
        }
        return true;
    }
    
}
