package A_2;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;

public class Library {
    private final Semaphore semaphore = new Semaphore(1);
    private final CopyOnWriteArrayList<Book> libraryBooks = new CopyOnWriteArrayList<>();

    public void addBook(Book book) {
        this.libraryBooks.add(book);
    }

    public void takeBook(Reader reader, List<String> namesOfBook) {
        String readerName = Thread.currentThread().getName();
        for (Book book : libraryBooks) {
            boolean isLibraryHasBook = namesOfBook.contains(book.getName());
            if (isLibraryHasBook && !book.isTaken) {
                takeConcreteBook(reader, readerName, book);
            } else if (isLibraryHasBook) {
                printBookIsTaken(readerName, book);
            }
        }
    }

    private void takeConcreteBook(Reader reader, String readerName, Book book) {
        try {
            semaphore.acquire();
            if (book.isReadingInLibraryOnly) {
                System.out.println(readerName + " взял " + book.getName() + " в зал");
            } else {
                System.out.println(readerName + " взял " + book.getName() + ", домой");
            }
            book.isTaken = true;
            libraryBooks.remove(book);
            reader.addBooks(book);
            semaphore.release();
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void returnBook(CopyOnWriteArrayList<Book> books) throws InterruptedException {
        String readerName = Thread.currentThread().getName();
        for (Book book : books) {
            semaphore.acquire();
            book.isTaken = false;
            libraryBooks.add(book);
            books.remove(book);
            System.out.println(readerName + " вернул книгу: " + book.getName());
            semaphore.release();
        }
    }

    private void printBookIsTaken(String readerName, Book book) {
        try {
            semaphore.acquire();
            System.out.println(readerName + " хотел взять " + book.getName() + " но она занята");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
