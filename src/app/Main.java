package app;

import com.formdev.flatlaf.FlatDarkLaf;
import data_access.GeoInfoDataAccessObject;
import interface_adapter.ViewManagerModel;
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
import view.AnswerQuestionView;
import view.MainMenuView;
import view.QuizEndedView;
import data_access.FileUserDataAccessObject;
import interface_adapter.profile.ProfileViewModel;
import use_case.profile.ProfileDataAccessInterface;
import view.ProfileView;
import view.ViewManager;
import view.*;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import use_case.profile.ProfileDataAccessInterface;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        FlatDarkLaf.setup();
        JFrame application = new JFrame("AtlasAdventures");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        application.setSize(600, 400);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();

        new ViewManager(views, cardLayout, viewManagerModel);

        // View Models
        SPQuizViewModel spQuizViewModel = new SPQuizViewModel();
        AnswerQuestionViewModel answerQuestionViewModel = new AnswerQuestionViewModel();
        QuizEndedViewModel quizEndedViewModel = new QuizEndedViewModel();
        ProfileViewModel profileViewModel = new ProfileViewModel();
        LeaderboardViewModel leaderboardViewModel = new LeaderboardViewModel();

        // DAOs
        ProfileDataAccessInterface profileDAO = null;
        try {
            profileDAO = new FileUserDataAccessObject("./profile.csv");
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }

        String connectionString = "mongodb+srv://AtlasAdventuresPlayer:pumpkinpatch240@cluster0.xhpezmy.mongodb.net/?retryWrites=true&w=majority";
        String databaseName = "AtlasAdventures";
        String collectionName = "leaderboard";
        MongoDBDataAccessObject leaderboardDAO = null;
        try {
            leaderboardDAO = new MongoDBDataAccessObject(connectionString, databaseName, collectionName);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        GeoInfoDataAccessObject spQuizDAO = new GeoInfoDataAccessObject();

        // Views
        AnswerQuestionViewPair questionViewPair = QuestionUseCaseFactory.create(viewManagerModel, answerQuestionViewModel,
                                                             spQuizViewModel, quizEndedViewModel, profileDAO);
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
