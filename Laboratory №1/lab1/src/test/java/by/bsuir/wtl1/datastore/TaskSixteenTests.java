package by.bsuir.wtl1.datastore;

import by.bsuir.wtl1.processing.AuthorThenTitleComparator;
import by.bsuir.wtl1.processing.AuthorTitlePriceComparator;
import by.bsuir.wtl1.processing.TitleComparator;
import by.bsuir.wtl1.processing.TitleThenAuthorComparator;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskSixteenTests {
    @Test
    public void TaskSixteenCheck() {
        Comparator<Book> titleComparator = new TitleComparator();
        Comparator<Book> authorTitleComparator = new AuthorThenTitleComparator();
        Comparator<Book> titleAuthorComparator = new TitleThenAuthorComparator();
        Comparator<Book> authorTitlePriceComparator = new AuthorTitlePriceComparator();
        List<Book> testSortingList = new ArrayList<>();
        List<Book> testExpectedList = new ArrayList<>();
        Book firstBook = new Book("XVI Century History","A.Hontor",100);
        Book secondBook = new Book("XV Century History","A.Hontor",100);
        Book thirdBook = new Book("XVI Century History","B.Koey",100);
        Book fourthBook = new Book("XVI Century History","B.Koey",150);
        Book fifthBook = new Book("XVI Century History","B.Koey",100);
        Book sixthBook = new Book("XV Century History","B.Koey",150);

        testSortingList.add(firstBook);
        testSortingList.add(secondBook);
        testSortingList.add(thirdBook);
        testSortingList.add(fourthBook);
        testSortingList.add(fifthBook);
        testSortingList.add(sixthBook);
        testExpectedList.add(0,secondBook);
        testExpectedList.add(1,sixthBook);
        testExpectedList.add(2,firstBook);
        testExpectedList.add(3,thirdBook);
        testExpectedList.add(4,fourthBook);
        testExpectedList.add(5,fifthBook);
        testSortingList.sort(titleComparator);
        for (int i = 0; i < testSortingList.size(); i++) {
            assertEquals(testExpectedList.get(i),testSortingList.get(i));
        }
        testExpectedList.clear();
        testExpectedList.add(0,secondBook);
        testExpectedList.add(1,firstBook);
        testExpectedList.add(2,sixthBook);
        testExpectedList.add(3,thirdBook);
        testExpectedList.add(4,fourthBook);
        testExpectedList.add(5,fifthBook);
        testSortingList.sort(authorTitleComparator);
        for (int i = 0; i < testSortingList.size(); i++) {
            assertEquals(testExpectedList.get(i),testSortingList.get(i));
        }
        testExpectedList.clear();
        testExpectedList.add(0,secondBook);
        testExpectedList.add(1,sixthBook);
        testExpectedList.add(2,firstBook);
        testExpectedList.add(3,thirdBook);
        testExpectedList.add(4,fourthBook);
        testExpectedList.add(5,fifthBook);
        testSortingList.sort(titleAuthorComparator);
        for (int i = 0; i < testSortingList.size(); i++) {
            assertEquals(testExpectedList.get(i),testSortingList.get(i));
        }
        testExpectedList.clear();
        testExpectedList.add(0,secondBook);
        testExpectedList.add(1,firstBook);
        testExpectedList.add(2,sixthBook);
        testExpectedList.add(3,thirdBook);
        testExpectedList.add(4,fifthBook);
        testExpectedList.add(5,fourthBook);
        testSortingList.sort(authorTitlePriceComparator);
        for (int i = 0; i < testSortingList.size(); i++) {
            assertEquals(testExpectedList.get(i),testSortingList.get(i));
        }
    }
}
