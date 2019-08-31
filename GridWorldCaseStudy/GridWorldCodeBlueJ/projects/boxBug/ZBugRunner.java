import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.*;
import info.gridworld.actor.*;
import java.awt.Color;

/**
 * This class creates a world with ZBugs.
 * 
 * @author Alex Yu 
 * @version September 16, 2016
 */
public class ZBugRunner
{
    /**
     * Creates the grid and fills it with 2 ZBugs.
     * 
     * @param args  information from the commond line
     */
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld(new BoundedGrid<Actor>(12,12));
        
        ZBug bob = new ZBug(2);
        bob.setColor(Color.GREEN);
        ZBug joe = new ZBug(3);
        world.add(new Location(3, 4), bob);
        world.add(new Location(5, 5), joe);
        world.show();
    }
}
