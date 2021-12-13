package org.ucm.poker3.view.mainFrame.opciones;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import org.ucm.poker3.control.Controller;
import org.ucm.poker3.model.cartas.Board;
import org.ucm.poker3.model.cartas.Carta;
import org.ucm.poker3.utils.Util;

public class PanelOpcionesBoard extends JPanel {

    private JSpinner numJugador;
    private JButton randomB;
    private JButton okB;
    private JTextField cartasTF;
    private JLabel infoSpinner;
    private JLabel infoTField;
    private Controller ctrl;

    public PanelOpcionesBoard(Controller ctrl) {
        this.ctrl = ctrl;
        iniGUI();
    }

    private void iniGUI() {
        setLayout(null);
        setBackground(new Color(76, 111, 145));
        setBounds(0, 220, 330, 150);

        Border b = BorderFactory.createLineBorder(Color.black, 2);
        setBorder(BorderFactory.createTitledBorder(b, "Opciones BOARD ", TitledBorder.LEFT, TitledBorder.TOP));

        infoSpinner = new JLabel();
        infoSpinner.setText("Seleccionar Nº de cartas en el board:");
        infoSpinner.setBounds(10, 20, 230, 30);

        SpinnerNumberModel modeloSpinner = new SpinnerNumberModel(3, 3, 5, 1);
        numJugador = new JSpinner(modeloSpinner);
        numJugador.setBounds(240, 20, 40, 30);
        numJugador.setToolTipText("Seleccionar Nº de cartas en el board:");

        randomB = new JButton();
        randomB.setBounds(10, 70, 130, 30);
        randomB.setText("ALEATORIO");
        randomB.setBackground(new Color(112, 146, 179));
        randomB.setBorder(new BevelBorder(BevelBorder.RAISED));
        randomBListener();

        okB = new JButton();
        okB.setBounds(210, 70, 100, 30);
        okB.setText("METER");
        okB.setBackground(new Color(112, 146, 179));
        okB.setBorder(new BevelBorder(BevelBorder.RAISED));
        okBListener();

        infoTField = new JLabel();
        infoTField.setText("Introducir cartas por texto: ");
        infoTField.setBounds(10, 110, 160, 30);

        cartasTF = new JTextField();
        cartasTF.setBounds(180, 110, 130, 30);

        add(infoSpinner);
        add(numJugador);
        add(randomB);
        add(okB);
        add(infoTField);
        add(cartasTF);
    }

    private void randomBListener() {
        randomB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int nB = (int) numJugador.getValue();
                Board b;
                String error = "nada";
                boolean bien = false;
                Random rand = new Random();
                if (nB != 3 && nB != 4 && nB != 5) {
                    JOptionPane.showMessageDialog(null, "El numero de cartas en el board solo puede ser 0, 3, 4 o 5", "Error message", JOptionPane.ERROR_MESSAGE);
                } else {
                    while (!bien) {
                        b = new Board();
                        error = "nada";
                        for (int i = 0; i < nB; i++) {
                            int c1 = rand.nextInt(13) + 2;
                            char p1 = Util.numAPalo(rand.nextInt(4));
                            b.addCarta(new Carta(c1, p1, false));
                        }
                        try {
                            ctrl.existeBoard(b);
                            sonIguales(b);
                        } catch (Exception e) {
                            error = e.getMessage();
                        }
                        if (error.equals("nada")) {
                            bien = true;
                            ctrl.setBoard(b);
                        }
                    }
                }
            }
        });
    }

    private void okBListener() {
        okB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String cartas = cartasTF.getText(), error = "nada";

                Board b = new Board();
                if (cartas.length() != 6 && cartas.length() != 8 && cartas.length() != 10) {

                    error = "Mete 3, 4 o 5 cartas (AhTc2h)";
                }
                try {
                    for (int i = 0; i < cartas.length(); i += 2) {
                        int c1 = Util.parseaNumCarta(cartas.charAt(i));
                        char p1 = cartas.charAt(i + 1);
                        b.addCarta(new Carta(c1, p1, false));
                    }
                    ctrl.existeBoard(b);
                } catch (Exception e) {
                    error = e.getMessage();
                }
                if (error.equals("nada")) {
                    ctrl.setBoard(b);
                } else {
                    JOptionPane.showMessageDialog(null, error, "Error message", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void sonIguales(Board b) throws Exception {
        List<Carta> cartas = b.getListaCartas();
        if (cartas.get(0).equals(cartas.get(1))) {
            throw new Exception("mal");
        }
        if (cartas.get(0).equals(cartas.get(2))) {
            throw new Exception("mal");
        }
        if (cartas.get(1).equals(cartas.get(2))) {
            throw new Exception("mal");
        }

        if (cartas.size() >= 4) {
            if (cartas.get(0).equals(cartas.get(3))) {
                throw new Exception("mal");
            }
            if (cartas.get(1).equals(cartas.get(3))) {
                throw new Exception("mal");
            }
            if (cartas.get(2).equals(cartas.get(3))) {
                throw new Exception("mal");
            }
        }
        if (cartas.size() >= 5) {
            if (cartas.get(0).equals(cartas.get(4))) {
                throw new Exception("mal");
            }
            if (cartas.get(1).equals(cartas.get(4))) {
                throw new Exception("mal");
            }
            if (cartas.get(2).equals(cartas.get(4))) {
                throw new Exception("mal");
            }
            if (cartas.get(3).equals(cartas.get(4))) {
                throw new Exception("mal");
            }
        }
    }
}
