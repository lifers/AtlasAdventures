package interface_adapter.question;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class QuestionViewModel extends ViewModel {

    public static final String SUBMIT_BUTTON_LABEL = "Submit";
    public static final String NEXT_BUTTON_LABEL = "Next";

    private QuestionState state = new QuestionState();

    public QuestionViewModel() {
        super("AnswerQuestionView");
    }

    public QuestionState getState() {
        return state;
    }

    public void setState(QuestionState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     *
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * @param listener
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
