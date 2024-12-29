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
            System.out.println("4. Exit");
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
                    System.out.println("Exiting the system. Thank you.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again."); 
            }
        } while (choice != 4);

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
                System.out.printf("Deposit successful.%nNew balance: %.2f%n" , balances[index]);
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
                System.out.printf("Withdrawal successful.%nNew balance: %.2f%n" , balances[index]);
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
            System.out.printf("Account Balance: %.2f%n" , balances[index]);
        } else {
            System.out.println("Account not found.");
        }
    }
  
}