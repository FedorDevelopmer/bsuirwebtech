package by.bsuir.wtl1.datastore;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskFifteenTests {
    @Test
    public void TaskFifteenCheck() {
        Random isbnGenerator = new Random();
        Book[] booksArray = new Book[10];

        for (int i = 0; i < booksArray.length; i++) {
            booksArray[i] = new Book(isbnGenerator.nextInt());
        }
        Arrays.sort(booksArray);
        for (int i = 0; i < booksArray.length-1; i++) {
            assertTrue(booksArray[i].getIsbn() <= booksArray[i + 1].getIsbn());
        }
    }
}
