import java.util.Arrays;
/**
 * HeapUtils provides several useful methods concerning a Heap, 
 * including heapify, remove, and heapSort.
 * 
 * @author Alex Yu
 * @version January 12, 2017
 */
@SuppressWarnings("unchecked")
public class HeapUtils
{
    /**
     * Heapifies a given subtree of the heap. Heapify runs in O(log n) time because
     * it traverses through a binary tree recursively.
     * 
     * @param heap  the heap from which to heapify
     * @param index the index of the subtree to heapify
     * @param heapSize  the size of the heap
     */
    public static void heapify(Comparable[] heap, int index, int heapSize)
    {
        int leftChild = index * 2;
        int rightChild = leftChild + 1;
        
        int indexOfMax = index;
        
        if(leftChild <= heapSize && 
           heap[leftChild].compareTo(heap[indexOfMax]) > 0)
            indexOfMax = leftChild;
            
        if(rightChild <= heapSize && 
           heap[rightChild].compareTo(heap[indexOfMax]) > 0)
            indexOfMax = rightChild;
            
        if(indexOfMax != index)
        {
            swapValues(heap, index, indexOfMax);
            heapify(heap, indexOfMax, heapSize);
        }
    }
    
    /**
     * Swaps two given values in an array designating a heap.
     * 
     * @precondition 0 <= index1 <= heap.length
     * @precondition 0 <= index2 <= heap.length
     * 
     * @param heap  the array in which to swap values
     * @param index1    the index of the first value to swap
     * @param index2    the index of the second value to swap
     */
    private static void swapValues(Comparable[] heap, int index1, int index2)
    {
        Comparable temp = heap[index2];
        heap[index2] = heap[index1];
        heap[index1] = temp;
    }
    
    /**
     * Creates an array that designates a heap. It runs in O(nlog n) time overall
     * because the for-loop runs in O(n) time and the heapify method runs in O(log n).
     * 
     * @param heap  the array for the heap
     * @param heapSize the size of the heap
     * 
     * @postcondition heap contains a binary tree that completes the heap
     *                condition
     */
    public static void buildHeap(Comparable[] heap, int heapSize)
    {
        for(int index = heapSize/2; index >= 1; index--)
            heapify(heap, index, heapSize);
    }
    
    /**
     * Uses a heap to sort a collection of values stored within the given array. Runs 
     * in O(nlog n) time because of the heapify process.
     * 
     * @param heap  the array of values to sort
     */
    public static void heapSort(Comparable[] heap, int heapSize)
    {
        int last = heapSize;
        buildHeap(heap, last);
        for(int i = last; i > 0; i--)
        {
           swapValues(heap, 1, i);
           last--;
           heapify(heap, 1, last);
        }
    }
    
    /**
     * Removes and returns the root value. Runs in O(log n) because of the 
     * heapify process.
     * 
     * @param heap  the array to remove root from
     * @param heapSize  the size of the heap
     * 
     * @postcondition array heap meets heap condition
     * 
     * @return the root value that was removed
     */
    public static Comparable remove(Comparable[] heap, int heapSize)
    {
        Comparable obj = heap[1];
        swapValues(heap, 1, heapSize);
        heap[heapSize] = null;
        heapSize--;
        heapify(heap, 1, heapSize);
        return obj;
    }
    
    /**
     * Inserts a Comparable object into heap and returns resulting heap. Runs in 
     * O(log n) time because of the sifting up process.
     * 
     * @param heap  the array to insert value into
     * @param item  the object to insert
     * @param heapSize  the size of the heap
     * 
     * @postcondition   array heap meets heap condition
     * 
     * @return the heap with the newly inserted value
     */
    public static Comparable[] insert(Comparable[] heap, Comparable item, int heapSize)
    {
        Comparable[] newHeap = new Comparable[heapSize+2];
        for(int i=1; i<= heapSize; i++)
            newHeap[i] = heap[i];
        newHeap[heapSize+1] = item;
        
        int index = newHeap.length-1;
        int parent;
        while(index != 1)
        {
            parent = index / 2;
            if(newHeap[parent].compareTo(newHeap[index]) < 0)
            {
                swapValues(newHeap, parent, index);
                index = parent;
            }
        }
        
        return newHeap;
    }
    
    /**
     * Prints the contents of an array. Used for debugging.
     * 
     * @param arr   the array to print
     */
    private static void printArray(Comparable[] arr)
    {
        for(Comparable c: arr)
            System.out.print(c + "\t");
        System.out.println();
    }
}
