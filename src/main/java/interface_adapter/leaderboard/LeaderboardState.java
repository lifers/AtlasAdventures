package interface_adapter.leaderboard;

import entity.Profile;

import java.util.ArrayList;

public class LeaderboardState {
    private ArrayList<Profile> leaderboard = new ArrayList<Profile>(10);

    public LeaderboardState() {}

    public void setLeaderboard(ArrayList<Profile> leaderboard) {
        this.leaderboard = leaderboard;
    }
    public ArrayList<Profile> getLeaderboard() {
        return leaderboard;
    }
}
