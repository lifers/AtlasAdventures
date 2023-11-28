package interface_adapter.leaderboard;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LeaderboardViewModel extends ViewModel {

    public static final String LEADERBOARD_BUTTON_LABEL = "Leaderboard";
    public static final String TITLE_LABEL = "Leaderboard";
    private LeaderboardState leaderboardState = new LeaderboardState();
    public LeaderboardViewModel() {
        super("LeaderboardView");
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("leaderboard state", null, this.leaderboardState);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
    public LeaderboardState getLeaderboardState() {
        return leaderboardState;
    }

    public void setLeaderboardState(LeaderboardState leaderboardState) {
        this.leaderboardState = leaderboardState;
    }
}
