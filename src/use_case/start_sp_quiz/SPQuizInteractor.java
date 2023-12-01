package use_case.start_sp_quiz;

import entity.Quiz;
import entity.Question;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class SPQuizInteractor implements SPQuizInputBoundary {
    private final SPQuizDataAccessInterface SPQuizDataAccessObject;
    private final SPQuizOutputBoundary SPQuizPresenter;
    public SPQuizInteractor(SPQuizDataAccessInterface SPQuizDataAccessObject, SPQuizOutputBoundary SPQuizPresenter) {
        this.SPQuizDataAccessObject = SPQuizDataAccessObject;
        this.SPQuizPresenter = SPQuizPresenter;
    }
    @Override
    public void execute() {
        List<List<String>> newInfo = SPQuizDataAccessObject.information();

        List<Question> questions = new ArrayList<>();

        for (int i = 0; i < 10; i++ ) {
            int randomNumber = (int) Math.round((Math.random() * 2));
            if (randomNumber == 0) {
                Double latitude = Double.valueOf(newInfo.get(i).get(1));
                Double longitude = Double.valueOf(newInfo.get(i).get(2));
                Coordinate newCoordinate = new Coordinate(latitude, longitude);
                String prompt = "Click on the country: " + newInfo.get(i).get(0);
                Question newQuestion = new Question(newCoordinate, prompt);
                questions.add(newQuestion);
            } else if (randomNumber == 1) {
                Double latitude = Double.valueOf(newInfo.get(i).get(1));
                Double longitude = Double.valueOf(newInfo.get(i).get(2));
                Coordinate newCoordinate = new Coordinate(latitude, longitude);
                String prompt = "Click on the country who's capital is: " + newInfo.get(i).get(3);
                Question newQuestion = new Question(newCoordinate, prompt);
                questions.add(newQuestion);
            } else {
                Double latitude = Double.valueOf(newInfo.get(i).get(1));
                Double longitude = Double.valueOf(newInfo.get(i).get(2));
                Coordinate newCoordinate = new Coordinate(latitude, longitude);
                String prompt = "Click on the country who's abbreviation is: " + newInfo.get(i).get(4);
                Question newQuestion = new Question(newCoordinate, prompt);
                questions.add(newQuestion);
            }
        }
        Quiz quiz = new Quiz(questions);
        SPQuizOutputData outputData = new SPQuizOutputData(quiz);
        SPQuizPresenter.prepareSuccessView(outputData);
    }
}