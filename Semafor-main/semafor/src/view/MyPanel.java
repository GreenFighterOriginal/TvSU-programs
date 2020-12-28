
package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class MyPanel extends JPanel implements Observer{
    Controller controller;

    public MyPanel(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        controller.draw(g);
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        repaint();
    }
    
}
