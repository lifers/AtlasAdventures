package interface_adapter.start_sp_quiz;

import interface_adapter.ViewManagerModel;
import interface_adapter.question.QuestionViewModel;
import use_case.start_sp_quiz.SPQuizOutputBoundary;

public class SPQuizPresenter implements SPQuizOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final SPQuizViewModel spQuizViewModel;
    private final QuestionViewModel questionViewModel;
    public SPQuizPresenter(ViewManagerModel viewManagerModel, SPQuizViewModel spQuizViewModel, QuestionViewModel questionViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.spQuizViewModel = spQuizViewModel;
        this.questionViewModel = questionViewModel;
    }

    @Override
    public void prepareSuccessView() {

    }

    @Override
    public void prepareFailView() {

    }
}
