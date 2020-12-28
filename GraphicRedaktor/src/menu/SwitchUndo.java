package menu;

import mvc.model.UndoMachine;
import java.awt.event.ActionEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.AbstractAction;
import javax.swing.Icon;

public class SwitchUndo extends AbstractAction implements Observer{

    public SwitchUndo(String name, Icon icon, UndoMachine machine) {
        super(name, icon);
        putValue("machine", machine);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        UndoMachine m = (UndoMachine)getValue("machine");
        if (this.isEnabled()) m.unexecute();
    }

    @Override
    public void update(Observable o, Object arg) {
        UndoMachine.UndoRedoButtonState buttonState = (UndoMachine.UndoRedoButtonState) arg;
        this.setEnabled(buttonState.undo);
    }
}
