package A_2;

public class Reader implements Runnable {
    private final String name;
    private final Library library;
//    private final CopyOnWriteArrayList<Book> books = new CopyOnWriteArrayList<>();

    public Reader(Library library, String name) {
        this.library = library;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Library.SEMAPHORE.acquire();
            library.takeBookToReadingRoom(name);
            library.takeBookHome(name);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Library.SEMAPHORE.release();
        }
    }
}

