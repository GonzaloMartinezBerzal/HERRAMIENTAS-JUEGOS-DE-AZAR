/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ucm.poker02p.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JSlider;
import org.ucm.poker02p.control.Controller;
import org.ucm.poker02p.model.Carta;
import org.ucm.poker02p.model.Mano;
import org.ucm.poker02p.model.Observer;
import org.ucm.poker02p.model.Ranking;

public class SliderPorcentaje extends JSlider implements Observer {

    private Controller ctrl;
    private Ranking rank;

    public SliderPorcentaje(Controller ctrl) {
        rank = new Ranking();
        this.ctrl = ctrl;
        ctrl.addObserver(this);
        setSize(new Dimension(510, 27));//37 520
        setLocation(5, 5);
        setBackground(Color.WHITE);
        this.setVisible(true);
        this.setValue(0);
        setEnabled(false);

        //hacer aqui el listener
        iniciaListener();
        
    }

    @Override
    public void onRegister() {

    }

    @Override
    public void onReset() {
        this.setValue(0);
    }

    @Override
    public void onRangeChanged(ArrayList<Mano> lista) {

    }

    @Override
    public void onAdvance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onCuadritoChanged(Mano mano, boolean seleccionado) {

    }

    @Override
    public void activaRanking(boolean rankingActivado) {
        if (rankingActivado) {
            setEnabled(true);
        } else {
            this.setValue(0);
            setEnabled(false);
        }
    }

    @Override
    public void onRankingChanged(java.util.List<Mano> lista) {
        
    }

    private void iniciaListener() {
        
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ctrl.rankingChanged(rank.getRankListActual(((JSlider)evt.getSource()).getValue()));
            }
        });
        
        
    }

    @Override
    public void activaJugadas(boolean jugadasActivado) {
        
    }

    @Override
    public void onCuadritoBoardChanged(Carta carta, boolean seleccionado) {
        
    }

    @Override
    public void onBoardChanged(ArrayList<Carta> lista) {
        
    }

}
