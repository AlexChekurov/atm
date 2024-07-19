package org.alexsoft.dto;

import java.util.ArrayList;
import java.util.List;


/**
 * Текущее состояние банкомата включая инфо о счетах
 */
public class AtmState {

    private final AtmConfig config = new AtmConfig();
    private List<Account> accounts = new ArrayList<>();

    public static AtmState fromString(String line) {
        String[] parts = line.split(" ");

        return new AtmState(
                Double.parseDouble(parts[0]),
                Double.parseDouble(parts[1]),
                Integer.parseInt(parts[2])
        );
    }

    public AtmState() {
    }

    public AtmState(double atmBalance, double maxDepositAmount, int maxPinAttempts) {
        config.setAtmBalance(atmBalance);
        config.setMaxDepositAmount(maxDepositAmount);
        config.setMaxPinAttempts(maxPinAttempts);
    }

    public AtmConfig getConfig() {
        return config;
    }

    public double getAtmBalance() {
        return config.getAtmBalance();
    }

    public void setAtmBalance(double atmBalance) {
        config.setAtmBalance(atmBalance);
    }

    public double getMaxDepositAmount() {
        return config.getMaxDepositAmount();
    }

    public void setMaxDepositAmount(double maxDepositAmount) {
        config.setMaxDepositAmount(maxDepositAmount);
    }

    public int getMaxPinAttempts() {
        return config.getMaxPinAttempts();
    }

    public void setMaxPinAttempts(int maxPinAttempts) {
        config.setMaxPinAttempts(maxPinAttempts);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

}
