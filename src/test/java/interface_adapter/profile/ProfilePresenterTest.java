package interface_adapter.profile;

import interface_adapter.ViewManagerModel;
import interface_adapter.answer_question.AnswerQuestionState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.profile.ProfileOutputData;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;

public class ProfilePresenterTest {
    private ProfileViewModel profileViewModel;

    private ViewManagerModel viewManagerModel;

    @BeforeEach
    void init() {
        this.profileViewModel = new ProfileViewModel();
        this.viewManagerModel = new ViewManagerModel();
    }

    @Test
    void prepareSuccessView() {
        ProfileOutputData outputData = new ProfileOutputData(500, 10);
        ProfilePresenter presenter = new ProfilePresenter(this.profileViewModel, this.viewManagerModel);

        var interceptor = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("display_text")) {
                    assertEquals(profileViewModel, evt.getSource());
                    assertEquals(profileViewModel.getDisplayText(), evt.getNewValue());
                } else {
                    fail("wrong event passed");
                }
            }
        };
        this.profileViewModel.addPropertyChangeListener(interceptor);
        presenter.prepareSuccessView(outputData);
    }
}
