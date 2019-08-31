import java.util.*;
/**
 * BSTUtilities can carry out multiple methods (contains, insert, and
 * delete) that can be used on a BinarySearchTree.
 * 
 * @author Alex Yu
 * @version 12/8/2017
 */
@SuppressWarnings("unchecked")
public abstract class BSTUtilities
{
    /**
     * Checks to see if a value is present within the given tree.
     * 
     * @param t the tree to check for the presence of the given value
     * @param x the value to check for in the tree
     * @param display the display for the tree
     * 
     * @return true if the value is in the tree; otherwise,
     *         false
     */
    public static boolean contains(TreeNode t, Comparable x, TreeDisplay display)
    {
        if(t == null)
            return false;
        
        display.visit(t);    
            
        if(x.compareTo(t.getValue()) > 0)
            return contains(t.getRight(), x, display);
        
        else if(x.compareTo(t.getValue()) < 0)  
            return contains(t.getLeft(), x, display);
            
        return true;
    }
    
    /**
     * Inserts a value into the given tree.
     * 
     * @param t the tree to insert a value into
     * @param x the value to insert into the tree
     * @param display the display for the tree
     * 
     * @return the newly modified tree
     */
    public static TreeNode insert(TreeNode t, Comparable x, TreeDisplay display)
    {
        if(t == null)
            return new TreeNode(x);
            
        display.visit(t);
            
        if(x.compareTo(t.getValue()) > 0)
            t.setRight(insert(t.getRight(), x, display));
            
        else if(x.compareTo(t.getValue()) < 0)
            t.setLeft(insert(t.getLeft(), x, display));
        
        return t;
    }
    
    /**
     * Deletes a given node from the tree.
     * 
     * @param t the node to delete from the tree
     * @param display the display for the tree
     * 
     * @return any subtree that must be added to the old node's spot.
     */
    private static TreeNode deleteNode(TreeNode t, TreeDisplay display)
    {
        display.visit(t);
        
        if(t.getLeft() == null && t.getRight() == null) return null;
        else if(t.getLeft() != null && t.getRight() == null) return t.getLeft();
        else if(t.getLeft() == null && t.getRight() != null) return t.getRight();
        
        TreeNode leftmost = (TreeNode) leftmostNode( t.getRight(), display );
        t.setValue( leftmost.getValue() );
        t.setRight( delete(t.getRight(), (Comparable) leftmost.getValue(), display) );
        
        return t;
    }
    
    /**
     * Finds the left-most node in a given tree.
     * 
     * @param t the tree to traverse through
     * @param display the display for the tree
     * 
     * @return the left-most node
     */
    private static TreeNode leftmostNode(TreeNode t, TreeDisplay display)
    {
        if( t == null ) return null;
        
        display.visit(t);
        if( t.getLeft() == null ) return t;
        
        return leftmostNode(t.getLeft(), display);
    }
    
    /**
     * Deletes a value from the given tree.
     * 
     * @param t the tree to delete the value from
     * @param x the value to delete
     * @param display the display for the tree
     * 
     * @return the newly modified tree
     */
    public static TreeNode delete(TreeNode t, Comparable x, TreeDisplay display)
    {
        if(x.compareTo(t.getValue()) == 0)
            return deleteNode(t, display);
            
        else if(x.compareTo(t.getValue()) > 0)
            t.setRight(delete(t.getRight(), x, display));
            
        else
            t.setLeft(delete(t.getLeft(), x, display));
        
        return t;
    }

}