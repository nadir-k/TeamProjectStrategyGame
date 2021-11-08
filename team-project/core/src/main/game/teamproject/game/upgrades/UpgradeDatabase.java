package game.teamproject.game.upgrades;
/**
 * This class stores all available upgrades
 */
import java.util.ArrayList;

import game.teamproject.game.Characters.debuff.Debuff;
import game.teamproject.game.Characters.debuff.DebuffDetail;
import game.teamproject.game.Characters.moves.Move;

public class UpgradeDatabase {
    private ArrayList<UpgradeDetails> upgradesList;

    public UpgradeDatabase() {
        upgradesList = new ArrayList<>();
        addUpgrade(new UpgradeDetails(Move.ARMORBREAK, Upgrade.CHANCE, false, 1, Debuff.ARMORSHREADED, 40));
        addUpgrade(new UpgradeDetails(Move.ARMORBREAK, Upgrade.DURATION, false, 2, Debuff.ARMORSHREADED, 4));
        addUpgrade(new UpgradeDetails(Move.ARMORBREAK, Upgrade.EFFECT, false, 3, Debuff.ARMORSHREADED, 75));
        addUpgrade(new UpgradeDetails(Move.FIRE, Upgrade.CHANCE, false, 4, Debuff.BURNED, 40));
        addUpgrade(new UpgradeDetails(Move.FIRE, Upgrade.DURATION, false, 5, Debuff.BURNED, 4));
        addUpgrade(new UpgradeDetails(Move.FIRE, Upgrade.EFFECT, false, 6, Debuff.BURNED, 15));
        addUpgrade(new UpgradeDetails(Move.STUN, Upgrade.CHANCE, false, 7, Debuff.STUNNED, 40));
        addUpgrade(new UpgradeDetails(Move.STUN, Upgrade.DURATION, false, 8, Debuff.STUNNED, 2));
    //    addUpgrade(new UpgradeDetails(Move.STUN, Upgrade.EFFECT, false, 9, Debuff.STUNNED));
    }

    public void addUpgrade(UpgradeDetails upgradeDetails) {
        upgradesList.add(upgradeDetails);
    }

    public ArrayList<UpgradeDetails> getUpgradesList() {
        return upgradesList;
    }
}
