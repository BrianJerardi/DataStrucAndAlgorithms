   package datastrucandalgorithms.linkedlist;

/**
* A Linked List to manage Polynomials using PolyNodes
* @author Brian Jerardi
* @instructor Kendall Martin
* @date 10/02/2011
* @course CIS 112
* @assignment HW2 Chapter 4 problem 13 page 239
*/
   public class PolyLinkedList implements Cloneable
   {
    /*
     *  Invariant of the PolyLinkedList class:
     *    1. The elements in the list are stored on a linked list.
     *    2. The head reference of the list is in the instance variable head.
     *    3. The tail reference of the list is in the instance variable tail.
     *    4. The total number of elements in the list is in the instance 
     *       variable size. 
     *    5. The last accessed node reference is in the instance variable
     *       lastAccessed
     */
      private PolyNode head;
      private PolyNode tail;
      private PolyNode lastAccessed;
      private int size;
   
   
   /**
   * Initialize an empty list.
   * @param - none
   * @postcondition
   *   This list is empty.
   **/
      public PolyLinkedList()
      {
         head = null;
         tail = null;
         size = 0;
      }
   
   /**
   * Initialize a list with one PolyNode whose term is intialCoeffiecient x^0
   * @param intialCoefficient 
   *    The coefficient of the exponent 0
   * @postcondition
   *    This list contains one node whose term is intialCoeffiecient x^0
   *    head, tail and lastAccessed are all set to this node
   */
      public PolyLinkedList(double intialCoefficient)
      {
         this(new PolyNode(0, intialCoefficient, null));
      }
   
   /**
   * Initialize a list with one PolyNode
   * @param intialNode 
   *    The node to put into the list
   * @postcondition
   *    This list contains one node that is set to intialNode
   *    head, tail and lastAccessed are all set to this node
   */
      public PolyLinkedList(PolyNode intialNode)
      {
         head = intialNode;
         tail = intialNode;
         lastAccessed = intialNode;
         size = 1;
      }
   
   /**
   * Add a node to the list with the specified exponent and coefficient
   * @param exponent
   *    The exponent to put into the added node
   * @param coefficient 
   *    The coefficient to put into the added node
   * @precondition
   *    There is no node in the list with this exponent and exponent is positive
   * @postcondition
   *    A node has been created and put in order into the this list
   *    lastAccessed is set to this node
   * @throws IllegalArgumentException
   *    exponent already exists in this list or negative
   */
      public void addNode(int exponent, double coefficient)
      {      
         PolyNode beforeInsert;
         if (isExponent(exponent))
         {
            throw new IllegalArgumentException("Exponent " + exponent 
                  + " already exists");
         }
      
         if(head == null)
         {
            head = new PolyNode(exponent, coefficient, null);
            tail = head;
            lastAccessed = head;
         }
         else
         {
         // Add the node in order
            beforeInsert = sortedAdd(exponent);
            if (beforeInsert == null)
            {
               head = new PolyNode(exponent, coefficient, head);
               lastAccessed = head;
            }
            else
            {
               beforeInsert.addNodeAfter(exponent, coefficient);
               lastAccessed = beforeInsert.getLink();
            }
         
         // Used for selection sort
         //head.addNodeAfter(exponent, coefficient);
         //head = listSort(head);
         
         // Set the new tail to the last node in the list
            tail = PolyNode.listPosition(head, size + 1);
         }       
      
         size++;
      }
   
   
   /**
   * Checks to see if checkExponent is already in this list
   * @param checkExponent
   *    The exponent to check for
   * @return 
   *    true: if the exponent is in the list
   *    false: if the exponent is not in the list
   */
      private boolean isExponent(int checkExponent)
      {
         if(PolyNode.listSearch(head, checkExponent) == null)
         {
            return false;
         }
      
         return true;
      }
   
   /**
   * Sorts the list by exponent from smallest to largest
   * @param head
   *    The head of the list to sort
   * @return 
   *    A PolyNode reference to a new head of a list that is sorted
   * @postcondition
   *    a new list has been created with all the nodes in the list of head
   *    that is sorted by exponent from smallest to largest
   * @note
   *    Not used in final implementation, but it shows the selection sort method
   *    described in the book
   */
      private PolyNode listSort(PolyNode head)
      {
         PolyNode answerHead = null;
         PolyNode cursor, largest;
         PolyNode beforeLargest = null;
         PolyNode lastFound = null;
      
      // Loop through list while head is not null
         while (head != null)
         {          
         // Set largest to head, and beforeLargest to null, and lastAccessed to
         // largest
            largest = head;
            beforeLargest = null;
            lastFound = largest;
         
         // Loop through list
            for(cursor = head.getLink(); cursor != null; cursor = cursor.getLink())
            {
            // If new largest found set beforeLargest and largest
               if (cursor.getExponent() > largest.getExponent())
               {
                  beforeLargest = lastFound;
                  largest = cursor;
               }
            
            // Set lastFound so it can be used to set beforeLargest
               lastFound = cursor;
            }
         
         // If the head was largest remove it
            if (beforeLargest == null)
            {
               head = head.getLink();
            }
            // Otherwise remove the node after beforeLargest
            else
            {
               beforeLargest.removeNodeAfter();
            }        
         
         
         // If the head of the new list is null set it to the largest
            if (answerHead == null)
            {
               answerHead = new PolyNode
                    (largest.getExponent(), largest.getCoefficient(), null);
            }
            // Otherwise add set the head to the largest data with a link to
            // the old head
            else
            {
               answerHead = new PolyNode(largest.getExponent(), 
                    largest.getCoefficient(), answerHead);
            }   
         }
      
         return answerHead;
      }
   
   /**
   * Finds the term before the place to insert a term with the given exponent
   * @param exponent
   *    The exponent of the term that will be inserted
   * @precondition
   *    This linked list is already sorted, and head is not null
   * @return 
   *    A reference of the term this exponent should be inserted after
   */
      private PolyNode sortedAdd(int exponent)
      {
         PolyNode preCursor = null;
      
         for (PolyNode cursor = head; 
              cursor != null && cursor.getExponent() < exponent; 
              cursor = cursor.getLink())
         {          
            preCursor = cursor;
         }
      
         return preCursor;
      }
   
   /**
   * Add an amount to the coefficient of the value at exponent k
   * @param amount
   *    The amount to add to the coefficient
   * @param k 
   *    The exponent whose coefficient to add to
   * @precondition
   *    A term exists with exponent k
   * @postcondition
   *    amount has been added to the coefficient at exponent k
   *    lastAcessed has been set to this node
   * @throws IllegalArgumentException
   *    if there is no term at exponent k
   */
      public void add_to_coef(double amount, int k)
      {
         lastAccessed = PolyNode.listSearch(head, k);
      
         if (lastAccessed == null)
         {
            throw new IllegalArgumentException
                  ("No term exists with exponent: " + k);
         }
      
         lastAccessed.setCoefficient(lastAccessed.getCoefficient() + amount);
      }
   
   /**
   * Assign a new coefficient to the term with exponent k
   * @param new_coefficient
   *    The new coefficient to assign to the term
   * @param k 
   *    The exponent of the term to change
   * @precondition
   *    A term exists with exponent k
   * @postcondition
   *    the coefficient at exponent k has been set to new_coefficient
   *    lastAcessed has been set to this node
   * @throws IllegalArgumentException
   *    if there is no term at exponent k
   */
      public void assing_coef(double new_coefficient, int k)
      {
         lastAccessed = PolyNode.listSearch(head, k);
      
         if (lastAccessed == null)
         {
            throw new IllegalArgumentException
                  ("No term exists with exponent: " + k);
         }
      
         lastAccessed.setCoefficient(new_coefficient);
      }
   
   /**
   * Clears the list by putting 0 into all of the existing terms coefficient
   * @param - none
   * @postcondition
   *    All terms in this list have 0 coefficients
   */
      public void clear()
      {
         for(PolyNode cursor = head; cursor != null; cursor = cursor.getLink())
         {
            cursor.setCoefficient(0);
         }
      }
   
   /**
   * Reserves space in this list by putting 0's into all coefficients up to 
   * degree that do not already have a term
   * @param degree 
   *    The degree to fill to
   * @postcondition
   *    term x^0 to x^degree exists and any that didn't already exist have
   *    coefficients of 0
   */
      public void reserve(int degree)
      {        
      // Insert 0 coefficients terms into any gaps in the existing list
         for(int i = 0; i < size; i++)
         {          
            if (!isExponent(i))
            {
               addNode(i, 0);
            }
         
         }
      
      // Insert 0 coefficients terms 
      // after the last term in the list up to degree
         for(int i = size; i < degree + 1; i++)
         {    
            addNode(i, 0);
         }
      }
   
   /**
   * Accessor method to get the coefficient with exponent k
   * @param k
   *    the exponent of the term to find the coefficient of
   * @precondition
   *    an exponent of k exists in this list
   * @return 
   *    the coefficient of the term with exponent k
   * @throws IllegalArgumentException
   *    There is no term with exponent k
   */
      public double coefficient(int k)
      {
         if(!isExponent(k))
         {
            throw new IllegalArgumentException("No exponent: " + k);
         }
         return PolyNode.listSearch(head, k).getCoefficient();
      }
   
   /**
   * Get the degree of this polynomial list
   * @return 
   *    The degree of this polynomial list 
   *    -1 if the list is empty
   */
      public int degree()
      {
         int degree = -1;
      
      
         for(PolyNode cursor = head; cursor != null; cursor = cursor.getLink())
         {
            if(cursor.getCoefficient() != 0)
            {
               degree = cursor.getExponent();
            }
         }
      
         return degree;          
      }
   
   /**
   * Get the next term with a non-zero coefficient after the term with 
   * exponent k
   * @param k
   *    The exponent of the term to start the search
   * @precondition
   *    A node exists with the exponent k
   * @return 
   *    The first term with a non-zero coefficient after the term with 
   *    exponent k
   * @throws IllegalArgumentException
   *    There is no term with exponent k
   */
      public int next_term(int k)
      {
         if (!isExponent(k))
         {
            throw new IllegalArgumentException("No exponent: " + k);
         }
      
      // Loop through list
         for(PolyNode cursor = PolyNode.listSearch(head, k).getLink(); 
              cursor != null; 
              cursor = cursor.getLink())
         {
          // If the coefficient is not 0 return this exponent
            if(cursor.getCoefficient() != 0)
            {
               return cursor.getExponent();
            }
         }
      
      // Return -1 if no non-zero coefficient was found 
      // after term with exponent k
         return -1;
      }
   
   /**
   * Evaluate the polynomial for x = parameter x
   * @param x
   *    The value to evaluate the polynomial for
   * @return 
   *    What the polynomial evaluates to with x = parameter x
   */
      public double eval(double x)
      {
         double answer = 0;
      
      // Add the evaluation of each term to answer
         for(PolyNode cursor = head; cursor != null; cursor = cursor.getLink())
         {
            answer += cursor.getCoefficient() * Math.pow(x, cursor.getExponent());
         }
      
         return answer;
      }
   
   /**
   * Add two polynomials together
   * @param l1
   *    The first polynomial to add
   * @param l2
   *    The second polynomial to add
   * @return 
   *    A polynomial linked list of l1 + l2
   */
      public static PolyLinkedList 
          polyAddition(PolyLinkedList l1, PolyLinkedList l2)
      {
         PolyLinkedList answer = new PolyLinkedList();
      
      // Ensure the size of the List's are equal
         if (l1.degree() < l2.degree())
         {
            l1.reserve(l2.degree());
            l2.reserve(l2.degree());
         }
         else if (l1.degree() > l2.degree())
         {
            l2.reserve(l1.degree());
            l1.reserve(l1.degree());
         }
      
      // Add coefficient's with the same exponents
         for (int i = 0; i < l1.size; i++)
         {
            answer.addNode(i, l1.coefficient(i) + l2.coefficient(i));
         }
      
         return answer;
      }
   
   /**
   * Subtract one polynomial from another
   * @param l1
   *    The first polynomial
   * @param l2
   *    The polynomial to subtract from the first
   * @return 
   *    A polynomial linked list of l1 - l2
   */
      public static PolyLinkedList 
          polySubtraction(PolyLinkedList l1, PolyLinkedList l2)
      {
         PolyLinkedList answer = new PolyLinkedList();
      
      // Ensure the size of the List's are equal
         if (l1.degree() < l2.degree())
         {
            l1.reserve(l2.degree());
            l2.reserve(l2.degree());
         }
         else if (l1.degree() > l2.degree())
         {
            l2.reserve(l1.degree());
            l1.reserve(l1.degree());
         }
      
      // Subtract coefficient's with the same exponents
         for (int i = 0; i < l1.size; i++)
         {
            answer.addNode(i, l1.coefficient(i) - l2.coefficient(i));
         }
      
         return answer;
      }
   
   /**
   * Multiply two polynomials
   * @param l1
   *    The first polynomial to multiply
   * @param l2
   *    The second polynomial to multiply
   * @return 
   *    A polynomial linked list of l1 * l2
   */
      public static PolyLinkedList 
          polyMultiplication(PolyLinkedList l1, PolyLinkedList l2)
      {
         PolyLinkedList answer = new PolyLinkedList(0);          
      
      // Ensure the size of the List's are equal
         if (l1.degree() < l2.degree())
         {
            l1.reserve(l2.degree());
            l2.reserve(l2.degree());
         }
         else if (l1.degree() > l2.degree())
         {
            l2.reserve(l1.degree());
            l1.reserve(l1.degree());
         }
      
      // Reserve space for answer
         answer.reserve(l1.degree() + l2.degree());
      
      // Loop through list l1
         for(PolyNode cursor = l1.getHead(); 
              cursor != null && cursor.getExponent() <= l1.degree(); 
              cursor = cursor.getLink())
         {
          // Loop through list l2
            for(PolyNode innerCursor = l2.getHead(); 
                  innerCursor != null && 
                  innerCursor.getExponent() <= l2.degree(); 
                  innerCursor = innerCursor.getLink())
            {
              // Multiply terms
               answer.add_to_coef
                      (cursor.getCoefficient() * innerCursor.getCoefficient(), 
                      cursor.getExponent() + innerCursor.getExponent());
            }              
         }      
      
         return answer;
      }
   
   /**
   * Get the n-th derivative of this polynomial
   * @param n
   *    The number of derivatives to calculate
   * @return 
   *    the n-th derivative of this polynomial
   * @postcondition
   *    The return value is the n-th derivative of this polynomial.
   * @note
   *    Tip to use recursion from Nathon Aston
   */
      public PolyLinkedList derivative(int n)
      {
         PolyLinkedList answer = new PolyLinkedList();
      
      // Loop through the list
         for(PolyNode cursor = head; cursor != null; cursor = cursor.getLink())
         {
          // Add a node to the answer List with exponent of the 
          // curosr exponent - 1 and coefficient of the cursor coefficient
          // times the cursor exponent
            try
            {
               answer.addNode(  (cursor.getExponent() - 1), (cursor.getCoefficient() * cursor.getExponent()));
            }
               catch (IllegalArgumentException e)
               {
               // Placed here to continue if exponent is negative
               }
         }
      
      
      // If the decrement of n is not 0 get the next derivative
         if (--n != 0)
         {
            answer = answer.derivative(n); 
         }      
      
         return answer;
      }
   
   /**
   * Get the n-th integral of this polynomial
   * @param n
   *    The number of integrals to calculate
   * @return 
   *    The n-th integral of this polynomial
   * @postcondition
   *    The return value is the n-th integral of this polynomial.
   */
      public PolyLinkedList integral(int n)
      {
         PolyLinkedList answer = new PolyLinkedList();
      
      // Loop through the list
         for(PolyNode cursor = head; cursor != null; cursor = cursor.getLink())
         {
          // Add a node to answer with an exponent of the cursors exponent plus
          // 1 and a coefficient of the cursor's coefficient divided by
          // the cursor's exponent plus 1
            answer.addNode(  (cursor.getExponent() + 1), (cursor.getCoefficient() 
                  / (cursor.getExponent() + 1) ) );
         }
      
      // If the decrement of n is not 0 get the next integral
         if (--n != 0)
         {
            answer = answer.integral(n); 
         }    
      
         return answer;
      }
   
   /**
   * Find the root of this polynomial
   * @param starting_guess
   *    The starting guess of the root
   * @param maximum_iterations
   *    The maximum times to try to find the root, if reached throws exception
   * @param epsilon
   *    How close the absolute value of the polynomial must be for success
   * @return 
   *    The value that will make this polynomial evaluate epsilon close to 0
   * @throws IllegalStateException
   *    The maximum iterations was reached, or Flat Failure occurred
   */
      public double find_root
          (double starting_guess, int maximum_iterations, double epsilon)
      {
      // Set x to the starting_guess
         double x = starting_guess;
      
      // Set x to x minus the evaluation of x dived by the evaluation of the
      // derivative of this polynomial
         x = x - (this.eval(x)/this.derivative(1).eval(x));      
      
      // If maximum_iterations has been reached throw IllegalStateException
         if (maximum_iterations <= 1)
         {
            throw new IllegalStateException("Maximum Iterations Reached");
         }
         // If Flat failure has occurred throw IllegalStateException
         else if(this.derivative(1).eval(x) < epsilon)
         {
            throw new IllegalStateException("Flat Failure");
         }
         // If the absolute value of the evaluation with x is still greater than
         // epsilon than call this method again
         else if(Math.abs(this.eval(x)) > epsilon)
         {
            x = find_root(x, --maximum_iterations, epsilon);  
         }    
      
      // Return the root
         return x;
      }
   
   /**
   * Accessor method to get the reference to the head of this list
   * @param - none
   * @return 
   *    The reference to the head of this list
   */
      public PolyNode getHead()
      {
         return head;
      }
   
   /**
   * Modification method to set the head of this list
   * @param head 
   *    the new head of this list
   * @postcondition
   *   The head of this list has been set to the parameter head
   */
      public void setHead(PolyNode head)
      {
         this.head = head;
      }
   
   /**
   * Accessor method to get the reference to the last accessed node of this list
   * @param - none
   * @return 
   *    The reference to the last accessed node of this list
   */
      public PolyNode getLastAccessed()
      {
         return lastAccessed;
      }
   
   /**
   * Modification method to set the lastAccessed of this list
   * @param lastAccessed 
   *    the new lastAccessed reference of this list
   * @postcondition
   *   The lastAccessed reference of this list has been set to the 
   *   parameter lastAccessed
   */
      public void setLastAccessed(PolyNode lastAccessed)
      {
         this.lastAccessed = lastAccessed;
      }
   
   /**
   * Accessor method to get the size of this list
   * @param - none
   * @return 
   *    the number of nodes in this list
   */
      public int getSize()
      {
         return size;
      }
   
   /**
   * Accessor method to get the reference to the tail of this list
   * @param - none
   * @return 
   *    the reference to the tail of this list
   */
      public PolyNode getTail()
      {
         return tail;
      }
   
   /**
   * Modification method to set the tail of this list
   * @param tail 
   *    the new tail of this list
   * @postcondition
   *   The tail of this list has been set to the parameter tail
   */
      public void setTail(PolyNode tail)
      {
         this.tail = tail;
      }    
   
   /**
   * Clone this linked list
   * @param - none
   * @return 
   *    A copy of this linked list
   */
      @Override
      public PolyLinkedList clone()
      {
         PolyLinkedList answer;
      
         try
         {
            answer = (PolyLinkedList) super.clone( );
         }
            catch (CloneNotSupportedException e)
            {  // This exception should not occur. But if it does, it would probably
            // indicate a programming error that made super.clone unavailable.
            // The most common error would be forgetting the 
            // "Implements Cloneable" clause at the start of this class.
               throw new RuntimeException
                  ("This class does not implement Cloneable");
            }
      
         answer.head = head.clone( );
         answer.tail = tail.clone( );
         answer.lastAccessed = lastAccessed.clone( );
      
         return answer;
      }        
   
   /**
   * Print all the nodes of this list to the screen
   * @param - none
   */
      public void printList()
      {
         for(PolyNode cursor = head; cursor != null; cursor = cursor.getLink())
         {
            System.out.println(cursor);
         }
      }
   
   }
