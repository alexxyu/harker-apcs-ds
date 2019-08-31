import info.gridworld.actor.Bug;

/**
 * SpiralBug moves in a similar pattern as CircleBug but moves in a
 *  spiral.
 * 
 * @author Alex Yu
 * @version September 16, 2016
 */
public class SpiralBug extends Bug
{
    private int steps;
    private int sideLength;

    /**
     * Constructs a SpiralBug that starts a spiral pattern with a given length
     * 
     * @param length the side length
     */
    public SpiralBug(int length)
    {
        steps = 0;
        sideLength = length;
    }

    /**
     * Moves to the next location of the spiral and increases side length at the
     *  end of every side.
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
            turn();
            steps = 0;
            sideLength++;
        }
    }
}
