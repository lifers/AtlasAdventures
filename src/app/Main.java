package app;

import com.formdev.flatlaf.FlatDarkLaf;
import data_access.GeoInfoDataAccessObject;
import entity.Question;
import entity.Quiz;
import interface_adapter.answer_question.AnswerQuestionState;
import interface_adapter.answer_question.AnswerQuestionViewModel;
import data_access.FileUserDataAccessObject;
import data_access.MongoDBDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.answer_question.QuizEndedViewModel;
import interface_adapter.start_sp_quiz.SPQuizViewModel;
import view.*;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import use_case.profile.ProfileDataAccessInterface;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        FlatDarkLaf.setup();
        JFrame application = new JFrame("AtlasAdventures");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setSize(600, 400);

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
        AnswerQuestionViewModel answerQuestionViewModel = new AnswerQuestionViewModel();
        QuizEndedViewModel quizEndedViewModel = new QuizEndedViewModel();
        ProfileViewModel profileViewModel = new ProfileViewModel();
        LeaderboardViewModel leaderboardViewModel = new LeaderboardViewModel();

        // TODO: make an actual quiz factory and delete this
        var questionState = new AnswerQuestionState(new Quiz(
                List.of(
                        new Question(new Coordinate(52, -1), "Where is England?"),
                        new Question(new Coordinate(56, -4), "Where is Scotland?"),
                        new Question(new Coordinate(43.667, -79.395),
                                     "Can you show me where is the legendary Northrop Frye McDonald's please?")
                )
        ));
        answerQuestionViewModel.setState(questionState);

        ProfileDataAccessInterface profileDAO = null;
        try{
            profileDAO = new FileUserDataAccessObject("./profile.csv");
        }
        catch(IOException e) {
        }

        MongoDBDataAccessObject leaderboardDAO = new MongoDBDataAccessObject(
                "mongodb://localhost:27017",
                "atlas-adventures-leaderbaord",
                "leaderboard");

        GeoInfoDataAccessObject spQuizDAO = new GeoInfoDataAccessObject();

        AnswerQuestionViewPair questionViewPair = QuestionUseCaseFactory.create(views, viewManagerModel, answerQuestionViewModel,
                                                             spQuizViewModel, quizEndedViewModel);
        views.add(questionViewPair.answerQuestionView(), AnswerQuestionView.viewName);
        views.add(questionViewPair.quizEndedView(), QuizEndedView.viewName);

        ProfileView profileView = new ProfileView(profileViewModel, viewManagerModel);
        views.add(profileView, profileView.viewName);
        LeaderboardView leaderboardView = new LeaderboardView(leaderboardViewModel, viewManagerModel);
        views.add(leaderboardView, leaderboardView.viewName);
        MainMenuView mainMenuView = MainMenuFactory.create(
                viewManagerModel,
                spQuizViewModel,
                spQuizDAO,
                answerQuestionViewModel,
                profileViewModel,
                profileDAO,
                leaderboardViewModel,
                leaderboardDAO);
        views.add(mainMenuView, mainMenuView.viewName);

        viewManagerModel.setActiveView(mainMenuView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}