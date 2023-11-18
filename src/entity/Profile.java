package entity;

public class Profile {
    private double average_score;
    private double games_played;
    public Profile(double average_score, double games_played){
        this.average_score = average_score;
        this.games_played = games_played;
    }

    public double getAverage_score() {
        return average_score;
    }

    public double getGames_played() {
        return games_played;
    }

    public void setAverage_score(double average_score) {
        this.average_score = average_score;
    }

    public void setGames_played(double games_played) {
        this.games_played = games_played;
    }
}