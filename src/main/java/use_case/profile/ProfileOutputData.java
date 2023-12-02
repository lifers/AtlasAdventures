package use_case.profile;
public class ProfileOutputData {

    private final double averageScore;
    private final int gamesPlayed;

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
     * Retrieves the number of games played.
     *
     * @return the number of games played
     */
    public int getGamesPlayed() {
        return gamesPlayed;
    }
}
