interface PaymentStrategy {
    void pay(double amount);
}

class CreditCardPayment implements PaymentStrategy {

    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " using Credit Card");
    }
}

class PayPalPayment implements PaymentStrategy {

    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " using PayPal");
    }
}

class PaymentContext {

    private PaymentStrategy strategy;

    PaymentContext(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    void makePayment(double amount) {
        strategy.pay(amount);
    }
}

public class ExEight {

    public static void main(String[] args) {

        PaymentContext payment = new PaymentContext(new CreditCardPayment());

        payment.makePayment(2500);

        payment.setStrategy(new PayPalPayment());

        payment.makePayment(5000);
    }
}