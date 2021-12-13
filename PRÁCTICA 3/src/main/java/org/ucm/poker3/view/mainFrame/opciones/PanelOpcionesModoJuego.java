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

public class PanelOpcionesModoJuego extends JPanel {

    private JButton modoB;
    private Controller ctrl;

    public PanelOpcionesModoJuego(Controller ctrl) {
        this.ctrl = ctrl;
        iniGUI();
    }

    public void iniGUI() {
        setLayout(null);
        setBackground(new Color(76, 111, 145));
        setBounds(0, 370, 330, 70);//sumar 70 a modo de juego

        modoB = new JButton();
        modoB.setBounds(10, 20, 300, 30);
        modoB.setText("CAMBIAR A OMAHA");
        modoB.setBackground(new Color(112, 146, 179));
        modoB.setBorder(new BevelBorder(BevelBorder.RAISED));
        modoBListener();
        Border b = BorderFactory.createLineBorder(Color.black, 2);
        setBorder(BorderFactory.createTitledBorder(b, "Cambiar MODO de JUEGO ", TitledBorder.LEFT, TitledBorder.TOP));
        add(modoB);
    }

    private void modoBListener() {
        modoB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ctrl.cambiaModo();
                if (ctrl.getModo()) {
                    modoB.setText("CAMBIAR A OMAHA");
                } else {
                    modoB.setText("CAMBIAR A NORMAL");
                }
            }
        });
    }
}
