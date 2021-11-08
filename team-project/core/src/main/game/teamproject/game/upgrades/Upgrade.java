package game.teamproject.game.upgrades;
/**
 * This class converts Upgrade objects to Strings
 */
public enum Upgrade {
    CHANCE("Chance"), EFFECT("Effect"),DURATION ("Duration");

    String upgrade;

    Upgrade(String name) {
        upgrade = name;
    }

    public String upgradeToString() {
        return upgrade;
    }
}
