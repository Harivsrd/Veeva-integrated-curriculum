package digital_library_management;

import java.util.*;
import java.util.stream.Collectors;

public class LibraryCatalog {

    private Set<Book> books;

    public LibraryCatalog() {
        books = new HashSet<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> searchBooks(String keyword) {
        return books.stream()
                .filter(book -> book.matchesKeyword(keyword))
                .sorted(Comparator.comparingInt((Book book) -> book.getBorrowCount())
                .reversed())
                .collect(Collectors.toList());
    }


    public Optional<Book> getMostBorrowedBook() {
        return books.stream()
                .max(Comparator.comparingInt((Book book) -> book.getBorrowCount()));
    }

    public static void main(String[] args) {

        LibraryCatalog catalog = new LibraryCatalog();

        catalog.addBook(new Book("978-0134685991", "Effective Java", "Joshua Bloch",150));

        catalog.addBook(new Book("978-0134685991", "Effective Java 3rd Ed", "Joshua Bloch", 200)); // Duplicate ISBN

        catalog.addBook(new Book("978-0596009205", "Head First Java", "Kathy Sierra", 320));

        System.out.println("Search Result:");

        catalog.searchBooks("java")
                .forEach(book -> System.out.println(book.getTitle()+"\t("+book.getBorrowCount()+")"));

        System.out.println();
        catalog.getMostBorrowedBook()
                .ifPresent(book ->
                        System.out.println("Most Borrowed Book: " + book.getTitle()));
    }
}
