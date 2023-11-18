package app;

import com.formdev.flatlaf.FlatDarkLaf;
import data_access.DummyDAO;
import data_access.MapDataAccessObject;
import entity.Question;
import entity.Quiz;
import interface_adapter.answer_question.AnswerQuestionState;
import interface_adapter.answer_question.AnswerQuestionViewModel;
import interface_adapter.answer_question.QuizEndedViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.start_sp_quiz.SPQuizViewModel;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import view.AnsweredView;
import view.MainMenuView;
import view.AnswerQuestionView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        FlatDarkLaf.setup();
        JFrame application = new JFrame("AtlasAdventures");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setSize(400, 400);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();

        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.

        SPQuizViewModel spQuizViewModel = new SPQuizViewModel();
        AnswerQuestionViewModel questionViewModel = new AnswerQuestionViewModel();
        // TODO: make an actual quiz factory and delete this
        var questionState = new AnswerQuestionState(new Quiz(
                List.of(
                        new Question(new Coordinate(52, -1), "Where is England?"),
                        new Question(new Coordinate(56, -4), "Where is Scotland?")
                )
        ));
        questionViewModel.setState(questionState);

        MapDataAccessObject mapDataAccessObject = new MapDataAccessObject();
        // Dummy data access object, to be replaced with actual DAO
        DummyDAO dummyDAO = new DummyDAO();

        var answerQuestionView = QuestionUseCaseFactory.create(viewManagerModel, questionViewModel, spQuizViewModel);
        views.add(answerQuestionView, AnswerQuestionView.viewName);

        MainMenuView mainMenuView = MainMenuFactory.create(viewManagerModel, spQuizViewModel, questionViewModel,
                                                           dummyDAO);
        views.add(mainMenuView, mainMenuView.viewName);

        viewManagerModel.setActiveView(mainMenuView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}