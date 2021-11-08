package game.teamproject.game.Characters.moves;

/**
 * A class with value about moves that apply debuffs
 */

import game.teamproject.game.Characters.debuff.Debuff;

public class DebufferDamageMove extends DamageMove {
    private Debuff debuff;

    /**
     *
     * @param minimumDamage The lowest range of damage
     * @param maximumDamge The highest range of damage
     * @param maxCooldown The time it take before you you use the move again
     * @param debuff  The debuff that could apply with this move
     */



    public DebufferDamageMove(int minimumDamage, int maximumDamge, int maxCooldown, Debuff debuff) {

        super(minimumDamage, maximumDamge, maxCooldown);
        this.debuff = debuff;
    }


    /**
     *
     * @return minimumDamage The lowest range of damage
     */
    @Override
    public int getMinimumDamage() {
        return super.getMinimumDamage();
    }

    /**
     *
     * @return maximumDamage The highest range of damage
     */
    @Override
    public int getMaximumDamage() {
        return super.getMaximumDamage();
    }


    /**
     *
     * @return Debuff The debuff that the move could apply
     */
    public Debuff getDebuff() {
        return debuff;
    }

    /**
     *
     * @return A string of all the value of the move that can applied debuff
     */
    @Override
    public String toString() {
        return "DebufferDamageMove{" +
                "debuff=" + debuff +
                ", maxCooldown=" + maxCooldown +
                '}';
    }
}
