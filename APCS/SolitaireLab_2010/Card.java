/**
 * Card stores info for each card in a deck.
 * 
 * @author Alex Yu 
 * @version November 1, 2017
 */
public class Card
{
    private int rank;
    private String suit;
    private boolean isFaceUp;

    /**
     * Constructs a Card object with given rank and suit.
     * 
     * @param r the rank of the card
     * @param s the suit of the card
     */
    public Card(int r, String s)
    {
        rank = r;
        suit = s;
        isFaceUp = false;
    }

    /**
     * Returns the rank of the card.
     * 
     * @return the rank
     */
    public int getRank()
    {
        return rank;
    }
    
    /**
     * Returns the suit of the card.
     * 
     * @return the suit
     */
    public String getSuit()
    {
        return suit;
    }
    
    /**
     * Checks if the card is red-suited.
     * 
     * @return true if the card is red-suited; else,
     *         false
     */
    public boolean isRed()
    {
        return suit.equals("d") || suit.equals("h");
    }
    
    /**
     * Checks if the card is face-up.
     * 
     * @return true if the card is face-up; else,
     *         false
     */
    public boolean isFaceUp()
    {
        return isFaceUp;
    }
    
    /**
     * Turns the card face-up.
     */
    public void turnUp()
    {
        isFaceUp = true;
    }
    
    /**
     * Turns the card face-down.
     */
    public void turnDown()
    {
        isFaceUp = false;
    }
    
    /**
     * Returns a formatted string of the card's image file and directory.
     * 
     * @reutrn a formatted string for the card's image
     */
    public String getFileName()
    {
        if(!isFaceUp) return "cards/back.gif";
        
        String rankString;
        if( rank == 1 ) rankString = "a";
        else if( rank == 10 ) rankString = "t";
        else if( rank == 11 ) rankString = "j";
        else if( rank == 12 ) rankString = "q";
        else if( rank == 13 ) rankString = "k";
        else rankString = Integer.toString(rank);
        return "cards/" + rankString + suit + ".gif";
    }
}
