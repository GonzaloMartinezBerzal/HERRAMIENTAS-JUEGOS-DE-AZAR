
package org.ucm.poker02p.view;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import org.ucm.poker02p.model.Solucion;


public class PanelGraficas extends JPanel{
    private TreeMap<Integer, ArrayList<Solucion>> soluciones;
    private ArrayList<JProgressBar> listaBarras;
    private ArrayList<JLabel> listaLabels;
    private ArrayList<JLabel> nombres;
    private ArrayList<Integer> numC;
    private int total;
    

    public PanelGraficas(TreeMap<Integer, ArrayList<Solucion>> soluciones) {
        total = 0;
        numC = new ArrayList();
        this.soluciones = soluciones;
        listaBarras = new ArrayList();
        listaLabels = new ArrayList();
        nombres = new ArrayList();
        setSize(new Dimension(830, 540));//550 840
        setBackground(java.awt.Color.WHITE);
        this.setVisible(true);
        setLocation(5, 5);
        dibujaBarras();
        dibujaSoluciones();
    }
    
    
    private void dibujaBarras(){
        for(int i = 0; i <= 17; i++){
            nombres.add(new JLabel());
            meteNombre(nombres.get(i),i);
            nombres.get(i).setPreferredSize(new Dimension(100, 20));
            nombres.get(i).setLocation(10, 5 * (i+1));
            this.add(nombres.get(i));
            
            
            listaBarras.add(new JProgressBar());
            //listaBarras.get(i).setSize(new Dimension(400, 20));
            listaBarras.get(i).setPreferredSize(new Dimension(400, 20));
            listaBarras.get(i).setStringPainted(true);
            listaBarras.get(i).setValue(0);
            listaBarras.get(i).setLocation(105, 5 * (i+1));
            this.add(listaBarras.get(i));
            
            listaLabels.add(new JLabel());
            listaLabels.get(i).setPreferredSize(new Dimension(300, 20));
            listaLabels.get(i).setText("0");
            listaLabels.get(i).setLocation(505, 5 * i);
            this.add(listaLabels.get(i));
            
            numC.add(0);
            
        }
    }
    
    private void dibujaSoluciones(){
        for(Map.Entry<Integer, ArrayList<Solucion>>  s : soluciones.entrySet()){
            Integer ke = s.getKey();
            ArrayList<Solucion> va = s.getValue();
            String sA = "";
            for(Solucion so : va){
                int aux = numC.get(ke);
                aux += so.getNumComb();
                total += so.getNumComb();
                numC.set(ke, aux);
                sA += so.getRango() + "(" + so.getNumComb() + ") ";
            }
            sA += "Total: " + numC.get(ke);
            listaLabels.get(ke).setText(sA);
        }
        for(int i = 0; i <= 17; i++){
            if(numC.get(i) != 0){
                Double aux = (double) numC.get(i) / total;
                aux = aux *100;
                listaBarras.get(i).setValue(aux.intValue());
            }
                
        }
    }
    
    
    private void meteNombre(JLabel lab,int i){
        switch(i){
            case 0:{
                lab.setText("Escalera Real    ");
            }
            break;
            case 1:{
                lab.setText("Escalera de color");
            }
            break;
            case 2:{
                lab.setText("Poker            ");
            }
            break;
            case 3:{
                lab.setText("Full house       ");
            }
            break;
            case 4:{
                lab.setText("Color            ");
            }
            break;
            case 5:{
                lab.setText("Escalera         ");
            }
            break;
            case 6:{
                lab.setText("Trio             ");
            }
            break;
            case 7:{
                lab.setText("Doble Pareja     ");
            }
            break;
            case 8:{
                lab.setText("Over Pair        ");
            }
            break;
            case 9:{
                lab.setText("Top Pair         ");
            }
            break;
            case 10:{
                lab.setText("Pp below tp      ");
            }
            break;
            case 11:{
                lab.setText("Middle pair      ");
            }
            break;
            case 12:{
                lab.setText("Weak pair        ");
            }
            break;
            case 13:{
                lab.setText("Proyecto color   ");
            }
            break;
            case 14:{
                lab.setText("Pro. Open-ended");
            }
            break;
            case 15:{
                lab.setText("Pro. Gutshot     ");
            }
            break;
            case 16:{
                
                lab.setText("Ace high         ");
            }
            break;
            case 17:{
                
                lab.setText("No made hand     ");
            }
            break;
            
        }
    }
}
