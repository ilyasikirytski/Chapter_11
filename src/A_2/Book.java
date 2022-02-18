package A_2;

public class Book {
    public boolean isReadingInLibraryOnly;
    private final String name;
    private final int index;

    public Book(boolean isReadingInLibraryOnly, String name, int index) {
        this.isReadingInLibraryOnly = isReadingInLibraryOnly;
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }
}
