import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.*;
import info.gridworld.actor.*;
import info.gridworld.grid.UnboundedGrid;

/**
 * This class manages a world full of DancingBugs.
 * 
 * @author Alex Yu
 * @version September 18, 2016
 */
public class DancingBugRunner
{
    /**
     * Creates the grid and fills it with DancingBugs.
     * 
     * @param args  information from the command line
     */
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld(new UnboundedGrid<Actor>());
        
        // create an array of integers
        int[] danceRoutine = {4, 37, 42, 2, 55, 6, 96, 8, 2, 9, 7, 5, 3, 1};
        
        // pass an integer array as a parameter 
        DancingBug bug = new DancingBug(4, danceRoutine);
        
        world.add(new Location(3, 4), bug);
        world.show();
    }
}
