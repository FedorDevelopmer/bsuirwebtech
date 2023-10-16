package by.bsuir.wtl1.processing;


public class ArraySorting {

    public static long[] sort(long[] array) {
        for (int i = 0;i < array.length - 1; i++) {
            if(array[i] > array[i + 1]) {
                long temporaryValue = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temporaryValue;
                if(i!=0) {
                    i -= 2;
                }
            }
        }
        return array;
    }
}
