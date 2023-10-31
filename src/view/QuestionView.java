package view;

import interface_adapter.question.QuestionController;
import interface_adapter.question.QuestionViewModel;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.JMapViewerTree;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.events.JMVCommandEvent;
import org.openstreetmap.gui.jmapviewer.interfaces.JMapViewerEventListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class QuestionView extends JPanel implements ActionListener, PropertyChangeListener, JMapViewerEventListener {
    private final QuestionController questionController;
    private final QuestionViewModel questionViewModel;
    private final JMapViewerTree treeMap;
    private Coordinate lastClick = null;

    public QuestionView(QuestionController questionController, QuestionViewModel questionViewModel) {
        this.treeMap = new JMapViewerTree("Map");
        this.questionController = questionController;
        this.questionViewModel = questionViewModel;
        this.questionViewModel.addPropertyChangeListener(this);

        this.map().addJMVListener(this);
        this.setSize(400, 400);
        this.setLayout(new BorderLayout());
        this.add(treeMap, BorderLayout.CENTER);

        var questionPanel = new JPanel();
        this.add(questionPanel, BorderLayout.WEST);

        var questionText = new JLabel("Click Submit to see where is the last chosen location.");
        questionPanel.add(questionText, BorderLayout.NORTH);

        var submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> System.out.println(this.lastClick));
        questionPanel.add(submitButton, BorderLayout.PAGE_END);

        this.map().addMouseListener(new MouseAdapter() {
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
                map().addMapMarker(new MapMarkerDot(lastClick));
            }
        });

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

    }

    /**
     * @param jmvCommandEvent
     */
    @Override
    public void processCommand(JMVCommandEvent jmvCommandEvent) {

    }
}