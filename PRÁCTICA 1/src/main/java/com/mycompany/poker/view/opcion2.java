/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.poker.view;

import com.mycompany.poker.model.Solucion;
import static com.mycompany.poker.view.Opcion1.mostrarImagen;


public class Opcion2 extends javax.swing.JFrame {

    public Opcion2(String jugador, String mesa, Solucion sol) {
       
        initComponents();
        Character uno = null, dos = null;
        uno = jugador.charAt(0);dos =jugador.charAt(1);
            mostrarImagen(this.carta1j,"Images/" + uno.toString()+dos.toString()+".png");
            uno = jugador.charAt(2);dos =jugador.charAt(3);
            mostrarImagen(this.carta2j,"Images/" + uno.toString()+dos.toString()+".png");
            for(int i = 0; i < (mesa.length()/2);i++){
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

        jugadas = new javax.swing.JLabel();
        carta1j = new javax.swing.JLabel();
        carta2j = new javax.swing.JLabel();
        carta1m = new javax.swing.JLabel();
        carta2m = new javax.swing.JLabel();
        carta3m = new javax.swing.JLabel();
        carta4m = new javax.swing.JLabel();
        carta5m = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jugadas.setText("solucion");

        carta1j.setText("carta1j");

        carta2j.setText("carta2j");

        carta1m.setText("carta1m");

        carta2m.setText("carta2m");

        carta3m.setText("carta3m");

        carta4m.setText("carta4m");

        carta5m.setText("carta5m");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(carta1j, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(carta1m, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(carta2m, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50)
                .addComponent(carta3m, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(carta4m, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(carta2j, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addComponent(carta5m, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jugadas, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jugadas, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(carta5m, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(carta4m, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(carta3m, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(carta1m, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(carta2m, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(carta1j, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(carta2j, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel carta1j;
    private javax.swing.JLabel carta1m;
    private javax.swing.JLabel carta2j;
    private javax.swing.JLabel carta2m;
    private javax.swing.JLabel carta3m;
    private javax.swing.JLabel carta4m;
    private javax.swing.JLabel carta5m;
    private javax.swing.JLabel jugadas;
    // End of variables declaration//GEN-END:variables
}
