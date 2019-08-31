import java.awt.Color;

/**
 * EnemyPlayer is the opposing player that the user can play
 * with over the network.
 * 
 * @author Alex Yu
 * @version May 17, 2017
 *
 */
public class EnemyPlayer extends Player
{
	
	/**
     * Constructs a EnemyPlayer.
     * 
     * @param bd    the display of the chess board
     * @param b     the chess board
     * @param name  the name of the player
     * @param c     the player's color (i.e. black or white)
     */
    public EnemyPlayer(Board b, String name, Color c)
    {
        super(b, name, c);
    }

	public Move nextMove() {
		
		return null;
	}

}
