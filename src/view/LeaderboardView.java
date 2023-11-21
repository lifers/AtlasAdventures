package view;

import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.leaderboard.LeaderboardViewModel;

import javax.swing.*;
import java.awt.*;

public class LeaderboardView extends JPanel {
    private final LeaderboardViewModel leaderboardViewModel;
    private final JButton backToMain;
    public LeaderboardView(LeaderboardViewModel leaderboardViewModel) {
        this.leaderboardViewModel = leaderboardViewModel;
        JLabel title = new JLabel(LeaderboardViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        JPanel buttons = new JPanel();
        backToMain = new JButton("Back to Main");
        buttons.add(backToMain);
    }
}
