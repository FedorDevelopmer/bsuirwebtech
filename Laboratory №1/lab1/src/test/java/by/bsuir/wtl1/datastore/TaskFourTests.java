package by.bsuir.wtl1.datastore;

import by.bsuir.wtl1.processing.ArrayElementsProcess;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class TaskFourTests {
    @Test
    public void fourthTaskCheck() {
        List<Integer> expected = new ArrayList<>();
        long[] testArray = new long[20];

        for (int i = 0; i < testArray.length; i++) {
            testArray[i] = i;
        }
        List<Integer> indexes = ArrayElementsProcess.processArray(testArray);
        expected.add(2);
        expected.add(3);
        expected.add(5);
        expected.add(7);
        expected.add(11);
        expected.add(13);
        expected.add(17);
        expected.add(19);
        assertEquals(expected.size(), indexes.size());
        for(int i = 0; i < indexes.size();i++){
            assertEquals(expected.get(i), indexes.get(i));
        }

    }

}
