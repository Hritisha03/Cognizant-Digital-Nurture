class Order {
    int orderId;
    String customerName;
    double totalPrice;

    Order(int orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }

    void display() {
        System.out.println(orderId + " | " + customerName + " | ₹" + totalPrice);
    }
}

public class exthree {

    static void displayOrders(Order[] orders) {
        for (Order o : orders) {
            o.display();
        }
    }

    // Bubble Sort
    static void bubbleSort(Order[] orders) {
        int n = orders.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].totalPrice > orders[j + 1].totalPrice) {
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }

    // Quick Sort
    static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);

            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }

    static int partition(Order[] orders, int low, int high) {

        double pivot = orders[high].totalPrice;
        int i = low - 1;

        for (int j = low; j < high; j++) {

            if (orders[j].totalPrice < pivot) {
                i++;

                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }

        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;

        return i + 1;
    }

    public static void main(String[] args) {

        Order[] orders1 = {
                new Order(101, "Amit", 3500),
                new Order(102, "Riya", 1200),
                new Order(103, "John", 5000),
                new Order(104, "Sara", 2500)
        };

        System.out.println("Original Orders:");
        displayOrders(orders1);

        bubbleSort(orders1);

        System.out.println("\nAfter Bubble Sort:");
        displayOrders(orders1);

        Order[] orders2 = {
                new Order(101, "Amit", 3500),
                new Order(102, "Riya", 1200),
                new Order(103, "John", 5000),
                new Order(104, "Sara", 2500)
        };

        quickSort(orders2, 0, orders2.length - 1);

        System.out.println("\nAfter Quick Sort:");
        displayOrders(orders2);
    }
}