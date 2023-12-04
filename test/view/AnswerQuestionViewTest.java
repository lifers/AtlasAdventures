package view;

import entity.Quiz;
import interface_adapter.answer_question.AnswerQuestionController;
import interface_adapter.answer_question.AnswerQuestionState;
import interface_adapter.answer_question.AnswerQuestionViewModel;
import use_case.answer_question.AnswerQuestionInputBoundary;
import use_case.answer_question.AnswerQuestionInputData;
import use_case.answer_question.NextQuestionInputData;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AnswerQuestionViewTest {

    private AnswerQuestionInputBoundary interactor = new AnswerQuestionInputBoundary() {
        @Override
        public void answer(AnswerQuestionInputData inputData) {
            // Success
        }

        @Override
        public void nextQuestion(NextQuestionInputData inputData) {
            // Success
        }

        @Override
        public void returnToMainMenu() {
            // Success
        }
    };
    AnswerQuestionViewModel answerQuestionViewModel = new AnswerQuestionViewModel();

    @org.junit.jupiter.api.Test
    void propertyChange() {
        answerQuestionViewModel.setState(new AnswerQuestionState(new Quiz(new ArrayList<>())));
        AnswerQuestionView view = new AnswerQuestionView(new AnswerQuestionController(interactor, answerQuestionViewModel), answerQuestionViewModel);
        view.propertyChange(new PropertyChangeEvent(view, "not answering", new AnswerQuestionState(new Quiz(new ArrayList<>())), new AnswerQuestionState(new Quiz(new ArrayList<>()))));
    }
}