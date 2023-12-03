package use_case.profile;
public class ProfileOutputData {

    private final double averageScore;
    private final int gamesPlayed;
    private final int UID;

public ProfileOutputData(double averageScore, int gamesPlayed, int UID) {
    this.averageScore = averageScore;
    this.gamesPlayed = gamesPlayed;
    this.UID = UID;
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

    /**
     * Retrieves the UID of the object.
     *
     * @return the UID of the object
     */
    public int getUID() {
        return UID;
    }
}
