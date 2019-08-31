/*
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 */

import java.awt.*;
import java.util.*;

/**
 * Board represesents a rectangular game board, containing Piece objects.
 * 
 * @author Cay Horstmann
 * @author Anu Datar
 * @author Alex Yu
 * @version May 9, 2017
 */
public class Board extends BoundedGrid<Piece>
{
    /**
     * Constructs a new Board with the given dimensions.
     */
    public Board()
    {
        super(8, 8);
    }

    /**
     * Undo the previous move.
     * 
     * @precondition   move has already been made on the board
     * @postcondition  piece has moved back to its source, 
     *                 and any captured piece is returned to its 
     *                 location
     * @param move the last move made 
     */
    public void undoMove(Move move)
    {
        Piece piece = move.getPiece();
        Location source = move.getSource();
        Location dest = move.getDestination();
        Piece victim = move.getVictim();

        piece.moveTo(source);
        
        if (victim != null)
            victim.putSelfInGrid(piece.getBoard(), dest);
    }
    
    /**
     * Retrieves all possible moves that the given color player
     * can make.
     * 
     * @param color the color player to check
     * 
     * @return all possible moves that the player can make
     */
    public ArrayList<Move> allMoves(Color color)
    {
        ArrayList<Move> moves = new ArrayList<Move>();
        ArrayList<Location> occupiedLocs = getOccupiedLocations();
        Piece p;
        for(Location loc: occupiedLocs)
        {
            p = get(loc);
            if(p.getColor().equals(color))
            {
                ArrayList<Location> possibleDests = p.destinations();
                for(Location dest: possibleDests)
                {
                    moves.add(new Move(p, dest));
                }
            }
        }
        
        return moves;
    }
    
    /**
     * Executes the given move by moving the piece to the correct location.
     * 
     * @param move  the given move to make
     */
    public void executeMove(Move move)
    {
        Piece p = move.getPiece();
        Location dest = move.getDestination();
        p.moveTo(dest);
    }
    
    /**
     * Checks to see if any pawns of a certain color are in position to be promoted.
     * 
     * @param player    the player who just made a move
     * @return true     if there are any pawns to be promoted; otherwise,
     *         false
     */
    public boolean checkPromotions(Player player)
    {
        ArrayList<Location> occupiedLocs = getOccupiedLocations();
        Color color = player.getColor();
        Piece piece;
        for(Location loc: occupiedLocs)
        {
            piece = (Piece) get(loc);
            if(color.equals(Color.WHITE))
            {
                if(piece instanceof Pawn && piece.getColor().equals(color) 
                   && loc.getRow() == 0)
                {
                    piece.removeSelfFromGrid();
                    Piece queen = new Queen(Color.WHITE, "white_queen.gif");
                    queen.putSelfInGrid(this, loc);
                    return true;
                }
            }
            else
            {
                if(piece instanceof Pawn && piece.getColor().equals(color) 
                   && loc.getRow() == 7)
                {
                    piece.removeSelfFromGrid();
                    Piece queen = new Queen(Color.BLACK, "black_queen.gif");
                    queen.putSelfInGrid(this, loc);
                    return true;
                }
            }
        }
        
        return false;
    }
}