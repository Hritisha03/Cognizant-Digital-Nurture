import java.util.Arrays;
import java.util.Comparator;

class Product {
    int productId;
    String productName;
    String category;

    Product(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    void display() {
        System.out.println("ID: " + productId);
        System.out.println("Product: " + productName);
        System.out.println("Category: " + category);
        System.out.println("---------------------");
    }
}

public class extwo {

    // Linear Search
    static void linearSearch(Product[] products, String key) {

        boolean found = false;

        for (Product p : products) {
            if (p.productName.equalsIgnoreCase(key)) {
                System.out.println("Product Found using Linear Search:");
                p.display();
                found = true;
                break;
            }
        }

        if (!found)
            System.out.println("Product Not Found.");
    }

    // Binary Search
    static void binarySearch(Product[] products, String key) {

        int low = 0;
        int high = products.length - 1;

        while (low <= high) {

            int mid = (low + high) / 2;

            int result = key.compareToIgnoreCase(products[mid].productName);

            if (result == 0) {
                System.out.println("Product Found using Binary Search:");
                products[mid].display();
                return;
            } else if (result < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        System.out.println("Product Not Found.");
    }

    public static void main(String[] args) {

        Product[] products = {
                new Product(101, "Laptop", "Electronics"),
                new Product(102, "Mouse", "Accessories"),
                new Product(103, "Keyboard", "Accessories"),
                new Product(104, "Monitor", "Electronics"),
                new Product(105, "Printer", "Office")
        };

        System.out.println("===== Linear Search =====");
        linearSearch(products, "Keyboard");

        // Sort before Binary Search
        Arrays.sort(products, Comparator.comparing(p -> p.productName));

        System.out.println("\n===== Binary Search =====");
        binarySearch(products, "Keyboard");
    }
}