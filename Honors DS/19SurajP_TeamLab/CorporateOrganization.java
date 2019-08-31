
/**
 * CorporateOrganization is a team that manages a business.
 * 
 * @author Alex Yu, Richard Wang
 * @version November 07, 2017
 */
public class CorporateOrganization extends Team
{
    /**
     * Constructs a CorporateOrganization with a given size.
     * @param size	the number of members in the team
     */
    public CorporateOrganization(int size)
    {
        super(size);
    }

    /**
     * Calls a meeting for the team.
     */
    public void meet()
    {
        System.out.println("Corporate meeting occurring");
    }

    /**
     * Ends the current team’s meeting.
     */
    public void complete()
    {
        System.out.println("Corporate meeting over");
    }
}
