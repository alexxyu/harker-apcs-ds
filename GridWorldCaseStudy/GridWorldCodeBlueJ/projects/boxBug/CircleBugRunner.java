import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.*;
import info.gridworld.actor.*;
import java.awt.Color;

/**
 * This class runs a world that contains circle bugs.
 * 
 * @author Alex Yu 
 * @version September 16, 2016
 */
public class CircleBugRunner
{
    /**
     * Creates the grid and fills it with CircleBug objects.
     * 
     * @param args  information from the commond line
     */
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld(new BoundedGrid<Actor>(12,12));
        
        CircleBug joe = new CircleBug(2);
        joe.setColor(Color.BLUE);
        CircleBug ken = new CircleBug(3);
        world.add(new Location(7, 8), joe);
        world.add(new Location(5, 5), ken);
        world.show();
    }
}
