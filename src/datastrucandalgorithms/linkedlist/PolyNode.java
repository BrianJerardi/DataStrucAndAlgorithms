package datastrucandalgorithms.linkedlist;

/**
 * A node for storing Polynomial terms
 * @author Brian Jerardi
 * @instructor Kendall Martin
 * @date 10/02/2011
 * @course CIS 112
 * @assignment HW2 Chapter 4 problem 13 page 239
 * @note
 *   Lists of nodes can be made of any length, limited only by the amount of
 *   free memory in the heap. But beyond Integer.MAX_VALUE (2,147,483,647),
 *   the answer from listLength is incorrect because of arithmetic
 *   overflow. 
 */
public class PolyNode implements Cloneable
{
    /*
     * Invariant of the PolynomialNodeV1 class:    
     *    1. The node's integer data is in the instance variable exponent.
     *    2. The node's double data is in the instance variable coefficient.
     *    3. For the final node of a list, the link part is null.
     *       Otherwise, the link part is a reference to the
     *       next node of the list. 
     */
    
    private int exponent;    
    private double coefficient;
    private PolyNode link;  
    
    /**
     * Initialize a node with a specified initial exponent, coefficient
     * and link to the next node. Note that the initialLink may be the 
     * null reference, which indicates that the new node has nothing after it.
     * @param intialExponent
     *  the initial exponent of this new node
     * @param intialCoefficient
     *  the initial coefficient of this new node
     * @param intialLink 
     *  a reference to the node after this new node--this reference may be null
     *  to indicate that there is no node after this new node.
     * @precondition
     *  intialExponent is not negative
     * @postcondition
     *  This node contains the specified data and link to the next node.
     * @throws IllegalArgumentException
     *  intialExponent is negative
     */
    public PolyNode
            (int intialExponent, double intialCoefficient, PolyNode intialLink)
    {
        if(intialExponent < 0)
        {
            throw new IllegalArgumentException("Exponent: " + intialExponent 
                    + " is not positive");
        }
        exponent = intialExponent;
        coefficient = intialCoefficient;
        link = intialLink;
    }
    
    /**
     * Modification method to add a new node after this node.   
     * @param ex
     *   the exponent to place in the new node
     * @param coe
     *   the coefficient to place in the new node
     * @precondition
     *  ex is not negative
     * @postcondition
     *   A new node has been created and placed after this node.
     *   The data for the new node is ex and coe. Any other nodes
     *   that used to be after this node are now after the new node.
     * @exception OutOfMemoryError
     *   Indicates that there is insufficient memory for a new 
     *   IntNode. 
     * @throws IllegalArgumentException
     *  ex is negative
     **/
    public void addNodeAfter(int ex, double coe)   
    {
        if(ex < 0)
        {
            throw new IllegalArgumentException("Exponent: " + ex 
                    + " is not positive");
        }
        
        link = new PolyNode(ex, coe, link);
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
    public void removeNodeAfter()
    {
        if(link != null)
        {
            link = link.link;
        }        
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
    public static PolyNode listCopy(PolyNode source)
    {
        PolyNode copyHead;
        PolyNode copyTail;
        
        // Handle the special case of the empty list.
        if (source == null)
        {
            return null;
        }            
        
        // Make the first node for the newly created list.
        copyHead = new PolyNode
                (source.getExponent(), source.getCoefficient(), null);
        copyTail = copyHead;
        
        // Make the rest of the nodes for the newly created list.
        while (source.link != null)
        {
            source = source.link;
            copyTail.addNodeAfter
                    (source.getExponent(), source.getCoefficient());
            copyTail = copyTail.link;
        }
        
        // Return the head reference for the new list.
        return copyHead;
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
    public static int listLength(PolyNode head)
    {
        PolyNode cursor;
        int answer;
              
        answer = 0;
              
        for (cursor = head; cursor != null; cursor = cursor.link)
        {
            answer++;
        }           
        
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
    public static PolyNode listPosition(PolyNode head, int position)
    {
        PolyNode cursor;
        int i;
        
        if (position <= 0)
        {
            throw new IllegalArgumentException("position is not positive");
        }            
        
        cursor = head;
        for (i = 1; (i < position) && (cursor != null); i++)
        {
            cursor = cursor.link;            
        }           
        
        return cursor;
    }  
    
    /**
     * Search for a term with exponent target in a linked list.
     * @param head
     *   the head reference for a linked list (which may be an empty list in
     *   which case the head is null)
     * @param target
     *   the exponent to search for
     * @return
     *   The return value is a reference to the first node that contains the
     *   specified target. If there is no such node, the null reference is 
     *   returned.     
     **/   
    public static PolyNode listSearch(PolyNode head, int target)
    {
        PolyNode cursor;
        
        for (cursor = head; cursor != null; cursor = cursor.link)
        {
            if (target == cursor.exponent)
            {
                return cursor;                
            }          
        }            
        
        return null;
    }
    
    /**
     * Accessor method to get the coefficient from this node.   
     * @param - none
     * @return
     *   the coefficient from this node
     **/
    public double getCoefficient()
    {
        return coefficient;
    }

    /**
     * Modification method to set the coefficient in this node.   
     * @param newCoefficient
     *   the new coefficient to place in this node
     * @postcondition
     *   The coefficient of this node has been set to newCoefficient.
     **/
    public void setCoefficient(double newCoefficient)
    {
        this.coefficient = newCoefficient;
    }

    /**
     * Accessor method to get the exponent from this node.   
     * @param - none
     * @return
     *   the exponent from this node
     **/
    public int getExponent()
    {
        return exponent;
    }

    /**
     * Modification method to set the exponent in this node.   
     * @param newExponent
     *   the new exponent to place in this node
     * @precondition
     *  newExponent is not negative
     * @postcondition
     *   The exponent of this node has been set to newExponent.
     * @throws IllegalArgumentException
     *  newExponent is negative
     **/
    public void setExponent(int newExponent)
    {
        if(newExponent < 0)
        {
            throw new IllegalArgumentException("Exponent: " + newExponent 
                    + " is not positive");
        }
        this.exponent = newExponent;
    }

    /**
     * Accessor method to get the link from this node.   
     * @param - none
     * @return
     *   the link from this node
     **/
    public PolyNode getLink()
    {
        return link;
    }

    /**
     * Modification method to set the link in this node.   
     * @param newLink
     *   the new link to place in this node
     * @postcondition
     *   The link of this node has been set to newLink.
     **/
    public void setLink(PolyNode newLink)
    {
        this.link = newLink;
    }
    
    /**
     * Clone this node
     * @return 
     *  A clone of this node
     */
    @Override
    public PolyNode clone()
      {
          PolyNode answer;
      
          try
          {
              answer = (PolyNode) super.clone( );
          }
          catch (CloneNotSupportedException e)
          {  // This exception should not occur. But if it does, it would probably
              // indicate a programming error that made super.clone unavailable.
              // The most common error would be forgetting the "Implements Cloneable"
              // clause at the start of this class.
              throw new RuntimeException
                      ("This class does not implement Cloneable");
          }
          
          if(link == null)
          {
              answer.link = null;
          }
          else
          {
              answer.link = link.clone( );
          }
          
          
          return answer;
      }    
    
    /**
     * Convert this node to a string represented as coefficient x^ exponent
     * @return 
     *  The String version of this node
     */
    @Override
    public String toString()
    {
        return coefficient + "x^" + exponent;
    }
    
}
