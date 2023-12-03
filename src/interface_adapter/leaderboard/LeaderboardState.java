package interface_adapter.leaderboard;

import entity.Profile;

import java.util.ArrayList;

public class LeaderboardState {
    private ArrayList<Profile> leaderboard = new ArrayList<Profile>(10);
    private String error = null;

    public LeaderboardState() {}

    public void setLeaderboard(ArrayList<Profile> leaderboard) {
        this.leaderboard = leaderboard;
    }
    public ArrayList<Profile> getLeaderboard() {
        return leaderboard;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
