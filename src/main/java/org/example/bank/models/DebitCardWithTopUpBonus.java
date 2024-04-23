package org.example.bank.models;

public class DebitCardWithTopUpBonus extends DebitCard {
    private double bonusBalance;

    public DebitCardWithTopUpBonus(double balance) {
        super(balance);
    }

    @Override
    public void topUp(double amount) {
        super.topUp(amount);
        bonusBalance += amount * 0.00005;
    }

    @Override
    public String getBonusInfo() {
        return "Накопление в размере 0.005% от суммы пополнений. Текущий баланс бонусов: " + bonusBalance;
    }
}