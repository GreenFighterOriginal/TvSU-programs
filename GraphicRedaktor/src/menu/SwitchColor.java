package menu;

import mvc.controller.State;
import java.awt.Color;
import javax.swing.JColorChooser;

public class SwitchColor implements Command{
    State state;


    public SwitchColor(State state) {
        this.state = state;
    }

    @Override
    public void execute() {
        Color c = JColorChooser.showDialog(null, "choose color", Color.BLACK);
        state.setColor(c);


    }

}