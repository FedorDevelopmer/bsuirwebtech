package by.bsuir.wtl1.datastore;


public class ProgrammerBook extends Book {
    private String language;
    private int level;

    public ProgrammerBook() {
        super();
        language = "en";
        level = 0;
    }

    public ProgrammerBook(String title, String author, int price, int isbn, String language) {
        super(title,author,price,isbn);
        this.language = language;
        level = 0;
    }

    public ProgrammerBook(String title, String author, int price, int isbn, int level) {
        super(title,author,price,isbn);
        language = "en";
        this.level = level;
    }

    public ProgrammerBook(int level, String language) {
        super();
        this.language = language;
        this.level = level;
    }
    public ProgrammerBook(String language) {
        super();
        this.language = language;
        level = 0;
    }

    public ProgrammerBook(int level) {
        super();
        language = "en";
        this.level = level;
    }

    public String getLanguage() {
        return language;
    }
    public int getLevel() {
        return level;
    }
    public void changeLevel(int newLevel) {
        level=newLevel;
    }
    public void changeLanguage(int newLanguage) {
        level=newLanguage;
    }

    @Override
    public boolean equals(Object object) {
        boolean result;
        if (object==this) {
            result = true;
        } else if (!(getClass() == object.getClass())) {
            result = false;
        } else if (!(super.equals(object))) {
            result = false;
        } else {
            ProgrammerBook comparableProgrammerBook = (ProgrammerBook) object;
            result = this.language.equals(comparableProgrammerBook.language)
                     && (this.level == comparableProgrammerBook.level);
        }
        return result;
    }
    @Override
    public String toString() {
        StringBuilder stringFormer = new StringBuilder();
        stringFormer.append(getClass() + "{")
                    .append("title: " + super.getTitle() + ", ")
                    .append("author: " + super.getAuthor() + ", ")
                    .append("price: " + super.getPrice() + ", ")
                    .append("isbn: " + super.getIsbn() + ", ")
                    .append("edition: " + Book.getEdition() + ", ")
                    .append("language: " + language + ", ")
                    .append("level: " + level + "}");
        return stringFormer.toString();
    }
    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash += (language != null ? language.hashCode() : 0);
        hash *= (level != 0 ? level : 1);
        return hash;
    }
}
