/*
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * BoardDisplay is used to display the contents of a game board.
 * 
 * @author Cay Horstmann
 * @author Anu Datar
 * @version May 27, 2015
 */
public class BoardDisplay implements ActionListener
{
    private Board board;
    private JButton[][] grid;
    private Piece selectedPiece;
    private Move selectedMove;
    private JFrame frame;
    private Color[][] colors;

    /**
     * Constructs a new display for displaying the given board.
     * 
     * @param board  the board object to be displayed 
     */
    public BoardDisplay(Board board)
    {
        this.board = board;
        grid = new JButton[board.getNumRows()][board.getNumCols()];
        colors = new Color[board.getNumRows()][board.getNumCols()];

        /**
         * Schedules a job for the event-dispatching thread:
         * creating and showing this application's GUI.
         */
        SwingUtilities.invokeLater(new Runnable()
            {
                public void run()
                {
                    createAndShowGUI();
                }
            });

        // waits until display has been drawn
        try
        {
            while (frame == null || !frame.isVisible())
                Thread.sleep(1);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Creates the GUI and shows it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private void createAndShowGUI()
    {
        // creates and sest up the window
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(board.getNumRows(), board.getNumCols()));

        // creates each square as a button
        for (int row = 0; row < grid.length; row++)
            for (int col = 0; col < grid[row].length; col++)
            {
                grid[row][col] = new JButton();
                grid[row][col].setOpaque(true);
                if ((row + col) % 2 == 0)
                    grid[row][col].setBackground(new Color(155, 145, 115));
                else
                    grid[row][col].setBackground(new Color(110, 85, 55));
                grid[row][col].setPreferredSize(new Dimension(50, 50));
                grid[row][col].setActionCommand(row + "," + col);
                grid[row][col].addActionListener(this);
                frame.getContentPane().add(grid[row][col]);
            }

        // shows the pieces
        showBoard();

        // displays the window.
        frame.pack();
        frame.setVisible(true);
    }

    //
    /**
     * Executes a specific action when a square is 
     * clicked.
     * 
     * @param event  the clicked event 
     *               (i.e, a clicked button) 
     */    
    public void actionPerformed(ActionEvent event)
    {
        // determines location of clicked button
        String command = event.getActionCommand();
        int comma = command.indexOf(",");
        int row = Integer.parseInt(command.substring(0, comma));
        int col = Integer.parseInt(command.substring(comma + 1));
        Location loc = new Location(row, col);

        if (selectedPiece == null)
        {
            //we have just selected a piece for the first time.
            selectedPiece = board.get(loc);
            clearColors();
            if (selectedPiece != null)
                setColor(loc, Color.YELLOW);
        }
        else if (loc.equals(selectedPiece.getLocation()))
        {
            // deselects the piece
            selectedPiece = null;
            selectedMove = null;
            clearColors();
        }
        else
        {
            // selects a move
            selectedMove = new Move(selectedPiece, loc);
            setColor(loc, Color.YELLOW);
        }
    }

    /**
     * Redraws this board to include the pieces and border colors.
     */    
    public void showBoard()
    {
        for (int row = 0; row < grid.length; row++)
            for (int col = 0; col < grid[row].length; col++)
            {
                Location loc = new Location(row, col);

                Piece piece = board.get(loc);

                Icon icon = null;
                if (piece != null)
                {
                    grid[row][col].setForeground(piece.getColor());
                    icon = new ImageIcon(piece.getImageFileName());
                }
                grid[row][col].setIcon(icon);

                Color color = colors[row][col];

                if (color == null)
                    grid[row][col].setBorder(null);
                else
                {
                    grid[row][col].setBorder(
                        BorderFactory.createLineBorder(color));
                }
            }
    }

    /**
     * Waits for the user to select a move and returns that move.
     *  
     * @return the selected move
     */  
    public Move selectMove()
    {
        try
        {
            selectedPiece = null;
            selectedMove = null;
            while (selectedMove == null)
                Thread.sleep(1);
            Move move = selectedMove;
            selectedPiece = null;
            selectedMove = null;
            return move;
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    /**
     * Sets the title of the window.
     *  
     * @param title  the title text for this display window
     */ 
    public void setTitle(String title)
    {
        frame.setTitle(title);
    }

    /**
     * Sets the color of the border for the given location, and redraws it.
     *  
     *  @param loc    the location object
     *  @param color  the color to set for the location
     */ 
    public void setColor(Location loc, Color color)
    {
        colors[loc.getRow()][loc.getCol()] = color;
        showBoard();
    }

    /**
     * Clears all border colors and redraws this board.
     */     
    public void clearColors()
    {
        for (int row = 0; row < colors.length; row++)
            for (int col = 0; col < colors[row].length; col++)
                colors[row][col] = null;
        showBoard();
    }
}