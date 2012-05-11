package datastrucandalgorithms.trees;

import edu.colorado.nodes.IntBTNode;
import java.util.ArrayList;

/**
 * A Complete Binary Tree Bag using an IntBTNode
 * @author Brian Jerardi
 * @instructor Kendall Martin
 * @date 11/02/2011
 * @course CIS 112
 * @assignment HW2 Chapter 9 problem 2 page 507 rewrite
 */
public class CBTBag2 implements Cloneable
{
    /*
     * Invariant of the CBTBag2 Class
     *  1. The elements in the bar a stored in a binary tree
     *  2. The instance variable root is a reference to the root of the tree
     *      (or null for an empty tree)
     */
    IntBTNode root;
    
    /**
     * Initialize an empty bag
     * @param - none
     * @postcondition
     *  The bag is empty
     */
    public CBTBag2()
    {
        root = null;
    }
    
    /**
     * Add a new element to this bag
     * @param element 
     *  the new element that is being added
     * @postcondition
     *  A new copy of the element has been added to this bag
     * @throws OutOfMemoryError
     *  Indicates insufficient memory for adding a new element
     */
    public void add(int element)
    {
        IntBTNode add = new IntBTNode(element, null, null);;

        if (isEmpty())
        {
            root = add;
        }
        else
        {
            setFirstNull(add);
        }
    }
    
    /**
     * Sets the first null node to newNode
     * @param newNode 
     *  The new node that will be put into the first null node
     * @precondition
     *  The tree is not empty
     * @postcondition
     *  newNode will be added to the first null node in the tree
     * @credit
     *  Idea for this method was provided by Nathan Aston 
     */
    private void setFirstNull(IntBTNode newNode)
    {
        // A temporary ArrayList for storing nodes of this tree
        ArrayList<IntBTNode> tempTree = new ArrayList<IntBTNode>();
        // A temporary node used for checking
        IntBTNode tempNode;
        
        // Add root to tempTree
        tempTree.add(root);  
        
        // While tempTree is not empty search for null node
        while (!tempTree.isEmpty())
        {
            // Remove the first node in tempTree and put into tempNode
            tempNode = tempTree.remove(0);
            
            // If tempNode's left child is null set it's left child to newNode
            // And clear the tree because we found the first null node
            if (tempNode.getLeft() == null)
            {
                tempNode.setLeft(newNode); 
                tempTree.clear();
            }
            // If tempNode's right child is null set it's right child to newNode
            // And clear the tree because we found the first null node
            else if(tempNode.getRight() == null)
            {
                tempNode.setRight(newNode); 
                tempTree.clear();
            }
            // Otherwise add both the left and right children of 
            // tempNode to tempTree
            else
            {
                tempTree.add(tempNode.getLeft());
                tempTree.add(tempNode.getRight());
            }
                  
        }
    }
    
    /**
     * Add a variable number of new elements to this bag
     * @param elements 
     *  a variable number of new elements that are all being added
     * @postcondition
     *  New copies of all the elements have been added to this bag
     */
    public void addMany(int... elements)
    {
        for (int num : elements)
        {
            add(num);              
        }
    }
    
    /**
     * Add the contents of another bag in pre order to this bag
     * @param addend 
     *  a bag whose contents will be added to this bag
     * @precondition
     *  the parameter, addend, is not null
     * @postcondition
     *  The elements from addend in pre order have been added to this bag
     * @throws IllegalArgumentException
     *  Indicates that addend is null
     */
    public void addAll(CBTBag2 addend)
    {
        if (addend == null)
        {
            throw new IllegalArgumentException("Parameter can not be null");
        }
        
        preOrderAdd(addend.root);               
    }
    
    /**
     * Adds all the nodes in a Tree Bag in pre order to this Tree Bag
     * @param cursorNode 
     *  The root of the Tree Bag to add
     * @precondition
     *  cursorNode is not null
     * @postcondition
     *  All nodes in the Tree Bag with root cursorNode 
     *  have been added to this Bag in pre order
     */
    private void preOrderAdd(IntBTNode cursorNode)
    {
        // Add cursorNode data to this bag
        add(cursorNode.getData());
        
        // If the left child is not null, call this method on the left child
        if (cursorNode.getLeft() != null)
        {
            preOrderAdd(cursorNode.getLeft());
        }
        
        // If the right child is not null, call this method on the right child
        if (cursorNode.getRight() != null)
        {
            preOrderAdd(cursorNode.getRight());
        }        
    }
    
    
    /**
     * Create a new bag that contains all the elements from two other bags
     * @param b1
     *  The first of two bags
     * @param b2
     *  The second of two bags
     * @precondition
     *  Neither b1 nor b2 is null
     * @return
     *  a new bag that is the union of b1 and b2
     * @throws IllegalArgumentException
     *  Indicates that one of the arguments is null
     */    
    public static CBTBag2 union(CBTBag2 b1, CBTBag2 b2)
    {
        if(b1 == null || b2 == null)
        {
            throw new IllegalArgumentException("Arguments can not be null");
        }
        
        CBTBag2 answerBag = b1.clone();
        
        answerBag.addAll(b2);
        
        return answerBag;
    }
    
    /**
     * Gets the leftmost data from the tree
     * @param - none
     * @precondition
     *  The tree is not empty
     * @postcondition 
     *  the leftmost data has been returned
     * @return 
     *  The leftmost data from the tree
     * @exception IllegalStateException
     *  The tree is empty
     */
    public int getLeftmostData()
    {      
        if (isEmpty())
        {
            throw new IllegalStateException("Bag can not be empty");
        }
        
        return root.getLeftmostData();        
    }
    
    /**
     * Gets the rightmost data from the tree
     * @param - none
     * @precondition
     *  The tree is not empty
     * @postcondition 
     *  the rightmost data has been returned
     * @return 
     *  The rightmost data from the tree
     * @exception IllegalStateException
     *  The tree is empty
     */
    public int getRightmostData()
    {      
        if (isEmpty())
        {
            throw new IllegalStateException("Bag can not be empty");
        }
        
        return root.getRightmostData();        
    }    
    
    /**
     * Removes the leftmost node of this tree
     * @param - none
     * @precondition
     *  The bag is not empty
     * @postcondition
     *  The leftmost node has been removed
     * @exception IllegalStateException
     *  The bag is empty
     */
    public void removeLeftmost()
    {
        if (isEmpty())
        {
            throw new IllegalStateException("Bag can not be empty");
        }
        
        root.removeLeftmost();
    }
    
    /**
     * Removes the rightmost node of this tree
     * @param - none
     * @precondition
     *  The bag is not empty
     * @postcondition
     *  The rightmost node has been removed
     * @exception IllegalStateException
     *  The bag is empty
     */
    public void removeRightmost()
    {
        if (isEmpty())
        {
            throw new IllegalStateException("Bag can not be empty");
        }
        
        root.removeRightmost();
    }
    
    /**
     * Counts the number of occurrences of target in the tree
     * @param target
     *  The target to count
     * @return 
     *  The number of times target was found
     */
    public int countOccurrences(int target)
    {
        return preOrderCount(root, target);      
    }
    
    /**
     * Counts each occurrence of target
     * @param cursorNode
     *  The root of the tree to start counting from
     * @param target
     *  The target to count
     * @return 
     *  The number of times target occurred in the tree
     */
    private int preOrderCount(IntBTNode cursorNode, int target)
    {
        int count = 0;
        
        // If the target is found increment count
        if (cursorNode.getData() == target)
        {
            count++;            
        }
        
        // If the left child is not null, call this method recursively
        // And add the result to count
        if (cursorNode.getLeft() != null)
        {
            count += preOrderCount(cursorNode.getLeft(), target);
        }
        
        // If the right child is not null, call this method recursively
        // And add the result to count
        if (cursorNode.getRight() != null)
        {
            count += preOrderCount(cursorNode.getRight(), target);
        }
        
        return count;        
    }
    
    
    /**
     * Prints the tree elements in order
     * @precondition
     *  The tree is not empty
     * @postcondition
     *  All elements in the tree have been printed in order
     * @exception IllegalStateException
     *  The bag is empty
     */
    public void inorderPrint()
    {
        if (isEmpty())
        {
            throw new IllegalStateException("Bag can not be empty");
        }
        
        root.inorderPrint();
    }
    
    /**
     * Prints the tree elements using post order
     * @precondition
     *  The tree is not empty
     * @postcondition
     *  All elements in the tree have been printed using post order
     * @exception IllegalStateException
     *  The bag is empty
     */
    public void postorderPrint()
    {
        if (isEmpty())
        {
            throw new IllegalStateException("Bag can not be empty");
        }
        
        root.postorderPrint();
    }
    
    /**
     * Prints the tree elements using pre order
     * @precondition
     *  The tree is not empty
     * @postcondition
     *  All elements in the tree have been printed using pre order
     * @exception IllegalStateException
     *  The bag is empty
     */
    public void preorderPrint()
    {
        if (isEmpty())
        {
            throw new IllegalStateException("Bag can not be empty");
        }
        
        root.preorderPrint();
    }  
    
    /**
     * Prints the tree elements
     * @precondition
     *  The tree is not empty
     * @postcondition
     *  All elements in the tree have been printed using pre order
     * @exception IllegalStateException
     *  The bag is empty
     */
    public void print()
    {
        if (isEmpty())
        {
            throw new IllegalStateException("Bag can not be empty");
        }
        
        root.print(0);
    }   
    
    /**
     * Checks if this tree is empty
     * @param - none
     * @return 
     *  True if the tree is empty
     *  False if the tree is NOT empty
     */
    public boolean isEmpty()
    {
        if (root == null)
        {
            return true;
        }
        
        return false;
    }
    
    /**
     * Accessor method to determine the number of elements in this bag
     * @return 
     *  the number of elements in this bag
     */
    public int size()
    {
        return IntBTNode.treeSize(root);
    }    
    
    /**
     * Clone this bag
     * @param - none
     * @return 
     *  a copy of this bag
     * @throws OutOfMemoryError
     *  Indicates insufficient memory for increasing the capacity 
     */
    @Override
    public CBTBag2 clone()
    {
        CBTBag2 answer;
        
        try
         {
            answer = (CBTBag2) super.clone( );
         }
            catch (CloneNotSupportedException e)
            {  // This exception should not occur. But if it does, it would probably
            // indicate a programming error that made super.clone unavailable.
            // The most common error would be forgetting the 
            // "Implements Cloneable" clause at the start of this class.
               throw new RuntimeException
                  ("This class does not implement Cloneable");
            }
      
         answer.root = IntBTNode.treeCopy(root);
      
         return answer;
    }
}
