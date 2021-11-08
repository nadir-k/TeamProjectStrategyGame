package game.teamproject.game.upgrades;
/**
 * Returns upgrade details
 */
import game.teamproject.game.Characters.debuff.Debuff;
import game.teamproject.game.Characters.moves.Move;

public class UpgradeDetails {
    private Move move;
    private Upgrade upgrade;
    private boolean Upgraded;
    private int ID;
    private Debuff debuff;
    private int value;

    public UpgradeDetails(Move move, Upgrade upgrade, boolean upgraded, int ID, Debuff debuff, int value) {
        this.move = move;
        this.upgrade = upgrade;
        Upgraded = upgraded;
        this.ID = ID;
        this.debuff = debuff;
        this.value = value;

    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public Move getMove() {
        return move;
    }

    public Upgrade getUpgrade() {
        return upgrade;
    }

    public boolean isUpgraded() {
        return Upgraded;
    }

    public Debuff getDebuff() {
        return debuff;
    }

    public void setDebuff(Debuff debuff) {
        this.debuff = debuff;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
