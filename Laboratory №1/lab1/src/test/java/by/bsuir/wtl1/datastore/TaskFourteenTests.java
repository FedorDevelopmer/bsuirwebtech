package by.bsuir.wtl1.datastore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;


public class TaskFourteenTests {
    @Test
    public void TaskFourteenCheck() throws CloneNotSupportedException{
        Book originalBook = new Book();
        Book cloneOfBook;
        cloneOfBook =(Book) originalBook.clone();
        assertTrue(originalBook!=cloneOfBook);
        assertTrue(originalBook.getClass() == cloneOfBook.getClass());
        assertEquals(originalBook, cloneOfBook);
        cloneOfBook.setAuthor("E.Auth");
        assertNotEquals(originalBook,cloneOfBook);
    }
}
