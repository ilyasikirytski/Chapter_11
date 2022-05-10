package A_2;

import java.util.concurrent.CopyOnWriteArrayList;

public class Reader extends Thread {
    private final Library library;
    CopyOnWriteArrayList<Book> readerBooks = new CopyOnWriteArrayList<>();

    public Reader(Library library, String readerName) {
        super(readerName);
        this.library = library;
    }

    @Override
    public void run() {
        try {
            System.out.printf("%s пришёл в билитотеку\n", Thread.currentThread().getName());
            library.takeBook(this, "LibraryBook1", "LibraryBook2", "HomeBook1", "HomeBook2");
            library.returnBook(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

