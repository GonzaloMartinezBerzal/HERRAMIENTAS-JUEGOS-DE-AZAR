/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poker.model;


import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.List;

public class Mano {
    private Solucion solucion = null;

    public Mano(String jugador, String mesa, int nC, boolean omaha){//nC de entrada es el número de cartas que hay en la mesa
        solucion(jugador , mesa , nC, omaha);
    }
    private void solucion(String jugador, String mesa, int nC, boolean omaha){
        ArrayList<Carta> cartas = new ArrayList<>(); Solucion solActual = null;
        ComparadorSoluciones comp = new ComparadorSoluciones();
        for(int i = 0; i < 2; i++){
            
            cartas.add(new Carta(jugador.charAt(i*2), jugador.charAt(i*2 + 1), true));
        }
        for(int i = 0; i < nC; i++){
            cartas.add(new Carta(mesa.charAt(i*2), mesa.charAt(i*2 + 1), false));
        }
        if(omaha){
            switch (nC) {
                case 3:
                 solActual = buscaJugadas(cartas);
                    solucion = comp.compara(solActual, solucion);
                    break;
                case 4:
                    caso4CartasOmaha(cartas);
                   break;
                default:
                    caso5CartasOmaha(cartas);
                    break;
            }
        }
        else{
            switch (nC) {
                case 3:
                 solActual = buscaJugadas(cartas);
                    solucion = comp.compara(solActual, solucion);
                    break;
                case 4:
                    caso4Cartas(cartas);
                   break;
                default:
                    caso5Cartas(cartas);
                    break;
            }
        }
    }
    

    private Solucion buscaJugadas(ArrayList<Carta> cartas){
        String manoOrd = "";
        Solucion solActual = null;
        Map<Integer,ArrayList<Carta>> repeticiones = new TreeMap<>();
        List<Carta> lista = new ArrayList<>();
        int colH = 0, colD = 0, colC = 0, colS = 0;
        int pareja1 = -1, pareja2 = -1, trio = -1, poker = -1;
        String escalera = null, escaleraReal = null, escaleraColor = null, drawSg = null,drawSo = null, drawF = null;

        for(int i = 0; i < 5; i++){
            ArrayList<Carta> aux1 = new ArrayList<>();
            aux1.add(cartas.get(i));
            aux1 = repeticiones.putIfAbsent(cartas.get(i).getNum(), aux1);
            if(aux1 != null){
                aux1.add(cartas.get(i));
                repeticiones.put(cartas.get(i).getNum(), aux1);
            }
                
           
           switch(cartas.get(i).getPalo()) {
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
        
        for( Map.Entry<Integer, ArrayList<Carta>>  r: repeticiones.entrySet()){
            Integer ke = r.getKey();//hacer que la clave sea el número y el valor una lista de cartas
            ArrayList<Carta> va = r.getValue();
            lista.add(va.get(0));
            for(int i = 0; i < va.size();i++){
                
                manoOrd += va.get(i).toString();
            }
                                           
            switch (va.size()) {
                case 2:
                    if(ke > pareja1){
                        pareja2 = pareja1;
                        pareja1 = ke;
                    }   break;
                case 3:
                    if(ke > trio)
                        trio = ke;
                    break;
                case 4:
                    if(ke > poker)
                        poker = ke;
                    break;
                default:
                    break;
            }
        }
        
        
        if(lista.size() == 5){
            
            if(lista.get(4).getNum() -lista.get(0).getNum()  == 4){//hay escalera
                
                if(lista.get(4).getPalo() == lista.get(3).getPalo()// Escalera de color o Real
                        && lista.get(4).getPalo() == lista.get(2).getPalo()
                        && lista.get(4).getPalo() == lista.get(1).getPalo()
                        && lista.get(4).getPalo() == lista.get(0).getPalo()){
                    
                    if(lista.get(4).getNum() == 10 && lista.get(0).getNum() == 14){//Escalera Real
                        escaleraReal = "RoyalFlush " + manoOrd;
                    }
                    else{//Escalera de color
                        escaleraColor = "Straight Flush " + manoOrd;
                    }
                }
                else{//escalera normal
                    escalera = "Straight " + manoOrd;
                }
            }
            
            else if(lista.get(3).getNum() - lista.get(0).getNum() == 4 || lista.get(4).getNum() - lista.get(1).getNum() == 4){
                drawSg = "gutshot " + manoOrd;
            } 
            else if(lista.get(3).getNum() - lista.get(0).getNum() == 3 || lista.get(4).getNum() - lista.get(1).getNum() == 3){//esto lo tengo que terminar               
                drawSo = "open-ended " + manoOrd;
            }
        }
        else if (lista.size() == 4){
            if(lista.get(3).getNum() -  lista.get(0).getNum()== 4){
                
                    drawSg = "gutshot " + manoOrd;
            }
            else if(lista.get(2).getNum() - lista.get(0).getNum() == 3 || lista.get(3).getNum() - lista.get(1).getNum() == 3){//esto lo tengo que terminar               
                drawSo = "open-ended " + manoOrd;
            }
        }
        
        /*
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
        if(escaleraReal != null){
            solActual = new Solucion(1,escaleraReal);
        }
        else if(escaleraColor != null){
            solActual = new Solucion(2,escaleraColor);
        }
        else if(poker != -1){//poker
            solActual = new Solucion(3, poker, manoOrd);
            
        }
        else if(pareja1 != -1 && trio != -1){        
            //fullhouse 
            solActual = new Solucion(4, pareja1, trio,manoOrd);
        }
        else if(colH == 5){
            //Color de hearts
            solActual = new Solucion(5, 1, manoOrd);
        }
        else if(colD == 5){
            //Color de diamonds
            solActual = new Solucion(5, 2, manoOrd);
        }
        else if(colC == 5){
            //Color de tréboles
            solActual = new Solucion(5, 3, manoOrd);
        }
        else if(colS == 5){
            //Color de picas
            solActual = new Solucion(5, 4, manoOrd);
        }
        else if(escalera != null){
            //escalera
           
            solActual = new Solucion(6, escalera);
            // System.out.println(solActual);
        }
        else if(trio != -1){
            //Trio
            solActual = new Solucion(7, trio, manoOrd);
        }
        else if(pareja1 != -1 && pareja2 != -1){
            //Dobles parejas
            solActual = new Solucion(8, pareja1, pareja2,manoOrd);
        }
        else if(pareja1 != -1){
            //Pareja
            solActual = new Solucion(9, pareja1, manoOrd);
        }
        else{
            //Carta alta
            solActual = new Solucion(10, lista.get(4).getNum(), manoOrd);
        }
        //Proyectos
        if(drawSg != null)
            solActual.setDrawSg(drawSg);
        else if(drawSo != null)
            solActual.setDrawSo(drawSo);
        
        
        if(colH == 4 || colD  == 4 || colC == 4 || colS == 4)//si hay que decir que color es se cambia
            solActual.setDrawF(manoOrd);
        return solActual;
    }
    
    public Solucion getSolucion(){
        return solucion;
    }
    
    private void caso4Cartas(ArrayList<Carta> cartas){
        ArrayList<Carta> cartas5 = new ArrayList<>(); Solucion solActual = null; ComparadorSoluciones comp = new ComparadorSoluciones();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(4));
        solActual = buscaJugadas(cartas5);
        solucion = comp.compara(solActual, solucion);
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(5));
        solActual = buscaJugadas(cartas5);
        solucion = comp.compara(solActual, solucion);
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(4));
        cartas5.add(cartas.get(5));
        solActual = buscaJugadas(cartas5);
        solucion = comp.compara(solActual, solucion);
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(4));
        cartas5.add(cartas.get(5));
        solActual = buscaJugadas(cartas5);
        solucion = comp.compara(solActual, solucion);
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(4));
        cartas5.add(cartas.get(5));
        solActual = buscaJugadas(cartas5);
        solucion = comp.compara(solActual, solucion);
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(2));
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(4));
        cartas5.add(cartas.get(5));
        solActual = buscaJugadas(cartas5);
        solucion = comp.compara(solActual, solucion);
    }
    
    private void caso5Cartas(ArrayList<Carta> cartas){
        Solucion aux;
        ArrayList<Carta> cartas5 = new ArrayList<>(); Solucion solActual = null; ComparadorSoluciones comp = new ComparadorSoluciones();
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
    
    private void caso4CartasOmaha(ArrayList<Carta> cartas){
        
        Solucion aux;
        ArrayList<Carta> cartas5 = new ArrayList<>(); Solucion solActual = null; ComparadorSoluciones comp = new ComparadorSoluciones();
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
    }
    
    private void caso5CartasOmaha(ArrayList<Carta> cartas){
        Solucion aux;
        ArrayList<Carta> cartas5 = new ArrayList<>(); Solucion solActual = null; ComparadorSoluciones comp = new ComparadorSoluciones();
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
        cartas5.add(cartas.get(3));
        cartas5.add(cartas.get(6));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(2));//4
        cartas5.add(cartas.get(4));
        cartas5.add(cartas.get(5));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(2));//5
        cartas5.add(cartas.get(4));
        cartas5.add(cartas.get(6));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(2));//6
        cartas5.add(cartas.get(5));
        cartas5.add(cartas.get(6));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(3));//7
        cartas5.add(cartas.get(4));
        cartas5.add(cartas.get(5));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(3));//8
        cartas5.add(cartas.get(4));
        cartas5.add(cartas.get(6));
        solActual = buscaJugadas(cartas5);
        aux = comp.compara(solActual, solucion);
        solucion = aux;
        
        cartas5 = new ArrayList<>();
        cartas5.add(cartas.get(0));
        cartas5.add(cartas.get(1));
        cartas5.add(cartas.get(3));//9
        cartas5.add(cartas.get(5));
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
    
    }
}