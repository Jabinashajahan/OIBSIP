import java.util.*;

public class GuessGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int score = 0, round = 1;

        System.out.println("🎮 Welcome to Number Guessing Game!");
        System.out.println("Guess a number between 1 and 100.");

        while (true) {
            int number = rand.nextInt(100) + 1;
            int attempts = 7;
            boolean won = false;

            System.out.println("\n🔁 Round " + round);
            while (attempts > 0) {
                System.out.print("Enter your guess (" + attempts + " attempts left): ");
                int guess = sc.nextInt();
                if (guess == number) {
                    System.out.println("🎉 Correct! You guessed the number.");
                    score += 10;
                    won = true;
                    break;
                } else if (guess < number) {
                    System.out.println("Too low!");
                } else {
                    System.out.println("Too high!");
                }
                attempts--;
            }

            if (!won) {
                System.out.println("❌ You've run out of attempts! The number was " + number);
            }

            System.out.println("Your score: " + score);
            System.out.print("Play another round? (yes/no): ");
            String again = sc.next();
            if (!again.equalsIgnoreCase("yes")) break;

            round++;
        }

        System.out.println("🏁 Game Over. Final Score: " + score);
    }
}
