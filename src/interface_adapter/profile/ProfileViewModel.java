package interface_adapter.profile;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ProfileViewModel extends ViewModel {
    public static final String BACK_BUTTON_LABEL = "Back to Main Menu";

    private String displayText;

    public ProfileViewModel() {
        super("ProfileView");
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Fires a property change event.
     **/
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("display_text", null, this.displayText);
    }

    /**
     * Adds a property change listener to the object.
     *
     * @param listener  the property change listener to be added
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Sets the display text for the object.
     *
     * @param  displayText  the new display text
     */
    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    /**
     * Retrieves the display text for the Java function.
     *
     * @return the display text as a String
     */
    public String getDisplayText() {
        return displayText;
    }
}
