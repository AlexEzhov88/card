package org.example.bank.models;

public abstract class BankCard {
    protected double balance;
    protected double creditLimit;

    protected BankCard(double balance, double creditLimit) {
        this.balance = balance;
        this.creditLimit = creditLimit;
    }

    // Пополнение счета
    public void topUp(double amount) {
        balance += amount;
    }

    // Оплата со счета
    public boolean pay(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    // Получение информации о балансе
    public double getBalance() {
        return balance;
    }

    // Получение информации о доступных средствах
    public double getAvailableFunds() {
        return balance + creditLimit;
    }

    // Абстрактный метод для получения информации о бонусах
    public abstract String getBonusInfo();
}