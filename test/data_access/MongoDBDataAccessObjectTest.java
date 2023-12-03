package data_access;

import entity.Profile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MongoDBDataAccessObjectTest {
    private final String connectionString = "mongodb+srv://AtlasAdventuresPlayer:pumpkinpatch240@cluster0.xhpezmy.mongodb.net/?retryWrites=true&w=majority";
    private final String databaseName = "AtlasAdventures";
    private final String collectionName = "test-leaderboard";


    @BeforeEach
    void setUp() {
        try (MongoDBDataAccessObject dao = new MongoDBDataAccessObject(connectionString, databaseName, collectionName)) {
            dao.clearCollection();
        }
    }

    @AfterEach
    void tearDown() {}

    @Test
    void insertionTest() {
        /**
         * This test ensures that
         * a) profiles are inserted
         * b) profiles are updated
         * c) uninserted profiles don't exist
         *
         * @param  None
         * @return None
         */
        try (MongoDBDataAccessObject dao = new MongoDBDataAccessObject(connectionString, databaseName, collectionName)) {
            Profile p1 = new Profile(70, 6, 10);
            Profile p2 = new Profile(80, 2, 13);
            dao.addProfile(p1);
            dao.addProfile(p2);
            assertEquals(dao.getLeaderboard().size(), 2);
            assertTrue(dao.uidExists(70));
            assertFalse(dao.uidExists(10));

            p1.setGames_played(15);
            p2.setAverage_score(9);
            ArrayList<Profile> updated_leaderboard = new ArrayList<>(Arrays.asList(p2, p1));
            assertNotSame(dao.getLeaderboard(), updated_leaderboard);

            dao.updateProfile(p1);
            dao.updateProfile(p2);

            assertTrue(dao.uidExists(70));

            ArrayList<Profile> new_leaderboard = dao.getLeaderboard();
            assertEquals(new_leaderboard, updated_leaderboard);
        }
    }

    @Test
    void correctLeaderboardTest() {
        /**
         * This test ensures that
         * a) returned leaderboard is sorted
         * b) returned leaderboard is at most of length 10
         *
         * @param  None
         * @return None
         */
        try (MongoDBDataAccessObject dao = new MongoDBDataAccessObject(connectionString, databaseName, collectionName)) {
            ArrayList<Profile> correctLeaderboard = new ArrayList<>();
            for (int i = 14; i > 0; i--) {
                Profile p = new Profile(i + 2, 10 * i + 10, 10);
                dao.addProfile(p);
                correctLeaderboard.add(p);
            }
            ArrayList<Profile> leaderboard = dao.getLeaderboard();
            assertTrue(dao.uidExists(10));
            assertEquals(leaderboard.size(), 10);
            assertEquals(leaderboard.get(0).getAverage_score(), 150);
            assertEquals(dao.getLeaderboard(), correctLeaderboard.subList(0, 10));
        }
    }

    @Test
    void uniqueIDGenerationTest() {
        /**
         * This test ensures that
         * a) unique IDs are generated
         *
         * @param  None
         * @return None
         */
        try (MongoDBDataAccessObject dao = new MongoDBDataAccessObject(connectionString, databaseName, collectionName)) {
            assertEquals(dao.generateNewUid(), 1);
            Profile p1 = new Profile(70, 6, 10);
            dao.addProfile(p1);
            assertEquals(dao.generateNewUid(), 71);
        }
    }
}