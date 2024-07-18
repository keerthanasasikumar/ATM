import java.util.ArrayList;
import java.util.Scanner;

class Account {
    private double balance;
    private String pin;
    private ArrayList<String> transactionHistory; 

    public Account(double initialBalance, String initialPin) {
        this.balance = initialBalance;
        this.pin = initialPin;
        this.transactionHistory = new ArrayList<>();
        this.transactionHistory.add("Account created with initial balance: " + initialBalance);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: " + amount + ", New Balance: " + balance);
            System.out.println("Deposit successful. New balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: " + amount + ", New Balance: " + balance);
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }

    public boolean changePin(String oldPin, String newPin) {
        if (this.pin.equals(oldPin) && newPin.length() >= 4) {
            this.pin = newPin;
            transactionHistory.add("PIN changed successfully.");
            return true;
        } else {
            return false;
        }
    }

    public boolean validatePin(String pin) {
        return this.pin.equals(pin);
    }

    public void printTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

public class ATMSimulation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Account account = new Account(1000, "1234");

        while (true) {
            System.out.println("\nATM Machine");
            System.out.println("1. Account Balance Inquiry");
            System.out.println("2. Cash Withdrawal");
            System.out.println("3. Cash Deposit");
            System.out.println("4. PIN Change");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Account Balance: " + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 4:
                    System.out.print("Enter old PIN: ");
                    String oldPin = scanner.next();
                    System.out.print("Enter new PIN: ");
                    String newPin = scanner.next();
                    if (account.changePin(oldPin, newPin)) {
                        System.out.println("PIN changed successfully.");
                    } else {
                        System.out.println("Invalid PIN or new PIN too short.");
                    }
                    break;
                case 5:
                    account.printTransactionHistory();
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}