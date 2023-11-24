package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.leaderboard.LeaderboardViewModel;

import javax.swing.*;
import java.awt.*;

public class LeaderboardView extends JPanel {
    public final String viewName = "LeaderboardView";
    private final LeaderboardViewModel leaderboardViewModel;
    private final ViewManagerModel viewManagerModel;
    private final JButton back;

    public LeaderboardView(LeaderboardViewModel leaderboardViewModel, ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        this.leaderboardViewModel = leaderboardViewModel;
        JLabel title = new JLabel(LeaderboardViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        JPanel buttons = new JPanel();
        back = new JButton("Back to Main");
        buttons.add(back);
    }
}
