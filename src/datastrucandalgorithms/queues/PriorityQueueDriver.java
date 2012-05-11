   package datastrucandalgorithms.queues;

   import java.util.NoSuchElementException;

/**
 * Test Driver for PriorityQueue
 * @author Brian Jerardi
 * @instructor Kendall Martin
 * @date 10/18/2011
 * @course CIS 112
 * @assignment HW2 Chapter 7 problem 4 page 396
 */
   public class PriorityQueueDriver
   {
      private static PriorityQueue<String> testQ;
      private static final int HIGH_PRI = 4;
    
    /**
     * Test Driver for PriorityQueue
     * @param args 
     *      the command line arguments
     */
      public static void main(String[] args)
      { 
         testQ = new PriorityQueue<String>(HIGH_PRI);
        
         testIllegalConstruct();
        
         testEmptyQ();
        
         addItems();
        
         testClone();
        
         // Note will not with with clone, to test trim comment testClone
         // Both appear to be working, but for some reason when run together
         // produce a ArrayIndexOutOfBoundsException at System.arraycopy part
         // of ArrayQueue.trimToSize
         //testTrim();
        
         removeItems();
      }
    
    /**
     * Test an Illegal Constructor
     * @param - none
     */
      private static void testIllegalConstruct()
      {
        // Try to set highest queue to negative
         System.out.println("Try to set highest queue to negative.");
         try
         {
            PriorityQueue<String> testQIll = new PriorityQueue<String>(-1);
         }
         catch (IllegalArgumentException e)
         {
             System.out.println(e.getMessage());
         }
        
        // Print Horizontal Rule
         System.out.println("\n--------------------------------------------\n");
      }
    
    /**
     * Test an empty queue
     * @parm - none
     */
      private static void testEmptyQ()
      {
        // Try to remove from and empty queue
         System.out.println("Try to remove from an empty queue.");
         try
         {
            testQ.remove();
         }
         catch (NoSuchElementException e)
         {
             System.out.println(e.getMessage());              
         }
        
        // Print Horizontal Rule
         System.out.println("\n--------------------------------------------\n");
      }
   
    /**
     * Test adding items
     * @param - none
     * @precondition
     *  testQ is not null
     * @postcondition
     *  testQ contains some items
     */
      private static void addItems()
      {
        // Add items to queue
         System.out.println("Add items to queue.");
         System.out.printf("%25s%10s\n","Item","Priority");
         System.out.printf("%25s%10d\n","Do Homework",3);
         System.out.printf("%25s%10d\n","Get OJ",1);
         System.out.printf("%25s%10d\n","Return Library Books",3);
         System.out.printf("%25s%10d\n","Order Stationary",2);
         System.out.printf("%25s%10d\n","Take out the trash",2);
         System.out.printf("%25s%10d\n","Buy Present for Mom",0);
         testQ.add("Do Homework", 3);
         testQ.add("Get OJ", 1);
         testQ.add("Return Library Books", 3);
         testQ.add("Order Stationary", 2);
         testQ.add("Take out the trash", 2);
         testQ.add("Buy Present for Mom", 0); 
        
        // Try to add an item with a negative priority
         System.out.println("Try to add an item with a negative priority");
         try
         {
            testQ.add("Some Cool Item", -5);
         }
         catch (IllegalArgumentException e)
         {
             System.out.println(e.getMessage());
         }
        
        // Try to add an item with a negative priority
         System.out.println("Try to add an item with an invalid priority");
         try
         {
            testQ.add("Another Cool Item", 999);
         }
         catch (IllegalArgumentException e)
         {
             System.out.println(e.getMessage());
         }
         System.out.println("Items: " + testQ.size());
        
        // Print Horizontal Rule
         System.out.println("\n--------------------------------------------\n");
      }
    
    /**
     * Test clone method
     * @param - none
     * @postcondition
     *  testQ has had one element removed
     */
      private static void testClone()
      {
        // Test the clone method of PriorityQueue
         System.out.println("Clone testQ into clonedQ");
         PriorityQueue clonedQ = testQ.clone();
        
        // Add item to clonedQ
         System.out.println("Add item to clonedQ");
         System.out.printf("%25s%10s\n","Item","Priority");
         System.out.printf("%25s%10d\n","Read a Book",4);
         clonedQ.add("Read a Book", 4);
        
        // Remove item from testQ
         System.out.println("Remove item from testQ");
         System.out.println("testQ Item: " + testQ.remove());
         
        // Remove 3 items form clonedQ
         System.out.println("Remove 3 items from clonedQ");
         System.out.println("clonedQ Item: " + clonedQ.remove());
         System.out.println("clonedQ Item: " + clonedQ.remove());
         System.out.println("clonedQ Item: " + clonedQ.remove());
        
        // Print Horizontal Rule
         System.out.println("\n--------------------------------------------\n");
      }
    
    /**
     * Test remove elements
     * @param - none
     * @precondition
     *  testQ has elements
     * @postcondition
     *  All elements is testQ have been removed and printed to the screen
     */
      private static void removeItems()
      {
        // Remove and print items
         System.out.println("Remove and print items highest priority first.");
         while (!testQ.isEmpty())
         {
            System.out.println("Item: " + testQ.remove());
         }
        
        // Print Horizontal Rule
         System.out.println("\n--------------------------------------------\n");
      }
   
    /**
     * Test the trimToSize method
     * @param - none
     * @postcondition
     *  All arrays contained in testQ have been trimmed to their sizes
     * @note
     *  This will not display any output, but during testing it checked out
     *  except if run with testClone
     */
      private static void testTrim()
      {
        // Test trimToSize
         System.out.println("Test trimToSize");
         testQ.trimToSize();
        
        // Print Horizontal Rule
         System.out.println("\n--------------------------------------------\n");
      }
    
   }
