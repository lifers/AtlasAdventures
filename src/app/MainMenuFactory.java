package app;


import data_access.GeoInfoAccessObject;
import interface_adapter.answer_question.AnswerQuestionViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.start_sp_quiz.SPQuizController;
import interface_adapter.start_sp_quiz.SPQuizPresenter;
import interface_adapter.start_sp_quiz.SPQuizViewModel;
import use_case.start_sp_quiz.SPQuizInputBoundary;
import use_case.start_sp_quiz.SPQuizInteractor;
import use_case.start_sp_quiz.SPQuizOutputBoundary;
import view.MainMenuView;

public class MainMenuFactory {
    private MainMenuFactory() {}

    public static MainMenuView create(
            ViewManagerModel viewManagerModel,
            SPQuizViewModel spQuizViewModel,
            AnswerQuestionViewModel questionViewModel,
            GeoInfoAccessObject dummyDAO) {

        SPQuizController spQuizController = createSPQuizUseCase(viewManagerModel, spQuizViewModel, questionViewModel, dummyDAO);
        return new MainMenuView(spQuizController, spQuizViewModel);

    }

    private static SPQuizController createSPQuizUseCase(ViewManagerModel viewManagerModel,
                                                        SPQuizViewModel spQuizViewModel,
                                                        AnswerQuestionViewModel questionViewModel,
                                                        GeoInfoAccessObject dummyDAO) {
        SPQuizOutputBoundary spQuizPresenter = new SPQuizPresenter(viewManagerModel, spQuizViewModel, questionViewModel);

        SPQuizInputBoundary spQuizInteractor = new SPQuizInteractor(dummyDAO, spQuizPresenter);

        return new SPQuizController(spQuizInteractor);
    }
}
