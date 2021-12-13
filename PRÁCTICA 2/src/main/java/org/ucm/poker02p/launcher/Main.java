/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ucm.poker02p.launcher;

import org.ucm.poker02p.control.Controller;
import org.ucm.poker02p.model.Simulator;
import org.ucm.poker02p.view.MainPanel;


/*
Lista de cosas  por hacer:

EN EL UNO:
hacer el traductor de texto a manos y de manos a texto

EN EL DOS:
rellenar la lista del ranking

EN EL TRES:
graficas y sacar soluciones...
 */
public class Main {

    public static void main(String[] args) {
        Simulator sim = new Simulator();
        Controller cntr = new Controller(sim);
        MainPanel mP = new MainPanel(cntr);
    }
}
