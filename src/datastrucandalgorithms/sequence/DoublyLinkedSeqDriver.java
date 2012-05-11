package datastrucandalgorithms.sequence;

/**
 * Test DoublyLinkedSeq Class and DoubleNodeDL Class
 * @author Brian Jerardi
 * @instructor Kendall Martin
 * @date 10/10/2011
 * @course CIS 112
 * @assignment HW2 Chapter 4 problem 16 page 241
 */
public class DoublyLinkedSeqDriver
{
    private static DoublyLinkedSeq dLSeq1;
    
    /**
     * Test DoubleNodeDL and DoublyLinkedSeq Classes
     * @param args 
     *      the command line arguments
     */
    public static void main(String[] args)
    {  
        dLSeq1 = new DoublyLinkedSeq();

        // Test the DoubleNodeDL class
        testNode();
        
        // Test the add, remove, advance, and retreat methods
        testAddRemoveMove();
        
        // Test addAll method
        testAddAllCatenation();
        
        // Test Clone
        testClone();
        
        
    }

    /**
     * Test the Node by adding nodes after nodes, walking through the 
     * nodes forward and backward, adding nodes before nodes, and
     * removing nodes before and after other nodes
     * @param - none
     */
    private static void testNode()
    {
        
        // Set up a head node
        DoubleNodeDL head = new DoubleNodeDL(2.8, null, null);

        System.out.println("--- TEST THE DoubleNodeDL CLASS ---");
        System.out.println("");
        
        // Add 3 more nodes after head
        System.out.println("Add nodes after head");
        System.out.println("");
        head.addNodeAfter(6.3);
        head.addNodeAfter(4.1);
        head.addNodeAfter(3.4);
        
        // Loop through the nodes starting from head
        System.out.println("Walk through the nodes forward");        
        for(DoubleNodeDL cursor = head; 
                cursor != null; cursor = cursor.getLink())
        {
            System.out.println(cursor.getData());            
        }
        System.out.println("");

        // Loop through the nodes starting from the last node
        System.out.println("Walk through the nodes backward");
        for(DoubleNodeDL cursor = head.getLink().getLink().getLink(); 
                cursor != null; cursor = cursor.getPrev())
        {
            System.out.println(cursor.getData());            
        }
        System.out.println("");
        
        System.out.println("Add a node before head");
        // Add node before head
        head.addNodeBefore(1.5);
        System.out.println("Add node before 6.3");
        // Add node before head.getLink().getLink().getLink() (6.3)
        head.getLink().getLink().getLink().addNodeBefore(5.9);
        System.out.println("");
        
        // Loop through the nodes starting from the newly added node
        System.out.println("Walk through the new nodes forward: "
                + "from the new head node");        
        for(DoubleNodeDL cursor = head.getPrev(); 
                cursor != null; cursor = cursor.getLink())
        {
            System.out.println(cursor.getData());            
        }
        System.out.println("");
        
        // Loop through the new nodes backward starting from the last node
        System.out.println("Walk through the new nodes backward");        
        for(DoubleNodeDL cursor = head.getLink().getLink().getLink().getLink(); 
                cursor != null; cursor = cursor.getPrev())
        {
            System.out.println(cursor.getData());            
        }
        System.out.println("");
        
        System.out.println("Remove node after 3.4");
        head.getLink().removeNodeAfter();
        
        // Loop through the nodes starting from the newly added node
        System.out.println("Walk through the new nodes forward: "
                + "from the new head node");        
        for(DoubleNodeDL cursor = head.getPrev(); 
                cursor != null; cursor = cursor.getLink())
        {
            System.out.println(cursor.getData());            
        }
        System.out.println("");
        
        // Loop through the new nodes backward starting from the last node
        System.out.println("Walk through the new nodes backward");        
        for(DoubleNodeDL cursor = head.getLink().getLink().getLink(); 
                cursor != null; cursor = cursor.getPrev())
        {
            System.out.println(cursor.getData());            
        }
        System.out.println("");
        
        System.out.println("Remove node before 5.9");
        head.getLink().getLink().removeNodeBefore();
        System.out.println("");
        
        // Loop through the nodes starting from the newly added node
        System.out.println("Walk through the new nodes forward: "
                + "from the new head node");        
        for(DoubleNodeDL cursor = head.getPrev(); 
                cursor != null; cursor = cursor.getLink())
        {
            System.out.println(cursor.getData());            
        }
        System.out.println("");
        
        // Loop through the new nodes backward starting from the last node
        System.out.println("Walk through the new nodes backward");        
        for(DoubleNodeDL cursor = head.getLink().getLink(); 
                cursor != null; cursor = cursor.getPrev())
        {
            System.out.println(cursor.getData());            
        }
        System.out.println("");   
    }
    
    /**
     * Test the collections add, remove, advance and retreat methods
     * @parm - none
     */
    private static void testAddRemoveMove()
    {
        System.out.println("--- TEST add, remove, advance, "
                + "and retreat METHODS ---");
        System.out.println("");
        
        // Add nodes, advance, retreat and remove a node
        System.out.println("Move cursor to start");
        // In this case cursor is not set, because there are no elements
        dLSeq1.start();
        System.out.println("Add 6 after cursor");
        dLSeq1.addAfter(6);
        System.out.println("Cursor: " + dLSeq1.getCurrent());
        System.out.println("Add 4 before cursor");
        dLSeq1.addBefore(4);
        System.out.println("Cursor: " + dLSeq1.getCurrent());
        System.out.println("Advance the cursor");
        dLSeq1.advance();
        System.out.println("Cursor: " + dLSeq1.getCurrent());
        System.out.println("Add 10 after cursor");
        dLSeq1.addAfter(10);  
        System.out.println("Cursor: " + dLSeq1.getCurrent());
        System.out.println("Retreat the cursor");
        dLSeq1.retreat();
        System.out.println("Cursor: " + dLSeq1.getCurrent());
        System.out.println("Add 8 after cursor");
        dLSeq1.addAfter(8);
        System.out.println("Cursor: " + dLSeq1.getCurrent());
        System.out.println("Advance the cursor");
        dLSeq1.advance();
        System.out.println("Cursor: " + dLSeq1.getCurrent());
        System.out.println("Remove the cursor");
        dLSeq1.removeCurrent();  
        System.out.println("");
        
        System.out.println("Print the Sequence Forward");
        dLSeq1.printSeqForward();
        System.out.println("");
        
        System.out.println("Print the Sequence backward");
        dLSeq1.printSeqBackward();
        System.out.println("");
    }

    /**
     * Test collection addAll and catenation methods
     * @parm - none
     */
    private static void testAddAllCatenation()
    {
        DoublyLinkedSeq dLSeqa = new DoublyLinkedSeq();
        DoublyLinkedSeq dLSeqb = new DoublyLinkedSeq();
        DoublyLinkedSeq dLSeqc = new DoublyLinkedSeq();
        DoublyLinkedSeq dLSeqd = new DoublyLinkedSeq();
        
        System.out.println("--- TEST addAll and catenation METHODS ---");
        System.out.println("");
        
        // Add nodes to dLSeqa
        System.out.println("Add 1 after cursor of dLSeqa");
        dLSeqa.addAfter(1);
        System.out.println("Add 2 after cursor of dLSeqa");
        dLSeqa.addAfter(2);
        System.out.println("Add 3 after cursor of dLSeqa");
        dLSeqa.addAfter(3);
        System.out.println("Add 4 after cursor of dLSeqa");
        dLSeqa.addAfter(4);
        System.out.println("Add 5 after cursor of dLSeqa");
        dLSeqa.addAfter(5);            
        System.out.println("");
        
        // Add nodes to dLSeqb
        System.out.println("Add 6 after cursor of dLSeqb");
        dLSeqb.addAfter(6);        
        System.out.println("Add 7 after cursor of dLSeqb");
        dLSeqb.addAfter(7);
        System.out.println("Add 8 after cursor of dLSeqb");
        dLSeqb.addAfter(8);        
        System.out.println("Add 9 after cursor of dLSeqb");
        dLSeqb.addAfter(9);
        System.out.println("Add 10 after cursor of dLSeqb");
        dLSeqb.addAfter(10);
        System.out.println("");
        
        // Print orginal dLSeqa
        System.out.println("Sequence dLSeqa:");
        dLSeqa.printSeqForward();
        System.out.println("");
        
        // Print dLSeqb
        System.out.println("Sequence dLSeqb:");
        dLSeqb.printSeqForward();
        System.out.println("");
        
        // Add dLSeqb to dLSeqa
        System.out.println("Add all of dLSeqb to dLSeqa");
        dLSeqa.addAll(dLSeqb);
        
        // Print new dLSeqa
        System.out.println("New Sequence dLSeqa:");
        dLSeqa.printSeqForward();
        System.out.println("");
        

        // Add nodes to dLSeqc
        System.out.println("Add 11 after cursor of dLSeqc");
        dLSeqc.addAfter(11);
        System.out.println("Add 12 after cursor of dLSeqc");
        dLSeqc.addAfter(12);
        System.out.println("Add 13 after cursor of dLSeqc");
        dLSeqc.addAfter(13);
        System.out.println("Add 14 after cursor of dLSeqc");
        dLSeqc.addAfter(14);
        System.out.println("Add 15 after cursor of dLSeqc");
        dLSeqc.addAfter(15);
        System.out.println("");
        
        // Print dLSeqc
        System.out.println("Sequence dLSeqc:");
        dLSeqc.printSeqForward();
        System.out.println("");
        
        // Catenation of dLSeqa with dLSeqc into dLSeqd
        System.out.println("Catenation of dLSeqa with dLSeqc into dLSeqd");
        dLSeqd = DoublyLinkedSeq.catenation(dLSeqa, dLSeqc);
        System.out.println("");
        
        // Print dLSeqd
        System.out.println("Sequence dLSeqd:");
        dLSeqd.printSeqForward();
        System.out.println("");          
    }

    /**
     * Test the collections clone method
     * @parm - none
     */
    private static void testClone()
    {
        DoublyLinkedSeq dLSeq2;
        
        System.out.println("--- TEST clone METHOD ---");
        System.out.println("");
        
        // Clone dLSeq1 into dLSeq2
        System.out.println("Clone dLSeq1 into dLSeq2");
        dLSeq2 = dLSeq1.clone();
        
        // Add 2 before start of dLSeq1
        System.out.println("Add 2 before start of dLSeq1");
        dLSeq1.start();
        dLSeq1.addBefore(2);
        System.out.println("");
        
        // Print dLSeq1
        System.out.println("Sequence dLSeq1:");
        dLSeq1.printSeqForward();
        System.out.println("");
        
        // Print dLSeq2
        System.out.println("Sequence dLSeq2:");
        dLSeq2.printSeqForward();
        System.out.println("");
    }
 
}
