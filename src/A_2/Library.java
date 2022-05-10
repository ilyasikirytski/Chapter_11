package A_2;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;

public class Library {
    private final Semaphore semaphore = new Semaphore(1);
    private final CopyOnWriteArrayList<Book> libraryBooks = new CopyOnWriteArrayList<>();

    public void addBook(Book book) {
        this.libraryBooks.add(book);
    }

//    как сделать чтобы читатель брал несколько книг?
    public void takeBook(String nameOfBook) {
        try {
            String readerName = Thread.currentThread().getName();
            for (Book book : libraryBooks) {
                if (book.getName().equals(nameOfBook)) {
                    if (book.isReadingInLibraryOnly) {
                        if (!book.isTaken) {
                            semaphore.acquire();
                            takeConcreteBook(readerName, book, book.isReadingInLibraryOnly);
                        } else {
                            printBookIsTaken(readerName, book);
                        }
                    } else {
                        if (!book.isTaken) {
                            takeConcreteBook(readerName, book, false);
                        } else {
                            printBookIsTaken(readerName, book);
                            semaphore.release();
                        }
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void takeConcreteBook(String readerName, Book book, boolean isReadingInLibraryOnly) throws InterruptedException {
        book.isTaken = true;
        if (isReadingInLibraryOnly) {
            System.out.println(readerName + " взял " + book.getName() + " в зал");
        } else {
            System.out.println(readerName + " взял " + book.getName() + ", домой");
        }
        Thread.sleep(3000);
        book.isTaken = false;
        printReturnBook(readerName, book);
        Thread.sleep(1000);
    }

    private void printReturnBook(String readerName, Book book) {
        System.out.println(readerName + " вернул книгу: " + book.getName());
    }

    private void printBookIsTaken(String readerName, Book book) {
        System.out.println(readerName + " хотел взять " + book.getName() + " но она занята");
    }

    @Override
    public String toString() {
        return "Library{" +
                "books=" + libraryBooks +
                '}';
    }
}
//    private void printBookAvailableHomeOnly(String readerName, Book book, Location location) throws InterruptedException {
//        if (location == Location.READING_ROOM) {
//            System.out.println(readerName + " хотел взять " + book.getName() + " домой - но она доступна только для читального зала");
//        } else {
//            System.out.println(readerName + " хотел взять " + book.getName() + " в зал - но она доступна только для дома");
//        }
//    }

//    public void takeBookHome(String nameOfBook) {
//        try {
//            String readerName = Thread.currentThread().getName();
//            for (Book book : libraryBooks) {
//                if (book.getName().equals(nameOfBook)) {
//                    if (!book.isReadingInLibraryOnly) {
//                        if (!book.isTaken) {
//                            semaphore.acquire();
//                            book.isTaken = true;
//                            System.out.println(readerName + " взял " + book.getName() + ", домой");
//                            Thread.sleep(10000);
//                            book.isTaken = false;
//                            System.out.println(readerName + " вернул книгу: " + book.getName());
//                            Thread.sleep(1000);
//                            semaphore.release();
//                        } else {
//                            System.out.println(readerName + " хотел взять " + book.getName() + " но она занята");
//                        }
//                    } else {
//                        System.out.println(readerName + " хотел взять " + book.getName() + " домой - но она доступна только для читального зала");
//                    }
//                }
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//    }
//}
