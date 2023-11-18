package use_case.AnswerQuestion;

import use_case.question.QuestionOutputData;

interface AnswerQuestionOutputBoundary {
    public void prepareSuccessView(AnswerQuestionOutputData user);

    public void prepareFailView(String error);
}