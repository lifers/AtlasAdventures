package interface_adapter.answer_question;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import use_case.answer_question.AnswerQuestionInputBoundary;
import use_case.answer_question.AnswerQuestionInputData;
import use_case.answer_question.NextQuestionInputData;

public record AnswerQuestionController(AnswerQuestionInputBoundary interactor, AnswerQuestionViewModel viewModel) {
    public void answer(Coordinate coordinate) {
        var questionInputData = new AnswerQuestionInputData(coordinate, this.viewModel.getState().getQuiz());

        this.interactor.answer(questionInputData);
    }

    public void nextQuestion() {
        var nextQuestionInputData = new NextQuestionInputData(this.viewModel.getState().getQuiz());

        this.interactor.nextQuestion(nextQuestionInputData);
    }

    public void returnMainMenu() {
        this.interactor.returnToMainMenu();
    }
}
