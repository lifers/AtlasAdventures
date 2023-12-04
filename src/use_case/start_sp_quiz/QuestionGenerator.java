package use_case.start_sp_quiz;

import entity.Question;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import java.util.List;

public interface QuestionGenerator {
    /**
     * Generates a question based on a given list of strings, which is the information received from the GeoInfoDataAccessObject.
     *
     * @param  list  the list of strings used to generate the question
     * @return       the generated question
     */
    Question generateQuestion(List<String> list);
}
