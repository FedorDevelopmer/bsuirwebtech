package by.bsuir.wtl1.datastore;

import by.bsuir.wtl1.processing.SequenceMerging;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskEightTests {
    @Test
    public void eighthTaskCheck() {
        long[] firstSequence = new long[5];
        long[] secondSequence = new long[5];
        List<Integer> indexes = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();

        for (int i = 0; i < firstSequence.length; i++) {
            firstSequence[i] = i;
            secondSequence[i] = i % 4;
        }
        Arrays.sort(secondSequence);
        indexes= SequenceMerging
                 .getSecondSequenceElementsInsertionPositions(firstSequence,
                                                              secondSequence);
        expected.add(1);
        expected.add(2);
        expected.add(4);
        expected.add(6);
        expected.add(8);
        assertEquals(expected.size(), indexes.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), indexes.get(i));
        }

    }
}
