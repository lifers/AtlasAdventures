package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuizTest {

    private Quiz quiz;

    private Question question1;
    private Question question2;

    @BeforeEach
    void init() {
        Coordinate coordinate1 = new Coordinate(45d, 10d);
        Coordinate coordinate2 = new Coordinate(15d, 15d);

        question1 = new Question(coordinate1, "This is the first question.");
        question2 = new Question(coordinate2, "This is the second question.");
        List<Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        quiz = new Quiz(questions);
        quiz.nextQuestion();
    }

    @Test
    void getCurrQuestion() {
        assertEquals(question1, quiz.getCurrQuestion());
    }

    @Test
    void nextQuestion() {
        quiz.nextQuestion();
        assertEquals(question2, quiz.getCurrQuestion());
    }
}