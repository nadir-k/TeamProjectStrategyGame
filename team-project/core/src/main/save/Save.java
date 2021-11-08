package save;
/**
 * Store player's progress
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;;

import game.teamproject.game.Characters.Player;
import game.teamproject.game.Characters.debuff.Debuff;
import game.teamproject.game.Characters.debuff.DebuffDatabase;
import game.teamproject.game.Characters.debuff.DebuffDetail;
import game.teamproject.game.Characters.moves.Move;

public class Save {
    private static final Save instance = new Save();
    Preferences prefs = Gdx.app.getPreferences("game-prefs");
    private Player player;
    private Statistics statistics;
    private DebuffDatabase debuffDatabase;

    public Save() {
        this.statistics = player.getInstance().getStatistics();
        this.player = player.getInstance();
        this.debuffDatabase = debuffDatabase.getInstance();

    }

    public static Save getInstance() {
        return instance;
    }


    /*
        Store progress
     */
    public void save() {
        prefs.putInteger("maxLevel", statistics.getMaxLevel());
        prefs.putInteger("currentEXP", player.getExperiences());
        prefs.putInteger("currentRanked", player.getLevel());
        prefs.putInteger("highScore", statistics.getScore());
        prefs.putInteger("death", statistics.getDeaths());
        prefs.putInteger("burnEffect", debuffDatabase.getDebuffDetails(Debuff.BURNED).getValue());
        prefs.putInteger("burnDuration", debuffDatabase.getDebuffDetails(Debuff.BURNED).getMaxDuration());
        prefs.putInteger("burnChance", debuffDatabase.getDebuffDetails(Debuff.BURNED).getChanceToApply());
        prefs.putInteger("stunEffect", debuffDatabase.getDebuffDetails(Debuff.STUNNED).getValue());
        prefs.putInteger("stunDuration", debuffDatabase.getDebuffDetails(Debuff.STUNNED).getMaxDuration());
        prefs.putInteger("stunChance", debuffDatabase.getDebuffDetails(Debuff.STUNNED).getChanceToApply());
        prefs.putInteger("armorEffect", debuffDatabase.getDebuffDetails(Debuff.ARMORSHREADED).getValue());
        prefs.putInteger("armorDuration", debuffDatabase.getDebuffDetails(Debuff.ARMORSHREADED).getMaxDuration());
        prefs.putInteger("armorChance", debuffDatabase.getDebuffDetails(Debuff.ARMORSHREADED).getChanceToApply());

        prefs.flush();
        load();
    }

    public void load() {
        statistics.setMaxLevel(prefs.getInteger("maxLevel"));
        player.setExperiences(prefs.getInteger("currentEXP"));
        player.setLevel(prefs.getInteger("currentRanked"));
        statistics.setDeaths(prefs.getInteger("highScore"));
        statistics.setScore(prefs.getInteger("death"));
        debuffDatabase.getDebuffDetails(Debuff.BURNED).setValue(prefs.getInteger("burnEffect"));
        debuffDatabase.getDebuffDetails(Debuff.BURNED).setValue(prefs.getInteger("burnDuration"));
        debuffDatabase.getDebuffDetails(Debuff.BURNED).setValue(prefs.getInteger("burnChance"));
        debuffDatabase.getDebuffDetails(Debuff.STUNNED).setValue(prefs.getInteger("stunEffect"));
        debuffDatabase.getDebuffDetails(Debuff.STUNNED).setValue(prefs.getInteger("stunDuration"));
        debuffDatabase.getDebuffDetails(Debuff.STUNNED).setValue(prefs.getInteger("stunChance"));
        debuffDatabase.getDebuffDetails(Debuff.ARMORSHREADED).setValue(prefs.getInteger("armorEffect"));
        debuffDatabase.getDebuffDetails(Debuff.ARMORSHREADED).setValue(prefs.getInteger("armorDuration"));
        debuffDatabase.getDebuffDetails(Debuff.ARMORSHREADED).setValue(prefs.getInteger("armorChance"));
    }
    public void reset() {
        prefs.putInteger("maxLevel", 0);
        prefs.putInteger("currentEXP", 0);
        prefs.putInteger("currentRanked", 1);
        prefs.putInteger("highScore", 0);
        prefs.putInteger("death", 0);
        prefs.putInteger("burnEffect", 5);
        prefs.putInteger("burnDuration", 3);
        prefs.putInteger("burnChance", 25);
        prefs.putInteger("stunEffect", 0);
        prefs.putInteger("stunDuration", 0);
        prefs.putInteger("stunChance", 25);
        prefs.putInteger("armorEffect", 50);
        prefs.putInteger("armorDuration", 3);
        prefs.putInteger("armorChance", 25);

        prefs.flush();
        load();

    }

}
