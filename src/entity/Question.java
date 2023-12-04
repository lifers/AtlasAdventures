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

    /**
     * Sets the score of the question.
     *
     * @param  score  the new score value
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Retrieves the score value.
     *
     * @return the score value
     */
    public int getScore() {
        return score;
    }

    /**
     * Retrieves the prompt.
     *
     * @return         	the prompt as a string
     */
    public String getPrompt() {
        return prompt;
    }


    /**
     * Get the solution coordinate.
     *
     * @return  the solution coordinate
     */
    public Coordinate getSolution() { return solution; }
}
