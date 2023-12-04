package use_case.start_sp_quiz;

import data_access.GeoInfoDataAccessObject;
import entity.Question;
import entity.Quiz;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import use_case.start_sp_quiz.SPQuizDataAccessInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class QuizBuilder {
    private Quiz quiz;
    private final SPQuizDataAccessInterface SPQuizDataAccessObject;
    private List<String> already_countries;

    public QuizBuilder(Quiz quiz, SPQuizDataAccessInterface spQuizDataAccessObject) {
        this.quiz = quiz;
        this.SPQuizDataAccessObject = spQuizDataAccessObject;
        this.already_countries = new ArrayList<>();
    }
    /**
     * Builds a question using the provided question generator and adds it to the quiz.
     *
     * @param  questionGenerator  the question generator used to generate the question
     */
    public void buildQuestion(QuestionGenerator questionGenerator) {
        List<String> newInfo = SPQuizDataAccessObject.information();
        while (already_countries.contains(newInfo.get(0))) {
            newInfo = SPQuizDataAccessObject.information();
        }
        already_countries.add(newInfo.get(0));


        quiz.addQuestion(questionGenerator.generateQuestion(newInfo));

    }
    public Quiz getQuiz() {
        return this.quiz;
    }
}
