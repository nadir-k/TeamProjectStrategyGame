package game.teamproject.game.Characters;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * This class check the player experiences and see if the player should level up
 */

public class LevelSystem {
    private static final Map<Integer, Integer>  EXP_REQUIREMENT;
    private static final int Max_Level = 4;
    public LevelSystem(){
    }


    /**
     * Value of all the level and experiences requirements
     */
    static {
        Map<Integer, Integer> map = new HashMap();
        map.put(2,100);
        map.put(3,250);
        map.put(4,500);

        EXP_REQUIREMENT = Collections.unmodifiableMap(map);
    }

    /**
     * Check if the player current exp is enough for the player to increase the level
     * @param currentLevel Player current level
     * @param currentExp Player current experience
     * @return boolean
     */
    public boolean checkLevelUp(int currentLevel, int currentExp) {
        if(!isMaxLevel(currentLevel)) {
            return (EXP_REQUIREMENT.get(currentLevel + 1) < currentExp);
        }
        return false;
    }

    public boolean isMaxLevel(int currentLevel) {
         return currentLevel == Max_Level;
    }


    public int getRequireExp(int level) {
        if (isMaxLevel(level)) {
            level = Max_Level;
        }
        System.out.println(EXP_REQUIREMENT.get(level));
        return EXP_REQUIREMENT.get(level) ;
    }


}
