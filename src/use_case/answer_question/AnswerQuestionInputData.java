package use_case.answer_question;

import entity.Quiz;
import org.openstreetmap.gui.jmapviewer.Coordinate;

/**
 * Provides the input data for the AnswerQuestion use case.
 * This class encapsulates the necessary data for answering a question.
 *
 * @param coordinate The coordinate chosen by the player.
 * @param quiz The currently played Quiz object.
 */
public record AnswerQuestionInputData(Coordinate coordinate, Quiz quiz) {
}