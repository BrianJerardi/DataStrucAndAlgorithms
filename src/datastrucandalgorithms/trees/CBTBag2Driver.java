package datastrucandalgorithms.trees;

/**
 * A driver to test CBTBag2
 * @author Brian Jerardi
 * @instructor Kendall Martin
 * @date 11/01/2011
 * @course CIS 112
 * @assignment HW2 Chapter 9 problem 2 page 507 rewrite
 */
public class CBTBag2Driver
{    
     private static CBTBag2 testBag;
    
    /**
     * Test Driver for CBTBag2
     * @param args 
     *  the command line arguments
     */
    public static void main(String[] args)
    { 
        testBag = new CBTBag2();
        
        testOnEmpty();
        
        createBag();
        
        testAddMulti();
        
        testCount();   
       
        testGetLeftAndRightMost();
        
        testPrintTree();
        
        testClone();            
        
        testRemove();
    }

    /**
     * Test methods on an empty bag
     * @param - none
     * @precondition
     *  testBag has been instantiated
     * @postcondition
     *  testBag is unchanged
     */
    private static void testOnEmpty()
    {
        System.out.println("--- TEST METHODS ON AN EMPTY BAG ---\n");
        
        System.out.println("testBag Empty: " + testBag.isEmpty());
        
        System.out.println("Try to get the leftmost element");
        try
        {
            testBag.getLeftmostData();
        }
        catch (IllegalStateException e)
        {
            System.out.println(e.getMessage());
        }
        
        System.out.println("Try to get the rightmost element");
        try
        {
            testBag.getRightmostData();
        }
        catch (IllegalStateException e)
        {
            System.out.println(e.getMessage());
        }             
        
        System.out.println("Try to remove the leftmost element");
        try
        {
            testBag.removeLeftmost();
        }
        catch (IllegalStateException e)
        {
            System.out.println(e.getMessage());
        }
        
        System.out.println("Try to remove the rightmost element");
        try
        {
            testBag.removeRightmost();
        }
        catch (IllegalStateException e)
        {
            System.out.println(e.getMessage());
        }
        
        System.out.println("Try to use inorder print");
        try
        {
            testBag.inorderPrint();
        }
        catch (IllegalStateException e)
        {
            System.out.println(e.getMessage());
        }
        
        System.out.println("Try to use postorder print");
        try
        {
            testBag.postorderPrint();
        }
        catch (IllegalStateException e)
        {
            System.out.println(e.getMessage());
        }
        
        System.out.println("Try to use preorder print");
        try
        {
            testBag.preorderPrint();
        }
        catch (IllegalStateException e)
        {
            System.out.println(e.getMessage());
        }
        
        System.out.println("Try to print");
        try
        {
            testBag.print();
        }
        catch (IllegalStateException e)
        {
            System.out.println(e.getMessage());
        }
      
        System.out.println("");
        
        
        System.out.println("--- END TEST METHODS ON AN EMPTY BAG ---\n");
    }

    /**
     * Test adding elements to the bag
     * @param - none
     * @precondition
     *  testBag has been instantiated
     * @postcondition
     *  5 and 6 have been added to the bag
     */
    private static void createBag()
    {
        System.out.println("--- TEST CREATING A BAG ---\n");
        
        System.out.println("Size: " + testBag.size());
        System.out.println("");
        
        System.out.println("Add 5 to testBag");
        testBag.add(5);
        
        System.out.println("testBag:");
        testBag.print();
        System.out.println("Size: " + testBag.size());
        System.out.println("");
        
        System.out.println("Add 6 to testBag");
        testBag.add(6);
        
        System.out.println("testBag:");
        testBag.print();
        System.out.println("Size: " + testBag.size());
        System.out.println("");
        
        System.out.println("--- END TEST CREATING A BAG ---\n");
    }

    /**
     * Test the addMany, addAll and static union methods
     * @param - none
     * @precondition
     *  testBag has been created
     * @postcondition
     *  testBag contains 5, 6, 7, 3, and 5
     */
    private static void testAddMulti()
    {
        CBTBag2 testBag2 = new CBTBag2();
        CBTBag2 testBag3 = new CBTBag2();
        CBTBag2 testBag4 = new CBTBag2();
        CBTBag2 testBag5 = new CBTBag2();
        
        System.out.println("--- TEST ADDING MULTIPLE ELEMENTS ---\n");
        
        
        System.out.println("Add 7 and 3 to testBag");
        testBag.addMany(7, 3);
        
        System.out.println("testBag:");
        testBag.print();
        System.out.println("Size: " + testBag.size());
        System.out.println("");
        
        System.out.println("Add 5 to testBag2");
        testBag2.add(5);
        
        System.out.println("testBag2:");
        testBag2.print();
        System.out.println("Size: " + testBag2.size());
        System.out.println("");
        
        System.out.println("Add testBag2 to testBag");
        testBag.addAll(testBag2);
        
        System.out.println("testBag:");
        testBag.print();
        System.out.println("Size: " + testBag.size());
        System.out.println("");
        
        System.out.println("Add 1, 2, 3 to testBag3");
        testBag3.addMany(1, 2, 3);
        
        System.out.println("testBag3:");
        testBag3.print();
        System.out.println("Size: " + testBag3.size());
        System.out.println("");
        
        System.out.println("Add 4, 5, 6 to testBag4");
        testBag4.addMany(4, 5, 6);
        
        System.out.println("testBag4:");
        testBag4.print();
        System.out.println("Size: " + testBag4.size());
        System.out.println("");
        
        System.out.println("Add testBag4 to testBag3");
        testBag3.addAll(testBag4);
        
        System.out.println("testBag3:");
        testBag3.print();
        System.out.println("Size: " + testBag3.size());
        System.out.println("");
        
        System.out.println("Union testBag with testBag3 into testBag5");
        testBag5 = CBTBag2.union(testBag, testBag3);
        
        System.out.println("testBag5:");
        testBag5.print();
        System.out.println("Size: " + testBag5.size());
        System.out.println("");
        
        System.out.println("");
        
        
        System.out.println("--- END TEST ADDING MULTIPLE ELEMENTS ---\n");   
    }

    /**
     * Test the countOccurrences method
     * @param - none
     * @precondition
     *  testBag has been instantiated
     */
    private static void testCount()
    {        
        System.out.println("--- TEST countOccurrences METHOD ---\n");
        
        System.out.println("1's: " + testBag.countOccurrences(1));
        System.out.println("3's: " + testBag.countOccurrences(3));
        System.out.println("5's: " + testBag.countOccurrences(5));
        System.out.println("6's: " + testBag.countOccurrences(6));
        System.out.println("7's: " + testBag.countOccurrences(7));
        System.out.println("");
        
        System.out.println("--- END TEST countOccurrences METHOD ---\n");
    }

    /**
     * Tests the getLeftmost and getRightmost methods
     * @param - none
     * @precondition
     *  testBag is not empty
     * @postcondition
     *  The leftmost and rightmost data will be printed
     */
    private static void testGetLeftAndRightMost()
    {
        System.out.println
                ("--- TEST getLeftmost AND getRightmost METHODS ---\n");
        
        System.out.println("testBag Leftmost: " + testBag.getLeftmostData());
        System.out.println("testBag Rightmost: " + testBag.getRightmostData());
        System.out.println("");
        
        System.out.println
                ("--- END TEST getLeftmost AND getRightmost METHODS ---\n");
    }

    /**
     * Test the printTree methods
     * @param - none
     * @precondition
     *  testBag has been instantiated
     * @postcondition
     *  testBag tree has been printed
     */
    private static void testPrintTree()
    {
        System.out.println("--- TEST PRINT TREE METHODS ---\n");
        
        System.out.println("testBag Print:");
        testBag.print();
        System.out.println("");
        
        System.out.println("testBag In Order Print");
        testBag.inorderPrint();
        System.out.println("");
        
        System.out.println("testBag Post Order Print");
        testBag.postorderPrint();
        System.out.println("");
        
        System.out.println("testBag Pre Order Print");
        testBag.preorderPrint();
        System.out.println("");
        
        System.out.println("--- END TEST PRINT TREE METHODS ---\n");
    }

    /**
     * Test the clone method
     * @param - none
     */
    private static void testClone()
    {
        CBTBag2 bagClone;
        System.out.println("--- TEST clone METHOD ---\n");
        
        System.out.println("Clone testBag into bagClone");
        bagClone = testBag.clone();
        
        System.out.println("Add 42 to bagClone");
        bagClone.add(42);
        System.out.println("");
        
        System.out.println("testBag:");
        testBag.print();
        System.out.println("Size: " + testBag.size());
        System.out.println("");
        
        System.out.println("bagClone:");
        bagClone.print();
        System.out.println("Size: " + bagClone.size());
        System.out.println("");
        
        System.out.println("--- END TEST clone METHOD ---\n");
    }

    /**
     * Test the remove functions
     * @param - none
     */
    private static void testRemove()
    {
        System.out.println("--- TEST REMOVE FUNCTIONS ---\n");
        
        System.out.println("testBag:");
        testBag.print();
        System.out.println("Size: " + testBag.size());
        System.out.println("");
        
        System.out.println("Remove leftmost Node from testBag");
        testBag.removeLeftmost();
        System.out.println("testBag:");
        testBag.print();
        System.out.println("Size: " + testBag.size());
        System.out.println("");
        
        System.out.println("Remove rightmost Node from testBag");
        testBag.removeRightmost();
        System.out.println("testBag:");
        testBag.print();
        System.out.println("Size: " + testBag.size());
        System.out.println("");
        
        
        System.out.println("--- END TEST REMOVE FUNCTIONS ---\n");
    }
    
}
