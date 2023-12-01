package use_case.leaderboard;

import entity.Profile;

import java.util.ArrayList;

public interface LeaderboardDataAccessInterface {
    public ArrayList<Profile> getLeaderboard();

    public boolean uidExists(int uid);

    public void updateProfile(Profile profile);

    public void addProfile(Profile profile);
}
