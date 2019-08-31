import info.gridworld.actor.Bug;

/**
 * CircleBug moves in a circular, or octagonal, pattern
 * 
 * @author Alex Yu
 * @version September 16, 2016
 */
public class CircleBug extends Bug
{
    private int steps;
    private int sideLength;

    /**
     * Creates a CircleBug that travels in an octagon with given side length
     * 
     * @param length the side length of the octagon
     */
    public CircleBug(int length)
    {
        steps = 0;
        sideLength = length;
    }

    /**
     * Moves to the next location of the octagon.
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
            turn();
            steps = 0;
        }
    }
}
