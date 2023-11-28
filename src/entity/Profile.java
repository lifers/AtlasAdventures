package entity;

public class Profile {
    private final int uid;
    private double average_score;
    private int games_played;
    public Profile(int uid, double average_score, int games_played){
        this.uid = uid;
        this.average_score = average_score;
        this.games_played = games_played;
    }

    public double getAverage_score() {
        return average_score;
    }

    public int getGames_played() {
        return games_played;
    }

    public void setAverage_score(double average_score) {
        this.average_score = average_score;
    }

    public void setGames_played(int games_played) {
        this.games_played = games_played;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        else {
            Profile other = (Profile) obj;
            return this.average_score == other.average_score && this.games_played == other.games_played;
        }
    }

    public int getUid() {
        return uid;
    }
}