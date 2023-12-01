package use_case.answer_question;

import entity.Question;
import entity.Quiz;
import org.openstreetmap.gui.jmapviewer.Coordinate;

public class AnswerQuestionInteractor implements AnswerQuestionInputBoundary {
    private final AnswerQuestionOutputBoundary answerQuestionPresenter;

    public AnswerQuestionInteractor(AnswerQuestionOutputBoundary answerQuestionPresenter) {
        this.answerQuestionPresenter = answerQuestionPresenter;
    }

    /**
     * Calculates the answer for a given question.
     *
     * @param inputData The input data containing the Quiz object and the user's guess.
     */
    @Override
    public void answer(AnswerQuestionInputData inputData) {
        Quiz quiz = inputData.quiz();
        Coordinate guess = inputData.coordinate();

        Question currentQuestionQuestion = quiz.getCurrQuestion();
        Coordinate solution = currentQuestionQuestion.getSolution();

        double distance = calculateDistance(solution, guess);
        double score = calculateScore(distance);

        AnswerQuestionOutputData outputData = new AnswerQuestionOutputData(score);

        answerQuestionPresenter.prepareAnsweredView(outputData);
    }

    /**
     * Go to the next question.
     *
     * @param inputData contains the current Quiz object.
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

    /**
     * Returns to the main menu.
     */
    @Override
    public void returnToMainMenu() {
        answerQuestionPresenter.returnToMainMenu();
    }

    /**
     * Calculates the true Earth distance between two coordinates.
     *
     * @param solution The solution coordinate.
     * @param guess    The guessed coordinate.
     * @return The distance between the solution and guess coordinates.
     */
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

    /**
     * Calculates the score based on the distance.
     *
     * @param distance The distance between the solution and guessed coordinate.
     * @return The calculated score.
     */
    private double calculateScore(double distance) {
        // Logic for computing score

        double score = 100 - distance / 100;
        return Math.max(score, 0);
    }
}
