/**
 * Main tests the HeapUtils class and its various methods.
 * 
 * @author Alex Yu
 * @version January 12, 2017
 */
public class Main
{
    private static final int INIT_ELEMENTS = 12;
    
    /**
     * Tests the program.
     * 
     * @param args  info from the command line
     */
    public static void main(String[] args)
    {
        Comparable[] ints = new Integer[INIT_ELEMENTS];
        for(int i=1; i<ints.length; i++)
            ints[i] = (int) (Math.random()*100 + 1);
        
        HeapUtils util = new HeapUtils();
        HeapDisplay display = new HeapDisplay();
        display.displayHeap(ints, ints.length - 1);
        
        // test for heapSort
        
        printArray(ints);
        util.heapSort(ints, ints.length-1);
        printArray(ints);
        
        display = new HeapDisplay();
        display.displayHeap(ints, ints.length-1);
        
       
        // test for insert
        /*
        printArray(ints);
        Comparable[] newInts = util.insert(ints, new Integer(103), ints.length-1);
        display = new HeapDisplay();
        display.displayHeap(newInts, ints.length-1);
        printArray(newInts);
        */
        
        // test for remove
        /*
        printArray(ints);
        Comparable obj = util.remove(ints, ints.length - 1);
        System.out.println("Returned object was " + obj);
        ints = trim(ints);
        printArray(ints);
        
        display = new HeapDisplay();
        display.displayHeap(ints, ints.length - 1);
        */
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
    
    /**
     * Removes null elements after element 1 from array. Used after 
     * removing.
     * 
     * @param arr   the array to trim
     * @return the new array
     */
    private static Comparable[] trim(Comparable[] arr)
    {
        if(arr.length < 2) return arr;
        
        int sizeCount = 1;
        for(int i=1; i<arr.length; i++)
            if(arr[i] != null) sizeCount++;
            
        Comparable[] newArr = new Comparable[sizeCount];
        for(int i=1; i<sizeCount; i++)
            newArr[i] = arr[i];
            
        return newArr;
    }
}
