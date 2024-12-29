import java.util.Scanner;

public class BankingSystem{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int choice;

        int[] accountNumbers = {20031004, 20030911, 20030831};
        double[] balances = {500.0, 1000.0, 1300.0};

        do{
            System.out.println("\n=== Banking System Menu ===");
            System.out.println("1. Deposit");
            System.out.println("2. Balance Inquiry");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer Money");
            System.out.println("5. Exit");
            System.out.print("Enter Your Choice: ");

            choice = scan.nextInt();

            switch (choice){
                case 1:
                    deposit(scan, accountNumbers, balances);
                    break;
                case 2:
                    balanceInquiry(scan, accountNumbers, balances);
                    break;
                case 3:
                    withdraw(scan, accountNumbers, balances);
                    break; 
                case 4:
                    transferMoney(scan, accountNumbers, balances); // Call transferMoney
                    break;
                case 5:
                    System.out.println("Exiting the system. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    }
        } while (choice != 5);

        scan.close();
    }

    static int findAccount(int[] accountNumbers, int accountNumber) {
        for (int i = 0; i < accountNumbers.length; i++) {
            if (accountNumbers[i] == accountNumber) {
                return i;
            }
        }
        return -1; // Account not found
    }

    static void deposit(Scanner scanner, int[] accountNumber, double[] balances) {
        System.out.print("Enter account number: ");
        int accountNumbers = scanner.nextInt();

        int index = findAccount(accountNumber, accountNumbers);
        if (index != -1) {
            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();
            if (amount > 0) {
                balances[index] += amount;
                System.out.println("Deposit successful. New balance: " + balances[index]);
            } else {
                System.out.println("Invalid amount. Please enter a positive value.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }
      
    static void withdraw(Scanner scanner, int[] accountNumbers, double[] balances) {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();

        int index = findAccount(accountNumbers, accountNumber);
        if (index != -1) {
            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();
            if (amount > 0 && amount <= balances[index]) {
                balances[index] -= amount;
                System.out.println("Withdrawal successful. New balance: " + balances[index]);
            } else {
                System.out.println("Invalid amount or insufficient balance.");
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    static void balanceInquiry(Scanner scanner, int[] accountNumbers, double[] balances) {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();

        int index = findAccount(accountNumbers, accountNumber);
        if (index != -1) {
            System.out.println("Account Balance: " + balances[index]);
        } else {
            System.out.println("Account not found.");
        }
    }  

    /**
     * Transfers money between accounts.
     *
     * @param scanner Scanner object for user input.
     * @param accountNumbers Array of account numbers.
     * @param balances Array of account balances.
     */
    public static void transferMoney(Scanner scanner, int[] accountNumbers, double[] balances) {
        System.out.print("Enter sender's account number: ");
        int senderAccount = scanner.nextInt();

        System.out.print("Enter receiver's account number: ");
        int receiverAccount = scanner.nextInt();

        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();

        int senderIndex = -1, receiverIndex = -1;
        for (int i = 0; i < accountNumbers.length; i++) {
            if (accountNumbers[i] == senderAccount) senderIndex = i;
            if (accountNumbers[i] == receiverAccount) receiverIndex = i;
        }

        if (senderIndex == -1 || receiverIndex == -1) {
            System.out.println("One or both account numbers are invalid.");
            return;
        }

        if (balances[senderIndex] < amount) {
            System.out.println("Insufficient balance in sender's account.");
            return;
        }

        balances[senderIndex] -= amount;
        balances[receiverIndex] += amount;

        System.out.println("Transfer successful! $" + amount + " transferred from account " + senderAccount + " to account " + receiverAccount + ".");
    }

}