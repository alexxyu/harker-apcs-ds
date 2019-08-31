import info.gridworld.actor.Flower;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import java.awt.Color;

/**
 * VenusFlyTrap eats bugs that are in front and behind it. If it has eaten
 * within the last step, then it lights up. Otherwise, it gradually loses its color.
 * 
 * @author Alex Yu
 * @version September 26, 2016
 */
public class VenusFlyTrap extends Flower
{
    private boolean hasEaten;

    /**
     * Constructs VenusFlyTrap that eats bugs
     */
    public VenusFlyTrap()
    {
        super(Color.GREEN);
    }
    
    /**
     * Checks to see if there is a bug at the given location and then consumes
     *  it if there is one.
     *  
     * @param loc the location to check for a bug
     */
    public void eatBug(Location loc)
    {
        Grid<Actor> gr = getGrid();
        if(!gr.isValid(loc) || gr == null)
        {
            return;
        }
        
        Actor actor = gr.get(loc);
        if(actor instanceof Bug)
        {
            actor.removeSelfFromGrid();
            setColor(Color.GREEN);
            hasEaten = true;
        }
    }
    
    /**
     * Oversees the eating by checking spaces in front and behind the plant for bugs.
     */
    public void eat()
    {
        int dir = getDirection();
        int frontDir = dir + Location.AHEAD;
        int backDir = dir + Location.HALF_CIRCLE;
        
        Location loc = getLocation();
        Location front = loc.getAdjacentLocation(frontDir);
        Location behind = loc.getAdjacentLocation(backDir);
        
        eatBug(front);
        eatBug(behind);
    }
    
    /**
     * Tries to eat and if it doesn't eat anything, then its color fades.
     */
    public void act()
    {
        hasEaten = false;
        eat();
        if(!hasEaten)
        {
            super.act();
        }
    }
}
