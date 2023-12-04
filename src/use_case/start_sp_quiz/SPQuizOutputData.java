package use_case.start_sp_quiz;

import entity.Quiz;

public class SPQuizOutputData {
    private final Quiz quiz;
    public SPQuizOutputData(Quiz quiz) {
        this.quiz = quiz;
    }

    /**
     * Retrieves the Quiz object associated with this class.
     *
     * @return          The Quiz object associated with this class.
     */
    public Quiz getQuiz() {
        return this.quiz;
    }
}