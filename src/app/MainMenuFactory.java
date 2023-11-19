package app;

import data_access.DummyDAO;
import interface_adapter.answer_question.AnswerQuestionViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.start_sp_quiz.SPQuizController;
import interface_adapter.start_sp_quiz.SPQuizPresenter;
import interface_adapter.start_sp_quiz.SPQuizViewModel;
import use_case.start_sp_quiz.SPQuizInputBoundary;
import use_case.start_sp_quiz.SPQuizOutputBoundary;
import use_case.start_sp_quiz.TestSPQuizInteractor;
import view.MainMenuView;

public class MainMenuFactory {
    private MainMenuFactory() {}

    public static MainMenuView create(
            ViewManagerModel viewManagerModel,
            SPQuizViewModel spQuizViewModel,
            AnswerQuestionViewModel questionViewModel,
            DummyDAO dummyDAO) {

        SPQuizController spQuizController = createSPQuizUseCase(viewManagerModel, spQuizViewModel, questionViewModel, dummyDAO);
        return new MainMenuView(spQuizController, spQuizViewModel);

    }

    private static SPQuizController createSPQuizUseCase(ViewManagerModel viewManagerModel,
                                                        SPQuizViewModel spQuizViewModel,
                                                        AnswerQuestionViewModel questionViewModel,
                                                        DummyDAO dummyDAO) {
        SPQuizOutputBoundary spQuizPresenter = new SPQuizPresenter(viewManagerModel, spQuizViewModel, questionViewModel);

        SPQuizInputBoundary spQuizInteractor = new TestSPQuizInteractor(spQuizPresenter, dummyDAO);

        return new SPQuizController(spQuizInteractor);
    }
}
