import java.awt.*;
import java.util.*;
/**
 * A Bishop is a chess piece that can only move diagonally but as many spaces
 * as possible.
 * 
 * @author Alex Yu 
 * @version May 3, 2017
 */
public class Bishop extends Piece
{
    /**
     * Constructs a Bishop piece.
     * 
     * @param col   the color of the Bishop (i.e. black or white)
     * @param fileName the name of the image for this Bishop
     */
    public Bishop(Color col, String fileName)
    {
        super(col, fileName, 3);
    }

    /**
     * Retrieves all valid locations that the Bishop can move to.
     * 
     * @return an ArrayList of valid destination locations
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> dests = new ArrayList<Location>();
        super.sweep(dests, Location.NORTHEAST);
        super.sweep(dests, Location.NORTHWEST);
        super.sweep(dests, Location.SOUTHEAST);
        super.sweep(dests, Location.SOUTHWEST);
        
        return dests;
    }
}
