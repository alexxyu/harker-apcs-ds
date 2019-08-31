import java.util.Iterator;
import java.util.ListIterator;

/**
 * MyLinkedList acts similar to a LinkedList and offers many of
 * the same operations, including add and remove. It uses several 
 * helper methods to aid in such operations.
 * 
 * @author Alex Yu
 * @version October 9, 2017
 */
public class MyLinkedList<E>
{
    private DoubleNode first;
    private DoubleNode last;
    private int size;

    /**
     * Constructs a MyLinkedList.
     */
    public MyLinkedList()
    {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Returns a formatted string of values stored in this MyLinkedList.
     * 
     * @return a formatted string of values in MyLinkedList
     */
    public String toString()
    {
        DoubleNode node = first;
        if (node == null)
            return "[]";
        String s = "[";
        while (node.getNext() != null)
        {
            s += node.getValue() + ", ";
            node = node.getNext();
        }
        return s + node.getValue() + "]";
    }

    /**
     * Returns the DoubleNode at the given index starting from
     * the front end of the list (i.e. from the first node).
     * 
     * @param index the index of the DoubleNode to return
     * 
     * @precondition:  0 <= index <= size / 2
     * @postcondition: starting from first, returns the node
     *                 with given index (where index 0
     *                 returns first)
     *                
     * @return the corresponding DoubleNode
    **/
    private DoubleNode getNodeFromFirst(int index)
    {
        DoubleNode node = first;
        while(index != 0)
        {
            node = node.getNext();
            index--;
        }
        
        return node;
    }

    /**
     * Returns the DoubleNode at the given index starting from
     * the back end of the list (i.e. from the last node).
     * 
     * @param index the index of the DoubleNode to return
     * 
     * @precondition:  size / 2 <= index < size
     * @postcondition: starting from last, returns the node
     *                 with given index (where index size-1
     *                 returns last)
     *                
     * @return the corresponding DoubleNode
    **/
    private DoubleNode getNodeFromLast(int index)
    {
        DoubleNode node = last;
        int countingIndex = size - index - 1;
        while(countingIndex != 0)
        {
            node = node.getPrevious();
            countingIndex--;
        }
        
        return node;
    }
    
    /**
     * Returns a DoubleNode at the given index using helper methods.
     * 
     * @param index the index of the DoubleNode to return
     * 
     * @precondition:  0 <= index < size
     * @postcondition: starting from first or last (whichever
     *                 is closer), returns the node with given
     *                 index
     *         
     * @return the corresponding DoubleNode
     */
    private DoubleNode getNode(int index)
    {
        if(index <= size / 2)
            return getNodeFromFirst(index);
        
        return getNodeFromLast(index);
    }

    /**
     * Retrieves the current size of this MyLinkedList.
     * 
     * @return the size
     */
    public int size()
    {
        return size;
    }

    /**
     * Returns the value stored in the given index of the list.
     * 
     * @return the corresponding value to the index
     */
    public E get(int index)
    {
        return (E) getNode(index).getValue();
    }

    /**
     * Sets the value to a new one at the given index and returns
     * the former value.
     * 
     * @param index the index of the value to change
     * @param obj   the new value
     * 
     * @postcondition: replaces the element at position index with obj
     *                 returns the element formerly at the specified position
     * 
     * @return the former value stored at the given index
     */
    public E set(int index, E obj)
    {   
        DoubleNode node = getNode(index);
        Object removed = node.getValue();
        node.setValue(obj);
        
        return (E) removed;
    }

    /**
     * Adds the value to the end of the list and increases size of list by one.
     * 
     * @postcondition: appends obj to end of list; returns true
     * @return true
     */
    public boolean add(E obj)
    {
        if(last == null)
            first = last = new DoubleNode(obj);

        else
        {
            DoubleNode newNode = new DoubleNode(obj);
            last.setNext(newNode);
            newNode.setPrevious(last);
            last = newNode;
        }
        size++;
        return true;
    }

    /**
     * Removes and returns the value stored at the given index and adjusts
     * the list accordingly.
     * 
     * @param index the index of the value to remove
     * 
     * @postcondition: removes element from position index, moving elements
     *                 at position index + 1 and higher to the left
     *                 (subtracts 1 from their indices) and adjusts size
     *                 returns the element formerly at the specified position
     *                 
     * @return the value formerly stored at the given index
     */
    public E remove(int index)
    {
        DoubleNode node = getNode(index);
        Object removed = node.getValue();
        DoubleNode previous = node.getPrevious();
        DoubleNode next = node.getNext();
       
        if(previous != null)
            previous.setNext(next);
        
        else
            first = next;

        if(next != null)
            next.setPrevious(previous);
           
        else
            last = previous;
 
        size--;
        return (E) removed;
    }

    /**
     * Adds the given value to the list at the given index.
     * 
     * @param index the index to add the value at
     * @param obj   the value to add to the list
     * 
     * @precondition:  0 <= index <= size
     * @postcondition: inserts obj at position index,
     *                 moving elements at position index and higher
     *                 to the right (adds 1 to their indices) and adjusts size
     *             
     */
    public void add(int index, E obj)
    {
        if(index == size) 
        {
            add(obj);
            return;
        }
        
        DoubleNode next = getNode(index);
        DoubleNode previous = next.getPrevious();
        
        DoubleNode newNode = new DoubleNode(obj);
        
        newNode.setNext(next);
        newNode.setPrevious(previous);
        
        if(previous != null)
            previous.setNext(newNode);
        else
            first = newNode;
        
        next.setPrevious(newNode);
        
        size++;
    }

    /**
     * Adds the given value to the beginning of the list.
     * 
     * @param obj   the value to add
     */
    public void addFirst(E obj)
    {
        add(0, obj);
    }

    /**
     * Adds the given value to the end of the list.
     * 
     * @param obj   the value to add
     */
    public void addLast(E obj)
    {
        add(obj);
    }

    /**
     * Returns the value stored at the first index in the list.
     * 
     * @return the corresponding value
     */
    public E getFirst()
    {
        return (E) first.getValue();
    }

    /**
     * Returns the value stored at the last index in the list.
     * 
     * @return the corresponding value
     */
    public E getLast()
    {
        return (E) last.getValue();
    }

    /**
     * Removes and returns the value stored in the first index in the list.
     * 
     * @return the value stored in the first index
     */
    public E removeFirst()
    {
        Object val = first.getValue();
        first = first.getNext();
        if(first != null)
            first.setPrevious(null);
        size--;
            
        return (E) val;
    }

    /**
     * Removes and returns the value stored in the last index of the list.
     * 
     * @return the value stored in the last index
     */
    public E removeLast()
    {
        Object val = last.getValue();
        last = last.getPrevious();
        if(last != null)
            last.setNext(null);
        size--;
            
        return (E) val;
    }

    /**
     * Retrieves an iterator for this MyLinkedList.
     * 
     * @return an iterator for this list
     */
    public Iterator<E> iterator()
    {
        return new MyLinkedListIterator();
    }

    /**
     * MyLinkedListIterator acts as an iterator for MyLinkedList. It
     * can carry out multiple operations like next and remove.
     * 
     * @author Alex Yu
     * @version October 9, 2017
     */
    private class MyLinkedListIterator implements Iterator<E>
    {
        private DoubleNode nextNode;

        /**
         * Constructs a MyLinkedListIterator.
         */
        public MyLinkedListIterator()
        {
            nextNode = first;
        }

        /**
         * Checks to see if the iterator can continue through the list.
         * 
         * @return true if there is an element following to iterate through; otherwise,
         *         false
         */
        public boolean hasNext()
        {
            return nextNode != null;
        }

        /**
         * Continues to iterate through the list and returns the previous value.
         * 
         * @return the previous value
         */
        public E next()
        {
            DoubleNode previous = nextNode;
            nextNode = nextNode.getNext();
            return (E) previous.getValue();
        }

        /**
         * Removes the last element returned by next.
         * 
         * @postcondition: removes the last element that was returned by next
         */
        public void remove()
        {
            DoubleNode previous = nextNode.getPrevious();
            if(previous == null)
                throw new IllegalStateException();
            DoubleNode previousPrevious = previous.getPrevious();
            
            if(previousPrevious != null)
            {
                previousPrevious.setNext(nextNode);
                nextNode.setPrevious(previousPrevious);
            }
            else
            {
                first = nextNode;
                first.setPrevious(null);
            }
            size--;
        }
    }
}