//Skylee Blaine

//IMPROVEMENT: adding points to the correctly  guessed words 

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ScrabbleGame {
    public static void main(String[] args) {
        // Initialize 
        ArrayList<Word> words = new ArrayList<>();

        // reading from the collins words file
        try (Scanner scanner = new Scanner(new File("CollinsScrabbleWords_2019.txt"))) {
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine().trim().toLowerCase();
                words.add(new Word(word));
            }
        } catch (FileNotFoundException e) {//if the file does not exist and/or cant be found
            System.out.println("File not found!");
            return; 
        }

        // WIll produce 4 random letter for the user to unscramble
        //using a string
        String randLetters = "";  
        String letters = "abcdefghijklmnopqrstuvwxyz";
        Random rand = new Random();
        for (int i = 0; i < 4; i++) {//4 letters
            randLetters += letters.charAt(rand.nextInt(letters.length())); // append to the random string
        }
        System.out.println("Random letters: " + randLetters);

        // Create a scanner to get input from the user
        Scanner inputScanner = new Scanner(System.in);
        System.out.print("Enter a word: ");
        String userInput = inputScanner.nextLine().trim().toLowerCase();//case insensitive

        // Binary search, checks if the word exists in the txt file
        //IMPROVEMENT: adding points to the words 
        int index = binarySearch(words, userInput);
        if (index >= 0) {
            int points = userInput.length() - 3; 
            System.out.println("Thats is a word! It is worth " + points + " points.");
        } else {
            System.out.println("Sorry, not a valid word!");
        }
    }
    // binary search method
    public static int binarySearch(ArrayList<Word> words, String target) {
        int left = 0;
        int right = words.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            String midWord = words.get(mid).getWord();

            if (midWord.equals(target)) {
                return mid;
            }
            if (midWord.compareTo(target) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1; // Word is not found on the list
    }
}

