package interface_adapter.leaderboard;

import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LeaderboardPresenterTest {

    private LeaderboardViewModel leaderboardViewModel;
    private ViewManagerModel viewManagerModel;
    @BeforeEach
    void init() {
        this.viewManagerModel = new ViewManagerModel();
        this.leaderboardViewModel = new LeaderboardViewModel();
    }

    @org.junit.jupiter.api.Test
    void prepareSuccessView() {
        LeaderboardPresenter presenter = new LeaderboardPresenter(this.viewManagerModel, this.leaderboardViewModel);

        var interceptor = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("leaderboard state")) {
                    assertEquals(leaderboardViewModel, evt.getSource());
                    assertEquals(leaderboardViewModel.getLeaderboardState(), evt.getNewValue());
                } else {
                    fail("wrong event passed");
                }
            }
        };
        this.leaderboardViewModel.addPropertyChangeListener(interceptor);
        presenter.prepareSuccessView(new ArrayList<>());
    }

    @org.junit.jupiter.api.Test
    void prepareFailView() {
        LeaderboardPresenter presenter = new LeaderboardPresenter(this.viewManagerModel, this.leaderboardViewModel);

        var interceptor = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("leaderboard state")) {
                    assertEquals(leaderboardViewModel, evt.getSource());
                    assertEquals(leaderboardViewModel.getLeaderboardState(), evt.getNewValue());
                } else {
                    fail("wrong event passed");
                }
            }
        };
        this.leaderboardViewModel.addPropertyChangeListener(interceptor);
        presenter.prepareFailView("test error");
    }
}