package interface_adapter.leaderboard;

import entity.Profile;
import interface_adapter.ViewManagerModel;
import use_case.leaderboard.LeaderboardOutputBoundary;
import view.LeaderboardView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LeaderboardPresenter implements LeaderboardOutputBoundary {
    /**
     * Presenter for the leaderboard use case.
     */
    private final ViewManagerModel viewManagerModel;
    private final LeaderboardViewModel leaderboardViewModel;
    public LeaderboardPresenter(ViewManagerModel viewManagerModel, LeaderboardViewModel leaderboardViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.leaderboardViewModel = leaderboardViewModel;
    }

    /**
     * Passes the leaderboard to the ViewModel and alerts the View.
     *
     * @param  leaderboardProfiles  the sorted ArrayList of Profile objects representing the leaderboard
     */
    @Override
    public void prepareSuccessView(ArrayList<Profile> leaderboardProfiles) {
        LeaderboardState leaderboardState = leaderboardViewModel.getLeaderboardState();
        leaderboardState.setLeaderboard(leaderboardProfiles);
        this.leaderboardViewModel.setLeaderboardState(leaderboardState);
        leaderboardViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(leaderboardViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Alerts the View that an error has occurred.
     *
     * @param  error   the error message
     */
    @Override
    public void prepareFailView(String error) {
        LeaderboardState leaderboardState = leaderboardViewModel.getLeaderboardState();
        leaderboardState.setError(error);
        this.leaderboardViewModel.setLeaderboardState(leaderboardState);
        leaderboardViewModel.firePropertyChanged();
    }
}
