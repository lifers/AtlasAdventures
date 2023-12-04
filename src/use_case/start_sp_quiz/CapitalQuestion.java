package use_case.start_sp_quiz;

import entity.Question;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import java.util.List;

public class CapitalQuestion implements QuestionGenerator{

    /**
     * Generates a question using the provided list of strings, which is the data received from the GeoInfoDataAccessObject.
     *
     * @param  list  a list of strings containing the necessary information to generate the question
     * @return       the generated question
     */
    @Override
    public Question generateQuestion(List<String> list) {
        Double latitude = Double.valueOf(list.get(1));
        Double longitude = Double.valueOf(list.get(2));
        Coordinate newCoordinate = new Coordinate(latitude, longitude);
        return new Question(newCoordinate, "Click on the country who's capital is: " + list.get(3));
    }
}
