package use_case.profile;

import entity.Profile;

public class ProfileOutputData {

    private double averageScore;
    private int gamesPlayed;

    public ProfileOutputData(double averageScore, int gamesPlayed) {
        this.averageScore = averageScore;
        this.gamesPlayed = gamesPlayed;
    }

    /**
     * Retrieves the average score.
     *
     * @return the average score
     */
    public double getAverageScore() {
        return averageScore;
    }

    /**
     * Set the average score.
     *
     * @param  averageScore  the new average score
     */
    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    /**
     * Retrieves the number of games played.
     *
     * @return the number of games played
     */
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    /**
     * Sets the number of games played.
     *
     * @param  gamesPlayed  the number of games played
     */
    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }
}
