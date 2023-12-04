package interface_adapter.leaderboard;

import org.junit.jupiter.api.Test;
import use_case.leaderboard.LeaderboardInputBoundary;

import static org.junit.jupiter.api.Assertions.*;

class LeaderboardControllerTest {

    @Test
    void execute() {
        final int[] times = {0};
        var interactor = new LeaderboardInputBoundary() {
            @Override
            public void execute() {
                times[0] += 1;
            }
        };

        var controller = new LeaderboardController(interactor);
        controller.execute();
        assertEquals(1, times[0]);
    }
}