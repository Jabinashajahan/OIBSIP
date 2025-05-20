import java.util.*;

public class ExamSystem {
    static Scanner sc = new Scanner(System.in);
    static String user = "student";
    static String pass = "1234";

    static String[] questions = {
        "1. Capital of India?\n a) Delhi\n b) Mumbai\n c) Kolkata",
        "2. 2 + 2 = ?\n a) 3\n b) 4\n c) 5",
        "3. Java is a ___?\n a) Language\n b) OS\n c) Browser",
        "4. CPU is?\n a) Central Power Unit\n b) Central Process Unit\n c) Central Processing Unit",
        "5. HTML stands for?\n a) Hyper Trainer Markup Language\n b) HyperText Markup Language\n c) HighText Machine Language"
    };

    static char[] answers = {'a', 'b', 'a', 'c', 'b'};

    public static void main(String[] args) {
        if (login()) {
            int score = 0;
            for (int i = 0; i < questions.length; i++) {
                System.out.println("\n" + questions[i]);
                System.out.print("Your answer: ");
                char ans = sc.next().charAt(0);
                if (ans == answers[i]) score++;
            }
            System.out.println("\n📈 Final Score: " + score + "/" + questions.length);
        }
    }

    static boolean login() {
        System.out.print("Enter username: ");
        String u = sc.next();
        System.out.print("Enter password: ");
        String p = sc.next();
        if (u.equals(user) && p.equals(pass)) {
            System.out.println("✅ Login Successful. Exam starting...");
            return true;
        } else {
            System.out.println("❌ Invalid Credentials.");
            return false;
        }
    }
}
