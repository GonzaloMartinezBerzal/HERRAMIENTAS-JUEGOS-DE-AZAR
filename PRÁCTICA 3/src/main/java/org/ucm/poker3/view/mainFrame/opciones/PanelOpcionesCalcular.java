
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

public class PanelOpcionesCalcular extends JPanel{
    private JButton calcularB;
    private Controller ctrl;
    
    public PanelOpcionesCalcular(Controller ctrl){
        this.ctrl = ctrl;
        iniGUI();
    }
    
    public void iniGUI(){
        setLayout(null);
        setBackground(new Color(76,111,145));
        setBounds(0,0,330,70);

        calcularB = new JButton();
        calcularB.setBounds(10, 20, 300, 30);
        calcularB.setText("CALCULAR EQUITY");
        calcularB.setBackground(new Color(112, 146, 179));
        calcularB.setBorder(new BevelBorder(BevelBorder.RAISED));
        calcularBListener();
        Border b = BorderFactory.createLineBorder(Color.black, 2);
        setBorder(BorderFactory.createTitledBorder(b, "Calcular EQUITY ", TitledBorder.LEFT, TitledBorder.TOP));
        add(calcularB);
    }
    
    private void calcularBListener() {
        calcularB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ctrl.calculaEquity();
            }
        });
    }
}
