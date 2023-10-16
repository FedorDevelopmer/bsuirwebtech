package by.bsuir.wtl1.datastore;

import by.bsuir.wtl1.processing.IncreasingSequenceProcess;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskFiveTests {
    @Test
    public void fifthTaskCheck() {
        int expected = 6;
        int removableElements = 0;
        long[] testArray = new long[20];

        for (int i = 0; i < testArray.length; i++) {
            if (i % 3 == 0) {
                testArray[i] = -1;
            } else {
                testArray[i] = i;
            }
        }
        removableElements = IncreasingSequenceProcess
                            .minimalRemovedElementsCount(testArray);
        assertEquals(expected, removableElements);
        expected = 16;
        for (int i = 0; i < testArray.length; i++) {
            if (i < 17) {
                testArray[i] = 20 - i;
            } else {
                testArray[i] = i;
            }
        }
        removableElements = IncreasingSequenceProcess
                .minimalRemovedElementsCount(testArray);
        assertEquals(expected, removableElements);
    }
}
