package A_2;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;

public class Library {
    static final Semaphore SEMAPHORE = new Semaphore(1, true);
    CopyOnWriteArrayList<Book> libraryBooks = new CopyOnWriteArrayList<>();
    CopyOnWriteArrayList<Book> readingBooks = new CopyOnWriteArrayList<>();
    CopyOnWriteArrayList<Book> homeBooks = new CopyOnWriteArrayList<>();

    public void addBook(Book book) {
        this.libraryBooks.add(book);
    }

    public void takeBookToReadingRoom(String name) {
        try {
            for (Book b : libraryBooks) {
                if (!libraryBooks.isEmpty() && b.isReadingInLibraryOnly) {
                    readingBooks.add(b);
                    libraryBooks.remove(b);
                    System.out.println(name + " взял книгу: " + b.getIndex() + ", в читальный зал");
                    if (readingBooks.size() >= 2) {
                        returnBook(name);
                    }
                } else {
                    System.out.println(name + " хотел взять книгу: " + b.getIndex() + " в читальный зал - но она доступна только для дома");
                }
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void takeBookHome(String name) {
        try {
            for (Book b : libraryBooks) {
                if (!libraryBooks.isEmpty() && !b.isReadingInLibraryOnly) {
                    homeBooks.add(b);
                    libraryBooks.remove(b);
                    System.out.println(name + " взял книгу: " + b.getIndex() + ", домой");
//                    returnBook();
                } else {
                    System.out.println(name + " хотел взять книгу: " + b.getIndex() + " домой - но она доступна только для читального зала");
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void returnBook(String name) {
        try {
            for (Book b : readingBooks) {
                libraryBooks.add(b);
                readingBooks.remove(b);
                System.out.println(name + " вернул книгу: " + b.getIndex());
                Thread.sleep(1000);
            }
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Library{" +
                "books=" + libraryBooks +
                '}';
    }
}
