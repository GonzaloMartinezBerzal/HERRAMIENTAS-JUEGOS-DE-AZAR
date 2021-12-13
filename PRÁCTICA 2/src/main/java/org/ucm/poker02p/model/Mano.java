/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ucm.poker02p.model;

public class Mano implements Comparable<Mano> {

    private int carta1;
    private int carta2;
    private char tipo;
    private String escrito;

    public Mano(int carta1, int carta2, char tipo) {
        this.carta1 = carta1;
        this.carta2 = carta2;
        this.tipo = tipo;
        generaEscrito();
    }

    public Mano(String escrito) {
        this.escrito = escrito;
        generaResto();
    }

    private void generaEscrito() {
        String sol = "";//Character auxC;
        if (carta1 >= carta2) {
            sol += cartaAString(carta1);
            sol += cartaAString(carta2);
        } else {
            sol += cartaAString(carta2);
            sol += cartaAString(carta1);
        }
        //auxC = tipo;
        if (tipo != 'p') {
            sol += tipo;
        }
        escrito = sol;
    }

    public char getTipo() {
        return tipo;
    }

    public int getCarta1() {
        return carta1;
    }

    public int getCarta2() {
        return carta2;
    }

    private String cartaAString(int carta) {
        switch (carta) {
            case 14: {
                return "A";
            }
            case 13: {
                return "K";
            }
            case 12: {
                return "Q";
            }
            case 11: {
                return "J";
            }
            case 10: {
                return "T";
            }
            default: {
                Integer aux;
                aux = carta;
                return aux.toString();
            }
        }
    }

    private void generaResto() {
        //mirar las k j q a t
        char aux = escrito.charAt(0);
        carta1 = Character.getNumericValue(aux);
        aux = escrito.charAt(1);
        carta2 = Character.getNumericValue(aux);
        if (carta1 != carta2) {
            tipo = escrito.charAt(2);
        } else {
            tipo = 'p';
        }
    }

    @Override
    public String toString() {
        return escrito;
    }

    @Override
    public int compareTo(Mano o) {
        if (this.tipo == 's') {
            if (this.carta2 > o.getCarta2()) {
                return 1; //mayor
            } else if (this.carta2 < o.getCarta2()) {
                return -1; //menor             
            } else {
                if (tipo == 'p') {
                    return 0;
                } else {
                    if (this.carta1 > o.getCarta1()) {
                        return 1; //mayor por la 2 carta
                    } else if (this.carta1 < o.getCarta1()) {
                        return -1; //menor por la 2 carta           
                    } else {
                        return -1; //igual ambas cartas
                    }
                }
            }
        } else {
            if (this.carta1 > o.getCarta1()) {
                return 1; //mayor
            } else if (this.carta1 < o.getCarta1()) {
                return -1; //menor             
            } else {
                if (tipo == 'p') {
                    return 0;
                } else {
                    if (this.carta2 > o.getCarta2()) {
                        return 1; //mayor por la 2 carta
                    } else if (this.carta2 < o.getCarta2()) {
                        return -1; //menor por la 2 carta           
                    } else {
                        return -1; //igual ambas cartas
                    }
                }
            }
        }

    }

}
