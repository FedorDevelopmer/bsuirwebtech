package by.bsuir.wtl1.processing;

import java.util.ArrayList;


public class ArrayElementsProcess {
    public static ArrayList<Integer> processArray(long[] array) {
        ArrayList<Integer> resultIndexes = new ArrayList<>();
        for(int i = 0; i < array.length; i++){
            if (PrimeNumberCheck.isPrime(array[i])) {
                resultIndexes.add(i);
            }
        }
        return resultIndexes;
    }
}
