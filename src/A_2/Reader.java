package A_2;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Reader extends Thread {
    private final Library library;
    private final CopyOnWriteArrayList<Book> readerBooks = new CopyOnWriteArrayList<>();

    /*
    коллекция должна быть private final
        написать методы взять книгу
        отдать книгу

        в метод returnBook передать только список книг а не всего читателя
     */
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
            ArrayList<String> namesOfBook = new ArrayList<>();
            namesOfBook.add("LibraryBook1");
            namesOfBook.add("LibraryBook2");
            namesOfBook.add("HomeBook1");
            namesOfBook.add("HomeBook2");

            System.out.printf("%s пришёл в билитотеку\n", Thread.currentThread().getName());
            library.takeBook(this, namesOfBook);
            library.returnBook(readerBooks);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

