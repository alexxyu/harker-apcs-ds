
/**
 * SportsTeam is a team that manages a sports organization.
 * 
 * @author Alex Yu, Richard Wang
 * @version November 07, 2017
 */
public class SportsTeam extends Team
{
    /**
     * Constructs a SportsTeam constructor with a given size.
     * @param size	the number of members in the team
     */
    public SportsTeam(int size)
    {
        super(size);
    }

    /**
     * Calls a meeting for the team.
     */
    public void meet()
    {
        System.out.println("Sports meet occuring");
    }

    /**
     * Ends the team’s current meeting.
     */
    public void complete()
    {
        System.out.println("Sports meet completed");
    }

}
