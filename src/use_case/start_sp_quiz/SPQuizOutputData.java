package use_case.start_sp_quiz;

import entity.Quiz;

public class SPQuizOutputData {
    private Quiz quiz;
    public SPQuizOutputData(Quiz quiz) {
        this.quiz = quiz;
    }

    public Quiz getQuiz() {
        return this.quiz;
    }
}