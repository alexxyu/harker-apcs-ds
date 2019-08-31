import info.gridworld.actor.Bug;
import info.gridworld.grid.Location; 

/**
 * ZBug starts facing east and moves in a z pattern before stopping.
 * 
 * @author Alex Yu
 * @version September 17, 2016
 */
public class ZBug extends Bug
{
    private int steps;
    private int sideLength;
    private int counter;

    /**
     * Constructs a ZBug that moves in a Z pattern with given side length
     * 
     * @param length the side length of each side of Z
     */
    public ZBug(int length)
    {
        steps = 0;
        counter = 0;
        sideLength = length;
        setDirection(Location.EAST);
    }

    /**
     * Moves to the next side of the Z and stops after finishing
     *  the pattern once
     */
    public void act()
    {
        if (steps < sideLength && canMove())
        {
            move();
            steps++;
        }
        else
        {
            if(counter == 0)
            {
                setDirection(Location.SOUTHWEST);
                counter++;
                steps = 0;
            }
            else if(counter == 1)
            {
                setDirection(Location.EAST);
                counter++;
                steps = 0;
            }

        }
    }
}
