package programma;

import javax.swing.*;
import java.awt.*;

public class GarageLight extends JButton {
    private int state;
    public GarageLight(){
        state = 0;
    }
    public void on(){
        setBackground(Color.YELLOW);
    }
    public void off(){
        setBackground(Color.WHITE);
    }
}
