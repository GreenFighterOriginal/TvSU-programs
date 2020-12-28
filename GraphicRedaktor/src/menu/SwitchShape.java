package menu;

import mvc.controller.State;
import mvc.model.shapes.ShapeInterface;

public class SwitchShape implements Command{
    State state;
    ShapeInterface rs;

    public SwitchShape(State state, ShapeInterface rs) {
        this.state = state;
        this.rs = rs;
    }


    @Override
    public void execute() {
        state.setShapeInterface(rs);
    }

}
