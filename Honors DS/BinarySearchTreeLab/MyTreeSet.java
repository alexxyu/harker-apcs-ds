/**
 * MyTreeSet is a BinarySearchTree and a Set rolled into one!
 * 
 * @author Alex Yu
 * @version January 8, 2017
 */
public class MyTreeSet<E>
{
    private TreeNode root;
    private int size;
    private TreeDisplay display;

    /**
     * Constructs a MyTreeSet.
     */
    public MyTreeSet()
    {
        root = null;
        size = 0;
        display = new TreeDisplay();

        //wait 1 millisecond when visiting a node
        display.setDelay(1);
    }

    /**
     * Gets the size of the TreeSet.
     * 
     * @return the size of the TreeSet
     */
    public int size()
    {
        return size;
    }

    /**
     * Checks to see if the given object is in the TreeSet.
     * 
     * @param obj the object to check
     * 
     * @return true if the object is in the TreeSet; otherwise,
     *         false
     */
    public boolean contains(Object obj)
    {
        return BSTUtilities.contains(root, (Comparable) obj, display);
    }

    /**
     * Adds the given object to the TreeSet.
     * 
     * @param obj the object to add
     * 
     * @return true if the object was added to the TreeSet; otherwise,
     *         false
     */
    public boolean add(E obj)
    {
        if( contains(obj) ) return false;
        
        root = BSTUtilities.insert(root, (Comparable) obj, display);
        size++;
        return true;
    }

    /**
     * Removes the given object from the TreeSet.
     * 
     * @param obj the object to remove
     * 
     * @return true if the object was removed from the TreeSet; otherwise,
     *         false
     */
    public boolean remove(Object obj)
    {
        if( !contains(obj) ) return false;
        
        root = BSTUtilities.delete(root, (Comparable) obj, display);
        size--;
        return true;
    }

    /**
     * Gets a formatted string representing the TreeSet.
     * 
     * @return a string representing the TreeSet
     */
    public String toString()
    {
        return toString(root);
    }

    /**
     * Gets a formatted string representing a tree.
     * 
     * @param t the root of the given tree
     * 
     * @return a string representing the tree
     */
    private String toString(TreeNode t)
    {
        if (t == null)
            return " ";
        return toString(t.getLeft()) + t.getValue() + "," + toString(t.getRight());
    }
}