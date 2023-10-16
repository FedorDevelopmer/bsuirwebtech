package by.bsuir.wtl1.datastore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TaskTwelveTests {
    @Test
    public void twelfthTaskCheck() {
        Book testBook = new Book("Various time","J.Annet",50);
        Book secondTestBook = new Book("Various time","J.Annet",50);
        Integer otherTypeEqualityObject = 10;
        assertEquals(testBook, testBook);
        assertNotEquals(null, testBook);
        assertEquals(testBook, secondTestBook);
        assertEquals(secondTestBook, testBook);
        secondTestBook.setAuthor("B.Rown");
        assertNotEquals(testBook, secondTestBook);
        assertNotEquals(secondTestBook, testBook);
        assertNotEquals(testBook, otherTypeEqualityObject);
        assertNotEquals(testBook.hashCode(),secondTestBook.hashCode());
        secondTestBook.setAuthor("J.Annet");
        assertEquals(testBook.hashCode(),secondTestBook.hashCode());
        assertEquals(testBook.hashCode(),testBook.hashCode());
        String testString = String.valueOf(testBook.getClass());
        testString=testString.concat("{title: Various time, ")
                             .concat("author: J.Annet, ")
                             .concat("price: 50, ")
                             .concat("isbn: 0, ")
                             .concat("edition: 0}");
        assertEquals(testString,testBook.toString());
    }
}
