package interface_adapter.answer_question;

import entity.Question;
import entity.Quiz;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import use_case.answer_question.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

interface AnswerQuestionInputBoundaryTest extends AnswerQuestionInputBoundary {
    @Override
    default void answer(AnswerQuestionInputData inputData) {
        fail("wrong function to call");
    }

    @Override
    default void nextQuestion(NextQuestionInputData inputData) {
        fail("wrong function to call");
    }

    @Override
    default void returnToMainMenu() {
        fail("wrong function to call");
    }
}

class AnswerQuestionControllerTest {
    static AnswerQuestionViewModel viewModel;

    @BeforeAll
    static void setUpBeforeClass() {
        // Set up the view model
        viewModel = new AnswerQuestionViewModel();
    }

    @BeforeEach
    void setUp() {
        viewModel.setState(new AnswerQuestionState(new Quiz(List.of(
                new Question(new Coordinate(52, -1), "Where is England?"),
                new Question(new Coordinate(56, -4), "Where is Scotland?")
        ))));
    }

    @Test
    void answer() {
        var guess = new Coordinate(43, -79);
        var interactor = new AnswerQuestionInputBoundaryTest() {
            @Override
            public void answer(AnswerQuestionInputData inputData) {
                assertEquals(guess, inputData.coordinate());
            }
        };
        var controller = new AnswerQuestionController(interactor, viewModel);
        controller.answer(guess);
    }

    @Test
    void nextQuestion() {
        viewModel.getState().getQuiz().nextQuestion();
        var interactor = new AnswerQuestionInputBoundaryTest() {
            @Override
            public void nextQuestion(NextQuestionInputData inputData) {
                assertEquals(
                        viewModel.getState().getQuiz().getCurrQuestion(),
                        inputData.quiz().getCurrQuestion()
                );
            }
        };
        var controller = new AnswerQuestionController(interactor, viewModel);
        controller.nextQuestion();
    }

    @Test
    void returnMainMenu() {
        var interactor = new AnswerQuestionInputBoundaryTest() {
            @Override
            public void returnToMainMenu() {
                // Success
            }
        };
        var controller = new AnswerQuestionController(interactor, viewModel);
        controller.returnMainMenu();
    }
}