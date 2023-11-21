package use_case.answer_question;

public interface AnswerQuestionInputBoundary {
    void answer(AnswerQuestionInputData inputData);
    void nextQuestion(NextQuestionInputData inputData);
}