package A_2;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;

public class Library {
    private final Semaphore semaphore = new Semaphore(1);
    private final CopyOnWriteArrayList<Book> libraryBooks = new CopyOnWriteArrayList<>();

    public void addBook(Book book) {
        this.libraryBooks.add(book);
    }

    /*
    добавить выбор книги
     */
    /*
    сделать так что бы они могли брать несколько книг одновременно(не блокировать всюбиблиотеку)
    избавиться от дублирования
     */
    public void takeBook(String nameOfBook) {
        try {
            String readerName = Thread.currentThread().getName();
            for (Book book : libraryBooks) {
                if (book.getName().equals(nameOfBook)) {
                    if (book.isReadingInLibraryOnly) {
                        if (!book.isTaken) {
                            semaphore.acquire();
                            book.isTaken = true;
                            System.out.println(readerName + " взял " + book.getName() + " в зал");
                            Thread.sleep(10000);
                            book.isTaken = false;
                            System.out.println(readerName + " вернул книгу: " + book.getName());
                            Thread.sleep(1000);
//                            semaphore.release();
                        } else {
                            System.out.println(readerName + " хотел взять " + book.getName() + " но она занята");
                        }
                    } else {
                        System.out.println(readerName + " хотел взять " + book.getName() + " в зал - но она доступна только для дома");
                        if (!book.isTaken) {
//                            semaphore.acquire();
                            book.isTaken = true;
                            System.out.println(readerName + " взял " + book.getName() + ", домой");
                            Thread.sleep(10000);
                            book.isTaken = false;
                            System.out.println(readerName + " вернул книгу: " + book.getName());
                            Thread.sleep(1000);
                            semaphore.release();
                        } else {
                            System.out.println(readerName + " хотел взять " + book.getName() + " но она занята");
                        }
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    private void printTakeBookHome(String readerName, Book book, Location location){
//        if (location == Location.READING_ROOM) {
//            System.out.println(readerName + " взял " + book.getName() + " в зал");
//        } else {
//            System.out.println(readerName + " взял " + book.getName() + ", домой");
//        }
//    }
//
//    private void printReturnBook(String readerName, Book book){
//        System.out.println(readerName + " вернул книгу: " + book.getName());
//    }
//
//    private void printBookIsTaken(String readerName, Book book){
//        System.out.println(readerName + " хотел взять " + book.getName() + " но она занята");
//    }
//
//    private void printBookAvailableHomeOnly(String readerName, Book book, Location location){
//        if (location == Location.READING_ROOM) {
//            System.out.println(readerName + " хотел взять " + book.getName() + " в зал - но она доступна только для дома");
//        } else {
//            System.out.println(readerName + " хотел взять " + book.getName() + " домой - но она доступна только для читального зала");
//
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

    @Override
    public String toString() {
        return "Library{" +
                "books=" + libraryBooks +
                '}';
    }
}
