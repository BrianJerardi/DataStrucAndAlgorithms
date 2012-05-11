package datastrucandalgorithms.bags;

/**
 * A  Keyed Bag for Data type String
 * @author Brian Jerardi
 * @instructor Kendall Martin
 * @date 9/18/2011
 * @course CIS 112
 * @assignment HW2 Chapter 3 problem 19 page 166-167
 * @credit I reused code and comments from the IntArrayBag class
 *      provided from Michael Main's text
 * @note
 *   (1) The capacity of one of these bags can change after it's created, but
 *   the maximum capacity is limited by the amount of free memory on the 
 *   machine. The constructor, addItem, clone, 
 *   and union will result in an OutOfMemoryError
 *   when free memory is exhausted.
 *   <p>
 *   (2) A bag's capacity cannot exceed the maximum integer 2,147,483,647
 *   (Integer.MAX_VALUE). Any attempt to create a larger capacity
 *   results in a failure due to an arithmetic overflow. 
 *   <p>
 *   (3) Because of the slow linear algorithms of this
 *   class, large bags will have poor performance.
 */
public class StringKeyedBag
{
    // Invariant of the StringKeyedBag class:
    //   1. The number of elements in the bag is in the instance variable 
    //      manyItems, which is no more than data.length.
    //   2. For an empty bag, we do not care what is stored in any of data;
    //      for a non-empty bag, the elements in the bag are stored in data[0]
    //      through data[manyItems-1], and we don't care what's in the
    //      rest of data.
    private int[] keys;
    private String[] data;
    private int manyItems;
   
   /**
    * Initialize an empty bag with an initial capacity of 10.  Note that the
    * addItem method works efficiently (without needing more
    * memory) until this capacity is reached.
    * @param - none
    * @postcondition
    *   This bag is empty and has an initial capacity of 10.
    * @exception OutOfMemoryError
    *   Indicates insufficient memory for: 
    *   new String[10].
    */
    public StringKeyedBag()
    {
        final int INITIAL_CAPACITY = 10;
        manyItems = 0;
        data = new String[INITIAL_CAPACITY];  
        keys = new int[INITIAL_CAPACITY];
    }
    
    /**
     * Initialize an empty bag with a specified initial capacity. Note that the
     * addItem method works efficiently (without needing more
     * memory) until this capacity is reached.
     * @param initialCapacity
     *   the initial capacity of this bag
     * @precondition
     *   initialCapacity is non-negative.
     * @postcondition
     *   This bag is empty and has the given initial capacity.
     * @exception IllegalArgumentException
     *   Indicates that initialCapacity is negative.
     * @exception OutOfMemoryError
     *   Indicates insufficient memory for: new String[initialCapacity].
     */
    public StringKeyedBag(int initialCapacity)
    {
        if (initialCapacity < 0)
        {
            throw new IllegalArgumentException
                    ("The initialCapacity is negative: " + initialCapacity);            
        }
        
        data = new String[initialCapacity];
        keys = new int[initialCapacity];
        manyItems = 0;        
    }
    
    /**
     * Add a new element and key to this bag. If the new element would take this
     * bag beyond its current capacity, then the capacity is increased
     * before adding the new element.
     * @param entry
     *   the new element that is being inserted
     * @param key 
     *   the key that corresponds to the inserted entry
     * @precondition
     *   bag does not yet contain any item with the given key
     * @postcondition
     *   A new copy of the element has been added to this bag with 
     *   corresponding key.
     * @exception OutOfMemoryError
     *   Indicates insufficient memory for increasing the bag's capacity.
     * @note
     *   An attempt to increase the capacity beyond
     *   Integer.MAX_VALUE will cause the bag to fail with an
     *   arithmetic overflow.
     * @exception IllegalArgumentException
     *   When the key chosen already exists.
     */
    public void insert(String entry, int key)
    {
        if (keyExists(key))
        {
            throw new IllegalArgumentException
                    ("Key " + key + " already exists.");
        }
        
        if (manyItems == data.length)
        {  // Ensure twice as much space as we need.
            ensureCapacity((manyItems + 1)*2);
        }

        data[manyItems] = entry;
        keys[manyItems] = key;
        manyItems++;        
    }
    
    /**
     * Remove one copy of a specified element from this bag.
     * @param key
     *   the key of the element to remove from the bag
     * @postcondition
     *   If key was found in the bag, then one copy of the keys 
     *   target has been removed and the method returns true. 
     *   Otherwise the bag remains unchanged and the method returns false. 
     * @return 
     *   True: one copy of the keys target has been removed
     *   False: the bag remains unchanged
     */
    public boolean remove(int key)
    {
        int index = findKey(key); // The location of target in the data array.    
         
        if (index == manyItems)
            // The target was not found, so nothing is removed.
            return false;
        else
        {   // The target was found at keys[index].
            // So reduce manyItems by 1 and copy the last element onto data[index].
            manyItems--;
            data[index] = data[manyItems];
            keys[index] = keys[manyItems];
            return true;
        }        
    }
    
    
    /**
     * Find the data and the specified key.
     * @param key
     *   the key of the element to find in the bag
     * @return 
     *   the data the corresponds to the key
     * @exception IllegalArgumentException
     *   When the key chosen does not exist.
     */
    public String retrieve(int key)
    {         
        int index = findKey(key); // The location of target in the data array.
        
        if (index == manyItems)
        {
            // The target was not found, so an exception is thrown
            throw new IllegalArgumentException
                    ("Key " + key + " does not exist.");            
        }            
        else
        {   // The target was found at keys[index].
            // So return the data and index
            return data[index];
        }        
    }
    
    /**
     * Find the index of the key parameter
     * @param key
     *   the key to whose index to find
     * @return 
     *   the index of key or 
     *   if key is not in the array, then index will be set equal to manyItems
     */
    private int findKey(int key)
    {
        int index; // The location of target in the data array.
        
        // First, set index to the location of target in the data array,
        // which could be as small as 0 or as large as manyItems-1; If target
        // is not in the array, then index will be set equal to manyItems;
        for (index = 0; (index < manyItems) && (key != keys[index]); index++)
            // No work is needed in the body of this for-loop.
            ;
        
        return index;        
    }

    
    /**
     * Change the current capacity of this bag.
     * @param minimumCapacity
     *   the new capacity for this bag
     * @postcondition
     *   This bag's capacity has been changed to at least minimumCapacity.
     *   If the capacity was already at or greater than minimumCapacity,
     *   then the capacity is left unchanged.
     * @exception OutOfMemoryError
     *   Indicates insufficient memory for: new int[minimumCapacity].
     */
    private void ensureCapacity(int minimumCapacity)
    {
        String[] biggerDataArray;
        int[] biggerKeyArray;
      
        if (data.length < minimumCapacity)
        {
            biggerDataArray = new String[minimumCapacity];
            biggerKeyArray = new int[minimumCapacity];
            System.arraycopy(data, 0, biggerDataArray, 0, manyItems);
            System.arraycopy(keys, 0, biggerKeyArray, 0, manyItems);
            
            data = biggerDataArray;
            keys = biggerKeyArray;
        }
    }
    
    /**
     * Check to see if key exists in the keys array
     * @param key
     *   The key to check against the existing keys.
     * @return 
     *   True: if the key exists in keys.
     *   False: if the key is not in keys.
     */
    public boolean keyExists(int key)
    {
        // Loop through keys array
        for(int item : keys)
        {
            // If key is found in array return true
            if (item == key)
            {
                return true;
            }
        }
     
        // If key was not found return false
        return false;
    }
    
    /**
     * Accessor method to get the current capacity of this bag. 
     * The add method works efficiently (without needing
     * more memory) until this capacity is reached.
     * @param - none
     * @return
     *   the current capacity of this bag
     */
    public int getCapacity( )
    {
        return data.length;
    }
    
    /**
     * Determine the number of elements in this bag.
     * @param - none
     * @return
     *   the number of elements in this bag
     */
    public int size()
    {
        return manyItems;        
    }
    
    /**
     * Reduce the current capacity of this bag to its actual size (i.e., the
     * number of elements it contains).
     * @param - none
     * @postcondition
     *   This bag's capacity has been changed to its current size.
     * @exception OutOfMemoryError
     *   Indicates insufficient memory for altering the capacity. 
     */
    public void trimToSize()
    {
        String[] trimmedDataArray;
        int[] trimmedKeyArray;
      
        if (data.length != manyItems)
        {
            trimmedDataArray = new String[manyItems];
            trimmedKeyArray = new int[manyItems];
            
            System.arraycopy(data, 0, trimmedDataArray, 0, manyItems);
            System.arraycopy(keys, 0, trimmedKeyArray, 0, manyItems);
            
            
            data = trimmedDataArray;
            keys = trimmedKeyArray;
        }
    }
    
    /**
     * Converts the bag to a String
     * @param - none
     * @return 
     *      A string version of this bag
     */
    @Override
    public String toString()
    {
        String resultString = "Name \t\t Key\n";
        
        
        for (int i = 0; i < this.size(); i++)
        {
            resultString += data[i] + "\t" + keys[i] + "\n";            
        }
        
        return resultString;
    }
    
    public void print()
    {
        System.out.printf("%20s%20s\n","Name","Key");
        
        for (int i = 0; i < this.size(); i++)
        {
            System.out.printf("%20s%20s\n",data[i],keys[i]);            
        }
        
    }
    

}
