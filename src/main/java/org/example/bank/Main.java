package org.example.bank;

import org.example.bank.models.BankCard;
import org.example.bank.models.CreditCard;
import org.example.bank.models.CreditCardWithPurchaseBonus;
import org.example.bank.models.DebitCard;
import org.example.bank.models.DebitCardWithPurchaseBonus;
import org.example.bank.models.DebitCardWithTopUpBonus;
import org.example.bank.services.CardService;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CardService cardService = new CardService();

    public static void main(String[] args) {
        // Создание тестовых карт
        cardService.addCard(new DebitCard(1000));
        cardService.addCard(new CreditCard(500, 5000));
        cardService.addCard(new DebitCardWithPurchaseBonus(2000));
        cardService.addCard(new CreditCardWithPurchaseBonus(1000, 10000));
        cardService.addCard(new DebitCardWithTopUpBonus(3000));

        showMenu();
    }

    private static void showMenu() {
        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Посмотреть информацию о картах");
            System.out.println("2. Пополнить карту");
            System.out.println("3. Оплатить с карты");
            System.out.println("4. Создать новую карту");
            System.out.println("0. Выход");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера после ввода числа

            switch (choice) {
                case 1 -> showCardInfo();
                case 2 -> topUpCard();
                case 3 -> payFromCard();
                case 4 -> createNewCard();
                case 0 -> {
                    System.out.println("Выход из программы.");
                    return;
                }
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static void showCardInfo() {
        System.out.println("Информация о картах:");
        for (int i = 0; i < cardService.getCards().length; i++) {
            BankCard card = cardService.getCards()[i];
            System.out.println((i + 1) + ". " + card.getClass().getSimpleName());
            System.out.println("   Баланс: " + cardService.getCardBalance(i));
            System.out.println("   Доступные средства: " + cardService.getCardAvailableFunds(i));
            System.out.println("   Бонусная информация: " + cardService.getCardBonusInfo(i));
        }
    }

    private static void topUpCard() {
        System.out.println("Выберите карту для пополнения:");
        int cardIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Очистка буфера после ввода числа

        if (cardIndex >= 0 && cardIndex < cardService.getCards().length) {
            System.out.println("Введите сумму для пополнения:");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Очистка буфера после ввода числа

            cardService.topUpCard(cardIndex, amount);
            System.out.println("Карта пополнена на " + amount);
        } else {
            System.out.println("Неверный индекс карты.");
        }
    }

    private static void payFromCard() {
        System.out.println("Выберите карту для оплаты:");
        int cardIndex = scanner.nextInt() - 1;
        scanner.nextLine(); // Очистка буфера после ввода числа

        if (cardIndex >= 0 && cardIndex < cardService.getCards().length) {
            System.out.println("Введите сумму для оплаты:");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Очистка буфера после ввода числа

            if (cardService.payFromCard(cardIndex, amount)) {
                System.out.println("Оплата прошла успешно.");
            } else {
                System.out.println("Недостаточно средств для оплаты.");
            }
        } else {
            System.out.println("Неверный индекс карты.");
        }
    }

    private static void createNewCard() {
        System.out.println("Выберите тип карты:");
        System.out.println("1. Дебетовая карта");
        System.out.println("2. Кредитная карта");
        int cardType = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера после ввода числа

        if (cardType == 1) {
            System.out.println("Введите начальный баланс для дебетовой карты:");
            double balance = scanner.nextDouble();
            scanner.nextLine(); // Очистка буфера после ввода числа

            System.out.println("Выберите бонусную программу:");
            System.out.println("1. Без бонусной программы");
            System.out.println("2. Бонусные баллы в размере 1% от покупок");
            System.out.println("3. Накопление в размере 0.005% от суммы пополнений");
            int bonusChoice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера после ввода числа

            BankCard newCard;
            switch (bonusChoice) {
                case 1 -> newCard = new DebitCard(balance);
                case 2 -> newCard = new DebitCardWithPurchaseBonus(balance);
                case 3 -> newCard = new DebitCardWithTopUpBonus(balance);
                default -> {
                    System.out.println("Неверный выбор бонусной программы.");
                    return;
                }
            }

            cardService.addCard(newCard);
            System.out.println("Новая дебетовая карта создана.");
        } else if (cardType == 2) {
            System.out.println("Введите начальный баланс для кредитной карты:");
            double balance = scanner.nextDouble();
            scanner.nextLine(); // Очистка буфера после ввода числа

            System.out.println("Введите кредитный лимит:");
            double creditLimit = scanner.nextDouble();
            scanner.nextLine(); // Очистка буфера после ввода числа

            System.out.println("Выберите бонусную программу:");
            System.out.println("1. Без бонусной программы");
            System.out.println("2. Потенциальный кэшбэк 5% от покупок при условии трат больше 5000");
            int bonusChoice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера после ввода числа

            BankCard newCard;
            switch (bonusChoice) {
                case 1 -> newCard = new CreditCard(balance, creditLimit);
                case 2 -> newCard = new CreditCardWithPurchaseBonus(balance, creditLimit);
                default -> {
                    System.out.println("Неверный выбор бонусной программы.");
                    return;
                }
            }

            cardService.addCard(newCard);
            System.out.println("Новая кредитная карта создана.");
        } else {
            System.out.println("Неверный выбор типа карты.");
        }
    }
}
