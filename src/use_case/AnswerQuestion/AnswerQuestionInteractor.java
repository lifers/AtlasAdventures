package use_case.AnswerQuestion;

import entity.Question;
import entity.Quiz;
import org.openstreetmap.gui.jmapviewer.Coordinate;

public record AnswerQuestionInteractor(
        AnswerQuestionOutputBoundary answerQuestionPresenter) implements AnswerQuestionInputBoundary {
    // ADD PRESENTER COMPONENTS AND A CONSTRUCTOR
    @Override
    public void answer(AnswerQuestionInputData inputData) {
        Quiz quiz = inputData.quiz;
        Coordinate guess = inputData.coordinate;

        Question currentQuestionQuestion = quiz.getCurrQuestion();
        Coordinate solution = currentQuestionQuestion.getSolution();

        double distance = calculateDistance(solution, guess);
        double score = calculateScore(distance);

        AnswerQuestionOutputData outputData = new AnswerQuestionOutputData(score);

        answerQuestionPresenter.prepareAnsweredView(outputData);
    }

    /**
     * @param inputData
     */
    @Override
    public void nextQuestion(NextQuestionInputData inputData) {
        try {
            inputData.quiz().nextQuestion();
            answerQuestionPresenter.prepareNextQuestionView();
        } catch (IndexOutOfBoundsException e) {
            answerQuestionPresenter.prepareEndQuizView();
        }
    }

    private double calculateDistance(Coordinate solution, Coordinate guess) {
        // Get Latitude and Longitude and convert to radians
        double lat1 = solution.getLat() / 57.29577951;
        double long1 = solution.getLon() / 57.29577951;
        double lat2 = guess.getLat() / 57.29577951;
        double long2 = guess.getLon() / 57.29577951;

        // Using the orthodromic distance formula
        return 6377.830272 * Math.acos(
                (Math.sin(lat1) * Math.sin(lat2)) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(long2 - long1));
    }

    private double calculateScore(double distance) {
        // Logic for computing score

        double score = 100 - distance / 100;
        return Math.max(score, 0);
    }
}
