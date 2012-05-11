package datastrucandalgorithms.sequence;

import java.util.Random;

/**
 * Driver to test DoubleArraySortedSeq class
 * @author Brian Jerardi
 * @instructor Kendall Martin
 * @date 9/21/2011
 * @course CIS 112
 * @assignment HW2 Chapter 3 problem 7 page 164
 */
public class DoubleArraySortedSeqDriver
{
    /**
     * Test DoubleArraySortedSeq class
     * @param args 
     *      the command line arguments
     */
    public static void main(String[] args)
    {
        DoubleArraySortedSeq dArrSortSeq = new DoubleArraySortedSeq(2);
        Random rand = new Random();
        
        // Check the capacity and size of dArrSortSeq
        System.out.printf("%-10s%-10d\n","Size: ",dArrSortSeq.size());
        System.out.printf("%-10s%-10d\n","Capacity: ",dArrSortSeq.getCapacity());
        System.out.println("");
    
        // Inserts two items into dArrSortSeq
        System.out.println("Add 9 to dArrSortSeq");
        dArrSortSeq.add(9);
        System.out.println("Add -5 to dArrSortSeq");
        dArrSortSeq.add(-5);
        
        // Check the capacity and size of dArrSortSeq
        System.out.printf("%-10s%-10d\n","Size: ",dArrSortSeq.size());
        System.out.printf("%-10s%-10d\n","Capacity: ",dArrSortSeq.getCapacity());
        
        // Print contents of dArrSortSeq
        System.out.println("");
        System.out.println(dArrSortSeq);
        System.out.println("");     
        
        // Inserts one items into dArrSortSeq
        System.out.println("Add 0 to dArrSortSeq");
        dArrSortSeq.add(0);
        
        // Check the capacity and size of dArrSortSeq
        System.out.printf("%-10s%-10d\n","Size: ",dArrSortSeq.size());
        System.out.printf("%-10s%-10d\n","Capacity: ",dArrSortSeq.getCapacity());
        
        // Print contents of dArrSortSeq
        System.out.println("");
        System.out.println(dArrSortSeq);
        System.out.println("");     
        
         // Inserts one items into dArrSortSeq
        System.out.println("Add 0 to dArrSortSeq");
        dArrSortSeq.add(0);
        
         // Check the capacity and size of dArrSortSeq
        System.out.printf("%-10s%-10d\n","Size: ",dArrSortSeq.size());
        System.out.printf("%-10s%-10d\n","Capacity: ",dArrSortSeq.getCapacity());
        
        // Print contents of dArrSortSeq
        System.out.println("");
        System.out.println(dArrSortSeq);
        System.out.println("");   
        
         // Inserts one items into dArrSortSeq
        System.out.println("Add -10 to dArrSortSeq");
        dArrSortSeq.add(-10);
        
         // Check the capacity and size of dArrSortSeq
        System.out.printf("%-10s%-10d\n","Size: ",dArrSortSeq.size());
        System.out.printf("%-10s%-10d\n","Capacity: ",dArrSortSeq.getCapacity());
        
        // Print contents of dArrSortSeq
        System.out.println("");
        System.out.println(dArrSortSeq);
        System.out.println("");   
        
         // Inserts one items into dArrSortSeq
        System.out.println("Add 10 to dArrSortSeq");
        dArrSortSeq.add(10);
        
         // Check the capacity and size of dArrSortSeq
        System.out.printf("%-10s%-10d\n","Size: ",dArrSortSeq.size());
        System.out.printf("%-10s%-10d\n","Capacity: ",dArrSortSeq.getCapacity());
        
        // Print contents of dArrSortSeq
        System.out.println("");
        System.out.println(dArrSortSeq);
        System.out.println("");   
        

        // Add 14 random doubles to dArrSortSeq bringing the total to 20
        System.out.println("Add 14 random doubles to dArrSortSeq "
                + "bringing the total to 20");
        for (int i = 0; i < 14; i++)
        {
            dArrSortSeq.add(rand.nextDouble() * 20.0 - 10.0);
        }
        
         // Check the capacity and size of dArrSortSeq
        System.out.printf("%-10s%-10d\n","Size: ",dArrSortSeq.size());
        System.out.printf("%-10s%-10d\n","Capacity: ",dArrSortSeq.getCapacity());
        
        // Print contents of dArrSortSeq
        System.out.println("");
        System.out.println(dArrSortSeq);
        System.out.println("");   

    }
    
}
