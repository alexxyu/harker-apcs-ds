import java.util.*;
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tester
{
    public static void main(String[] args)
    {
        /*
        TreeNode tree = TreeUtil.createRandom(6);
        TreeDisplay display = new TreeDisplay();
        display.displayTree(tree);
        
        System.out.println("There are " + TreeUtil.countNodes(tree) + " nodes");
        System.out.println("There are " + TreeUtil.countLeaves(tree) + " leaves");
        
        TreeUtil.postOrder(tree, display);
        
        
        TreeUtil.saveTree("TreeData", tree);
        TreeUtil.postOrder(tree, display);
        
        tree = TreeUtil.createRandom(4);
        display.displayTree(tree);
        TreeUtil.postOrder(tree, display);
        
        tree = TreeUtil.loadTree("TreeData");
        display.displayTree(tree);
        
       
        TreeNode copy = TreeUtil.copy(tree);
        
        tree = TreeUtil.createRandom(4);
        display.displayTree(tree);
        TreeUtil.postOrder(tree, display);
        
        display.displayTree(copy);
        */
         
       
        TreeNode tree = new TreeNode("*",
                            new TreeNode("+", new TreeNode(new Integer(4)), new TreeNode(new Integer(1))),
                            new TreeNode("-", new TreeNode(new Integer(4)), new TreeNode(new Integer(1))));
                            
        System.out.println("Answer is " + TreeUtil.eval(tree));

       
    }
}
