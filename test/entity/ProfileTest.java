package entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

    private static Profile profile;

    @BeforeEach
    void init() {
        profile = new Profile(20, 90d, 10);
    }

    @Test
    void getAverage_score() {
        assertEquals(90d, profile.getAverage_score());
    }

    @Test
    void getGames_played() {
        assertEquals(10, profile.getGames_played());
    }

    @Test
    void setAverage_score() {
        profile.setAverage_score(94d);
        assertEquals(94d, profile.getAverage_score());
    }

    @Test
    void setGames_played() {
        profile.setGames_played(11);
        assertEquals(11, profile.getGames_played());
    }

    @Test
    void toStringFunction() {
        assertEquals("Profile{" +
                "uid='" + "20" + '\'' +
                ", avgScore='" + "90.0" + '\'' +
                ", gamesPlayed=" + 10 +
                '}', profile.toString());
    }
    @Test
    void equalsTrue() {
        Profile new_profile = profile;
        assertTrue(profile.equals(new_profile));
    }

    @Test
    void notEquals() {
        assertFalse(profile.equals(null));
    }
}