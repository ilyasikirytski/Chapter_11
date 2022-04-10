package A_2;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;

public class Library {
    private final Semaphore semaphore = new Semaphore(5);
    CopyOnWriteArrayList<Book> libraryBooks = new CopyOnWriteArrayList<>();

    public void addBook(Book book) {
        this.libraryBooks.add(book);
    }

    public void takeBookToReadingRoom() {
        try {
            semaphore.acquire();
            String readerName = Thread.currentThread().getName();
            for (Book book : libraryBooks) {
                if (book.isReadingInLibraryOnly) {
                    if (!book.isTaken) {
                        book.isTaken = true;
                        System.out.println(readerName + " взял " + book.getName() + " в зал");
                        Thread.sleep(1000);
                        returnBook();
                        Thread.sleep(1000);
                    } else {
                        System.out.println(readerName + " хотел взять " + book.getName() + " но она занята");
                    }
                } else {
                    System.out.println(readerName + " хотел взять " + book.getName() + " в зал - но она доступна только для дома");
                }
            }
            semaphore.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void takeBookHome() {
        try {
            semaphore.acquire();
            String readerName = Thread.currentThread().getName();
            for (Book book : libraryBooks) {
                if (!book.isReadingInLibraryOnly) {
                    if (!book.isTaken) {
                        book.isTaken = true;
                        System.out.println(readerName + " взял " + book.getName() + ", домой");
                        Thread.sleep(1000);
                        returnBook();
                        Thread.sleep(1000);
                    } else {
                        System.out.println(readerName + " хотел взять " + book.getName() + " но она занята");
                    }
                } else {
                    System.out.println(readerName + " хотел взять " + book.getName() + " домой - но она доступна только для читального зала");
                }
            }
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void returnBook() {
        try {
            semaphore.acquire();
            String readerName = Thread.currentThread().getName();
            for (Book book : libraryBooks) {
                if (book.isTaken) {
                    book.isTaken = false;
                    System.out.println(readerName + " вернул книгу: " + book.getName());
                }
            }
            semaphore.release();
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
