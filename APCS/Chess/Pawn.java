import java.awt.*;
import java.util.*;
/**
 * A Pawn piece can move forward one space or two spaces if
 * it has not moved yet.
 * 
 * @author Alex Yu
 * @version May 6, 2017
 */
public class Pawn extends Piece
{
    /**
     * Constructs a Pawn piece.
     * 
     * @param col   the color of the Pawn (i.e. black or white)
     * @param fileName  the name of the image for this Pawn
     */
    public Pawn(Color col, String fileName)
    {
        super(col, fileName, 1);
    }

    /**
     * Retrieves all valid locations that the Pawn can move to.
     * 
     * @return an ArrayList of valid destination locations
     */
    public ArrayList<Location> destinations()
    {
        ArrayList<Location> dests = new ArrayList<Location>();
        Location currentLoc = getLocation();
        
        Location oneStep;
        Board b = getBoard();
        Color c = getColor();
        if(c.equals(Color.WHITE))
            oneStep = currentLoc.getAdjacentLocation(Location.NORTH);
        else
            oneStep = currentLoc.getAdjacentLocation(Location.SOUTH);
        if(isValidDestination(oneStep) && ! (b.get(oneStep) instanceof Piece))
            dests.add(oneStep);
           
        Location twoSteps;
        if(!hasMoved())
        {
            // Pawn can advance 2 spaces on its first move
            
            if( getColor().equals(Color.WHITE) )
                twoSteps = currentLoc.getAdjacentLocation(Location.NORTH).
                        getAdjacentLocation(Location.NORTH);
            else
                twoSteps = currentLoc.getAdjacentLocation(Location.SOUTH).
                        getAdjacentLocation(Location.SOUTH);
            if(isValidDestination(twoSteps) && isValidDestination(oneStep) &&
               ! (b.get(twoSteps) instanceof Piece))
                dests.add(twoSteps);
        }

        // Pawn can capture pieces diagonally
        if(c.equals(Color.WHITE))
            oneStep = currentLoc.getAdjacentLocation(Location.NORTHWEST);
        else
            oneStep = currentLoc.getAdjacentLocation(Location.SOUTHWEST);
        if(isValidDestination(oneStep) && (b.get(oneStep) instanceof Piece))
            dests.add(oneStep);
        
        if(c.equals(Color.WHITE))
            oneStep = currentLoc.getAdjacentLocation(Location.NORTHEAST);
        else
            oneStep = currentLoc.getAdjacentLocation(Location.SOUTHEAST);
        if(isValidDestination(oneStep) && (b.get(oneStep) instanceof Piece))
            dests.add(oneStep);    
            
        return dests;
    }
    
    /**
     * Checks to see if this Pawn has already moved before (i.e. whether
     * it has moved from its original location)
     * 
     * @return whether the Pawn has moved before
     */
    private boolean hasMoved()
    {
        if( getOrigin().compareTo( getLocation() ) == 0 )
            return false;
            
        return true;
    }
}
