/*
Маленькая библиотека. Доступны для чтения несколько книг. Одинаковых
книг в библиотеке нет. Некоторые выдаются на руки, некоторые только
в читальный зал. Читатель может брать на руки и в читальный зал несколько книг
 */
package A_2;

public class Main {
    public static void main(String[] args) {

        Library library = new Library();
        library.addBook(new Book(false, "a", 1));
        library.addBook(new Book(true, "b", 2));
        library.addBook(new Book(false, "c", 3));
        library.addBook(new Book(true, "d", 4));

        for (Book b : library.libraryBooks) {
            System.out.printf("%s-%s; \n", b.getIndex(), b.getName());
        }

        new Thread(new Reader(library, "Читатель_1")).start();
        new Thread(new Reader(library, "Читатель_2")).start();
        new Thread(new Reader(library, "Читатель_3")).start();
    }
}
