package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.leaderboard.LeaderboardState;
import interface_adapter.leaderboard.LeaderboardViewModel;

import java.beans.PropertyChangeEvent;

import static org.junit.jupiter.api.Assertions.*;

class LeaderboardViewTest {

    @org.junit.jupiter.api.Test
    void propertyChange() {
        LeaderboardView view = new LeaderboardView(new LeaderboardViewModel(), new ViewManagerModel());
        view.propertyChange(new PropertyChangeEvent(view, "leaderboard state", null, new LeaderboardState()));
    }

}