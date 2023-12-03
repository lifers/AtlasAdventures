package view;

import entity.Profile;
import interface_adapter.ViewManagerModel;
import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.leaderboard.LeaderboardState;
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
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * View for the Leaderboard page.
 */
public class LeaderboardView extends JPanel implements PropertyChangeListener {
    public final String viewName;
    private final LeaderboardViewModel leaderboardViewModel;
    private final ViewManagerModel viewManagerModel;
    private DefaultTableModel table;
    private final JButton back;

    public LeaderboardView(LeaderboardViewModel leaderboardViewModel, ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        this.leaderboardViewModel = leaderboardViewModel;
        leaderboardViewModel.addPropertyChangeListener(this);

        this.viewName = this.leaderboardViewModel.getViewName();
        JLabel title = new JLabel(LeaderboardViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        ArrayList<Profile> leaderboardList = leaderboardViewModel.getLeaderboardState().getLeaderboard();

        Object[][] leaderboardEntries = new Object[3][10];

        for (int i = 0; i < leaderboardList.size(); i++) {
            Profile p = leaderboardList.get(i);
            Object[] leaderboardEntry = {"Player" + p.getUid(), p.getAverage_score(), p.getGames_played()};
            leaderboardEntries[i] = leaderboardEntry;
        }

        String[] columnNames = {"Player", "Score", "Games Played"};

        this.table = new DefaultTableModel(leaderboardEntries, columnNames);
        JTable leaderboardTable = new JTable(table);
        leaderboardTable.setEnabled(false);
        leaderboardTable.setCellSelectionEnabled(false);
        leaderboardTable.setRowSelectionAllowed(false);
        leaderboardTable.setColumnSelectionAllowed(false);

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
    /**
     * When the leaderboard state changes, update the leaderboard.
     *
     * @param  evt  the property change event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("leaderboard state")) {
            LeaderboardState state = (LeaderboardState) evt.getNewValue();
            if (state.getError() == null) {
                updateTableModel(state.getLeaderboard());
            }
        }
    }
    /**
     * Helper method for updating the leaderboard with updated data from the database.
     *
     * @param  newLeaderboard  a sorted ArrayList of profiles to be displayed on the leaderboard.
     */
    private void updateTableModel(ArrayList<Profile> newLeaderboard) {
        // Clear existing data
        table.setRowCount(0);

        // Populate the table with new data
        for (int i = 0; i < newLeaderboard.size(); i++) {
            Profile p = newLeaderboard.get(i);
            Object[] leaderboardEntry = {"Player" + p.getUid(), p.getAverage_score(), p.getGames_played()};
            table.addRow(leaderboardEntry);
        }
    }
}

