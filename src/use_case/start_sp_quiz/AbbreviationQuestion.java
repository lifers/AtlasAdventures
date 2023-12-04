package use_case.start_sp_quiz;

import entity.Question;
import org.openstreetmap.gui.jmapviewer.Coordinate;

public class AbbreviationQuestion implements QuestionGenerator {
    @Override
    public Question generateQuestion(Coordinate coordinate, String abbreviation) {
        return new Question(coordinate, "Click on the country who's abbreviation is: " + abbreviation);
    }
}
