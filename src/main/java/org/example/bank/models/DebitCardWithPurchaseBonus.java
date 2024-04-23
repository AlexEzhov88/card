package org.example.bank.models;

public class DebitCardWithPurchaseBonus extends DebitCard {
    private double bonusBalance;

    public DebitCardWithPurchaseBonus(double balance) {
        super(balance);
    }

    @Override
    public boolean pay(double amount) {
        if (super.pay(amount)) {
            bonusBalance += amount * 0.01;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getBonusInfo() {
        return "Бонусные баллы в размере 1% от покупок. Текущий баланс бонусов: " + bonusBalance;
    }
}