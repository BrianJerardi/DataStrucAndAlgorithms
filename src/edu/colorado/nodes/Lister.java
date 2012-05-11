// File: Lister.java from the package edu.colorado.nodes
// Complete documentation is available from the Lister link in:
//   http://www.cs.colorado.edu/~main/docs/

package edu.colorado.nodes;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.colorado.nodes.Node; 

/******************************************************************************
* A <CODE>Lister</CODE> implements Java's <CODE>Iterator&lt;E&gt;</CODE>
* interface for a linked list made from <CODE>Node&lt;E&gt;</CODE> nodes.
* Note that this implementation of an <CODE>Iterator&lt;E&gt;</CODE> does not
* support the <CODE>remove</CODE> method. Any activation of <CODE>remove</CODE>
* results in an
* <CODE>UnsupportedOperationException</CODE>.
*
* <dt><b>Java Source Code for this class:</b><dd>
*   <A HREF="../../../../edu/colorado/nodes/Lister.java">
*   http://www.cs.colorado.edu/~main/edu/colorado/nodes/Lister.java </A>
*
* @author Michael Main 
*   <A HREF="mailto:main@colorado.edu"> (main@colorado.edu) </A>
*
* @version
*   Jul 16, 2005
*
* @see Node
******************************************************************************/
public class Lister<E> implements Iterator<E>
{
   // Invariant of the Lister class:
   //   The instance variable list is the head reference for the linked list 
   //   that contains the elements that have not yet been provided by
   //   the next method. If there are no more elements to provide, then
   //   list is the null reference.  
   private Node<E> list;
 

   /**
   * Initialize a Lister with a specified linked list.
   * @param head
   *   a head reference for a linked list of objects
   * <dt><b>Postcondition:</b><dd>
   *   Subsequent activations of <CODE>next</CODE> will return the elements 
   *   from this linked list, one after another. If the linked list changes
   *   in any way before all the elements have been returned, then the
   *   subsequent behavior of this <CODE>Lister</CODE> is unspecified.
   **/
   public Lister(Node<E> head)
   {
      list = head;
   }
        
 
   /**
   * Determine whether there are any more elements in this Lister.
   * @param - none
   * @return
   *   <CODE>true</CODE> if there are more elements in this
   *   <CODE>Lister</CODE>; <CODE>false</CODE> otherwise.
   **/
   public boolean hasNext( )
   {      
      return (list != null);
   }


   /**
   * Retrieve the next element of this <CODE>Lister</CODE>.
   * @param - none
   *   a bag whose contents will be added to this bag
   * <dt><b>Precondition:</b><dd>
   *   <CODE>hasMoreElements()</CODE> must be <CODE>true</CODE>.
   * @return
   *   The return value is the next element of this <CODE>Lister</CODE>.
   *   Note that each element is only returned once, and then the
   *   <CODE>Lister</CODE> automatically advances to the next element.
   * @exception NoSuchElementException
   *   Indicates that <CODE>hasMoreElements()</CODE> is <CODE>false<CODE>.
   **/
   public E next( )
   {
      E answer;
      
      if (!hasNext( ))
         throw new NoSuchElementException("The Lister is empty");
      
      answer = list.getData( );
      list = list.getLink( );
      
      return answer;
   }
      
   
   public void remove( )
   {
      throw new UnsupportedOperationException("Lister.remove not allowed");
   }
}
