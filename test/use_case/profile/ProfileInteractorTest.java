package use_case.profile;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

interface ProfileOutputBoundaryTest extends ProfileOutputBoundary{
    @Override
    default void prepareSuccessView(ProfileOutputData outputData){ fail(); }
}
interface ProfileDataAccessInterfaceTest extends ProfileDataAccessInterface{
    default void setGamesPlayed(int gamesPlayed){fail();}

    default void setAverageScore(Double score){fail();}

    default int getGamesPlayed(){
        fail();
        return 0;
    }

    default Double getAverageScore(){
        fail();
        return 0.0;
    }

    default void update() throws IOException{}
}
class ProfileInteractorTest {

    /**
     * This method tests that the ProfileInteractor class calls the update
     * method of the callsDataAccess method in the ProfileInteractor class.
     */
    @Test
    void callsDataAccess() {

    }
    /**
     * This method tests that the ProfileInteractor Creates the proper OutputData
     */
    void createsOutputData() {
    }
}