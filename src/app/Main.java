package app;

import com.formdev.flatlaf.FlatDarkLaf;
import data_access.DummyDAO;
import data_access.FileUserDataAccessObject;
import data_access.MapDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.start_sp_quiz.SPQuizViewModel;
import interface_adapter.question.QuestionViewModel;
import use_case.profile.ProfileDataAccessInterface;
import view.MainMenuView;
import view.ProfileView;
import view.QuestionView;
import view.ViewManager;

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
        QuestionViewModel questionViewModel = new QuestionViewModel();
        ProfileViewModel profileViewModel = new ProfileViewModel();

        MapDataAccessObject mapDataAccessObject = new MapDataAccessObject();
        ProfileDataAccessInterface DAO = null;
        try{
            DAO = new FileUserDataAccessObject("./profile.csv");
        }
        catch(IOException e) {
        }

        // Dummy data access object, to be replaced with actual DAO
        DummyDAO dummyDAO = new DummyDAO();

        QuestionView questionView = QuestionUseCaseFactory.create(viewManagerModel, questionViewModel, mapDataAccessObject);
        views.add(questionView, questionView.viewName);

        ProfileView profileView = new ProfileView(profileViewModel, viewManagerModel);
        views.add(profileView, profileView.viewName);

        MainMenuView mainMenuView = MainMenuFactory.create(viewManagerModel, spQuizViewModel, questionViewModel, profileViewModel, DAO, dummyDAO);
        views.add(mainMenuView, mainMenuView.viewName);

        viewManagerModel.setActiveView(mainMenuView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}