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
     *
     */
    @Override
    public void prepareEndQuizView() {
        this.quizEndedViewModel.setState(this.answerQuestionViewModel.getState());
        this.quizEndedViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(this.quizEndedViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     *
     */
    @Override
    public void prepareNextQuestionView() {
        this.answerQuestionViewModel.getState().setAnswering(true);
        this.answerQuestionViewModel.firePropertyChanged();
    }

    /**
     * @param result
     */
    @Override
    public void prepareAnsweredView(AnswerQuestionOutputData result) {
        var state = this.answerQuestionViewModel.getState();
        state.addTotalScore(result.score());
        state.setAnswering(false);
        this.answerQuestionViewModel.firePropertyChanged();
    }

    /**
     *
     */
    @Override
    public void returnToMainMenu() {
        this.viewManagerModel.setActiveView(this.spQuizViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
