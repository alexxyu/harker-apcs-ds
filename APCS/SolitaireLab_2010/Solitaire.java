import java.util.*;

/**
 * Solitaire plays a game of solitaire.
 * 
 * @author Alex Yu
 * @version November 2, 2017
 */
public class Solitaire
{
    /**
     * Main method
     * 
     * @param args  info from command line
     */
    public static void main(String[] args)
    {
        new Solitaire();
    }

    private Stack<Card> stock;
    private Stack<Card> waste;
    private Stack<Card>[] foundations;
    private Stack<Card>[] piles;
    private SolitaireDisplay display;
    
    private int cardsToMove;

    /**
     * Constructs a Solitaire object.
     */
    public Solitaire()
    {
        foundations = new Stack[4];
        piles = new Stack[7];

        //INSERT CODE HERE
        for(int i=0; i<foundations.length; i++)
            foundations[i] = new Stack<Card>();
            
        for(int i=0; i<piles.length; i++)
            piles[i] = new Stack<Card>();
        
        stock = new Stack<Card>();
        waste = new Stack<Card>();
        createStock();
        deal();
                
        display = new SolitaireDisplay(this);
        
        cardsToMove = display.getCardsToMove();
    }
    
    /**
     * Creates the stock pile with cards in a random order.
     */
    private void createStock()
    {
        ArrayList<Card> cards = new ArrayList<Card>();
        String[] suits = new String[]{ "d", "h", "c", "s" };
        
        for(int suitIndex = 0; suitIndex < suits.length; suitIndex++)
            for(int rankIndex = 1; rankIndex <= 13; rankIndex++)
                cards.add( new Card(rankIndex, suits[suitIndex]) );
        
        int deckSize = cards.size();
        for(int i = 0; i < deckSize; i++)
        {
            stock.push( cards.remove( (int) (Math.random() * cards.size()) ) );
        }
    }
    
    /**
     * Deals cards from the stock to the piles.
     */
    private void deal()
    {
        for(int pile = 0; pile < piles.length; pile++)
        {
            for(int numCards = 0; numCards <= pile; numCards++)
            {
                piles[pile].push( stock.pop() );
            }
            
            piles[pile].peek().turnUp();
        }
    }
    
    /**
     * Deals a number of cards from the stock to the waste pile.
     */
    private void dealCards()
    {
        if( stock.size() < cardsToMove )
            cardsToMove = stock.size();
            
        for(int i = 0; i < cardsToMove; i++)
        {
            Card card = stock.pop();
            card.turnUp();
            waste.push( card );
        }
    }

    /**
     * Resets the stock by returning all cards from the waste.
     */
    private void resetStock()
    {
        for(int i = waste.size(); i > 0; i--)
        {
            Card card = waste.pop();
            card.turnDown();
            stock.push( card );
        }
    }

    /**
     * Returns the top card from the stock pile.
     * 
     * @return the card on top of the stock or null if stock is empty
     */
    public Card getStockCard()
    {
        if( stock.isEmpty() )
            return null;
        return stock.peek();
    }

    /**
     * Returns the top card from the waste pile.
     * 
     * @return the card on top of the waste or null if the waste is empty
     */
    public Card getWasteCard()
    {
        if( waste.isEmpty() )
            return null;
        return waste.peek();
    }

    /**
     * Returns the top card from the foundation pile.
     * 
     * @precondition:  0 <= index < 4
     * 
     * @return returns the card on top of the given foundation or null 
     *                  if the foundation is empty
     */
    public Card getFoundationCard(int index)
    {
        if( foundations[index].isEmpty() )
            return null;
        return foundations[index].peek();
    }

    /**
     * Returns a reference of the given pile.
     * 
     * @param index the index of the pile to return
     * @precondition:  0 <= index < 7
     * 
     * @return a reference to the given pile
     */
    public Stack<Card> getPile(int index)
    {
        return piles[index];
    }

    /**
     * Deals cards to the waste or resets the stock (when empty) when clicked.
     */
    public void stockClicked()
    {
        if( !(display.isWasteSelected() || display.isPileSelected()) )
        {
            if( stock.size() > 0 )
                dealCards();
            
            else
                resetStock();
        }
    }

    /**
     * Selects the top card on the waste pile when clicked.
     */
    public void wasteClicked()
    {
        if( waste.size() > 0 && !display.isWasteSelected() && !display.isPileSelected() )
            display.selectWaste();
            
        else
            display.unselect();
    }

    /**
     * Selects the given foundation. A card can be added to the given foundation, or
     * a card may be taken from the foundation to a pile.
     * 
     * @param index   the index of the foundation clicked
     * @precondition:  0 <= index < 4
     */
    public void foundationClicked(int index)
    {
        if( display.isFoundationSelected() )
            display.unselect();
        
        else if( !(display.isWasteSelected() || display.isPileSelected()) )
            display.selectFoundation(index);
            
        else if( display.isWasteSelected() )
        {
            Card card = waste.peek();
            if( canAddToFoundation(card, index) )
                foundations[index].push( waste.pop() );
            
            display.unselect();
        }
        
        else if( display.isPileSelected() )
        {
            Card card = piles[display.selectedPile()].peek();
            if( canAddToFoundation(card, index) )
            {
                foundations[index].push( piles[display.selectedPile()].pop() );
                if(!piles[display.selectedPile()].isEmpty())
                    piles[display.selectedPile()].peek().turnUp();
            }
                
            display.unselect();
        }
    }

    /**
     * Selects the given pile. A card can be moved from the waste or foundation to
     * the pile, or cards may be moved from other piles to this pile.
     * 
     * @param index the index of the pile selected
     * @precondition:  0 <= index < 7
     */
    public void pileClicked(int index)
    {
        if( display.isWasteSelected() && canAddToPile(waste.peek(), index) )
        {
            piles[index].push( waste.pop() );
            display.unselect();
        }
        
        else if( display.isFoundationSelected() && 
                 canAddToPile(foundations[display.selectedFoundation()].peek(), index) )
        {
            piles[index].push( foundations[display.selectedFoundation()].pop() );
            display.unselect();
        }
        
        else if( display.isPileSelected() && display.selectedPile() != index )
        {
            Stack<Card> cards = removeFaceUpCards(display.selectedPile());
            if(!cards.isEmpty() && canAddToPile(cards.peek(), index))
            {
                addToPile(cards, index);
                if(!piles[display.selectedPile()].isEmpty())
                    piles[display.selectedPile()].peek().turnUp();
            }
            else
                addToPile(cards, display.selectedPile());
            display.unselect();
        }
        else if( display.isPileSelected() && display.selectedPile() == index)
            display.unselect();
            
        else
            display.selectPile(index);
            
        if( hasWonGame() )
            display.gameWon();
    }
        
    /**
     * Checks to see if the given card can be added to the given pile.
     * 
     * @param card  the card to check
     * @param index the index of the pile to check against
     * @precondition:  0 <= index < 7
     * 
     * @return true if the card can be added to the pile; else,
     *         false
     */
    private boolean canAddToPile(Card card, int index)
    {
        if(piles[index].isEmpty())
            return card.getRank() == 13;
        
        Card topCard = piles[index].peek();
        
        String topCardSuit;
        if(topCard.getSuit().equals("c") || topCard.getSuit().equals("s"))
            topCardSuit = "b";
        else topCardSuit = "r";
        
        String cardSuit;
        if(card.getSuit().equals("c") || card.getSuit().equals("s"))
            cardSuit = "b";
        else cardSuit = "r";
        
        return card.getRank() == topCard.getRank() - 1 && !cardSuit.equals(topCardSuit);
    }
    
    /**
     * Removes all face-up cards, if any, from the given pile.
     * 
     * @param index the index of the pile to remove cards from
     * @precondition:  0 <= index < 7
     * 
     * @return a stack of all face-up cards in the pile
     */
    private Stack<Card> removeFaceUpCards(int index)
    {
        Stack<Card> faceUpCards = new Stack<Card>();
        Stack<Card> pile = piles[index];
        while(!pile.isEmpty() && pile.peek().isFaceUp())
            faceUpCards.push( pile.pop() );
        return faceUpCards;
    }
    
    /**
     * Adds all of the given cards to the given pile.
     * 
     * @param cards the stack of cards to add to pile
     * @param index the index of the pile to add cards to
     * @precondition:  0 <= index < 7
     * 
     */
    private void addToPile(Stack<Card> cards, int index)
    {
        Stack<Card> pile = piles[index];
        while(!cards.isEmpty())
            pile.push( cards.pop() );
    }
    
    /**
     * Checks to see if the card can be added to the given foundation
     * 
     * @param card  the card to check
     * @param index the index of the foundation to check against
     * @precondition:  0 <= index < 4
     * 
     * @return true if the card can be added to the foundation; else,
     *         false
     */
    private boolean canAddToFoundation(Card card, int index)
    {
        if(foundations[index].isEmpty())
            return card.getRank() == 1;

        Card foundationCard = foundations[index].peek();
        return card.getRank() == foundationCard.getRank() + 1 && 
               card.getSuit().equals(foundationCard.getSuit());
    }
    
    /**
     * Starts a new game.
     */
    public void resetGame()
    {
        new Solitaire();
    }
    
    private boolean hasWonGame()
    {
        for( Stack<Card> f: foundations )
            if( f.size() != 13 ) return false;
            
        return true;
    }
}