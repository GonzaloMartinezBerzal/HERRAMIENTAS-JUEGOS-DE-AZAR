package org.ucm.poker3.model.equity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.ucm.poker3.model.cartas.Board;
import org.ucm.poker3.model.cartas.Carta;
import org.ucm.poker3.model.cartas.Jugador;
import org.ucm.poker3.model.cartas.JugadorOmaha;

public class Mano {

    Solucion solucion;

    public Mano() {
        solucion = new Solucion(11, null);
    }

    public Solucion calcula(Jugador j, Board b) {
        
            ArrayList<Carta> cartas = new ArrayList();
            cartas.add(j.getCarta1());
            cartas.add(j.getCarta2());
            for (Carta c : b.getListaCartas()) {
                cartas.add(c);
            }
            caso5Cartas(cartas);
        

        return solucion;
    }
    
    public Solucion calculaO(JugadorOmaha j, Board b) {
        
            ArrayList<Carta> cartas = new ArrayList();
            cartas.add(j.getCarta1());
            cartas.add(j.getCarta2());
            cartas.add(j.getCarta3());
            cartas.add(j.getCarta4());
            for (Carta c : b.getListaCartas()) {
                cartas.add(c);
            }
            caso5CartasOmaha(cartas);
        

        return solucion;
    }

    private Solucion buscaJugadas(ArrayList<Carta> cartas) {
        String manoOrd = "";
        Solucion solActual = null;
        Map<Integer, ArrayList<Carta>> repeticiones = new TreeMap<>();
        List<Carta> lista = new ArrayList<>();
        int colH = 0, colD = 0, colC = 0, colS = 0;
        int pareja1 = -1, pareja2 = -1, trio = -1, poker = -1;
        String escalera = null, escaleraReal = null, escaleraColor = null, drawSg = null, drawSo = null, drawF = null;

        for (int i = 0; i < 5; i++) {
            ArrayList<Carta> aux1 = new ArrayList<>();
            aux1.add(cartas.get(i));
            aux1 = repeticiones.putIfAbsent(cartas.get(i).getNum(), aux1);
            if (aux1 != null) {
                aux1.add(cartas.get(i));
                repeticiones.put(cartas.get(i).getNum(), aux1);
            }

            switch (cartas.get(i).getPalo()) {
                case 'h':
                    colH++;
                    break;
                case 'd':
                    colD++;
                    break;
                case 'c':
                    colC++;
                    break;
                case 's':
                    colS++;
                    break;
            }

        }

        for (Map.Entry<Integer, ArrayList<Carta>> r : repeticiones.entrySet()) {
            Integer ke = r.getKey();//hacer que la clave sea el número y el valor una lista de cartas
            ArrayList<Carta> va  = r.getValue();
            lista.add(va.get(0));
            for (int i = 0; i < va.size(); i++) {

                manoOrd += va.get(i).toString();
            }

            switch (va.size()) {
                case 2:
                    if (ke > pareja1) {
                        pareja2 = pareja1;
                        pareja1 = ke;
                    }
                    break;
                case 3:
                    if (ke > trio) {
                        trio = ke;
                    }
                    break;
                case 4:
                    if (ke > poker) {
                        poker = ke;
                    }
                    break;
                default:
                    break;
            }
        }

        if (lista.size() == 5) {
            if(lista.get(4).getNum() == 14 && lista.get(0).getNum() == 2 &&
                    lista.get(3).getNum() - lista.get(0).getNum() == 3 ){
                if (lista.get(4).getPalo() == lista.get(3).getPalo()
                        && lista.get(4).getPalo() == lista.get(2).getPalo()
                        && lista.get(4).getPalo() == lista.get(1).getPalo()
                        && lista.get(4).getPalo() == lista.get(0).getPalo()) {
                    escaleraColor = "1" + lista.get(4).getPalo() 
                            + "2" + lista.get(0).getPalo()
                            + "3" + lista.get(1).getPalo()
                            + "4" + lista.get(2).getPalo()
                            + "5" + lista.get(3).getPalo();
                }
                else{
                    escalera = "1" + lista.get(4).getPalo() 
                            + "2" + lista.get(0).getPalo()
                            + "3" + lista.get(1).getPalo()
                            + "4" + lista.get(2).getPalo()
                            + "5" + lista.get(3).getPalo();
                }
            }

            else if (lista.get(4).getNum() - lista.get(0).getNum() == 4) {//hay escalera

                if (lista.get(4).getPalo() == lista.get(3).getPalo()// Escalera de color o Real
                        && lista.get(4).getPalo() == lista.get(2).getPalo()
                        && lista.get(4).getPalo() == lista.get(1).getPalo()
                        && lista.get(4).getPalo() == lista.get(0).getPalo()) {

                    if (lista.get(0).getNum() == 10 && lista.get(4).getNum() == 14) {//Escalera Real
                        escaleraReal = manoOrd;
                    } else {//Escalera de color
                        escaleraColor = manoOrd;
                    }
                } else {//escalera normal
                    escalera = manoOrd;
                }
            }
        }/*
        1. Escalera real de color (cartas 10, J, Q, K y A del mismo palo)
        2. Escalera de color
        3. Poker
        4. Full house
        5. Color
        6. Escalera
        7. Trio
        8. Dobles parejas
        9. Parejas
        10. Carta alta
         */
        if (escaleraReal != null) {
            solActual = new Solucion(1, escaleraReal);
        } else if (escaleraColor != null) {
            solActual = new Solucion(2, escaleraColor);
        } else if (poker != -1) {//poker
            solActual = new Solucion(3, poker, manoOrd);

        } else if (pareja1 != -1 && trio != -1) {
            //fullhouse 
            solActual = new Solucion(4, pareja1, trio, manoOrd);
        } else if (colH == 5) {
            //Color de hearts
            solActual = new Solucion(5, 1, manoOrd);
        } else if (colD == 5) {
            //Color de diamonds
            solActual = new Solucion(5, 2, manoOrd);
        } else if (colC == 5) {
            //Color de tréboles
            solActual = new Solucion(5, 3, manoOrd);
        } else if (colS == 5) {
            //Color de picas
            solActual = new Solucion(5, 4, manoOrd);
        } else if (escalera != null) {
            //escalera

            solActual = new Solucion(6, escalera);
            // System.out.println(solActual);
        } else if (trio != -1) {
            //Trio
            solActual = new Solucion(7, trio, manoOrd);
        } else if (pareja1 != -1 && pareja2 != -1) {
            //Dobles parejas
            solActual = new Solucion(8, pareja1, pareja2, manoOrd);
        } else if (pareja1 != -1) {
            //Pareja
            solActual = new Solucion(9, pareja1, manoOrd);
        } else {
            //Carta alta
            solActual = new Solucion(10, lista.get(lista.size() - 1).getNum(), manoOrd);
        }
        //Proyectos
        /*
        if(drawSg != null)
            solActual.setDrawSg(drawSg);
        else if(drawSo != null)
            solActual.setDrawSo(drawSo);
        
        
        if(colH == 4 || colD  == 4 || colC == 4 || colS == 4)//si hay que decir que color es se cambia
            solActual.setDrawF(manoOrd);*/
        return solActual;
    }

    private void caso5Cartas(ArrayList<Carta> cartas) {
        Solucion aux;
        ArrayList<Carta> cartas5 = new ArrayList<>();
        Solucion solActual = null;
        ComparadorSoluciones comp = new ComparadorSoluciones();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(2));//1
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(4));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;

        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(2));//2
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(5));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;

        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(2));//3
        cartas5.add(cartas.get(4));
        cartas5.add(cartas.get(5));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;

        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(3));//4
        cartas5.add(cartas.get(4));
        cartas5.add(cartas.get(5));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;

        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(3));//5
        cartas5.add(cartas.get(4));
        cartas5.add(cartas.get(5));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;

        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(3));//6
        cartas5.add(cartas.get(4));
        cartas5.add(cartas.get(5));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;

        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(2));//7
        cartas5.add(cartas.get(4));
        cartas5.add(cartas.get(6));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;

        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(2));//8
        cartas5.add(cartas.get(5));
        cartas5.add(cartas.get(6));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;

        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(3));//9
        cartas5.add(cartas.get(4));
        cartas5.add(cartas.get(6));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;

        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(4));//10
        cartas5.add(cartas.get(5));
        cartas5.add(cartas.get(6));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;

        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(2));//11
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(6));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;

        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(3));//12
        cartas5.add(cartas.get(4));
        cartas5.add(cartas.get(6));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;

        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(4));//13
        cartas5.add(cartas.get(5));
        cartas5.add(cartas.get(6));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;

        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(3));//14
        cartas5.add(cartas.get(4));
        cartas5.add(cartas.get(6));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;

        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(4));//15
        cartas5.add(cartas.get(5));
        cartas5.add(cartas.get(6));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;

        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(4));//16
        cartas5.add(cartas.get(5));
        cartas5.add(cartas.get(6));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;

        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(3));//17
        cartas5.add(cartas.get(5));
        cartas5.add(cartas.get(6));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;

        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(0));//18
        cartas5.add(cartas.get(5));
        cartas5.add(cartas.get(6));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;

        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(4));//19
        cartas5.add(cartas.get(5));
        cartas5.add(cartas.get(6));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;

        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(4));//20
        cartas5.add(cartas.get(5));
        cartas5.add(cartas.get(6));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;

        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(1));//21
        cartas5.add(cartas.get(5));
        cartas5.add(cartas.get(6));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
    }
    
    private void caso5CartasOmaha(ArrayList<Carta> cartas){
        Solucion aux;//Jugador: 0 1 2 3 Board: 4 5 6 7 8
        ArrayList<Carta> cartas5 = new ArrayList<>(); Solucion solActual = null; ComparadorSoluciones comp = new ComparadorSoluciones();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(4));//1
        cartas5.add(cartas.get(5));
        cartas5.add(cartas.get(6));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(4));//2
        cartas5.add(cartas.get(5));
        cartas5.add(cartas.get(7));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(4));//3
        cartas5.add(cartas.get(5));
        cartas5.add(cartas.get(8));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(4));//4
        cartas5.add(cartas.get(6));
        cartas5.add(cartas.get(7));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(4));//5
        cartas5.add(cartas.get(6));
        cartas5.add(cartas.get(8));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(5));//6
        cartas5.add(cartas.get(6));
        cartas5.add(cartas.get(7));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(4));//7
        cartas5.add(cartas.get(7));
        cartas5.add(cartas.get(8));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(6));//8
        cartas5.add(cartas.get(7));
        cartas5.add(cartas.get(8));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(5));//9
        cartas5.add(cartas.get(7));
        cartas5.add(cartas.get(8));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(5));//10
        cartas5.add(cartas.get(6));
        cartas5.add(cartas.get(8));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(4));//1
        cartas5.add(cartas.get(5));
        cartas5.add(cartas.get(6));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(4));//2
        cartas5.add(cartas.get(5));
        cartas5.add(cartas.get(7));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(4));//3
        cartas5.add(cartas.get(5));
        cartas5.add(cartas.get(8));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(4));//4
        cartas5.add(cartas.get(6));
        cartas5.add(cartas.get(7));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(4));//5
        cartas5.add(cartas.get(6));
        cartas5.add(cartas.get(8));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(5));//6
        cartas5.add(cartas.get(6));
        cartas5.add(cartas.get(7));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(4));//7
        cartas5.add(cartas.get(7));
        cartas5.add(cartas.get(8));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(6));//8
        cartas5.add(cartas.get(7));
        cartas5.add(cartas.get(8));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(5));//9
        cartas5.add(cartas.get(7));
        cartas5.add(cartas.get(8));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(5));//10
        cartas5.add(cartas.get(6));
        cartas5.add(cartas.get(8));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        
        
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(4));//1
        cartas5.add(cartas.get(5));
        cartas5.add(cartas.get(6));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(4));//2
        cartas5.add(cartas.get(5));
        cartas5.add(cartas.get(7));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(4));//3
        cartas5.add(cartas.get(5));
        cartas5.add(cartas.get(8));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(4));//4
        cartas5.add(cartas.get(6));
        cartas5.add(cartas.get(7));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(4));//5
        cartas5.add(cartas.get(6));
        cartas5.add(cartas.get(8));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(5));//6
        cartas5.add(cartas.get(6));
        cartas5.add(cartas.get(7));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(4));//7
        cartas5.add(cartas.get(7));
        cartas5.add(cartas.get(8));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(6));//8
        cartas5.add(cartas.get(7));
        cartas5.add(cartas.get(8));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(5));//9
        cartas5.add(cartas.get(7));
        cartas5.add(cartas.get(8));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(5));//10
        cartas5.add(cartas.get(6));
        cartas5.add(cartas.get(8));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
    
        
        
        
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(4));//1
        cartas5.add(cartas.get(5));
        cartas5.add(cartas.get(6));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(4));//2
        cartas5.add(cartas.get(5));
        cartas5.add(cartas.get(7));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(4));//3
        cartas5.add(cartas.get(5));
        cartas5.add(cartas.get(8));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(4));//4
        cartas5.add(cartas.get(6));
        cartas5.add(cartas.get(7));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(4));//5
        cartas5.add(cartas.get(6));
        cartas5.add(cartas.get(8));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(5));//6
        cartas5.add(cartas.get(6));
        cartas5.add(cartas.get(7));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(4));//7
        cartas5.add(cartas.get(7));
        cartas5.add(cartas.get(8));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(6));//8
        cartas5.add(cartas.get(7));
        cartas5.add(cartas.get(8));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(5));//9
        cartas5.add(cartas.get(7));
        cartas5.add(cartas.get(8));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(5));//10
        cartas5.add(cartas.get(6));
        cartas5.add(cartas.get(8));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        
        
        
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(4));//1
        cartas5.add(cartas.get(5));
        cartas5.add(cartas.get(6));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(4));//2
        cartas5.add(cartas.get(5));
        cartas5.add(cartas.get(7));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(4));//3
        cartas5.add(cartas.get(5));
        cartas5.add(cartas.get(8));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(4));//4
        cartas5.add(cartas.get(6));
        cartas5.add(cartas.get(7));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(4));//5
        cartas5.add(cartas.get(6));
        cartas5.add(cartas.get(8));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(5));//6
        cartas5.add(cartas.get(6));
        cartas5.add(cartas.get(7));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(4));//7
        cartas5.add(cartas.get(7));
        cartas5.add(cartas.get(8));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(6));//8
        cartas5.add(cartas.get(7));
        cartas5.add(cartas.get(8));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(5));//9
        cartas5.add(cartas.get(7));
        cartas5.add(cartas.get(8));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(5));//10
        cartas5.add(cartas.get(6));
        cartas5.add(cartas.get(8));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        
        
        
        
        
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(4));//1
        cartas5.add(cartas.get(5));
        cartas5.add(cartas.get(6));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(4));//2
        cartas5.add(cartas.get(5));
        cartas5.add(cartas.get(7));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(4));//3
        cartas5.add(cartas.get(5));
        cartas5.add(cartas.get(8));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(4));//4
        cartas5.add(cartas.get(6));
        cartas5.add(cartas.get(7));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(4));//5
        cartas5.add(cartas.get(6));
        cartas5.add(cartas.get(8));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(5));//6
        cartas5.add(cartas.get(6));
        cartas5.add(cartas.get(7));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(4));//7
        cartas5.add(cartas.get(7));
        cartas5.add(cartas.get(8));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(6));//8
        cartas5.add(cartas.get(7));
        cartas5.add(cartas.get(8));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(5));//9
        cartas5.add(cartas.get(7));
        cartas5.add(cartas.get(8));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(5));//10
        cartas5.add(cartas.get(6));
        cartas5.add(cartas.get(8));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
    }
}
