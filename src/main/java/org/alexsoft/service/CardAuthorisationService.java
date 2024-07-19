package org.alexsoft.service;

import org.alexsoft.dto.Account;
import org.alexsoft.validator.CardValidator;

import java.util.Scanner;


public class CardAuthorisationService {

    private final Scanner scanner;
    private final int maxAttempts;

    public CardAuthorisationService(Scanner scanner, int maxAttempts) {
        this.scanner = scanner;
        this.maxAttempts = maxAttempts;
    }

    public boolean authorize(Account currentAccount) {
        int pinAttempts = 0;
        while (pinAttempts < maxAttempts) {
            System.out.println("Пожалуйста, введите PIN-код:");
            String pin = scanner.nextLine();

            if (!CardValidator.isValidPin(pin)) {
                System.out.println("Неверный формат PIN-кода!");
                pinAttempts++;
                continue;
            }
            if (currentAccount.getPinCode().equals(pin)) {
                return true;
            } else {
                System.out.println("Неверный PIN-код!");
                pinAttempts++;
            }
        }
        if (pinAttempts == maxAttempts) {
            currentAccount.block();
            System.out.println("Превышен лимит ввода PIN-кода, ваша карта заблокирована на 24 часа!");
        }
        return false;
    }

}
