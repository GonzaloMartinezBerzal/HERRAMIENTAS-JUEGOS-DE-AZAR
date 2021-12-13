/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ucm.poker02p.view;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JTextField;
import org.ucm.poker02p.control.Controller;
import org.ucm.poker02p.model.Carta;
import org.ucm.poker02p.model.Mano;
import org.ucm.poker02p.model.Observer;
import org.ucm.poker02p.model.Rango;
import org.ucm.poker02p.model.TraduceMano;


public class PanelRango extends JTextField implements Observer{

    private Rango rango;
    private Controller cntr;
    private TraduceMano tM;
    public PanelRango(Controller cntr) {
        
        tM = new TraduceMano();
        rango = new Rango(cntr);
        cntr.addObserver(this);
        setSize(new Dimension(973, 30));//40 983
        setLocation(5, 5);
        setBackground(Color.WHITE);
        this.setVisible(true);
        this.setText("");
        
    }
    
    
    public Rango getRango(){
        return rango;
    }
    
    @Override
    public void onRegister() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onReset() {
        this.setText("");
    }

    @Override
    public void onRangeChanged(ArrayList<Mano> lista) {
        
        this.setText(tM.tablaToTexto(rango));//rango.toString()
        
    }

    @Override
    public void onAdvance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onCuadritoChanged(Mano mano, boolean seleccionado) {
        this.setText(tM.tablaToTexto(rango));//rango.toString()
    }

    @Override
    public void activaRanking(boolean activaRanking) {
        this.setText("");
    }

    @Override
    public void onRankingChanged(java.util.List<Mano> lista) {
        this.setText(tM.tablaToTexto(rango));
    }

    @Override
    public void activaJugadas(boolean jugadasActivado) {
        this.setText("");
    }

    @Override
    public void onCuadritoBoardChanged(Carta carta, boolean seleccionado) {
        
    }

    @Override
    public void onBoardChanged(ArrayList<Carta> lista) {
        
    }
    
}
