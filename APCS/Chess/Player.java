import java.awt.*;

/**
 * Abstract class Player focuses on its board, name, and color.
 * 
 * @author Alex Yu
 * @version May 3, 2017
 */
public abstract class Player
{
    private Board board;
    private String name;
    private Color color;

    /**
     * Constructs a player with the given info.
     * 
     * @param b the chess board
     * @param n the name of the player
     * @param c the player's color (i.e. black or white)
     */
    public Player(Board b, String n, Color c)
    {
        board = b;
        name = n;
        color = c;
    }
    
    /**
     * Retrieves the player's next move.
     * 
     * @return the player's chosen move
     */
    public abstract Move nextMove();
    
    /**
     * Retrieves the chess board that the Player is in.
     * 
     * @return the chess board
     */
    public Board getBoard()
    {
        return board;
    }
    
    /**
     * Retrieves the name of the Player.
     * 
     * @return the Player's name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Retrieves the color of the Player.
     * 
     * @return the Player's color
     */
    public Color getColor()
    {
        return color;
    }
}
