import java.util.Scanner;

/**
 * GuessingGame guides a user to a randomly picked number
 * 
 * @author Susan King
 * @author Alex Yu 
 * @version August 30, 2016
 */
public class GuessingGame
{
    // instance variables 
    private int rand;  // random number

    /**
     * Constructor for objects of class GuessingGame.
     */
    public GuessingGame()
    {
        // initialise instance variables
        rand = 0;
    }

    /**
     * Generates a random number between 1 and 100, inclusive.
     * 
     * @return a random number between 1 and 100, inclusive 
     */
    public int getRandomNumber( )
    {
    	rand = (int) (Math.random()*100+1);
        return rand;
    }

    /**
     * Asks the user for a guess and returns the user's reply.
     * 
     * @return the user's guess
     */
    public int getUserGuess( )
    {
        System.out.print("Guess a number: ");
        Scanner in = new Scanner (System.in);
        int guess = in.nextInt( );
        return guess;
    }

    /**
     * Tells the user if their guess is too high, too low 
     * or just right.
     * 
     * @param guess    the user's guess
     */
    public void giveUserFeedback(int guess)
    {

    	if(guess>rand)
    	{
    		System.out.println("Too high!");
    	}
    	
    	else if(guess<rand)
    	{
    		System.out.println("Too low!");
    	}
    	
    	else 
    	{
    	    System.out.println("Correct!");
    	}
    	
    }

    /**
     * Determines if the user guessed correctly.
     * 
     * @param userGuess the user's guesstimate
     * 
     * @return true if the user is correct; otherwise, false
     */
    public boolean isWinner(int userGuess)
    {
    	return userGuess == rand;
    }

    /**
     * Oversees the guessing game.
     * 
     * @param args information read from the command line
     */
    public static void main(String [ ] args)
    {
        GuessingGame bob = new GuessingGame( );
        bob.getRandomNumber( );
        int est = bob.getUserGuess( );
        bob.giveUserFeedback(est);
        while ( ! bob.isWinner(est) )
        {
            est = bob.getUserGuess( );
            bob.giveUserFeedback(est);
        }
    }

}
