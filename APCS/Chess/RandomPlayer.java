import java.awt.*;
import java.util.*;

/**
 * RandomPlayer is controlled solely by the computer and always
 * makes a random but valid move.
 * 
 * @author Alex Yu
 * @version May 3, 2017
 */
public class RandomPlayer extends Player
{
    /**
     * Constructs a RandomPlayer.
     * 
     * @param b the chess board
     * @param n the name of the player
     * @param c the player's color (i.e. black or white)
     */
    public RandomPlayer(Board b, String n, Color c)
    {
        super(b, n, c);
    }
    
    /**
     * Retrieves a random but valid move.
     * 
     * @return a random move
     */
    public Move nextMove()
    {
        Board board = super.getBoard();
        ArrayList<Move> moves = board.allMoves(super.getColor());
        
        int rand = (int) (Math.random() * moves.size());
        return moves.get(rand);
    }
}
