/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accounts;

/**
 *
 * @author H.P
 */
public class Account {
    private int number;
    private double balance;

    public void deposit(double amount) {
        requirePositiveAmount(amount);
        balance += amount;
    }

    public void withdraw(double amount) {
        requirePositiveAmount(amount);
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
        balance -= amount;
    }
    public double getBalance() {
        return balance;
    }

    private static void requirePositiveAmount(double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive and finite.");
        }
    }
}
