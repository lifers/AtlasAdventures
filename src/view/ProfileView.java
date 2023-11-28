package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.profile.ProfileController;
import interface_adapter.profile.ProfileViewModel;
import interface_adapter.start_sp_quiz.SPQuizController;
import interface_adapter.start_sp_quiz.SPQuizViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ProfileView extends JPanel implements PropertyChangeListener {
    public final String viewName = "ProfileView";
    private final ProfileViewModel profileViewModel;
    private final ViewManagerModel viewManagerModel;
    private final JButton back;

    private final JLabel displayLabel = new JLabel();

    private String displayText;


    public ProfileView(ProfileViewModel profileViewModel, ViewManagerModel viewManagerModel){
        this.profileViewModel = profileViewModel;
        this.viewManagerModel = viewManagerModel;
        this.profileViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("           Profile Statistics");
        // title.setAlignmentX(CENTER_ALIGNMENT);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));


        JPanel buttons = new JPanel();
        back = new JButton(ProfileViewModel.BACK_BUTTON_LABEL);
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


        displayLabel.setText(this.displayText);
        displayLabel.setFont(new Font(displayLabel.getFont().getName(), Font.BOLD, 20));


        this.setSize(400,400);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(displayLabel);
        this.add(buttons);
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.displayText = "                 " + this.profileViewModel.getDisplayText();
        displayLabel.setText(this.displayText);

    }

}
