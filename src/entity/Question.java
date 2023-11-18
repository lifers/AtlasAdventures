package entity;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Question {
    private final Coordinate solution;
    private final String prompt;
    private int score = 0;

    public Question(Coordinate solution, String prompt) {
        this.solution = solution;
        this.prompt = prompt;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public Coordinate getSolution() { return solution; }
}
