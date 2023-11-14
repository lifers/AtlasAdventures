package use_case.AnswerQuestion;

import org.openstreetmap.gui.jmapviewer.Coordinate;

class AnswerQuestionInputData {

    Coordinate coordinate;

    Quiz quiz;

    public AnswerQuestionInputData(Coordinate coordinate, Quiz quiz) {
        this.coordinate = coordinate;
        this.quiz = quiz;
    }
}