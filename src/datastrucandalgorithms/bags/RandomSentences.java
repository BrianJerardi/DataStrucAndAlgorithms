package datastrucandalgorithms.bags;

import edu.colorado.collections.LinkedBag;
import java.util.Scanner;

/**
 * Ask the user for 10 nouns and 10 verbs and creates random sentences
 * @author Brian Jerardi
 * @instructor Kendall Martin
 * @date 10/16/2011
 * @course CIS 112
 * @assignment HW2 Chapter 5 problem 3 page 301
 */
public class RandomSentences
{ 
    // Used for getting keyboard input
    private static Scanner keyboard = new Scanner(System.in);
    
    /**
     * Generates random sentences
     * @param args 
     *      the command line arguments
     */
    public static void main(String[] args)
    {   
        // How many words to ask for
        final int WORDS = 10;
        // How many sentences to print
        final int SENTS = 5;
        
        // Bags for storing nouns and verbs
        LinkedBag<String> nouns = new LinkedBag<String>();
        LinkedBag<String> verbs = new LinkedBag<String>();
    
        // Ask the user for nouns
        askForWords(nouns, WORDS, "nouns");
        
        // Ask the user for verbs
        askForWords(verbs, WORDS, "verbs");
        
        // Print horizontal rule
        System.out.println("-----------------------------------");
        
        // Print Sentences
        printSentences(nouns, verbs, SENTS);
    }

    /**
     * Ask the user for the given numbers of words and store them in a bag
     * @param bag
     *  The bag to store the words in
     * @param count
     *  The number of words to ask for
     * @param prompt 
     *  The type of word to ask for
     * @precondition
     *  count is greater than 0
     * @postcontion
     *  count number of strings have been read using keyboard.next,
     *  and these strings have been placed in bag
     * @throws IllegalArgumentException
     *  count is less than 1
     */
    private static void askForWords
            (LinkedBag<String> bag, int count, String prompt)
    {
        // For storing user input
        String userInput;
        
        // If count is less than 1 throw IllegalArgumentException
        if (count < 1)
        {
            throw new IllegalArgumentException
                    ("Count is: " + count + ", but most be positive.");
        }
        
        // Prompt User for input
        System.out.println("Please type " + count + " " + prompt 
                + ", separated by spaces.");
        System.out.println("Press the <return> key after the final entry:");
        
        // Get users input
        for (int i = 0; i < count; i++)
        {
            userInput = keyboard.next();
            bag.add(userInput);
        }
        
        // Clear Input in case more than count inputs were received
        keyboard.nextLine();
        
        // Print blank line
        System.out.println();
    }

    /**
     * Print a number of random sentences to the screen
     * @param nouns
     *  The nouns to use when generating sentences
     * @param verbs
     *  The verbs to use when generating sentences
     * @param sentCount 
     *  The number of sentences to print
     * @precondition
     *  nouns and verbs are not empty
     *  sentCount is greater than 0
     * @postcontion
     *  sentCount number of sentences have been printed to the screen
     * @throws IllegalStateException
     *  noun or verb bags are empty
     */
    private static void printSentences
            (LinkedBag<String> nouns, LinkedBag<String> verbs, int sentCount)
    {
        // Print header
        System.out.println(sentCount + " random sentences.\n");
        
        // Print random sentences
        for(int i = 0; i < sentCount; i++)
        {
            System.out.println("The " + nouns.grab() + " " 
                    + verbs.grab() + " the " + nouns.grab() + ".");
        }
    }
    
}
