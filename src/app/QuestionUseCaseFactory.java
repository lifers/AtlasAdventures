package app;

import interface_adapter.answer_question.AnswerQuestionController;
import interface_adapter.answer_question.AnswerQuestionPresenter;
import interface_adapter.answer_question.AnswerQuestionViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.start_sp_quiz.SPQuizViewModel;
import use_case.answer_question.AnswerQuestionInteractor;
import view.AnswerQuestionView;

import javax.swing.JPanel;

public class QuestionUseCaseFactory {
    private QuestionUseCaseFactory() {
    }

    public static AnswerQuestionView create(JPanel parent, ViewManagerModel viewManagerModel, AnswerQuestionViewModel questionViewModel,
                                            SPQuizViewModel spQuizViewModel) {
        var questionController = createQuestionUseCase(viewManagerModel, questionViewModel, spQuizViewModel);
        return new AnswerQuestionView(parent, questionController, questionViewModel);
    }

    private static AnswerQuestionController createQuestionUseCase(ViewManagerModel viewManagerModel,
                                                                  AnswerQuestionViewModel questionViewModel,
                                                                  SPQuizViewModel spQuizViewModel) {
        var questionOutputBoundary = new AnswerQuestionPresenter(viewManagerModel, questionViewModel, spQuizViewModel);
        var questionInteractor = new AnswerQuestionInteractor(questionOutputBoundary);

        return new AnswerQuestionController(questionInteractor, questionViewModel);
    }
}