package datastrucandalgorithms;

import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * A command line menu to run sample programs
 * @author Brian Jerardi
 * @date 05/10/2012
 */
public class DataStrucAndAlgorithms 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // Array of Class Names to call
        String[] classNames = {
            "datastrucandalgorithms.bags.IntCircularLinkedBagDriver",
            "datastrucandalgorithms.bags.RandomSentences",
            "datastrucandalgorithms.bags.StringKeyedBagDriver",
            "datastrucandalgorithms.linkedlist.LinkedListSplit",
            "datastrucandalgorithms.linkedlist.PolyDriver",
            "datastrucandalgorithms.sequence.DoublyLinkedSeqDriver",
            "datastrucandalgorithms.queues.PriorityQueueDriver",
            "datastrucandalgorithms.queues.WordByWordPalindrome",
            "datastrucandalgorithms.trees.CBTBagDriver", 
            "datastrucandalgorithms.trees.CBTBag2Driver"};
        int userSelection;
        
        // Display the Menu and get user input until valid
        do
        {
            displayMenu(classNames);
            userSelection = getUserChoice(classNames.length);   
            System.out.println("");
        } while(userSelection == -1);
        
        // If the exit choice was not chosen Run user selected Program
        if(userSelection != classNames.length)
        {
            try
            {
                Class<?> cls = Class.forName(classNames[userSelection]);
                Method meth = cls.getMethod("main", String[].class);
                displayClassName(classNames[userSelection]);
                meth.invoke(null, (Object) args);
            }
            catch (Exception ex)
            {
                System.out.println(ex);
            }          
        }
        
        System.out.println("\nGoodbye");

    }
    
    /**
     * Displays the menu
     * @param classNames 
     *  The classes that will be diplayed in the menu
     */
    private static void displayMenu(String[] classNames)
    {
        int i = 1;
        
        // Display Menu
        System.out.println("Brian Jerardi's Data Structures and Algorithms");
        for (String cName : classNames)
        {
            System.out.println(i + ") " + cName);
            i++;
        }
        
        // Display Menu Exit
        System.out.println(i + ") Exit");
    }
    
    /**
     * Gets the users selection
     * @return 
     *  The array value coresponding to the users selection
     *  -1 if no valid selection was chosen
     */
    private static int getUserChoice(int choiceCount)
    {
        int userSelection;
                
        // Used for getting keyboard input
        Scanner keyboard = new Scanner(System.in);
        
         // Get user Input
        System.out.print("Run: ");
        try
        {
            userSelection = keyboard.nextInt();
        }
        catch (java.util.InputMismatchException ex)
        {
            System.out.println("Please enter an integer");
            return -1;
        }
        
        if (userSelection <= 0 || userSelection > choiceCount + 1)
        {
            System.out.println("Invalid Choice");
            return -1;
        }
        
        return --userSelection;
    }
    
    private static void displayClassName(String className)
    {
        // Stores the board for printing the name
        String boarder = "";
        
        // Add *'s to the board to be equal to the name length + 4
        for (int i = 0; i < className.length() + 4; i++)
        {
            boarder += "*";
        }
        
        // Print the name with boarder
        System.out.println(boarder);
        System.out.println("* " + className + " *");
        System.out.println(boarder);
        System.out.println("");
    }

    
}
