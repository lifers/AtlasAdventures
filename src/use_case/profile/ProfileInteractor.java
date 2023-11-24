package use_case.profile;

public class ProfileInteractor implements ProfileInputBoundary{

    private final ProfileOutputBoundary profilePresenter;

    private final ProfileDataAccessInterface dataAccessInterface;

    public ProfileInteractor(ProfileOutputBoundary profilePresenter, ProfileDataAccessInterface dataAccessInterface) {
        this.profilePresenter = profilePresenter;
        this.dataAccessInterface = dataAccessInterface;
    }


    @Override
    public void execute(ProfileInputData inputData) {

    }
}
