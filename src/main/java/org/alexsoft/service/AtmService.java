package org.alexsoft.service;

import org.alexsoft.dto.Account;
import org.alexsoft.validator.CardValidator;

import java.util.Scanner;


public class AtmService {

    private static final Scanner scanner = new Scanner(System.in);

    private final AtmStateManager stateManager;
    private final CardAuthorisationService cardAuthorisationService;
    private final AccountOperationProcessor accountOperationProcessor;
    private Account currentAccount;

    public AtmService() {
        stateManager = new AtmStateManager();
        cardAuthorisationService = new CardAuthorisationService(scanner, stateManager.getMaxPinAttempts());
        accountOperationProcessor = new AccountOperationProcessor(scanner, stateManager);
    }

    boolean exit = false;

    public void start() {
        while (!exit) {
            System.out.println("Банкомат приветствует Вас!");
            System.out.println("Введите номер карты в формате XXXX-XXXX-XXXX-XXXX):");

            String cardNumber = scanner.nextLine();

            if (!CardValidator.isValidCardNumber(cardNumber)) {
                System.out.println("Неверный формат номера карты!");
                continue;
            }

            currentAccount = stateManager.getAccountByCardNumber(cardNumber);
            if (currentAccount == null) {
                System.out.println("Карта не найдена!");
                continue;
            }
            if (currentAccount.isBlocked()) {
                System.out.println("Карта заблокирована! Попробуйте позже.");
                continue;
            }
            if (cardAuthorisationService.authorize(currentAccount)) {
                accountOperationProcessor.processOperations(currentAccount);
            }
            stateManager.saveState();
        }
    }

}

