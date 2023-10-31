package interface_adapter.question;

import use_case.question.QuestionInputBoundary;
import use_case.question.QuestionInputData;

public class QuestionController {
    final QuestionInputBoundary questionUseCaseInteractor;
    public QuestionController(QuestionInputBoundary questionUseCaseInteractor) {
        this.questionUseCaseInteractor = questionUseCaseInteractor;
    }


    public void execute() {
        QuestionInputData loginInputData = new QuestionInputData();

        questionUseCaseInteractor.execute(loginInputData);
    }}
