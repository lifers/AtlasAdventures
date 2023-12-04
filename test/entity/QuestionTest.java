package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    private Question question;

    private Coordinate coordinate;

    @BeforeEach
    void init() {
        coordinate = new Coordinate(12d, 45d);
        question = new Question(coordinate, "Find country X");
        question.setScore(4);
    }

    @Test
    void setScore() {
        assertEquals(4, question.getScore());
    }

    @Test
    void getScore() {
        question.setScore(5);
        assertEquals(5, question.getScore());
    }

    @Test
    void getPrompt() {
        assertEquals("Find country X", question.getPrompt());
    }
}