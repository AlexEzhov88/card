package org.example.bank.models;

public class CreditCard extends BankCard {

    private double ownFunds; // Собственные средства
    private double creditFunds; // Кредитные средства

    public CreditCard(double balance, double creditLimit) {
        super(balance, creditLimit);
        this.ownFunds = balance;
        this.creditFunds = creditLimit;
    }

    @Override
    public void topUp(double amount) {
        if (creditFunds < creditLimit) {
            double remainingCreditCapacity = creditLimit - creditFunds;
            if (amount <= remainingCreditCapacity) {
                creditFunds += amount;
            } else {
                creditFunds = creditLimit;
                ownFunds += (amount - remainingCreditCapacity);
            }
        } else {
            ownFunds += amount;
        }
    }

    @Override
    public boolean pay(double amount) {
        if (amount <= ownFunds + creditFunds) {
            if (amount <= ownFunds) {
                ownFunds -= amount;
            } else {
                double amountFromCredit = amount - ownFunds;
                creditFunds -= amountFromCredit;
                ownFunds = 0;
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public double getBalance() {
        return ownFunds + creditFunds;
    }

    @Override
    public double getAvailableFunds() {
        return ownFunds + creditFunds;
    }

    @Override
    public String getBonusInfo() {
        return "Для кредитной карты бонусов нет.";
    }

    public double getOwnFunds() {
        return ownFunds;
    }

    public double getCreditFunds() {
        return creditFunds;
    }

}