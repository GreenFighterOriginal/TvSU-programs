package mvc.model;

import mvc.model.shapes.ShapeInterface;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Model extends Observable {

    ShapeDecorator currentShape = null;
    ShapeDecorator sampleShape;
    ArrayList<ShapeDecorator> list;

    public void setSampleShape(ShapeDecorator sampleShape) {
        this.sampleShape = sampleShape;
    }

    public Model() {
        list = new ArrayList<>();
    }

    public Model(ShapeDecorator sampleShape) {
        this.sampleShape = sampleShape;
        list = new ArrayList<>();
    }

    public ShapeDecorator inintCurrentShape() {
        currentShape = sampleShape.clone();
        list.add(currentShape);
        return currentShape;
    }

    public void setMyShape(ShapeDecorator myShape) {
        this.currentShape = myShape;
    }

    public void changeShape(Point2D[] pd) {
        currentShape.setFrame(pd);
        this.setChanged();
        this.notifyObservers();
    }

    public void draw(Graphics2D g) {
        if (list != null) {
            for (ShapeDecorator s : list) {
                s.draw(g);
            }
        }
    }

    public void upd(){
        this.setChanged();
        this.notifyObservers();
    }

    public ShapeDecorator findShape(Point2D p1) {
        if (list != null) {
            for (ShapeDecorator s : list) {
                if (s.contains(p1)) {
                    currentShape = s;
                    return currentShape;
                };
            }
        }
        return null;
    }

    public void moveShape(Point2D[] p) {
        double deltaX = p[0].getX() - p[1].getX();
        double deltaY = p[0].getY() - p[1].getY();
        if (currentShape != null) {
            ShapeInterface s = currentShape.getShape();
            double xMin = s.getMinX() - deltaX;
            double yMin = s.getMinY() - deltaY;
            double xMax = s.getMaxX() - deltaX;
            double yMax = s.getMaxY() - deltaY;
            s.setShapeByTwoPoint(xMin, yMin, xMax, yMax);
            p[0] = p[1];
            setChanged();
            notifyObservers();
        }
    }

    ///////////////////////undo/////////////////////////
    public ShapeDecorator ctrlZ_Shape() {
        ShapeDecorator s = list.remove(list.size() - 1);
        setChanged();
        notifyObservers();
        return s;
    }

    public void setActiveShape(ShapeDecorator activeShape) {
        this.currentShape = activeShape;
        list.add(currentShape);
        setChanged();
        notifyObservers();
    }

    public void reseverMove(ShapeDecorator shapeNew, Point2D[] oldP) {
        shapeNew.setFrame(oldP);
        currentShape = shapeNew;
        setChanged();
        notifyObservers();
    }
    public void save(File file) throws IOException {
        FileWriter w = new FileWriter(file);
        FileOutputStream fout = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(fout);
        out.writeObject(list);
        out.close();
    }

    public void open(File file) throws IOException {

        try {
            ObjectInputStream fin = new ObjectInputStream(new FileInputStream(file));
            try {
                list=(ArrayList<ShapeDecorator>)fin.readObject();
                currentShape =list.get(list.size()-1);
                setChanged();
                notifyObservers();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}