/**
 * TeamMember is a Member that can talk.
 * 
 * @author Nemo Yang
 * @version 11/9/17
 */
public class TeamMember implements Member
{
    private String name;
    
    /**
     * Constructs a TeamMember.
     * 
     * @param n the TeamMember's name
     */
    public TeamMember(String n)
    {
        name = n;
    }
    
    /**
     * Returns the name.
     * 
     * @return the name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Talks.
     */
    public void talk()
    {
        System.out.println(name + " is talking");
    }
}
