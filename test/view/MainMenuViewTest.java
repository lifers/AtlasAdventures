package view;

import app.Main;
import app.MainMenuFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.answer_question.AnswerQuestionViewModel;
import interface_adapter.answer_question.QuizEndedViewModel;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.start_sp_quiz.SPQuizViewModel;
import org.junit.Test;
import use_case.leaderboard.LeaderboardDataAccessInterface;
import use_case.profile.ProfileDataAccessInterface;
import use_case.start_sp_quiz.SPQuizDataAccessInterface;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MainMenuViewTest {

    private JButton getButton(String buttonText) {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app); // found the window?
        Component root = app.getComponent(0);
        Component cp = ((JRootPane) root).getContentPane();
        JPanel jp = (JPanel) cp;
        JPanel jp2 = (JPanel) jp.getComponent(0);
        MainMenuView sv = (MainMenuView) jp2.getComponent(4);
        JPanel buttons = (JPanel) sv.getComponent(1);

        if (buttonText.equals("SPQuiz")) {
            return (JButton) buttons.getComponent(0); // this should be the Leaderboard button
        } else if (buttonText.equals("Profile")) {
            return (JButton) buttons.getComponent(1); // this should be the Leaderboard button
        } else if (buttonText.equals("Leaderboard")) {
            return (JButton) buttons.getComponent(2); // this should be the Leaderboard button
        } else {
            return null;
        }
    }

    @Test
    public void allButtonsPresent() {
        Main.main(null);
        JButton spQuizButton = getButton("SPQuiz");
        assert(spQuizButton.getText().equals("Single Player"));
        JButton profileButton = getButton("Profile");
        assert(profileButton.getText().equals("Local Profile Statistics"));
        JButton leaderboardButton = getButton("Leaderboard");
        assert(leaderboardButton.getText().equals("Leaderboard"));
    }

    @Test
    public void SPQuizButtonWorks() {
        Main.main(null);
        JButton spQuizButton = getButton("SPQuiz");
        spQuizButton.doClick();
    }

    @Test
    public void ProfileButtonWorks() {
        Main.main(null);
        JButton profileButton = getButton("Profile");
        profileButton.doClick();
    }

    @Test
    public void LeaderboardButtonWorks() {
        Main.main(null);
        JButton leaderboardButton = getButton("Leaderboard");
        leaderboardButton.doClick();
    }


    // Instantiate all the view models and DAOs
//    ViewManagerModel viewManagerModel = new ViewManagerModel();
//    SPQuizViewModel spQuizViewModel = new SPQuizViewModel();
//    AnswerQuestionViewModel answerQuestionViewModel = new AnswerQuestionViewModel();
//    ProfileViewModel profileViewModel = new ProfileViewModel();
//    LeaderboardViewModel leaderboardViewModel = new LeaderboardViewModel();
//
//    SPQuizDataAccessInterface spQuizDAO = null;
//    ProfileDataAccessInterface profileDAO = null;
//    LeaderboardDataAccessInterface leaderboardDAO = null;
//
//    MainMenuView mainMenuView = MainMenuFactory.create(
//            viewManagerModel,
//            spQuizViewModel,
//            spQuizDAO,
//            answerQuestionViewModel,
//            profileViewModel,
//            profileDAO,
//            leaderboardViewModel,
//            leaderboardDAO);
//
//    JLabel title = (JLabel) mainMenuView.getComponent(0);
//    assertEquals(title.getText(), "Main Menu");
//
//    JPanel buttons = (JPanel) mainMenuView.getComponent(1);
//    JButton spButton = (JButton) buttons.getComponent(0);
//    JButton profileButton = (JButton) buttons.getComponent(1);
//    JButton leaderboardButton = (JButton) buttons.getComponent(2);
//    assertEquals(spButton.getText(), "Single Player");
//    assertEquals(profileButton.getText(), "Local Profile Statistics");
//    assertEquals(leaderboardButton.getText(), "Leaderboard");
}
