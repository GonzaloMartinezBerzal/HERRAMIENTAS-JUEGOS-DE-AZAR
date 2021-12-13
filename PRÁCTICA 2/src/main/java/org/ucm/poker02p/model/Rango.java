/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ucm.poker02p.model;

import java.util.ArrayList;
import org.ucm.poker02p.control.Controller;


public class Rango implements Observer{
    ArrayList<Mano> parejas;
    ArrayList<Mano> suited;
    ArrayList<Mano> offSuited;
    
    public Rango(Controller cntr) {
        cntr.addObserver(this);
        parejas = new ArrayList();
        suited = new ArrayList();
        offSuited = new ArrayList();
    }

    public ArrayList<Mano> getParejas() {
        return parejas;
    }

    public ArrayList<Mano> getSuited() {
        return suited;
    }

    public ArrayList<Mano> getOffSuited() {
        return offSuited;
    }
    
    private void addMano(Mano mano){
        switch(mano.getTipo()){
            case 'p':{
                parejas.add(mano);
            }
            break;
            case 's':{
                suited.add(mano);
            }
            break;
            case 'o':{
                offSuited.add(mano);
            }
            break;
        }
    }
    
    private void removeMano(Mano mano){
        switch(mano.getTipo()){
            case 'p':{
                parejas.remove(mano);
            }
            break;
            case 's':{
                suited.remove(mano);
            }
            break;
            case 'o':{
                offSuited.remove(mano);
            }
            break;
        }
    }

    @Override
    public String toString() {
        return "Rango{" + "parejas=" + parejas + ", suited=" + suited + ", offSuited=" + offSuited + '}';
    }
    
    
    @Override
    public void onRegister() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onReset() {
        parejas = new ArrayList();
        suited = new ArrayList();
        offSuited = new ArrayList();
    }

    @Override
    public void onRangeChanged(ArrayList<Mano> lista) {
        parejas = new ArrayList();
        suited = new ArrayList();
        offSuited = new ArrayList();
        for(Mano manos : lista){
            addMano(manos);
        }
    }

    @Override
    public void onAdvance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onCuadritoChanged(Mano mano, boolean seleccionado) {
        
        if(seleccionado)
            addMano(mano);
        else
            removeMano(mano);
    }

    @Override
    public void activaRanking(boolean activaRanking) {
        parejas = new ArrayList();
        suited = new ArrayList();
        offSuited = new ArrayList();
    }

    @Override
    public void onRankingChanged(java.util.List<Mano> lista) {
        parejas = new ArrayList();
        suited = new ArrayList();
        offSuited = new ArrayList();
        for(Mano manos : lista){
            addMano(manos);
        }
    }

    @Override
    public void activaJugadas(boolean jugadasActivado) {
        parejas = new ArrayList();
        suited = new ArrayList();
        offSuited = new ArrayList();
    }

    @Override
    public void onCuadritoBoardChanged(Carta carta, boolean seleccionado) {
        
    }

    @Override
    public void onBoardChanged(ArrayList<Carta> lista) {
        
    }
    
}
