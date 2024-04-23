package org.example.bank;

import org.example.bank.models.BankCard;
import org.example.bank.models.CreditCard;
import org.example.bank.models.CreditCardWithPurchaseBonus;
import org.example.bank.models.DebitCard;
import org.example.bank.models.DebitCardWithPurchaseBonus;
import org.example.bank.models.DebitCardWithTopUpBonus;
import org.junit.jupiter.api.Test;

class CardTests {

    @Test
    void mainTest() {
        testDebitCard();
        testCreditCard();
        testDebitCardWithPurchaseBonus();
        testCreditCardWithPurchaseBonus();
        testDebitCardWithTopUpBonus();
        testCreditCardWithSeparateFunds();
    }

    private static void testDebitCard() {
        System.out.println("Тест дебетовой карты:");
        BankCard debitCard = new DebitCard(1000);
        debitCard.topUp(500);
        System.out.println("Баланс дебетовой карты после пополнения: " + debitCard.getBalance());
    }

    private static void testCreditCard() {
        System.out.println("\nТест кредитной карты:");
        BankCard creditCard = new CreditCard(500, 5000);
        creditCard.topUp(2000);
        creditCard.pay(1500);
        System.out.println("Баланс кредитной карты: " + creditCard.getBalance());
        System.out.println("Доступные средства кредитной карты: " + creditCard.getAvailableFunds());
    }

    private static void testDebitCardWithPurchaseBonus() {
        System.out.println("\nТест дебетовой карты с бонусами на покупки:");
        BankCard debitCardWithBonusOnPurchase = new DebitCardWithPurchaseBonus(2000);
        debitCardWithBonusOnPurchase.pay(1000);
        System.out.println("Баланс дебетовой карты с бонусами на покупки: " + debitCardWithBonusOnPurchase.getBalance());
        System.out.println("Информация о бонусах: " + debitCardWithBonusOnPurchase.getBonusInfo());
    }

    private static void testCreditCardWithPurchaseBonus() {
        System.out.println("\nТест кредитной карты с бонусами на покупки:");
        BankCard creditCardWithBonusOnPurchase = new CreditCardWithPurchaseBonus(1000, 10000);
        creditCardWithBonusOnPurchase.pay(3000);
        creditCardWithBonusOnPurchase.pay(3000);
        System.out.println("Баланс кредитной карты с бонусами на покупки: " + creditCardWithBonusOnPurchase.getBalance());
        System.out.println("Информация о бонусах: " + creditCardWithBonusOnPurchase.getBonusInfo());
    }

    private static void testDebitCardWithTopUpBonus() {
        System.out.println("\nТест дебетовой карты с бонусами на пополнение:");
        BankCard debitCardWithBonusOnTopUp = new DebitCardWithTopUpBonus(3000);
        debitCardWithBonusOnTopUp.topUp(1000);
        System.out.println("Баланс дебетовой карты с бонусами на пополнение: " + debitCardWithBonusOnTopUp.getBalance());
        System.out.println("Информация о бонусах: " + debitCardWithBonusOnTopUp.getBonusInfo());
    }

    private static void testCreditCardWithSeparateFunds() {
        System.out.println("\nТест кредитной карты с разделением собственных и кредитных средств:");
        CreditCard creditCard = new CreditCard(500, 10000);
        System.out.println("Кредитная карта с лимитом 10000:");
        System.out.println("Собственные средства: " + creditCard.getOwnFunds());
        System.out.println("Кредитные средства: " + creditCard.getCreditFunds());

        creditCard.topUp(5000);
        System.out.println("\nПосле пополнения карты на 5000:");
        System.out.println("Собственные средства: " + creditCard.getOwnFunds());
        System.out.println("Кредитные средства: " + creditCard.getCreditFunds());

        creditCard.pay(5000);
        System.out.println("\nПосле оплаты на 5000:");
        System.out.println("Собственные средства: " + creditCard.getOwnFunds());
        System.out.println("Кредитные средства: " + creditCard.getCreditFunds());

        creditCard.pay(3000);
        System.out.println("\nПосле оплаты на 3000:");
        System.out.println("Собственные средства: " + creditCard.getOwnFunds());
        System.out.println("Кредитные средства: " + creditCard.getCreditFunds());

        creditCard.topUp(2000);
        System.out.println("\nПосле пополнения на 2000:");
        System.out.println("Собственные средства: " + creditCard.getOwnFunds());
        System.out.println("Кредитные средства: " + creditCard.getCreditFunds());

        creditCard.topUp(2000);
        System.out.println("\nПосле пополнения на 2000:");
        System.out.println("Собственные средства: " + creditCard.getOwnFunds());
        System.out.println("Кредитные средства: " + creditCard.getCreditFunds());
    }
}