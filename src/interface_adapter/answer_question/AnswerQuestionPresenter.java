package interface_adapter.answer_question;

import interface_adapter.ViewManagerModel;
import interface_adapter.start_sp_quiz.SPQuizViewModel;
import use_case.answer_question.AnswerQuestionOutputBoundary;
import use_case.answer_question.AnswerQuestionOutputData;

public class AnswerQuestionPresenter implements AnswerQuestionOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final SPQuizViewModel spQuizViewModel;
    private final AnswerQuestionViewModel answerQuestionViewModel;
    private final QuizEndedViewModel quizEndedViewModel;

    public AnswerQuestionPresenter(ViewManagerModel viewManagerModel, AnswerQuestionViewModel answerQuestionViewModel,
                                   SPQuizViewModel spQuizViewModel, QuizEndedViewModel quizEndedViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.spQuizViewModel = spQuizViewModel;
        this.answerQuestionViewModel = answerQuestionViewModel;
        this.quizEndedViewModel = quizEndedViewModel;
    }

    /**
     * Updates the state of the {@link QuizEndedViewModel} with the state from
     * the {@link AnswerQuestionViewModel}, and change the active view to
     * {@link view.QuizEndedView}.
     */
    @Override
    public void prepareEndQuizView() {
        this.quizEndedViewModel.setState(this.answerQuestionViewModel.getState());
        this.quizEndedViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(this.quizEndedViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Notifies the {@link view.AnswerQuestionView} to let the player
     * answer the next question.
     */
    @Override
    public void prepareNextQuestionView() {
        this.answerQuestionViewModel.getState().setAnswering(true);
        this.answerQuestionViewModel.firePropertyChanged();
    }

    /**
     * Shows the result of the last answered question.
     *
     * @param result the result from the last answered question.
     */
    @Override
    public void prepareAnsweredView(AnswerQuestionOutputData result) {
        var state = this.answerQuestionViewModel.getState();
        state.addTotalScore(result.score());
        state.setAnswering(false);
        this.answerQuestionViewModel.firePropertyChanged();
    }

    /**
     * Return to the main menu.
     */
    @Override
    public void returnToMainMenu() {
        this.viewManagerModel.setActiveView(this.spQuizViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
