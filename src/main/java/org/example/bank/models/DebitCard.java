package org.example.bank.models;

public class DebitCard extends BankCard {
    public DebitCard(double balance) {
        super(balance, 0);
    }

    @Override
    public String getBonusInfo() {
        return "Для дебетовой карты бонусов нет.";
    }
}