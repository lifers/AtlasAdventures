package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.question.QuestionController;
import interface_adapter.question.QuestionPresenter;
import interface_adapter.question.QuestionViewModel;
import use_case.question.QuestionDataAccessInterface;
import use_case.question.QuestionInteractor;
import view.QuestionView;

public class QuestionUseCaseFactory {
    private QuestionUseCaseFactory() {}

    public static QuestionView create(ViewManagerModel viewManagerModel,
                                      QuestionViewModel questionViewModel,
                                      QuestionDataAccessInterface questionDataAccessObject) {
        var questionController = createQuestionUseCase(viewManagerModel, questionViewModel, questionDataAccessObject);
        return new QuestionView(questionController, questionViewModel);
    }

    public static QuestionController createQuestionUseCase(ViewManagerModel viewManagerModel,
                                                           QuestionViewModel questionViewModel,
                                                           QuestionDataAccessInterface questionDataAccessObject) {
        var questionOutputBoundary = new QuestionPresenter(viewManagerModel, questionViewModel);

        var questionInteractor = new QuestionInteractor(questionDataAccessObject, questionOutputBoundary);

        return new QuestionController(questionInteractor);
    }
}