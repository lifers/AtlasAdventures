package interface_adapter.answer_question;

import interface_adapter.ViewManagerModel;
import interface_adapter.start_sp_quiz.SPQuizViewModel;
import use_case.answer_question.AnswerQuestionOutputBoundary;
import use_case.answer_question.AnswerQuestionOutputData;

public record AnswerQuestionPresenter(ViewManagerModel viewManagerModel,
                                      AnswerQuestionViewModel answerQuestionViewModel,
                                      SPQuizViewModel spQuizViewModel,
                                      QuizEndedViewModel quizEndedViewModel) implements AnswerQuestionOutputBoundary {
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
