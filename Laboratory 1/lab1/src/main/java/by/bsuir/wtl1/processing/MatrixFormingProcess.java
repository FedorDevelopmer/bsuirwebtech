package by.bsuir.wtl1.processing;

import java.util.List;
import java.util.LinkedList;


public class MatrixFormingProcess {
    public static long[][] createMatrixFromNumbers(long[] array) {
        LinkedList<Long> arrayList = new LinkedList<>();
        for (long elementOfArray : array) {
            arrayList.add(elementOfArray);
        }
        long[][] resultMatrix = new long[array.length][array.length];
        for (int i = 0;i < array.length; i++) {
            for(int j=0; j < array.length; j++){
                resultMatrix[i][j] = arrayList.get(j);
            }
            long temporaryFirstElement = arrayList.getFirst();
            arrayList.remove(0);
            arrayList.addLast(temporaryFirstElement);
        }
        return resultMatrix;
    }
}
