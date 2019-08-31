import info.gridworld.actor.Bug;

/**
 * DancingBug moves in a "dance routine" by moving the appropriate
 * number of steps before turning a specified amount. Then it repeats
 * the routine indefinitely.
 * 
 * @author Alex Yu
 * @version September 17, 2016
 */
public class DancingBug extends Bug
{
    private int steps;
    private int turns;
    private int sideLength;
    
    private int[] danceRoutine;
    private int routineCounter;

    /**
     * Constructs a DancingBug that moves a given amount of steps
     * 
     * @param length the number of steps to take before turning
     * @param routine the dance routine
     */
    public DancingBug(int length, int[] routine)
    {
        steps = 0;
        turns = 0;
        sideLength = length;
        routineCounter = 0;
       
        danceRoutine = routine;
    }

    /**
     * Moves and turns using the specified routine and then repeats
     */
    public void act()
    {
        if(steps < sideLength && canMove())
        {
            move();
            steps++;
        }
        
        else if(routineCounter < danceRoutine.length && turns < danceRoutine[routineCounter])
        {
            turn();
            turns++;
        }
        
        else
        {   
            routineCounter++;
            steps = 0;
            turns = 0;
            
            if(routineCounter >= danceRoutine.length)
            {
                routineCounter = 0;
            }
        }
    }
}
