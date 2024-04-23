package org.example.bank.models;

public class CreditCardWithPurchaseBonus extends CreditCard {
    private double bonusBalance;

    private double totalPurchases;

    public CreditCardWithPurchaseBonus(double balance, double creditLimit) {
        super(balance, creditLimit);
    }

    @Override
    public boolean pay(double amount) {
        if (super.pay(amount)) {
            totalPurchases += amount;
            if (totalPurchases >= 5000) {
                bonusBalance += amount * 0.05;
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getBonusInfo() {
        return "Потенциальный кэшбэк 5% от покупок при условии трат больше 5000. Текущий баланс бонусов: " + bonusBalance;
    }
}