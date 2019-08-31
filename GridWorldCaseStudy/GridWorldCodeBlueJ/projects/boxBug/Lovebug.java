import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import java.awt.Color;

/**
 * Lovebug acts like a normal Bug but also has a chance of breeding and
 * cloning itself into one or two spaces that are empty or occupied by a flower.
 * 
 * @author Alex Yu
 * @version September 26, 2016
 */
public class Lovebug extends Bug
{
    /**
     * The default chance that Lovebug clones itself
     */
    public static final int DEFAULT_BREEDING_PROBABILITY = 10;
    
    private int breedProb;

    /**
     * Constructs Lovebug with given color and chance of breeding.
     * 
     * @param color the color of the bug
     * @param probBreed the probability of breeding
     */
    public Lovebug(Color color, int probBreed)
    {
        super(color);
        breedProb = probBreed;
    }
    
    /**
     * Constructs Lovebug with given color and default breeding chance.
     * 
     * @param color the color of the bug
     */
    public Lovebug(Color color)
    {
        this(color, DEFAULT_BREEDING_PROBABILITY);
    }
    
    /**
     * Constructs Lovebug with default color and given breeding chance.
     * 
     * @param probBreed the probability of breeding
     */
    public Lovebug(int probBreed)
    {
        this(Color.RED, probBreed);
    }
    
    /**
     * Constructs Lovebug with default color and chance of breeding.
     */
    public Lovebug()
    {
        this(Color.RED, DEFAULT_BREEDING_PROBABILITY);
    }

    /**
     * Checks to see if the given space is empty or occupied by a Flower;
     * 
     * @param loc the space to check
     * @return true if loc is empty or is occupied by a Flower; otherwise,
     *         false
     */
    public boolean canClone(Location loc)
    {
        Grid<Actor> gr = getGrid();
        Location next = loc.getAdjacentLocation(getDirection());
        if (gr == null || !gr.isValid(next))
        {
            return false;
        }
        Actor neighbor = gr.get(next);
        return (neighbor == null) || (neighbor instanceof Flower);
    }
    
    /**
     * Breeds and creates a new Lovebug in the given location with the
     *  same direction and color as itself.
     * 
     * @param loc the space to clone itself into
     */
    public void clone(Location loc)
    {
        Grid gr = getGrid();
        int rand = (int) (Math.random()*101);
        
        if(rand < breedProb && gr.isValid(loc))
        {
            Lovebug bug = new Lovebug(getColor(), breedProb);
            bug.putSelfInGrid(getGrid(), loc);
            bug.setDirection(getDirection());
        }
    }
    
    /**
     * Acts like a normal Bug before checking if it can breed in a
     *  location behind and to the left or behind and to the right.
     */
    public void act()
    {
        super.act();
        
        Location loc = getLocation();
        int dir = getDirection();
        
        Location behindAndLeft = loc.getAdjacentLocation(dir+Location.LEFT+Location.HALF_LEFT);
        Location behindAndRight = loc.getAdjacentLocation(dir+Location.RIGHT+Location.HALF_RIGHT);
        
        if(canClone(behindAndLeft))
        {
            clone(behindAndLeft);
        }

        if(canClone(behindAndRight))
        {
            clone(behindAndRight);
        }
    }
}
