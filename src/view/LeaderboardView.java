package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.start_sp_quiz.SPQuizController;
import interface_adapter.start_sp_quiz.SPQuizState;
import interface_adapter.start_sp_quiz.SPQuizViewModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

public class LeaderboardView extends JPanel {
    public final String viewName;
    private final LeaderboardViewModel leaderboardViewModel;
    private final ViewManagerModel viewManagerModel;
    private final JButton back;

    public LeaderboardView(LeaderboardViewModel leaderboardViewModel, ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        this.leaderboardViewModel = leaderboardViewModel;
        this.viewName = this.leaderboardViewModel.getViewName();
        JLabel title = new JLabel(LeaderboardViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        Object[][] data = {
                {"Player1", 100},
                {"Player2", 90},
                {"Player3", 80},
        };

        String[] columnNames = {"Player", "Score"};

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable leaderboardTable = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(leaderboardTable);

        JPanel buttons = new JPanel();
        back = new JButton("Back");
        buttons.add(back);

        back.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(back)) {
                            viewManagerModel.setActiveView("MainMenuView");
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );



        this.setSize(400,400);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(scrollPane);
        this.add(buttons);
    }
}
