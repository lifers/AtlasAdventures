package interface_adapter.leaderboard;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LeaderboardViewModel extends ViewModel {

    public static final String LEADERBOARD_BUTTON_LABEL = "Leaderboard";

    public LeaderboardViewModel() {
        super("MainMenuView");
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
    public static final String LEADERBOARD_BUTTON_LABEL = "Leaderboard";
}
