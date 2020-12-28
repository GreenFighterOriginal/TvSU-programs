package menu;

import mvc.controller.State;
import mvc.model.activity.Activity;

public class SwitchActivity implements Command{
    State state;
    Activity activity;

    public SwitchActivity(State state, Activity activity) {
        this.state = state;
        this.activity = activity;
    }

    @Override
    public void execute() {
        state.setActivity(activity);
    }
}
