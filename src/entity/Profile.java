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

    public Profile(double average_score, int games_played){
        this.average_score = average_score;
        this.games_played = games_played;
        this.uid = 0; // Default uid
    }

    /**
     * Retrieves the average score.
     *
     * @return  the average score
     */
    public double getAverage_score() {
        return average_score;
    }

    /**
     * Returns the number of games played.
     *
     * @return  the number of games played
     */
    public int getGames_played() {
        return games_played;
    }

    /**
     * Sets the average score.
     *
     * @param  average_score  the new average score
     */
    public void setAverage_score(double average_score) {
        this.average_score = average_score;
    }

    /**
     * Set the number of games played.
     *
     * @param  games_played  the number of games played
     */
    public void setGames_played(int games_played) {
        this.games_played = games_played;
    }

    /**
     * Overrides the equals() method to compare two Profile objects.
     *
     * @param  obj  the object to be compared
     * @return      true if the objects are equal, false otherwise
     */
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
            return this.average_score == other.average_score && this.games_played == other.games_played && this.uid == other.uid;
        }
    }

    /**
     * Returns a string representation of the Profile object.
     *
     * @return  a string representation of the Profile object
     */
    @Override
    public String toString() {
        return "Profile{" +
                "uid='" + uid + '\'' +
                ", avgScore='" + average_score + '\'' +
                ", gamesPlayed=" + games_played +
                '}';
    }

    /**
     * Returns the uid.
     *
     * @return        uid
     */
    public int getUid() {
        return uid;
    }
}