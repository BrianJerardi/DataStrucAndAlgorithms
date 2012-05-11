package datastrucandalgorithms.queues;

import edu.colorado.collections.ArrayQueue;
import java.util.NoSuchElementException;

/**
 * Implements a Priority Queue
 * @author Brian Jerardi
 * @instructor Kendall Martin
 * @date 10/18/2011
 * @course CIS 112
 * @assignment HW2 Chapter 7 problem 4 page 396
 */
public class PriorityQueue<E> implements Cloneable
{
    /*
     *  Invariant of the PriorityQueue class:
     *    1. The elements in the queue are stored in an array of ArrayQueues.
     *    2. The highest priority is stored in instance variable highest.
     *    3. The highest non empty ArrayQueue is found with the instance 
     *       variable highestNonEmpty.
     *    4. The total number of elements in the queue is in the instance 
     *       variable totalSize. 
     */
    private ArrayQueue<E>[] queues;
    private int highest;
    private int highestNonEmpty;
    private int totalSize;
    
    /**
     * Initialize an empty priority queue
     * @param highest
     *  the highest priority allowed in this priority queue
     * @precondition
     *  highest >= 0
     * @postcondition
     *  This priority queue is empty
     * @throws IllegalArgumentException
     *  Indicates that highest is negative
     *  
     */
    public PriorityQueue(int highest)
    {
        if (highest < 0)
        {
            throw new IllegalArgumentException("highest can not be negative");
        }
        
        // Set up a new array of ArrayQueues with a size of highest + 1
        queues = new ArrayQueue[highest + 1];
        
        // Set up each of the ArrayQueue in queues
        for (int i = 0; i < highest + 1; i++)
        {
            queues[i] = new ArrayQueue<E>();
        }
        
        // Set instance variables
        this.highest = highest;
        totalSize = 0;
        highestNonEmpty = 0;
    }

    /**
     * Get how many items are in the queue
     * @return 
     *  The number of items in the queue
     */
    public int size()
    {
        return totalSize;
    }
    
    /**
     * Add a new item to this priority queue
     * @param item
     *  the item to be added to this queue
     * @param priority 
     *  the priority of the new item
     * @precondition
     *  0 <= priority and priority is no more than the highsest priority
     * @postcondition
     *  The item has been added to this priority queue
     * @throws IllegalArgumentException
     *  Indicates an illegal priority
     * @throws IllegalArgumentException
     *  Indicates insufficient memory for adding a new item to this 
     *  priority queue
     */
    public void add(E item, int priority)
    {
        if (priority < 0 || priority > highest)
        {
            throw new IllegalArgumentException("Invalid priority: " + priority);
        }
        
        // Add the item to the correct ArrayQueue
        queues[priority].add(item);
        
        // If priority is higher that the highest non empty ArrayQueue change
        // highestNonEmpty to priority
        if (priority > highestNonEmpty)
        {
            highestNonEmpty = priority;
        }
        
        // Increment totalSize
        totalSize++;
    }
    
    /**
     * Get the highest priority item, removing it from this priority queue.
     * @precondition
     *  This queue is not empty
     * @postcondition
     *  The return value is the highest priority item of this queue, and the
     *  item has been removed.
     *  If several items have equal priority, then the one that entered first 
     *  will come out first.
     * @return 
     *  The highest priority item of this queue
     * @throws NoSuchElementException
     *  Indicates that this queue is empty
     */
    public E remove()
    {
        E answer;
        answer = queues[highestNonEmpty].remove();
        
        // Set highestNonEmpty to the highest position of queue 
        // that is not empty
        for (int i = highestNonEmpty; 
                i >= 0 && queues[highestNonEmpty].isEmpty(); 
                i--)
        {
            highestNonEmpty = i;
        }
        
        // Decrement totalSize
        totalSize--;
        
        return answer;
    }
    
    /**
     * Test if this queue is empty
     * @param - none
     * @return 
     *  true if this queue is empty
     *  false if it is NOT empty
     */
    public boolean isEmpty()
    {
        if (totalSize == 0)
        {
            return true;
        }
        
        return false;
    }
    
    /**
     * Makes a copy of this queue
     * @param - none
     * @postcondition
     *  the copy is a reference to a new difference ArrayQueue with all the 
     *  same items
     * @return 
     *  A copy of this ArrayQueue
     */
    @Override
    public PriorityQueue<E> clone()
    {
        PriorityQueue<E> answer;
        
        try
        {
            answer = (PriorityQueue<E>) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            throw new RuntimeException
                    ("This class does not implement Cloneable.");
        }

        
        answer.queues = new ArrayQueue[answer.highest + 1];

        for (int i = 0; i <= answer.highest ; i++)
        {
            answer.queues[i] = queues[i].clone();
        }

        
        return answer;
    }
    
    /**
   * Reduce the current capacity of this queue to its actual size (i.e., the
   * number of items it contains).
   * @param - none
   * @postcondition
   *   This queue's capacity has been changed to its current size.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for altering the capacity. 
   **/  
    public void trimToSize()
    {
        for (int i = highest; i >= 0 ; i--)
        {
            queues[i].trimToSize();
        }
    }
    
}
