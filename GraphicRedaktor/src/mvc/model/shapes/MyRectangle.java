package mvc.model.shapes;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class MyRectangle extends Rectangle2D.Double implements ShapeInterface {

    @Override
    public void setShapeByTwoPoint(double x1, double y1, double x2, double y2) {
        setFrameFromDiagonal(x1,y1,x2,y2);
    }

    @Override
    public void setShapeByTwoPoint(Point2D first, Point2D second) {
        setFrameFromDiagonal(first,second);
    }

    @Override
    public ShapeInterface clone(){
        return (MyRectangle)super.clone();
    }
}
