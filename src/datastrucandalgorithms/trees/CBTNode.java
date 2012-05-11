package datastrucandalgorithms.trees;

/**
 * A Node for a complete Binary Tree
 * @author Brian Jerardi
 * @instructor Kendall Martin
 * @date 11/01/2011
 * @course CIS 112
 * @assignment HW2 Chapter 9 problem 2 page 507
 */
public class CBTNode<E>
{
    private E data;
    private CBTNode<E> left;
    private CBTNode<E> right;

    public CBTNode(E intialData, CBTNode<E> intialLeft, CBTNode<E> intialRight)
    {
        data = intialData;
        left = intialLeft;
        right = intialRight;
    }
    
    public E getData()
    {
        return data;
    }
    
    public CBTNode<E> getLeft()
    {
        return left;
    }
    
    public CBTNode<E> getRight()
    {
        return right;
    }
    
    public void setData(E newData)
    {
        data = newData;
    }
    
    public void setLeft(CBTNode<E> newLeft)
    {
        left = newLeft;
    }
    
    public void setRight(CBTNode<E> newRight)
    {
        right = newRight;
    }
    
    public boolean isLeaf()
    {
        return (left == null) && (right == null);
    }
    
    public E getLeftmostData()
    {
        if(left == null)
        {
            return data;
        }
        else
        {
            return left.getLeftmostData();
        }
    }
    
    public E getRightmostData()
    {
        if(right == null)
        {
            return data;
        }
        else
        {
            return right.getRightmostData();
        }
    }
    
    public CBTNode<E> removeLeftmost()
    {
        if(left == null)
        {
            return right;
        }
        else
        {
            left = left.removeLeftmost();
            return this;
        }
    }
    
    public CBTNode<E> removeRightmost()
    {
        if(right == null)
        {
            return left;
        }
        else
        {
            right = right.removeRightmost();
            return this;
        }
    }
}
