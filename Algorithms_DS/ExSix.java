import java.util.Arrays;
import java.util.Comparator;

class Book {

    int bookId;
    String title;
    String author;

    Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    void display() {
        System.out.println(bookId + " | " + title + " | " + author);
    }
}

public class ExSix {

    // Linear Search
    static void linearSearch(Book[] books, String key) {

        for (Book b : books) {

            if (b.title.equalsIgnoreCase(key)) {
                System.out.println("\nBook Found using Linear Search:");
                b.display();
                return;
            }
        }

        System.out.println("Book Not Found.");
    }

    // Binary Search
    static void binarySearch(Book[] books, String key) {

        int low = 0;
        int high = books.length - 1;

        while (low <= high) {

            int mid = (low + high) / 2;

            int result = key.compareToIgnoreCase(books[mid].title);

            if (result == 0) {
                System.out.println("\nBook Found using Binary Search:");
                books[mid].display();
                return;
            }
            else if (result < 0) {
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }

        System.out.println("Book Not Found.");
    }

    public static void main(String[] args) {

        Book[] books = {

            new Book(101, "Java Programming", "James Gosling"),
            new Book(102, "Data Structures", "Mark Allen"),
            new Book(103, "Operating Systems", "Galvin"),
            new Book(104, "Computer Networks", "Andrew Tanenbaum"),
            new Book(105, "Database Systems", "Elmasri")

        };

        System.out.println("===== Linear Search =====");
        linearSearch(books, "Operating Systems");

        // Sort before Binary Search
        Arrays.sort(books, Comparator.comparing(b -> b.title));

        System.out.println("\n===== Binary Search =====");
        binarySearch(books, "Operating Systems");
    }
}