import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

/**
 * ClientGame handles the client side of the chess game.
 * 
 * @author Alex Yu
 * @version June 2, 2017
 */
public class ClientGame
{
	private boolean gameOver;
    private Board board;
    private BoardDisplay display;
    
    private String serverIPAddress;
	private int serverPortNumber;
	
	private InputStream is;
	private OutputStream os;
	
    /**
     * Constructor that prepares the backend before the game
     * actually begins.
     */
    public ClientGame()
    {
    	// default values
        serverIPAddress = "localhost"; 
        serverPortNumber = 5454;

        gameOver = false;
        
        initialize();
    }
    
    /**
     * Initializes the game by setting up necessary info before starting.
     * 
     * @precondition the server is already running beforehand
     */
    private void initialize()
    {
    	do
    	{
    		serverIPAddress = getServerIP();
    	} while(!isValidIP(serverIPAddress));
        
    	connect();
    	
        board = new Board();
        display = new BoardDisplay(board);
        
        setUpBoard(board);
        
        play(new HumanPlayer(display, board, "User", Color.WHITE), 
             new HumanPlayer(display, board, "Opponent", Color.BLACK));
    }
    
    /**
     * Asks for and returns the server's IP address, which is needed to start
     * the game.
     * 
     * @return the server's IP address
     */
    private String getServerIP()
    {
    	String code = JOptionPane.showInputDialog(null, "Enter server's ip address");
    	if(code == null)
    		System.exit(0);
    	return code;
    }
    
    /**
     * Checks to see if the given IP address is a valid address.
     * 
     * @param ip the given IP address to check if valid
     * @return true if the IP address if valid; otherwise,
     * 		   false
     */
    private boolean isValidIP (String ip) {
        try {
            if ( ip.equals("") ) 
            {
            	JOptionPane.showMessageDialog(null, "Invalid IP address: empty string!");
                return false;
            }

            String[] parts = ip.split( "\\." , -1);
            if ( parts.length != 4 ) 
            {
            	JOptionPane.showMessageDialog(null, "Invalid IP address: wrong number of 'parts'");
                return false;
            }

            for (String s : parts)
            {
                int i = Integer.parseInt(s);
                if ( !(i>=0 && i<=255) )
                {
                	JOptionPane.showMessageDialog(null, "Invalid IP address: invalid integer");
                    return false;
                }
            }

            return true;
        } catch (NumberFormatException nfe)
        {
        	JOptionPane.showMessageDialog(null, "Invalid IP address: try again");
            return false;
        }
    }
    
    /**
     * Connects to the server and prepares the necessary network
     * components.
     */
    public void connect()
    {
		try
		{
			Socket socket = new Socket(serverIPAddress, serverPortNumber);
			is = socket.getInputStream();
			os = socket.getOutputStream();
		} catch(Exception e){}
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
     * Asks for the user's move and sends it to the server-side game.
     * 
     * @param black	the user player
     */
    public void nextUserTurn(Player black)
    {
    	Move move = black.nextMove();
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

    	try
    	{
    		ObjectOutputStream oos = new ObjectOutputStream(os);
    		oos.writeObject(move);
    	} catch (IOException e) {}
    }

    /**
     * Retrieves the opponent's move, executes it, and displays it on the board.
     */
    public void nextOppTurn()
    {
    	try
    	{
    		ObjectInputStream ois = new ObjectInputStream(is);
    		Object o = ois.readObject();
    		if(o instanceof Move) 
    		{
    			Move move = (Move) o;
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

    	} catch (ClassNotFoundException e) {} 
    	catch (IOException e)
    	{
    		JOptionPane.showMessageDialog(null, "Opponent has disconnected! Game will now terminate.");
    		System.exit(0);
    	}
    	catch (NullPointerException e)
    	{
    		JOptionPane.showMessageDialog(null, "Error: the ServerGame class must be run first!");
    		System.exit(0);
    	}

    }
    
    /**
     * Handles the game of chess by alternating turns between players.
     * 
     * @param user     the client's player
     * @param opponent     the server's player
     */
    public void play(Player user, Player opponent)
    {
        while(!gameOver)
        {
            try
            {
            	Thread.sleep(1000);
            } catch(InterruptedException e) {}
            display.enableOrDisableHighlights(false);
            display.setTitle("Opponent's turn (Black)");
            nextOppTurn();
            board.checkPromotions(opponent);
            
            display.enableOrDisableHighlights(true);
            display.setTitle("Your turn (White)");
            nextUserTurn(user);
            board.checkPromotions(user);
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
        new ClientGame();
    }

}
