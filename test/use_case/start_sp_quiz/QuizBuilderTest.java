package use_case.start_sp_quiz;
import data_access.GeoInfoDataAccessObject;
import entity.Question;
import entity.Quiz;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.start_sp_quiz.QuizBuilder;
import use_case.start_sp_quiz.SPQuizDataAccessInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class QuizBuilderTest {

    private Quiz quiz;
    private SPQuizDataAccessInterface SPQuizDataAccessObject;

    @BeforeEach
    void init() {
        List<Question> questions = new ArrayList<>();
        this.quiz = new Quiz(questions);
        this.SPQuizDataAccessObject = new GeoInfoDataAccessObject();
    }

    @Test
    void buildQuestion() {
        QuizBuilder quizBuilder = new QuizBuilder(quiz, SPQuizDataAccessObject);
        for (int i = 0; i < 10; i++) {
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
        Quiz newquiz = quizBuilder.getQuiz();
        newquiz.nextQuestion();
        List<Question> questions = new ArrayList<>();

        try {
            for (int i = 0; i < 11; i++) {
                questions.add(newquiz.getCurrQuestion());
                newquiz.nextQuestion();
            }

        } catch (Exception IndexOutofBoundsException) {
            assertEquals(10, questions.size());
        }

    }

    @Test
    void getQuiz() {
        QuizBuilder quizBuilder = new QuizBuilder(quiz, SPQuizDataAccessObject);
        assertEquals(quiz, quizBuilder.getQuiz());
    }
}