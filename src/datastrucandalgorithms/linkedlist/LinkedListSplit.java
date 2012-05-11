package datastrucandalgorithms.linkedlist;

import edu.colorado.nodes.IntNode;
import java.util.Random;

/**
 * Create two lists from an original list based on a splitting value
 * @author Brian Jerardi
 * @instructor Kendall Martin
 * @date 9/27/2011
 * @course CIS 112
 * @assignment HW2 Chapter 4 problem 6 page 239
 */
public class LinkedListSplit
{
    /**
     * Test this class class
     * @param args 
     *      the command line arguments
     */
      public static void main(String[] args)
      {
         Random rand = new Random();
          
         // Declare and initialize an int for loop 10-20 
         // (determines how many nodes there will be)
         int loop = rand.nextInt(10) + 10;
         // Sets up an array to store ints for node elements
         int num[] = new int[loop];
         // Declare and initialize a random int from 1-10 to use for splinting
         int split = rand.nextInt(10) + 1;
         // Set up a IntNode cursor for goign through the list
         IntNode cursor;
        
         // Fills num array with random integers
         fillNum(num);
         
         // Set up the head of the list
         IntNode head = new IntNode(num[0], null);  
        
         // Add nodes after head from num array
         for (int i = 1; i < num.length; i++)
         {
            head.addNodeAfter(num[i]);
         }
        
         // Print the numbers in the list
         System.out.println("Print Numbers in linked list: ");
         // Set the cursor to head
         cursor = head;
         // Loop through the list until cursor is null
         while (cursor != null)
         {         
            // Print out the data from the cursor
            System.out.print(cursor.getData() + "\t");
            // Move to the next node in the list
            cursor = cursor.getLink();
         }
         System.out.println("");

         // Get two heads split by the split int
         IntNode twoLists[] = split(head, split);

         // Print first list that was less than split
         System.out.println("List Less than " + split + ": ");
         cursor = twoLists[0];
         while (cursor != null)
         {            
             System.out.print(cursor.getData() + "\t");
             cursor = cursor.getLink();
         }
         System.out.println("");
        
         // Print second list that contains everything else
         System.out.println("List Rest: ");
         cursor = twoLists[1];
         while (cursor != null)        
         {            
             System.out.print(cursor.getData() + "\t");
             cursor = cursor.getLink();
         }
         System.out.println("");
      
      }
            

      /**
       * Split a linked list putting numbers below splittingValue in one list
       *    and the rest of the numbers in another list
       * @param head
       *    The head of the list to split (Remains intact)
       * @param splittingValue
       *    The value to use when splitting the elements
       * @precondition
       *    head is a valid reference to the head of a list
       * @postcondion
       *    The original list is unchanged, and two new list are created
       *    using the splitting value
       * @return 
       *    An array of two IntNode heads
       *    The first contains the head of the list with values less than
       *    the splitting value
       *    The second contains the head of the list containing all the other 
       *    values
       */
        private static IntNode[] split(IntNode head, int splittingValue)
        {
             // Set the cursor to head
             IntNode cursor = head;
             // Instantiate headLess and headRest to null
             // They will store the head values of of the two returned lists
             IntNode headLess = null;
             IntNode headRest = null;


             // Loop through the list
             while (cursor != null)
             {           
                 // If the data in the current node is less than splittingValue
                 // put it into the list of headLess
                 if (cursor.getData() < splittingValue)
                 {
                     // If there are no values in headLess list put this one
                     // into that list with a link to null
                     if (headLess == null)
                     {
                         headLess = new IntNode(cursor.getData(), null);
                     }
                     // If there are already values in the headLess list
                     // add this one after the head
                     else
                     {
                         headLess.addNodeAfter(cursor.getData());
                     }

                 }
                 // If the data in the current node wasn't less than 
                 // splittingValue put it into the list of headRest
                 else
                 {
                     // If there are no values in headRest list put this one 
                     // into that list with a link to null
                     if (headRest == null)
                     {
                         headRest = new IntNode(cursor.getData(), null);
                     }
                     // If there are already values in the headRest list
                     // add this one after the head
                     else
                     {
                         headRest.addNodeAfter(cursor.getData());
                     }

                 }

                // Move cursor to the next link
                cursor = cursor.getLink();
             }   

             // Put the two heads into answer array and return it
             IntNode answer[] = {headLess, headRest};
             return answer;
        }
        
        /**
       * Fill array num with random integers
       * @param num 
       *    The array to fill
       * @postcondition
       *    num is filled with random integers from 1-10
       */
      private static void fillNum(int[] num)
      {
         Random rand = new Random();
         for (int i = 0; i < num.length; i++)
         {
            num[i] = rand.nextInt(10) + 1;
         }
      }
    
}
