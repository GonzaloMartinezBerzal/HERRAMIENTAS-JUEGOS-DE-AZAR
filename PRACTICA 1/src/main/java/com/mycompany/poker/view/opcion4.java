/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.poker.view;

import com.mycompany.poker.model.Solucion;
import static com.mycompany.poker.view.Opcion1.mostrarImagen;

public class Opcion4 extends javax.swing.JFrame {
    public Opcion4(String jugador, String mesa, Solucion sol) {
        initComponents();
        Character uno = null, dos = null;
        uno = jugador.charAt(0);dos =jugador.charAt(1);
         mostrarImagen(this.carta1j,"Images/" + uno.toString()+dos.toString()+".png");
         uno = jugador.charAt(2);dos =jugador.charAt(3);
         mostrarImagen(this.carta2j,"Images/" + uno.toString()+dos.toString()+".png");
         uno = jugador.charAt(4);dos =jugador.charAt(5);
         mostrarImagen(this.carta3j,"Images/" + uno.toString()+dos.toString()+".png");
         uno = jugador.charAt(6);dos =jugador.charAt(7);
         mostrarImagen(this.carta4j,"Images/" + uno.toString()+dos.toString()+".png");
         
         for(int i = 0; i < mesa.length()/2;i++){
                uno = mesa.charAt(i*2);dos =mesa.charAt(i*2+1);
                muestra(i+1,"Images/" + uno.toString()+dos.toString()+".png");
            }
         String aux = "";
         aux = sol.toString() + " " + sol.getDraws();
         jugadas.setText(aux);
    }

    private void muestra(int i,String ruta){
        
        switch(i){
            case 1:
                mostrarImagen(carta1m,ruta);
                break;
            case 2:
                mostrarImagen(carta2m,ruta);
                break;
            case 3:
                mostrarImagen(carta3m,ruta);
                break;    
            case 4:
                mostrarImagen(carta4m,ruta);
                break;
            case 5:
                mostrarImagen(carta5m,ruta);
                break;            
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        carta1m = new javax.swing.JLabel();
        carta2m = new javax.swing.JLabel();
        carta3m = new javax.swing.JLabel();
        carta4m = new javax.swing.JLabel();
        carta5m = new javax.swing.JLabel();
        carta1j = new javax.swing.JLabel();
        carta2j = new javax.swing.JLabel();
        carta3j = new javax.swing.JLabel();
        carta4j = new javax.swing.JLabel();
        jugadas = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        carta1m.setText("jLabel1");

        carta2m.setText("jLabel2");

        carta3m.setText("jLabel3");

        carta4m.setText("jLabel4");

        carta5m.setText("jLabel5");

        carta1j.setText("jLabel6");

        carta2j.setText("jLabel7");

        carta3j.setText("jLabel8");

        carta4j.setText("jLabel9");

        jugadas.setText("soluciones");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(carta1j, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(carta1m, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                        .addComponent(carta2m, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(carta3m, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(carta4m, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(carta2j, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(carta3j, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(carta5m, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(carta4j, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61))
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jugadas, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jugadas, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(carta5m, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(carta4m, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(carta3m, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(carta2m, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(carta1m, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(carta2j, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(carta1j, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(carta3j, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(carta4j, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel carta1j;
    private javax.swing.JLabel carta1m;
    private javax.swing.JLabel carta2j;
    private javax.swing.JLabel carta2m;
    private javax.swing.JLabel carta3j;
    private javax.swing.JLabel carta3m;
    private javax.swing.JLabel carta4j;
    private javax.swing.JLabel carta4m;
    private javax.swing.JLabel carta5m;
    private javax.swing.JLabel jugadas;
    // End of variables declaration//GEN-END:variables
}
