package use_case.start_sp_quiz;

import entity.Question;
import org.openstreetmap.gui.jmapviewer.Coordinate;

public class CountryQuestion implements QuestionGenerator{
    @Override
    public Question generateQuestion(Coordinate coordinate, String country) {
        return new Question(coordinate, "Click on the country: " + country);
    }
}
