package use_case.answer_question;

import entity.Quiz;

/**
 * This class encapsulates the Quiz object that contains the next question.
 *
 * @param quiz The Quiz object containing the next question.
 */
public record NextQuestionInputData(Quiz quiz) {}
