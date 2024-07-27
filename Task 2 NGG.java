import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    private static final int MAX_ATTEMPTS = 5;

    public static void main(String[] args) {
        // Create a Random object to generate a random number
        Random random = new Random();
        // Generate a random number between 1 and 100
        int numberToGuess = random.nextInt(100) + 1;
        // Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        int attempts = 0;
        boolean hasGuessedCorrectly = false;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("Guess the number between 1 and 100.");

        while (attempts < MAX_ATTEMPTS && !hasGuessedCorrectly) {
            System.out.print("Enter your guess: ");
            // Ensure the user inputs an integer
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and 100.");
                scanner.next(); // Clear the invalid input
                System.out.print("Enter your guess: ");
            }
            int userGuess = scanner.nextInt();
            
            if (userGuess < 1 || userGuess > 100) {
                System.out.println("Please enter a number between 1 and 100.");
                continue; // Skip to the next iteration of the loop
            }
            
            attempts++;

            // Compare the user's guess with the generated number
            if (userGuess < numberToGuess) {
                System.out.println("Too low! Try again.");
            } else if (userGuess > numberToGuess) {
                System.out.println("Too high! Try again.");
            } else {
                hasGuessedCorrectly = true;
                System.out.println("Congratulations! You've guessed the number.");
            }

            // Provide feedback on the number of remaining attempts
            if (!hasGuessedCorrectly) {
                int remainingAttempts = MAX_ATTEMPTS - attempts;
                if (remainingAttempts > 0) {
                    System.out.println("You have " + remainingAttempts + " attempts left.");
                } else {
                    System.out.println("Sorry, you've run out of attempts. The number was " + numberToGuess + ".");
                }
            }
        }

        // Close the scanner
        scanner.close();
    }
}
