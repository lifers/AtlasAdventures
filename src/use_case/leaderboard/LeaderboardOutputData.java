package use_case.leaderboard;

import entity.Profile;

import java.util.ArrayList;

public class LeaderboardOutputData {
    private ArrayList<Profile> leaderboard;

    public LeaderboardOutputData(ArrayList<Profile> leaderboard) {
        this.leaderboard = leaderboard;
    }

    public ArrayList<Profile> getLeaderboard() {
        return leaderboard;
    }
}
