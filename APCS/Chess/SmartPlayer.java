import java.awt.*;
import java.util.*;

/**
 * SmartPlayer evaluates possible moves of both itself and its
 * opponent in order to make the best move.
 * 
 * @author Alex Yu
 * @version May 3, 2017
 */
public class SmartPlayer extends Player
{
    /**
     * Constructs a SmartPlayer.
     * 
     * @param b the chess board that it is in
     * @param n the name of the SmartPlayer
     * @param c the SmartPlayer's color (i.e. black or white)
     */
    public SmartPlayer(Board b, String n, Color c)
    {
        super(b, n ,c);
    }
    
    /**
     * Retrieves the SmartPlayer's chosen move.
     * 
     * @return the move for the SmartPlayer to make
     */
    public Move nextMove()
    {
        Board b = super.getBoard();
        ArrayList<Move> possMoves = b.allMoves(super.getColor());
        
        int highestScore = Integer.MIN_VALUE;
        int valueOMR;
        Move bestMove = null;
        for(Move m: possMoves)
        {
            b.executeMove(m);
            valueOMR = valueOfMeanestResponse(2);
            if(valueOMR >= highestScore)
            {
                highestScore = valueOMR;
                bestMove = m;
            }
            b.undoMove(m);
        }
        
        return bestMove;
    }
    
    /**
     * Retrieves the SmartPlayer's current score in the game.
     * 
     * @return the SmartPlayer's score
     */
    private int score()
    {
        Board b = super.getBoard();
        ArrayList<Location> occupiedLocs = b.getOccupiedLocations();
        
        int score = 0;
        Color color = super.getColor();
        for(Location location: occupiedLocs)
        {
            Piece p = b.get(location);
            if(p.getColor().equals(color))
                score += p.getValue();
                
            else
                score -= p.getValue();
        }
        
        return score;
    }
    
    /**
     * Calculates the score value of each of the opponent's possible moves
     * and returns the smallest of those values.
     * 
     * @param movesAhead    the number of moves to look ahead, >= 0
     * 
     * @return the score value of the opponent's worst move
     */
    private int valueOfMeanestResponse(int movesAhead)
    {
        if(movesAhead == 0)
            return score();
        
        Board b = super.getBoard();

        Color thisColor = super.getColor();
        Color oppColor;
        if(thisColor.equals(Color.WHITE))
            oppColor = Color.BLACK;
            
        else
            oppColor = Color.WHITE;
            
        ArrayList<Move> possMoves = b.allMoves(oppColor);
        int lowestScore = Integer.MAX_VALUE; // own score will decrease with meaner response
        for(Move m: possMoves)
        {
            b.executeMove(m);
            if(valueOfBestMove(movesAhead-1) <= lowestScore)
                lowestScore = valueOfBestMove(movesAhead-1);
            b.undoMove(m);
        }
        
        return lowestScore;
    }
    
    /**
     * Calculates the score value of each of SmartPlayer's possible moves
     * and returns the greatest of those values.
     * 
     * @param movesAhead    the number of moves to look ahead, >= 0
     * 
     * @return the score value of SmartPlayer's best move
     */
    private int valueOfBestMove(int movesAhead)
    {
        if(movesAhead == 0)
            return score();
        
        Board b = super.getBoard();
        ArrayList<Move> possMoves = b.allMoves(super.getColor());
        int greatestScore = Integer.MIN_VALUE;
        for(Move m: possMoves)
        {
            b.executeMove(m);
            if(valueOfMeanestResponse(movesAhead-1) >= greatestScore)
                greatestScore = valueOfMeanestResponse(movesAhead-1);
            b.undoMove(m);
        }
        
        return greatestScore;
    }
}
