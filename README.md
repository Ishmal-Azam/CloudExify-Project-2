# CloudExify-Project-2

# 📚 Smart Library Manager

A desktop-based **Library Management System** developed in **Java Swing**. This application provides an easy-to-use graphical interface for managing books, issuing and returning books, maintaining inventory, and storing records using file handling.

---

## Project Overview

Smart Library Manager is designed to simplify library operations by allowing users to manage books through an interactive GUI. The application supports adding, updating, deleting, searching, issuing, returning, and sorting books while maintaining persistent data storage.

---

## Features

- Add new books
- Update existing book details
- Delete books
- Search books by ISBN or Title
- Issue books
- Return books
- Sort books alphabetically
- View library statistics
  - Total Books
  - Available Books
  - Issued Books
- Low stock detection
- Most popular book tracking
- Automatic data saving using serialization
- Modern Java Swing graphical interface

---

## Technologies Used

- Java
- Java Swing
- Object-Oriented Programming (OOP)
- Collections Framework
- File Handling
- Serialization

---

## Project Structure

```
SmartLibraryManager/
│
├── Book.java
├── Library.java
├── LibraryGUI.java
├── FileManager.java
├── library.dat
└── README.md
```

---

## Classes Description

### Book.java
Represents a single book in the library.

Functions include:
- Store book information
- Issue and return books
- Maintain issue count
- Getters and setters

---

### Library.java

Handles all library operations.

Functions include:
- Add Book
- Delete Book
- Update Book
- Search by ISBN
- Search by Title
- Issue Book
- Return Book
- Sort Books
- Library Statistics
- Low Stock Detection

---

### LibraryGUI.java

Provides the graphical user interface using Java Swing.

Features include:
- Interactive buttons
- Book information form
- JTable for displaying records
- Status bar
- Dialog messages
- Automatic table refresh

---

### FileManager.java

Responsible for data persistence.

Functions:
- Save library data
- Load library data
- Maintain records after application restart

---

## Data Storage

The project uses Java Serialization to save and load library records automatically.

Data is stored inside:

```
library.dat
```

This allows the application to preserve records even after closing.

---

## How to Run

1. Open the project in **VS Code**, **IntelliJ IDEA**, or **NetBeans**.
2. Make sure all Java source files are in the same project.
3. Compile the project.
4. Run:

```
LibraryGUI.java
```

5. The Smart Library Manager window will open.

---

## Main Functionalities

- Add a new book
- Search books
- Update records
- Delete records
- Issue books
- Return books
- Sort books
- Display statistics
- Low stock warning
- Persistent data storage

---

##  Learning Outcomes

This project demonstrates practical implementation of:

- Object-Oriented Programming
- Java Swing GUI Development
- Event Handling
- Collections Framework
- File Handling
- Serialization
- Exception Handling
- Software Design Principles

---

## Future Improvements

- User Login System
- MySQL Database Integration
- Barcode Scanner Support
- Fine Calculation
- Book Reservation
- Due Date Management
- PDF Report Generation
- Dark Mode
- Multi-user Support

---

## Author

**Ishmal Azam**

Software Engineering Student  
COMSATS University Islamabad, Wah Campus

---
## GUI Screenshots
<img width="1032" height="639" alt="11" src="https://github.com/user-attachments/assets/9a6c8964-eb8e-4718-af99-ae3f2ef0df45" />

## Category
<img width="246" height="162" alt="Screenshot 2026-07-29 144125" src="https://github.com/user-attachments/assets/2d71540f-35ae-4c1e-b55d-fb6ee5f6d8dd" />

## Buttons
<img width="284" height="146" alt="22" src="https://github.com/user-attachments/assets/75c2fc19-77c1-4758-a9e0-ff3fa20e99a0" />
<img width="288" height="150" alt="3" src="https://github.com/user-attachments/assets/961a469e-cf50-43c0-8b30-8a44fc0b0657" />
<img width="296" height="163" alt="4" src="https://github.com/user-attachments/assets/5d8d8f9f-3a12-40bc-a093-d365bfd7dbb2" />
