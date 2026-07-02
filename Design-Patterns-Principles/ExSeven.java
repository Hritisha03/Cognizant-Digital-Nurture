import java.util.ArrayList;

interface Observer {
    void update(double price);
}

interface Stock {
    void register(Observer o);
    void deregister(Observer o);
    void notifyObservers();
}

class StockMarket implements Stock {

    private ArrayList<Observer> observers = new ArrayList<>();
    private double stockPrice;

    public void register(Observer o) {
        observers.add(o);
    }

    public void deregister(Observer o) {
        observers.remove(o);
    }

    public void setPrice(double price) {
        stockPrice = price;
        notifyObservers();
    }

    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(stockPrice);
        }
    }
}

class MobileApp implements Observer {

    public void update(double price) {
        System.out.println("Mobile App: Stock Price = ₹" + price);
    }
}

class WebApp implements Observer {

    public void update(double price) {
        System.out.println("Web App: Stock Price = ₹" + price);
    }
}

public class ExSeven {

    public static void main(String[] args) {

        StockMarket market = new StockMarket();

        Observer mobile = new MobileApp();
        Observer web = new WebApp();

        market.register(mobile);
        market.register(web);

        market.setPrice(2500);

        System.out.println();

        market.setPrice(2750);
    }
}