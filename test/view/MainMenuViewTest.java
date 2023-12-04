package view;

import app.Main;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        assert (spQuizButton.getText().equals("Single Player"));
        JButton profileButton = getButton("Profile");
        assert (profileButton.getText().equals("Local Profile Statistics"));
        JButton leaderboardButton = getButton("Leaderboard");
        assert (leaderboardButton.getText().equals("Leaderboard"));
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
}
