package use_case.start_sp_quiz;

import data_access.GeoInfoAccessObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SPQuizDataAccessInterfaceTest {

    public SPQuizDataAccessInterface SPQuizDataAccessObject;
    @BeforeEach
    void init() {
        this.SPQuizDataAccessObject = new GeoInfoAccessObject();
    }
    @Test
    void information() {
        assertEquals(10, SPQuizDataAccessObject.information().size());
    }
    void unique_countries() {
        List<List<String>> information = SPQuizDataAccessObject.information();
        int recurrences = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i != j) {
                    if (information.get(i).get(0).equals(information.get(j).get(0))) {
                        recurrences += 1;
                    }
                }
            }
        }

        assertEquals(0, recurrences);
    }
}