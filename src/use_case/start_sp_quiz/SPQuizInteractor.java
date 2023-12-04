package use_case.start_sp_quiz;

import entity.Quiz;
import entity.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SPQuizInteractor implements SPQuizInputBoundary {
    private final SPQuizDataAccessInterface SPQuizDataAccessObject;
    private final SPQuizOutputBoundary SPQuizPresenter;
    private int length = 10;
    public SPQuizInteractor(SPQuizDataAccessInterface SPQuizDataAccessObject, SPQuizOutputBoundary SPQuizPresenter) {
        this.SPQuizDataAccessObject = SPQuizDataAccessObject;
        this.SPQuizPresenter = SPQuizPresenter;
    }
    /**
     * This function calls the quizBuilder ten times with 3 different
     * randomly chosen QuestionGenerator implementations.
     *
     */
    @Override
    public void execute() {
        List<Question> questions = new ArrayList<>();
        Quiz quiz = new Quiz(questions);
        QuizBuilder quizBuilder = new QuizBuilder(quiz, SPQuizDataAccessObject);
        for (int i = 0; i < length; i++) {
            Random rand = new Random();
            int randomNumber = rand.nextInt(3);
            if (randomNumber == 0) {
                quizBuilder.buildQuestion(new CountryQuestion());
            } else if (randomNumber == 1) {
                quizBuilder.buildQuestion(new CapitalQuestion());
            } else {
                quizBuilder.buildQuestion(new AbbreviationQuestion());
            }
        }

        SPQuizOutputData outputData = new SPQuizOutputData(quiz);
        SPQuizPresenter.prepareSuccessView(outputData);
    }
}