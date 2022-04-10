/*
Маленькая библиотека. Доступны для чтения несколько книг. Одинаковых
книг в библиотеке нет. Некоторые выдаются на руки, некоторые только
в читальный зал. Читатель может брать на руки и в читальный зал несколько книг
 */
package A_2;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        library.addBook(new Book(true, "LibraryBook1"));
        library.addBook(new Book(false, "HomeBook1"));
        library.addBook(new Book(true, "LibraryBook2"));
        library.addBook(new Book(false, "HomeBook2"));

        for (int i = 0; i < 5; i++) {
            new Reader(library, "" + i).start();
        }
    }
}
