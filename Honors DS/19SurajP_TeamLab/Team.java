/**
 * Team provides a basic outline for what a team can do.
 * 
 * @author Bennett Liu
 * @version 11/7/2017
 */
public abstract class Team implements Activities
{
    private int size;
    
    /**
     * Constructs Team with given size.
     * 
     * @param s the number of members in Team.
     */
    public Team(int s) 
    {
        size = s;
    }
    
    /**
     * Meets.
     */
    public abstract void meet();
    
    /**
     * Completes.
     */
    public abstract void complete();
}
