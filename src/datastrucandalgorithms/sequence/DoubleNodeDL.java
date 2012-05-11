package datastrucandalgorithms.sequence;


/**
 * A DoubleNodeDL provides a node for a doubly linked list with 
 * double data in each node.
 * @author Brian Jerardi
 * @instructor Kendall Martin
 * @date 10/10/2011
 * @course CIS 112
 * @assignment HW2 Chapter 4 problem 16 page 241
 * @note
*   Lists of nodes can be made of any length, limited only by the amount of
*   free memory in the heap. But beyond Integer.MAX_VALUE (2,147,483,647),
*   the answer from listLength is incorrect because of arithmetic
*   overflow. 
 * @credit 
 *  to Michael Main for the original implementation of the node, 
 *  I modified it to work as a doubly linked list
 */
public class DoubleNodeDL implements Cloneable
{
   // Invariant of the DoubleNodeDL class:
   //   1. The node's double data is in the instance variable data.
   //   2. For the final node of a list, the link part is null.
   //      Otherwise, the link part is a reference to the
   //      next node of the list.
   //   3. For the first node of a list, the prev part is null.
   //      Otherwise, the prev part is a reference to the 
   //      previous node of the list
   private double data;
   private DoubleNodeDL link;   
   private DoubleNodeDL prev;


   /**
   * Initialize a node with a specified initial data and link to the next
   * node. Note that the initialLink may be the null reference, 
   * which indicates that the new node has nothing after it.
   * Also intialPrev may be the null reference, 
   * which indicates that the new node has nothing before it.
   * @param initialData
   *   the initial data of this new node
   * @param initialLink
   *   a reference to the node after this new node--this reference may be null
   *   to indicate that there is no node after this new node.
   * @param intialPrev
   *   a reference to the node before this new node--this reference may be null
   *   to indicate that there is no node before this new node.
   * @postcondition
   *   This node contains the specified data, link to the next node and
   *   prev set to the previous node 
   **/   
   public DoubleNodeDL(double initialData, 
           DoubleNodeDL initialLink, DoubleNodeDL intialPrev)
   {
      data = initialData;
      link = initialLink;
      prev = intialPrev;
   }


   /**
   * Modification method to add a new node after this node.   
   * @param item
   *   the data to place in the new node
   * @postcondition
   *   A new node has been created and placed after this node.
   *   The data for the new node is item. Any other nodes
   *   that used to be after this node are now after the new node.
   * @exception OutOfMemoryError
   *   Indicates that there is insufficient memory for a new 
   *   DoubleNodeDL. 
   **/
   public void addNodeAfter(double item)   
   {
       // Set a temp node with the param item and link to the link of this node
       // and prev pointing to this node
      DoubleNodeDL temp = new DoubleNodeDL(item, link, this);
      
      // Set the link of this node to temp
      link = temp;
      
      // If there is a node after the inserted node set its prev to temp
      if (temp.link != null)
      {
          temp.link.setPrev(temp);           
      }              

   }
   
   /**
   * Modification method to add a new node before this node.   
   * @param item
   *   the data to place in the new node
   * @postcondition
   *   A new node has been created and placed before this node.
   *   The data for the new node is item. Any other nodes
   *   that used to be before this node are now before the new node.
   * @exception OutOfMemoryError
   *   Indicates that there is insufficient memory for a new 
   *   DoubleNodeDL. 
   **/
   public void addNodeBefore(double item)   
   {
      // Set a temp node with the param item and link to the this node
      // and prev pointing to the prev of this node
      DoubleNodeDL temp = new DoubleNodeDL(item, this, prev);
      
      // Set the prev of this node to temp
      prev = temp;         
       
      // If there was a node before this one set it's link to temp
      if (temp.prev != null)
      {
          temp.prev.setLink(temp);           
      }   
      
   }  
   
   
   /**
   * Accessor method to get the data from this node.   
   * @param - none
   * @return
   *   the data from this node
   **/
   public double getData( )   
   {
      return data;
   }
   
   
   /**
   * Accessor method to get a reference to the next node after this node. 
   * @param - none
   * @return
   *   a reference to the node after this node (or the null reference if there
   *   is nothing after this node)
   **/
   public DoubleNodeDL getLink( )
   {
      return link;                                               
   } 
   
   /**
   * Accessor method to get a reference to the previous node before this node. 
   * @param - none
   * @return
   *   a reference to the node before this node (or the null reference if there
   *   is nothing before this node)
   **/
   public DoubleNodeDL getPrev( )
   {
      return prev;                                               
   } 
    
    
   /**
   * Copy a list.
   * @param source
   *   the head of a linked list that will be copied (which may be
   *   an empty list in where source is null)
   * @return
   *   The method has made a copy of the linked list starting at 
   *   source. The return value is the head reference for the
   *   copy. 
   * @exception OutOfMemoryError
   *   Indicates that there is insufficient memory for the new list.   
   **/ 
   public static DoubleNodeDL listCopy(DoubleNodeDL source)
   {
      DoubleNodeDL copyHead;
      DoubleNodeDL copyTail;
      
      // Handle the special case of the empty list.
      if (source == null)
      {
         return null; 
      }         
         
      // Make the first node for the newly created list.
      copyHead = new DoubleNodeDL(source.data, null, null);
      copyTail = copyHead;
      
      // Make the rest of the nodes for the newly created list.
      while (source.link != null)
      {
         source = source.link;
         copyTail.addNodeAfter(source.data);
         copyTail = copyTail.link;
      }
 
      // Return the head reference for the new list.
      return copyHead;
   }
   
   
   /**
   * Copy a list, returning both a head and tail reference for the copy.
   * @param source
   *   the head of a linked list that will be copied (which may be
   *   an empty list in where source is null)
   * @return
   *   The method has made a copy of the linked list starting at 
   *   source.  The return value is an
   *   array where the [0] element is a head reference for the copy and the [1]
   *   element is a tail reference for the copy.
   * @exception OutOfMemoryError
   *   Indicates that there is insufficient memory for the new list.   
   **/
   public static DoubleNodeDL[ ] listCopyWithTail(DoubleNodeDL source)
   {
      DoubleNodeDL copyHead;
      DoubleNodeDL copyTail;
      DoubleNodeDL[ ] answer = new DoubleNodeDL[2];
     
      // Handle the special case of the empty list.   
      if (source == null)
      {
          return answer; // The answer has two null references .
      }         
      
      // Make the first node for the newly created list.
      copyHead = new DoubleNodeDL(source.data, null, null);
      copyTail = copyHead;
      
      // Make the rest of the nodes for the newly created list.
      while (source.link != null)
      {
         source = source.link;
         copyTail.addNodeAfter(source.data);
         copyTail = copyTail.link;
      }
      
      // Return the head and tail references.
      answer[0] = copyHead;
      answer[1] = copyTail;
      return answer;
   }
   
   
   /**
   * Compute the number of nodes in a linked list.
   * @param head
   *   the head reference for a linked list (which may be an empty list
   *   with a null head)
   * @return
   *   the number of nodes in the list with the given head 
   * @note
   *   A wrong answer occurs for lists longer than Int.MAX_VALUE.     
   **/   
   public static int listLength(DoubleNodeDL head)
   {
      DoubleNodeDL cursor;
      int answer;
      
      answer = 0;
      for (cursor = head; cursor != null; cursor = cursor.link)
      {
          answer++;          
      }         
        
      return answer;
   }
   

   /**
   * Copy part of a list, providing a head and tail reference for the new copy. 
   * @param start/end
   *   references to two nodes of a linked list
   * @param copyHead/copyTail
   *   the method sets these to refer to the head and tail node of the new
   *   list that is created
   * @precondition
   *   start and end are non-null references to nodes
   *   on the same linked list,
   *   with the start node at or before the end node. 
   * @return
   *   The method has made a copy of the part of a linked list, from the
   *   specified start node to the specified end node. The return value is an
   *   array where the [0] component is a head reference for the copy and the
   *   [1] component is a tail reference for the copy.
   * @exception IllegalArgumentException
   *   Indicates that start and end are not references
   *   to nodes on the same list.
   * @exception NullPointerException
   *   Indicates that start is null.
   * @exception OutOfMemoryError
   *   Indicates that there is insufficient memory for the new list.    
   **/   
   public static DoubleNodeDL[ ] listPart(DoubleNodeDL start, DoubleNodeDL end)
   {
      DoubleNodeDL copyHead;
      DoubleNodeDL copyTail;
      DoubleNodeDL cursor;
      DoubleNodeDL[ ] answer = new DoubleNodeDL[2];
      
      // Make the first node for the newly created list. Notice that this will
      // cause a NullPointerException if start is null.
      copyHead = new DoubleNodeDL(start.data, null, null);
      copyTail = copyHead;
      cursor = start;
      
      // Make the rest of the nodes for the newly created list.
      while (cursor != end)
      {
         cursor = cursor.link;
         if (cursor == null)
            throw new IllegalArgumentException
            ("end node was not found on the list");
         copyTail.addNodeAfter(cursor.data);
         copyTail = copyTail.link;
      }
      
      // Return the head and tail references
      answer[0] = copyHead;
      answer[1] = copyTail;
      return answer;
   }        
   
   
   /**
   * Find a node at a specified position in a linked list.
   * @param head
   *   the head reference for a linked list (which may be an empty list in
   *   which case the head is null)
   * @param position
   *   a node number
   * @precondition
   *   position > 0.
   * @return
   *   The return value is a reference to the node at the specified position in
   *   the list. (The head node is position 1, the next node is position 2, and
   *   so on.) If there is no such position (because the list is too short),
   *   then the null reference is returned.
   * @exception IllegalArgumentException
   *   Indicates that position is not positive.    
   **/   
   public static DoubleNodeDL listPosition(DoubleNodeDL head, int position)
   {
      DoubleNodeDL cursor;
      int i;
      
      if (position <= 0)
           throw new IllegalArgumentException("position is not positive");
      
      cursor = head;
      for (i = 1; (i < position) && (cursor != null); i++)
         cursor = cursor.link;

      return cursor;
   }


   /**
   * Search for a particular piece of data in a linked list.
   * @param head
   *   the head reference for a linked list (which may be an empty list in
   *   which case the head is null)
   * @param target
   *   a piece of data to search for
   * @return
   *   The return value is a reference to the first node that contains the
   *   specified target. If there is no such node, the null reference is 
   *   returned.     
   **/   
   public static DoubleNodeDL listSearch(DoubleNodeDL head, double target)
   {
      DoubleNodeDL cursor;
      
      for (cursor = head; cursor != null; cursor = cursor.link)
         if (target == cursor.data)
            return cursor;
        
      return null;
   }

   
   /**
   * Modification method to remove the node after this node.   
   * @param - none
   * @precondition
   *   This node must not be the tail node of the list.
   * @postcondition
   *   The node after this node has been removed from the linked list.
   *   If there were further nodes after that one, they are still
   *   present on the list.
   * @exception NullPointerException
   *   Indicates that this was the tail node of the list, so there is nothing
   *   after it to remove.
   **/
   public void removeNodeAfter( )   
   {
      link = link.link;
      link.prev = this;
   }  
   
   /**
   * Modification method to remove the node before this node.   
   * @param - none
   * @precondition
   *   This node must not be the head node of the list.
   * @postcondition
   *   The node before this node has been removed from the linked list.
   *   If there were further nodes before that one, they are still
   *   present on the list.
   * @exception NullPointerException
   *   Indicates that this was the head node of the list, so there is nothing
   *   before it to remove.
   **/
   public void removeNodeBefore( )   
   {
      prev = prev.prev;
      prev.link = this;
   } 
   
   
   /**
   * Modification method to set the data in this node.   
   * @param newData
   *   the new data to place in this node
   * @postcondition
   *   The data of this node has been set to newData.
   **/
   public void setData(double newData)   
   {
      data = newData;
   }                                                               
   
   
   /**
   * Modification method to set the link to the next node after this node.
   * @param newLink
   *   a reference to the node that should appear after this node in the linked
   *   list (or the null reference if there is no node after this node)
   * @postcondition
   *   The link to the node after this node has been set to newLink.
   *   Any other node (that used to be in this link) is no longer connected to
   *   this node.
   **/
   public void setLink(DoubleNodeDL newLink)
   {                    
      link = newLink;
      newLink.prev = this;
   }
   
   /**
   * Modification method to set the prev to the node before this node.
   * @param newLink
   *   a reference to the node that should appear before this node in the linked
   *   list (or the null reference if there is no node before this node)
   * @postcondition
   *   The link to the node before this node has been set to newPrev.
   *   Any other node (that used to be in this prev) is no longer connected to
   *   this node.
   **/
   public void setPrev(DoubleNodeDL newPrev)
   {                    
      prev = newPrev;
      newPrev.link = this;
   }
    
}
           
