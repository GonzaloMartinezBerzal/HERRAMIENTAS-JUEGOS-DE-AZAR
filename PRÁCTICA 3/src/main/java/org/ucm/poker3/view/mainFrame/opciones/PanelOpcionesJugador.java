package org.ucm.poker3.view.mainFrame.opciones;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
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
import org.ucm.poker3.model.cartas.Carta;
import org.ucm.poker3.model.cartas.Jugador;
import org.ucm.poker3.model.cartas.JugadorOmaha;
import org.ucm.poker3.utils.Util;

public class PanelOpcionesJugador extends JPanel {

    private JSpinner numJugador;
    private JButton foldB;
    private JButton randomB;
    private JButton okB;
    private JTextField cartasTF;
    private JLabel infoSpinner;
    private JLabel infoTField;
    Controller ctrl;

    public PanelOpcionesJugador(Controller ctrl) {
        this.ctrl = ctrl;
        iniGUI();
    }

    private void iniGUI() {
        setLayout(null);
        setBackground(new Color(76, 111, 145));
        setBounds(0, 70, 330, 150);

        Border b = BorderFactory.createLineBorder(Color.black, 2);
        setBorder(BorderFactory.createTitledBorder(b, "Opciones JUGADOR ", TitledBorder.LEFT, TitledBorder.TOP));

        infoSpinner = new JLabel();
        infoSpinner.setText("Seleccionar NºJugador:");
        infoSpinner.setBounds(10, 20, 135, 30);

        SpinnerNumberModel modeloSpinner = new SpinnerNumberModel(1, 1, 6, 1);
        numJugador = new JSpinner(modeloSpinner);
        numJugador.setBounds(155, 20, 40, 30);
        numJugador.setToolTipText("Seleccionar numero de jugador");

        foldB = new JButton();
        foldB.setBounds(210, 20, 100, 30);
        foldB.setText("FOLD");
        foldB.setBackground(new Color(112, 146, 179));
        foldB.setBorder(new BevelBorder(BevelBorder.RAISED));
        foldBListener();

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
        add(foldB);
        add(randomB);
        add(okB);
        add(infoTField);
        add(cartasTF);
    }

    private void foldBListener() {
        foldB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ctrl.fold((int) numJugador.getValue());
            }
        });
    }

    private void randomBListener() {
        randomB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String excep = "nada";
                Random rand = new Random();
                boolean normal = ctrl.getModo();
                boolean bien = false;
                int nJ = (int) numJugador.getValue();
                Jugador j = null;
                JugadorOmaha jO = null;
                if (ctrl.existeNumJug(nJ)) {
                    JOptionPane.showMessageDialog(null, "Jugador ya en la mesa.", "Error message", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (normal) {
                        while (!bien) {
                            excep = "nada";
                            try {
                                int c1 = rand.nextInt(13) + 2;
                                char p1 = Util.numAPalo(rand.nextInt(4));
                                int c2 = rand.nextInt(13) + 2;
                                char p2 = Util.numAPalo(rand.nextInt(4));

                                if (c1 >= c2) {
                                    j = new Jugador(new Carta(c1, p1, true), new Carta(c2, p2, true), nJ);
                                } else {
                                    j = new Jugador(new Carta(c2, p2, true), new Carta(c1, p1, true), nJ);
                                }
                                if (j.getCarta1().equals(j.getCarta2())) {
                                    excep = "mal";
                                } else {
                                    ctrl.existeJugadorOCartaN(j);
                                }
                            } catch (Exception e) {
                                excep = e.getMessage();
                            }
                            if (excep.equals("nada")) {
                                ctrl.addJugador(j);
                                bien = true;
                            }
                        }
                    } else {
                        while (!bien) {
                            excep = "nada";
                            try {
                                int c1 = rand.nextInt(13) + 2;
                                char p1 = Util.numAPalo(rand.nextInt(4));
                                int c2 = rand.nextInt(13) + 2;
                                char p2 = Util.numAPalo(rand.nextInt(4));
                                int c3 = rand.nextInt(13) + 2;
                                char p3 = Util.numAPalo(rand.nextInt(4));
                                int c4 = rand.nextInt(13) + 2;
                                char p4 = Util.numAPalo(rand.nextInt(4));
                                jO = ordenaJO(new Carta(c1, p1, true), new Carta(c2, p2, true),
                                        new Carta(c3, p3, true), new Carta(c4, p4, true), nJ);
                                if (jO.getCarta1().equals(jO.getCarta2())
                                        || jO.getCarta1().equals(jO.getCarta3())
                                        || jO.getCarta1().equals(jO.getCarta4())
                                        || jO.getCarta2().equals(jO.getCarta3())
                                        || jO.getCarta2().equals(jO.getCarta4())
                                        || jO.getCarta3().equals(jO.getCarta4())) {
                                    excep = "mal";
                                } else {
                                    ctrl.existeJugadorOCartaO(jO);
                                }

                            } catch (Exception e) {
                                excep = e.getMessage();
                            }
                            if (excep.equals("nada")) {
                                ctrl.addJugadorOmaha(jO);
                                bien = true;
                            }
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
                String cartas = cartasTF.getText(), excep = "nada";
                boolean normal = ctrl.getModo();
                Jugador j = null;
                JugadorOmaha jO = null;
                if (normal) {
                    if (cartas.length() != 4) {
                        excep = "La longitud debe de ser de 4 (AhQh)";
                    }
                    try {
                        int c1 = Util.parseaNumCarta(cartas.charAt(0));
                        char p1 = cartas.charAt(1);
                        int c2 = Util.parseaNumCarta(cartas.charAt(2));
                        char p2 = cartas.charAt(3);
                        int nJ = (int) numJugador.getValue();
                        j = new Jugador(new Carta(c1, p1, true), new Carta(c2, p2, true), nJ);
                        ctrl.existeJugadorOCartaN(j);
                    } catch (Exception e) {
                        excep = e.getMessage();
                    }
                } else {
                    if (cartas.length() != 8) {
                        excep = "La longitud debe de ser de 8 (AhKhQhJh)";
                    }
                    try {
                        int c1 = Util.parseaNumCarta(cartas.charAt(0));
                        char p1 = cartas.charAt(1);
                        int c2 = Util.parseaNumCarta(cartas.charAt(2));
                        char p2 = cartas.charAt(3);
                        int c3 = Util.parseaNumCarta(cartas.charAt(4));
                        char p3 = cartas.charAt(5);
                        int c4 = Util.parseaNumCarta(cartas.charAt(6));
                        char p4 = cartas.charAt(7);
                        int nJ = (int) numJugador.getValue();
                        jO = new JugadorOmaha(new Carta(c1, p1, true), new Carta(c2, p2, true),
                                new Carta(c3, p3, true), new Carta(c4, p4, true), nJ);
                        ctrl.existeJugadorOCartaO(jO);
                    } catch (Exception e) {
                        excep = e.getMessage();
                    }
                }
                if ("nada".equals(excep)) {
                    if (normal) {
                        ctrl.addJugador(j);
                    } else {
                        ctrl.addJugadorOmaha(jO);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, excep, "Error message", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private JugadorOmaha ordenaJO(Carta c1, Carta c2, Carta c3, Carta c4, int nJ) {
        ArrayList<Carta> lAux = new ArrayList();
        lAux.add(c1);
        lAux.add(c2);
        lAux.add(c3);
        lAux.add(c4);
        Collections.sort(lAux);
        return new JugadorOmaha(lAux.get(0), lAux.get(1), lAux.get(2), lAux.get(3), nJ);
    }
}
