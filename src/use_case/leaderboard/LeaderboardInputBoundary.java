package use_case.leaderboard;

/**
 * Input boundary for the leaderboard use case.
 */
public interface LeaderboardInputBoundary {
    /**
     * Creates/prepares leaderboard data and passes it through the leaderboard output boundary
     */
    void execute();
}
