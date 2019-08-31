import java.util.*;
import java.awt.*;

/**
 * A Tetrad is a set of four blocks that moves downward and can
 * be subject to movement right and left.
 * 
 * @author Alex Yu
 * @version February 24, 2017
 */
public class Tetrad
{
    private Block[] blocks;
    
    private boolean isOShape;
    
    /**
     * Constructs a new tetrad object of a random shape.
     * 
     * @param g the grid that contains the tetrad
     */
    public Tetrad(MyBoundedGrid<Block> g)
    {
        blocks = new Block[4];
        for(int i=0; i<blocks.length; i++)
        {
            blocks[i] = new Block();
        }
        
        int rand = (int) (Math.random()*7);
        Location[] locs = new Location[4];
        
        Color color;
        isOShape = false;
        if(rand == 0) 
        {
            // I shape
            locs[0] = new Location(1, 5); // Pivot point
            locs[1] = new Location(0, 5);
            locs[2] = new Location(2, 5);
            locs[3] = new Location(3, 5);
            color = Color.RED;
        }
        
        else if(rand == 1)
        {
            // T shape
            locs[0] = new Location(0, 5); // Pivot point
            locs[1] = new Location(0, 4);
            locs[2] = new Location(1, 5);
            locs[3] = new Location(0, 6);
            color = Color.GRAY;
        }
        
        else if(rand == 2)
        {
            // O shape
            locs[0] = new Location(0, 5);
            locs[1] = new Location(1, 5);
            locs[2] = new Location(0, 6);
            locs[3] = new Location(1, 6);
            color = Color.CYAN;
            isOShape = true;
        }
        
        else if(rand == 3)
        {
            // L shape
            locs[0] = new Location(1, 5); // Pivot point
            locs[1] = new Location(0, 5);
            locs[2] = new Location(2, 5);
            locs[3] = new Location(2, 6);
            color = Color.YELLOW;
        }
        
        else if(rand == 4)
        {   
            // J shape
            locs[0] = new Location(1, 5); // Pivot point
            locs[1] = new Location(0, 5);
            locs[2] = new Location(2, 5);
            locs[3] = new Location(2, 4);
            color = Color.MAGENTA;
        }
        
        else if(rand == 5)
        {
            // S shape
            locs[0] = new Location(0, 5); // Pivot point
            locs[1] = new Location(1, 5);
            locs[2] = new Location(1, 4);
            locs[3] = new Location(0, 6);
            color = Color.BLUE;
        }
        
        else
        {
            // Z shape
            locs[0] = new Location(0, 5); // Pivot point
            locs[1] = new Location(0, 4);
            locs[2] = new Location(1, 5);
            locs[3] = new Location(1, 6);
            color = Color.GREEN;
        }
        
        for(Block b: blocks)
            b.setColor(color);
        addToLocations(g, locs);
    }

    /**
     * Adds the blocks that constitutes the tetrad to the grid.
     * 
     * @precondition the blocks are not contained in any grid
     * 
     * @param grid  the grid that contains the blocks
     * @param locs  the starting locations of each of the blocks
     */
    private void addToLocations(MyBoundedGrid<Block> grid, Location[] locs)
    {
        for(int i = 0; i < blocks.length; i++)
        {
            blocks[i].putSelfInGrid(grid, locs[i]);
        }
    }

    /**
     * Removes the blocks that constitutes the tetrad from the grid.
     * 
     * @precondition the blocks are contained in a grid
     * 
     * @return  an array of the former locations of each of the blocks
     */
    private Location[] removeBlocks()
    {
        Location[] locs = new Location[blocks.length];
        for(int i = 0; i < locs.length; i++)
        {
            locs[i] = blocks[i].getLocation();
            blocks[i].removeSelfFromGrid();
        }
        
        return locs;
    }
    
    /**
     * Checks to see if the given locations are empty within the grid.
     * 
     * @param grid  the grid to check
     * @param locs  the locations within the grid to check if empty
     * 
     * @return true if all the given locations are both valid and empty; otherwise,
     *         false
     */
    private boolean areEmpty(MyBoundedGrid<Block> grid, Location[] locs)
    {
        for(int i = 0; i < locs.length; i++)
        {
            if(!grid.isValid(locs[i]) || grid.get(locs[i]) != null)
                return false;
        }
        
        return true;
    }
    
    /**
     * Moves the entire tetrad left/right and up/down by a specific amount.
     * 
     * @param deltaRow  the number of rows and the direction for the tetrad to move
     * @param deltaCol  the number of columns and the direction for the tetrad to move
     * 
     * @return true if the tetrad was translated successfully; otherwise,
     *         false
     */
    public boolean translate(int deltaRow, int deltaCol)
    {
        MyBoundedGrid<Block> grid = blocks[0].getGrid();
        
        Location[] orig = removeBlocks();
        Location[] newLocs = new Location[orig.length];
        
        for(int i = 0; i < orig.length; i++)
        {
            Location temp = orig[i];
            newLocs[i] = new Location(temp.getRow()+deltaRow, temp.getCol()+deltaCol);
        }
        
        if(!areEmpty(grid, newLocs))
        {
            addToLocations(grid, orig);
            return false;
        }
        
        addToLocations(grid, newLocs);
        return true;
    }
    
    /**
     * Rotates the entire tetrad 90 degrees around a pivot point.
     * 
     * @return  true    if the tetrad was able to be rotated; otherwise,
     *          false
     */
    public boolean rotate()
    {   
        if(isOShape)
            return true;
        
        MyBoundedGrid<Block> grid = blocks[0].getGrid();    
            
        Location pivotLoc = blocks[0].getLocation();
        int pivotRow = pivotLoc.getRow();
        int pivotCol = pivotLoc.getCol();
        
        Location[] orig = removeBlocks();
        Location[] newLocs = new Location[orig.length];

        for(int i = 0; i < orig.length; i++)
        {
            Location temp = orig[i];
            newLocs[i] = new Location(pivotRow - pivotCol + temp.getCol(), 
                                      pivotRow + pivotCol - temp.getRow());
                                      
        }
        
        if(!areEmpty(grid, newLocs))
        {
            addToLocations(grid, orig);
            return false;
        }
        
        addToLocations(grid, newLocs);
        return true;
    }
}
