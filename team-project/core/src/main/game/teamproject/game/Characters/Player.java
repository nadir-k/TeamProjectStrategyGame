package game.teamproject.game.Characters;
/**
 * Create player
 */
import java.util.Iterator;
import java.util.Map;

import game.teamproject.game.Characters.debuff.DebuffDatabase;
import game.teamproject.game.Characters.moves.DebufferDamageMove;
import game.teamproject.game.Characters.moves.DamageMove;
import game.teamproject.game.Characters.debuff.Debuff;
import game.teamproject.game.Characters.moves.Move;
import game.teamproject.game.Characters.moves.MoveSet;
import save.Statistics;

public class Player extends Character {
    private static final Player instance = new Player();

    private final static int MAX_HEALTH = 100;

    private int level;
    private int experiences;
    private MoveSet moveSet;
    private Statistics statistics;

    /**
     * Create player containing all the player stats
     */
    private Player() {
        super(MAX_HEALTH);
        level = 1;
        experiences = 0;
        moveSet = new MoveSet();
        statistics = new Statistics();
        createMoveSet();
    }

    public static Player getInstance() {
        return instance;
    }

    /**
     * Create the stat of all the move the player can use
     */
    public void createMoveSet() {
        moveSet.addMove(Move.NORMAL,
                new DamageMove(2,4, 0));
        moveSet.addMove(Move.ARMORBREAK,
                new DebufferDamageMove(2, 6, 4, Debuff.ARMORSHREADED));
        moveSet.addMove(Move.FIRE,
                new DebufferDamageMove(2, 5, 5, Debuff.BURNED));
        moveSet.addMove(Move.STUN,
                new DebufferDamageMove(2, 5, 3, Debuff.STUNNED));
    }


    public void levelUp() {
        level++;
        resetExp();

    }

    /**
     * Loop over every skill the player have and reduce the cooldown by one
     */
    public void reduceSkillCooldown() {
        for (Iterator<Map.Entry<Move, DamageMove>> it = moveSet.getMoves().entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<Move, DamageMove> entry = it.next();
                entry.getValue().reduceCooldown();
                if (entry.getValue().getCooldown() <= 0) {
                    entry.getValue().setCooldown(0);
                }
          //  System.out.println("Skill cooldown " + entry.getValue().getCooldown());
        }
    }


    /**
     * Increase the player level by one and reset the exp of the player
     */

    public void incrementLevel() {
        level++;
        resetExp();
    }

    /**
     * Add exp to the player
     * @param amount The amount of exp the player get
     */
    public void addExperiences(int amount) {
        experiences += amount;
    }

    /**
     *
     * @return  player health
     */
    public int getHealth() {
        return super.getHealth();
    }

    /**
     *
     * @param health The current health of the character
     */
    public void setHealth(int health) {
       super.setHealth(health);

    }

    /**
     *
     * @return player move
     */
    public MoveSet getMoveSet() {
        return moveSet;
    }

    /**
     *
     * @return player exp
     */
    public int getExperiences() {
        return experiences;
    }

    /**
     *
     * @return player level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Set player exp to zero
     */
    public void resetExp(){
        experiences = 0;
    }

    public void setExperiences(int experiences) {
        this.experiences = experiences;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setHeathToMax() {
        super.setHealth(MAX_HEALTH);
    }


}






