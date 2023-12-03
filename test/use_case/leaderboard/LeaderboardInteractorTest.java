package use_case.leaderboard;

import data_access.MongoDBDataAccessObject;
import entity.Profile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.profile.ProfileDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.platform.commons.function.Try.success;

public class LeaderboardInteractorTest {
    private final String connectionString = "mongodb+srv://AtlasAdventuresPlayer:pumpkinpatch240@cluster0.xhpezmy.mongodb.net/?retryWrites=true&w=majority";
    private final String databaseName = "AtlasAdventures";
    private final String collectionName = "test-leaderboard";


    @BeforeEach
    void setUp() {
        try (MongoDBDataAccessObject dao = new MongoDBDataAccessObject(connectionString, databaseName, collectionName)) {
            dao.clearCollection();
            dao.addProfile(new Profile(3,1, 1));
            dao.addProfile(new Profile(5,2, 4));
            dao.addProfile(new Profile(1,10, 30));
        }
    }
    @Test
    void addNewProfileTest() {
        LeaderboardDataAccessInterface leaderboardDAO = new MongoDBDataAccessObject(connectionString, databaseName, collectionName);
        ProfileDataAccessInterface profileDAO = new TestProfileDAO(0,5,5.0);

        // This creates a successPresenter that tests whether the test case is as we expect.
        LeaderboardOutputBoundary successPresenter = new LeaderboardOutputBoundary() {
            @Override
            public void prepareSuccessView(ArrayList<Profile> leaderboard) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                Profile p1 = new Profile(3,1, 1);
                Profile p2 = new Profile(5,2, 4);
                Profile p3 = new Profile(1,10, 30);
                Profile newProfile = new Profile(6,5,5);
                ArrayList<Profile> correctLeaderboard = new ArrayList<>(Arrays.asList(p3, newProfile, p2, p1));
                assertEquals(leaderboard, correctLeaderboard);
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        LeaderboardInputBoundary leaderboardInteractor = new LeaderboardInteractor(successPresenter, leaderboardDAO, profileDAO);
        leaderboardInteractor.execute();
    }

    @Test
    void updateProfileTest() {
        LeaderboardDataAccessInterface leaderboardDAO = new MongoDBDataAccessObject(connectionString, databaseName, collectionName);
        ProfileDataAccessInterface profileDAO = new TestProfileDAO(3,69,420.0);

        LeaderboardOutputBoundary successPresenter = new LeaderboardOutputBoundary() {

            @Override
            public void prepareSuccessView(ArrayList<Profile> leaderboard) {
                Profile p1 = new Profile(3,420, 69);
                Profile p2 = new Profile(5,2, 4);
                Profile p3 = new Profile(1,10, 30);
                ArrayList<Profile> correctLeaderboard = new ArrayList<>(Arrays.asList(p1, p3, p2));
                assertEquals(leaderboard, correctLeaderboard);
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        LeaderboardInputBoundary leaderboardInteractor = new LeaderboardInteractor(successPresenter, leaderboardDAO, profileDAO);
        leaderboardInteractor.execute();
    }

    @Test
    void failTest() {
        LeaderboardDataAccessInterface leaderboardDAO = new MongoDBDataAccessObject(connectionString, databaseName, collectionName);
        ProfileDataAccessInterface profileDAO = new TestProfileDAO(0,5,5.0);

        // Close connection so that the database cannot be accessed
        ((MongoDBDataAccessObject) leaderboardDAO).close();

        // This creates a presenter that tests whether the test case is as we expect.
        LeaderboardOutputBoundary failurePresenter = new LeaderboardOutputBoundary() {
            @Override
            public void prepareSuccessView(ArrayList<Profile> leaderboard) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
            }
        };

        LeaderboardInputBoundary interactor = new LeaderboardInteractor(failurePresenter, leaderboardDAO, profileDAO);
        interactor.execute();
    }

    private class TestProfileDAO implements ProfileDataAccessInterface {
        private int uid = 0;
        private int gamesPlayed = 0;
        private double averageScore = 0.0;

        public TestProfileDAO(int uid, int gamesPlayed, double averageScore) {
            this.uid = uid;
            this.gamesPlayed = gamesPlayed;
            this.averageScore = averageScore;
        }

        @Override
        public void setGamesPlayed(int gamesPlayed) {
            this.gamesPlayed = gamesPlayed;
        }

        @Override
        public void setAverageScore(Double score) {
            this.averageScore = score;
        }

        @Override
        public int getGamesPlayed() {
            return gamesPlayed;
        }

        @Override
        public Double getAverageScore() {
            return averageScore;
        }

        @Override
        public int getUid() {
            return uid;
        }

        @Override
        public void setUid(int newUid) {
            this.uid = newUid;
        }

        @Override
        public void update() throws IOException {

        }

        @Override
        public void save() throws IOException {

        }
    }
}
