package interface_adapter.answer_question;

import entity.Quiz;

/**
 * The AnswerQuestionState class represents the state of {@link view.AnswerQuestionView}.
 */
public class AnswerQuestionState {
    private final Quiz quiz;
    private double totalScore = 0;
    private boolean answering = false;

    public AnswerQuestionState(Quiz quiz) {
        this.quiz = quiz;
    }

    /**
     * Retrieves the quiz object.
     *
     * @return the quiz object
     */
    public Quiz getQuiz() {
        return quiz;
    }

    /**
     * Adds the given score to the total score.
     *
     * @param  score  The score to be added.
     */
    public void addTotalScore(double score) {
        this.totalScore += score;
    }

    /**
     * Returns the total score.
     *
     * @return the total score
     */
    public double getTotalScore() {
        return totalScore;
    }

    /**
     * This function checks if the player is currently answering.
     *
     * @return  true if the object is currently answering, false otherwise
     */
    public boolean isAnswering() {
        return answering;
    }

    /**
     * Determines whether we show the answering view or not.
     *
     * @param  answering  the new value for the "answering" field
     */
    public void setAnswering(boolean answering) {
        this.answering = answering;
    }
}
