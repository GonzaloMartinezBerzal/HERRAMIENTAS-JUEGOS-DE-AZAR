
package org.ucm.poker3.main;

import org.ucm.poker3.control.Controller;
import org.ucm.poker3.view.mainFrame.MainFrame;


public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame(new Controller()).setVisible(true);
            }
        });
    }
}
