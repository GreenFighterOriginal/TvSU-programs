package mvc.model;

import mvc.model.shapes.MyRectangle;
import mvc.model.shapes.ShapeInterface;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.Serializable;

public class MyShape implements Serializable, ShapeDecorator {

    Color color;
    ShapeInterface shape;
    FillBehavior fb;

    public MyShape(ShapeInterface shape) {
        this.shape = shape;
        color = Color.BLUE;
        fb = FillBehavior.NO_FILL;
    }

    public MyShape() {
        color = Color.BLUE;
        shape = new MyRectangle();
        fb =  FillBehavior.NO_FILL;;
    }

    public MyShape(Color color, ShapeInterface shape, FillBehavior fb) {
        this.color = color;
        this.shape = shape;
        this.fb = fb;
    }

    public void setFb(FillBehavior fb) {
        this.fb = fb;
    }

    public void setShape(ShapeInterface shape) {
        this.shape = shape;
    }

    public void setFrame(Point2D[] pd) {
        shape.setShapeByTwoPoint(pd[0], pd[1]);
    }

    public void draw(Graphics2D g) {
        fb.draw(g,color,shape);
    }

    @Override
    public void setParametr(int p, Color c) { }

    public void setColor(Color color) {
        this.color = color;
    }

    public MyShape.FillBehavior getFb() {
        return fb;
    }
    public boolean contains(Point2D p){
        return shape.contains(p);
    }
    public ShapeDecorator clone() {
        MyShape s = new MyShape();
        ShapeInterface s1 = shape.clone();
        s.setColor(color);
        s.setShape(s1);
        s.fb = this.fb;
        return s;
    }

    public ShapeInterface getShape() {
        return shape;
    }



    /////////////////////inner enum/////////////////////////////////////

    public enum FillBehavior implements Serializable{
        FILL {
            @Override
            public void draw(Graphics2D g,  Color c, ShapeInterface sh) {
                Paint paint = g.getPaint();
                g.setPaint(c);
                g.fill(sh);
                g.setPaint(paint);
            }
        } ,
        NO_FILL {
            @Override
            public void draw(Graphics2D g, Color c, ShapeInterface sh) {
                Paint paint = g.getPaint();
                g.setPaint(c);
                g.draw(sh);
                g.setPaint(paint);
            }
        };
        public abstract void  draw(Graphics2D g, Color c, ShapeInterface sh);

    }


}