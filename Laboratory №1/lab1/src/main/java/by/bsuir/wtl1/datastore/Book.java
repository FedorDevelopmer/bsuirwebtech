package by.bsuir.wtl1.datastore;

public class Book implements Cloneable, Comparable<Book> {
    private static int edition=0;
    private String title;
    private String author;
    private int price;
    private int isbn;

    public Book() {
        title = "default";
        author = "anonymous";
        price = 0;
        isbn = 0;
    }

    public Book(String bookTitle, int bookPrice) {
        title = bookTitle;
        author = "anonymous";
        price = bookPrice;
        isbn = 0;
    }

    public Book(String bookTitle, String bookAuthor, int bookPrice) {
        title = bookTitle;
        author = bookAuthor;
        price = bookPrice;
        isbn = 0;
    }

    public Book(int isbn) {
        title = "default";
        author = "anonymous";
        price = 0;
        this.isbn = isbn;
    }

    public Book(String bookTitle, String bookAuthor, int bookPrice,int bookIsbn) {
        title = bookTitle;
        author = bookAuthor;
        price = bookPrice;
        isbn = bookIsbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPrice() {
        return price;
    }

    public int getIsbn() {
        return isbn;
    }
    public static int getEdition() {
        return edition;
    }
    public void setPrice(int newPrice) {
        price = newPrice;
    }
    public void setTitle(String newTitle) {
        title = newTitle;
    }
    public void setAuthor(String newAuthor) {
        author= newAuthor;
    }
    public static void setEdition(int newEdition) {
        edition = newEdition;
    }

    public void setIsbn(int bookIsbn) {
        isbn = bookIsbn;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        } else if (object == null) {
            return false;
        } else if (getClass() != object.getClass()) {
            return false;
        } else {
            boolean result;
            Book comparableBook = (Book) object;
            result = author.equals(comparableBook.author)
                    && title.equals(comparableBook.title)
                    && (price == comparableBook.price)
                    && (isbn == comparableBook.isbn);
            return result;
        }
    }

    @Override
    public String toString(){
        StringBuilder stringFormer = new StringBuilder();
        stringFormer.append(getClass() + "{")
                    .append("title: " + title + ", ")
                    .append("author: " + author + ", ")
                    .append("price: " + price + ", ")
                    .append("isbn: " + isbn + ", ")
                    .append("edition: " + Book.edition + "}");
        return stringFormer.toString();
    }

    @Override
    public int hashCode(){
        int hash = 257;
        hash += (author != null ? author.hashCode() : 0);
        hash += (title != null ? title.hashCode() : 0);
        hash *= (price != 0 ? price : 1);
        hash *= (isbn != 0 ? isbn : 1);
        hash *= (edition != 0 ? edition : 1);
        return hash;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public int compareTo(Book o) {
        if (this.isbn < o.isbn) {
            return -1;
        } else if (this.isbn > o.isbn) {
            return 1;
        } else {
            return 0;
        }
    }
}
