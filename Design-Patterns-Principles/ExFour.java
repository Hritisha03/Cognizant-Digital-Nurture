interface PaymentProcessor {
    void processPayment(double amount);
}

class PayPalGateway {
    void makePayment(double amount) {
        System.out.println("Paid ₹" + amount + " using PayPal");
    }
}

class StripeGateway {
    void pay(double amount) {
        System.out.println("Paid ₹" + amount + " using Stripe");
    }
}

class PayPalAdapter implements PaymentProcessor {

    private PayPalGateway paypal = new PayPalGateway();

    public void processPayment(double amount) {
        paypal.makePayment(amount);
    }
}

class StripeAdapter implements PaymentProcessor {

    private StripeGateway stripe = new StripeGateway();

    public void processPayment(double amount) {
        stripe.pay(amount);
    }
}

public class ExFour {

    public static void main(String[] args) {

        PaymentProcessor payment1 = new PayPalAdapter();
        payment1.processPayment(2500);

        PaymentProcessor payment2 = new StripeAdapter();
        payment2.processPayment(5000);
    }
}