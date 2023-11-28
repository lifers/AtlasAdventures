package use_case.profile;

import entity.Profile;

public class ProfileOutputData {

    private double averageScore;
    private int gamesPlayed;

    public ProfileOutputData(double averageScore, int gamesPlayed) {
        this.averageScore = averageScore;
        this.gamesPlayed = gamesPlayed;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }
}
