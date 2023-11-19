package use_case.answer_question;

import entity.Quiz;
import org.openstreetmap.gui.jmapviewer.Coordinate;

public class AnswerQuestionInputData {

    Coordinate coordinate;

    Quiz quiz;

    public AnswerQuestionInputData(Coordinate coordinate, Quiz quiz) {
        this.coordinate = coordinate;
        this.quiz = quiz;
    }
}