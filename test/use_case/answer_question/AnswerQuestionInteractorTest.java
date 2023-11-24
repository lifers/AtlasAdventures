package use_case.answer_question;

import entity.Question;
import entity.Quiz;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnswerQuestionInteractorTest {

    @org.junit.jupiter.api.Test
    void answerPerfect() {
        var presenter = new AnswerQuestionOutputBoundary() {
            @Override
            public void prepareEndQuizView() {
                fail("wrong function to call");
            }

            @Override
            public void prepareNextQuestionView() {
                fail("wrong function to call");
            }

            @Override
            public void prepareAnsweredView(AnswerQuestionOutputData result) {
                assertEquals(100, result.score(), 0.001);
            }

            @Override
            public void returnToMainMenu() {
                fail("wrong function to call");
            }
        };
        var interactor = new AnswerQuestionInteractor(presenter);
        var input = new AnswerQuestionInputData(
                new Coordinate(52, -1), // England
                new Quiz(List.of(
                        new Question(new Coordinate(52, -1), "Where is England?"),
                        new Question(new Coordinate(56, -4), "Where is Scotland?")
                ))
        );
        input.quiz().nextQuestion(); // Ask for England
        interactor.answer(input);
    }

    @org.junit.jupiter.api.Test
    void answerSlightlyWrong() {
        var presenter = new AnswerQuestionOutputBoundary() {
            @Override
            public void prepareEndQuizView() {
                fail("wrong function to call");
            }

            @Override
            public void prepareNextQuestionView() {
                fail("wrong function to call");
            }

            @Override
            public void prepareAnsweredView(AnswerQuestionOutputData result) {
                assertEquals(98.07, result.score(), 0.001);
            }

            @Override
            public void returnToMainMenu() {
                fail("wrong function to call");
            }
        };
        var interactor = new AnswerQuestionInteractor(presenter);
        var input = new AnswerQuestionInputData(
                new Coordinate(55, -6.5), // Northern Ireland
                new Quiz(List.of(
                        new Question(new Coordinate(52, -1), "Where is England?"),
                        new Question(new Coordinate(56, -4), "Where is Scotland?")
                ))
        );
        input.quiz().nextQuestion();
        input.quiz().nextQuestion(); // Ask for Scotland
        interactor.answer(input);
    }

    @org.junit.jupiter.api.Test
    void answerHorriblyWrong() {
        var presenter = new AnswerQuestionOutputBoundary() {
            @Override
            public void prepareEndQuizView() {
                fail("wrong function to call");
            }

            @Override
            public void prepareNextQuestionView() {
                fail("wrong function to call");
            }

            @Override
            public void prepareAnsweredView(AnswerQuestionOutputData result) {
                assertEquals(0, result.score(), 0.001);
            }

            @Override
            public void returnToMainMenu() {
                fail("wrong function to call");
            }
        };
        var interactor = new AnswerQuestionInteractor(presenter);
        var input = new AnswerQuestionInputData(
                new Coordinate(-7.5, 110), // Java
                new Quiz(List.of(
                        new Question(new Coordinate(52, -1), "Where is England?"),
                        new Question(new Coordinate(56, -4), "Where is Scotland?")
                ))
        );
        input.quiz().nextQuestion();
        input.quiz().nextQuestion(); // Ask for Scotland
        interactor.answer(input);
    }

    @org.junit.jupiter.api.Test
    void nextQuestionStart() {
        var presenter = new AnswerQuestionOutputBoundary() {
            @Override
            public void prepareEndQuizView() {
                fail("wrong function to call");
            }

            @Override
            public void prepareNextQuestionView() {
                // Success
            }

            @Override
            public void prepareAnsweredView(AnswerQuestionOutputData result) {
                fail("wrong function to call");
            }

            @Override
            public void returnToMainMenu() {
                fail("wrong function to call");
            }
        };
        var interactor = new AnswerQuestionInteractor(presenter);
        var input = new NextQuestionInputData(
                new Quiz(List.of(
                        new Question(new Coordinate(52, -1), "Where is England?"),
                        new Question(new Coordinate(56, -4), "Where is Scotland?")
                ))
        );
        interactor.nextQuestion(input);
    }

    @org.junit.jupiter.api.Test
    void nextQuestionEnd() {
        var presenter = new AnswerQuestionOutputBoundary() {
            @Override
            public void prepareEndQuizView() {
                // Success
            }

            @Override
            public void prepareNextQuestionView() {
                fail("wrong function to call");
            }

            @Override
            public void prepareAnsweredView(AnswerQuestionOutputData result) {
                fail("wrong function to call");
            }

            @Override
            public void returnToMainMenu() {
                fail("wrong function to call");
            }
        };
        var interactor = new AnswerQuestionInteractor(presenter);
        var input = new NextQuestionInputData(
                new Quiz(List.of(
                        new Question(new Coordinate(52, -1), "Where is England?"),
                        new Question(new Coordinate(56, -4), "Where is Scotland?")
                ))
        );
        input.quiz().nextQuestion();
        input.quiz().nextQuestion();
        interactor.nextQuestion(input);
    }
}