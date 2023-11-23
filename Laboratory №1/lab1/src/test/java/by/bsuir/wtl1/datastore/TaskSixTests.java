package by.bsuir.wtl1.datastore;

import by.bsuir.wtl1.processing.MatrixFormingProcess;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskSixTests {
    @Test
    public void sixTaskCheck() {
        long[][] result;
        long[] testArray = new long[5];
        long[][] expected = new long[testArray.length][testArray.length];
        for (int i = 0; i < testArray.length; i++) {
            testArray[i] = i;
        }
        for (int i = 0; i < expected.length; i++) {
            for (int j = i; j < expected[i].length + i; j++) {
                expected[i][j-i] = j % expected.length;
            }
        }
        result = MatrixFormingProcess
                          .createMatrixFromNumbers(testArray);
        for (int i = 0; i < expected.length; i++) {
            for (int j = 0; j < expected.length; j++) {
                assertEquals(expected[i][j], result[i][j]);
            }
        }

    }
}
