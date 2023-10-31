package interface_adapter.question;

import interface_adapter.ViewManagerModel;
import use_case.question.QuestionOutputBoundary;
import use_case.question.QuestionOutputData;

public class QuestionPresenter implements QuestionOutputBoundary {
    private final QuestionViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    public QuestionPresenter(ViewManagerModel viewManagerModel, QuestionViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
    }

    /**
     * @param user
     */
    @Override
    public void prepareSuccessView(QuestionOutputData user) {
        this.loginViewModel.firePropertyChanged();
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * @param error
     */
    @Override
    public void prepareFailView(String error) {
        this.loginViewModel.firePropertyChanged();
    }
}
