package use_case.leaderboard;

import entity.Profile;

import java.util.ArrayList;

public class LeaderboardInteractor implements LeaderboardInputBoundary {
    final LeaderboardOutputBoundary leaderboardPresenter;
    final LeaderboardDataAccessInterface leaderboardDAO;

    public LeaderboardInteractor(LeaderboardOutputBoundary leaderboardPresenter,
                                 LeaderboardDataAccessInterface leaderboardDAO) {
        this.leaderboardPresenter = leaderboardPresenter;
        this.leaderboardDAO = leaderboardDAO;
    }

    @Override
    public void execute() {
        ArrayList<Profile> leaderboardProfiles = leaderboardDAO.getLeaderboard();
        if (leaderboardProfiles.isEmpty()) {
            leaderboardPresenter.prepareFailView();
        } else {
            leaderboardPresenter.prepareSuccessView(leaderboardProfiles);
        }
    }
}
