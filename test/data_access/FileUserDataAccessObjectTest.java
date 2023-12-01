package data_access;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileUserDataAccessObjectTest {

    /**
     * Test case for the update method in FileUserDataAccessObject class.
     */
    @Test
    void update() throws IOException {
        // Create a test instance of FileUserDataAccessObject
        FileUserDataAccessObject userDataAccessObject = new FileUserDataAccessObject("test.csv");

        // Set up the initial state of the test data
        userDataAccessObject.setAverageScore(3.5);
        userDataAccessObject.setGamesPlayed(10);

        // Call the update method
        try {
            userDataAccessObject.update();
        } catch (IOException e) {
            fail("IOException occurred during update");
        }

        // Verify that the data has been updated correctly
        assertEquals(7.2, userDataAccessObject.getAverageScore(), 0.01);
        assertEquals(15, userDataAccessObject.getGamesPlayed());
    }

    /**
     * Test case for the setGamesPlayed method in FileUserDataAccessObject class.
     */
    @Test
    void setGamesPlayed() {
    }

    /**
     * Test case for the setAverageScore method in FileUserDataAccessObject class.
     */
    @Test
    void setAverageScore() {
    }

    /**
     * Test case for the getGamesPlayed method in FileUserDataAccessObject class.
     */
    @Test
    void getGamesPlayed() {
    }

    /**
     * Test case for the getAverageScore method in FileUserDataAccessObject class.
     */
    @Test
    void getAverageScore() {
    }
}