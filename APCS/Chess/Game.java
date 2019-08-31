import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Game oversees the match of chess.
 * 
 * @author Alex Yu
 * @version June 1, 2017
 */
public class Game
{
    private boolean gameOver;
    private Board board;
    private BoardDisplay display;
    
    /**
     * Constructor that prepares the backend before the game
     * actually begins.
     */
    public Game()
    {
        board = new Board();
        display = new BoardDisplay(board);
        
        setUpBoard(board);
        gameOver = false;

        play( new HumanPlayer(display, board, "You", Color.BLACK), 
              getTypePlayer() );
    }
    
    /**
     * Prompts the user for what type of player s/he wants to play against.
     * 
     * @return the opposing player for the user to play against
     */
    private Player getTypePlayer()
    {
    	int choice = JOptionPane.showOptionDialog(null, "What type of player to play against?", "Choose an option",
    											  JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, 
    											  new ImageIcon("black_pawn.gif", ""), new String[]{"Human", "Random", "AI"}, "AI");

		if(choice == 0 )
			return new HumanPlayer(display, board, "Opponent", Color.WHITE);

		else if(choice == 1)
			return new RandomPlayer(board, "Random", Color.WHITE);
		
		return new SmartPlayer(board, "Smart", Color.WHITE);
    }
    
    /**
     * Sets up the board with the necessary pieces.
     * 
     * @param board the chess board
     */
    public void setUpBoard(Board board)
    {
        Piece blackKing = new King(Color.BLACK, "black_king.gif");
        blackKing.putSelfInGrid(board, new Location(0, 4));
        
        Piece whiteKing = new King(Color.WHITE, "white_king.gif");
        whiteKing.putSelfInGrid(board, new Location(7, 4));

        Piece blackRookNW = new Rook(Color.BLACK, "black_rook.gif");
        blackRookNW.putSelfInGrid(board, new Location(0, 0));

        Piece blackRookNE = new Rook(Color.BLACK, "black_rook.gif");
        blackRookNE.putSelfInGrid(board, new Location(0, 7));

        Piece whiteRookSW = new Rook(Color.WHITE, "white_rook.gif");
        whiteRookSW.putSelfInGrid(board, new Location(7, 0));

        Piece whiteRookSE = new Rook(Color.WHITE, "white_rook.gif");
        whiteRookSE.putSelfInGrid(board, new Location(7, 7));

        Piece blackQueen = new Queen(Color.BLACK, "black_queen.gif");
        blackQueen.putSelfInGrid(board, new Location(0, 3));
        
        Piece whiteQueen = new Queen(Color.WHITE, "white_queen.gif");
        whiteQueen.putSelfInGrid(board, new Location(7, 3));
        
        Piece blackPawn1 = new Pawn(Color.BLACK, "black_pawn.gif");
        blackPawn1.putSelfInGrid(board, new Location(1, 0));
        
        Piece blackPawn2 = new Pawn(Color.BLACK, "black_pawn.gif");
        blackPawn2.putSelfInGrid(board, new Location(1, 1));
        
        Piece blackPawn3 = new Pawn(Color.BLACK, "black_pawn.gif");
        blackPawn3.putSelfInGrid(board, new Location(1, 2));
        
        Piece blackPawn4 = new Pawn(Color.BLACK, "black_pawn.gif");
        blackPawn4.putSelfInGrid(board, new Location(1, 3));
        
        Piece blackPawn5 = new Pawn(Color.BLACK, "black_pawn.gif");
        blackPawn5.putSelfInGrid(board, new Location(1, 4));
        
        Piece blackPawn6 = new Pawn(Color.BLACK, "black_pawn.gif");
        blackPawn6.putSelfInGrid(board, new Location(1, 5));
        
        Piece blackPawn7 = new Pawn(Color.BLACK, "black_pawn.gif");
        blackPawn7.putSelfInGrid(board, new Location(1, 6));
        
        Piece blackPawn8 = new Pawn(Color.BLACK, "black_pawn.gif");
        blackPawn8.putSelfInGrid(board, new Location(1, 7));
        
        Piece whitePawn1 = new Pawn(Color.WHITE, "white_pawn.gif");
        whitePawn1.putSelfInGrid(board, new Location(6, 0));
       
        Piece whitePawn2 = new Pawn(Color.WHITE, "white_pawn.gif");
        whitePawn2.putSelfInGrid(board, new Location(6, 1));
        
        Piece whitePawn3 = new Pawn(Color.WHITE, "white_pawn.gif");
        whitePawn3.putSelfInGrid(board, new Location(6, 2));
        
        Piece whitePawn4 = new Pawn(Color.WHITE, "white_pawn.gif");
        whitePawn4.putSelfInGrid(board, new Location(6, 3));
        
        Piece whitePawn5 = new Pawn(Color.WHITE, "white_pawn.gif");
        whitePawn5.putSelfInGrid(board, new Location(6, 4));
        
        Piece whitePawn6 = new Pawn(Color.WHITE, "white_pawn.gif");
        whitePawn6.putSelfInGrid(board, new Location(6, 5));
        
        Piece whitePawn7 = new Pawn(Color.WHITE, "white_pawn.gif");
        whitePawn7.putSelfInGrid(board, new Location(6, 6));
        
        Piece whitePawn8 = new Pawn(Color.WHITE, "white_pawn.gif");
        whitePawn8.putSelfInGrid(board, new Location(6, 7));

        Piece blackKnightNW = new Knight(Color.BLACK, "black_knight.gif");
        blackKnightNW.putSelfInGrid(board, new Location(0, 1));
        
        Piece blackKnightNE = new Knight(Color.BLACK, "black_knight.gif");
        blackKnightNE.putSelfInGrid(board, new Location(0, 6));
        
        Piece whiteKnightSW = new Knight(Color.WHITE, "white_knight.gif");
        whiteKnightSW.putSelfInGrid(board, new Location(7, 1));
        
        Piece whiteKnightSE = new Knight(Color.WHITE, "white_knight.gif");
        whiteKnightSE.putSelfInGrid(board, new Location(7, 6));
        
        Piece blackBishopNW = new Bishop(Color.BLACK, "black_bishop.gif");
        blackBishopNW.putSelfInGrid(board, new Location(0, 2));
        
        Piece blackBishopNE = new Bishop(Color.BLACK, "black_bishop.gif");
        blackBishopNE.putSelfInGrid(board, new Location(0, 5));
        
        Piece whiteBishopSW = new Bishop(Color.WHITE, "white_bishop.gif");
        whiteBishopSW.putSelfInGrid(board, new Location(7, 2));
        
        Piece whiteBishopSE = new Bishop(Color.WHITE, "white_bishop.gif");
        whiteBishopSE.putSelfInGrid(board, new Location(7, 5));
    }

    /**
     * Updates the display for the game to reflect the moves made and the current
     * player and also executes the player's move.
     * 
     * @param player    the current player
     */
    public void nextTurn(Player player)
    {
        display.setTitle(player.getName());
        Move move = player.nextMove();
        display.setColor(move.getSource(), Color.YELLOW);
        display.setColor(move.getDestination(), Color.YELLOW);
        
        Piece victim = move.getVictim();
        if(victim != null && victim instanceof King)
        {
            gameOver = true;
            return;
        }
        board.executeMove(move);
        display.clearColors();
    }
    
    /**
     * Handles the game of chess by alternating turns between players.
     * 
     * @param black     the black player
     * @param white     the white player
     */
    public void play(Player black, Player white)
    {
        while(!gameOver)
        {
            try {Thread.sleep(1000);} catch(InterruptedException e) {}
            display.setTitle(black.getName() + "Your turn");
            nextTurn(black);
            board.checkPromotions(black);
            display.setTitle(white.getName() + "'s turn");
            nextTurn(white);
            board.checkPromotions(white);
        }
        
        display.setTitle("Checkmate!");
        JOptionPane.showMessageDialog(null, "Checkmate!");
    }

    /**
     * Begins the game of chess.
     * 
     * @param args info from the command line
     */
    public static void main(String[] args)
    {
        new Game();
    }
}
