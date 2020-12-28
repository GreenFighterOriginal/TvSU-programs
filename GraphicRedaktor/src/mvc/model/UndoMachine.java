package mvc.model;

import mvc.model.activity.Activity;
import java.util.ArrayList;
import java.util.Observable;

public class UndoMachine extends Observable {
    public enum UndoRedoButtonState {

        EUndoERedo(true, true), DUndoDRedo(false, false), EUndoDRedo(true, false), DUndoERedo(false, true);
        public boolean undo;
        public boolean redo;

        UndoRedoButtonState(boolean u, boolean r) {
            undo = u;
            redo = r;
        }
    }
    private class StateDUndoDRedo extends UndoRedoState {

        public StateDUndoDRedo() {
            super(UndoRedoButtonState.DUndoDRedo);
        }

        @Override
        public void undo() {
        }

        @Override
        public void redo() {
        }

        @Override
        void goToEUndoERedo() {
        }

        @Override
        void deleteHistory() {
        }
    }

    private class StateDUndoERedo extends UndoRedoState {

        public StateDUndoERedo() {
            super(UndoRedoButtonState.DUndoERedo);
        }

        @Override
        public void undo() {
        }
    }

    private class StateEUndoDRedo extends UndoRedoState {

        public StateEUndoDRedo() {
            super(UndoRedoButtonState.EUndoDRedo);
        }

        @Override
        public void redo() {
        }

        @Override
        void deleteHistory() {
        }
    }

    private class StateEUndoERedo extends UndoRedoState {

        public StateEUndoERedo() {
            super(UndoRedoButtonState.EUndoERedo);
        }

        @Override
        void goToEUndoERedo() {
        }
    }

    private class UndoRedoState {

        UndoRedoButtonState buttonState;

        public UndoRedoState(UndoRedoButtonState buttonState) {
            this.buttonState = buttonState;
        }

        public UndoRedoButtonState getButtonState() {
            return buttonState;
        }

        void undo() {
            activityList.get(undoIterator).unexecute();
            undoIterator--;
            if (undoIterator == -1) {
                state = stateDUndoERedo;
                notifyMenu();
            } else {
                goToEUndoERedo();
            }
        }

        void redo() {
            undoIterator++;
            activityList.get(undoIterator).execute();
            if (undoIterator == activityList.size() - 1) {
                state = stateEUndoDRedo;
                notifyMenu();
            } else {
                goToEUndoERedo();
            }
        }

        final void add(Activity action) {
            deleteHistory();
            activityList.add(action);
            undoIterator++;
            state = stateEUndoDRedo;
            notifyMenu();
        }

        void goToEUndoERedo() {
            state = stateEUndoERedo;
            notifyMenu();
        }

        void deleteHistory() {
            if (!activityList.isEmpty()) {
                for (int i = undoIterator; i < activityList.size(); i++) {
                    if (i <0) i=0;
                    activityList.remove(i);
                }
            }
        }
    }
    ArrayList<Activity> activityList;

    UndoRedoState stateDUndoDRedo;
    UndoRedoState stateEUndoERedo;
    UndoRedoState stateDUndoERedo;
    UndoRedoState stateEUndoDRedo;
    UndoRedoState state;
    int undoIterator;

    public UndoMachine() {

        activityList = new ArrayList<Activity>();
        stateDUndoDRedo = new StateDUndoDRedo();
        stateEUndoERedo = new StateEUndoERedo();
        stateDUndoERedo = new StateDUndoERedo();
        stateEUndoDRedo = new StateEUndoDRedo();
        state = stateDUndoDRedo;
        undoIterator = -1;
    }

    public void add(Activity action) {
        state.add(action);
    }

    public void execute() {
        state.redo();
    }

    public void unexecute() {
        state.undo();
    }

    public void notifyMenu() {
        setChanged();
        notifyObservers(state.getButtonState());
    }
}