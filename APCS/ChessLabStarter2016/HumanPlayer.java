import java.awt.*;
import java.util.*;

/**
 * HumanPlayer is a player that is controlled by the user instead of the
 * computer.
 * 
 * @author Alex Yu
 * @version May 3, 2017
 */
public class HumanPlayer extends Player
{
    private BoardDisplay display;
    
    /**
     * Constructs a HumanPlayer.
     * 
     * @param bd    the display of the chess board
     * @param b     the chess board
     * @param name  the name of the player
     * @param c     the player's color (i.e. black or white)
     */
    public HumanPlayer(BoardDisplay bd, Board b, String name, Color c)
    {
        super(b, name, c);
        display = bd;
    }
    
    /**
     * Asks for and retrieves the player's next move.
     * 
     * @return  the player's next move
     */
    public Move nextMove()
    {
        Move move = display.selectMove();
        ArrayList<Move> validMoves = super.getBoard().allMoves(super.getColor());
        for(Move m: validMoves)
            if(m.equals(move))
                return move;
                
        return nextMove();
    }
}
