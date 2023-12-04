package use_case.start_sp_quiz;

import data_access.GeoInfoDataAccessObject;
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
        this.SPQuizDataAccessObject = new GeoInfoDataAccessObject();
    }
    @Test
    void information() {
        assertEquals(5, SPQuizDataAccessObject.information().size());
    }

}