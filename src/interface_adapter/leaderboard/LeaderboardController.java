package interface_adapter.leaderboard;

import use_case.leaderboard.LeaderboardInputBoundary;

public class LeaderboardController {
    final LeaderboardInputBoundary leaderboardInteractor;
    public LeaderboardController(LeaderboardInputBoundary leaderboardInteractor) {
        this.leaderboardInteractor = leaderboardInteractor;
    }

    public void execute() {
        leaderboardInteractor.execute();
    }
}
