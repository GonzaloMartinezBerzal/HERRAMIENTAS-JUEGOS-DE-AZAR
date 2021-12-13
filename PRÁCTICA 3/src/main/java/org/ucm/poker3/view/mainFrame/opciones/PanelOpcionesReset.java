
package org.ucm.poker3.view.mainFrame.opciones;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import org.ucm.poker3.control.Controller;

public class PanelOpcionesReset extends JPanel{
    private JButton resetB;
    private Controller ctrl;
    
    public PanelOpcionesReset(Controller ctrl){
        this.ctrl = ctrl;
        iniGUI();
    }
    
    public void iniGUI(){
        setLayout(null);
        setBackground(new Color(76,111,145));
        setBounds(0,440,330,70);//sumar 70 a modo de juego

        resetB = new JButton();
        resetB.setBounds(10, 20, 300, 30);
        resetB.setText("RESET");
        resetB.setBackground(new Color(112, 146, 179));
        resetB.setBorder(new BevelBorder(BevelBorder.RAISED));
        resetBListener();
        Border b = BorderFactory.createLineBorder(Color.black, 2);
        setBorder(BorderFactory.createTitledBorder(b, "Hacer RESET ", TitledBorder.LEFT, TitledBorder.TOP));
        add(resetB);
    }
    
    private void resetBListener() {
        resetB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ctrl.reset();
            }
        });
    }
}
