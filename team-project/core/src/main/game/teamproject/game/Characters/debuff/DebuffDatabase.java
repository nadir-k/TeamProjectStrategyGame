package game.teamproject.game.Characters.debuff;

import java.util.HashMap;

/**
 *  This class hold the value of each debuffs
 */

public class DebuffDatabase {
    private static final DebuffDatabase instance = new DebuffDatabase();
    private HashMap<Debuff, DebuffDetail> debuffs;

    /**
     * This code create and store debuffs in a hashmap
     */
    private DebuffDatabase() {
        debuffs = new HashMap<>();
        debuffs.put(Debuff.ARMORSHREADED,
                new DebuffDetail(3,25, 50));
        debuffs.put(Debuff.STUNNED,
                new DebuffDetail(1, 25, 0));
        debuffs.put(Debuff.BURNED,
                new DebuffDetail(3,25, 5));
    }

    public static DebuffDatabase getInstance() {
        return instance;
    }

    /**
     * Return the current debuffs value of a debuffs
     *
     * @param debuff
     * @return debuffDetails
     */

    public DebuffDetail getDebuffDetails(Debuff debuff) {
        return debuffs.get(debuff);
    }

}
