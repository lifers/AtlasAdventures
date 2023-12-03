package use_case.start_sp_quiz;

import data_access.GeoInfoDataAccessObject;
import entity.Question;
import entity.Quiz;
import interface_adapter.ViewManagerModel;
import interface_adapter.answer_question.AnswerQuestionViewModel;
import interface_adapter.start_sp_quiz.SPQuizPresenter;
import interface_adapter.start_sp_quiz.SPQuizViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SPQuizInteractorTest {

    private SPQuizInteractor spQuizInteractor;
    private AnswerQuestionViewModel answerQuestionViewModel;
    @BeforeEach
    void init() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SPQuizViewModel spQuizViewModel = new SPQuizViewModel();
        this.answerQuestionViewModel = new AnswerQuestionViewModel();
        SPQuizOutputBoundary spQuizPresenter = new SPQuizPresenter(viewManagerModel, spQuizViewModel, answerQuestionViewModel);
        SPQuizDataAccessInterface spQuizDataAccessObject = new GeoInfoDataAccessObject();
        this.spQuizInteractor = new SPQuizInteractor(spQuizDataAccessObject, spQuizPresenter);
    }

    @Test
    void execute() {
        spQuizInteractor.execute();
        Quiz quiz = answerQuestionViewModel.getState().getQuiz();
        List<Question> questions = new ArrayList<>();

        try {
            for (int i = 0; i < 11; i++) {
                questions.add(quiz.getCurrQuestion());
                quiz.nextQuestion();
            }
        } catch(Exception IndexOutOfBoundsException) {
            assertEquals(10, questions.size());
        }
    }

}