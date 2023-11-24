package interface_adapter.answer_question;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import use_case.answer_question.AnswerQuestionInputBoundary;
import use_case.answer_question.AnswerQuestionInputData;
import use_case.answer_question.NextQuestionInputData;

public class AnswerQuestionController {
    private final AnswerQuestionInputBoundary interactor;
    private final AnswerQuestionViewModel viewModel;

    public AnswerQuestionController(AnswerQuestionInputBoundary interactor, AnswerQuestionViewModel viewModel) {
        this.interactor = interactor;
        this.viewModel = viewModel;
    }

    /**
     * Answer the current question with the user-chosen coordinate.
     *
     * @param  coordinate     the coordinate used to answer the question
     */
    public void answer(Coordinate coordinate) {
        var questionInputData = new AnswerQuestionInputData(coordinate, this.viewModel.getState().getQuiz());

        this.interactor.answer(questionInputData);
    }

    /**
     * Go to the next question.
     */
    public void nextQuestion() {
        var nextQuestionInputData = new NextQuestionInputData(this.viewModel.getState().getQuiz());

        this.interactor.nextQuestion(nextQuestionInputData);
    }

    /**
     * Return to the main menu.
     */
    public void returnMainMenu() {
        this.interactor.returnToMainMenu();
    }
}
