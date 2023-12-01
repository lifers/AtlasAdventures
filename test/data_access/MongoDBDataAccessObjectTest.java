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

    @BeforeEach
    void setUp() {
        MongoDBDataAccessObject dao = new MongoDBDataAccessObject(
                "mongodb://localhost:27017",
                "atlas-adventures-leaderbaord",
                "test-leaderboard");
        dao.clearCollection();
        dao.close();
    }

    @AfterEach
    void tearDown() {}

    @Test
    void generalTest() {
        /* Ensures that
        * a) profiles are inserted
        * b) profiles are updated
        * c) returned leaderboard is sorted
        * d) returned leaderboard is at most of length 10
        * e) uninserted profiles don't exist
         */
        MongoDBDataAccessObject dao = new MongoDBDataAccessObject(
                "mongodb://localhost:27017",
                "atlas-adventures-leaderbaord",
                "test-leaderboard");

        Profile p1 = new Profile(70,6, 10);
        Profile p2 = new Profile(80,2, 13);
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

        ArrayList<Profile> correctLeaderboard = new ArrayList<>();
        for (int i=10; i > 0; i--) {
            Profile p = new Profile(i+2,10*i + 10, 10);
            dao.addProfile(p);
            correctLeaderboard.add(p);
        }
        ArrayList<Profile> leaderboard = dao.getLeaderboard();
        assertTrue(dao.uidExists(10));
        assertEquals(leaderboard.size(), 10);
        assertEquals(leaderboard.get(0).getAverage_score(), 110);
        assertEquals(dao.getLeaderboard(), correctLeaderboard);
        dao.close();
    }
}