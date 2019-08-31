import java.util.*;
import java.awt.*;

/**
 * A Rook piece can move as many possible spaces directly forwards,
 * backwards, or to the sides.
 * 
 * @author Alex Yu
 * @version May 3, 2017
 */
public class Rook extends Piece
{
    /**
     * Constructs a Rook piece.
     * 
     * @param col   the color of the Rook (i.e. black or white)
     * @param fileName  the name of the image for this Rook
     */
    public Rook(Color col, String fileName)
    {
        super(col, fileName, 1000);
    }

    /**
     * Retrieves all valid locations that the Rook can move to.
     * 
     * @return an ArrayList of valid destination locations
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> dests = new ArrayList<Location>();
        super.sweep(dests, Location.NORTH);
        super.sweep(dests, Location.SOUTH);
        super.sweep(dests, Location.EAST);
        super.sweep(dests, Location.WEST);
        
        return dests;
    }
}
