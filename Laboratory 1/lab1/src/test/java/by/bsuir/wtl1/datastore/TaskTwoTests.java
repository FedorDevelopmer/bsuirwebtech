package by.bsuir.wtl1.datastore;

import by.bsuir.wtl1.processing.AreaBelongingProcess;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TaskTwoTests {
    @Test
    public void secondTaskCheck() {
        Coordinates2D point = new Coordinates2D();
        boolean result;

        point.setX(3);
        point.setY(-1);
        result = AreaBelongingProcess.isBelongToArea(point);
        assertTrue(result);
        point.setX(1);
        point.setY(4);
        result = AreaBelongingProcess.isBelongToArea(point);
        assertTrue(result);
        point.setX(-2);
        point.setY(3);
        result = AreaBelongingProcess.isBelongToArea(point);
        assertTrue(result);
        point.setX(-4);
        point.setY(-2);
        result = AreaBelongingProcess.isBelongToArea(point);
        assertTrue(result);
        point.setX(4);
        point.setY(5);
        result = AreaBelongingProcess.isBelongToArea(point);
        assertTrue(result);
        point.setX(0);
        point.setY(0);
        result = AreaBelongingProcess.isBelongToArea(point);
        assertTrue(result);
        point.setX(10);
        point.setY(10);
        result = AreaBelongingProcess.isBelongToArea(point);
        assertFalse(result);
        point.setX(-100);
        point.setY(-100);
        result = AreaBelongingProcess.isBelongToArea(point);
        assertFalse(result);
    }
}
