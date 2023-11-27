package use_case.answer_question;

import entity.Question;
import entity.Quiz;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

interface AnswerQuestionOutputBoundaryTest extends AnswerQuestionOutputBoundary {
    @Override
    default void prepareEndQuizView() {
        fail("wrong function to call");
    }

    @Override
    default void prepareNextQuestionView() {
        fail("wrong function to call");
    }

    @Override
    default void prepareAnsweredView(AnswerQuestionOutputData result) {
        fail("wrong function to call");
    }

    @Override
    default void returnToMainMenu() {
        fail("wrong function to call");
    }
}

class AnswerQuestionInteractorTest {
    Quiz quiz;

    @BeforeEach
    void setUp() {
        this.quiz = new Quiz(List.of(
                new Question(new Coordinate(52, -1), "Where is England?"),
                new Question(new Coordinate(56, -4), "Where is Scotland?")
        ));
    }
    
    @Test
    void answerPerfect() {
        var presenter = new AnswerQuestionOutputBoundaryTest() {
            @Override
            public void prepareAnsweredView(AnswerQuestionOutputData result) {
                assertEquals(100, result.score(), 0.001);
            }
        };
        var interactor = new AnswerQuestionInteractor(presenter);
        var input = new AnswerQuestionInputData(new Coordinate(52, -1), this.quiz); // England
        input.quiz().nextQuestion(); // Ask for England
        interactor.answer(input);
    }

    @Test
    void answerSlightlyWrong() {
        var presenter = new AnswerQuestionOutputBoundaryTest() {
            @Override
            public void prepareAnsweredView(AnswerQuestionOutputData result) {
                assertEquals(98.07, result.score(), 0.001);
            }
        };
        var interactor = new AnswerQuestionInteractor(presenter);
        var input = new AnswerQuestionInputData(new Coordinate(55, -6.5), this.quiz); // Northern Ireland
        input.quiz().nextQuestion();
        input.quiz().nextQuestion(); // Ask for Scotland
        interactor.answer(input);
    }

    @Test
    void answerHorriblyWrong() {
        var presenter = new AnswerQuestionOutputBoundaryTest() {
            @Override
            public void prepareAnsweredView(AnswerQuestionOutputData result) {
                assertEquals(0, result.score(), 0.001);
            }
        };
        var interactor = new AnswerQuestionInteractor(presenter);
        var input = new AnswerQuestionInputData(new Coordinate(-7.5, 110), this.quiz); // Java
        input.quiz().nextQuestion();
        input.quiz().nextQuestion(); // Ask for Scotland
        interactor.answer(input);
    }

    @Test
    void nextQuestionStart() {
        var presenter = new AnswerQuestionOutputBoundaryTest() {
            @Override
            public void prepareNextQuestionView() {
                // Success
            }
        };
        var interactor = new AnswerQuestionInteractor(presenter);
        var input = new NextQuestionInputData(this.quiz);
        interactor.nextQuestion(input);
    }

    @Test
    void nextQuestionEnd() {
        var presenter = new AnswerQuestionOutputBoundaryTest() {
            @Override
            public void prepareEndQuizView() {
                // Success
            }
        };
        var interactor = new AnswerQuestionInteractor(presenter);
        var input = new NextQuestionInputData(this.quiz);
        input.quiz().nextQuestion();
        input.quiz().nextQuestion();
        interactor.nextQuestion(input);
    }

    @Test
    void returnToMainMenu() {
        var presenter = new AnswerQuestionOutputBoundaryTest() {
            @Override
            public void returnToMainMenu() {
                // Success
            }
        };
        var interactor = new AnswerQuestionInteractor(presenter);
        interactor.returnToMainMenu();
    }
}