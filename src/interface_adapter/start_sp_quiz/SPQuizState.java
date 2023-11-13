package interface_adapter.start_sp_quiz;

public class SPQuizState {
    // Object data type is used as a placeholder
    private Object quiz;
    public SPQuizState(SPQuizState copy) {
        quiz = copy.quiz;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public SPQuizState() {}

    public Object getSPQuiz() {
        return quiz;
    }
}
