package A_2;

public class Reader extends Thread {
    private final Library library;

    public Reader(Library library, String readerName) {
        super(readerName);
        this.library = library;
    }

    @Override
    public void run() {
        try {
            System.out.printf("%s пришёл в билитотеку\n", Thread.currentThread().getName());
            library.takeBookToReadingRoom("LibraryBook1");
            library.takeBookHome("HomeBook1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

