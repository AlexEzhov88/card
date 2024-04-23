package org.example.bank;

import org.example.bank.models.BankCard;
import org.example.bank.models.CreditCard;
import org.example.bank.models.CreditCardWithPurchaseBonus;
import org.example.bank.models.DebitCard;
import org.example.bank.models.DebitCardWithPurchaseBonus;
import org.example.bank.models.DebitCardWithTopUpBonus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BankCardTests {

    @Test
    @DisplayName("Тест пополнения дебетовой карты")
    void testDebitCardTopUp() {
        DebitCard debitCard = new DebitCard(1000);
        debitCard.topUp(500);
        assertThat(debitCard.getBalance()).isEqualTo(1500);
    }

    @Test
    @DisplayName("Тест оплаты с кредитной карты")
    void testCreditCardPay() {
        CreditCard creditCard = new CreditCard(500, 5000);
        boolean paymentSuccess = creditCard.pay(1000);
        assertThat(paymentSuccess).isTrue();
        assertThat(creditCard.getBalance()).isEqualTo(4500);
    }

    @Test
    @DisplayName("Тест бонусов для дебетовой карты с бонусами на покупки")
    void testDebitCardWithPurchaseBonusInfo() {
        DebitCardWithPurchaseBonus debitCard = new DebitCardWithPurchaseBonus(2000);
        debitCard.pay(1000);
        String bonusInfo = debitCard.getBonusInfo();
        assertThat(bonusInfo).contains("Бонусные баллы в размере 1% от покупок");
    }

    @Test
    @DisplayName("Тест создания абстрактного класса BankCard")
    void testAbstractBankCard() {
        BankCard bankCard = new DebitCard(1000);
        assertThat(bankCard).isInstanceOf(BankCard.class);
    }

    @Test
    @DisplayName("Тест создания производных классов DebitCard и CreditCard")
    void testDerivativeCards() {
        DebitCard debitCard = new DebitCard(1000);
        assertThat(debitCard).isInstanceOf(DebitCard.class);

        CreditCard creditCard = new CreditCard(500, 5000);
        assertThat(creditCard).isInstanceOf(CreditCard.class);
    }

    @Test
    @DisplayName("Тест функций пополнения и получения баланса в классе DebitCard")
    void testDebitCardFunctions() {
        DebitCard debitCard = new DebitCard(1000);
        debitCard.topUp(500);
        assertThat(debitCard.getBalance()).isEqualTo(1500);
    }

    @Test
    @DisplayName("Тест создания производных классов от DebitCard и CreditCard")
    void testDerivativeCardsFromDebitAndCredit() {
        DebitCardWithPurchaseBonus debitCardWithBonus = new DebitCardWithPurchaseBonus(2000);
        assertThat(debitCardWithBonus).isInstanceOf(DebitCardWithPurchaseBonus.class);

        CreditCardWithPurchaseBonus creditCardWithBonus = new CreditCardWithPurchaseBonus(1000, 10000);
        assertThat(creditCardWithBonus).isInstanceOf(CreditCardWithPurchaseBonus.class);
    }

    @Test
    @DisplayName("Тест бонусных программ для конкретных карт")
    void testBonusPrograms() {
        List<BankCard> cards = new ArrayList<>();
        cards.add(new DebitCardWithPurchaseBonus(2000));
        cards.add(new CreditCardWithPurchaseBonus(1000, 10000));
        cards.add(new DebitCardWithTopUpBonus(3000));

        for (BankCard card : cards) {
            if (card instanceof DebitCardWithPurchaseBonus) {
                DebitCardWithPurchaseBonus debitCard = (DebitCardWithPurchaseBonus) card;
                debitCard.pay(1000);
                String bonusInfo = debitCard.getBonusInfo();
                assertThat(bonusInfo).contains("Бонусные баллы в размере 1% от покупок");
            } else if (card instanceof CreditCardWithPurchaseBonus creditCard) {
                creditCard.pay(3000);
                creditCard.pay(3000);
                String bonusInfo = creditCard.getBonusInfo();
                assertThat(bonusInfo).contains("Потенциальный кэшбэк 5% от покупок при условии трат больше 5000");
            } else if (card instanceof DebitCardWithTopUpBonus debitCard) {
                debitCard.topUp(1000);
                String bonusInfo = debitCard.getBonusInfo();
                assertThat(bonusInfo).contains("Накопление в размере 0.005% от суммы пополнений");
            }
        }
    }

    @Test
    @DisplayName("Тест функций пополнения, оплаты и получения баланса в классе CreditCard")
    void testCreditCardFunctions() {
        CreditCard creditCard = new CreditCard(500, 5000);
        creditCard.topUp(2000);
        assertThat(creditCard.getBalance()).isEqualTo(7500);

        boolean paymentSuccess = creditCard.pay(1000);
        assertThat(paymentSuccess).isTrue();
        assertThat(creditCard.getBalance()).isEqualTo(6500);
    }

    @Test
    @DisplayName("Тест разделения собственных и кредитных средств в классе CreditCard")
    void testCreditCardSeparateFunds() {
        CreditCard creditCard = new CreditCard(500.0, 10000.0);
        assertThat(creditCard.getOwnFunds()).isEqualTo(500.0);
        assertThat(creditCard.getCreditFunds()).isEqualTo(10000.0);

        creditCard.topUp(5000.0);
        assertThat(creditCard.getOwnFunds()).isEqualTo(5500);
        assertThat(creditCard.getCreditFunds()).isEqualTo(10000.0);

        creditCard.pay(5000.0);
        assertThat(creditCard.getOwnFunds()).isEqualTo(500);
        assertThat(creditCard.getCreditFunds()).isEqualTo(10000.0);

        creditCard.pay(3000.0);
        assertThat(creditCard.getOwnFunds()).isEqualTo(0.0);
        assertThat(creditCard.getCreditFunds()).isEqualTo(7500.0);
    }

    @Test
    @DisplayName("Тест корректировки функций пополнения и оплаты для учета бонусных программ на дебетовой карте")
    void testBonusProgramsInDebitCard() {
        DebitCardWithPurchaseBonus debitCardWithBonus = new DebitCardWithPurchaseBonus(2000);
        double initialBalance = debitCardWithBonus.getBalance();
        debitCardWithBonus.pay(1000);
        double balanceAfterPayment = debitCardWithBonus.getBalance();
        double bonusBalance = Double.parseDouble(debitCardWithBonus.getBonusInfo().split(": ")[1]);
        assertThat(balanceAfterPayment).isEqualTo(initialBalance - 1000);
        assertThat(bonusBalance).isEqualTo(10.0);
    }

    @Test
    @DisplayName("Тест корректировки функций пополнения и оплаты для учета бонусных программ на кредитной карте")
    void testBonusProgramsInCreditCard() {
        CreditCardWithPurchaseBonus creditCardWithBonus = new CreditCardWithPurchaseBonus(1000.0, 10000.0);
        double initialBalance = creditCardWithBonus.getBalance();
        creditCardWithBonus.pay(3000.0);
        creditCardWithBonus.pay(3000.0);
        double balanceAfterPayment = creditCardWithBonus.getBalance();
        double bonusBalance = Double.parseDouble(creditCardWithBonus.getBonusInfo().split(": ")[1]);
        assertThat(balanceAfterPayment).isEqualTo(initialBalance - 6000.0);
        assertThat(bonusBalance).isEqualTo(150.0);
    }

    @Test
    @DisplayName("Тест корректировки функций пополнения и оплаты для учета бонусных программ на дебетовой карте с бонусами за пополнение")
    void testBonusProgramsInDebitCardWithTopUpBonus() {
        DebitCardWithTopUpBonus debitCardWithTopUpBonus = new DebitCardWithTopUpBonus(3000.0);
        double initialBalance = debitCardWithTopUpBonus.getBalance();
        debitCardWithTopUpBonus.topUp(1000.0);
        double balanceAfterTopUp = debitCardWithTopUpBonus.getBalance();
        double bonusBalance = Double.parseDouble(debitCardWithTopUpBonus.getBonusInfo().split(": ")[1]);
        assertThat(balanceAfterTopUp).isEqualTo(initialBalance + 1000.0);
        assertThat(bonusBalance).isEqualTo(0.05);
    }

}