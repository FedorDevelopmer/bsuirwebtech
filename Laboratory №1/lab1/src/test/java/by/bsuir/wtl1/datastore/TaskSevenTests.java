package by.bsuir.wtl1.datastore;

import by.bsuir.wtl1.processing.ArraySorting;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskSevenTests {
    @Test
    public void seventhTaskCheck() {
        long[] testArray = new long[50];
        long[] sortedArray = new long[testArray.length];

        for (int i = 0; i < testArray.length; i++) {
            testArray[i] = testArray.length - i - 1;
            sortedArray[i] = i;
        }
        ArraySorting.sort(testArray);
        for (int i = 0; i < testArray.length; i++) {
            assertEquals(testArray[i], sortedArray[i]);
        }
    }
}
