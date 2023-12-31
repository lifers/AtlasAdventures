package interface_adapter.answer_question;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class QuizEndedViewModel extends ViewModel {
    public static final String BACK_BUTTON_LABEL = "Go back to Main Menu";
    public static final String TOP_TEXT_LABEL = "Your final score is";
    private AnswerQuestionState state;

    public QuizEndedViewModel() {
        super("QuizEndedView");
    }

    public AnswerQuestionState getState() {
        return state;
    }

    public void setState(AnswerQuestionState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     *
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("quizEndedState", null, this.state);
    }

    /**
     * @param listener
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
