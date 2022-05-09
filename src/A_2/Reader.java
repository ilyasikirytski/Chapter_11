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
            library.takeBook("HomeBook1");
            library.takeBook("LibraryBook1");
//            library.takeBook("LibraryBook1", Location.READING_ROOM);
//            library.takeBook("LibraryBook2", Location.READING_ROOM);
//            library.takeBook("HomeBook1", Location.HOME);
//            library.takeBook("HomeBook2", Location.HOME);
//            library.takeBook("LibraryBook1", Location.HOME);
//            library.takeBook("LibraryBook2", Location.HOME);
//            library.takeBookHome("HomeBook1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

