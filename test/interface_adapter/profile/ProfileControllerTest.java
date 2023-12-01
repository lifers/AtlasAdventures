package interface_adapter.profile;

import entity.Question;
import entity.Quiz;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import use_case.answer_question.*;
import use_case.profile.ProfileInputBoundary;
import use_case.profile.ProfileInputData;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

interface ProfileInputBoundaryTest extends ProfileInputBoundary {
    @Override
    default void execute(ProfileInputData inputData){ fail("wrong function to call");}
}

class AnswerQuestionControllerTest {
    @Test
    void execute(){
        var interactor = new ProfileInputBoundaryTest() {
            @Override
            public void execute(ProfileInputData inputData) {
                //success
            }
        };
        var controller = new ProfileController(interactor);
        controller.execute();
    }
}