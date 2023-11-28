package use_case.answer_question;

/**
 * Represents the output data for the AnswerQuestion use case.
 * This class encapsulates the score for the answered question.
 *
 * @param score The score of the last answered question.
 */
public record AnswerQuestionOutputData(double score) {
}