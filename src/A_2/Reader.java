package A_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

public class Reader extends Thread {
    private final Library library;
    private final CopyOnWriteArrayList<Book> readerBooks = new CopyOnWriteArrayList<>();

    public void addBooks(Book book) {
        readerBooks.add(book);
    }

    public Reader(Library library, String readerName) {
        super(readerName);
        this.library = library;
    }

    @Override
    public void run() {
        try {
            System.out.printf("%s пришёл в билитотеку\n", Thread.currentThread().getName());
            library.takeBook(this, new ArrayList<>(Arrays.asList("LibraryBook1", "LibraryBook2", "HomeBook1", "HomeBook2")));
            library.returnBook(readerBooks);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

