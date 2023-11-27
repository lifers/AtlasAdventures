package data_access;

import entity.Profile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
        // Ensures that a) profiles are inserted, b) returned leaderboard is sorted and smaller than 10
        MongoDBDataAccessObject dao = new MongoDBDataAccessObject(
                "mongodb://localhost:27017",
                "atlas-adventures-leaderbaord",
                "test-leaderboard");
        dao.addProfile(new Profile(6, 10));
        dao.addProfile(new Profile(2, 13));
        assertEquals(dao.getLeaderboard().size(), 2);
        ArrayList<Profile> correctLeaderboard = new ArrayList<>();
        for (int i=10; i > 0; i--) {
            Profile p = new Profile(10*i + 10, 10);
            dao.addProfile(p);
            correctLeaderboard.add(p);
        }
        ArrayList<Profile> leaderboard = dao.getLeaderboard();
        assertEquals(leaderboard.size(), 10);
        assertEquals(leaderboard.get(0).getAverage_score(), 110);
        assertEquals(dao.getLeaderboard(), correctLeaderboard);
        dao.close();
    }
}