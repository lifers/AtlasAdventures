package interface_adapter.leaderboard;

import use_case.leaderboard.LeaderboardInputBoundary;
import use_case.start_sp_quiz.SPQuizInputBoundary;

public class LeaderboardController {
    final LeaderboardInputBoundary leaderboardInteractor;
    public LeaderboardController(LeaderboardInputBoundary leaderboardInteractor) {
        this.leaderboardInteractor = leaderboardInteractor;
    }

    public void execute() {
        leaderboardInteractor.execute();
    }
}
