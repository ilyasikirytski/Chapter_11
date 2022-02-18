package A_2;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;

public class Reader implements Runnable {
    private final Semaphore semaphore;
    private final String name;
    private final Library library;
    private final CopyOnWriteArrayList<Book> books = new CopyOnWriteArrayList<>();

    public Reader(Semaphore semaphore, Library library, String name) {
        this.semaphore = semaphore;
        this.library = library;
        this.name = name;
    }

    private void takeBookToReadingRoom() {
        try {
            for (Book b : library.books) {
                if (b.isReadingInLibraryOnly) {
                    this.books.add(b);
                    library.books.remove(b);
                    System.out.println(name + " взял книгу: " + b.getIndex() + ", в читальный зал");
                    if (this.books.size() >= 2) {
                        returnBook();
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

    private void takeBookHome() {
        try {
            for (Book b : library.books) {
                if (!b.isReadingInLibraryOnly) {
                    this.books.add(b);
                    library.books.remove(b);
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

    private void returnBook() {
        try {
            for (Book b : books) {
                library.books.add(b);
                this.books.remove(b);
                System.out.println(name + " вернул книгу: " + b.getIndex());
                Thread.sleep(1000);
            }
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            takeBookToReadingRoom();
            takeBookHome();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}

