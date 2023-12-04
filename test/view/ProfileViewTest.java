package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.profile.ProfileViewModel;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class ProfileViewTest {

    @BeforeEach
    void setUp() {
    }
    @org.junit.jupiter.api.Test
    void propertyChange() {
        ProfileView view = new ProfileView(new ProfileViewModel(), new ViewManagerModel());
        view.propertyChange(null);
    }

}