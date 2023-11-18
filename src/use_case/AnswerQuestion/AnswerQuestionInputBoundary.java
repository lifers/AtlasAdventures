package use_case.AnswerQuestion;

public interface AnswerQuestionInputBoundary {
    void answer(AnswerQuestionInputData inputData);
    void nextQuestion(NextQuestionInputData inputData);
}