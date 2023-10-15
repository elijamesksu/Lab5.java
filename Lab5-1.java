/*
eli james lab b thurs @ 1:05
cis200
Assignment description
Read the contents of words.txt into a String array.
2) Pick a random word from the array (you will need to use a random number generator).
3) Using StringBuilder, randomly “jumble” the characters in the chosen word. The given Java file contains a
suggestion for how to jumble the letters.
4) Display the jumbled word and repeatedly ask the user to guess the original word. Continue until the user
guesses correctly (up to 5 guess max) and then display out how many tries it took them (if 5 or less) otherwise
display “Maximum number of guesses reached - word was” and display word.
5) Ask if they want to run the program again
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Lab5 {
    public static void main(String[] args) {
       
        String[] words = readWordsFromFile("Lab5_Words.txt");// reading words from the file into an array string

        Random random = new Random(); // RANDOM NUMBER GENERATOR

        Scanner sc = new Scanner(System.in);// scanner created for user input

        // Main(single entry and exit attempt to implement
        boolean playAgain = true;
        while (playAgain) {
            String theRandomWord = words[random.nextInt(words.length)];//picking a random word from the array 
            String jumbledWord = jumbleWord(theRandomWord);// jumbling the characters in the word

            System.out.println("Unjumbled word: " + theRandomWord);
            System.out.println("Jumbled word: " + jumbledWord);
            System.out.println();// adding space tab

            int attempts = 0;
            boolean youGuessedCorrectly = false;

            while (attempts < 5) {
                System.out.print("Enter a guess: ");
                String guess = sc.nextLine();

                if (guess.equalsIgnoreCase(theRandomWord)) {
                    youGuessedCorrectly = true;
                    break;
                } else {
                    System.out.println("Incorrect. Try again");
                    attempts++;
                }
            }

            if (youGuessedCorrectly && attempts != 0) {
                System.out.println("You guessed it! It took you " + (attempts + 1) + " guesses.");
            } 
            else if(youGuessedCorrectly && attempts == 0) {
            	System.out.println("You guessed it! It took you " + (attempts + 1) + " guess.");
            	}
            else {
                System.out.println("Maximum number of guesses reached. Word was " + theRandomWord);
            }

            System.out.print("Run again? (Y/N): ");
            String playAgainInput = sc.nextLine();
            playAgain = playAgainInput.equalsIgnoreCase("Y");
        }
        sc.close();
        System.out.println("Goodbye! Have a good day!");
    }

    // my method to  read from the files into a string array 
    private static String[] readWordsFromFile(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();
            int numWords = Integer.parseInt(line);
            String[] words = new String[numWords];
            for (int i = 0; i < numWords; i++) {
                words[i] = reader.readLine();
            }
            reader.close();
            return words;
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    //,my own created method to jumble the characters in one word..private static only want accessible in the declared class
    private static String jumbleWord(String word) {
    	StringBuilder jumbledWord = new StringBuilder(word);
        Random random = new Random();
        int length = word.length();
        for (int i = 0; i < length ; i++) {
            int j = random.nextInt(length);
            char temp = jumbledWord.charAt(i);
            jumbledWord.setCharAt(i, jumbledWord.charAt(j));
           jumbledWord.setCharAt(j, temp);
        }
        return jumbledWord.toString();
    }
}