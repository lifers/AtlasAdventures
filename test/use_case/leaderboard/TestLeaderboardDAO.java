package use_case.leaderboard;

import entity.Profile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class TestLeaderboardDAO implements LeaderboardDataAccessInterface{
    private ArrayList<Profile> leaderboard;
    private Comparator<Profile> profileComparator;
    public TestLeaderboardDAO() {
        Profile p1 = new Profile(1, 1);
        Profile p2 = new Profile(2, 4);
        Profile p3 = new Profile(10, 30);
        this.leaderboard = new ArrayList<Profile>(Arrays.asList(p1, p2, p3));
        this.profileComparator = new Comparator<Profile>() {
            @Override
            public int compare(Profile p1, Profile p2) {
                if(p1.getAverage_score() < p2.getAverage_score()) {
                    return 1;
                } else if (p1.getAverage_score() > p2.getAverage_score()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };
        this.leaderboard.sort(this.profileComparator);
    }


    @Override
    public ArrayList<Profile> getLeaderboard() {
        return leaderboard;
    }

    @Override
    public void addProfile(Profile profile) {
        leaderboard.add(profile);
        leaderboard.sort(profileComparator);
    }
}
