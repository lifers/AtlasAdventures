package use_case.AnswerQuestion;

public interface AnswerQuestionOutputBoundary {
    void prepareEndQuizView();
    void prepareNextQuestionView();
    void prepareAnsweredView(AnswerQuestionOutputData result);
}