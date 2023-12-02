package interface_adapter.answer_question;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AnswerQuestionViewModel extends ViewModel {
    public static final String SUBMIT_BUTTON_LABEL = "Submit";
    public static final String NEXT_BUTTON_LABEL = "Next";
    public static final String START_BUTTON_LABEL = "Start";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private AnswerQuestionState state;

    public AnswerQuestionViewModel() {
        super("AnswerQuestionView");
    }

    public AnswerQuestionState getState() {
        return state;
    }

    public void setState(AnswerQuestionState state) {
        this.state = state;
    }

    private String makePropertyName() {
        if (this.state.isAnswering()) {
            return "answering";
        } else {
            return "not answering";
        }
    }

    /**
     *
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange(this.makePropertyName(), null, this.state);
    }

    /**
     * @param listener
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
