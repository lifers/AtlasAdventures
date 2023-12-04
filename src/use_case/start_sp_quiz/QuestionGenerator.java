package use_case.start_sp_quiz;

import entity.Question;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import java.util.List;

public interface QuestionGenerator {
    Question generateQuestion(List<String> list);
}
