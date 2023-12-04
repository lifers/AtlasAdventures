package use_case.start_sp_quiz;

public interface SPQuizOutputBoundary {

    /**
     * Prepares the success view for the given SPQuizOutputData.
     *
     * @param  SPQuizOutputData  the data to be used for preparing the success view
     */
    void prepareSuccessView(SPQuizOutputData SPQuizOutputData);

}
