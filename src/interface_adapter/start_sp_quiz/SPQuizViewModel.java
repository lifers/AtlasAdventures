package interface_adapter.start_sp_quiz;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SPQuizViewModel extends ViewModel {

    public static final String SPQUIZ_BUTTON_LABEL = "Single Player";

    public SPQuizViewModel() {
        super("StartSPQuiz");
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
