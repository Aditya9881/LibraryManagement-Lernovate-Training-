package Manager;

import model.*;
import java.util.ArrayList;

public class LibraryManager {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();

    // Add a new book
    public void addBook(Book book) {
        books.add(book);
    }

    // Add a new user
    public void addUser(User user) {
        users.add(user);
    }

    // Get all users
    public ArrayList<User> getAllUsers() {
        return users;
    }

    // View all books
    public void ViewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in library");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    // Get user by ID
    public User getUserById(int id) {
        for (User u : users) {
            if (u.getUserId() == id) {
                return u;
            }
        }
        return null;
    }

    // Get book by ID
    public Book getBookById(int id) {
        for (Book b : books) {
            if (b.getBookId() == id) {
                return b;
            }
        }
        return null;
    }

    // Search books by title
    public void searchBookByTitle(String keyword) {
        boolean found = false;
        String k = keyword.toLowerCase();

        for (Book book : books) {
            String bTitle = book.getTitle().toLowerCase();
            if (bTitle.contains(k)) {
                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found");
        }
    }

    // Borrow a book
    public void borrowBook(int bookId, int userId) {
        User u = getUserById(userId);
        Book b = getBookById(bookId);

        if (u != null && b != null && b.getAvailableCopies() > 0) {
            b.decreaseCopies();
            u.borrowBook(bookId);
            System.out.println("Book borrowed successfully");
        } else {
            System.out.println("Unable to borrow book");
        }
    }

    // Return a book
    public void returnBook(int bookId, int userId) {
        Book book = getBookById(bookId);
        User user = getUserById(userId);

        if (book != null && user != null) {
            book.increaseCopies();
            user.returnBook(bookId);
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Unable to return book.");
        }
    }

    // View borrowed books by a user
    public void viewBorrowedBooks(int userId) {
        User user = getUserById(userId);

        if (user != null) {
            ArrayList<Integer> borrowedBookIds = user.getBorrowedBookIds();

            if (borrowedBookIds.isEmpty()) {
                System.out.println("You have not borrowed any books.");
                return;
            }

            System.out.println("Your Borrowed Books:");
            for (int bookId : borrowedBookIds) {
                Book book = getBookById(bookId);
                if (book != null) {
                    System.out.println("ID: " + book.getBookId() +
                            ", Title: " + book.getTitle() +
                            ", Author: " + book.getAuthor() +
                            ", Publisher: " + book.getPublisher() +
                            ", Year: " + book.getYear());
                }
            }
        } else {
            System.out.println("User not found.");
        }
    }
}
