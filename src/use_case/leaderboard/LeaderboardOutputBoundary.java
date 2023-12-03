package use_case.leaderboard;

import entity.Profile;

import java.util.ArrayList;

/**
 * Output boundary for the leaderboard use case.
 */
public interface LeaderboardOutputBoundary {
    /**
     * Prepares the view for displaying the leaderboard.
     *
     * @param  leaderboardProfiles  the list of profiles to be displayed in the leaderboard
     */
    void prepareSuccessView(ArrayList<Profile> leaderboardProfiles);
    /**
     * Prepares the fail view with the given error.
     *
     * @param  error  the error message to display in the fail view
     */
    void prepareFailView(String error);
}
