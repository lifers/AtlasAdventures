package use_case.answer_question;

import entity.Quiz;
import org.openstreetmap.gui.jmapviewer.Coordinate;

public record AnswerQuestionInputData(Coordinate coordinate, Quiz quiz) {
}