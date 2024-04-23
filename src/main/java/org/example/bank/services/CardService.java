package org.example.bank.services;

import org.example.bank.models.BankCard;

public class CardService {
    private BankCard[] cards;

    public CardService() {
        setCards(new BankCard[0]);
    }

    // Добавление новой карты
    public void addCard(BankCard card) {
        BankCard[] newCards = new BankCard[getCards().length + 1];
        System.arraycopy(getCards(), 0, newCards, 0, getCards().length);
        newCards[getCards().length] = card;
        setCards(newCards);
    }

    // Пополнение счета выбранной карты
    public void topUpCard(int cardIndex, double amount) {
        getCards()[cardIndex].topUp(amount);
    }

    // Оплата со счета выбранной карты
    public boolean payFromCard(int cardIndex, double amount) {
        return getCards()[cardIndex].pay(amount);
    }

    // Получение информации о балансе выбранной карты
    public double getCardBalance(int cardIndex) {
        return getCards()[cardIndex].getBalance();
    }

    // Получение информации о доступных средствах выбранной карты
    public double getCardAvailableFunds(int cardIndex) {
        return getCards()[cardIndex].getAvailableFunds();
    }

    // Получение информации о бонусах выбранной карты
    public String getCardBonusInfo(int cardIndex) {
        return getCards()[cardIndex].getBonusInfo();
    }

    public BankCard[] getCards() {
        return cards;
    }

    public void setCards(BankCard[] cards) {
        this.cards = cards;
    }
}
