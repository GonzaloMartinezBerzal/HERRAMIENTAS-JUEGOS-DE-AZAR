
package org.ucm.poker3.view.mainFrame.cartas;

import java.awt.Color;
import javax.swing.JLabel;


public class labelPorcentajes extends JLabel{
    public labelPorcentajes(boolean modoNormal){
        iniGUI(modoNormal);
    }
    private void iniGUI(boolean modoNormal){
        if(modoNormal){
            setBounds(0, 120, 100, 20);
        }
        else{
            setBounds(0, 120, 115, 20);
        }       
        setBackground(Color.white);
        setText("0.0%");
    }
}
