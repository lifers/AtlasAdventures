package interface_adapter.profile;

import use_case.profile.ProfileInputBoundary;
import use_case.profile.ProfileInputData;

public class ProfileController {
    ProfileInputBoundary profileInteractor;

    public ProfileController(ProfileInputBoundary profileInteractor) {
        this.profileInteractor = profileInteractor;
    }

    /**
     * Executes the interactor usecase.
     */
    public void execute(){
        ProfileInputData inputData = new ProfileInputData();
        profileInteractor.execute(inputData);
    }
}
