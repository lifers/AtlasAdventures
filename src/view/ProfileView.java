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

        this.setLayout(new GridBagLayout());

        JLabel title = new JLabel("Profile Statistics");
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));


        back = new JButton(ProfileViewModel.BACK_BUTTON_LABEL);

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
        displayLabel.setAlignmentX(CENTER_ALIGNMENT);

        var gbTitle = new GridBagConstraints();
        gbTitle.gridy = 0;

        var gbDisplay = new GridBagConstraints();
        gbDisplay.gridy = 1;

        var gbBack = new GridBagConstraints();
        gbBack.gridy = 2;
        gbBack.insets = new Insets(10, 0, 0, 0);

        this.add(title, gbTitle);
        this.add(displayLabel, gbDisplay);
        this.add(back, gbBack);
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.displayText = this.profileViewModel.getDisplayText();
        displayLabel.setText(this.displayText);

    }

}
