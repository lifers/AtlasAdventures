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
        Profile profile = new Profile(profileDAO.getUid(), profileDAO.getAverageScore(), profileDAO.getGamesPlayed());
        if (profile.getUid() != 0) {
            if (leaderboardDAO.uidExists(profile.getUid())) {
                leaderboardDAO.updateProfile(profile);
            } else {
                leaderboardDAO.addProfile(profile);
            }
        }
        ArrayList<Profile> leaderboardProfiles = leaderboardDAO.getLeaderboard();
        if (leaderboardProfiles.isEmpty()) {
            leaderboardPresenter.prepareFailView();
        } else {
            leaderboardPresenter.prepareSuccessView(leaderboardProfiles);
        }
    }
}
