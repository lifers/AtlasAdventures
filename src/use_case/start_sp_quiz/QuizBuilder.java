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

    public void buildQuestion() {
        List<String> newInfo = SPQuizDataAccessObject.information();
        while (already_countries.contains(newInfo.get(0))) {
            newInfo = SPQuizDataAccessObject.information();
        }
        already_countries.add(newInfo.get(0));
        Random rand = new Random();
        int randomNumber = rand.nextInt(3);
        if (randomNumber == 0) {
            Double latitude = Double.valueOf(newInfo.get(1));
            Double longitude = Double.valueOf(newInfo.get(2));
            Coordinate newCoordinate = new Coordinate(latitude, longitude);
            String prompt = "Click on the country: " + newInfo.get(0);
            Question newQuestion = new Question(newCoordinate, prompt);
            quiz.addQuestion(newQuestion);
        } else if (randomNumber == 1) {
            Double latitude = Double.valueOf(newInfo.get(1));
            Double longitude = Double.valueOf(newInfo.get(2));
            Coordinate newCoordinate = new Coordinate(latitude, longitude);
            String prompt = "Click on the country who's capital is: " + newInfo.get(3);
            Question newQuestion = new Question(newCoordinate, prompt);
            quiz.addQuestion(newQuestion);
        } else {
            Double latitude = Double.valueOf(newInfo.get(1));
            Double longitude = Double.valueOf(newInfo.get(2));
            Coordinate newCoordinate = new Coordinate(latitude, longitude);
            String prompt = "Click on the country who's abbreviation is: " + newInfo.get(4);
            Question newQuestion = new Question(newCoordinate, prompt);
            quiz.addQuestion(newQuestion);
        }

    }
    public Quiz getQuiz() {
        return this.quiz;
    }
}
