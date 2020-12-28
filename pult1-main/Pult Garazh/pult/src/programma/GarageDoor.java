package programma;

import javax.swing.*;
import java.awt.*;

public class GarageDoor extends JButton {
    private int state;
    public GarageDoor(){
        state = 0;
    }
    public void up(){
        setBackground(Color.BLACK);
    }
    public void down(){
        setBackground(Color.WHITE);
    }
}
