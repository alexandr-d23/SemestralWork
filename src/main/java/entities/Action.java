package entities;

public class Action {
    private AchievementTypes type;
    private int value;

    public Action(AchievementTypes type, int value) {
        this.type = type;
        this.value = value;
    }

    public AchievementTypes getType() {
        return type;
    }

    public int getValue() {
        return value;
    }
}
