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


public class PanelPorcentaje  extends JTextField implements Observer{
    private Double porcentaje;
    private Controller ctrl;
    private final double TOTAL = 169;
    private double actuales;

    public PanelPorcentaje(Controller ctrl) {
        
        actuales = 0;
        this.ctrl = ctrl;
        porcentaje = 0.0;
        ctrl.addObserver(this);
        setSize(new Dimension(80, 35));
        setLocation(1, 1);
        setBackground(Color.WHITE);
        this.setVisible(true);
        this.setText(porcentaje.toString());
    }

    @Override
    public void onRegister() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onReset() {
        porcentaje = 0.0;actuales = 0;
        this.setText(porcentaje.toString());
    }

    @Override
    public void onRangeChanged(ArrayList<Mano> lista) {
        actuales = lista.size();
        porcentaje = (100 * (actuales / TOTAL));
        porcentaje = Math.floor(porcentaje * 100) / 100;
        this.setText(porcentaje.toString());
    }

    @Override
    public void onAdvance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onCuadritoChanged(Mano mano, boolean seleccionado) {
        
        if(seleccionado)
            actuales++;
        else
            actuales--;
        porcentaje = (100 * (actuales / TOTAL));
        porcentaje = Math.floor(porcentaje * 100) / 100;
        this.setText(porcentaje.toString());
    }

    @Override
    public void activaRanking(boolean activaRanking) {
        porcentaje = 0.0;actuales = 0;
        this.setText(porcentaje.toString());
    }

    @Override
    public void onRankingChanged(java.util.List<Mano> lista) {
        actuales = lista.size();
        porcentaje = (100 * (actuales / TOTAL));
        porcentaje = Math.floor(porcentaje * 100) / 100;
        this.setText(porcentaje.toString());
    }

    @Override
    public void activaJugadas(boolean jugadasActivado) {
        porcentaje = 0.0;actuales = 0;
        this.setText(porcentaje.toString());
    }

    @Override
    public void onCuadritoBoardChanged(Carta carta, boolean seleccionado) {
        
    }

    @Override
    public void onBoardChanged(ArrayList<Carta> lista) {
        
    }
    
}
