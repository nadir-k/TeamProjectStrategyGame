package game.teamproject.game.Characters;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import game.teamproject.game.Characters.debuff.DebuffDatabase;
import game.teamproject.game.Characters.moves.DamageMove;
import game.teamproject.game.Characters.debuff.Debuff;
import game.teamproject.game.Characters.debuff.DebuffDetail;
import game.teamproject.game.Characters.moves.Move;
import game.teamproject.game.Characters.moves.MoveSet;

/**
 * The class of the character the player is attacking
 */

public class Enemy extends Character {
    private final static int MAX_HEATH = 100;
    private HashMap<Debuff, DebuffDetail> currentDebuffs;
    private boolean debuffed;

    private MoveSet moveSet;

    /**
     * Create the enemy with its list of debuff and the list moves
     */
    public Enemy() {
        super(MAX_HEATH);
        currentDebuffs = new HashMap<>();
        debuffed = false;
        moveSet = new MoveSet();
        createMoveSet();
        checkDebuff();

    }

    public boolean isStunned() {
        return currentDebuffs.containsKey(Debuff.STUNNED);
    }

    public boolean isArmorshread() {
        return currentDebuffs.containsKey(Debuff.ARMORSHREADED);
    }

    public boolean isBurned() {
        return currentDebuffs.containsKey(Debuff.BURNED);
    }


    /**
     * Add all the moves for the enemy
     */
    public void createMoveSet() {
        moveSet.addMove(Move.NORMAL,
                new DamageMove(2,3, 0));


    }

    /**
     *
     * @return health The current health of the character
     */

    public int getHealth() {
        return super.getHealth();
    }

    /**
     *
     * @param health The current health of the character
     */
    @Override
    public void setHealth(int health) {
        super.setHealth(health);
    }

    /**
     * Check if the currents debuffs contains the debuff if it true set the duration of the Debuffs to Max or put the Debuff in the current debuffs
     * @param debuff Name of debuffs
     * @param debuffDatabase Contain all the debuffs information
     */

    public void addDebuff(Debuff debuff, DebuffDatabase debuffDatabase) {
        if (currentDebuffs.containsKey(debuff)) {
            currentDebuffs.get(debuff).setDurationToMax();
        } else {
            currentDebuffs.put(debuff, debuffDatabase.getDebuffDetails(debuff));
            setDebuff(true);
        }
    }

    /**
     * Go though the current debuff list if and reduce the each debuff by one if it 0 then remove the debuff from the list
     */
    public void reduceAllDebuffsDuration() {
        for(Iterator<Map.Entry<Debuff, DebuffDetail>> it = currentDebuffs.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<Debuff, DebuffDetail> entry = it.next();
            entry.getValue().reduceDuration();
            if (entry.getValue().getDuration() <= 0) {
                entry.getValue().setDurationToMax();
                it.remove();
            }
        }

    }

    /**
     *
     * @return Move of the enemys
     */

    public MoveSet getMoveSet() {
        return moveSet;
    }

    /**
     * Go through the list of debuffs and set true if the list is empty and false if it not
     */
    public void checkDebuff() {
        if(!currentDebuffs.isEmpty()) {
            setDebuff(true);
        } else {
            setDebuff(false);
        }
    }

    /**
     *
     * @return A boolean stating if the enemy is debuffed or not
     */

    public boolean isDebuffed() {
        return debuffed;
    }

    /**
     *
     * @param debuff negative effect a enemy can get
     */

    public void setDebuff(boolean debuff) {
        this.debuffed = debuff;
    }

    /**
     *
     * @return currentDebuffs return all the debuff currently on the enemy
     */

    public boolean getDebuffed(){
        return debuffed;
    }


    public HashMap<Debuff, DebuffDetail> getCurrentDebuffs() {
        return currentDebuffs;
    }

}
