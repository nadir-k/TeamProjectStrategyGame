package game.teamproject.game.Characters.moves;

/**
 *  A class for move that doesn't apply debuffs
 */

public class DamageMove {

    private int cooldown;
    protected int maxCooldown;
    private int minimumDamage;
    private int maximumDamage;

    /**
     *
     * @param minimumDamage The lowest range of damage
     * @param maximumDamage The highest range of damage
     * @param maxCooldown The time it take before you you use the move again
     */

    public DamageMove(int minimumDamage, int maximumDamage, int maxCooldown) {
        this.minimumDamage = minimumDamage;
        this.maximumDamage = maximumDamage;
        cooldown = 0;
        this.maxCooldown = maxCooldown;
    }

    /**
     *
     * @return minimumDamage The lowest range of damage
     */

    public int getMinimumDamage() {
        return minimumDamage;
    }

    /**
     *
     * @param minimumDamage The lowest range of damage
     */

    public void setMinimumDamage(int minimumDamage) {
        this.minimumDamage = minimumDamage;
    }

    /**
     *
     * @return maximumDamage The highest range of damage
     */
    public int getMaximumDamage() {
        return maximumDamage;
    }

    /**
     *
     * @param maximumDamage The lowest range of damage
     */
    public void setMaximumDamage(int maximumDamage) {
        this.maximumDamage = maximumDamage;
    }


    /**
     * Reduce the current cooldown by one
     */
    public void reduceCooldown() {
        cooldown--;
    }

    /**
     *
     * @return maxCooldown The time it take before you you use the move again
     */
    public int getMaxCooldown() {
        return maxCooldown;
    }

    /**
     * Set the current cooldown to the max cooldown
     */
    public void setCooldownToMax() {
        cooldown = maxCooldown;
    }

    /**
     *
     * @return cooldown Current cooldown value
     */
    public int getCooldown() {
        return cooldown;
    }

    /**
     * @param cooldown Current cooldown value
     */
    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    /**
     *
     * @param maxCooldown The time it take before you you use the move again
     */
    public void setMaxCooldown(int maxCooldown) {
        this.maxCooldown = maxCooldown;
    }

    /**
     *
     * @return  A string of all value of the moves
     */

    @Override
    public String toString() {
        return "DamageMove{" +
                "cooldown=" + cooldown +
                ", maxCooldown=" + maxCooldown +
                ", minimumDamage=" + minimumDamage +
                ", maximumDamge=" + maximumDamage +
                '}';
    }
}
