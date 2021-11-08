package game.teamproject.game.upgrades;
/**
 * This class stores debuff move effects
 */
import java.util.HashMap;

import game.teamproject.game.Characters.debuff.Debuff;
import game.teamproject.game.Characters.moves.DamageMove;
import game.teamproject.game.Characters.moves.DebufferDamageMove;
import game.teamproject.game.Characters.moves.Move;

public class MoveDatabase {
    private HashMap<Move, DebufferDamageMove> upgradeDebufferMove;
    public MoveDatabase() {
        upgradeDebufferMove = new HashMap<>();
        upgradeDebufferMove.put(Move.ARMORBREAK,
                new DebufferDamageMove(5,10,3, Debuff.ARMORSHREADED));
        upgradeDebufferMove.put(Move.FIRE,
                new DebufferDamageMove(5,10,3, Debuff.BURNED));
        upgradeDebufferMove.put(Move.STUN,
                new DebufferDamageMove(5,10,3, Debuff.STUNNED));



    }

    public DamageMove getUpgradeMove(Move move) {
        return upgradeDebufferMove.get(move);
    }

}
