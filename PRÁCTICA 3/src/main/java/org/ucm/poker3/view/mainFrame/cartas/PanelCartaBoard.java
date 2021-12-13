package org.ucm.poker3.view.mainFrame.cartas;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.ucm.poker3.model.cartas.Carta;

public class PanelCartaBoard extends JPanel {

    private Carta carta;
    private JLabel carta1;
    private int pos;

    public PanelCartaBoard(Carta carta, int pos) {
        this.carta = carta;
        this.pos = pos;
        iniGUIb();
    }

    public void iniGUIb() {
        setLayout(null);
        setBoundsCarta();
        ImageIcon img = null;
        Image image = null;
        try {
            BufferedImage myPicture = ImageIO.read(new File(carta.getCartaImagen()));
            image = myPicture;
            img = new ImageIcon(image.getScaledInstance(60, 80, Image.SCALE_SMOOTH));
            carta1 = new JLabel(img);
            carta1.setBounds(0, 0, 60, 80);
            add(carta1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void setBoundsCarta() {
        switch (pos) {
            case 1:
                setBounds(210, 260, 60, 80);
                break;
            case 2:
                setBounds(290, 260, 60, 80);
                break;
            case 3:
                setBounds(370, 260, 60, 80);
                break;
            case 4:
                setBounds(450, 260, 60, 80);
                break;
            case 5:
                setBounds(530, 260, 60, 80);
                break;

        }
    }

}
