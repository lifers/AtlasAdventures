package data_access;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import use_case.question.QuestionDataAccessInterface;

public class MapDataAccessObject implements QuestionDataAccessInterface {

    public MapDataAccessObject() {}

    public Coordinate get() {
        return new Coordinate(43, 79);
    }
}
