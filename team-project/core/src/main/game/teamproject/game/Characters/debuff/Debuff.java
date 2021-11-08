package game.teamproject.game.Characters.debuff;

/**
 *  This create a enum type of the different type of debuffs in the game
 *  Debuffs are negative effect that character can get.
 *
 *  - Stunned = Character can not attack for that period of duration
 *  - Burned = Character take damage each turned
 *  - Armorshreads = Character takes extra damage from other moves
 */
public enum Debuff {
    STUNNED("Stunned"),
    BURNED("Burned"),
    ARMORSHREADED("Armorbreak");

    String debuff;

    Debuff(String name) {
        debuff = name;
    }

    public String debuffToString(){
        return debuff;
    }

}
