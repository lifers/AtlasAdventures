package interface_adapter.leaderboard;

import use_case.leaderboard.LeaderboardInputBoundary;

/**
 * The controller for the leaderboard use case.
 */
public class LeaderboardController {
    final LeaderboardInputBoundary leaderboardInteractor;
    public LeaderboardController(LeaderboardInputBoundary leaderboardInteractor) {
        this.leaderboardInteractor = leaderboardInteractor;
    }
    /**
     * Calls the leaderboard use case interactor.
     */
    public void execute() {
        leaderboardInteractor.execute();
    }
}
