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
        try{
            FileUserDataAccessObject dataAccessObject = new FileUserDataAccessObject("test.csv");
            // Set up the initial state of the test data
            dataAccessObject.setAverageScore(3.5);
            dataAccessObject.setGamesPlayed(10);

            // Call the update method
            try {
                dataAccessObject.update();
            } catch (IOException e) {
                fail("IOException occurred during update");
            }

            // Verify that the data has been updated correctly
            assertEquals(7.2, dataAccessObject.getAverageScore(), 0.01);
            assertEquals(15, dataAccessObject.getGamesPlayed());
        } catch (IOException e){
            fail("Initializer error in FileUserDataAccessObject");
        }

    }

    /**
     * Test case for the setGamesPlayed method in FileUserDataAccessObject class.
     */
    @Test
    void setGamesPlayed() {
        try{
            FileUserDataAccessObject dataAccessObject = new FileUserDataAccessObject("test.csv");

            // Set up the initial state of the test data
            dataAccessObject.setGamesPlayed(10);

            assertEquals(10, dataAccessObject.getGamesPlayed());
        } catch (IOException e){
            fail("Initializer error in FileUserDataAccessObject");
        }
    }

    /**
     * Test case for the setAverageScore method in FileUserDataAccessObject class.
     */
    @Test
    void setAverageScore() {
        try{
            FileUserDataAccessObject dataAccessObject = new FileUserDataAccessObject("test.csv");

            // Set up the initial state of the test data
            dataAccessObject.setAverageScore(3.5);

            assertEquals(3.5, dataAccessObject.getAverageScore());
        } catch (IOException e){
            fail("Initializer error in FileUserDataAccessObject");
        }
    }

    /**
     * Test case for the getGamesPlayed method in FileUserDataAccessObject class.
     */
    @Test
    void getGamesPlayed() {
        // Create a test instance of FileUserDataAccessObject
        try{
            FileUserDataAccessObject dataAccessObject = new FileUserDataAccessObject("test.csv");

            assertEquals(15, dataAccessObject.getGamesPlayed());
        } catch (IOException e){
            fail("Initializer error in FileUserDataAccessObject");
        }
    }

    /**
     * Test case for the getAverageScore method in FileUserDataAccessObject class.
     */
    @Test
    void getAverageScore() {
        // Create a test instance of FileUserDataAccessObject
        try{
            FileUserDataAccessObject dataAccessObject = new FileUserDataAccessObject("test.csv");

            assertEquals(7.2, dataAccessObject.getAverageScore());
        } catch (IOException e){
            fail("Initializer error in FileUserDataAccessObject");
        }
    }

    @Test
    void save() {
        try {
            FileUserDataAccessObject dataAccessObject = new FileUserDataAccessObject("test2.csv");
            dataAccessObject.setAverageScore(3.5);
            dataAccessObject.setGamesPlayed(10);
            dataAccessObject.save();
            FileUserDataAccessObject dao2 = new FileUserDataAccessObject("test2.csv");
            assertEquals(dao2.getAverageScore(), 3.5);
            assertEquals(dao2.getGamesPlayed(), 10);
        }
        catch (IOException e){
            fail("Save/Initializer error in FileUserDataAccessObject");
        }
    }
}