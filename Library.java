import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Library implements Serializable {

    private static final long serialVersionUID = 1L;

    private ArrayList<Book> books;

    // Constructor
    public Library() {
        books = new ArrayList<>();
    }

    // ==========================
    // Add Book
    // ==========================
    public boolean addBook(Book book) {

    if (searchByISBN(book.getISBN()) != null) {
        return false; // ISBN already exists
    }

    books.add(book);
    return true;
}

    // ==========================
    // Get All Books
    // ==========================
    public ArrayList<Book> getBooks() {
        return books;
    }

    // ==========================
    // Search by ISBN
    // ==========================
    public Book searchByISBN(String isbn) {

        for (Book b : books) {

            if (b.getISBN().equalsIgnoreCase(isbn)) {
                return b;
            }

        }

        return null;
    }

    // ==========================
    // Search by Title
    // ==========================
    public Book searchByTitle(String title) {

        for (Book b : books) {

            if (b.getTitle().equalsIgnoreCase(title)) {
                return b;
            }

        }

        return null;
    }

    // ==========================
    // Delete Book
    // ==========================
    public boolean deleteBook(String isbn) {

        Book book = searchByISBN(isbn);

        if (book != null) {

            books.remove(book);
            return true;

        }

        return false;
    }

    // ==========================
    // Update Book
    // ==========================
    public boolean updateBook(String isbn,
                              String title,
                              String author,
                              String category,
                              int quantity) {

        Book book = searchByISBN(isbn);

        if (book == null)
            return false;

        book.setTitle(title);
        book.setAuthor(author);
        book.setCategory(category);
        book.setQuantity(quantity);

        return true;
    }

    // ==========================
    // Issue Book
    // ==========================
    public boolean issueBook(String isbn) {

        Book book = searchByISBN(isbn);

        if (book == null)
            return false;

        return book.issueBook();
    }

    // ==========================
    // Return Book
    // ==========================
    public boolean returnBook(String isbn) {

        Book book = searchByISBN(isbn);

        if (book == null)
            return false;

        book.returnBook();

        return true;
    }

    // ==========================
    // Sort by Title
    // ==========================
    public void sortBooks() {

        Collections.sort(books, Comparator.comparing(Book::getTitle));

    }

    // ==========================
    // Total Books
    // ==========================
    public int getTotalBooks() {

        return books.size();

    }

    // ==========================
    // Available Copies
    // ==========================
    public int getAvailableBooks() {

    int count = 0;

    for (Book b : books) {

        if (b.getQuantity() > 0) {

            count++;

        }

    }

    return count;

}

    // ==========================
    // Issued Books
    // ==========================
    public int getIssuedBooks() {

    int count = 0;

    for (Book b : books) {

        if (b.getQuantity() == 0) {

            count++;

        }

    }

    return count;

}

    // ==========================
    // Most Popular Book
    // ==========================
    public Book getMostPopularBook() {

        if (books.isEmpty())
            return null;

        Book popular = books.get(0);

        for (Book b : books) {

            if (b.getIssueCount() > popular.getIssueCount()) {

                popular = b;

            }

        }

        return popular;
    }

    // ==========================
    // Low Stock
    // ==========================
    public ArrayList<Book> getLowStockBooks() {

        ArrayList<Book> lowStock = new ArrayList<>();

        for (Book b : books) {

            if (b.getQuantity() <= 2) {

                lowStock.add(b);

            }

        }

        return lowStock;
    }

}