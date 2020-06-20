package io.github.danildzambrana.guilib.data.objective;

public class SimpleObjective extends AbstractObjective {
    private final Objective objective;

    public SimpleObjective(Objective objective) {
        this.objective = objective;
    }

    public Objective getObjective() {
        return objective;
    }

    public enum Objective {
        ITEM_NAME, ITEM_LORE
    }
}
