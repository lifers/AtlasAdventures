package interface_adapter.leaderboard;

public class LeaderboardState {
    private String[] leaderboard = new String[10];

    public LeaderboardState() {}

    public void setLeaderboard(String[] leaderboard) {
        this.leaderboard = leaderboard;
    }
}
