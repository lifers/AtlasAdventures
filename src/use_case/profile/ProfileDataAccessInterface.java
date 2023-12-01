package use_case.profile;

import entity.Profile;

import java.io.IOException;
import java.util.ArrayList;

public interface ProfileDataAccessInterface {
    /**
     * Sets the number of games played.
     *
     * @param  gamesPlayed   the number of games played
     */
    public void setGamesPlayed(int gamesPlayed);

    /**
     * Sets the average score.
     *
     * @param  score the average score to be set
     */
    public void setAverageScore(Double score);

    /**
     * Retrieves the number of games played.
     *
     * @return the number of games played
     */
    public int getGamesPlayed();

    /**
     * Gets the average score.
     *
     * @return the average score
     */
    public Double getAverageScore();

    /**
     * Updates the DAO to use the current values within the file.
     *
     * @throws IOException  If an input or output exception occurred
     */
    public void update() throws IOException;
}
