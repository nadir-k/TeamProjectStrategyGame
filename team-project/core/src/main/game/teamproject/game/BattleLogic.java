package game.teamproject.game;
/**
 * Logic behind the battle state
 */

import java.util.Random;
import game.teamproject.game.Characters.debuff.Debuff;
import game.teamproject.game.Characters.debuff.DebuffDatabase;
import game.teamproject.game.Characters.debuff.DebuffDetail;
import game.teamproject.game.Characters.moves.DamageMove;
import game.teamproject.game.Characters.moves.DebufferDamageMove;
import game.teamproject.game.Characters.moves.Move;
import game.teamproject.game.Characters.moves.MoveSet;
import game.teamproject.game.Characters.Character;
import game.teamproject.game.Characters.Enemy;



public class BattleLogic {
    private DebuffDatabase debuffDatabase;
    private int damage;
    private String move;
    private String debuff;
    private boolean applied;
    private Random random;
    private DebuffDetail debuffDetail;



    /**
     * Create all the field required for calculate the battle
     */
    public BattleLogic() {
        random = new Random();
        debuffDatabase = DebuffDatabase.getInstance();

    }


    /**
     * Check if the move is normal or not if it not normal it will have a chance to apply debuff and
     * @param moveset List of all the moveal
     * @param move The move the character has used
     * @param character The character that being damage
     */
    public void useMove(MoveSet moveset, Move move, Character character) {
        DamageMove damageMove = moveset.getMove(move);
        this.move = move.moveToString();
        dealDamage(damageMove, character);
        if (move != Move.NORMAL) {
            applyDebuff((DebufferDamageMove) damageMove, character);
        } else  {
            applied = false;
        }
        damageMove.setCooldownToMax();
    //    System.out.println("Skill cooldown" + damageMove.getCooldown());
    }

    /**
     * Deal damage to the defending character with the move the attack is using  if if the defender is debuff by armor break the defender take bonus damage
     * @param damageMove The move the attack use
     * @param defender The character that being attack
     */
    public void dealDamage(DamageMove damageMove, Character defender) {

        damage = getRandomDamageFromMove(damageMove.getMinimumDamage(), damageMove.getMaximumDamage());
        if (isDefenderDebuffed(defender)) {
            damage *= 1.5;
        }
        defender.setHealth(defender.getHealth() - damage);
    }

    /**
     *  Check if the character class is a enemy or not
     * @param character The character being checked
     * @return boolean
     */
    public boolean isEnemy(Character character) {
        if (character.getClass() == Enemy.class) {
            return true;
        }
        return character.getClass().isInstance(Enemy.class);
    }

    /**
     * Check if the defend has the debuff of armor shred if it is then return true
     * @param defender The character that being attack
     * @return boolean
     */
    public boolean isDefenderDebuffed(Character defender) {
        if (isEnemy(defender)) {
            Enemy enemy = (Enemy) defender;
            return enemy.getCurrentDebuffs().containsKey(Debuff.ARMORSHREADED);
        }
        return false;
    }

    /**
     * Random pick a number to deal damage to the defender
     * @param min Lowest range
     * @param max Highest range
     * @return damage number
     */
    public int getRandomDamageFromMove(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }


    /**
     * Do a random chance of apply debuff to the character
     * @param move The debuffer move
     * @param character The character being attack
     */
    public void applyDebuff(DebufferDamageMove move, Character character) {
        Enemy enemy = (Enemy) character;
        debuffDetail = debuffDatabase.getDebuffDetails(move.getDebuff());
        debuff = move.getDebuff().debuffToString();
        int chance = random.nextInt(100) + 1;

        if (chance < debuffDetail.getChanceToApply()) {
            applied = true;
            enemy.addDebuff(move.getDebuff(), debuffDatabase);
        } else {
            applied = false;
        }
    }

    public DebuffDetail getDebuffDetail() {
        return debuffDetail;
    }

    public String getDebuff(){
        return debuff;
    }

    public String getMove(){
        return move;
    }

    public int getDamageDealt(){
        return damage;
    }

    public boolean isApplied() {
        return applied;
    }

}