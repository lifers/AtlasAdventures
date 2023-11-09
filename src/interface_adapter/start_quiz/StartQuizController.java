package interface_adapter.start_quiz;

import use_case.start_quiz.StartQuizInputBoundary;

public class StartQuizController {
    final StartQuizInputBoundary startQuizInteractor;
    public StartQuizController(StartQuizInputBoundary startQuizInteractor) {
        this.startQuizInteractor = startQuizInteractor;
    }

    public void execute() {
        startQuizInteractor.execute();
    }
}