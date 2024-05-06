

import java.util.Date;

public class Subscriber {

    private final int s_id;
    private static int count = 1_000_000;
    private final String name;
    private int age;
    private boolean isActive = true;
    private ServiceProvider s_provider;
    private Invoice invoice;
    private double totalVoiceCallTime = 0;
    private int totalNumberOfMessages = 0;
    private double totalInternetUsage = 0;


    public Subscriber(String name, int age, ServiceProvider s_provider, double usageLimit) {
        this.name = name;
        this.age = age;
        this.s_provider = s_provider;
        invoice = new Invoice(usageLimit, new Date());
        s_id = count++;
        if (s_id > 9_000_000) {
            System.out.println("There is not any available id ");
        }
    }

    public void updateStatus() {
        if (!invoice.IsPaid()) {
            isActive = false;
        }
    }

    public void makeVoiceCall(int minute, Subscriber receiver) {
        updateStatus();
        if (isActive && receiver.IsActive()) {
            double charge = s_provider.calculateVoiceCallCost(minute, this);

            if (!(invoice.isLimitExceeded(charge)) && !(receiver.getInvoice().isLimitExceeded(charge))) {
                System.out.println("Your cannot make that amount of call due to your or receiver's insufficient user limit!");
            } else {
                invoice.addCost(charge);
                receiver.getInvoice().addCost(charge);
                totalVoiceCallTime += minute;
                s_provider.setTotalVoiceCallTime(minute);
            }

        } else {
            System.out.println("Your either did not pay your invoice on time or your usage limit is not sufficient to afford this cost!");
        }
    }

    public void sendMessage(int quantity, Subscriber receiver) {
        updateStatus();
        if (isActive) {
            double charge = s_provider.calculateMessagingCost(quantity, this, receiver);
            if (!invoice.isLimitExceeded(charge)) {
                System.out.println("Your cannot send that amount of message due to your insufficient user limit!");
            } else {
                invoice.addCost(charge);
                totalNumberOfMessages += quantity;
                s_provider.setTotalNumberOfMessages(quantity);
            }

        } else {
            System.out.println("Your either did not pay your invoice on time or your usage limit is not sufficient to afford this cost!");
        }

    }

    public void connectToInternet(double amount) {
        updateStatus();
        if (isActive) {
            double charge = s_provider.calculateInternetCost(amount, this);
            if (!invoice.isLimitExceeded(charge)) {
                System.out.println("Your cannot use that amount of mb due to your insufficient user limit!");
            } else {
                invoice.addCost(charge);
                totalInternetUsage += amount;
                s_provider.setTotalInternetUsage(amount);
            }

        } else {
            System.out.println("Your either did not pay your invoice on time or your usage limit is not sufficient to afford this cost!");
        }
    }

    public void changeServiceProvider(ServiceProvider provider) {
        if (invoice.IsPaid()) {
            s_provider.removeSubscriber(this);
            provider.addSubscriber(this);
            s_provider=provider;
        } else {
            System.out.println("You have to pay your invoice first!");
        }
    }

    public int getS_id() {
        return s_id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean IsActive() {
        return isActive;
    }

    public ServiceProvider getS_provider() {
        return s_provider;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public double getTotalVoiceCallTime() {
        return totalVoiceCallTime;
    }

    public int getTotalNumberOfMessages() {
        return totalNumberOfMessages;
    }

    public double getTotalInternetUsage() {
        return totalInternetUsage;
    }

    @Override
    public String toString() {
        return "Subscriber{"
                + "s_id=" + s_id
                + ", name='" + name + '\''
                + ", age=" + age
                + ", isActive=" + isActive
                + ", s_provider=" + s_provider.toString()
                + ", invoice=" + invoice.toString()
                + ", totalVoiceCallTime=" + totalVoiceCallTime
                + ", totalNumberOfMessages=" + totalNumberOfMessages
                + ", totalInternetUsage=" + totalInternetUsage
                + '}';
    }
}
