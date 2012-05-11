package datastrucandalgorithms.trees;

/**
 * A driver to test CBTBag<E>
 * @author Brian Jerardi
 * @instructor Kendall Martin
 * @date 11/01/2011
 * @course CIS 112
 * @assignment HW2 Chapter 9 problem 2 page 507
 */
public class CBTBagDriver
{

    private static CBTBag<Integer> testBag;
    
    /**
     * Test Driver for CBTBag
     * @param args 
     *  the command line arguments
     */
    public static void main(String[] args)
    { 
        testBag = new CBTBag<Integer>();
        
        testOnEmpty();
        
        createBag();
        
        testAddMulti();
        
        testCount();   
        
        testPrintChildren();
       
        testGet();
        
        testClone();
        
        testPrintTree();
    }

    /**
     * Creates a tree bag
     * @param - none
     * @precondition
     *  testBag has been instantiated
     * @postcondtion
     *  testBag contains the numbers 5, 6, 7, 3, 5
     */
    private static void createBag()
    {
        System.out.println("--- CREATING BAG ---\n");
        
        System.out.println("Adding 5, 6, 7, 3, 5 to testBag");
        testBag.add(5);
        testBag.add(6);
        testBag.add(7);
        testBag.add(3);
        testBag.add(5);
        System.out.println("");
        
        System.out.println("Size: " + testBag.size() + "\n");
        
        System.out.println("--- END CREATING BAG ---\n");
    }

    /**
     * Test the printChildren method
     * @param - none
     * @precondition
     *  testbag contains 5 numbers
     * @postcondition
     *  The children of all of testBags numbers have been printed
     */
    private static void testPrintChildren()
    {
        System.out.println("--- TEST printChildren ---\n");
        
        System.out.println("Children of index 0");
        testBag.printChildren(0);
        System.out.println("Leaf: " + testBag.isLeaf(0));        
        System.out.println("");
        
        System.out.println("Children of index 1");
        testBag.printChildren(1);
        System.out.println("Leaf: " + testBag.isLeaf(1));  
        System.out.println("");
        
        System.out.println("Children of index 2");
        testBag.printChildren(2);
        System.out.println("Leaf: " + testBag.isLeaf(2));  
        System.out.println("");
        
        System.out.println("Children of index 3");
        testBag.printChildren(3);
        System.out.println("Leaf: " + testBag.isLeaf(3));  
        System.out.println("");
        
        System.out.println("Children of index 4");
        testBag.printChildren(4);
        System.out.println("Leaf: " + testBag.isLeaf(4));  
        System.out.println("");
        
        System.out.println("Try to get Children of index 5");
        try
        {
            testBag.printChildren(5);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }  
        System.out.println("Try to get check isLeaf of index 5");
        try
        {
            System.out.println("Leaf: " + testBag.isLeaf(5)); 
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }  
        System.out.println("");        
        
        System.out.println("--- END TEST printChildren ---\n");
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
        
        System.out.println("testBag isEmpty: " + testBag.isEmpty());
        System.out.println("");
        
        System.out.println("Try to remove from an empty bag");
        System.out.println("Remove: " + testBag.remove() + "\n");     
        
        System.out.println("Try to print empty bags");
        try
        {
            testBag.inorderPrint();
        }
        catch (IllegalStateException e)
        {
            System.out.println(e.getMessage());
        }
        
        try
        {
            testBag.postorderPrint();
        }
        catch (IllegalStateException e)
        {
            System.out.println(e.getMessage());
        }
        
        try
        {
            testBag.printTree();
        }
        catch (IllegalStateException e)
        {
            System.out.println(e.getMessage());
        }
        
        try
        {
            testBag.preorderPrint();
        }
        catch (IllegalStateException e)
        {
            System.out.println(e.getMessage());
        }
        System.out.println("");
        
        System.out.println("Try to union a null bag");
        try
        {
            CBTBag testBag2 = null;
            CBTBag.union(testBag, testBag2);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }        
        System.out.println("");
        
        System.out.println("Try to check if index 0 is a leaf");
        try
        {
            testBag.isLeaf(0);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        System.out.println("");
        
        
        System.out.println("--- END TEST METHODS ON AN EMPTY BAG ---\n");
    }

    /**
     * Test the getDataAt, getLeftmostData and getRightmostData methods
     * @param - none
     * @precondition
     *  testBag has been instantiated
     */
    private static void testGet()
    {
        System.out.println("--- TEST ALL GET METHODS ---\n");
        
        System.out.println("Item at index 0: " + testBag.getDataAt(0));
        System.out.println("Item at index 1: " + testBag.getDataAt(1));
        System.out.println("Item at index 2: " + testBag.getDataAt(2));
        System.out.println("Item at index 3: " + testBag.getDataAt(3));
        System.out.println("Item at index 4: " + testBag.getDataAt(4));
        System.out.println("Item at index 5: " + testBag.getDataAt(5));
        System.out.println("");
        
        System.out.println("Leftmost: " + testBag.getLeftmostData()); 
        System.out.println("Rightmost: " + testBag.getRightmostData());
        System.out.println("");
        
        System.out.println("Leftmost at 2: " + testBag.getLeftmostData(2)); 
        System.out.println("Rightmost at 1: " + testBag.getRightmostData(1));
        System.out.println("");
        
        
        
        System.out.println("--- END TEST ALL GET METHODS ---\n");               
    }
    
    /**
     * Test the printTree methods
     * @param - none
     * @precondition
     *  testBag has been instantiated
     * @postcondition
     *  testBag tree has been printed
     *  testBag contains 5, 6, 7, 3, 5, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
     */
    private static void testPrintTree()
    {
        System.out.println("--- TEST PRINT TREE METHODS ---\n");
        
        System.out.println("In Order Print");
        testBag.inorderPrint();
        System.out.println("");
        
        System.out.println("Post Order Print");
        testBag.postorderPrint();
        System.out.println("");
        
        System.out.println("Pre Order Print");
        testBag.preorderPrint();
        System.out.println("");
        
        System.out.println("In Order Print at index 1");
        testBag.inorderPrint(1);
        System.out.println("");
        
        System.out.println("Post Order Print at index 2");
        testBag.inorderPrint(2);
        System.out.println("");
        
        System.out.println("Pre Order Print at index 3");
        testBag.inorderPrint(3);
        System.out.println("");
        
        System.out.println("Try In Order Print at index 5");
        try
        {
            testBag.inorderPrint(5);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        System.out.println("");
        
        System.out.println("Try Post Order Print at index 5");
        try
        {
            testBag.postorderPrint(5);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        System.out.println("");
        
        System.out.println("Try Pre Order Print at index 5");
        try
        {
            testBag.preorderPrint(5);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        System.out.println("");       
        
        System.out.println("testBag as a Tree:");
        testBag.printTree();
        System.out.println("");
        
        System.out.println("Add 11 new numbers to testBag and print");
        testBag.addMany(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        System.out.println("testBag as a Tree:");
        testBag.printTree();
        System.out.println("");
        
        System.out.println("--- END TEST PRINT TREE METHODS ---\n");
    }

    /**
     * Test the addMany, addAll and static union methods
     * @param - none
     * @postcondition
     *  addMany, addAll and static union methods; have been demonstrated
     */
    private static void testAddMulti()
    {
        CBTBag<Integer> testBag2 = new CBTBag<Integer>();
        CBTBag<Integer> testBag3 = new CBTBag<Integer>();
        CBTBag<Integer> testBag4 = new CBTBag<Integer>();
        
        System.out.println("--- TEST ADDING MULTIPLE ELEMENTS ---\n");
        
        System.out.println("Add 5, 6, and 7 to testBag2");
        testBag2.addMany(5, 6, 7);
        
        System.out.println("TestBag2");
        testBag2.inorderPrint();
        System.out.println("");
        
        System.out.println("Add 3 and 5 to testBag3");
        testBag3.addMany(3, 5);
        
        System.out.println("TestBag3");
        testBag3.inorderPrint();
        System.out.println("");
        
        System.out.println("Add testBag3 to testBag2");
        testBag2.addAll(testBag3);
        
        System.out.println("TestBag2");
        testBag2.inorderPrint();
        System.out.println("");
        
        System.out.println("union testBag2 and testBag3 into testbag4");
        testBag4 = CBTBag.union(testBag2, testBag3);
        
        System.out.println("TestBag4");
        testBag4.inorderPrint();
        System.out.println("");    
        
        System.out.println("--- END TEST ADDING MULTIPLE ELEMENTS ---\n");
    }

    /**
     * Test the countOccurrences method
     * @param - none
     * @precondition
     *  testBag has been instantiated
     * @postcondition
     *  countOccurrences method has been demonstrated
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
     * Test the clone method
     * @param - none
     * @postcondition
     *  clone method has been demonstrated
     */
    private static void testClone()
    {
        CBTBag bagClone;
        System.out.println("--- TEST clone METHOD ---\n");
        
        System.out.println("Clone testBag into bagClone");
        bagClone = testBag.clone();
        
        System.out.println("Add 42 to bagClone");
        bagClone.add(42);
        System.out.println("");
        
        System.out.println("testBag In Order Print");
        testBag.inorderPrint();
        System.out.println("");
        
        System.out.println("bagClone In Order Print");
        bagClone.inorderPrint();
        System.out.println("");
        
        System.out.println("--- END TEST clone METHOD ---\n");
    }
}
