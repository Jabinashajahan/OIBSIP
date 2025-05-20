import java.util.*;

class Ticket {
    static int counter = 1000;
    int pnr;
    String name, from, to, date, trainName;
    int trainNo;

    Ticket(String name, String from, String to, String date, int trainNo, String trainName) {
        this.pnr = counter++;
        this.name = name;
        this.from = from;
        this.to = to;
        this.date = date;
        this.trainNo = trainNo;
        this.trainName = trainName;
    }

    public String toString() {
        return "PNR: " + pnr + " | Name: " + name + " | From: " + from + " | To: " + to +
               " | Date: " + date + " | Train: " + trainName + " (" + trainNo + ")";
    }
}

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Ticket> tickets = new ArrayList<>();
    static String username = "user";
    static String password = "pass";

    public static void main(String[] args) {
        if (login()) {
            while (true) {
                System.out.println("\n1. Book Ticket\n2. Cancel Ticket\n3. View All Tickets\n4. Exit");
                System.out.print("Enter your choice: ");
                int ch = sc.nextInt();
                switch (ch) {
                    case 1: bookTicket(); break;
                    case 2: cancelTicket(); break;
                    case 3: viewTickets(); break;
                    case 4: System.exit(0);
                    default: System.out.println("Invalid choice!");
                }
            }
        }
    }

    static boolean login() {
        System.out.print("Enter username: ");
        String u = sc.next();
        System.out.print("Enter password: ");
        String p = sc.next();
        if (u.equals(username) && p.equals(password)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid credentials!");
            return false;
        }
    }

    static void bookTicket() {
        System.out.print("Enter name: ");
        String name = sc.next();
        System.out.print("From: ");
        String from = sc.next();
        System.out.print("To: ");
        String to = sc.next();
        System.out.print("Date (DD/MM/YYYY): ");
        String date = sc.next();
        System.out.print("Train No: ");
        int trainNo = sc.nextInt();
        sc.nextLine();  // consume newline
        System.out.print("Train Name: ");
        String trainName = sc.nextLine();

        Ticket t = new Ticket(name, from, to, date, trainNo, trainName);
        tickets.add(t);
        System.out.println("Ticket booked! PNR: " + t.pnr);
    }

    static void cancelTicket() {
        System.out.print("Enter PNR to cancel: ");
        int pnr = sc.nextInt();
        boolean found = false;
        for (Ticket t : tickets) {
            if (t.pnr == pnr) {
                tickets.remove(t);
                System.out.println("Ticket with PNR " + pnr + " cancelled.");
                found = true;
                break;
            }
        }
        if (!found)
            System.out.println("Ticket not found!");
    }

    static void viewTickets() {
        if (tickets.isEmpty()) {
            System.out.println("No tickets booked.");
            return;
        }
        for (Ticket t : tickets)
            System.out.println(t);
    }
}
