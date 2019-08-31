import java.awt.*;
import java.util.*;
/**
 * A Queen piece can move as many possible spaces
 * in any direction.
 * 
 * @author Alex Yu
 * @version May 3, 2017
 */
public class Queen extends Piece
{
    /**
     * Constructs a Queen piece with a given color.
     * 
     * @param col     the color of the Queen (i.e. black or white)
     * @param fileName the name of the image for this Queen
     */
    public Queen(Color col, String fileName)
    {
        super(col, fileName, 9);
    }

    /**
     * Retrieves all valid locations that the Queen can move to.
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
        super.sweep(dests, Location.NORTHEAST);
        super.sweep(dests, Location.NORTHWEST);
        super.sweep(dests, Location.SOUTHEAST);
        super.sweep(dests, Location.SOUTHWEST);
        
        return dests;
    }
}
