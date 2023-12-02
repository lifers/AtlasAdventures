package use_case.profile;

public interface ProfileInputBoundary {

    /**
     * Executes the useCase with the given input data.
     *
     * @param  inputData   the input data for the function (empty for profile)
     */
    void execute(ProfileInputData inputData);
}
