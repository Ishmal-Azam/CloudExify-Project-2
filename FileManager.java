import java.io.*;

public class FileManager {
    private static final String FILE_NAME = "library.dat";

    // Save Library
    public static void saveLibrary(Library library) {

        try {
            ObjectOutputStream out =
                    new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            out.writeObject(library);
            out.close();
            System.out.println("Library saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving library!");
        }
    }
    // Load Library
    public static Library loadLibrary() {
        try {
            ObjectInputStream in =
                    new ObjectInputStream(new FileInputStream(FILE_NAME));
            Library library = (Library) in.readObject();
        int maxID = 1000;

    for (Book b : library.getBooks()) {
        if (b.getBookID() > maxID) {
        maxID = b.getBookID();
    }
}
        Book.setNextID(maxID + 1);
            in.close();
            System.out.println("Library loaded successfully.");
            return library;
        }
        catch (FileNotFoundException e) {
            System.out.println("No previous library found.");
            return new Library();
        }
        // Error while loading
        catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading library.");
            return new Library();
        }
    }
}