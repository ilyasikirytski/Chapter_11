package A_2;

public class Book {
    public boolean isReadingInLibraryOnly;
    public boolean isTaken;
    private final String name;

    public Book(boolean isReadingInLibraryOnly, String name) {
        this.isReadingInLibraryOnly = isReadingInLibraryOnly;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
