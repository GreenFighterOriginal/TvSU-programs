package mvc.controller;

import mvc.model.Model;
import mvc.model.MyShape;
import mvc.model.activity.Activity;
import mvc.model.activity.Draw;
import mvc.model.ShapeDecorator;
import mvc.model.shapes.ShapeInterface;

import java.awt.*;
public class State {
    //from controller
    Model model;
    // menu created
    ShapeDecorator shape;
    Color color;
    MyShape.FillBehavior fb;
    ShapeInterface shapeInterface;
    Activity activity;

    public void setActivity(Activity activity) {
        this.activity = activity;
        this.activity.setModel(model);
    }

    public Activity getActivity() {
        return activity;
    }

    public State(Model model) {
        this.model = model;
        activity = new Draw(model);
    }

    public void setShape(ShapeDecorator shape) {
        this.shape = shape;
        model.setSampleShape(shape);
    }

    public void setColor(Color color) {
        this.color = color;
        shape.setColor(color);
        model.setSampleShape(shape);
    }

    public void setFb(MyShape.FillBehavior fb) {
        this.fb = fb;
        shape.setFb(fb);
        model.setSampleShape(shape);
    }

    public void setShapeInterface(ShapeInterface shapeInterface) {
        if (shapeInterface == null) return;
        this.shapeInterface = (ShapeInterface)shapeInterface.clone();
        shape.setShape(shapeInterface);
    }



    public ShapeDecorator getShape() {
        return shape;
    }

    public Color getColor(){
        return color;
    }

    public Model getModel() {
        return model;
    }

}