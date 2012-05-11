   package datastrucandalgorithms.linkedlist;

/**
 * Test PolyNode and PolyLinkedList Classes
 * @author Brian Jerardi
 * @instructor Kendall Martin
 * @date 10/02/2011
 * @course CIS 112
 * @assignment HW2 Chapter 4 problem 13 page 239
 */
   public class PolyDriver
   {   
    // Instantiate a Poly Linked list
      private static PolyLinkedList l1;
    
    /**
     * Test PolyNode and PolyLInkedList Classes
     * @param args 
     *      the command line arguments
     */
      public static void main(String[] args)
      {  
         l1 = new PolyLinkedList();
        // Uncomment the below to test different constructors
        // Warning these test are not set up to work with these constructors
        //l1 = new PolyLinkedList(1.0);
        //l1 = new PolyLinkedList(new PolyNode(0, 1.0, null));
        
        // Test Node
         testNode();
        
        // Test Adding and Sorting
         testAdding();
        
        // Test Clone
         testClone();       
        
        // Test add_to_coef and assign_coef
         testCoef();
        
        // Test clear and reserve methods
         testClearAndReserve();        
        
        // Test Methods coefficient degree and next_term
         testAccessors();
        
        // Test Evaluation Method
         testEval();
        
        // Test Arithmetic Operators
         testArith();
        
        // Test derivative
         testDerivative();
        
        // Test integral
         testIntegral();
        
        // Test find_root
         textFind_Root();
      }  
   
    /**
     * Print a list to the screen
     * @param listToPrint 
     *  The PolyLinkedList to be printed
     * @precondition listToPrint is not null
     */
      private static void printList(PolyLinkedList listToPrint)
      {
        // Print out size of listToPrint and listToPrint list
         System.out.println("Size: " + listToPrint.getSize());
         System.out.println("List: ");
         listToPrint.printList();
         System.out.println("");
      }
    
    /**
     * Print the items in the list of head
     * @param head 
     *  The head of the list to print
     */
      public static void printItemsInList(PolyNode head)
      {
         System.out.println("All Items in list: ");
         for(PolyNode cursor = head; cursor != null; cursor = cursor.getLink())
         {
            System.out.println(cursor);
         }
         System.out.println("");
      }
    
    /**
     *  Test PolyNode Class
     * @parm - none
     */
      private static void testNode()
      {
         System.out.println("---TEST PolyNode CLASS---");
         System.out.println("");
        
         System.out.println("Start new list with exponent 0 and coe 1.0");
         PolyNode head = new PolyNode(0, 1, null);
         printItemsInList(head);
        
        // Add term after head
         System.out.println("Add term 0.3x^3");
         head.addNodeAfter(3, 0.3);
         printItemsInList(head);
        
        // Add term after head
         System.out.println("Add term 0.5x^2");
         head.addNodeAfter(2, 0.5);
         printItemsInList(head);
        
        // Add term after head
         System.out.println("Add term -0.9x^1");
         head.addNodeAfter(1, -0.9);     
         printItemsInList(head);
        
        // Remove term after head
         System.out.println("Remove node after head");
         head.removeNodeAfter();
         printItemsInList(head);
        
        // Print number of items, item at position 2, and item with exponent 2
         System.out.println("Numbers of Items: " + PolyNode.listLength(head));
         System.out.println("Item at position 2: " + PolyNode.listPosition(head, 2));
         System.out.println("Item with exponent 2: " + PolyNode.listSearch(head, 2));
         System.out.println("");
      }
    
    /**
     * Test the adding of new nodes to make sure they are sorted as inserted
     * @param - none
     * @precondition
     *  list l1 has been declared
     * @postcondition
     *  list l1 contains nodes 0.3x^3, 0.5^2, -0.9^1, 1.0^0
     */
      private static void testAdding()
      {             
         System.out.println("---TEST ADDING AND SORTING---");
         System.out.println("");
        
        // Add nodes to the list
         System.out.println("Adding nodes to the list");
         l1.addNode(0, 1.0);
         l1.addNode(3, 0.3);
         l1.addNode(1, -0.9);
         l1.addNode(2, 0.5);
         System.out.println("");
        
        // Try to add a node with an exponent that already exists
         System.out.println("Try to add a node with an exponent that already "
                + "exists");
         try
         {
            l1.addNode(1, 5.4);
         }
            catch (IllegalArgumentException e)
            {
               System.out.println(e.getMessage());
            }
         System.out.println("");
        
        // Print out size of l1 and l1 list
         System.out.println("List 1:");
         printList(l1);
      }
   
   
    /**
     * Test the clone method by cloning l1 into l2 then adds a node to l2;
     * and prints out both lists to see the difference
     * @param - none 
     * @precondition
     *  list l1 has been declared and is not null
     */
      private static void testClone()
      {
        
         System.out.println("---TEST CLONE---");
         System.out.println("");
        
        // Test clone
         PolyLinkedList l2 = l1.clone();
        
        // Add node to l2
         System.out.println("Add node -0.7x^4 to list l2\n");
         l2.addNode(4, -0.7);
        
        // Print out size of l1 and l1 list
         System.out.println("List 1:");
         printList(l1);
        
        // Print out size of l2 and l2 list
         System.out.println("List 2:");
         printList(l2); 
      }
   
    /**
     * Test the methods add_to_coef and assign_coef
     * @param - none
     * @precondition
     *  list l1 has exponents of 2 and 3
     * @postcondition
     *  list l1 term's have changed to 1.0x^2 and -0.2x^3
     */
      private static void testCoef()
      { 
         System.out.println("---TEST METHODS add_to_coef and assign_coef---");
         System.out.println("");
        
        // Add 1.0 to the coef of the term x^2
         System.out.println("Add 1.0 to the coef of the term x^2");
         l1.add_to_coef(1.0, 2);
         System.out.println(l1.getLastAccessed());
         System.out.println("");
        
        // Try to add to a coefficient of a term with an exponent that doesn't
        // exist
         System.out.println("Try to add to a coefficient of a term with an "
                + "exponent that doesn't exist");
         try
         {
            l1.add_to_coef(1.0, 10);
         }
            catch (IllegalArgumentException e)
            {
               System.out.println(e.getMessage());
            }
         System.out.println("");
        
        // Set coef to -0.2 of the term x^3
         System.out.println("Set coef to -0.2 of the term x^3");
         l1.assing_coef(-0.2, 3);
         System.out.println(l1.getLastAccessed());        
         System.out.println("");
        
        // Try to set a coefficient of a term with an exponent that doesn't
        // exist
         System.out.println("Try to set a coefficient of a term with an "
                + "exponent that doesn't exist");
         try
         {
            l1.assing_coef(1.0, 10);
         }
            catch (IllegalArgumentException e)
            {
               System.out.println(e.getMessage());
            }
         System.out.println("");
      }
   
    /**
     * Test the clear and reserve methods
     * @param - none
     * @precondition
     *  list l1 is not null
     * @postcondition
     *  list l1 is now 0.0x^0, 7.0x^1, 0.0x^2, 0.0x^3, 0.0x^4, 2.0x^5, 0.0x^6,
     *  6.0x^7,,0.0x^8, 0.0x^9, 0.0x^10
     */
      private static void testClearAndReserve()
      {
         System.out.println("---TEST CLEAR and RESERVE---");
         System.out.println("");
        
        // Clear list
         System.out.println("Clear List");
         l1.clear();
        
        // Print out size of l1 and l1 list
         System.out.println("List 1:");
         printList(l1);
        
        
        // Set up reserve test
         System.out.println("Add terms to list l1: 7.0x^1, 2.0x^5.0, 6.0x^7\n");
         l1.addNode(5, 2);
         l1.assing_coef(7, 1);
         l1.addNode(7, 6);
        
        // Reserve Space
         System.out.println("Reserving Space for up to 10 terms");
         l1.reserve(10);
        
        // Print out size of l1 and l1 list
         System.out.println("List 1:");
         printList(l1);
      }
   
    /**
     * Test the assessor methods
     * @param - none
     * @precondition
     *  list l1 is not null
     * @postcondition
     *  list l1 contains terms 1.0x^0, 0.0x^1, 0.0x^2, 0.3x^3, 0.0x^7
     */
      private static void testAccessors()
      {
        // Reset the list for this test
         resestListForTest();
        
         System.out.println("---TEST ACESSSORS--");
         System.out.println("");
        
        // Test coefficient method
         System.out.println("Get the coefficient of the x^2 term");
         System.out.println("Coefficient: " + l1.coefficient(2));
         System.out.println("");
        
        // Set up Test of degree
         System.out.println("Add term 2.0x^7 for the degree test");
         l1.addNode(7, 2.0);
        
        // Test degree method        
         System.out.println("Get the degree");
         System.out.println("Degree: " + l1.degree());
         System.out.println("");
        
        // Set up Test for next_term method
         System.out.println("Clear l1 and assign terms 1.0x^0 and 0.3x^3");
         l1.clear();
         l1.assing_coef(1.0, 0);
         l1.assing_coef(0.3, 3);
        
        // Test next_term method
         System.out.println("Get the exponent of the next non-zero term after x^1");
         System.out.println("Exponent: " + l1.next_term(1));
         System.out.println("");
        
        // Try to get the next_term after a term where the exponent doesn't 
        // exist
         try
         {
            System.out.println("Try to get the next_term after a term where "
                    + "the exponent doesn't exist");
            System.out.println("Exponent: " + l1.next_term(10));
         }
            catch (Exception e)
            {
               System.out.println(e.getMessage());
            }        
         System.out.println("");
      }
   
    /**
     * Test eval method
     * @parm - none
     * @precondition
     *  l1 is not null
     */
      private static void testEval()
      {
        // Reset the list for this test
         resestListForTest();
        
         System.out.println("---TEST EVAL---");
         System.out.println("");
        
        // Test eval method
         System.out.println("Evalulate l1 for x = 2");
         System.out.println("Answer: " + l1.eval(2));        
         System.out.println("");
      }
   
    /**
     * Test the arithmetic methods of PolyLinkedList; 
     * polyAddition, polySubtraction, and polyMultiplication
     * @param - none
     */
      private static void testArith()
      {
         System.out.println("---TEST ARITHMETIC---");
         System.out.println("");
        
        // Instantiate q and r PolyLinkedList's
         PolyLinkedList q = new PolyLinkedList();
         PolyLinkedList r = new PolyLinkedList();
        
        // Add nodes to q
         q.addNode(0, 1.0);
         q.addNode(1, 3);
         q.addNode(2, 4);
         q.addNode(3, 2);
        
        // Add nodes to r
         r.addNode(0, 5);
         r.addNode(1, 6);
         r.addNode(2, 7);
        
        // Print q and r
         System.out.println("List q: ");
         printList(q);
         System.out.println("List r: ");
         printList(r);
        
        // Add q and r
         System.out.println("q + r = ");
         printList(PolyLinkedList.polyAddition(q, r));
        
        // Subtract r from q
         System.out.println("q - r = ");
         printList(PolyLinkedList.polySubtraction(q, r));
        
        // Multiply q x r
         System.out.println("q * r = ");
         printList(PolyLinkedList.polyMultiplication(q, r));              
      }
    
    /**
     * Reset list l1 to it's starting values for accurate testing
     * @param - none
     * @postcondition
     *  list l1 contains nodes 0.3x^3, 0.5^2, -0.9^1, 1.0^0
     */
      private static void resestListForTest()
      {
         System.out.println("---RESET LIST L1---");
         System.out.println("");
        
        // Construct new list
         System.out.println("Construct a new list in l1");
         l1 = new PolyLinkedList();
        
        // Add nodes to list
         System.out.println("Adding nodes to the list");        
         l1.addNode(0, 1.0);
         l1.addNode(3, 0.3);
         l1.addNode(1, -0.9);
         l1.addNode(2, 0.5);
        
        // Print out size of l1 and l1 list
         System.out.println("List 1:");
         printList(l1);  
      }
   
    /**
     * Test the derivative method
     * @param - none
     */
      private static void testDerivative()
      {
         resestListForTest();
      
         System.out.println("---DERIVATIVE TEST---");
         System.out.println("");
        
        // Print derivative 1
         System.out.println("Derivative 1: ");
         printList(l1.derivative(1));
        
        // Print derivative 2
         System.out.println("Derivative 2: ");
         printList(l1.derivative(2));
        
        // Print derivative 3
         System.out.println("Derivative 3: ");
         printList(l1.derivative(3));
        
        // Print derivative 4
         System.out.println("Derivative 4: ");
         printList(l1.derivative(4));       
      }
   
    /**
     * Test the Integral method
     * @param - none
     */
      private static void testIntegral()
      {
         resestListForTest();
      
         System.out.println("---INTEGRAL TEST---");
         System.out.println("");
        
        // Print integral 1
         System.out.println("Integral 1: ");
         printList(l1.integral(1));
        
        // Print integral 2
         System.out.println("Integral 2: ");
         printList(l1.integral(2));
        
        // Print integral 3
         System.out.println("Integral 3: ");
         printList(l1.integral(3));
        
        // Print integral 4
         System.out.println("Integral 4: ");
         printList(l1.integral(4));    
      }
   
    /**
     * Test the find_root method
     * @parm - none
     */
      private static void textFind_Root()
      {
         PolyLinkedList rootTestList = new PolyLinkedList();
        
         System.out.println("---FIND_ROOT TEST---");
         System.out.println("");
        
        // Add nodes for test
         rootTestList.addNode(0, 1.0);
         rootTestList.addNode(1, -1.0);
         rootTestList.addNode(3, 1.0);
        
         System.out.println("Root Test List");
         printList(rootTestList);
         try
         {
            System.out.println("Root: " + rootTestList.find_root(-1, 7, 0.00000001));
         }
            catch (IllegalStateException e)
            {
               System.out.println(e.getMessage());
            }     
      }
    
   }
