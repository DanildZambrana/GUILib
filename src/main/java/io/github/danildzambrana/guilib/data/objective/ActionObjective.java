package io.github.danildzambrana.guilib.data.objective;

public class ActionObjective extends AbstractObjective {
    private final Action action;

    public ActionObjective(Action action) {
        this.action = action;
    }

    public Action getAction() {
        return action;
    }

    public interface Action {
        void execute(Object parameterData);
    }
}
