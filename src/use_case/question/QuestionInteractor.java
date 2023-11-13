package use_case.question;

public class QuestionInteractor implements QuestionInputBoundary {
    final QuestionDataAccessInterface questionDataAccessObject;
    final QuestionOutputBoundary questionPresenter;

    public QuestionInteractor(QuestionDataAccessInterface questionDataAccessObject,
                              QuestionOutputBoundary questionPresenter) {
        this.questionDataAccessObject = questionDataAccessObject;
        this.questionPresenter = questionPresenter;
    }

    /**
     * @param questionInputData
     */
    @Override
    public void execute(QuestionInputData questionInputData) {
        this.questionPresenter.prepareSuccessView(new QuestionOutputData());
    }
}
