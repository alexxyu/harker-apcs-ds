import java.util.*;

/**
 * TeamTester tests the code.
 * 
 * @author Suraj Pakala, Alex Yu
 * @version 11/9/17
 */
public class TeamTester
{
    /**
     * Main method
     * 
     * @param args  info from command line.
     */
    public static void main(String[] args)
    {
        Team sports = new SportsTeam(15);
        Team corp = new CorporateOrganization(100);
        sports.meet();
        sports.complete();
        corp.meet();
        corp.complete();
        Activities sports2 = new SportsTeam(15);
        Activities corp2 = new CorporateOrganization(100);
        sports2.meet();
        sports2.complete();
        corp2.meet();
        corp2.complete();
        SportsTeam sports3 = new SportsTeam(15);
        CorporateOrganization corp3 = new CorporateOrganization(100);
        sports3.meet();
        sports3.complete();
        corp3.meet();
        corp3.complete();
        
        Activities[] teams = new Activities[]{sports, corp, sports2, corp2, sports3, corp3};
        for(int i = 0; i < 100; i++)
        {
            if( ! (teams[random(6)] instanceof Team) )
                System.out.println("You lose!");
        }
        
        int teamSize = random(10);
        Member[] teamMembers = new Member[teamSize];
        for (int i = 0; i < teamSize; i++)
        {
            teamMembers[i] = new TeamMember("Member #"+ (i+1));
        }
        
        for(int i = 0; i < teamSize; i++)
        {
            teamMembers[i].talk();
        }
        
        if (! (teamMembers[random(teamSize)] instanceof Member))
        {
            System.out.println("You lose!");
        }
        
        System.out.println("You win!");
    }

    /**
     * Produces a random number between 0 (inclusive) and n (noninclusive).
     * 
     * @return a random number in given range
     */
    private static int random(int n)
    {
        return (int)(Math.random() * n);
    }
}