import java.io.Serializable;

public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    private static int nextID = 1001;

    private int bookID;
    private String title;
    private String author;
    private String isbn;
    private String category;
    private int quantity;
    private int issueCount;

    // Constructor
    public Book(String title, String author, String isbn,
                String category, int quantity) {

        this.bookID = nextID++;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.quantity = quantity;
        this.issueCount = 0;
    }
    // Getters
    public int getBookID() {
        return bookID;}
    public String getTitle() {
        return title;}
    public String getAuthor() {
        return author;}
    public String getISBN() {
        return isbn;}
    public String getCategory() {
        return category;}
    public int getQuantity() {
        return quantity;}
    public int getIssueCount() {
        return issueCount;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public static void setNextID(int id) {
    nextID = id;
}
    // Issue Book
    public boolean issueBook() {

        if (quantity > 0) {
            quantity--;
            issueCount++;
            return true;
        }
        return false;
    }
    // Return Book
    public void returnBook() {
        quantity++;
    }
    // Display Book Details
    @Override
    public String toString() {
        return String.format(
                "%-6d %-25s %-20s %-15s %-15s %-5d",
                bookID,
                title,
                author,
                isbn,
                category,
                quantity);
    }
}