package save;

/**
 * Store the player's stats
 */
public class Statistics {
    private int score;
    private int deaths;
    private int maxLevel;

    public Statistics() {
      score = 0;
      deaths = 0;
      maxLevel = 0;

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public int getMaxLevel() {
        return maxLevel;
    }
}
