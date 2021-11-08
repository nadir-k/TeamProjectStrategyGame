package game.teamproject.game.upgrades;
/**
 * Class for Upgrade System logic
 */
import game.teamproject.game.Characters.debuff.Debuff;
import game.teamproject.game.Characters.debuff.DebuffDatabase;
import game.teamproject.game.Characters.debuff.DebuffDetail;
import game.teamproject.game.Characters.moves.DebufferDamageMove;
import game.teamproject.game.Characters.moves.Move;

public class UpgradeSystem {
    private MoveDatabase moveDatabase;
    private DebuffDatabase debuffDatabase;


    public UpgradeSystem(MoveDatabase moveDatabase, DebuffDatabase debuffDatabase) {
        this.moveDatabase = moveDatabase;
        this.debuffDatabase = debuffDatabase;
    }

    public void upgradeMove(UpgradeDetails upgradeDetails) {
        Debuff debuff = upgradeDetails.getDebuff();
        int upgradeValue =  upgradeDetails.getValue();

        if (upgradeDetails.getUpgrade() == Upgrade.DURATION) {
            durationUpgrade(upgradeValue, debuff);
            System.out.println("Duration");
        }
        else if (upgradeDetails.getUpgrade() == Upgrade.EFFECT) {
            valueUpgrade(upgradeValue, debuff);
            System.out.println("Effect");
        }
        else if (upgradeDetails.getUpgrade() == Upgrade.CHANCE) {
            chanceUpgrade(upgradeValue, debuff);
            System.out.println("chance");
        }
    }



    public void damageUpgrade(Move move, DebufferDamageMove debufferDamageMove) {
        int maxValue = moveDatabase.getUpgradeMove(move).getMaximumDamage();
        int minValue = moveDatabase.getUpgradeMove(move).getMinimumDamage();
        debufferDamageMove.setMinimumDamage(minValue);
        debufferDamageMove.setMaximumDamage(maxValue);

    }

    public void chanceUpgrade(int value, Debuff debuff) {
        debuffDatabase.getDebuffDetails(debuff).setChanceToApply(value);
    }

    public void cooldownUpgrade(Move move,DebufferDamageMove debufferDamageMove) {
        int CDValue = moveDatabase.getUpgradeMove(move).getMaxCooldown();
        debufferDamageMove.setMaxCooldown(CDValue);
    }

    public void durationUpgrade(int value, Debuff debuff) {
        debuffDatabase.getDebuffDetails(debuff).setMaxDuration(value);
    }

    public void valueUpgrade(int value, Debuff debuff) {
        debuffDatabase.getDebuffDetails(debuff).setValue(value);
    }


}
