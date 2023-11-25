package interface_adapter.start_sp_quiz;

import interface_adapter.answer_question.AnswerQuestionState;
import interface_adapter.answer_question.AnswerQuestionViewModel;
import interface_adapter.ViewManagerModel;
import use_case.start_sp_quiz.SPQuizOutputBoundary;
import use_case.start_sp_quiz.SPQuizOutputData;

public class SPQuizPresenter implements SPQuizOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final SPQuizViewModel spQuizViewModel;

    private final AnswerQuestionViewModel questionViewModel;

    public SPQuizPresenter(ViewManagerModel viewManagerModel, SPQuizViewModel spQuizViewModel, AnswerQuestionViewModel questionViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.spQuizViewModel = spQuizViewModel;
        this.questionViewModel = questionViewModel;
    }

    @Override
    public void prepareSuccessView(SPQuizOutputData SPQuizOutputData) {
        // For the following to work, the ViewModel viewName must be the same as the View viewName
        AnswerQuestionState quizState = new AnswerQuestionState(SPQuizOutputData.getQuiz());
        questionViewModel.setState(quizState);
        viewManagerModel.setActiveView(questionViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView() {

    }
}
