package use_case.start_sp_quiz;

import entity.Question;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import java.util.List;

public class AbbreviationQuestion implements QuestionGenerator {
    @Override
    public Question generateQuestion(List<String> list) {
        Double latitude = Double.valueOf(list.get(1));
        Double longitude = Double.valueOf(list.get(2));
        Coordinate newCoordinate = new Coordinate(latitude, longitude);
        return new Question(newCoordinate, "Click on the country who's abbreviation is: " + list.get(4));    }
}
