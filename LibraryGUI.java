import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


public class LibraryGUI extends JFrame implements ActionListener {

    // Library Object
    private final Library library;

    // Text Fields
    private final JTextField idField = new JTextField();
    private JTextField titleField;
    private JTextField authorField;
    private JTextField isbnField;
    private JTextField quantityField;

    // Combo Box
    private JComboBox<String> categoryBox;

    // Buttons
    private final JButton addButton;
    private final JButton updateButton;
    private final JButton deleteButton;
    private final JButton issueButton;
    private final JButton returnButton;
    private final JButton sortButton;
    private final JButton clearButton;

    // Table
    private JTable table;
    private DefaultTableModel model;

    // Status
    private final JLabel statusLabel;
    public LibraryGUI() {

        // Load Library
        library = FileManager.loadLibrary();
        setTitle("📚 Smart Library Manager");
        setSize(1050, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout(15,15));
        mainPanel.setBorder(new EmptyBorder(15,15,15,15));
        mainPanel.setBackground(new Color(245,247,250));

        // Header
        JLabel heading = new JLabel("Smart Library Manager", SwingConstants.CENTER);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 28));
        heading.setForeground(new Color(25,55,109));

        mainPanel.add(heading, BorderLayout.NORTH);

        // Left Panel
        JPanel leftPanel = new JPanel(new GridLayout(0,2,10,10));
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setBorder(BorderFactory.createTitledBorder("Book Information"));

        var idField = new JTextField();
        titleField = new JTextField();
        authorField = new JTextField();
        isbnField = new JTextField();
        quantityField = new JTextField();
       
        String[] categories = {
                "Programming",
                "Novel",
                "Science",
                "History",
                "Others"
        };
        categoryBox = new JComboBox<>(categories);

        leftPanel.add(new JLabel("ID"));
        leftPanel.add(idField);

        leftPanel.add(new JLabel("Title"));
        leftPanel.add(titleField);

        leftPanel.add(new JLabel("Author"));
        leftPanel.add(authorField);

        leftPanel.add(new JLabel("ISBN"));
        leftPanel.add(isbnField);

        leftPanel.add(new JLabel("Category"));
        leftPanel.add(categoryBox);

        leftPanel.add(new JLabel("Quantity"));
        leftPanel.add(quantityField);

        // Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(4,2,10,10));
        buttonPanel.setBackground(Color.WHITE);

        addButton = createButton("Add");
        updateButton = createButton("Update");
        deleteButton = createButton("Delete");
        issueButton = createButton("Issue");
        returnButton = createButton("Return");
        sortButton = createButton("Sort");
        clearButton = createButton("Clear");

        addButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);
        issueButton.addActionListener(this);
        returnButton.addActionListener(this);
        sortButton.addActionListener(this);
        clearButton.addActionListener(this);

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(issueButton);
        buttonPanel.add(returnButton);
        buttonPanel.add(sortButton);
        buttonPanel.add(clearButton);

        JPanel westPanel = new JPanel(new BorderLayout(10,10));
        westPanel.setBackground(new Color(245,247,250));

        westPanel.add(leftPanel, BorderLayout.CENTER);
        westPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(westPanel, BorderLayout.WEST);

        // Table
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
                "ID",
                "Title",
                "Author",
                "ISBN",
                "Category",
                "Quantity"
        });
        table = new JTable(model);
        table.setSelectionBackground(new Color(25,55,109));
        table.setSelectionForeground(Color.WHITE);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setShowGrid(true);
        table.setFillsViewportHeight(true);
        table.getSelectionModel().addListSelectionListener(e -> {
    int row = table.getSelectedRow();

    if (row >= 0) {
        titleField.setText(model.getValueAt(row,1).toString());
        authorField.setText(model.getValueAt(row,2).toString());
        isbnField.setText(model.getValueAt(row,3).toString());
        categoryBox.setSelectedItem(model.getValueAt(row,4).toString());
        quantityField.setText(model.getValueAt(row,5).toString());
    }
});
        table.setRowHeight(25);
        table.setFont(new Font("Segoe UI", Font.PLAIN,14));

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI",Font.BOLD,15));
        header.setBackground(new Color(25,55,109));
        header.setForeground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Status Bar
        statusLabel = new JLabel(" Ready");
        statusLabel.setFont(new Font("Segoe UI",Font.BOLD,14));
        statusLabel.setBorder(new EmptyBorder(10,5,5,5));
        mainPanel.add(statusLabel, BorderLayout.SOUTH);
        add(mainPanel);
        refreshTable();
        setVisible(true);
}

    // Button Style
    private JButton createButton(String text){
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI",Font.BOLD,14));
        button.setBackground(new Color(25,55,109));
        button.setForeground(Color.WHITE);
        return button;
    }
    @Override
public void actionPerformed(ActionEvent e) {

    // Add Book
            if (e.getSource() == addButton) {

                if(!validateFields())
            return;

            try{
            String title = titleField.getText().trim();
            String author = authorField.getText().trim();
            String isbn = isbnField.getText().trim();
            String category = categoryBox.getSelectedItem().toString();
            int quantity = Integer.parseInt(quantityField.getText());

            if (title.isEmpty() || author.isEmpty() || isbn.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill all fields.");
                return;
            }
            Book book = new Book(title, author, isbn,
                    category, quantity);
           if (!library.addBook(book)) {

    JOptionPane.showMessageDialog(this,
            "ISBN already exists!");
    return;
}
            FileManager.saveLibrary(library);
            refreshTable();
            if(quantity <= 2){
    statusLabel.setText("⚠ Low Stock Book Added");
}
    else{
    statusLabel.setText("✔ Book Added Successfully");
}
            clearFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Invalid Input!");
        }
    }

    // Update
    else if (e.getSource() == updateButton) {
        try {
            boolean updated = library.updateBook(
                    isbnField.getText(),
                    titleField.getText(),
                    authorField.getText(),
                    categoryBox.getSelectedItem().toString(),
                    Integer.parseInt(quantityField.getText())
            );
            if (updated) {
                FileManager.saveLibrary(library);
                refreshTable();
                statusLabel.setText("✔ Book Updated");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Book Not Found");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Invalid Data");
        }
    }

    // Delete
    else if (e.getSource() == deleteButton) {
    int option = JOptionPane.showConfirmDialog(
            this,
            "Delete this book?",
            "Confirm",
            JOptionPane.YES_NO_OPTION);

    if (option == JOptionPane.YES_OPTION) {
        boolean deleted = library.deleteBook(isbnField.getText());

        if (deleted) {
            FileManager.saveLibrary(library);
            refreshTable();
            clearFields();
            statusLabel.setText("✔ Book Deleted");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Book Not Found");
        }
    }
}

    // Issue
    else if (e.getSource() == issueButton) {
        if (library.issueBook(isbnField.getText())) {
            FileManager.saveLibrary(library);
            refreshTable();
            statusLabel.setText("✔ Book Issued");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Book Not Available");
        }
    }

    // Return
    else if (e.getSource() == returnButton) {
        if (library.returnBook(isbnField.getText())) {
            FileManager.saveLibrary(library);
            refreshTable();
            statusLabel.setText("✔ Book Returned");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Book Not Found");
        }
    }

    // Sort
    else if (e.getSource() == sortButton) {
        library.sortBooks();
        refreshTable();
        statusLabel.setText("✔ Books Sorted");
    }

    // Clear
    else if (e.getSource() == clearButton) {
        clearFields();
    }
}

// Refresh JTable
    private void refreshTable() {
    model.setRowCount(0);
    for (Book b : library.getBooks()) {

        model.addRow(new Object[]{
                b.getBookID(),
                b.getTitle(),
                b.getAuthor(),
                b.getISBN(),
                b.getCategory(),
                b.getQuantity()
        });
    }
    updateStatistics();
}
// Clear Input Fields
    private void clearFields() {
    titleField.setText("");
    authorField.setText("");
    isbnField.setText("");
    quantityField.setText("");
    categoryBox.setSelectedIndex(0);
    titleField.requestFocus();
}

// Update
    private void updateStatistics() {
    int total = library.getTotalBooks();
    int available = library.getAvailableBooks();
    int issued = library.getIssuedBooks();
    statusLabel.setText(
            "Total Books : " + total +
            "     Available : " + available +
            "     Issued : " + issued);
}
private boolean validateFields() {

    if(titleField.getText().trim().isEmpty()
            || authorField.getText().trim().isEmpty()
            || isbnField.getText().trim().isEmpty()
            || quantityField.getText().trim().isEmpty()) {

        JOptionPane.showMessageDialog(this,
                "Please fill all fields.");
        return false;
    }
    return true;
}
    // Main Method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryGUI());
    }
}