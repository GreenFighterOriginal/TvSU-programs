package mvc.model.shapes;

import java.awt.*;
import java.awt.geom.Point2D;

public interface ShapeInterface extends Shape {
    void setShapeByTwoPoint(double x1, double y1, double x2, double y2);
    void setShapeByTwoPoint(Point2D first, Point2D second);

    ShapeInterface clone();

    double getMinX();
    double getMinY();

    double getMaxX();
    double getMaxY();
}
