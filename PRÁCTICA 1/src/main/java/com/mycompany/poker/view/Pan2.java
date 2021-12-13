package com.mycompany.poker.view;


import com.mycompany.poker.model.ComparadorSoluciones;
import com.mycompany.poker.model.Mano;
import com.mycompany.poker.model.Solucion;
import com.mycompany.poker.view.Opcion1;
import com.mycompany.poker.view.Opcion2;
import com.mycompany.poker.view.Opcion3;
import com.mycompany.poker.view.Opcion4;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Pan2 extends javax.swing.JFrame {

    String fichero;
    
    public Pan2(String fichero) {
        initComponents();
        this.fichero = fichero;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Seleccione el caso del fichero introducido");

        jButton1.setText("Caso 1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Caso 2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Caso 3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Caso 4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(jLabel1)))
                .addContainerGap(105, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
            String jugador = "", mesa = "";int num = 0;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fichero))));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pan2.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while(reader.ready()){
                String linea = reader.readLine();
                jugador = linea.substring(0, 4);
                mesa = linea.substring(4, 10);
                Mano mano = new Mano(jugador, mesa,3, false);
                Opcion1 a = new Opcion1(jugador, mesa, mano.getSolucion());
                a.setVisible(true);
                this.setVisible(false);
                jugador = "";mesa = "";
            }
        } catch (IOException ex) {
            Logger.getLogger(Pan2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String jugador = "", mesa = "";int num = 0;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fichero))));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pan2.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while(reader.ready()){
                String linea = reader.readLine();
                jugador = linea.substring(0, 4);
                char ch = linea.charAt(5);
                num = Character.getNumericValue(ch); // Lee el numero de cartas del river
                mesa = linea.substring(7, ((num*2) + 7));
                Mano mano = new Mano(jugador, mesa,num, false);
                Opcion2 a = new Opcion2(jugador, mesa, mano.getSolucion());
                a.setVisible(true);
                this.setVisible(false);
                jugador = "";mesa = "";
            }
        } catch (IOException ex) {
            Logger.getLogger(Pan2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String solu = "";
        String manJug = "", mesa = "";int numJugadores = 0; ArrayList<String> cartasJugador = new ArrayList<>();
        BufferedReader reader = null;TreeMap<Solucion, Integer> soluciones = new TreeMap<>();
        ComparadorSoluciones comp = new ComparadorSoluciones();
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fichero))));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pan2.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while(reader.ready()){
                String linea = reader.readLine();
                numJugadores = Character.getNumericValue(linea.charAt(0));
                            int ini = 4, fin = 9;
                            for(int i =0; i< numJugadores;i++){
                                manJug = "";//4 11 18
                                manJug = linea.substring(ini +(i * 7), fin + (i * 7) -1);
                                cartasJugador.add(manJug);
                                //System.out.println(manJug);
                            }
                            
                            mesa = linea.substring(2+(numJugadores * 7), 12 + (numJugadores * 7));
                            
                            //System.out.println(mesa);
                            for(int i = 0; i< numJugadores; i++){
                                
                                Mano mano = new Mano(cartasJugador.get(i), mesa, 5, false);
                                Solucion aux = mano.getSolucion();
                                Integer numJ = i;
                                soluciones.put(aux, numJ + 1);
                            }      
                            //comparar y ordenar a los jugadores para escribirlos despues
                            
                            for( Map.Entry<Solucion, Integer>  r: soluciones.entrySet()){
                                Solucion aux = r.getKey();
                                Integer numJ = r.getValue();
                                solu += " J" + numJ + " "+ aux.toString() + "\n";
                            }
                            
                Opcion3 a = new Opcion3(cartasJugador, mesa, solu);
                a.setVisible(true);
                this.setVisible(false);
                
            }
        } catch (IOException ex) {
            Logger.getLogger(Pan2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
            String jugador = "", mesa = "", cJ = "";int num = 0;Mano mano; Solucion aux;
            ComparadorSoluciones cmp = new ComparadorSoluciones();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fichero))));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pan2.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while(reader.ready()){
                String linea = reader.readLine();
                            jugador = linea.substring(0, 8);
                            char ch = linea.charAt(9);
                            num = Character.getNumericValue(ch); // Lee el numero de cartas del river                           
                            mesa = linea.substring(11, ((num*2) + 11));
                            
                            
                            cJ += jugador.charAt(0);cJ += jugador.charAt(1);
                            cJ += jugador.charAt(2);cJ += jugador.charAt(3);
                            mano = new Mano(cJ, mesa,num, true);
                            aux = mano.getSolucion();                         
                            cJ = "";
                            
                            cJ += jugador.charAt(2);cJ += jugador.charAt(3);
                            cJ += jugador.charAt(4);cJ += jugador.charAt(5);
                            mano = new Mano(cJ, mesa,num, true);
                            aux = cmp.compara(mano.getSolucion(),aux);                          
                            cJ = "";
                            
                            cJ += jugador.charAt(4);cJ += jugador.charAt(5);
                            cJ += jugador.charAt(6);cJ += jugador.charAt(7);
                            mano = new Mano(cJ, mesa,num, true);
                            aux = cmp.compara(mano.getSolucion(),aux);                          
                            cJ = "";
                            
                            cJ += jugador.charAt(0);cJ += jugador.charAt(1);
                            cJ += jugador.charAt(4);cJ += jugador.charAt(5);
                            mano = new Mano(cJ, mesa,num, true);
                            aux = cmp.compara(mano.getSolucion(),aux);                          
                            cJ = "";
                            
                            cJ += jugador.charAt(0);cJ += jugador.charAt(1);
                            cJ += jugador.charAt(6);cJ += jugador.charAt(7);
                            mano = new Mano(cJ, mesa,num, true);
                            aux = cmp.compara(mano.getSolucion(),aux);                          
                            cJ = "";
                            
                            cJ += jugador.charAt(2);cJ += jugador.charAt(3);
                            cJ += jugador.charAt(6);cJ += jugador.charAt(7);
                            mano = new Mano(cJ, mesa,num, true);
                            aux = cmp.compara(mano.getSolucion(),aux);                       
                            cJ = "";
                                                                                 
                        Opcion4 a = new Opcion4(jugador, mesa, aux);
                    a.setVisible(true);
                this.setVisible(false);
            }
        } catch (IOException ex) {
            Logger.getLogger(Pan2.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
