package game.teamproject.game.Characters.debuff;

/**
 *  This class store the information about each of the debuffs
 */

public class DebuffDetail {
    private int maxDuration;
    private int duration;
    private int chanceToApply;
    private int value;

    /**
     *   Create and store the value for each debuffs
     * @param maxDuration How long the debuffs last
     * @param chanceToApply The percentage of the debuff can be applied
     * @param value How much the debuff will do
     */

    public DebuffDetail(int maxDuration, int chanceToApply, int value) {
        this.maxDuration = maxDuration;
        duration = maxDuration;
        this.chanceToApply = chanceToApply;
        this.value = value;
    }

    /**
     *
     * @return duration of the debuff in the battle
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Duration is reduce by one
     */
    public void reduceDuration() {
        duration--;
    }

    /**
     *
     * @return Return the percentage of the debuff can be applied
     */
    public int getChanceToApply() {
        return chanceToApply;
    }

    /**
     *  Set the current duration to maximum duration
     */
    public void setDurationToMax() {
        duration = maxDuration;
    }

    /**
     *
     * @return Return the value fo the debuff
     */
    public int getValue() {
        return value;
    }

    /**
     *
     * @return MaxDuration How long the debuffs last
     */
    public int getMaxDuration() {
        return maxDuration;
    }

    /**
     *
     * @param maxDuration  How long the debuffs last
     */
    public void setMaxDuration(int maxDuration) {
        this.maxDuration = maxDuration;
    }

    /**
     *
     * @param chanceToApply The percentage of the debuff can be applied
     */
    public void setChanceToApply(int chanceToApply) {
        this.chanceToApply = chanceToApply;
    }


    /**
     *
     * @param value How much the debuff will do
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     *
     * @return A string of details of the debuffs
     */
    @Override
    public String toString() {
        return "DebuffDetail{" +
                "maxDuration=" + maxDuration +
                ", duration=" + duration +
                ", chanceToApply=" + chanceToApply +
                ", value=" + value +
                '}';
    }
}
