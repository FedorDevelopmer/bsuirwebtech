package by.bsuir.wtl1.datastore;

import by.bsuir.wtl1.processing.FormulaProcess;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskOneTests {
    @Test
    public void firstTaskCheck() {
        Coordinates2D point = new Coordinates2D();
        double result = 0;

        point.setX(5);
        point.setY(10);
        result = FormulaProcess.countFormula(point);
        assertEquals(5.2033,result,0.0001);
        point.setX(-5);
        point.setY(15);
        result = FormulaProcess.countFormula(point);
        assertEquals(-4.8148,result,0.0001);
        point.setX(0);
        point.setY(0);
        result = FormulaProcess.countFormula(point);
        assertEquals(0.5,result,0.0001);
    }
}
