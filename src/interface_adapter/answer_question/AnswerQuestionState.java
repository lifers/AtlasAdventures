package interface_adapter.answer_question;

import entity.Quiz;

public class AnswerQuestionState {
    private final Quiz quiz;
    private double totalScore = 0;
    private boolean answering = false;

    public AnswerQuestionState(Quiz quiz) {
        this.quiz = quiz;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void addTotalScore(double score) {
        this.totalScore += score;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public boolean isAnswering() {
        return answering;
    }

    public void setAnswering(boolean answering) {
        this.answering = answering;
    }
}
