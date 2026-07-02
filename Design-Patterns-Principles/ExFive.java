interface Notifier {
    void send();
}

class EmailNotifier implements Notifier {

    public void send() {
        System.out.println("Email Notification Sent");
    }
}

abstract class NotifierDecorator implements Notifier {

    protected Notifier notifier;

    NotifierDecorator(Notifier notifier) {
        this.notifier = notifier;
    }
}

class SMSNotifierDecorator extends NotifierDecorator {

    SMSNotifierDecorator(Notifier notifier) {
        super(notifier);
    }

    public void send() {
        notifier.send();
        System.out.println("SMS Notification Sent");
    }
}

class SlackNotifierDecorator extends NotifierDecorator {

    SlackNotifierDecorator(Notifier notifier) {
        super(notifier);
    }

    public void send() {
        notifier.send();
        System.out.println("Slack Notification Sent");
    }
}

public class ExFive {

    public static void main(String[] args) {

        Notifier notifier = new EmailNotifier();

        System.out.println("Email Only:");
        notifier.send();

        System.out.println();

        notifier = new SMSNotifierDecorator(notifier);

        System.out.println("Email + SMS:");
        notifier.send();

        System.out.println();

        notifier = new SlackNotifierDecorator(notifier);

        System.out.println("Email + SMS + Slack:");
        notifier.send();
    }
}