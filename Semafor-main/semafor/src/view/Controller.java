package view;

import graphicsModel.GraphicsModel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import semafor.StateSemaphor;

public class Controller {

    GraphicsModel model;
    StateSemaphor semaphor;
    MyPanel panel;
    MyFrame frame;
    private static Controller controller = null;

    public void draw(Graphics g) {
        model.paint(g);
    }

    private Controller() {
        panel = new MyPanel(this);
        model = new GraphicsModel();
        model.addObserver(panel);
        semaphor = new StateSemaphor(model);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MyFrame(panel, semaphor).setVisible(true);
            }
        });
    }

    public static Controller getIntance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }
}
