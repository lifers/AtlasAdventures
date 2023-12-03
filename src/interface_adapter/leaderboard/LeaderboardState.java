package interface_adapter.leaderboard;

import entity.Profile;

import java.util.ArrayList;

public class LeaderboardState {
    private ArrayList<Profile> leaderboard = new ArrayList<Profile>(10);
    private String error = null;

    public LeaderboardState() {}
    /**
     * Sets the leaderboard.
     *
     * @param  leaderboard  An ArrayList of Profile objects representing the new leaderboard.
     */
    public void setLeaderboard(ArrayList<Profile> leaderboard) {
        this.leaderboard = leaderboard;
    }
    /**
     * Returns the leaderboard as an ArrayList of Profile objects.
     *
     * @return  the leaderboard as an ArrayList of Profile objects
     */
    public ArrayList<Profile> getLeaderboard() {
        return leaderboard;
    }
    /**
     * Retrieves the error message associated with this object.
     *
     * @return the error message
     */
    public String getError() {
        return error;
    }
    /**
     * Sets the error message for this object.
     *
     * @param  error  the error message to be set
     */
    public void setError(String error) {
        this.error = error;
    }
}
