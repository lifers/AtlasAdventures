package view;

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
    private final JButton spQuiz;
    private final JButton mpQuiz;
    private final JButton profile;

    public MainMenuView(SPQuizController spQuizController, SPQuizViewModel spQuizViewModel) {
        this.spQuizController = spQuizController;
        this.spQuizViewModel = spQuizViewModel;
        spQuizViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Main Menu");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        spQuiz = new JButton(SPQuizViewModel.SPQUIZ_BUTTON_LABEL);
        buttons.add(spQuiz);
        mpQuiz = new JButton("Unimplemented MPQuiz");
        buttons.add(mpQuiz);
        profile = new JButton("Unimplemented Profile");
        buttons.add(profile);

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
        }
    }
}
