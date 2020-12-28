package mvc.model;

import mvc.model.shapes.ShapeInterface;

import java.awt.*;
import java.awt.geom.Point2D;


public interface ShapeDecorator {
    void draw(Graphics2D g);
    void setParametr(int p, Color c);
    void setColor(Color c);
    void setFb(MyShape.FillBehavior f);
    void setShape(ShapeInterface r);
    void setFrame(Point2D [] p);

    boolean contains(Point2D p);

    MyShape.FillBehavior getFb();
    ShapeInterface getShape();
    ShapeDecorator clone();
}
