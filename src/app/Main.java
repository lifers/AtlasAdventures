package app;

import com.formdev.flatlaf.FlatDarkLaf;
import data_access.MapDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.question.QuestionViewModel;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        FlatDarkLaf.setup();

        var application = new JFrame("AtlasAdventures");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setSize(400, 400);

        var cardLayout = new CardLayout();

        var views = new JPanel(cardLayout);
        application.add(views);

        var viewManagerModel = new ViewManagerModel();

        new ViewManager(views, cardLayout, viewManagerModel);

        var questionViewModel = new QuestionViewModel();

        var mapDataAccessObject = new MapDataAccessObject();

        var questionView = QuestionUseCaseFactory.create(viewManagerModel, questionViewModel, mapDataAccessObject);
        views.add(questionView);

        viewManagerModel.setActiveView(questionView);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}