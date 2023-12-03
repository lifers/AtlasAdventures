package interface_adapter.profile;

import interface_adapter.ViewManagerModel;
import use_case.profile.ProfileOutputBoundary;
import use_case.profile.ProfileOutputData;

public class ProfilePresenter implements ProfileOutputBoundary {
    private final ProfileViewModel profileViewModel;

    private ViewManagerModel viewManagerModel;

    public ProfilePresenter(ProfileViewModel profileViewModel, ViewManagerModel viewManagerModel) {
        this.profileViewModel = profileViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares the success view with the given profile output data.
     *
     * @param  outputData  the profile output data to be used
     */
    @Override
    public void prepareSuccessView(ProfileOutputData outputData) {
        int gamesPlayed = outputData.getGamesPlayed();
        double score = outputData.getAverageScore();
        int UID = outputData.getUID();
        String text = "Profile Number: " + String.valueOf(UID) + "      Games Played: " + String.valueOf(gamesPlayed) + "       Average Score: " + String.format("%.2f", score);
        profileViewModel.setDisplayText(text);
        profileViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(profileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
