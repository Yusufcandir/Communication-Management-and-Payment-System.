

import java.util.Date;

public class Invoice {
    private double usageLimit;
    private double currentSpending;
    private Date lastDayToPay;
    private boolean isPaid = true;
    private double totalSpending;

    public Invoice(double usageLimit, Date lastDayToPay) {
        Date invoiceCreated = new Date();
        lastDayToPay = new Date(invoiceCreated.getTime() + (30L * 24 * 60 * 60 * 1000)); // 30 days later from invoice created
        this.usageLimit = usageLimit;
        this.lastDayToPay = lastDayToPay;
        currentSpending = 0;

    }

    public boolean isLimitExceeded(double amount) {
        return amount+currentSpending <= usageLimit;
    }

    public void addCost(double amount) {
        if (isLimitExceeded(amount + currentSpending) && isPaidOnTime()) {
            currentSpending += amount;
            totalSpending += amount;
        }
    }

    public void pay(double amount) {
        if (amount == currentSpending) {
            lastDayToPay = new Date(lastDayToPay.getTime() + (30L * 24 * 60 * 60 * 1000));
            isPaid = true;
            currentSpending = 0.0;
        }else{
            System.out.println("You have to pay the exact amount of your current spending! which is: " + currentSpending + " TL");
        }
    }

    public void changeUsageLimit(double amount) {
        usageLimit = amount;
    }

    public boolean isPaidOnTime() {
        if(new Date().after(lastDayToPay)){
            isPaid = false;
        }
        return isPaid;
    }


    public double getUsageLimit() {
        return usageLimit;
    }

    public double getTotalSpending() {
        return totalSpending;
    }

    public boolean IsPaid() {
        return isPaid;
    }

    public Date getLastDayToPay() {
        return lastDayToPay;
    }

    public double getCurrentSpending() {
        return currentSpending;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "usageLimit=" + usageLimit +
                ", currentSpending=" + currentSpending +
                ", lastDayToPay=" + lastDayToPay.toString() +
                ", isPaid=" + isPaid +
                ", totalSpending=" + totalSpending +
                '}';
    }
}
