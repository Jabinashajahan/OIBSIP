import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int numberToGuess = rand.nextInt(100) + 1;
        int guess;
        int attempts = 0;

        System.out.println("Welcome to Number Guessing Game!");
        System.out.println("Guess a number between 1 and 100:");

        do {
            System.out.print("Your guess: ");
            guess = sc.nextInt();
            attempts++;

            if (guess > numberToGuess) {
                System.out.println("Too high!");
            } else if (guess < numberToGuess) {
                System.out.println("Too low!");
            } else {
                System.out.println("Correct! You guessed in " + attempts + " attempts.");
            }

        } while (guess != numberToGuess);
    }
}
