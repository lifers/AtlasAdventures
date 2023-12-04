package use_case.start_sp_quiz;

import entity.Question;
import org.openstreetmap.gui.jmapviewer.Coordinate;

public interface QuestionGenerator {
    public Question generateQuestion(Coordinate coordinate, String string);
}
