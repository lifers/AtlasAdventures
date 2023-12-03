package use_case.leaderboard;

import entity.Profile;

import java.util.ArrayList;

/**
 * Data access interface for leaderboard use case
 */
public interface LeaderboardDataAccessInterface {
    /**
     * Returns a list of ten profiles from the database, sorted in descending order
     * by average score.
     *
     * @return  a sorted ArrayList of Profile objects representing the leaderboard
     */
    public ArrayList<Profile> getLeaderboard();
    /**
     * Checks if a user ID exists in the database
     *
     * @param  uid  the user ID to check
     * @return      true if the user ID exists, false otherwise
     */
    public boolean uidExists(int uid);
    /**
     * Update a profile in the database with updated stats.
     *
     * @param  profile  the profile to be updated
     */
    public void updateProfile(Profile profile);
    /**
     * Adds a profile to the database.
     *
     * @param  profile  the profile to be added
     */
    public void addProfile(Profile profile);
    /**
     * Generates a new, unique user id.
     *
     * @return  The generated unique user id.
     */
    public int generateNewUid();
}
