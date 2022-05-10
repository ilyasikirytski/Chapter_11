package A_2;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;

public class Library {
    private final Semaphore semaphore = new Semaphore(1);
    private final CopyOnWriteArrayList<Book> libraryBooks = new CopyOnWriteArrayList<>();

    public void addBook(Book book) {
        this.libraryBooks.add(book);
    }

    public void takeBook(Reader reader, String... namesOfBook) {
        String readerName = Thread.currentThread().getName();
        try {
            for (Book book : libraryBooks) {
                for (String nameOfBook : namesOfBook) {
                    if (book.getName().equals(nameOfBook)) {
                        if (!book.isTaken) {
                            takeConcreteBook(reader, readerName, book, book.isReadingInLibraryOnly);
                        } else {
                            printBookIsTaken(readerName, book);
                        }
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void takeConcreteBook(Reader reader, String readerName, Book book, boolean isReadingInLibraryOnly) throws InterruptedException {
        semaphore.acquire();
        if (isReadingInLibraryOnly) {
            System.out.println(readerName + " взял " + book.getName() + " в зал");
        } else {
            System.out.println(readerName + " взял " + book.getName() + ", домой");
        }
        book.isTaken = true;
        libraryBooks.remove(book);
        reader.readerBooks.add(book);
        semaphore.release();
        Thread.sleep(3000);
    }

    public void returnBook(Reader reader) throws InterruptedException {
        String readerName = Thread.currentThread().getName();
        for (Book b : reader.readerBooks) {
            semaphore.acquire();
            b.isTaken = false;
            libraryBooks.add(b);
            reader.readerBooks.remove(b);
            System.out.println(readerName + " вернул книгу: " + b.getName());
            semaphore.release();
        }
    }

    private void printBookIsTaken(String readerName, Book book) throws InterruptedException {
        semaphore.acquire();
        System.out.println(readerName + " хотел взять " + book.getName() + " но она занята");
        semaphore.release();
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
