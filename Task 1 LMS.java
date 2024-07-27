import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private boolean isCheckedOut;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isCheckedOut = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public void checkOut() {
        isCheckedOut = true;
    }

    public void returnBook() {
        isCheckedOut = false;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", status=" + (isCheckedOut ? "Checked Out" : "Available") +
                '}';
    }
}

class LibraryCatalog {
    private ArrayList<Book> books;

    public LibraryCatalog() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public ArrayList<Book> searchByTitle(String title) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                result.add(book);
            }
        }
        return result;
    }

    public ArrayList<Book> searchByAuthor(String author) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }
        return result;
    }

    public boolean checkOutBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && !book.isCheckedOut()) {
                book.checkOut();
                return true;
            }
        }
        return false;
    }

    public boolean returnBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && book.isCheckedOut()) {
                book.returnBook();
                return true;
            }
        }
        return false;
    }

    public void listBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        LibraryCatalog catalog = new LibraryCatalog();
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Welcome to the Library Management System");
        while (true) {
            System.out.println("\nEnter a command: add, search, checkout, return, list, or exit");
            command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "add":
                    System.out.println("Enter the book title:");
                    String title = scanner.nextLine().trim();
                    System.out.println("Enter the book author:");
                    String author = scanner.nextLine().trim();
                    catalog.addBook(new Book(title, author));
                    System.out.println("Book added successfully.");
                    break;
                case "search":
                    System.out.println("Search by title or author?");
                    String searchType = scanner.nextLine().trim().toLowerCase();
                    if (searchType.equals("title")) {
                        System.out.println("Enter the book title:");
                        String searchTitle = scanner.nextLine().trim();
                        ArrayList<Book> titleResults = catalog.searchByTitle(searchTitle);
                        System.out.println("Search results:");
                        for (Book book : titleResults) {
                            System.out.println(book);
                        }
                    } else if (searchType.equals("author")) {
                        System.out.println("Enter the book author:");
                        String searchAuthor = scanner.nextLine().trim();
                        ArrayList<Book> authorResults = catalog.searchByAuthor(searchAuthor);
                        System.out.println("Search results:");
                        for (Book book : authorResults) {
                            System.out.println(book);
                        }
                    } else {
                        System.out.println("Invalid search type.");
                    }
                    break;
                case "checkout":
                    System.out.println("Enter the book title to check out:");
                    String checkoutTitle = scanner.nextLine().trim();
                    if (catalog.checkOutBook(checkoutTitle)) {
                        System.out.println("Book checked out successfully.");
                    } else {
                        System.out.println("Book could not be checked out.");
                    }
                    break;
                case "return":
                    System.out.println("Enter the book title to return:");
                    String returnTitle = scanner.nextLine().trim();
                    if (catalog.returnBook(returnTitle)) {
                        System.out.println("Book returned successfully.");
                    } else {
                        System.out.println("Book could not be returned.");
                    }
                    break;
                case "list":
                    System.out.println("Listing all books:");
                    catalog.listBooks();
                    break;
                case "exit":
                    System.out.println("Exiting the Library Management System. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }
    }
}
