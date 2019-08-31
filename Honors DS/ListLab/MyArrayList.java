import java.util.Iterator;
import java.util.ListIterator;

/**
 * MyArrayList acts like an ArrayList and does many of the same
 * functions. It can store objects using an array.
 * 
 * @author Alex Yu
 * @version October 6, 2017
 */
public class MyArrayList<E>
{
    private int size;
    private Object[] values;  //(Java doesn't let us make an array of type E)

    /**
     * Constructs a MyArrayList.
     */
    public MyArrayList()
    {
        size = 0;
        values = new Object[1];
    }

    /**
     * Returns a formatted string of the elements within the list.
     * 
     * @return a formatted string of the elements
     */
    public String toString()
    {
        if (size == 0)
            return "[]";

        String s = "[";
        for (int i = 0; i < size - 1; i++)
            s += values[i] + ", ";
        return s + values[size - 1] + "]";
    }

    /**
     * Doubles the length of the array so that it can hold more objects.
     * 
     * @postcondition: replaces the array with one that is twice as long,
     *                 and copies all of the old elements into it
     */
    private void doubleCapacity()
    {
        Object[] newValues = new Object[ size * 2 ];
        for(int i = 0; i < size; i++)
            newValues[i] = values[i];
            
        values = newValues;
    }

    /**
     * Returns the length of the array.
     * 
     * @return the length of the array
     */
    public int getCapacity()
    {
        return values.length;
    }

    /**
     * Returns the size of MyArrayList.
     * 
     * @return the size of MyArrayList
     */
    public int size()
    {
        return size;
    }

    /**
     * Retrieves the value stored at the given index.
     * 
     * @param index the index from which to retrieve the value
     * @return the value stored at the index of the array
     */
    public E get(int index)
    {
        return (E) values[index];
    }

    /**
     * Sets the value of the element in the given index to the given value and
     * returns the replaced value.
     * 
     * @param index the index of the element to change the value of
     * @param obj   the new value to be stored in the appropriate element
     * @return the old value stored in the appropriate element
     * 
     * @postcondition: replaces the element at position index with obj
     *                 returns the element formerly at the specified position
     */
    public E set(int index, E obj)
    {
        if(index >= size || index < 0)
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");
            
        Object replaced = values[index];
        values[index] = obj;
        return (E) replaced;
    }

    /**
     * Adds the given object to the end of MyArrayList
     * 
     * @param obj the value to add to the end of MyArraylist
     * @return true
     * 
     * @postcondition: appends obj to end of list; returns true
     */
    public boolean add(E obj)
    {
        if(size >= getCapacity())
            doubleCapacity();
        
        values[size] = obj;
        size++;
        
        return true;
    }

    /**
     * Removes and returns the element at the given index.
     * 
     * @param index the index of the element to remove
     * @return the removed element
     * 
     * @postcondition: removes element from position index, moving elements
     *                 at position index + 1 and higher to the left
     *                 (subtracts 1 from their indices) and adjusts size
     *                 returns the element formerly at the specified position
     */
    public E remove(int index)
    {
        if(index >= size || index < 0)
            throw new ArrayIndexOutOfBoundsException("Index out of bounds");

        Object removed = values[index];
        int i;
        for(i = index; i < size - 1; i++)
            values[i] = values[i + 1];
            
        values[i] = null;
        size--;
        return (E) removed;
    }

    /**
     * Retrieves an iterator for this MyArrayList.
     * 
     * @return the iterator for MyArrayList
     */
    public Iterator<E> iterator()
    {
        return new MyArrayListIterator();
    }
    
    /**
     * Retrieves a list iterator for this MyArrayList.
     * 
     * @param index the starting index for the ListIterator
     * @return the ListIterator for MyArrayList
     */
    public Iterator<E> listIterator(int index)
    {
        return new MyArrayListListIterator(index);
    }

    /**
     * Adds the given object to this MyArrayList at the given index.
     * @precondition:  0 <= index <= size
     * 
     * @param index the index at which to add the given object
     * @param obj   the object to add
     * 
     * @postcondition: inserts obj at position index,
     *                 moving elements at position index and higher
     *                 to the right (adds 1 to their indices) and adjusts size
     */
    public void add(int index, E obj)
    {
        if(size >= getCapacity())
            doubleCapacity();
            
        for(int i = size + 1; i > index; i--)
            values[i] = values[i-1];
            
        values[index] = obj;
        size++;
    }

    /**
     * MyArrayListIterator acts as an iterator for MyArrayList by 
     * traversing through and having the ability to carry out several operations.
     * 
     * @author Alex Yu
     * @version October 6, 2017
     */
    private class MyArrayListIterator implements Iterator<E>
    {
        //the index of the value that will be returned by next()
        private int nextIndex;

        /**
         * Constructs a MyArrayListIterator.
         */
        public MyArrayListIterator()
        {
            nextIndex = 0;
        }

        /**
         * Checks if there are any remaining elements in MyArrayList
         * to traverse through.
         * 
         * @return true if there are any remaining elements to traverse; else,
         *         false
         */
        public boolean hasNext()
        {
            return nextIndex < size;
        }

        /**
         * Traverses through MyArrayList and returns the previous element.
         * 
         * @return the previous element
         */
        public E next()
        {
            return (E) values[nextIndex++];
        }

        /**
         * Removes the element last returned by next.
         * 
         * @return the element last returned by next
         * @postcondition: removes the last element that was returned by next
         */
        public void remove()
        {
            MyArrayList.this.remove(nextIndex - 1);
            nextIndex--;
        }
    }
    
    /**
     * MyArrayListListIterator acts as a list iterator for MyArrayList by 
     * traversing through and having the ability to carry out several operations.
     * 
     * @author Alex Yu
     * @version October 6, 2017
     */
    private class MyArrayListListIterator implements Iterator<E>
    {
        //the index of the value that will be returned by next()
        private int nextIndex;
        private int previousIndex;

        /**
         * Constructs a MyArrayListIterator.
         */
        public MyArrayListListIterator(int index)
        {
            nextIndex = index;
            previousIndex = index-1;
        }

        /**
         * Checks if there are any remaining elements in MyArrayList
         * to traverse through forwards.
         * 
         * @return true if there are any remaining elements to traverse; else,
         *         false
         */
        public boolean hasNext()
        {
            return nextIndex < size;
        }
        
        /**
         * Checks if there are any remaining elements in MyArrayList
         * to traverse through backwards.
         * 
         * @return true if there are any remaining elements to traverse; else,
         *         false
         */
        public boolean hasPrevious()
        {
            return previousIndex >= 0;
        }

        /**
         * Traverses through MyArrayList forwards and returns the previous element.
         * 
         * @return the previous element
         */
        public E next()
        {
            previousIndex++;
            return (E) values[nextIndex++];
        }
        
        /**
         * Traverses through MyArrayList backwards and returns the next element.
         * 
         * @return the next element
         */
        public E previous()
        {
            nextIndex--;
            return (E) values[previousIndex--];
        }

        /**
         * Removes the element last returned by next.
         * 
         * @return the element last returned by next
         * @postcondition: removes the last element that was returned by next
         */
        public void remove()
        {
            MyArrayList.this.remove(nextIndex - 1);
            nextIndex--;
        }
        
        /**
         * Sets the element last returned by next to the given object.
         * 
         * @param obj   the new value of the element last returned by next
         */
        public void set(E obj)
        {
            MyArrayList.this.set(nextIndex - 1, obj);
        }
    }
}