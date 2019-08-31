import java.util.*;
/**
 * TreeUtil contains the following methods for manipulating binary trees:
 * 
 * void preOrder(TreeNode t, TreeDisplay display)
 * void inOrder(TreeNode t, TreeDisplay display)
 * void postOrder(TreeNode t, TreeDisplay display)
 * void saveTree(String fileName, TreeNode t)
 * TreeNode loadTree(String fileName)
 * TreeNode copy(TreeNode t)
 * boolean sameShape(TreeNode t1, TreeNode t2)
 * int eval(TreeNode expTree)
 * void insertMorse(TreeNode decodingTree, String letter,
 *                                   String code, TreeDisplay display)
 * String decodeMorse(TreeNode decodingTree, String cipherText, TreeDisplay display)
 * 
 * @author Alex Yu
 * @version December 4, 2017
 *
 */
public class TreeUtil
{
    //used to prompt for command line input
    private static Scanner in = new Scanner(System.in);
    
    private static final boolean debug = false;


    /**
     * Returns the left node of the given TreeNode.
     * 
     * @param t the given TreeNode
     * @return the left node of the given TreeNode
     */
    public static Object leftmost(TreeNode t)
    {
        if(t == null)
            return null;
        
        return t.getLeft();
    }

    /**
     * Returns the left node of the given TreeNode.
     * 
     * @param t the given TreeNode
     * @return the left node of the given TreeNode
     */
    public static Object rightmost(TreeNode t)
    {
        if(t == null)
            return null;
        
        return t.getRight();
    }
    
    /**
     * Returns the max size of the given tree.
     * 
     * @param t the given tree
     * @return the size of the given tree
     */
    public static int maxDepth(TreeNode t)
    {
        // base case
        if( t == null ) return 0;
        
        return 1 + Math.max( maxDepth(t.getLeft()), maxDepth(t.getRight()) );
    }

    /**
     * create a random tree of the specified depth.  No attempt to balance the tree
     * is provided.
     * @param depth of the tree
     * @return TreeNode object that points to the generated tree
     */
    public static TreeNode createRandom(int depth)
    {
        if (Math.random() * Math.pow(2, depth) < 1)
            return null;
            
        return new TreeNode(((int)(Math.random() * 10)),
            createRandom(depth - 1),
            createRandom(depth - 1));
    }
    /**
     * Returns the number of nodes in the given tree.
     * 
     * @param t the given tree
     * @return the number of nodes in the given tree
     */
    public static int countNodes(TreeNode t)
    {
        // base case
        if( t == null ) return 0;
        
        return 1 + countNodes(t.getLeft()) + countNodes(t.getRight());
    }
    /**
     * Returns the number of leaves in the given tree.
     * 
     * @param t the given tree
     * @return the number of leaves in the given tree
     */
    public static int countLeaves(TreeNode t)
    {
        // base cases
        if( t == null ) return 0;
    
        if( t.getLeft() == null && t.getRight() == null)
            return 1;
            
        return countLeaves(t.getLeft()) + countLeaves(t.getRight());
    }
    /**
     * Visits each node in the given tree in pre-order fashion.
     * 
     * @param t the given tree
     * @param display the display of the given tree
     */
    public static void preOrder(TreeNode t, TreeDisplay display)
    {
        // base case
        if( t == null )
            return;
            
        display.visit(t);
        preOrder(t.getLeft(), display);
        preOrder(t.getRight(), display);
    }
    
    /**
     * Visits each node in the given tree in in-order fashion.
     * 
     * @param t the given tree
     * @param display the display of the given tree
     */
    public static void inOrder(TreeNode t, TreeDisplay display)
    {
        // base case
        if( t == null ) 
            return;
        
        inOrder(t.getLeft(), display);
        display.visit(t);
        inOrder(t.getRight(), display);
    }
    
    /**
     * Visits each node in the given tree in post-order fashion.
     * 
     * @param t the given tree
     * @param display the display of the given tree
     */
    public static void postOrder(TreeNode t, TreeDisplay display)
    {
        // base case
        if( t == null )
            return;
            
        postOrder(t.getLeft(), display);
        postOrder(t.getRight(), display);
        display.visit(t);
    }
    
    /**
     * Converts a given tree to string format that is added to a list.
     * 
     * @param t the tree to convert
     * @param list the list with which to fill strings for the converted tree
     */
    public static void fillList(TreeNode t, List<String> list)
    {
        // base case
        if( t == null )
        {
            list.add("$");
            return;
        }
            
        list.add(String.valueOf( t.getValue()));
        fillList(t.getLeft(), list);
        fillList(t.getRight(), list);
    }
    
    /**
     * saveTree uses the FileUtil utility class to save the tree rooted at t
     * as a file with the given file name
     * @param fileName is the name of the file to create which will hold the data
     *        values in the tree
     * @param t is the root of the tree to save
     */
    public static void saveTree(String fileName, TreeNode t)
    {
        List<String> treeData = new ArrayList<String>();
        fillList(t, treeData);
        FileUtil.saveFile(fileName, treeData.iterator());
    }
    
    /**
     * buildTree takes in an iterator which will iterate through a valid description of
     * a binary tree with String values.  Null nodes are indicated by "$" markers
     * @param it the iterator which will iterate over the tree description
     * @return a pointer to the root of the tree built by the iteration
     */
    public static TreeNode buildTree(Iterator<String> it)
    {
        if( !it.hasNext() )
            return null;
            
        String s = it.next();    
        if(s.equals("$"))
            return null;
            
        TreeNode node = new TreeNode(s);
        node.setLeft( buildTree(it) );
        node.setRight( buildTree(it) );
        
        return node;
    }
    
    /**
     * read a file description of a tree and then build the tree
     * @param fileName is a valid file name for a file that describes a binary tree
     * @return a pointer to the root of the tree
     */
    public static TreeNode loadTree(String fileName)
    {
        Iterator<String> iter = FileUtil.loadFile(fileName);
        return buildTree(iter);
    }
    
    /**
     * utility method that waits for a user to type text into Std Input and then press enter
     * @return the string entered by the user
     */
    private static String getUserInput()
    {
        return in.nextLine();
    }
    
    /**
     * plays a single round of 20 questions
     * postcondition:  plays a round of twenty questions, asking the user questions as it
     *                 walks down the given knowledge tree, lighting up the display as it goes;
     *                 modifies the tree to include information learned.
     * @param t a pointer to the root of the game tree
     * @param display which will show the progress of the game
     */
    private static void twentyQuestionsRound(TreeNode t, TreeDisplay display)
    {   
        throw new RuntimeException("Write ME!");
    }
    
    /** 
     * plays a game of 20 questions
     * Begins by reading in a starting file and then plays multiple rounds
     * until the user enters "quit".  Then the final tree is saved
     */
    public static void twentyQuestions()
    {
        throw new RuntimeException("Write ME!");
    }
    
    /**
     * copy a binary tree
     * @param t the root of the tree to copy
     * @return a new tree, which is a complete copy
     *         of t with all new TreeNode objects
     *         pointing to the same values as t (in the same order, shape, etc)
     */
    public static TreeNode copy(TreeNode t)
    {
        if( t == null )
            return null;
        
        TreeNode node = new TreeNode(t.getValue());
        node.setLeft( copy(t.getLeft()) );
        node.setRight( copy(t.getRight()) );
        return node;
    }
    
    /**
     * tests to see if two trees have the same shape, but not necessarily the
     * same values.  Two trees have the same shape if they have TreeNode objects
     * in the same locations relative to the root
     * @param t1 pointer to the root of the first tree
     * @param t2 pointer to the root of the second tree
     * @return true if t1 and t2 describe trees having the same shape, false otherwise
     */
    public static boolean sameShape(TreeNode t1, TreeNode t2)
    {
        if( (t1 == null && t2 != null) || (t1 != null && t2 == null) )
            return false;
            
        if( t1 == null && t2 == null )
            return true;
            
        return sameShape(t1.getLeft(), t2.getLeft()) && sameShape(t1.getRight(), t2.getRight());
    }
    /**
     * Generate a tree for decoding Morse code
     * @param display the display that will show the decoding tree
     * @return the decoding tree
     */
    public static TreeNode createDecodingTree(TreeDisplay display)
    {
        TreeNode tree = new TreeNode("Morse Tree");
        display.displayTree(tree);
        insertMorse(tree, "a", ".-", display);
        insertMorse(tree, "b", "-...", display);
        insertMorse(tree, "c", "-.-.", display);
        insertMorse(tree, "d", "-..", display);
        insertMorse(tree, "e", ".", display);
        insertMorse(tree, "f", "..-.", display);
        insertMorse(tree, "g", "--.", display);
        insertMorse(tree, "h", "....", display);
        insertMorse(tree, "i", "..", display);
        insertMorse(tree, "j", ".---", display);
        insertMorse(tree, "k", "-.-", display);
        insertMorse(tree, "l", ".-..", display);
        insertMorse(tree, "m", "--", display);
        insertMorse(tree, "n", "-.", display);
        insertMorse(tree, "o", "---", display);
        insertMorse(tree, "p", ".--.", display);
        insertMorse(tree, "q", "--.-", display);
        insertMorse(tree, "r", ".-.", display);
        insertMorse(tree, "s", "...", display);
        insertMorse(tree, "t", "-", display);
        insertMorse(tree, "u", "..-", display);
        insertMorse(tree, "v", "...-", display);
        insertMorse(tree, "w", ".--", display);
        insertMorse(tree, "x", "-..-", display);
        insertMorse(tree, "y", "-.--", display);
        insertMorse(tree, "z", "--..", display);
        return tree;
    }
    /**
     * helper method for building a Morse code decoding tree.
     * postcondition:  inserts the given letter into the decodingTree,
     *                 in the appropriate position, as determined by
     *                 the given Morse code sequence; lights up the display
     *                 as it walks down the tree
     * @param decodingTree is the partial decoding tree
     * @param letter is the letter to add
     * @param code is the Morse code for letter
     * @param display is the display that will show progress as the method walks 
     *        down the tree
     */
    private static void insertMorse(TreeNode decodingTree, String letter,
                                    String code, TreeDisplay display)
    {
        // display.visit(decodingTree);
        if(code.substring(0, 1).equals("-"))
        {
            if(decodingTree.getRight() == null)
                decodingTree.setRight(new TreeNode(""));
                
            if(code.equals("-"))
                decodingTree.getRight().setValue(letter);
            else
                insertMorse(decodingTree.getRight(), letter, code.substring(1), display);
            return;
        }
        
        else if(code.substring(0, 1).equals("."))
        {
            if(decodingTree.getLeft() == null)
                decodingTree.setLeft(new TreeNode(""));
                
            if(code.equals("."))
                decodingTree.getLeft().setValue(letter);
            else
                insertMorse(decodingTree.getLeft(), letter, code.substring(1), display);
        }
    }
    
    /**
     * decodes Morse code by walking the decoding tree according to the input code
     * @param decodingTree is the Morse code decoding tree
     * @param cipherText is Morse code consisting of dots, dashes, and spaces
     * @param display is the display object that will show the decoding progress
     * @return the string represented by cipherText
     */
    public static String decodeMorse(TreeNode decodingTree, String cipherText, TreeDisplay display)
    {
        String[] ciphers = cipherText.split(" ");
        
        TreeNode node;
        String ciph;
        String msg = "";
        for(int i=0; i<ciphers.length; i++)
        {
            ciph = ciphers[i];
            node = decodingTree;
            while(!ciph.isEmpty())
            {
                if(ciph.substring(0, 1).equals("-"))
                    node = node.getRight();
                    
                else if(ciph.substring(0, 1).equals("."))
                    node = node.getLeft();
                    
                ciph = ciph.substring(1);
            }
            msg += node.getValue();
        }
        
        return msg;
    }
    
    /**
    * optional work
    */
    public static int eval(TreeNode expTree)
    {
        if(expTree.getLeft() == null && expTree.getRight() == null)
            return (Integer) expTree.getValue();
        
        Integer left = eval(expTree.getLeft());
        Integer right = eval(expTree.getRight());
        String op = (String) expTree.getValue();
        
        if(op.equals("+")) return left + right;
        if(op.equals("-")) return left - right;
        if(op.equals("*")) return left * right;
        if(op.equals("/")) return left / right;
        return left % right;
    }
    
    /**
    * optional work
    */
    public static TreeNode createExpressionTree(String exp)
    {
        throw new RuntimeException("Write ME!");
    }

    /**
     * debug printout
     * postcondition: out is printed to System.out
     * @param out the string to send to System.out
     */
    
    private static void debugPrint(String out)
    {
        if(debug) System.out.println("debug: " + out);
    }
}
	