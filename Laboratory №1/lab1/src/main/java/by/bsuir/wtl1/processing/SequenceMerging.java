package by.bsuir.wtl1.processing;

import java.util.ArrayList;
import java.util.List;


public class SequenceMerging {
    public static List<Integer> getSecondSequenceElementsInsertionPositions(long[] firstSequence,
                                                                            long[] secondSequence) {
        long[] mergedSequence = new long[firstSequence.length + secondSequence.length];
        int firstSequenceIndex = 0;
        int secondSequenceIndex = 0;
        ArrayList<Integer> resultIndexes = new ArrayList<>();
        int i = 0;
        while((i < mergedSequence.length) && (firstSequenceIndex < firstSequence.length)
                                          && (secondSequenceIndex < secondSequence.length)) {
            if (firstSequence[firstSequenceIndex] <= secondSequence[secondSequenceIndex]) {
                mergedSequence[i] = firstSequence[firstSequenceIndex];
                firstSequenceIndex++;
            } else {
                mergedSequence[i] = secondSequence[secondSequenceIndex];
                resultIndexes.add(i);
                secondSequenceIndex++;
            }
            i++;
        }
        if (firstSequenceIndex >= firstSequence.length) {
            while (i < mergedSequence.length) {
                mergedSequence[i] = secondSequence[secondSequenceIndex];
                resultIndexes.add(i);
                secondSequenceIndex++;
                i++;
            }
        } else {
            while(i < mergedSequence.length){
                mergedSequence[i] = firstSequence[firstSequenceIndex];
                firstSequenceIndex++;
                i++;
            }
        }
        return resultIndexes;
    }
}
