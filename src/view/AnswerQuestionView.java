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
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AnswerQuestionView extends JPanel implements ActionListener, PropertyChangeListener, JMapViewerEventListener {
    public static final String viewName = "AnswerQuestionView";
    private final AnswerQuestionController questionController;
    private final AnswerQuestionViewModel questionViewModel;
    private final JMapViewerTree treeMap = createTreeMap();
    private final JLabel totalScore = createTotalScore();
    private final JLabel questionText = createQuestionText();
    private final JButton submitButton = this.createSubmitButton();
    private final JButton nextButton = this.createNextButton();
    private final MouseAdapter mapClicker = this.createMapClicker();
    private Coordinate lastClick = null;

    public AnswerQuestionView(JPanel parent, AnswerQuestionController questionController, AnswerQuestionViewModel questionViewModel) {
        this.questionController = questionController;
        this.questionViewModel = questionViewModel;
        this.questionViewModel.addPropertyChangeListener(this);

        this.setSize(600, 400);
        this.setLayout(new BorderLayout());
        this.add(treeMap, BorderLayout.EAST);

        var questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.PAGE_AXIS));
        questionPanel.setPreferredSize(new Dimension(200, 400));
        preventExcessiveWidthShrink(parent, questionPanel, this.treeMap, 200);

        this.add(questionPanel, BorderLayout.WEST);

        questionPanel.add(this.questionText);
        questionPanel.add(Box.createVerticalGlue());
        questionPanel.add(this.submitButton);
        questionPanel.add(this.nextButton);
        questionPanel.add(this.totalScore);
    }

    private JMapViewer map() {
        return treeMap.getViewer();
    }

    private static void preventExcessiveWidthShrink(Component parent, Component leftThird, Component rightThird,
                                                    int leftMinWidth) {
        parent.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                var parentWidth = parent.getWidth();
                var leftWidth = Math.max(leftMinWidth, parentWidth / 3);
                var rightWidth = parentWidth - leftWidth;
                leftThird.setPreferredSize(new Dimension(leftWidth, leftThird.getHeight()));
                rightThird.setPreferredSize(new Dimension(rightWidth, rightThird.getHeight()));
                parent.revalidate();
                parent.repaint();
            }
        });
    }

    private JMapViewerTree createTreeMap() {
        var map = new JMapViewerTree("AnswerQuestionMap");
        map.getViewer().addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                var p = e.getPoint();
                var cursorHand = map.getViewer().getAttribution().handleAttributionCursor(p);
                if (cursorHand) {
                    map.getViewer().setCursor(new Cursor(Cursor.HAND_CURSOR));
                } else {
                    map.getViewer().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
                map.getViewer().setToolTipText(map.getViewer().getPosition(p).toString());
            }
        });
        map.getViewer().addJMVListener(this);
        return map;
    }

    private static JLabel createTotalScore() {
        var label = new JLabel("Total score: 0");
        label.setAlignmentX(CENTER_ALIGNMENT);
        return label;
    }

    private static JLabel createQuestionText() {
        var label = new JLabel("Click Start!");
        label.setFont(FlatUIUtils.nonUIResource(UIManager.getFont("h2.font")));
        label.setAlignmentX(CENTER_ALIGNMENT);
        return label;
    }

    private JButton createSubmitButton() {
        var button = new JButton(AnswerQuestionViewModel.SUBMIT_BUTTON_LABEL);
        button.setFont(FlatUIUtils.nonUIResource(UIManager.getFont("h2.font")));
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setEnabled(false);
        button.addActionListener(e -> {
            this.questionController.answer(this.lastClick);
            this.nextButton.setEnabled(true);
            button.setEnabled(false);
            this.map().removeMouseListener(this.mapClicker);
        });
        return button;
    }

    private JButton createNextButton() {
        var button = new JButton(AnswerQuestionViewModel.START_BUTTON_LABEL);
        button.setFont(FlatUIUtils.nonUIResource(UIManager.getFont("h2.font")));
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.addActionListener(e -> this.questionController.nextQuestion());
        return button;
    }

    private MouseAdapter createMapClicker() {
        return new MouseAdapter() {
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