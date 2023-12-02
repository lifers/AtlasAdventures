package interface_adapter.start_sp_quiz;

import entity.Quiz;

public class SPQuizState {
    // Object data type is used as a placeholder
    private Quiz quiz;
    public SPQuizState(SPQuizState copy) {
        quiz = copy.quiz;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public SPQuizState() {}

    public Quiz getSPQuiz() {
        return quiz;
    }
}
