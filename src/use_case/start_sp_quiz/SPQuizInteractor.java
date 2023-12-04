package use_case.start_sp_quiz;

import entity.Quiz;
import entity.Question;

import java.util.ArrayList;
import java.util.List;

public class SPQuizInteractor implements SPQuizInputBoundary {
    private final SPQuizDataAccessInterface SPQuizDataAccessObject;
    private final SPQuizOutputBoundary SPQuizPresenter;
    private int length = 10;
    public SPQuizInteractor(SPQuizDataAccessInterface SPQuizDataAccessObject, SPQuizOutputBoundary SPQuizPresenter) {
        this.SPQuizDataAccessObject = SPQuizDataAccessObject;
        this.SPQuizPresenter = SPQuizPresenter;
    }
    @Override
    public void execute() {
        List<Question> questions = new ArrayList<>();
        Quiz quiz = new Quiz(questions);
        QuizBuilder quizBuilder = new QuizBuilder(quiz, SPQuizDataAccessObject);
        for (int i = 0; i < length; i++) {
            quizBuilder.buildQuestion();
        }

        SPQuizOutputData outputData = new SPQuizOutputData(quiz);
        SPQuizPresenter.prepareSuccessView(outputData);
    }
}