import java.util.*;

class ATM {
    private double balance;
    private String userId = "user123";
    private String userPin = "1234";
    private List<String> history = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    void start() {
        if (login()) {
            int choice;
            do {
                System.out.println("\n1. Deposit\n2. Withdraw\n3. Transfer\n4. Check Balance\n5. Transaction History\n6. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                switch (choice) {
                    case 1 -> deposit();
                    case 2 -> withdraw();
                    case 3 -> transfer();
                    case 4 -> checkBalance();
                    case 5 -> showHistory();
                    case 6 -> System.out.println("Thank you for using ATM!");
                    default -> System.out.println("Invalid choice.");
                }
            } while (choice != 6);
        }
    }

    boolean login() {
        System.out.print("Enter User ID: ");
        String uid = sc.next();
        System.out.print("Enter PIN: ");
        String pin = sc.next();
        return uid.equals(userId) && pin.equals(userPin);
    }

    void deposit() {
        System.out.print("Enter amount to deposit: ");
        double amt = sc.nextDouble();
        balance += amt;
        history.add("Deposited: " + amt);
        System.out.println("✅ Amount deposited successfully.");
    }

    void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amt = sc.nextDouble();
        if (amt <= balance) {
            balance -= amt;
            history.add("Withdrawn: " + amt);
            System.out.println("✅ Please collect your cash.");
        } else {
            System.out.println("❌ Insufficient balance.");
        }
    }

    void transfer() {
        System.out.print("Enter recipient account ID: ");
        String acc = sc.next();
        System.out.print("Enter amount to transfer: ");
        double amt = sc.nextDouble();
        if (amt <= balance) {
            balance -= amt;
            history.add("Transferred: " + amt + " to " + acc);
            System.out.println("✅ Transfer successful.");
        } else {
            System.out.println("❌ Insufficient balance.");
        }
    }

    void checkBalance() {
        System.out.println("💰 Current Balance: " + balance);
    }

    void showHistory() {
        if (history.isEmpty()) {
            System.out.println("📭 No transactions yet.");
        } else {
            System.out.println("📋 Transaction History:");
            history.forEach(System.out::println);
        }
    }

    public static void main(String[] args) {
        new ATM().start();
    }
}
