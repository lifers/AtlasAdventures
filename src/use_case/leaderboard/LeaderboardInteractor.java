package use_case.leaderboard;

import entity.Profile;
import use_case.profile.ProfileDataAccessInterface;

import java.util.ArrayList;

public class LeaderboardInteractor implements LeaderboardInputBoundary {
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
