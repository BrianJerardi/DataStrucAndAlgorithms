   package datastrucandalgorithms.linkedlist;

   import edu.colorado.nodes.IntNode;
   import java.util.Random;

/**
 * Write a line to the screen containing all integers in the list that are 
 * between the first occurrence of x and the first occurrence of y
 * @author Brian Jerardi
 * @instructor Kendall Martin
 * @date 9/26/2011
 * @course CIS 112
 * @assignment HW2 Chapter 4 problem 3 page 239
 */
   public class LineList
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
         // Sets x and y to random ints 1-10
         int x = rand.nextInt(10) + 1;
         int y = rand.nextInt(10) + 1;
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
         
        // Print out the numbers between x and y
         System.out.println("Numbers to print between: " + x + ", " + y);
         printLine(head, x, y);
      
      
      }
    
      /**
       * Print a line of numbers between x and y
       * @param head
       *    The start of the list
       * @param x
       *    Numbers printed out must be after x
       * @param y 
       *    Numbers printed out will stop before y
       * @precondition
       *    head is part of a linked list
       * @postcondition
       *    A line will be printed to the screen containing all the integers
       *    between x and y of the list of head
       * @notes
       *    does not print inclusive (ex. List 1, 2, 3; x = 1; y = 3; Print 2)
       *    first occurrence of y is before first occurrence of x: Print nothing
       *    x == y: Print nothing
       *    x is not in the list: Print nothing
       *    y is not in the list: Print everything after x
       *    x and y are not in the list: Print nothing
       */
      public static void printLine(IntNode head, int x, int y)
      {
         // Set the cursor to head
         IntNode cursor = head;
         
         // Boolean conditions to indicate if the start condition 
         // and end condition have been found
         boolean startCondition = false;
         boolean endConition = false;
         
         // Loop through the list until cursor is null
         while (cursor != null)
         {           
            // If the end condition is found set endCondition to true
            if (cursor.getData() == y)
            {
               endConition = true;
            }
            
            // If the start condition has been found and the end condition
            // has not been found print out the cursors data
            if (startCondition && !endConition)
            {
               System.out.print(cursor.getData() + "\t");                
            }        
            
            // If the start condition is found set startCondition to true
            if(cursor.getData() == x)
            {
               startCondition = true;
            }   
            
            // Move to the next node in the list
            cursor = cursor.getLink();
         }   
         System.out.println("");
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
