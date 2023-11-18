package use_case.AnswerQuestion;

import entity.Question;
import entity.Quiz;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import java.lang.Math;

class AnswerQuestionInteractor implements AnswerQuestionInputBoundary {


    final AnswerQuestionOutputBoundary AnswerQuestionPresenter;

    AnswerQuestionInteractor(AnswerQuestionOutputBoundary answerQuestionPresenter) {
        AnswerQuestionPresenter = answerQuestionPresenter;
    }

    // ADD PRESENTER COMPONENTS AND A CONSTRUCTOR
    @Override
    public void execute(AnswerQuestionInputData inputData) {
        Quiz quiz = inputData.quiz;
        Coordinate guess = inputData.coordinate;

        Question currentQuestionQuestion = quiz.getCurrQuestion();
        Coordinate solution = currentQuestionQuestion.getSolution();

        double score = calculateScore(solution, guess);

        quiz.nextQuestion();

        AnswerQuestionOutputData outputData = new AnswerQuestionOutputData(score);

        AnswerQuestionPresenter.prepareSuccessView(outputData);
    }

    public double calculateScore(Coordinate solution, Coordinate guess){
        // Get Latitude and Longitude and convert to radians
        double lat1 = solution.getLat()/57.29577951;
        double long1 = solution.getLon()/57.29577951;
        double lat2 = guess.getLat()/57.29577951;
        double long2 = guess.getLon()/57.29577951;

        // Using the orthodromic distance formula

        double distance = 6377.830272 * Math.acos((Math.sin(lat1) * Math.sin(lat2)) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(long2 - long1));

        // Logic for computing score

        double score = 100 - distance/100;
        return Math.max(score, 0);
    }
}