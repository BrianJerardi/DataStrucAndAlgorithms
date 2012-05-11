package datastrucandalgorithms.trees;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A Complete Binary Tree Bag using an ArrayList
 * @author Brian Jerardi
 * @instructor Kendall Martin
 * @date 11/01/2011
 * @course CIS 112
 * @assignment HW2 Chapter 9 problem 2 page 507
 */
public class CBTBag<E> implements Cloneable
{
    /*
     * Invariant of the CTBag Class
     *  1. The elements in the bag are stored in a complete binary tree
     *  2. The instance variable cTree contains all the elements in the tree
     *  3. The size of cTree is the size of the Tree
     *  4. If cTree size equals 0 the tree is empty
     *  5. All elements added to the tree are added after the last element
     *  6. Any element removed from the tree is the last element added
     */
    private ArrayList<E> cTree;
    
    /**
     * Creates a new CBTBag of type E
     */
    public CBTBag()
    {
        cTree = new ArrayList<E>();
    }
    
    /**
     * Adds the data to the last spot in the Complete Binary Tree
     * @param data 
     *  The data to add
     * @postcondtion
     *  data has been added to the last position in this complete tree
     * @throws OutOfMemoryError
     *  Indicates insufficient memory for increasing the capacity
     * @note
     *  Creating a bag with capacity beyond 
     *  Integer.MAX_VALUE causes arithmetic overflow
     */
    public void add(E data)
    {
        cTree.add(data);
    }
    
    /**
     * Add a variable number of new elements to this bag
     * @param elements 
     *  a variable number of new elements that are all being added
     * @postcondition
     *  New copies of all the elements have been added to this bag
     * @throws OutOfMemoryError
     *  Indicates insufficient memory for increasing the capacity
     * @note
     *  Creating a bag with capacity beyond 
     *  Integer.MAX_VALUE causes arithmetic overflow
     */
    public void addMany(E... elements)
    {
        ArrayList<E> elementsList = new ArrayList(Arrays.asList(elements));
        cTree.addAll(elementsList);
    }
    
    /**
     * Add the contents of another bag to this bag
     * @param addend 
     *  a bag whose contents will be added to this bag
     * @preconditon
     *  the parameter, addend, is not null
     * @postcondition
     *  The elements from addend have been added to this bag
     * @throws IllegalArgumentException
     *  Indicates that addend is null
     * @throws OutOfMemoryError
     *  Indicates insufficient memory for increasing the capacity
     */
    public void addAll(CBTBag<E> addend)
    {
        if (addend == null)
        {
            throw new IllegalArgumentException("Parameter can not be null");
        }
        
        cTree.addAll(addend.cTree);        
    }
    
    /**
     * Create a new bag that contains all the elements from two other bags
     * @param b1
     *  The first of two bags
     * @param b2
     *  The second of two bags
     * @precondtion
     *  Neither b1 nor b2 is null
     * @returns
     *  a new bag that is the union of b1 and b2
     * @throws IllegalArgumentException
     *  Indicates that one of the arguments is null
     * @throws OutOfMemoryError
     *  Indicates insufficient memory for increasing the capacity
     */    
    public static CBTBag union(CBTBag b1, CBTBag b2)
    {
        if(b1 == null || b2 == null)
        {
            throw new IllegalArgumentException("Arguments can not be null");
        }
        
        CBTBag answerBag = b1.clone();
        
        answerBag.addAll(b2);
        
        return answerBag;
    }
    
    /**
     * Counts the number of occurrences of target in the tree
     * @param target
     *  The target to count
     * @return 
     *  The number of times target was found
     */
    public int countOccurrences(E target)
    {
        int count = 0;
        
        for (E e : cTree)
        {
            if (e.equals(target))
            {
                count++;
            }
        }
        
        return count;        
    }
    
    /**
     * Removes the last items from the tree
     * @param - none
     * @postcondition
     *  The last items in the tree has been removed
     *  If there was no last items, nothing happens
     * @return
     *  true if the last items removed
     *  false if there was no items to remove
     */
    public boolean remove()
    {
        if(cTree.size() > 0)
        {
            cTree.remove(cTree.size() - 1);
            return true;
        }        
        
        return false;
    }
   
    
    /**
     * Gets the number of items in the tree
     * @return 
     *  The number of items in the tree
     */
    public int size()
    {
        return cTree.size();
    }
    
    /**
     * Clone this bag
     * @param - none
     * @returns 
     *  a copy of this bag
     * @throws OutOfMemoryError
     *  Indicates insufficient memory for increasing the capacity 
     */
    @Override
    public CBTBag<E> clone()
    {
        CBTBag<E> answer;
        
        try
         {
            answer = (CBTBag<E>) super.clone( );
         }
            catch (CloneNotSupportedException e)
            {  // This exception should not occur. But if it does, it would probably
            // indicate a programming error that made super.clone unavailable.
            // The most common error would be forgetting the 
            // "Implements Cloneable" clause at the start of this class.
               throw new RuntimeException
                  ("This class does not implement Cloneable");
            }
      
         answer.cTree = (ArrayList<E>)cTree.clone();
      
         return answer;
    }
    
    /**
     * Gets the data stored at the given index
     * @param index
     *  The index of the data to retrieve
     * @return 
     *  The data at index
     *  null if there is no item at index
     */
    public E getDataAt(int index)
    {
        if(!exists(index))
        {
            return null;
        }
        else
        {
            return cTree.get(index);
        }
    }
    
    /**
     * Gets the left child of the given index
     * @param index
     *  The index whose left child to get
     * @precondition
     *  there is an item at index
     * @postcondition
     *  The left child of the item at index has been returned
     *  or null if there is no left child
     * @return 
     *  The left child of the item at index
     *  null if there is no left child
     * @exception IllegalArgumentException
     *  There is no item at index
     */
    public E getLeftChild(int index)
    {
        if (!exists(index))
        {
            throw new IllegalArgumentException
                    ("No node exists at index " + index);
        }
        
        int lChildIndex = 2 * index + 1;
        return (lChildIndex >= cTree.size()) ? null : cTree.get(lChildIndex);
    }
    
    /**
     * Gets the right child of the given index
     * @param index
     *  The index whose right child to get
     * @precondition
     *  there is an item at index
     * @postcondition
     *  The right child of the item at index has been returned
     *  or null if there is no right child
     * @return 
     *  The right child of the item at index
     *  null if there is no right child
     * @exception IllegalArgumentException
     *  There is no item at index
     */
    public E getRightChild(int index)
    {
        if (!exists(index))
        {
            throw new IllegalArgumentException
                    ("No node exists at index " + index);
        }
        
        int rChildIndex = 2 * index + 2;
        return (rChildIndex >= cTree.size()) ? null : cTree.get(rChildIndex);
    }
    
    /**
     * Gets the leftmost data from the root
     * @return 
     *  The leftmost data from the root
     *  null if the tree is empty
     *  
     */
    public E getLeftmostData()
    {
        E leftmost = getLeftmostData(0);       
        
        return leftmost;        
    }
    
    /**
     * Gets the leftmost data from the starting index
     * @param index
     *  The index to use as the root of the search
     * @return 
     *  The leftmost data from index
     *  null if there is no data at index
     */
    public E getLeftmostData(int index)
    {
        if(!exists(index))
        {
            return null;
        }
        
        E leftmost = cTree.get(index);        
        
        if ((2 * index + 1) < cTree.size())
        {
            leftmost = getLeftmostData(2 * index + 1);
        }
        
        return leftmost;
    }
    
    /**
     * Gets the rightmost data from the root
     * @return 
     *  The rightmost data from the root
     *  null if the tree is empty
     */
    public E getRightmostData()
    {
        E rightmost = getRightmostData(0);       
        
        return rightmost;        
    }
    
    /**
     * Gets the rightmost data from the starting index
     * @param index
     *  The index to use as the root of the search
     * @return 
     *  The rightmost data from index
     *  null if there is no data at index
     */
    public E getRightmostData(int index)
    {
        if(!exists(index))
        {
            return null;
        }
        
        E rightmost = cTree.get(index);
        
        if ((2 * index + 2) < cTree.size())
        {
            rightmost = getLeftmostData(2 * index + 2);
        }
        
        return rightmost;
    }
    
    /**
     * Check if the item at the given index is a leaf (has no children)
     * @param index
     *  The index of the item to check
     * @precondition
     *  There is an item at index
     * @return 
     *  True if the item at index is a leaf
     *  False if the item at index is NOT a leaf
     * @exception IllegalArgumentException
     *  There is no data at index
     */
    public boolean isLeaf(int index)
    {
        if (!exists(index))
        {
            throw new IllegalArgumentException
                    ("No node exists at index " + index);
        }
        
        return (getLeftChild(index) == null) && (getRightChild(index) == null);
    }
    
    /**
     * Checks if the tree is empty
     * @return 
     *  True if the tree is empty
     *  False if the tree has data
     */
    public boolean isEmpty()
    {
        return size() < 1;
    }
    
    /**
     * Test if there is data at the given index
     * @param index
     *  The index to checks for data
     * @return 
     *  True if there is data at index
     *  False if there is NO data at index
     */
    public boolean exists(int index)
    {
        return index < size();
    }
    
    /**
     * Prints the children of the node at the given index
     * @param index 
     *  The index of the node whose children to print
     * @precondition
     *  There is an item at index
     * @postcondition
     *  The children of the node at index have been printed
     *  If a child doesn't exits null will be printed
     * @exception IllegalArgumentException
     *  There is no data at index
     */
    public void printChildren(int index)
    {
        if (!exists(index))
        {
            throw new IllegalArgumentException
                    ("No node exists at index " + index);
        }
                
        System.out.println("Left Child: " + getLeftChild(index));
        System.out.println("Right Child: " + getRightChild(index));
    }
    
    /**
     * Prints the tree in order from root
     * @param - none
     * @precondition
     *  The tree is not empty
     * @postcondition
     *  All elements in the tree have been printed in order
     * @exception IllegalStateException
     *  The tree is empty
     */
    public void inorderPrint()
    {
        if (isEmpty())
        {
            throw new IllegalStateException
                    ("The tree is empty ");
        }
        
        inorderPrint(0);
    }
    
    /**
     * Prints the tree in order starting from index
     * @param index 
     *  The root to start printing from
     * @precondition
     *  There is data at index
     * @postcondition
     *  All elements in the tree from index have been printed in order
     * @exception IllegalArgumentException
     *  There is no data at index
     */
    public void inorderPrint(int index)
    {
        if (!exists(index))
        {
            throw new IllegalArgumentException
                    ("No node exists at index " + index);
        }
        
        if(2 * index + 1 < cTree.size())
        {
            inorderPrint(2 * index + 1);
        }
        System.out.println(cTree.get(index));
        if(2 * index + 2 < cTree.size())
        {
            inorderPrint(2 * index + 2);
        }
    }
    
    /**
     * Prints the tree with post order from root
     * @param - none
     * @precondition
     *  The tree is not empty
     * @postcondition
     *  All elements in the tree have been printed in post order
     * @exception IllegalStateException
     *  The tree is empty
     */
    public void postorderPrint()
    {
        if (isEmpty())
        {
            throw new IllegalStateException
                    ("The tree is empty ");
        }
        
        postorderPrint(0);
    }
    
    /**
     * Prints the tree in post order starting from index
     * @param index 
     *  The root to start printing from
     * @precondition
     *  There is data at index
     * @postcondition
     *  All elements in the tree from index have been printed in post order
     * @exception IllegalArgumentException
     *  There is no data at index
     */
    public void postorderPrint(int index)
    {
        if (!exists(index))
        {
            throw new IllegalArgumentException
                    ("No node exists at index " + index);
        }
        
        if(2 * index + 1 < cTree.size())
        {
            postorderPrint(2 * index + 1);
        }        
        if(2 * index + 2 < cTree.size())
        {
            postorderPrint(2 * index + 2);
        }
        System.out.println(cTree.get(index));
    }
    
    /**
     * Prints the tree in pre order from root
     * @param - none
     * @precondition
     *  The tree is not empty 
     * @postcondition
     *  All elements in the tree have been printed in pre order
     * @exception IllegalStateException
     *  The tree is empty
     */
    public void preorderPrint()
    {
        if (isEmpty())
        {
            throw new IllegalStateException
                    ("The tree is empty ");
        }
        
        preorderPrint(0);
    }
    
    /**
     * Prints the tree in pre order starting from index
     * @param index 
     *  The root to start printing from
     * @precondition
     *  There is data at index
     * @postcondition
     *  All elements in the tree from index have been printed in pre order
     * @exception IllegalArgumentException
     *  There is no data at index
     */
    public void preorderPrint(int index)
    {
        if (!exists(index))
        {
            throw new IllegalArgumentException
                    ("No node exists at index " + index);
        }
        
        System.out.println(cTree.get(index));
        if(2 * index + 1 < cTree.size())
        {
            preorderPrint(2 * index + 1);
        }        
        if(2 * index + 2 < cTree.size())
        {
            preorderPrint(2 * index + 2);
        }        
    }
    
    /**
     * Prints the tree in tree form (adjusting for different sized trees)
     * @parm - none
     * @precondition
     *  The tree is not empty
     * @postconition
     *  The tree has been printed in tree form
     * @exception IllegalStateException
     *  The tree is empty
     */
    public void printTree()
    {
        if (isEmpty())
        {
            throw new IllegalStateException
                    ("The tree is empty ");
        }
        
        // Make a copy of the cTree ArrayList to be able to remove from it
        ArrayList<E> pritingTree = (ArrayList<E>)cTree.clone();
        
        // Start the depth count at 1 and the item count at 0
        int depthCount = 1;
        int itemCount = 0;
        
        // Set the offset to 2 times the trees size
        int offset = pritingTree.size() * 2;
        
        // Set the spacing to 4 times the trees size
        int spacing = pritingTree.size() * 4;
        
        // Set the Strings formatStrOffset and formatStrSpacing for use
        // with printf
        String formatStrOffset = "%"+offset+"s"; 
        String formatStrSpacing = "%"+spacing+"s";
        
        // Set the constants for the line Strings
        final String LINE_LEFT = "/";
        final String LINE_RIGHT = "\\";
        
        
        // Print first number in tree and increment itemCount and depthCount
        System.out.printf(formatStrOffset,pritingTree.remove(0));
        itemCount++;    
        depthCount++;

        // Print numbers in tree depth by depth
        while (!pritingTree.isEmpty())
        {                        
            // If this item is the start of a new depth
            // Halve offset and spacing
            // And print the lines, and the item with offset
            if (itemCount + 1 == Math.pow(2, depthCount - 1 ))
            {
                offset /= 2;
                spacing /= 2;
                formatStrOffset = "%"+offset+"s"; 
                formatStrSpacing = "%"+spacing+"s";
                
                System.out.println("");
                // For each item in the next depth print a tree line
                for (int i = 0; i <= itemCount  && i < pritingTree.size(); i++)
                {
                    // If it is the first line 
                    // print LINE_LEFT at formatStrOffset
                    if (i == 0)
                    {
                        System.out.printf(formatStrOffset,LINE_LEFT);
                    }
                    // If it is an even number line 
                    // print LINE_LEFT with formatStrSpacing
                    else if(i % 2 == 0)
                    {
                        System.out.printf(formatStrSpacing,LINE_LEFT);
                    }
                    // Otherwise print LINE_RIGHT with formatStrSpacing
                    else
                    {
                        System.out.printf(formatStrSpacing,LINE_RIGHT);
                    }                    
                }
                
                System.out.println("");           
                // Print the first item of the new depth at formatStrOffset
                System.out.printf(formatStrOffset,pritingTree.remove(0));
                
                // Increment itemCount and depthCount
                itemCount++;
                depthCount++;
            }
            // If it is not the first item of a new depth print the item
            // with formatStrSpacing and increment itemCount
            else
            {
                System.out.printf(formatStrSpacing,pritingTree.remove(0)); 
                itemCount++;                
            }
        }
        
        System.out.println("");
   
    }

}
