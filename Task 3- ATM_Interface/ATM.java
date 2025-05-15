import java.util.Scanner;

public class ATM {
    private static double balance = 1000.00;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int pin = 1234;
        System.out.print("Enter your ATM PIN: ");
        int enteredPin = sc.nextInt();

        if (enteredPin == pin) {
            while (true) {
                System.out.println("\n----- ATM Menu -----");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Exit");
                System.out.print("Choose an option: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Your current balance is: ₹" + balance);
                        break;
                    case 2:
                        System.out.print("Enter deposit amount: ₹");
                        double deposit = sc.nextDouble();
                        balance += deposit;
                        System.out.println("₹" + deposit + " deposited successfully.");
                        break;
                    case 3:
                        System.out.print("Enter withdrawal amount: ₹");
                        double withdraw = sc.nextDouble();
                        if (withdraw <= balance) {
                            balance -= withdraw;
                            System.out.println("₹" + withdraw + " withdrawn successfully.");
                        } else {
                            System.out.println("Insufficient balance!");
                        }
                        break;
                    case 4:
                        System.out.println("Thank you for using our ATM. Goodbye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } else {
            System.out.println("Incorrect PIN. Access denied.");
        }

        sc.close();
    }
}
