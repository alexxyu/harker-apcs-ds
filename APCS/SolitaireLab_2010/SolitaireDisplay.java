import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.imageio.*;

public class SolitaireDisplay extends JComponent implements MouseListener
{
    private static final int CARD_WIDTH = 73;
    private static final int CARD_HEIGHT = 97;
    private static final int SPACING = 5;  //distance between cards
    private static final int FACE_UP_OFFSET = 15;  //distance for cascading face-up cards
    private static final int FACE_DOWN_OFFSET = 5;  //distance for cascading face-down cards

    private JFrame frame;
    private int selectedRow = -1;
    private int selectedCol = -1;
    private Solitaire game;

    public SolitaireDisplay(Solitaire game)
    {
        this.game = game;
        
        frame = new JFrame("Solitaire");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);

        this.setPreferredSize(new Dimension(CARD_WIDTH * 7 + SPACING * 8, CARD_HEIGHT * 2 + SPACING * 3 + FACE_DOWN_OFFSET * 7 + 13 * FACE_UP_OFFSET));
        this.addMouseListener(this);
        
        JButton resetButton = new JButton("reset");
        resetButton.setBounds( SPACING, (int) (getPreferredSize().getHeight() - SPACING - 20), 60, 20);
        add(resetButton);
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                frame.setVisible(false);
                frame.dispose();
                game.resetGame();
            }
        });
        
        frame.pack();
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g)
    {
        //background
        g.setColor(new Color(0, 128, 0));
        g.fillRect(0, 0, getWidth(), getHeight());

        //face down
        drawCard(g, game.getStockCard(), SPACING, SPACING);

        //stock
        drawCard(g, game.getWasteCard(), SPACING * 2 + CARD_WIDTH, SPACING);
        if (selectedRow == 0 && selectedCol == 1)
            drawBorder(g, SPACING * 2 + CARD_WIDTH, SPACING);

        //foundations
        for (int i = 0; i < 4; i++)
        {
            drawCard(g, game.getFoundationCard(i), SPACING * (4 + i) + CARD_WIDTH * (3 + i), SPACING);
            if(selectedRow == 0 && selectedCol >=2 && selectedCol <= 6 && game.getFoundationCard(i) != null)
                drawBorder(g, SPACING + (CARD_WIDTH + SPACING) * selectedCol, SPACING);
        }

        //piles
        for (int i = 0; i < 7; i++)
        {
            Stack<Card> pile = game.getPile(i);
            int offset = 0;
            for (int j = 0; j < pile.size(); j++)
            {
                drawCard(g, pile.get(j), SPACING + (CARD_WIDTH + SPACING) * i, CARD_HEIGHT + 2 * SPACING + offset);
                if (selectedRow == 1 && selectedCol == i && j == pile.size() - 1)
                    drawBorder(g, SPACING + (CARD_WIDTH + SPACING) * i, CARD_HEIGHT + 2 * SPACING + offset);

                if (pile.get(j).isFaceUp())
                    offset += FACE_UP_OFFSET;
                else
                    offset += FACE_DOWN_OFFSET;
            }
        }
    }

    private void drawCard(Graphics g, Card card, int x, int y)
    {
        if (card == null)
        {
            g.setColor(Color.BLACK);
            g.drawRect(x, y, CARD_WIDTH, CARD_HEIGHT);
        }
        else
        {
            String fileName = card.getFileName();
            if (!new File(fileName).exists())
                throw new IllegalArgumentException("bad file name:  " + fileName);
            Image image = new ImageIcon(fileName).getImage();
            g.drawImage(image, x, y, CARD_WIDTH, CARD_HEIGHT, null);
        }
    }

    public void mouseExited(MouseEvent e)
    {
    }

    public void mouseEntered(MouseEvent e)
    {
    }

    public void mouseReleased(MouseEvent e)
    {
    }

    public void mousePressed(MouseEvent e)
    {
    }

    public void mouseClicked(MouseEvent e)
    {
        //none selected previously
        int col = e.getX() / (SPACING + CARD_WIDTH);
        int row = e.getY() / (SPACING + CARD_HEIGHT);
        if (row > 1)
            row = 1;
        if (col > 6)
            col = 6;

        if (row == 0 && col == 0)
            game.stockClicked();
        else if (row == 0 && col == 1)
            game.wasteClicked();
        else if (row == 0 && col >= 3)
            game.foundationClicked(col - 3);
        else if (row == 1)
            game.pileClicked(col);
        repaint();
    }

    private void drawBorder(Graphics g, int x, int y)
    {
        g.setColor(Color.YELLOW);
        g.drawRect(x, y, CARD_WIDTH, CARD_HEIGHT);
        g.drawRect(x + 1, y + 1, CARD_WIDTH - 2, CARD_HEIGHT - 2);
        g.drawRect(x + 2, y + 2, CARD_WIDTH - 4, CARD_HEIGHT - 4);
    }

    public int getCardsToMove()
    {
        Object[] options = {"Easy", "Hard"};
        int n = JOptionPane.showOptionDialog(frame,
                "Choose difficulty: \n(Easy deals 1 card from stock \nwhile Hard deals 3)",
                "Solitaire",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,     
                options, 
                options[0]);
                
        if (n == 0) return 1;
        return 3;
    }
    
    public void unselect()
    {
        selectedRow = -1;
        selectedCol = -1;
    }

    public boolean isWasteSelected()
    {
        return selectedRow == 0 && selectedCol == 1;
    }

    public void selectWaste()
    {
        selectedRow = 0;
        selectedCol = 1;
    }

    public boolean isPileSelected()
    {
        return selectedRow == 1;
    }

    public int selectedPile()
    {
        if (selectedRow == 1)
            return selectedCol;
        else
            return -1;
    }

    public void selectPile(int index)
    {
        selectedRow = 1;
        selectedCol = index;
    }
    
    public void selectFoundation(int index)
    {
        selectedRow = 0;
        selectedCol = 3 + index;
    }
    
    public boolean isFoundationSelected()
    {
        return selectedRow == 0 && selectedCol >= 3 && selectedCol <= 6;
    }
    
    public int selectedFoundation()
    {
        if( selectedRow == 0 )
            return selectedCol - 3;
        
        return -1;
    }
    
    public void gameWon()
    {
        Object[] options = {"No", "Yes"};
        int n = JOptionPane.showOptionDialog(frame,
                "You won!\nStart a new game?",
                "Solitaire",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,     
                options, 
                options[1]);
                
        if (n == 0) System.exit(0);
        else
            game.resetGame();
    }
}