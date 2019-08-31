import java.awt.*;
import java.util.*;

/**
 * A King chess piece can move one adjacent space or one
 * diagonal space.
 * 
 * @author Alex Yu 
 * @version May 3, 2017
 */
public class King extends Piece
{
    /**
     * Constructs a King piece.
     * 
     * @param col   the color of the King (i.e. black or white)
     * @param fileName the name of the image for this King
     */
    public King(Color col, String fileName)
    {
        super(col, fileName, 1000);
    }
    
    /**
     * Retrieves all valid locations that the King can move to.
     * 
     * @return an ArrayList of valid destination locations
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> dests = new ArrayList<Location>();
        Location currentLoc = super.getLocation();
        
        Location temp = currentLoc.getAdjacentLocation(Location.NORTH);
        if(super.isValidDestination(temp))
            dests.add(temp);
        
        temp = currentLoc.getAdjacentLocation(Location.SOUTH);
        if(super.isValidDestination(temp))
            dests.add(temp);

        temp = currentLoc.getAdjacentLocation(Location.WEST);
        if(super.isValidDestination(temp))
            dests.add(temp);
        
        temp = currentLoc.getAdjacentLocation(Location.EAST);
        if(super.isValidDestination(temp))
            dests.add(temp);
        
        temp = currentLoc.getAdjacentLocation(Location.NORTHWEST);
        if(super.isValidDestination(temp))
            dests.add(temp);
        
        temp = currentLoc.getAdjacentLocation(Location.NORTHEAST);
        if(super.isValidDestination(temp))
            dests.add(temp);
        
        temp = currentLoc.getAdjacentLocation(Location.SOUTHWEST);
        if(super.isValidDestination(temp))
            dests.add(temp);
        
        temp = currentLoc.getAdjacentLocation(Location.SOUTHEAST);
        if(super.isValidDestination(temp))
            dests.add(temp);
        
        return dests;
    }
}
