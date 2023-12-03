package view;

import interface_adapter.leaderboard.LeaderboardState;
import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.start_sp_quiz.SPQuizController;
import interface_adapter.start_sp_quiz.SPQuizState;
import interface_adapter.start_sp_quiz.SPQuizViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainMenuView extends JPanel implements PropertyChangeListener {
    public final String viewName = "MainMenuView";
    private final SPQuizViewModel spQuizViewModel;
    private final SPQuizController spQuizController;
    private final LeaderboardViewModel leaderboardViewModel;
    private final LeaderboardController leaderboardController;
    private final ProfileViewModel profileViewModel;
    private final ProfileController profileController;
    private final JButton spQuiz;
    private final JButton leaderboard;
    private final JButton profile;

    public MainMenuView(SPQuizController spQuizController, SPQuizViewModel spQuizViewModel,
                        LeaderboardController leaderboardController, LeaderboardViewModel leaderboardViewModel,
                        ProfileViewModel profileViewModel, ProfileController profileController) {
        this.spQuizController = spQuizController;
        this.spQuizViewModel = spQuizViewModel;
        this.profileController = profileController;
        this.profileViewModel = profileViewModel;
        spQuizViewModel.addPropertyChangeListener(this);
        this.leaderboardController = leaderboardController;
        this.leaderboardViewModel = leaderboardViewModel;
        leaderboardViewModel.addPropertyChangeListener(this);
        profileViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Main Menu");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));

        JPanel buttons = new JPanel();
        spQuiz = new JButton(SPQuizViewModel.SPQUIZ_BUTTON_LABEL);
        buttons.add(spQuiz);
        profile = new JButton("Local Profile Statistics");
        buttons.add(profile);
        leaderboard = new JButton(LeaderboardViewModel.LEADERBOARD_BUTTON_LABEL);
        buttons.add(leaderboard);

        spQuiz.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(spQuiz)) {
                            spQuizController.execute();
                        }
                    }
                }
        );

        profile.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(profile)) {
                            profileController.execute();
                        }
                    }
                }

        );

        leaderboard.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(leaderboard)) {
                            leaderboardController.execute();
                        }
                    }
                }
        );

        this.setSize(400,400);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("sp quiz state")) {
            SPQuizState state = (SPQuizState) evt.getNewValue();
            if (state.getSPQuiz() == null) {
                JOptionPane.showMessageDialog(this, "Error: Failed to create quiz.");
            }
        } else if (evt.getPropertyName().equals("leaderboard state")) {
            LeaderboardState state = (LeaderboardState) evt.getNewValue();
            if (state.getError() != null) {
                JOptionPane.showMessageDialog(this, "Error: Failed to access database.");
            }
        }
    }
}
