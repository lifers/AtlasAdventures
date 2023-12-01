package interface_adapter.answer_question;

import entity.Profile;
import entity.Question;
import entity.Quiz;
import interface_adapter.ViewManagerModel;
import interface_adapter.start_sp_quiz.SPQuizViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import use_case.answer_question.AnswerQuestionOutputData;
import use_case.profile.ProfileDataAccessInterface;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

interface ProfileDataAccessInterfaceTest extends ProfileDataAccessInterface {
    @Override
    default void update() {
        fail("wrong function to call");
    }

    @Override
    default void save() {
        fail("wrong function to call");
    }

    @Override
    default void setGamesPlayed(int gamesPlayed) {
        fail("wrong function to call");
    }

    @Override
    default int getGamesPlayed() {
        fail("wrong function to call");
        return -1;
    }

    @Override
    default void setAverageScore(Double score) {
        fail("wrong function to call");
    }

    @Override
    default Double getAverageScore() {
        fail("wrong function to call");
        return null;
    }
}

class AnswerQuestionPresenterTest {
    ViewManagerModel viewManagerModel;
    AnswerQuestionViewModel answerQuestionViewModel;
    SPQuizViewModel spQuizViewModel;
    QuizEndedViewModel quizEndedViewModel;
    AnswerQuestionState state;
    ProfileDataAccessInterfaceTest dao;

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
        this.answerQuestionViewModel.getState().getQuiz().nextQuestion();
        this.answerQuestionViewModel.getState().getQuiz().nextQuestion();
        this.answerQuestionViewModel.getState().addTotalScore(180.0);

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

        var dataAccess = new ProfileDataAccessInterfaceTest() {
            @Override
            public void update() {
                // Correct
            }

            @Override
            public void save() {
                // Correct
            }

            @Override
            public int getGamesPlayed() {
                return 5;
            }

            @Override
            public void setGamesPlayed(int gamesPlayed) {
                assertEquals(6, gamesPlayed);
            }

            @Override
            public Double getAverageScore() {
                return 120.0;
            }

            @Override
            public void setAverageScore(Double score) {
                assertEquals(130.0, score);
            }
        };

        this.quizEndedViewModel.addPropertyChangeListener(interceptor);
        this.viewManagerModel.addPropertyChangeListener(interceptor);
        var presenter = new AnswerQuestionPresenter(this.viewManagerModel, this.answerQuestionViewModel,
                                                    this.spQuizViewModel, this.quizEndedViewModel, dataAccess);
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
                                                    this.spQuizViewModel, this.quizEndedViewModel, this.dao);
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
                                                    this.spQuizViewModel, this.quizEndedViewModel, this.dao);
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
                                                    this.spQuizViewModel, this.quizEndedViewModel, this.dao);
        presenter.returnToMainMenu();
    }
}