/**
 * The Tetris class oversees a game of Tetris.
 * 
 * @author Alex Yu
 * @version February 24, 2017
 */
public class Tetris implements ArrowListener
{
    private MyBoundedGrid<Block> grid;
    private BlockDisplay display;

    private Tetrad activeTetrad;
    
    private boolean isGameOver;
    private int points;
    
    /**
     * Constructs a Tetris object that oversees the game.
     */
    public Tetris()
    {
        grid = new MyBoundedGrid<Block>(20, 10);
        display = new BlockDisplay(grid);
        display.setTitle("Tetris - 0 points");
        display.setArrowListener(this);
        
        activeTetrad = new Tetrad(grid);
        
        display.showBlocks();
        isGameOver = false;
        points = 0;
        play();
    }
     
    /**
     * Rotates the active tetrad 90 degrees.
     */
    public void upPressed()
    {
        if(isGameOver)
            return;
        
        activeTetrad.rotate();
        display.showBlocks();
    }
    
    /**
     * Moves the active tetrad by one space downwards.
     */
    public void downPressed()
    {
        if(isGameOver)
            return;
        activeTetrad.translate(1, 0);
        display.showBlocks();
    }
    
    /**
     * Moves the active tetrad by one space to the left.
     */
    public void leftPressed()
    {
        if(isGameOver)
            return;
            
        activeTetrad.translate(0, -1);
        display.showBlocks();
    }
    
    /**
     * Moves the active tetrad by one space to the right.
     */
    public void rightPressed()
    {
        if(isGameOver)
            return;
        
        activeTetrad.translate(0, 1);
        display.showBlocks();
    }
    
    /**
     * Moves the active tetrad down until it cannot go down any further.
     */
    public void spacePressed()
    {
        while(activeTetrad.translate(1, 0))
        {}
    }
    
    /**
     * Checks to see if the specified row is completely filled with blocks.
     * 
     * @precondition    the row is a valid row between 0, inclusive, and the total
     *                  number of rows, exclusive
     * 
     * @param row   the specified row number to check
     * @return true if the row is completely filled with blocks; otherwise,
     *         false
     */
    private boolean isCompletedRow(int row)
    {
        for(int col = 0; col < grid.getNumCols(); col++)
        {
            if(grid.get( new Location(row, col) ) == null)
                return false;
        }
        
        return true;
    }
    
    /**
     * Clears the specified row by removing its blocks.
     * 
     * @precondition    the row is filled with blocks
     * @precondition    the row is a valid row between 0, inclusive, and the total
     *                  number of rows, exclusive
     * 
     * @param row   the specified row number to clear of blocks
     */
    private void clearRow(int row)
    {
        for(int col = 0; col < grid.getNumCols(); col++)
        {
            Block b = grid.get( new Location(row, col) );
            b.removeSelfFromGrid();
        }
        
        for(int r = row - 1; r >= 0; r--)
        {
            for(int c = 0; c < grid.getNumCols(); c++)
            {
                Location loc = new Location(r, c);
                Location newLoc = new Location(r+1, c);
                
                Block b = grid.get(loc);
                if(b != null)
                    b.moveTo(newLoc);
            }
        }
    }
    
    /**
     * Removes any rows that are completely filled with blocks.
     */
    public void clearCompletedRows()
    {
        for(int row = 0; row < grid.getNumRows(); row++)
        {
            if(isCompletedRow(row))
            {
                clearRow(row);
                points += 10;
                display.setTitle("Tetris - " + points + " points");
            }
        }
    }
    
    /**
     * Oversees the game of Tetris.
     */
    public void play()
    {
        boolean notAtBottom = false;
        
        int timeWait = 1000;
        
        while(!isGameOver)
        {
            try
            {
                Thread.sleep(timeWait);
            } 
            catch(InterruptedException e)
            {
                System.out.println(e);
            }
            
            notAtBottom = activeTetrad.translate(1, 0);
            display.showBlocks();
            if(!notAtBottom)
            {
                clearCompletedRows();
                activeTetrad = new Tetrad(grid);
                
                if(!activeTetrad.translate(1, 0))
                    isGameOver = true;
            }
        }
        
        System.out.println("Game Over");
        display.showBlocks();
    }
    
    /**
     * Creates a Tetris object to oversee the gmae
     * 
     * @param args  information from the command line
     */
    public static void main(String[] args)
    {
        new Tetris();
    }
}