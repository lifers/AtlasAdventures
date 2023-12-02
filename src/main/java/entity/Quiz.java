package entity;

import java.util.List;

public class Quiz {
    private final List<Question> questions;
    private final int length;
    private int currQuestionIndex = -1;

    public Quiz(List<Question> questions) {
        this.questions = questions;
        this.length = questions.size();
    }

    public Question getCurrQuestion() {
        return questions.get(currQuestionIndex);
    }

    public void nextQuestion() throws IndexOutOfBoundsException {
        currQuestionIndex += 1;
        if (currQuestionIndex == length) {
            throw new IndexOutOfBoundsException();
        }
    }
}
