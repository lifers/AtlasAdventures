package use_case.leaderboard;

import entity.Profile;
import org.junit.jupiter.api.Test;
import use_case.profile.ProfileDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class LeaderboardInteractorTest {
    @Test
    void successTest() {
        LeaderboardDataAccessInterface leaderboardDAO = new TestLeaderboardDAO();
        ProfileDataAccessInterface profileDAO = new TestProfileDAO();

        // This creates a successPresenter that tests whether the test case is as we expect.
        LeaderboardOutputBoundary successPresenter = new LeaderboardOutputBoundary() {
            @Override
            public void prepareSuccessView(ArrayList<Profile> leaderboard) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                Profile p1 = new Profile(1, 1);
                Profile p2 = new Profile(2, 4);
                Profile p3 = new Profile(10, 30);
                ArrayList<Profile> correctLeaderboard = new ArrayList<>(Arrays.asList(p3, p2, p1));
                assertEquals(leaderboard, correctLeaderboard);
            }

            @Override
            public void prepareFailView() {
                fail("Use case failure is unexpected.");
            }
        };

        LeaderboardInputBoundary leaderboardInteractor = new LeaderboardInteractor(successPresenter, leaderboardDAO, profileDAO);
        leaderboardInteractor.execute();
    }

    @Test
    void noLeaderboardTest() {
        LeaderboardDataAccessInterface leaderboardDAO = new TestLeaderboardDAO();
        ProfileDataAccessInterface profileDAO = new TestProfileDAO();

        // This creates a presenter that tests whether the test case is as we expect.
        LeaderboardOutputBoundary failurePresenter = new LeaderboardOutputBoundary() {
            @Override
            public void prepareSuccessView(ArrayList<Profile> leaderboard) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView() {
                assertTrue(leaderboardDAO.getLeaderboard().isEmpty());
            }
        };

        LeaderboardInputBoundary interactor = new LeaderboardInteractor(failurePresenter, leaderboardDAO, profileDAO);
        interactor.execute();
    }

    private class TestProfileDAO implements ProfileDataAccessInterface {

        @Override
        public void setGamesPlayed(int gamesPlayed) {

        }

        @Override
        public void setAverageScore(Double score) {

        }

        @Override
        public int getGamesPlayed() {
            return 10;
        }

        @Override
        public Double getAverageScore() {
            return 10.0;
        }

        @Override
        public int getUid() {
            return 0;
        }

        @Override
        public void update() throws IOException {

        }
    }
}
