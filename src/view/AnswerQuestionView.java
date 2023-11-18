package view;

import com.formdev.flatlaf.ui.FlatUIUtils;
import interface_adapter.answer_question.AnswerQuestionController;
import interface_adapter.answer_question.AnswerQuestionState;
import interface_adapter.answer_question.AnswerQuestionViewModel;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.JMapViewerTree;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.events.JMVCommandEvent;
import org.openstreetmap.gui.jmapviewer.interfaces.JMapViewerEventListener;

import javax.swing.*;
import javax.swing.text.ParagraphView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

public class AnswerQuestionView extends JPanel implements ActionListener, PropertyChangeListener, JMapViewerEventListener {
    public static final String viewName = "AnswerQuestionView";
    private final AnswerQuestionController questionController;
    private final AnswerQuestionViewModel questionViewModel;
    private final JMapViewerTree treeMap = new JMapViewerTree("AnswerQuestionMap");
    private final JLabel totalScore = new JLabel("0", SwingConstants.CENTER);
    private final JLabel questionText = new JLabel("Click Start!");
    private final JButton submitButton = new JButton(AnswerQuestionViewModel.SUBMIT_BUTTON_LABEL);
    private final JButton nextButton = new JButton(AnswerQuestionViewModel.START_BUTTON_LABEL);
    private final MouseAdapter mapClicker = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            var p = e.getPoint();
            var cursorHand = map().getAttribution().handleAttributionCursor(p);
            if (cursorHand) {
                map().setCursor(new Cursor(Cursor.HAND_CURSOR));
            } else {
                map().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
            map().removeAllMapMarkers();
            lastClick = (Coordinate) map().getPosition(p);
            submitButton.setEnabled(true);
            map().addMapMarker(new MapMarkerDot(lastClick));
        }
    };
    private Coordinate lastClick = null;

    public AnswerQuestionView(AnswerQuestionController questionController, AnswerQuestionViewModel questionViewModel) {
        this.questionController = questionController;
        this.questionViewModel = questionViewModel;
        this.questionViewModel.addPropertyChangeListener(this);

        this.map().addJMVListener(this);
        this.setSize(600, 400);
        this.setLayout(new BorderLayout());
        this.add(treeMap, BorderLayout.EAST);

        var questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.PAGE_AXIS));
        this.add(questionPanel, BorderLayout.WEST);

        this.questionText.setFont(FlatUIUtils.nonUIResource(UIManager.getFont("h2.font")));
        questionPanel.add(this.questionText);
        questionPanel.add(Box.createVerticalGlue());

        questionPanel.add(this.submitButton);
        questionPanel.add(this.nextButton);
        questionPanel.add(this.totalScore);

        this.submitButton.setEnabled(false);
        this.submitButton.addActionListener(e -> {
            this.questionController.answer(this.lastClick);
            this.nextButton.setEnabled(true);
            this.submitButton.setEnabled(false);
            this.map().removeMouseListener(this.mapClicker);
        });

        this.nextButton.addActionListener(e -> this.questionController.nextQuestion());

        this.map().addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                var p = e.getPoint();
                var cursorHand = map().getAttribution().handleAttributionCursor(p);
                if (cursorHand) {
                    map().setCursor(new Cursor(Cursor.HAND_CURSOR));
                } else {
                    map().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
                map().setToolTipText(map().getPosition(p).toString());
            }
        });
    }

    private JMapViewer map() {
        return treeMap.getViewer();
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getNewValue()) {
            case AnswerQuestionState state when state.isAnswering() -> {
                this.questionText.setText(state.getQuiz().getCurrQuestion().getPrompt());
                this.map().removeAllMapMarkers();
                this.map().addMouseListener(this.mapClicker);
                this.nextButton.setEnabled(false);
                this.nextButton.setText(AnswerQuestionViewModel.NEXT_BUTTON_LABEL);
            }
            case AnswerQuestionState state when !state.isAnswering() -> {
                this.totalScore.setText("Total score: " + state.getTotalScore());
                this.questionText.setText("Press Next");
            }
            default -> throw new IllegalStateException("Unexpected value: " + evt.getNewValue());
        }
    }

    /**
     * @param jmvCommandEvent
     */
    @Override
    public void processCommand(JMVCommandEvent jmvCommandEvent) {

    }
}