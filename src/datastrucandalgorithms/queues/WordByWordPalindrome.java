package datastrucandalgorithms.queues;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * Check a sentence to see if it is a word-by-word palindrome
 * @author Brian Jerardi
 * @instructor Kendall Martin
 * @date 10/18/2011
 * @course CIS 112
 * @assignment HW2 Chapter 7 problem 1 page 395
 */
public class WordByWordPalindrome
{
    // Used for getting keyboard input
    private static Scanner keyboard = new Scanner(System.in);
    
    /**
     * UI to check sentences for a word-by-word palindrome
     * @param args 
     *      the command line arguments
     */
    public static void main(String[] args)
    { 
        String sent;
        
        // Do this loop until sent equals ""
        do
        {
            // Get the users sentence
            sent = askForSent();     
            
            // If a sentence was entered check if it is a WBWPalindrome
            if (!sent.equals(""))
            {
                // Print whether the users entered sentence was a WBWPalindrome
                if (isWBWPalindrome(sent))
                {
                    System.out.println("Is a word-by-word Palindrome!");
                }
                else
                {
                    System.out.println("Is NOT a word-by-word Palindrome!");
                }
                System.out.println();
            }            
        }while (!sent.equals(""));
        
        // Print Goodbye
        System.out.println("Goodbye");
        
    }
    
    /**
     * Prompt user to enter a sentence
     * @parm - none
     * @return 
     *  The users entered string
     */
    private static String askForSent()
    {        
        // Prompt User
        System.out.println("Please type a sentence to check "
                + "if it is a word-by-word palindrome.");
        System.out.println("Press the <return> key with no sentences to quit:");
        
        // Return the users entered string
        return keyboard.nextLine();
    }

    /**
     * Check if sent is a word-by-word palindrome
     * @param sent
     *  The sentence to check
     * @return 
     *  true if it is a word-by-word palindrome
     *  false if NOT
     */
    private static boolean isWBWPalindrome(String sent)
    {
        // Set up a queue, stack and array
        Queue<String> q = new LinkedList<String>();
        Stack<String> s = new Stack<String>();
        String[] sentArray;
        
        // Store each word of String into sentArray
        String delims = "[ .,?!:;]+";
        sentArray = sent.split(delims);
        
        // Put each word of string sent into q and s
        for (String word : sentArray)
        {
            q.add(word);
            s.add(word);
        }
        
        // While there are still words in q
        while (!q.isEmpty())
        {            
            // Check if the word from q is NOT the same as the word from s
            if (!q.remove().equalsIgnoreCase(s.pop()))
            {
                return false;                            
            }
        }
        
        // Return true if all the words were the same
        return true;
    }
}
