package view;

import com.formdev.flatlaf.ui.FlatUIUtils;
import interface_adapter.answer_question.AnswerQuestionController;
import interface_adapter.answer_question.AnswerQuestionState;
import interface_adapter.answer_question.QuizEndedViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

public class QuizEndedView extends JPanel implements PropertyChangeListener {
    public static final String viewName = "QuizEndedView";
    private final AnswerQuestionController questionController;
    private final QuizEndedViewModel quizEndedViewModel;
    private final JLabel scoreText = createScoreText();

    public QuizEndedView(AnswerQuestionController questionController, QuizEndedViewModel quizEndedViewModel) {
        this.questionController = questionController;
        this.quizEndedViewModel = quizEndedViewModel;
        this.quizEndedViewModel.addPropertyChangeListener(this);

        this.setLayout(new GridBagLayout());

        var backButton = new JButton(QuizEndedViewModel.BACK_BUTTON_LABEL);
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        backButton.setFont(FlatUIUtils.nonUIResource(UIManager.getFont("h3.font")));
        backButton.addActionListener(e -> this.questionController.returnMainMenu());

        var topText = new JLabel(QuizEndedViewModel.TOP_TEXT_LABEL);
        topText.setAlignmentX(CENTER_ALIGNMENT);
        topText.setFont(FlatUIUtils.nonUIResource(UIManager.getFont("h3.font")));

        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setAlignmentY(CENTER_ALIGNMENT);

        panel.add(topText);
        panel.add(this.scoreText);
        panel.add(backButton);

        var gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(panel, gbc);
    }

    private JLabel createScoreText() {
        var label = new JLabel("0");
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setFont(new Font(label.getFont().getName(), Font.BOLD, 72));
        return label;
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Im here");
        if (Objects.requireNonNull(evt.getNewValue()) instanceof AnswerQuestionState state) {
            this.scoreText.setText(String.format("%.2f", state.getTotalScore()));
        } else {
            throw new IllegalStateException("Unexpected value: " + evt.getNewValue());
        }
    }
}
