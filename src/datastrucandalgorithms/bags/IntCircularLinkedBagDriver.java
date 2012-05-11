package datastrucandalgorithms.bags;

/**
 * Test IntCircularLinkedBag Class
 * @author Brian Jerardi
 * @instructor Kendall Martin
 * @date 10/12/2011
 * @course CIS 112
 * @assignment HW2 Chapter 4 problem 17 page 241
 */
public class IntCircularLinkedBagDriver
{    
    private static IntCircularLinkedBag testBag = new IntCircularLinkedBag();
    
    /**
     * Test IntCircularLinkedBag Class
     * @param args 
     *      the command line arguments
     */
    public static void main(String[] args)
    { 
        // Test Bag set up
        testAdd();
        
        // Test addAll and addMany
        testAddAllMany();
        
        // Test remove and grab
        testRemoveAndGrab();
        
        // Test clone
        testClone();
        
        // Test countOccurrences
        testCount();
        
        // Test union
        testUnion();
    }

    /**
     * Test the setting up and adding to an IntCirularLinkedBag
     * @param - none
     */
    private static void testAdd()
    {      
        System.out.println("--- TEST add METHOD---");
        System.out.println("");
        
        // Print the size and add to the bag
        System.out.println("Size: " + testBag.size());
        System.out.println("Add 3 to testBag");
        testBag.add(3);
        System.out.println("Size: " + testBag.size());
        System.out.println("Add 2 to testBag");
        testBag.add(2);
        System.out.println("Size: " + testBag.size());
        System.out.println("Add 1 to testBag");
        testBag.add(1);
        System.out.println("Size: " + testBag.size());
        System.out.println("");       
        
                
        // Print the bag
        System.out.println("Print testBag");
        testBag.printList();
        System.out.println("");
    }

    /**
     * Test addAll and addMany methods
     * @parm - none
     * @precondition
     *  testAdd has been called before this
     */
    private static void testAddAllMany()
    {
        IntCircularLinkedBag testBag2 = new IntCircularLinkedBag();
        
        System.out.println("--- TEST addAll and addMany METHODS---");
        System.out.println("");
        
        // Add elements to testBag2
        System.out.println("Size: " + testBag2.size());
        System.out.println("Add 6 to testBag2");
        testBag2.add(6);
        System.out.println("Size: " + testBag2.size());
        System.out.println("Add 5 to testBag2");
        testBag2.add(5);
        System.out.println("Size: " + testBag.size());
        System.out.println("Add 4 to testBag2");
        testBag2.add(4);
        System.out.println("Size: " + testBag2.size());
        System.out.println("");
        
        // Print testBag2
        System.out.println("testbag2: ");
        testBag2.printList();
        System.out.println("");
        
        System.out.println("Add all of testBag2 to testBag");
        testBag.addAll(testBag2);
        
        // Print testBag
        System.out.println("testBag: ");
        testBag.printList();
        System.out.println("");
        
        // Test addMany
        System.out.println("Adding -1, -2, -3 to testBag");
        testBag.addMany(-1, -2, -3);
        
        // Print testBag
        System.out.println("testBag: ");
        testBag.printList();
        System.out.println(""); 
    }
    
    /**
     * Test remove and grab methods
     * @parm - none
     */
    private static void testRemoveAndGrab()
    {        
        System.out.println("--- TEST remove AND grab METHODS---");
        System.out.println("");
        
        // Print testBag
        System.out.println("testBag: ");
        testBag.printList();
        System.out.println(""); 
        
        // Remove -3 and try to remove 99
        System.out.println("Remove -3 from testBag");
        testBag.remove(-3);        
        System.out.println("Try to remove 99 from testBag");
        testBag.remove(99);
        System.out.println("");
        
        // Print testBag
        System.out.println("testBag: ");
        testBag.printList();
        System.out.println("");
        
        // Test grab
        System.out.println("Print Random numbers from testBag");
        for (int i = 0; i < 10; i++)
        {
            System.out.println("Random : " + testBag.grab());
        }
        System.out.println("");
    }
    
    /**
     * Test clone method
     * @parm - none
     * @precondition
     *  testAdd has been called before this
     */
    private static void testClone()
    {
        IntCircularLinkedBag testBag3 = new IntCircularLinkedBag();
        
        System.out.println("--- TEST clone METHOD---");      
        System.out.println("");
        
        // Clone testbag into testBag3
        System.out.println("Clone testBag into testBag3");
        testBag3 = testBag.clone();
        System.out.println("");
        
        // Add -4, -5, -6 to testBag
        System.out.println("Adding -4, -5, -6 to testBag");
        testBag.addMany(-4, -5, -6);
        
        // Print testBag
        System.out.println("testBag: ");
        testBag.printList();
        System.out.println(""); 
        
        // Print testBag3
        System.out.println("testBag3: ");
        testBag3.printList();
        System.out.println(""); 
    }
    
    /**
     * Test countOccurrences method
     * @parm - none
     * @precondition
     *  testAdd has been called before this
     */
    private static void testCount()
    {
        System.out.println("--- TEST countOccurrences METHOD---");
        System.out.println("");
        
        // Print testBag
        System.out.println("testBag: ");
        testBag.printList();
        System.out.println("");
        
        // Test countOccurrences
        System.out.println("Occurances of 1: " + testBag.countOccurrences(1));
        System.out.println("Occurances of 2: " + testBag.countOccurrences(2));
        System.out.println("Occurances of 3: " + testBag.countOccurrences(3));
        System.out.println("");
        
        // Add two 1's, four 2's and remove one 3 from testBag
        System.out.println("Add two 1's to testBag");
        testBag.addMany(1, 1);
        System.out.println("Add four 2's to testBag");
        testBag.addMany(2, 2, 2, 2);
        System.out.println("Remove one 3 from testBag");
        testBag.remove(3);
        System.out.println("");
        
        // Print testBag
        System.out.println("testBag: ");
        testBag.printList();
        System.out.println("");
        
        // Test countOccurrences
        System.out.println("Occurances of 1: " + testBag.countOccurrences(1));
        System.out.println("Occurances of 2: " + testBag.countOccurrences(2));
        System.out.println("Occurances of 3: " + testBag.countOccurrences(3));
        System.out.println("");   
    }
    
    /**
     * Test union method
     * @parm - none
     */
    private static void testUnion()
    {
        IntCircularLinkedBag testBaga = new IntCircularLinkedBag();
        IntCircularLinkedBag testBagb = new IntCircularLinkedBag();
        IntCircularLinkedBag testBagc = new IntCircularLinkedBag();
        
        System.out.println("--- TEST union METHOD---");
        System.out.println("");
        
        // Add ints to testBaga and testBagb
        System.out.println("Add 30, 20, 10 to testBaga");
        testBaga.addMany(30, 20, 10);
        System.out.println("Add 60, 50, 40 to testBagb");
        testBagb.addMany(60, 50, 40);
        System.out.println("");
        
        // Print testBaga
        System.out.println("testBaga: ");
        testBaga.printList();
        System.out.println("");
        
        // Print testBagb
        System.out.println("testBagb: ");
        testBagb.printList();
        System.out.println("");
        
        // Union testBaga and testBagb into testBagc
        System.out.println("Union testBaga and testBagb into testBagc");
        testBagc = IntCircularLinkedBag.union(testBaga, testBagb);
        System.out.println("");
        
        // Print testBagb
        System.out.println("testBagc: ");
        testBagc.printList();
        System.out.println("");
    }
    
}
