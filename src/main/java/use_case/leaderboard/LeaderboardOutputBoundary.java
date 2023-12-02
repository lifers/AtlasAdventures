package use_case.leaderboard;

import entity.Profile;

import java.util.ArrayList;

public interface LeaderboardOutputBoundary {
    void prepareSuccessView(ArrayList<Profile> leaderboardProfiles);
    void prepareFailView();
}
