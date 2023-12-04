package entity;

import java.util.List;

public class Quiz {
    private final List<Question> questions;
    private int length;
    private int currQuestionIndex = -1;

    public Quiz(List<Question> questions) {
        this.questions = questions;
        this.length = questions.size();
    }

    /**
     * Add a question to the list of questions.
     *
     * @param  question  the question to be added
     */
    public void addQuestion(Question question) {
        questions.add(question);
        length += 1;
    }

    /**
     * Gets the current question.
     *
     * @return  the current question
     */
    public Question getCurrQuestion() {
        return questions.get(currQuestionIndex);
    }

    /**
     * Increments the current question index by 1 and throws an IndexOutOfBoundsException
     * if the current question index is equal to the length.
     *
     * @throws IndexOutOfBoundsException if the current question index is equal to the length
     */
    public void nextQuestion() throws IndexOutOfBoundsException {
        currQuestionIndex += 1;
        if (currQuestionIndex == length) {
            throw new IndexOutOfBoundsException();
        }
    }
}
