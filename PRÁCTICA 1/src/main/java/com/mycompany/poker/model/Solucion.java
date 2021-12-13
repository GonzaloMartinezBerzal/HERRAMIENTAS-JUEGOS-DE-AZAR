/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poker.model;


public class Solucion implements Comparable<Solucion> {
    private int jugada;
    private int tipo; // pareja 1 de full house o doble pareja
    private int tipo2; // segunda pareja para la doble pareja o trio para el full house
    private String mano; // las 5 cartas que has cogido
    private String drawSg, drawSo, drawF;
    
    public Solucion(int jugada, String mano) {
        this.jugada = jugada;
        this.tipo = -1;
        this.mano = mano;
        tipo2 = -1;
        drawSg = null;
        drawSo = null;
        drawF = null;
    }
    public Solucion(int jugada, int tipo, String mano) {
        this.jugada = jugada;
        this.tipo = tipo;
        this.mano = mano;
        tipo2 = -1;
        drawSg = null;
        drawSo = null;
        drawF = null;
    }
    public Solucion(int jugada, int tipo,int tipo2, String mano) {
        this.jugada = jugada;
        this.tipo = tipo;
        this.mano = mano;
        this.tipo2 = tipo2;
        drawSg = null;
        drawSo = null;
        drawF = null;
    }
    public int getJugada() {
        return jugada;
    }

    public int getTipo() {
        return tipo;
    }

    public int getTipo2() {
        return tipo2;
    }
    
    public String getMano() {
        return mano;
    }
    public void setDrawSg(String drawSg){
        this.drawSg = drawSg;
    }
    public void setDrawSo(String drawSo){
        this.drawSo = drawSo;
    }
    public void setDrawF(String drawF){
        this.drawF = drawF;
    }
    public String getDraws(){
        String texto = "";
        
        if(drawSg != null){
            texto += "Draw: Straight Gutshot\n";
        }
        if(drawSo != null){
            texto += "Draw: Straight Open Ended\n";
        }
        if(drawF != null){
            texto += "Draw: Flush\n";
        }
        return texto;
    }
    public String toString(){
        switch(jugada){
                case 1:
                {
                    return "Royal Flush (" + mano + " )";
                }
                
                case 2:
                {
                    
                    return "Straight Flush (" + mano + " )";
                }

                case 3:
                {
                    return "Poker of " + parseaTipo() + " (" + mano + " )";
                }

                case 4:
                {
                    return "Full House of " + parseaTipo() + "and" + parseaTipo2() + " (" + mano + " )";
                }

                case 5:
                {
                    return "Flush (" + mano + " )";
                }

                case 6:
                {
                    return "Straigth (" + mano + ")";
                }

                case 7:
                {
                    return "Three of a kind of " + parseaTipo() + " (" + mano + " )";
                }

                case 8:
                {
                    return "Two Pairs of " + parseaTipo() + "and" + parseaTipo2() + " (" + mano + " )";
                }

                case 9:
                {
                    
                    return "Pair of " + parseaTipo() + " (" + mano + " )";
                }

                case 10:
                {
                    return "High Hand " + parseaTipo() + " (" + mano + " )";
                }

            }
        return "Error";
    }
    
    private String parseaTipo(){
        Integer n;Character aux = 'o';String aux1;
        switch(tipo){
                    case 14:{
                        aux = 'A';
                        aux1 = aux.toString();
                    }
                        
                    break;
                    case 10:{
                        aux = 'T';
                        aux1 = aux.toString();
                    }
                    break;
                    case 11:{
                        aux = 'J';
                        aux1 = aux.toString();
                    }
                    break;
                    case 12:{
                        aux = 'Q';
                        aux1 = aux.toString();
                    }
                    break;
                    case 13:{
                        aux = 'K';
                        aux1 = aux.toString();
                    }
                    break;
                    default:{
                        n = tipo;
                        aux1 = n.toString();
                    }
                        
                }

        return aux1.toString();
    }
    private String parseaTipo2(){
        Integer n;Character aux = 'o';String aux1;
        switch(tipo2){
                    case 14:{
                        aux = 'A';
                        aux1 = aux.toString();
                    }
                        
                    break;
                    case 10:{
                        aux = 'T';
                        aux1 = aux.toString();
                    }
                    break;
                    case 11:{
                        aux = 'J';
                        aux1 = aux.toString();
                    }
                    break;
                    case 12:{
                        aux = 'Q';
                        aux1 = aux.toString();
                    }
                    break;
                    case 13:{
                        aux = 'K';
                        aux1 = aux.toString();
                    }
                    break;
                    default:{
                        n = tipo;
                        aux1 = n.toString();
                    }
                        
                }

        return aux1.toString();
    }

    @Override
    public int compareTo(Solucion o) {
        ComparadorSoluciones comp = new ComparadorSoluciones();
        if(this.jugada > o.getJugada()) return 1;
        else if(this.jugada == o.getJugada()){
            if(this == comp.compara(this, o))
                return 1;
            else
                return -1;
        }
        else
            return -1;
    }
}
