package by.bsuir.wtl1.datastore;

import by.bsuir.wtl1.processing.TangentRangeProcess;
import org.junit.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class TaskThreeTests {

    @Test
    public void thirdTaskCheck() {
        double a;
        double b;
        double h;
        List<Coordinates2D> result;

        a = 0;
        b = 1;
        h = 0.1;
        result = TangentRangeProcess.countTangentInRange(a,b,h);
        for (int i = 0; i < result.size(); i++) {
            double tanA = Math.tan(a);
            assertEquals(a, result.get(i).getX());
            assertEquals(tanA, result.get(i).getY());
            a += h;
        }
        a = 10;
        b = -1;
        h = -0.1;
        result = TangentRangeProcess.countTangentInRange(a,b,h);
        for (int i = 0; i < result.size(); i++) {
            double tanA = Math.tan(a);
            assertEquals(a, result.get(i).getX());
            assertEquals(tanA, result.get(i).getY());
            a += h;
        }

    }


}
