package org.alexsoft.service;

import org.alexsoft.dto.Account;

import java.util.Scanner;


public class AccountOperationProcessor {

    private final Scanner scanner;
    private final AtmStateManager stateManager;

    public AccountOperationProcessor(Scanner scanner, AtmStateManager stateManager) {
        this.scanner = scanner;
        this.stateManager = stateManager;
    }

    public void processOperations(Account currentAccount) {
        while (true) {
            System.out.println("1. Проверить баланс");
            System.out.println("2. Снять наличные");
            System.out.println("3. Внести наличные");
            System.out.println("4. Выход");
            System.out.print("Выберите операцию: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    checkBalance(currentAccount);
                    break;
                case 2:
                    withdrawFunds(currentAccount);
                    break;
                case 3:
                    depositFunds(currentAccount);
                    break;
                case 4:
                    System.out.println("Спасибо, что воспользовались нашим банкоматом!");
                    return;
                default:
                    System.out.println("Неверная операция. Повторите выбор");
            }
        }
    }

    private void checkBalance(Account currentAccount) {
        System.out.println("Ваш баланс: " + currentAccount.getBalance());
    }

    private void withdrawFunds(Account currentAccount) {
        System.out.println("Введите сумму для снятия:");
        double amount = Double.parseDouble(scanner.nextLine());

        if (amount > currentAccount.getBalance()) {
            System.out.println("Недостаточно средств на счете!");
        } else if (amount > stateManager.getAtmBalance()) {
            System.out.println("В банкомате недостаточно наличных!");
        } else {
            currentAccount.withdraw(amount);
            stateManager.withdrawAtmBalance(amount);
            System.out.println("Операция выполнена! Ваш баланс: " + currentAccount.getBalance());
        }
    }

    private void depositFunds(Account currentAccount) {
        System.out.println("Введите сумму пополнения (не более " + stateManager.getMaxDepositAmount() + "):");
        double amount = Double.parseDouble(scanner.nextLine());

        if (amount > stateManager.getMaxDepositAmount()) {
            System.out.println("Превышена сумма пополнения!");
        } else {
            currentAccount.deposit(amount);
            System.out.println("Пополнение прошло успешно. Ваш баланс: " + currentAccount.getBalance());
        }
    }

}
