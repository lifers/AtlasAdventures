package use_case.start_sp_quiz;

public class TestSPQuizInteractor implements SPQuizInputBoundary {
    final SPQuizOutputBoundary spQuizPresenter;

    final SPQuizDataAccessInterface spQuizDAO;

    public TestSPQuizInteractor(SPQuizOutputBoundary spPresenter,
                                SPQuizDataAccessInterface spQuizDAO) {
        this.spQuizPresenter = spPresenter;
        this.spQuizDAO = spQuizDAO;
    }

    @Override
    public void execute() {
        spQuizPresenter.prepareSuccessView();
    }
}
