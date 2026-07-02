import java.util.HashMap;

class Product {
    int productId;
    String productName;
    int quantity;
    double price;

    Product(int productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    void display() {
        System.out.println("ID: " + productId);
        System.out.println("Name: " + productName);
        System.out.println("Quantity: " + quantity);
        System.out.println("Price: ₹" + price);
        System.out.println("-------------------------");
    }
}


public class Main {

    static HashMap<Integer, Product> inventory = new HashMap<>();

    static void addProduct(Product p) {
        inventory.put(p.productId, p);
        System.out.println("Product Added!");
    }

    static void updateProduct(int id, int quantity, double price) {
        Product p = inventory.get(id);

        if (p != null) {
            p.quantity = quantity;
            p.price = price;
            System.out.println("Product Updated!");
        } else {
            System.out.println("Product Not Found!");
        }
    }

    static void deleteProduct(int id) {
        if (inventory.remove(id) != null)
            System.out.println("Product Deleted!");
        else
            System.out.println("Product Not Found!");
    }

    static void displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is Empty.");
            return;
        }

        for (Product p : inventory.values()) {
            p.display();
        }
    }

    public static void main(String[] args) {

        // Add Products
        addProduct(new Product(101, "Laptop", 10, 55000));
        addProduct(new Product(102, "Mouse", 50, 700));
        addProduct(new Product(103, "Keyboard", 25, 1200));

        System.out.println("\nInventory:");
        displayInventory();

        // Update Product
        updateProduct(102, 60, 650);

        System.out.println("\nInventory After Update:");
        displayInventory();

        // Delete Product
        deleteProduct(101);

        System.out.println("\nInventory After Deletion:");
        displayInventory();
    }
}