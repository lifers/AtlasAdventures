package use_case.start_sp_quiz;

import entity.Question;
import org.openstreetmap.gui.jmapviewer.Coordinate;

public class CapitalQuestion implements QuestionGenerator{

    @Override
    public Question generateQuestion(Coordinate coordinate, String capital) {
        return new Question(coordinate, "Click on the country who's capital is: " + capital);
    }
}
