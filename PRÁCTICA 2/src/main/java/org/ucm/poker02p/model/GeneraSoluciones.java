/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ucm.poker02p.model;

import java.util.ArrayList;
import java.util.TreeMap;

public class GeneraSoluciones {
    
    TreeMap<Integer, ArrayList<Solucion>> soluciones;
        
    
    public GeneraSoluciones(){
        
        
    }

    public TreeMap<Integer, ArrayList<Solucion>> getSoluciones() {
        return soluciones;
    }
    
    
    
    
    public void generaSolucion(Rango rango, Board board){
        SolucionCasilla sC;
        soluciones = new TreeMap<>();
        board.preparaBoard();
        iniMap(soluciones);
        ArrayList<Mano> aux;ArrayList<Solucion> aux2;
        
        aux = rango.getParejas();
        for(Mano a :aux){
            sC = new SolucionCasilla(a,board);
            aux2= soluciones.get(sC.getSolucion().getJugada());
            aux2.add(sC.getSolucion());
            soluciones.put(sC.getSolucion().getJugada(), aux2);
        }
        
        aux = rango.getOffSuited();
        for(Mano a :aux){
            sC = new SolucionCasilla(a,board);
            aux2= soluciones.get(sC.getSolucion().getJugada());
            aux2.add(sC.getSolucion());
            soluciones.put(sC.getSolucion().getJugada(), aux2);
        }
        
        aux = rango.getSuited();
        for(Mano a :aux){
            sC = new SolucionCasilla(a,board);
            aux2= soluciones.get(sC.getSolucion().getJugada());
            aux2.add(sC.getSolucion());
            soluciones.put(sC.getSolucion().getJugada(), aux2);
        }
    }
    
    
    private void iniMap(TreeMap<Integer, ArrayList<Solucion>> soluciones){
        for(int i = 0; i < 18; i++){
            soluciones.put(i, new ArrayList());
        }
    }
}
