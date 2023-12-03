package interface_adapter.start_sp_quiz;

import entity.Quiz;

public class SPQuizState {
    // Object data type is used as a placeholder
    private Quiz quiz;
    public SPQuizState(SPQuizState copy) {
        quiz = copy.quiz;
    }

    public Quiz getSPQuiz() {
        return quiz;
    }
}
