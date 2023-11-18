package entity;

import java.util.List;

public class Quiz {
    private final List<Question> questions;
    private final int length;
    private int currQuestionIndex = 0;

    public Quiz(List<Question> questions) {
        this.questions = questions;
        this.length = questions.size();
    }

    public Question getCurrQuestion() throws IndexOutOfBoundsException {
        return questions.get(currQuestionIndex);
    }

    public void nextQuestion() {
        currQuestionIndex += 1;
    }
}
