package game.teamproject.game.Characters;

/**
 *  A class that contain all the field and moves character must contain 
 */

public abstract class Character {
    private int health;

    /**
     * 
     * @param health The current health of the character
     */
    Character(int health) {
        this.health = health;
    }

    /**
     *
     * @return health The current health of the character
     */

    public int getHealth() {
        return health;
    }

    /**
     *
     * @param health The current health of the character
     */

    public void setHealth(int health) {
        this.health = health;
    }
}
