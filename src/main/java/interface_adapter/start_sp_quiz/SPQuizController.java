package interface_adapter.start_sp_quiz;

import use_case.start_sp_quiz.SPQuizInputBoundary;

public class SPQuizController {
    final SPQuizInputBoundary spQuizInteractor;
    public SPQuizController(SPQuizInputBoundary spQuizInteractor) {
        this.spQuizInteractor = spQuizInteractor;
    }

    public void execute() {
        spQuizInteractor.execute();
    }
}