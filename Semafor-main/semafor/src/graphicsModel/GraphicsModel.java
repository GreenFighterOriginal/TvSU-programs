package graphicsModel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;
import java.util.Observable;
import semafor.ColorEnum;
import static semafor.ColorEnum.TGreenYellowRed;

public class GraphicsModel extends Observable {

    
    ColorEnum colorEnum;
    

    public GraphicsModel() {
       
        colorEnum = TGreenYellowRed;
        
    }

    public void paint(Graphics g) {
        
        if (colorEnum != null) {
            g.setColor(Color.red);
            int x = -88, y = -88;
            if (colorEnum.red) {
                g.fillOval(165 + x, 100 + y, -2 * x, -2 * y);
            } else {
                g.drawOval(165 + x, 100 + y, -2 * x, -2 * y);
            }
            g.setColor(Color.yellow);
            if (colorEnum.yellow) {
                g.fillOval(165 + x, 285 + y, -2 * x, -2 * y);
            } else {
                g.drawOval(165 + x, 285 + y, -2 * x, -2 * y);
            }
            g.setColor(Color.green);
            if (colorEnum.green) {
                g.fillOval(165 + x, 470 + y, -2 * x, -2 * y);
            } else {
                g.drawOval(165 + x, 470 + y, -2 * x, -2 * y);
            }
        }
    }

    public void setColor(ColorEnum c) {
        colorEnum = c;
        setChanged();
        notifyObservers();
    }
}
