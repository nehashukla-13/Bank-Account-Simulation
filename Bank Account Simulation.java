import java.util.ArrayList;
import java.util.Scanner;

// Parent class
class Account {
private String accountHolder;
private String accountNumber;
protected double balance;
private ArrayList<String> transactions;

// Constructor
public Account(String accountHolder, String accountNumber, double balance) {
    this.accountHolder = accountHolder;
    this.accountNumber = accountNumber;
    this.balance = balance;
    transactions = new ArrayList<String>();
    transactions.add("Account opened with balance: Rs. " + balance);
}

public String getAccountType() {
    return "Bank Account";
}

public void deposit(double amount) {
    if (amount <= 0) {
        System.out.println("Amount must be greater than zero.");
        return;
    }

    balance += amount;
    transactions.add("Deposited: Rs. " + amount);
    System.out.println("Deposit successful!");
    System.out.println("Current balance: Rs. " + balance);
}

public void withdraw(double amount) {
    if (amount <= 0) {
        System.out.println("Amount must be greater than zero.");
    } else if (amount > balance) {
        System.out.println("Insufficient balance!");
    } else {
        balance -= amount;
        transactions.add("Withdrawn: Rs. " + amount);
        System.out.println("Withdrawal successful!");
        System.out.println("Current balance: Rs. " + balance);
    }
}

public void checkBalance() {
    System.out.println("Account Holder: " + accountHolder);
    System.out.println("Account Number: " + accountNumber);
    System.out.println("Account Type: " + getAccountType());
    System.out.println("Available Balance: Rs. " + balance);
}

public void showTransactions() {
    System.out.println("\n--- Transaction History ---");
    for (String transaction : transactions) {
        System.out.println(transaction);
    }
}

}

// Child class: Inheritance and method overriding
class SavingsAccount extends Account {
public SavingsAccount(String name, String number, double balance) {
super(name, number, balance);
}

@Override
public String getAccountType() {
    return "Savings Account";
}

}

// Main class
public class Main {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);

    Account account = new SavingsAccount(
        "Your Name", "1234567890", 1000.0
    );

    int choice=0;;

    do {
        System.out.println("\n===== BANK ACCOUNT SIMULATION =====");
        System.out.println("1. Check Account Details");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. View Transaction History");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");

        if (!sc.hasNextInt()) {
            System.out.println("Please enter a valid menu number.");
            sc.next();
            continue;
        }

        choice = sc.nextInt();

        switch (choice) {
            case 1:
                account.checkBalance();
                break;

            case 2:
                System.out.print("Enter deposit amount: Rs. ");
                if (sc.hasNextDouble()) {
                    account.deposit(sc.nextDouble());
                } else {
                    System.out.println("Invalid amount.");
                    sc.next();
                }
                break;

            case 3:
                System.out.print("Enter withdrawal amount: Rs. ");
                if (sc.hasNextDouble()) {
                    account.withdraw(sc.nextDouble());
                } else {
                    System.out.println("Invalid amount.");
                    sc.next();
                }
                break;

            case 4:
                account.showTransactions();
                break;

            case 5:
                System.out.println("Thank you for using our bank!");
                break;

            default:
                System.out.println("Invalid choice. Try again.");
        }

    } while (choice != 5);

    sc.close();
}

}