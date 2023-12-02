package use_case.answer_question;

public interface AnswerQuestionOutputBoundary {
    void prepareEndQuizView();
    void prepareNextQuestionView();
    void prepareAnsweredView(AnswerQuestionOutputData result);
    void returnToMainMenu();
}