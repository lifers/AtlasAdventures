package app;

import data_access.FileUserDataAccessObject;
import interface_adapter.answer_question.AnswerQuestionController;
import interface_adapter.answer_question.AnswerQuestionPresenter;
import interface_adapter.answer_question.AnswerQuestionViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.answer_question.QuizEndedViewModel;
import interface_adapter.start_sp_quiz.SPQuizViewModel;
import use_case.answer_question.AnswerQuestionInteractor;
import use_case.answer_question.AnswerQuestionOutputBoundary;
import use_case.profile.ProfileDataAccessInterface;
import view.AnswerQuestionView;
import view.AnswerQuestionViewPair;
import view.QuizEndedView;

import javax.swing.JPanel;

public class QuestionUseCaseFactory {
    private QuestionUseCaseFactory() {
    }

    public static AnswerQuestionViewPair create(ViewManagerModel viewManagerModel, AnswerQuestionViewModel questionViewModel,
                                                SPQuizViewModel spQuizViewModel, QuizEndedViewModel quizEndedViewModel, ProfileDataAccessInterface dao) {
        var questionController = createQuestionUseCase(viewManagerModel, questionViewModel, spQuizViewModel, quizEndedViewModel, dao);
        var answerQuestionView = new AnswerQuestionView(questionController, questionViewModel);
        var quizEndedView = new QuizEndedView(questionController, quizEndedViewModel);
        return new AnswerQuestionViewPair(answerQuestionView, quizEndedView);
    }

    private static AnswerQuestionController createQuestionUseCase(ViewManagerModel viewManagerModel,
                                                                  AnswerQuestionViewModel questionViewModel,
                                                                  SPQuizViewModel spQuizViewModel,
                                                                  QuizEndedViewModel quizEndedViewModel, ProfileDataAccessInterface dao) {
        AnswerQuestionOutputBoundary questionOutputBoundary = new AnswerQuestionPresenter(viewManagerModel, questionViewModel,
                                                                 spQuizViewModel, quizEndedViewModel, dao);
        AnswerQuestionInteractor questionInteractor = new AnswerQuestionInteractor(questionOutputBoundary);

        return new AnswerQuestionController(questionInteractor, questionViewModel);
    }
}