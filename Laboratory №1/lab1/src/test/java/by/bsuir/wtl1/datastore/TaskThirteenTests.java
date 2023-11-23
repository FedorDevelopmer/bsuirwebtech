package by.bsuir.wtl1.datastore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TaskThirteenTests {
    @Test
    public void thirteenTaskCheck() {
        ProgrammerBook testProgrammerBook = new ProgrammerBook("Modern C++ Guide","I.Shimm",
                                                              100,1,2);
        ProgrammerBook secondProgrammerBook = new ProgrammerBook("Modern C++ Guide","I.Shimm",
                                                                100,1,2);
        Integer otherTypeEqualityObject = 150;
        assertEquals(testProgrammerBook, testProgrammerBook);
        assertNotEquals(null, testProgrammerBook);
        assertEquals(testProgrammerBook, secondProgrammerBook);
        assertEquals(secondProgrammerBook, testProgrammerBook);
        secondProgrammerBook.setPrice(90);
        assertNotEquals(testProgrammerBook, secondProgrammerBook);
        assertNotEquals(secondProgrammerBook, testProgrammerBook);
        assertNotEquals(testProgrammerBook, otherTypeEqualityObject);
        assertNotEquals(testProgrammerBook.hashCode(),secondProgrammerBook.hashCode());
        secondProgrammerBook.setPrice(100);
        assertEquals(testProgrammerBook.hashCode(),secondProgrammerBook.hashCode());
        assertEquals(testProgrammerBook.hashCode(),testProgrammerBook.hashCode());
        String testString = String.valueOf(testProgrammerBook.getClass());
        testString=testString.concat("{title: Modern C++ Guide, ")
                .concat("author: I.Shimm, ")
                .concat("price: 100, ")
                .concat("isbn: 1, ")
                .concat("edition: 0, ")
                .concat("language: en, ")
                .concat("level: 2}");
        assertEquals(testString,testProgrammerBook.toString());
    }
}
