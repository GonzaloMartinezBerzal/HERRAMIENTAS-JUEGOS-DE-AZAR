/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ucm.poker02p.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import org.ucm.poker02p.control.Controller;

public class CuadritoBoard extends JButton {

    private static Controller ctrl;
    private Carta carta;
    private String color;
    private boolean seleccionado;
    private static int contBoard;

    public CuadritoBoard(Carta carta, String color, Controller ctrl) {
        this.carta = carta;
        contBoard = 0;

        this.ctrl = ctrl;
        seleccionado = false;
        this.color = color;
        setColorMio(color);
        setSize(new Dimension(40, 40));
        setMargin(new java.awt.Insets(0, 0, 0, 0));
        this.setFont(new Font("arial", Font.PLAIN, 17));
        this.setMinimumSize(new Dimension(40, 40));
        setText(carta.toString());//mano.toString()

        setVisible(true);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                

                if (contBoard < 5) {
                    if (seleccionado) {
                        contBoard--;
                    } else {
                        
                        contBoard++;
                    }
                    
                    actualizaCuadrito();
                    ctrl.cuadritoBoardChanged(carta, seleccionado);
                }
                else if(contBoard == 5){
                    if (seleccionado) {
                        contBoard--;
                        actualizaCuadrito();
                        ctrl.cuadritoBoardChanged(carta, seleccionado);
                    }
                }
                

            }
        });
    }

    public void actualizaCuadrito() {
        if (!seleccionado) {
            setColorMio("amarillo");
            seleccionado = true;
        } else {
            seleccionado = false;
            setColorMio(color);
        }
    }

    private void setColorMio(String color) {
        switch (color) {
            case "rojo": {
                setBackground(new Color(255, 125, 125));//rojo clarito
            }
            break;
            case "amarillo": {
                setBackground(new Color(255, 255, 125));//amarillo clarito
            }
            break;
            case "azul": {
                setBackground(new Color(125, 225, 255));//azul clarito
            }
            break;
            case "verde": {
                setBackground(new Color(130, 255, 125));//verde clarito
            }
            break;
            case "gris": {
                setBackground(Color.LIGHT_GRAY);
            }
            break;
        }
    }

}
