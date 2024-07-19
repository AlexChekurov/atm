package org.alexsoft.dto;

/**
 * Текущая конфигурация банкомата
 */
public class AtmConfig {

    private double atmBalance = 10_000; // баланс банкомата
    private double maxDepositAmount = 1_000_000; // максимальная сумма пополнения за один раз
    private int maxPinAttempts = 3; // максимальное число попыток ввода пин-кода

    public double getAtmBalance() {
        return atmBalance;
    }

    public void setAtmBalance(double atmBalance) {
        this.atmBalance = atmBalance;
    }

    public double getMaxDepositAmount() {
        return maxDepositAmount;
    }

    public void setMaxDepositAmount(double maxDepositAmount) {
        this.maxDepositAmount = maxDepositAmount;
    }

    public int getMaxPinAttempts() {
        return maxPinAttempts;
    }

    public void setMaxPinAttempts(int maxPinAttempts) {
        this.maxPinAttempts = maxPinAttempts;
    }

    @Override
    public String toString() {
        return atmBalance + " " + maxDepositAmount + " " + maxPinAttempts;
    }

}
