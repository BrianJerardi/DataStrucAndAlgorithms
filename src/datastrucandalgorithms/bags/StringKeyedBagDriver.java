package datastrucandalgorithms.bags;

/**
 * Driver to test StringKeyedBag class
 * @author Brian Jerardi
 * @instructor Kendall Martin
 * @date 9/20/2011
 * @course CIS 112
 * @assignment HW2 Chapter 3 problem 19 page 166-167
 */
public class StringKeyedBagDriver
{
    /**
     * Test StringKeyedBag class
     * @param args 
     *      the command line arguments
     */
    public static void main(String[] args)
    {
        // Instantiate StringKeyedBag with initial capacity of 2
        StringKeyedBag sKBag = new StringKeyedBag(2);
        
        // Check the capacity and size of sKBag
        System.out.printf("%-10s%-10d\n","Size: ",sKBag.size());
        System.out.printf("%-10s%-10d\n","Capacity: ",sKBag.getCapacity());
        System.out.println("");
        
        // Inserts two items into sKBag with unique keys
        System.out.println("Add Bobby Guy with key 1001 to sKBag");
        sKBag.insert("Bobby Guy", 1001);
        System.out.println("Add Jane Girl with key 1002 to sKBag");
        sKBag.insert("Jane Girl", 1002);
        
        // Check the capacity and size of sKBag
        System.out.printf("%-10s%-10d\n","Size: ",sKBag.size());
        System.out.printf("%-10s%-10d\n","Capacity: ",sKBag.getCapacity());
        System.out.println("");
        
        // Try to insert an item with an already used key
        try
        {
            System.out.println("Try to add John Guy with key 1002 to sKBag");
            sKBag.insert("John Guy", 1002);
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        
        // Check the capacity and size of sKBag
        System.out.printf("%-10s%-10d\n","Size: ",sKBag.size());
        System.out.printf("%-10s%-10d\n","Capacity: ",sKBag.getCapacity());
        System.out.println("");
        
        // Insert an item with an already used name, but a unique key
        System.out.println("Add Bobby Guy with key 1003 to sKBag");
        sKBag.insert("Bobby Guy", 1003);
        
        // Check the capacity and size of sKBag
        System.out.printf("%-10s%-10d\n","Size: ",sKBag.size());
        System.out.printf("%-10s%-10d\n","Capacity: ",sKBag.getCapacity());
        
        // Print contents of sKBag
        System.out.println("");
        sKBag.print();
        System.out.println("");
        
        // Remove item with key 1001
        System.out.println("Remove item with key 1001");
        sKBag.remove(1001);
        
        // Check the capacity and size of sKBag
        System.out.printf("%-10s%-10d\n","Size: ",sKBag.size());
        System.out.printf("%-10s%-10d\n","Capacity: ",sKBag.getCapacity());
        System.out.println("");
        
        // Try to remove item with key 1004
        System.out.println("Try to remove item with key 1004");
        sKBag.remove(1004);
                
        // Check the capacity and size of sKBag
        System.out.printf("%-10s%-10d\n","Size: ",sKBag.size());
        System.out.printf("%-10s%-10d\n","Capacity: ",sKBag.getCapacity());
        
        // Print contents of sKBag
        System.out.println("");
        sKBag.print();
        System.out.println("");
        
        // Trim sKBag to size
        System.out.println("Trim sKBag to size");
        sKBag.trimToSize();
        
        // Check the capacity and size of sKBag
        System.out.printf("%-10s%-10d\n","Size: ",sKBag.size());
        System.out.printf("%-10s%-10d\n","Capacity: ",sKBag.getCapacity());
        System.out.println("");
        
        // Retrieve item with key 1002
        System.out.println("Retrieve item with key 1002");
        System.out.println(sKBag.retrieve(1002));
        
        // Try to retrieve item with key 1004
        System.out.println("Try to retrieve item with key 1004");
        try
        {
            System.out.println(sKBag.retrieve(1004));            
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        
        // Print contents of sKBag
        System.out.println("");
        sKBag.print();
        System.out.println("");
        
        // Test keyExists method
        System.out.println("Does key 1001 exists: " + sKBag.keyExists(1001));
        System.out.println("Does key 1002 exists: " + sKBag.keyExists(1002));
        System.out.println("Does key 1003 exists: " + sKBag.keyExists(1003));
        System.out.println("Does key 1004 exists: " + sKBag.keyExists(1004));
        
        
        
    }
    
}
