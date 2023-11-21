package interface_adapter.leaderboard;

import entity.Profile;
import interface_adapter.ViewManagerModel;
import interface_adapter.question.QuestionViewModel;
import use_case.leaderboard.LeaderboardOutputBoundary;

import java.util.ArrayList;

public class LeaderboardPresenter implements LeaderboardOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final LeaderboardViewModel leaderboardViewModel;
    public LeaderboardPresenter(ViewManagerModel viewManagerModel, LeaderboardViewModel leaderboardViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.leaderboardViewModel = leaderboardViewModel;
    }

    @Override
    public void prepareSuccessView(ArrayList<Profile> leaderboardProfiles) {
        // For the following to work, the ViewModel viewName must be the same as the View viewName
        viewManagerModel.setActiveView(leaderboardViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView() {

    }
}
