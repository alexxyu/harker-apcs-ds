import java.awt.*;
import java.util.*;
/**
 * A Knight is a chess piece that moves in an L-shape fashion.
 * 
 * @author Alex Yu 
 * @version May 3, 2017
 */
public class Knight extends Piece
{
    /**
     * Constructs a Knight piece.
     * 
     * @param col   the color of the knight (i.e. black or white)
     * @param fileNwame the name of the image for this Knight
     */
    public Knight(Color col, String fileName)
    {
        super(col, fileName, 3);
    }

    /**
     * Retrieves all valid locations that the Knight can move to.
     * 
     * @return an ArrayList of valid destination locations
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> dests = new ArrayList<Location>();
        Location loc = super.getLocation();
        
        Location temp = loc.getAdjacentLocation(Location.NORTH).getAdjacentLocation(Location.NORTH)
                   .getAdjacentLocation(Location.WEST);
        if(super.isValidDestination(temp))
            dests.add(temp);
        
        temp = loc.getAdjacentLocation(Location.NORTH).getAdjacentLocation(Location.NORTH)
                   .getAdjacentLocation(Location.EAST);
        if(super.isValidDestination(temp))
            dests.add(temp);

        temp = loc.getAdjacentLocation(Location.WEST).getAdjacentLocation(Location.WEST)
                   .getAdjacentLocation(Location.NORTH);
        if(super.isValidDestination(temp))
            dests.add(temp);    
            
        temp = loc.getAdjacentLocation(Location.WEST).getAdjacentLocation(Location.WEST)
                   .getAdjacentLocation(Location.SOUTH);
        if(super.isValidDestination(temp))
            dests.add(temp);
            
        temp = loc.getAdjacentLocation(Location.EAST).getAdjacentLocation(Location.EAST)
                   .getAdjacentLocation(Location.NORTH);
        if(super.isValidDestination(temp))
            dests.add(temp);    
        
        temp = loc.getAdjacentLocation(Location.EAST).getAdjacentLocation(Location.EAST)
                   .getAdjacentLocation(Location.SOUTH);
        if(super.isValidDestination(temp))
            dests.add(temp);    
            
        temp = loc.getAdjacentLocation(Location.SOUTH).getAdjacentLocation(Location.SOUTH)
                   .getAdjacentLocation(Location.EAST);
        if(super.isValidDestination(temp))
            dests.add(temp);    
            
        temp = loc.getAdjacentLocation(Location.SOUTH).getAdjacentLocation(Location.SOUTH)
                   .getAdjacentLocation(Location.WEST);
        if(super.isValidDestination(temp))
            dests.add(temp);
            
        return dests;
    }
}
