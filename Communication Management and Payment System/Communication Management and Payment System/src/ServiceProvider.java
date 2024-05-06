

import java.util.ArrayList;

public class ServiceProvider {

    private int p_id;
    private static int count = 500;
    private String p_name;
    private double voiceCallCost;
    private double messagingCost;
    private double internetCost;
    private int discountRatio;
    private double totalVoiceCallTime = 0;
    private int totalNumberOfMessages = 0;
    private double totalInternetUsage = 0;
    private final ArrayList<Subscriber> subscribersList = new ArrayList<>();

    public ServiceProvider(double voiceCallCost, double messagingCost, double internetCost, int discountRatio) {
        this.voiceCallCost = voiceCallCost;
        this.messagingCost = messagingCost;
        this.internetCost = internetCost;
        this.discountRatio = discountRatio;
        p_id = count++;
        if (p_id > 600) {
            System.out.println("There is not any available id ");
        }
    }

    public ServiceProvider(String p_name, double voiceCallCost, double messagingCost, double internetCost, int discountRatio) {
        this.p_name = p_name;
        this.voiceCallCost = voiceCallCost;
        this.messagingCost = messagingCost;
        this.internetCost = internetCost;
        this.discountRatio = discountRatio;
        p_id = count++;
        if (p_id > 600) {
            System.out.println("There is not any available id ");
        }
    }

    public double calculateVoiceCallCost(int minute, Subscriber caller) {
        double charge = 0.0;
        double discount;

        if (caller.getInvoice().getUsageLimit() >= caller.getInvoice().getCurrentSpending() && caller.IsActive()) {
            if (caller.getAge() >= 10 && caller.getAge() < 18) {
                charge = (minute - 5) * voiceCallCost;
                discount = charge * (discountRatio / 100.0);
                charge -= discount;
            } else if (caller.getAge() >= 65) {
                charge = minute * voiceCallCost;
                discount = charge * (discountRatio / 100.0);
                charge -= discount;
            } else {
                charge = minute * voiceCallCost;
            }

        }
        return charge;
    }

    public double calculateMessagingCost(int quantity, Subscriber sender, Subscriber receiver) {
        double charge = 0;
        double discount;
        if (sender.getInvoice().getUsageLimit() >= sender.getInvoice().getCurrentSpending() && sender.IsActive()) {

            if (sender.getAge() >= 10 && sender.getAge() < 18) {
                charge = (quantity - 10) * messagingCost;
            } else {
                charge = quantity * messagingCost;
            }
            if (sender.getS_provider().equals(receiver.getS_provider())) {
                discount = charge * (discountRatio / 100.0);
                charge -= discount;
            }
        }
        return charge;
    }

    public double calculateInternetCost(double amount, Subscriber subscriber) {
        double charge = 0.0;
        if (subscriber.getInvoice().getUsageLimit() >= subscriber.getInvoice().getCurrentSpending() && subscriber.IsActive()) {
            if (subscriber.getAge() >= 10 && subscriber.getAge() < 18) {
                charge = (amount - 5) * internetCost;

            } else {
                charge = amount * internetCost;
            }
        }
        return charge;
    }

    public void addSubscriber(Subscriber s) {
        if (!subscribersList.contains(s)) {
            subscribersList.add(s);
        }
    }

    public void removeSubscriber(Subscriber s) {
        subscribersList.remove(s);
    }

    public int getP_id() {
        return p_id;
    }

    public String getP_name() {
        return p_name;
    }

    public double getVoiceCallCost() {
        return voiceCallCost;
    }

    public void setVoiceCallCost(double voiceCallCost) {
        this.voiceCallCost = voiceCallCost;
    }

    public double getMessagingCost() {
        return messagingCost;
    }

    public void setMessagingCost(double messagingCost) {
        this.messagingCost = messagingCost;
    }

    public double getInternetCost() {
        return internetCost;
    }

    public void setInternetCost(double internetCost) {
        this.internetCost = internetCost;
    }

    public int getDiscountRatio() {
        return discountRatio;
    }

    public void setDiscountRatio(int discountRatio) {
        this.discountRatio = discountRatio;
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

    public void setTotalVoiceCallTime(int totalVoiceCallTime) {
        this.totalVoiceCallTime += totalVoiceCallTime;
    }

    public void setTotalNumberOfMessages(int totalNumberOfMessages) {
        this.totalNumberOfMessages += totalNumberOfMessages;
    }

    public void setTotalInternetUsage(double totalInternetUsage) {
        this.totalInternetUsage += totalInternetUsage;
    }

    @Override
    public String toString() {
        return "ServiceProvider{"
                + "p_id=" + p_id
                + ", p_name='" + p_name + '\''
                + ", voiceCallCost=" + voiceCallCost
                + ", messagingCost=" + messagingCost
                + ", internetCost=" + internetCost
                + ", discountRatio=" + discountRatio
                + ", totalVoiceCallTime=" + totalVoiceCallTime
                + ", totalNumberOfMessages=" + totalNumberOfMessages
                + ", totalInternetUsage=" + totalInternetUsage
                + '}';
    }

}
