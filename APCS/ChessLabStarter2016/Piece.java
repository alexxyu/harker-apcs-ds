/*
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2002-2006 College Entrance Examination Board
 * (http://www.collegeboard.com)
 */

import java.awt.*;
import java.util.*;

/**
 * A Piece is an object that can be placed on the chess board. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 * 
 * @author Alyce Brady
 * @author APCS Development Committee
 * @author Cay Horstmann
 * @author Anu Datar
 * @author Alex Yu
 * @version May 6, 2017
 */
public abstract class Piece
{
    
    private Board board;       // the board this piece is on
    private Location origin;   // the original location of this piece
    private Location location; // the location of this piece 
                               // on the board
    private Color color;       // the color of the piece
    private int value;         // the approximate value of this
                               // piece in a game of chess
    private String imageFileName; // the file used to display 
                               // this piece         
    private boolean firstTime; // is it being created                         
                               
    /**
     * Constructs a new Piece with a color, value and 
     * image information.
     * 
     * @param col color of the piece
     * @param fileName the image file name for piece
     * @param val the weight for the piece
     */
    public Piece(Color col, String fileName, int val)
    {
        color = col;
        imageFileName = fileName;
        value = val;
        firstTime = true;
    }

    /**
     * Returns the board this piece is on.
     * 
     * @return the board this piece belongs to
     */
    public Board getBoard()
    {
        return board;
    }

    /**
     * Returns the location of this piece on the board.
     * 
     * @return the location this piece belongs to on the board
     */
    public Location getLocation()
    {
        return location;
    }

    /**
     * Returns the color of this piece.
     * 
     * @return the color this piece belongs to on the board
     */
    public Color getColor()
    {
        return color;
    }

    /**
     * Returns the name of the file used to display this piece.
     * 
     * @return the file name for this piece
     */
    public String getImageFileName()
    {
        return imageFileName;
    }

    /**
     * Returns a number representing the relative value of this 
     * piece.
     * 
     * @return the value of this piece
     * 
     */
    public int getValue()
    {
        return value;
    }

    /**
     * Returns the original location on the board where this piece was.
     * 
     * @return the original location
     */
    public Location getOrigin()
    {
        return origin;
    }
    
    /**
     * Puts this piece into a board. If there is another piece at the 
     * given location, it is removed. <br />
     * 
     * @precondition (1) this piece is not contained in a grid 
     *               (2) <code>loc</code> is valid in <code>gr</code>
     * 
     * @param brd the board into which this piece should be placed
     * @param loc the location into which the piece should be placed
     */
    public void putSelfInGrid(Board brd, Location loc)
    {
        if (board != null)
            throw new IllegalStateException(
                "This piece is already contained in a board.");

        Piece piece = brd.get(loc);
        if (piece != null)
            piece.removeSelfFromGrid();
        brd.put(loc, this);
        board = brd;
        location = loc;
        
        if(firstTime)
        {
            origin = new Location( location.getRow(), location.getCol() );
            firstTime = false;
        }
    }

    /**
     * Removes this piece from its board. 
     * 
     * @precondition this piece is contained in a board
     * 
     */
    public void removeSelfFromGrid()
    {
        if (board == null)
            throw new IllegalStateException(
                "This piece is not contained in a board.");
        if (board.get(location) != this)
            throw new IllegalStateException(
                "The board contains a different piece at location "
                + location + ".");

        board.remove(location);
        board = null;
        location = null;
    }

    /**
     * Moves this piece to a new location. If there is another piece 
     * at the given location, it is removed.
     * 
     * @precondition (1) this piece is contained in a grid 
     *               (2) <code>newLocation</code> is valid in the 
     *                   grid of this piece
     * 
     * @param newLocation the new location
     */
    public void moveTo(Location newLocation)
    {
        if (board == null)
            throw new IllegalStateException(
                "This piece is not on a board.");
        if (board.get(location) != this)
            throw new IllegalStateException(
                "The board contains a different piece at location "
                + location + ".");
        if (!board.isValid(newLocation))
            throw new IllegalArgumentException(
                "Location " + newLocation + " is not valid.");

        if (newLocation.equals(location))
            return;
        board.remove(location);
        Piece other = board.get(newLocation);
        if (other != null)
            other.removeSelfFromGrid();
        location = newLocation;
        board.put(location, this);
    }
    
    /**
     * Checks if the given location is in the board and if the piece
     * can move there.
     * 
     * @param dest  the location to check
     * @return true if the given location is a valid destination for the piece; otherwise,
     *         false 
     */
    public boolean isValidDestination(Location dest)
    {
        return board.isValid(dest) && (board.get(dest) == null || 
                                       board.get(dest).getColor() != color);
    }
    
    /**
     * Returns the valid destinations for this piece.
     * 
     * @return an ArrayList of all valid destinations
     */
    public abstract ArrayList<Location> destinations();
    
    /**
     * Adds all valid destinations directly in front of this piece to 
     * an ArrayList of Locations.
     * 
     * @param dests the ArrayList to fill with valid destinations
     * @param direction the current direction the piece is facing
     */
    public void sweep(ArrayList<Location> dests, int direction)
    {
        Location temp = location.getAdjacentLocation(direction);
        boolean hasSeenEnemy = false;
        while( board.isValid(temp) && !hasSeenEnemy &&
              (board.get(temp) == null || board.get(temp).getColor() != color) )
        {
            if(board.get(temp) instanceof Piece)
                hasSeenEnemy = true;
            dests.add(temp);
            temp = temp.getAdjacentLocation(direction);
        }
    }
}