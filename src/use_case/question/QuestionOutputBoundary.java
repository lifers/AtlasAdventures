package use_case.question;

public interface QuestionOutputBoundary {
    void prepareSuccessView(QuestionOutputData user);

    void prepareFailView(String error);
}
