package A_2;

import java.util.concurrent.CopyOnWriteArrayList;

public class Library {

    CopyOnWriteArrayList<Book> books = new CopyOnWriteArrayList<>();

    public void addBook(Book book) {
        this.books.add(book);
    }

    @Override
    public String toString() {
        return "Library{" +
                "books=" + books +
                '}';
    }
}
