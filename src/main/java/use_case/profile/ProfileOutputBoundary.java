package use_case.profile;

import entity.Profile;

public interface ProfileOutputBoundary {

    /**
     * The prepareSuccessView method is used to display the profileView.
     *
     * @param  outputData  description of the outputData parameter
     * @return             description of the return value
     */
    public void prepareSuccessView(ProfileOutputData outputData);
}
