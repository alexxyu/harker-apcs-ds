public class Cipher
{
    /** A two-dimensional array of single-character strings, instantiated in the constructor */
    private String[][] letterBlock;
    /** The number of rows of letterBlock, set by the constructor */
    private int numRows;
    /** The number of columns of letterBlock, set by the constructor */
    private int numCols;
    /** Places a string into letterBlock in row-major order.
     * @param str the string to be processed
     * Postcondition:
     * if str.length() < numRows * numCols, "A" is placed in each unfilled cell
     * if str.length() > numRows * numCols, trailing characters are ignored
     */
    private void fillBlock(String str)
    {
        int charIndex = 0;
        int len = str.length();
        
        for(int r = 0; r < letterBlock.length; r++)
        {
            for(int c = 0; c < letterBlock[r].length; c++)
            {
                if(charIndex >= len)
                    letterBlock[r][c] = "A";
                
                else
                {
                    letterBlock[r][c] = str.substring(charIndex, charIndex+1);
                }
            }
        }
    }

    /** Extracts encrypted string from letterBlock in column-major order.
     * Precondition: letterBlock has been filled
     * @return the encrypted string from letterBlock
     */
    private String encryptBlock()
    { /* implementation not shown */ return "";}

    /** Encrypts a message.
     * @param message the string to be encrypted
     * @return the encrypted message;
     * if message is the empty string, returns the empty string
     */
    public String encryptMessage(String message)
    {
        int blockSize = numRows * numCols;
        String concatString = "";
        while(message.length() > 0)
        {
            if(message.length() < blockSize)
                blockSize = message.length();
            fillBlock(message);
            concatString += encryptBlock();
            message = message.substring(blockSize);
        }
        
        return concatString;
    }
}