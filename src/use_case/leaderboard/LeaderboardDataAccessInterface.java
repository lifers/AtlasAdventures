package use_case.leaderboard;

import entity.Profile;

import java.util.ArrayList;

public interface LeaderboardDataAccessInterface {
    public ArrayList<Profile> getLeaderboard();

    public void addProfile(Profile profile);
}
