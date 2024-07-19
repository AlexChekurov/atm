package org.alexsoft.service;

import org.alexsoft.dto.Account;
import org.alexsoft.dto.AtmState;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AtmStateManager {

    private static final String FILE_PATH = "accounts.txt";
    private final AtmState state;

    public AtmStateManager() {
        this.state = loadState();
    }

    public AtmState loadState() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            AtmState atmState = initializeState(reader);
            atmState.setAccounts(loadAccounts(reader));
            return atmState;
        } catch (IOException e) {
            System.out.println("Ошибка чтения состояния банкомата: " + e.getMessage());
            return new AtmState();
        }
    }

    public Integer getMaxPinAttempts() {
        return state.getMaxPinAttempts();
    }

    public double getMaxDepositAmount() {
        return state.getMaxDepositAmount();
    }

    public double getAtmBalance() {
        return state.getAtmBalance();
    }

    public Account getAccountByCardNumber(String cardNumber) {
        for (Account account : state.getAccounts()) {
            if (account.getCardNumber().equals(cardNumber)) {
                return account;
            }
        }
        return null;
    }

    public void saveState() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(state.getConfig().toString());
            writer.newLine();
            for (Account account : state.getAccounts()) {
                writer.write(account.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка записи состояния банкомата: " + e.getMessage());
        }
    }

    private AtmState initializeState(BufferedReader reader) {
        try {
            String line = reader.readLine();
            if (line != null) {
                return AtmState.fromString(line);
            }
            return new AtmState();
        } catch (IOException e) {
            System.out.println("Ошибка при чтении настроек банкомата: " + e.getMessage());
            return new AtmState();
        }
    }

    private List<Account> loadAccounts(BufferedReader reader) {
        var accounts = new ArrayList<Account>();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                accounts.add(Account.fromString(line));
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении счетов: " + e.getMessage());
        }

        return accounts;
    }

    public boolean withdrawAtmBalance(double amount) {
        if (state.getAtmBalance() >= amount) {
            state.setAtmBalance(state.getAtmBalance() - amount);
            return true;
        } else {
            return false;
        }
    }

}

