package use_case.leaderboard;

import entity.Profile;
import use_case.profile.ProfileDataAccessInterface;

import java.util.ArrayList;

public class LeaderboardInteractor implements LeaderboardInputBoundary {
    /**
     * Interactor for the leaderboard use case.
     */
    final LeaderboardOutputBoundary leaderboardPresenter;
    final LeaderboardDataAccessInterface leaderboardDAO;
    final ProfileDataAccessInterface profileDAO;

    public LeaderboardInteractor(LeaderboardOutputBoundary leaderboardPresenter,
                                 LeaderboardDataAccessInterface leaderboardDAO,
                                 ProfileDataAccessInterface profileDAO) {
        this.leaderboardPresenter = leaderboardPresenter;
        this.leaderboardDAO = leaderboardDAO;
        this.profileDAO = profileDAO;
    }

    /**
     * Checks if the user's profile is the default profile (uid = 0). If it is, generates a new, unique
     * uid and adds the profile to the database. If it is not, checks if the profile already exists in the
     * database and either adds it or updates the existing profile.
     * Then, it retrieves the top 10 profiles with the highest average scores from the database and passes
     * it to the presenter.
     */
    @Override
    public void execute() {
        try {
            Profile profile = new Profile(profileDAO.getUid(), profileDAO.getAverageScore(), profileDAO.getGamesPlayed());
            if (profile.getUid() != 0) {
                if (leaderboardDAO.uidExists(profile.getUid())) {
                    leaderboardDAO.updateProfile(profile);
                } else {
                    leaderboardDAO.addProfile(profile);
                }
            } else {
                // Give the Profile a new, unique uid and add the profile to the leaderboard
                int uniqueUid = leaderboardDAO.generateNewUid();
                profileDAO.setUid(uniqueUid);
                Profile newProfile = new Profile(uniqueUid, profileDAO.getAverageScore(), profileDAO.getGamesPlayed());
                leaderboardDAO.addProfile(newProfile);
            }
            ArrayList<Profile> leaderboardProfiles = leaderboardDAO.getLeaderboard();
            leaderboardPresenter.prepareSuccessView(leaderboardProfiles);
        } catch (Exception e) {
            leaderboardPresenter.prepareFailView(e.getMessage());
        }
    }
}
