package use_case.profile;

import entity.Profile;

import java.io.IOException;
import java.util.ArrayList;

public interface ProfileDataAccessInterface {
    public void setGamesPlayed(int gamesPlayed);

    public void setAverageScore(Double score);

    public int getGamesPlayed();

    public Double getAverageScore();

    public int getUid();
    public void setUid(int newUid);

    public void update() throws IOException;
}
