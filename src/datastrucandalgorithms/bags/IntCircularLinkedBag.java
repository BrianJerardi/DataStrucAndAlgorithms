package datastrucandalgorithms.bags;

import edu.colorado.nodes.IntNode; 

/**
 * An IntCircularLinkedBag is a collection of int numbers.
 * @author Brian Jerardi
 * @instructor Kendall Martin
 * @date 10/12/2011
 * @course CIS 112
 * @assignment HW2 Chapter 4 problem 17 page 241
 * @note
 *   (1) Beyond Int.MAX_VALUE elements, countOccurrences,
 *   size, and grab are wrong.
 *   <p>
 *   (2) Because of the slow linear algorithms of this class, large bags will have
 *   poor performance.
 * @credit 
 *  Michael Main for original implementation 
 *  I converted it to a circular linked list
 */
public class IntCircularLinkedBag implements Cloneable
{
   // Invariant of the IntCircularLinkedBag class:
   //   1. The elements in the bag are stored on a linked list.
   //   2. The head reference of the list is in the instance variable head.
   //   3. The tail reference of the list is in the instance variable tail.
   //   4. The total number of elements in the list is in the instance 
   //      variable manyNodes.
   private IntNode head;
   private IntNode tail;
   private int manyNodes;   


   /**
   * Initialize an empty bag.
   * @param - none
   * @postcondition
   *   This bag is empty.
   **/
   public IntCircularLinkedBag( )
   {
      head = null;
      tail = head;
      manyNodes = 0;
   }
        
 
   /**
   * Add a new element to this bag.
   * @param element
   *   the new element that is being added
   * @postcondition
   *   A new copy of the element has been added to this bag.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory a new IntNode.
   **/
   public void add(int element)
   {     
      if (head == null)
      {
          // Set the head to a new IntNode with element and link null
          head = new IntNode(element, null);
          // Set tail equal to head
          tail = head;
          // Set the link of head to tail (in this case itself)
          head.setLink(tail);          
      }
      else
      {
          // Set the new element to head which links to the old head
          head = new IntNode(element, head);  
          // Set the link of tail to the hew head
          tail.setLink(head);
      }
      
      manyNodes++;
   }


   /**
   * Add the contents of another bag to this bag.
   * @param addend
   *   a bag whose contents will be added to this bag
   * @precondition
   *   The parameter, addend, is not null.
   * @postcondition
   *   The elements from addend have been added to this bag.
   * @exception NullPointerException
   *   Indicates that addend is null.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory to increase the size of the bag.
   **/
   public void addAll(IntCircularLinkedBag addend)
   {      
       // If this bag is empty set the head and tail to the 
       // head and tail of addend
       if(tail == null)
       {
          head = addend.head;
          tail = addend.tail;
       }
       // Other wise add addend onto the end of this bag
       else
       {
           // The precondition indicates that addend is not null. If it is null,
          // then a NullPointerException is thrown here. 
          // Set the link of the tail of this list to the head of addend
          tail.setLink(addend.head);
          // Set tail to the tail of addend
          tail = addend.tail;
          // Set the link of the tail of addend to the head of this list
          tail.setLink(head);           
       }
      
      manyNodes += addend.manyNodes;
   }

     
   /**
   * Add new elements to this bag. If the new elements would take this
   * bag beyond its current capacity, then the capacity is increased
   * before adding the new elements.
   * @param elements
   *   (a variable-arity argument)
   *   one or more new elements that are being inserted
   * @postcondition
   *   A new copy of the element has been added to this bag.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory to increase the size of the bag.
   **/
   public void addMany(int... elements)
   {
      // Activate the ordinary add method for each integer in the
      // elements array.
      for (int i : elements)
          add(i); 	       
   }

     
   /**
   * Generate a copy of this bag.
   * @param - none
   * @return
   *   The return value is a copy of this bag. Subsequent changes to the
   *   copy will not affect the original, nor vice versa. Note that the return
   *   value must be type cast to an IntCircularLinkedBag before it can be used.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for creating the clone.
   * @credit Nathan Aston provided me with the idea to use listPart
   **/ 
    @Override
   public IntCircularLinkedBag clone( )
   {  
      // Clone a nIntLinkedBag object.
      IntCircularLinkedBag answer;
      IntNode temp[];
      
      try
      {
         answer = (IntCircularLinkedBag) super.clone( );
      }
      catch (CloneNotSupportedException e)
      {  // This exception should not occur. But if it does, it would probably
         // indicate a programming error that made super.clone unavailable.
         // The most common error would be forgetting the "Implements Cloneable"
         // clause at the start of this class.
         throw new RuntimeException
         ("This class does not implement Cloneable");
      }
      
      // Get the head and tail of the bag being copied
      temp = IntNode.listPart(head, tail);
      // Set the head of answer to the head of the bag being copied
      answer.head = temp[0];
      // Set the tail of answer to the tail of the bag being copied
      answer.tail = temp[1];
      // Set the link of answers tail to answer head
      answer.tail.setLink(answer.head);
      
      return answer;
   }
   

   /**
   * Accessor method to count the number of occurrences of a particular element
   * in this bag.
   * @param target
   *   the element that needs to be counted
   * @return
   *   the number of times that target occurs in this bag
   **/
   public int countOccurrences(int target)
   {
      int answer = 0;
      IntNode cursor = head;

      // Check and count occurrences until cursor is equal head again
      do
      {   
          // If the data in cursor is equal to target increment answer
          if (cursor.getData() == target)
          {
              answer++;
          }    
          // Go to the next node
          cursor = cursor.getLink( );
      } while (cursor != head);
      
      return answer;
   }

    
   /**
   * Accessor method to retrieve a random element from this bag.
   * @param - none
   * @precondition
   *   This bag is not empty.
   * @return
   *   a randomly selected element from this bag
   * @exception IllegalStateException
   *   Indicates that the bag is empty.
   **/
   public int grab( )
   {
      int i;
      IntNode cursor;
      
      if (manyNodes == 0)
         throw new IllegalStateException("Bag size is zero");
         
      i =  (int)(Math.random( ) * manyNodes) + 1;
      cursor = IntNode.listPosition(head, i);
      return cursor.getData( );
   }
   
             
   /**
   * Remove one copy of a specified element from this bag.
   * @param target
   *   the element to remove from the bag
   * @postcondition
   *   If target was found in the bag, then one copy of
   *   target has been removed and the method returns true. 
   *   Otherwise the bag remains unchanged and the method returns false. 
   **/
   public boolean remove(int target)
   {
      IntNode cursor = head;
      
      // Check for target until cursor is equal head again
      do
      {   
          // If the data in cursor is equal to target remove it and return true
          if (cursor.getData() == target)
          {              
              // Set the data of cursor to the data of head
              cursor.setData(head.getData( ));
              // Set head to head's link
              head = head.getLink( );
              // Set tail's link to the new head
              tail.setLink(head);
              // Decrement manyNodes
              manyNodes--;
              
              // Return true because the target was found
              return true;
          }         
          cursor = cursor.getLink( );
      } while (cursor != head);
      
      // If the data was not found return false
      return false;
   }
    
      
   /**
   * Determine the number of elements in this bag.
   * @param - none
   * @return
   *   the number of elements in this bag
   **/                           
   public int size( )
   {
      return manyNodes;
   }
   

   /**
   * Create a new bag that contains all the elements from two other bags.
   * @param b1
   *   the first of two bags
   * @param b2
   *   the second of two bags
   * @precondition
   *   Neither b1 nor b2 is null.
   * @return
   *   the union of b1 and b2
   * @exception IllegalArgumentException
   *   Indicates that one of the arguments is null.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for the new bag.
   **/   
   public static IntCircularLinkedBag union(IntCircularLinkedBag b1, IntCircularLinkedBag b2)
   {       
      // The precondition requires that neither b1 nor b2 is null.
      // If one of them is null, then addAll will throw a NullPointerException.  
      IntCircularLinkedBag answer = new IntCircularLinkedBag( );
      
      answer.addAll(b1);
      answer.addAll(b2);     
      return answer;
   }
   
   /**
    * Prints the list and its size to the screen from head to tail
    * @parm - none
    */
   public void printList()
   {
       IntNode cursor = head;

       do
       {
           System.out.println(cursor.getData());       
           cursor = cursor.getLink();
       } while(cursor != head);
       
       System.out.println("Size: " + manyNodes);
   }
      
}
           
