package interface_adapter.answer_question;

import entity.Question;
import entity.Quiz;
import interface_adapter.ViewManagerModel;
import interface_adapter.start_sp_quiz.SPQuizViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import use_case.answer_question.AnswerQuestionOutputData;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnswerQuestionPresenterTest {
    ViewManagerModel viewManagerModel;
    AnswerQuestionViewModel answerQuestionViewModel;
    SPQuizViewModel spQuizViewModel;
    QuizEndedViewModel quizEndedViewModel;
    AnswerQuestionState state;

    @BeforeEach
    void init() {
        this.viewManagerModel = new ViewManagerModel();
        this.answerQuestionViewModel = new AnswerQuestionViewModel();
        this.spQuizViewModel = new SPQuizViewModel();
        this.quizEndedViewModel = new QuizEndedViewModel();
        this.state = new AnswerQuestionState(new Quiz(List.of(
                new Question(new Coordinate(52, -1), "Where is England?"),
                new Question(new Coordinate(56, -4), "Where is Scotland?")
        )));
        answerQuestionViewModel.setState(this.state);
    }

    @Test
    void prepareEndQuizView() {
        // Go to the end of the quiz
        answerQuestionViewModel.getState().getQuiz().nextQuestion();
        answerQuestionViewModel.getState().getQuiz().nextQuestion();

        var interceptor = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("quizEndedState")) {
                    assertEquals(quizEndedViewModel, evt.getSource());
                    assertInstanceOf(AnswerQuestionState.class, evt.getNewValue());
                    assertEquals(state, evt.getNewValue());
                    assertEquals(state, quizEndedViewModel.getState());
                } else if (evt.getPropertyName().equals("view")) {
                    assertEquals(viewManagerModel, evt.getSource());
                    assertInstanceOf(String.class, evt.getNewValue());
                    assertEquals("QuizEndedView", evt.getNewValue());
                } else {
                    fail("wrong event passed");
                }
            }
        };

        this.quizEndedViewModel.addPropertyChangeListener(interceptor);
        this.viewManagerModel.addPropertyChangeListener(interceptor);
        var presenter = new AnswerQuestionPresenter(this.viewManagerModel, this.answerQuestionViewModel,
                                                    this.spQuizViewModel, this.quizEndedViewModel);
        presenter.prepareEndQuizView();
    }

    @Test
    void prepareNextQuestionView() {
        var interceptor = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("answerQuestionState")) {
                    assertEquals(answerQuestionViewModel, evt.getSource());
                    assertInstanceOf(AnswerQuestionState.class, evt.getNewValue());
                    assertEquals(state, evt.getNewValue());
                    assert answerQuestionViewModel.getState().isAnswering();
                } else {
                    fail("wrong event passed");
                }
            }
        };

        this.answerQuestionViewModel.addPropertyChangeListener(interceptor);
        var presenter = new AnswerQuestionPresenter(this.viewManagerModel, this.answerQuestionViewModel,
                                                    this.spQuizViewModel, this.quizEndedViewModel);
        presenter.prepareNextQuestionView();
    }

    @Test
    void prepareAnsweredView() {
        answerQuestionViewModel.getState().addTotalScore(245.99);
        var result = new AnswerQuestionOutputData(149.57);
        var interceptor = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("answerQuestionState")) {
                    assertEquals(answerQuestionViewModel, evt.getSource());
                    assertInstanceOf(AnswerQuestionState.class, evt.getNewValue());
                    assertEquals(state, evt.getNewValue());
                    assertEquals(149.57 + 245.99, answerQuestionViewModel.getState().getTotalScore());
                    assert !answerQuestionViewModel.getState().isAnswering();
                } else {
                    fail("wrong event passed");
                }
            }
        };

        this.answerQuestionViewModel.addPropertyChangeListener(interceptor);
        var presenter = new AnswerQuestionPresenter(this.viewManagerModel, this.answerQuestionViewModel,
                                                    this.spQuizViewModel, this.quizEndedViewModel);
        presenter.prepareAnsweredView(result);
    }

    @Test
    void returnToMainMenu() {
        var interceptor = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("view")) {
                    assertEquals(viewManagerModel, evt.getSource());
                    assertInstanceOf(String.class, evt.getNewValue());
                    assertEquals("MainMenuView", evt.getNewValue());
                } else {
                    fail("wrong event passed");
                }
            }
        };

        this.viewManagerModel.addPropertyChangeListener(interceptor);
        var presenter = new AnswerQuestionPresenter(this.viewManagerModel, this.answerQuestionViewModel,
                                                    this.spQuizViewModel, this.quizEndedViewModel);
        presenter.returnToMainMenu();
    }
}