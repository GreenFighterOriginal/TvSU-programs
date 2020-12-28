package mvc.controller;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import mvc.model.Model;
import mvc.model.MyShape;
import mvc.model.UndoMachine;
import mvc.model.activity.Activity;
import mvc.model.shapes.MyRectangle;
import mvc.view.MyFrame;
import mvc.view.MyPanel;
import java.awt.Color;

public class Controller {
    Model model;
    UndoMachine undoMachine;
    MyFrame frame;
    MyPanel panel;
    Point2D [] pd;
    State state;
    public Controller() {
        model = new Model();
        undoMachine = new UndoMachine();
        state = new State(model);
        state.setShape(new MyShape(new MyRectangle()));
        state.setColor(Color.black);
        panel = new MyPanel();
        panel.setController(this);
        model.addObserver(panel);
        frame = new MyFrame(state,undoMachine);
        frame.setPanel(panel);
        pd = new Point2D[2];
    }
    public void getPointOne(Point2D p){
        Activity activity = state.getActivity();
        activity.getPointOne(p);
        undoMachine.add(activity.clone());

    }
    public void getPointTwo(Point2D p){
        state.getActivity().getPointTwo(p);
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }
}