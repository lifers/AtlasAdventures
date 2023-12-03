package interface_adapter.leaderboard;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * View model for leaderboard view
 */
public class LeaderboardViewModel extends ViewModel {

    public static final String LEADERBOARD_BUTTON_LABEL = "Leaderboard";
    public static final String TITLE_LABEL = "Leaderboard";
    private LeaderboardState leaderboardState = new LeaderboardState();
    public LeaderboardViewModel() {
        super("LeaderboardView");
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    /**
     * Alerts observing classes that a property of the View Model has changed.
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("leaderboard state", null, this.leaderboardState);
    }

    /**
     * Adds an observer/listener to this view model
     *
     * @param  listener  the observing/listening class
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    /**
     * Retrieves the current state of the leaderboard.
     *
     * @return the current leaderboard state.
     */
    public LeaderboardState getLeaderboardState() {
        return leaderboardState;
    }

    /**
     * Sets the leaderboard state.
     *
     * @param  leaderboardState  the new leaderboard state
     */
    public void setLeaderboardState(LeaderboardState leaderboardState) {
        this.leaderboardState = leaderboardState;
    }
}
