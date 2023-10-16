package by.bsuir.wtl1.processing;

import by.bsuir.wtl1.datastore.Book;

import java.util.Comparator;


public class AuthorTitlePriceComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        if (o1.getAuthor().compareTo(o2.getAuthor()) > 0) {
            return 1;
        } else if (o1.getAuthor().compareTo(o2.getAuthor()) < 0) {
            return -1;
        } else {
            if (o1.getTitle().compareTo(o2.getTitle()) > 0) {
                return 1;
            } else if (o1.getTitle().compareTo(o2.getTitle()) < 0) {
                return -1;
            } else {
                return Integer.compare(o1.getPrice(), o2.getPrice());
            }
        }
    }
}
