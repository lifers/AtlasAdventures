package use_case.profile;

import entity.Profile;

import java.io.IOException;

public class ProfileInteractor implements ProfileInputBoundary{

    private final ProfileOutputBoundary profilePresenter;

    private final ProfileDataAccessInterface dataAccessObject;
    public ProfileInteractor(ProfileOutputBoundary profilePresenter, ProfileDataAccessInterface dataAccessObject) {
        this.profilePresenter = profilePresenter;
        this.dataAccessObject = dataAccessObject;
    }


    /**
     * Executes the profile use case.
     *
     * @param inputData The input data for the profile use case.
     */
    @Override
    public void execute(ProfileInputData inputData) {
        // Get data from the csv file
        try{
            dataAccessObject.update();
        }
        catch (IOException e){

        }
        double score = dataAccessObject.getAverageScore();
        int gamesPlayed = dataAccessObject.getGamesPlayed();
        // pass the profile to the presenter to get displayed
        ProfileOutputData outputData = new ProfileOutputData(score, gamesPlayed);

        profilePresenter.prepareSuccessView(outputData);
    }
}
