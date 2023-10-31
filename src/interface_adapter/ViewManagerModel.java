package interface_adapter;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewManagerModel {

    private JPanel activeView;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public JPanel getActiveView() {
        return activeView;
    }

    public void setActiveView(JPanel activeView) {
        this.activeView = activeView;
    }

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("view", null, this.activeView);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
