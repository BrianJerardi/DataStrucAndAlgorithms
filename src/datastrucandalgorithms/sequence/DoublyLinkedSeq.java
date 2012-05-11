package datastrucandalgorithms.sequence;


/**
 * An implementation of the Sequence class using a double linked list
 * a collection of double values
 * @author Brian Jerardi
 * @instructor Kendall Martin
 * @date 10/10/2011
 * @course CIS 112
 * @assignment HW2 Chapter 4 problem 16 page 241
 * @limitations
 *  Beyond Int.MAX_VALUE elements, the size method does not work.
 */
public class DoublyLinkedSeq implements Cloneable
{
    /*
     *  Invariant of the DoublyLinkedSeq class:
     *    1. The elements in the bag are stored on a linked list.
     *    2. The head reference of the list is a dummy node.
     *    3. The tail reference of the list is a dummy node.
     *    4. The total number of elements in the list is in the instance 
     *       variable manyNodes. 
     *    5. The cursor reference of the list is in the instance variable cursor
     */
    
    private int manyNodes;
    DoubleNodeDL head;
    DoubleNodeDL tail;
    DoubleNodeDL cursor;
   
   /**
   * Initialize an empty sequence.
   * @param - none
   * @postcondition
   *   This sequence is empty.
   **/   
   public DoublyLinkedSeq( )
   {
      head = new DoubleNodeDL(Double.NaN, null, null);
      head.addNodeAfter(Double.NaN);
      tail = head .getLink();
      manyNodes = 0;
   }
    
 
   /**
   * Add a new element to this sequence, after the current element. 
   * @param element
   *   the new element that is being added
   * @postcondition
   *   A new copy of the element has been added to this sequence. If there was
   *   a current element, then the new element is placed after the current
   *   element. If there was no current element, then the new element is placed
   *   at the end of the sequence. In all cases, the new element becomes the
   *   new current element of this sequence. 
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for a new node.
   **/
   public void addAfter(int element)
   {
       if (isCurrent() && cursor != tail)
       {
           cursor.addNodeAfter(element);
           cursor = cursor.getLink();
       }
       else
       {
           tail.addNodeBefore(element);
           cursor = tail.getPrev();
       }      
       manyNodes++;
   }


   /**
   * Add a new element to this sequence, before the current element. 
   * @param element
   *   the new element that is being added
   * @postcondition
   *   A new copy of the element has been added to this sequence. If there was
   *   a current element, then the new element is placed before the current
   *   element. If there was no current element, then the new element is placed
   *   at the start of the sequence. In all cases, the new element becomes the
   *   new current element of this sequence. 
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for a new node.
   **/
   public void addBefore(int element)
   {
       if (isCurrent() && cursor != head)
       {
           cursor.addNodeBefore(element);
           cursor = cursor.getPrev();
       }
       else
       {
           head.addNodeAfter(element);
           cursor = head.getLink();
       }   

      manyNodes++;
   }
   
   
   /**
   * Place the contents of another sequence at the end of this sequence.
   * @param addend
   *   a sequence whose contents will be placed at the end of this sequence
   * @precondition
   *   The parameter, addend, is not null. 
   * @postcondition
   *   The elements from addend have been placed at the end of 
   *   this sequence. The current element of this sequence remains where it 
   *   was, and the addend is also unchanged.
   * @exception NullPointerException
   *   Indicates that addend is null. 
   * @exception OutOfMemoryError
   *   Indicates insufficient memory to increase the size of this sequence.
   **/
   public void addAll(DoublyLinkedSeq addend)
   {
      for(DoubleNodeDL cursorTemp = addend.head.getLink(); 
              cursorTemp.getLink() != null; cursorTemp = cursorTemp.getLink())
      {
          tail.addNodeBefore(cursorTemp.getData());
          manyNodes++;
      }
   }   
   
   
   /**
   * Move forward, so that the current element is now the next element in
   * this sequence.
   * @param - none
   * @precondition
   *   isCurrent() returns true. 
   * @postcondition
   *   If the current element was already the end element of this sequence 
   *   (with nothing after it), then there is no longer any current element. 
   *   Otherwise, the new element is the element immediately after the 
   *   original current element.
   * @exception IllegalStateException
   *   Indicates that there is no current element, so 
   *   advance may not be called.
   **/
   public void advance( )
   {
       if (!isCurrent())
       {
           throw new IllegalStateException("There is no current element.");
       }
       
       if (cursor.getLink() == tail)
       {
           cursor = null;
       }
       else
       {
           cursor = cursor.getLink();
       }             
   }
   
   /**
   * Move backward, so that the current element is now the previous element in
   * this sequence.
   * @param - none
   * @precondition
   *   isCurrent() returns true. 
   * @postcondition
   *   If the current element was already the first element of this sequence 
   *   (with nothing before it), then there is no longer any current element. 
   *   Otherwise, the new element is the element immediately before the 
   *   original current element.
   * @exception IllegalStateException
   *   Indicates that there is no current element, so 
   *   retreat may not be called.
   **/
   public void retreat( )
   {
       if (!isCurrent())
       {
           throw new IllegalStateException("There is no current element.");
       }
       
       if (cursor.getPrev() == head)
       {
           cursor = null;
       }
       else
       {
           cursor = cursor.getPrev();
       } 
   }
   
   
   /**
   * Generate a copy of this sequence.
   * @param - none
   * @return
   *   The return value is a copy of this sequence. Subsequent changes to the
   *   copy will not affect the original, nor vice versa.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for creating the clone.
   **/ 
    @Override
   public DoublyLinkedSeq clone( )
   {  
       DoublyLinkedSeq answer;
       DoubleNodeDL headAndTail[];
      
      try
      {
         answer = (DoublyLinkedSeq) super.clone( );
      }
      catch (CloneNotSupportedException e)
      {  // This exception should not occur. But if it does, it would probably
         // indicate a programming error that made super.clone unavailable.
         // The most common error would be forgetting the "Implements Cloneable"
         // clause at the start of this class.
         throw new RuntimeException
         ("This class does not implement Cloneable");
      }

      headAndTail = DoubleNodeDL.listCopyWithTail(head);
      answer.head = headAndTail[0];
      answer.tail = headAndTail[1];
      if (cursor != null)
      {
          answer.cursor = DoubleNodeDL.listPart(cursor, cursor)[0];           
      }
      
      
      return answer;
   }
   

   /**
   * Create a new sequence that contains all the elements from one sequence
   * followed by another.
   * @param s1
   *   the first of two sequences
   * @param s2
   *   the second of two sequences
   * @precondition
   *   Neither s1 nor s2 is null.
   * @return
   *   a new sequence that has the elements of s1 followed by the
   *   elements of s2 (with no current element)
   * @exception NullPointerException.
   *   Indicates that one of the arguments is null.
   * @exception OutOfMemoryError
   *   Indicates insufficient memory for the new sequence.
   **/   
   public static DoublyLinkedSeq catenation(DoublyLinkedSeq s1, DoublyLinkedSeq s2)
   {
      DoublyLinkedSeq answer = new DoublyLinkedSeq();
      
      answer.addAll(s1);
      answer.addAll(s2);
      
      return answer;
   }


   /**
   * Accessor method to get the current element of this sequence. 
   * @param - none
   * @precondition
   *   isCurrent() returns true.
   * @return
   *   the current capacity of this sequence
   * @exception IllegalStateException
   *   Indicates that there is no current element, so 
   *   getCurrent may not be called.
   **/
   public double getCurrent( )
   {
      if (!isCurrent())
      {
          throw new IllegalStateException("There is no current element.");
      }
       
      return cursor.getData();
   }


   /**
   * Accessor method to determine whether this sequence has a specified 
   * current element that can be retrieved with the 
   * getCurrent method. 
   * @param - none
   * @return
   *   true (there is a current element) or 
   *  false (there is no current element at the moment)
   **/
   public boolean isCurrent( )
   {
      if(cursor == null)
      {
          return false;
      }
      
      return true;
   }
              
   /**
   * Remove the current element from this sequence.
   * @param - none
   * @precondition
   *   isCurrent() returns true.
   * @postcondition
   *   The current element has been removed from this sequence, and the 
   *   following element (if there is one) is now the new current element. 
   *   If there was no following element, then there is now no current 
   *   element.
   * @exception IllegalStateException
   *   Indicates that there is no current element, so 
   *   removeCurrent may not be called. 
   **/
   public void removeCurrent( )
   {
      if(!isCurrent())
      {
          throw new IllegalStateException("No current element");
      }
      
      cursor.getPrev().removeNodeAfter();
      if (cursor.getLink() == tail)
      {
          cursor = null;
      }
      else
      {
          cursor = cursor.getLink();
      }
       
      manyNodes--;
   }
                 
   
   /**
   * Determine the number of elements in this sequence.
   * @param - none
   * @return
   *   the number of elements in this sequence
   **/ 
   public int size( )
   {
      return manyNodes;
   }
   
   
   /**
   * Set the current element at the front of this sequence.
   * @param - none
   * @postcondition
   *   The front element of this sequence is now the current element (but 
   *   if this sequence has no elements at all, then there is no current 
   *   element).
   **/ 
   public void start( )
   {
       if (head.getLink() != tail)
       {
           cursor = head.getLink();
       }
      
   }
   
   /**
    * Print each element in the Sequence in order from the head to the tail
    * Also prints the size
    * @param - none
    * @postcondition
    *   The sequence and it's size were printed to the screen
    */
   public void printSeqForward()
   {
       for(DoubleNodeDL tempCursor = head.getLink(); 
               tempCursor.getLink() != null; tempCursor = tempCursor.getLink())
       {
           System.out.println(tempCursor.getData());
       }
       
       System.out.println("Size: " + manyNodes);
       
   }
   
   /**
    * Print each element in the Sequence in order from the tail to the head
    * Also prints the size
    * @param - none
    * @postcondition
    *   The sequence and it's size were printed to the screen
    */
   public void printSeqBackward()
   {
       for(DoubleNodeDL tempCursor = tail.getPrev(); 
               tempCursor.getPrev() != null; tempCursor = tempCursor.getPrev())
       {
           System.out.println(tempCursor.getData());
       }
       
       System.out.println("Size: " + manyNodes);
   }
}
