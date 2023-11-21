package use_case.start_sp_quiz;

public interface SPQuizOutputBoundary {

    void prepareSuccessView(SPQuizOutputData SPQuizOutputData);

    void prepareFailView();
}
