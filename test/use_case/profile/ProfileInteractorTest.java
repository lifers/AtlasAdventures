package use_case.profile;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

interface ProfileOutputBoundaryTest extends ProfileOutputBoundary{
    @Override
    default void prepareSuccessView(ProfileOutputData outputData){ fail(); }
}
interface ProfileDataAccessInterfaceTest extends ProfileDataAccessInterface{
    @Override
    default void setGamesPlayed(int gamesPlayed){fail();}

    @Override
    default void setAverageScore(Double score){fail();}

    @Override
    default int getGamesPlayed(){
        fail();
        return 0;
    }

    @Override
    default Double getAverageScore(){
        fail();
        return 0.0;
    }

    @Override
    default void update() throws IOException{}

    @Override
    default void save() {fail();}

    @Override
    default int getUid() {fail(); return 0;}

    @Override
    default void setUid(int newUid) {fail();}

}
class ProfileInteractorTest {

    /**
     * This method tests that the ProfileInteractor Creates the proper OutputData
     */
    @Test
    void createsOutputData() {
        ProfileOutputBoundaryTest presenter = new ProfileOutputBoundaryTest() {
            @Override
            public void prepareSuccessView(ProfileOutputData outputData) {
                assertEquals(0.0, outputData.getAverageScore());
                assertEquals(0, outputData.getAverageScore());
            }
        }; // presenter
        ProfileDataAccessInterfaceTest dataAccess = new ProfileDataAccessInterfaceTest() {
            @Override
            public void update() throws IOException {

            }
            @Override
            public Double getAverageScore() {
                return 0.0;
            }
            @Override
            public int getGamesPlayed() {
                return 0;
            }

        };
        ProfileInteractor interactor = new ProfileInteractor(presenter, dataAccess);
        interactor.execute(new ProfileInputData());
    }
}