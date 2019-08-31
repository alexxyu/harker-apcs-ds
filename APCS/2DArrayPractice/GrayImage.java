public class GrayImage
{
    public static final int BLACK = 0;
    public static final int WHITE = 255;
    
    /** 
     * The 2-dimensional representation of this image. Guaranteed not to be null.
     * All values in the array are within the range [BLACK, WHITE], inclusive.
     */
    private int[][] pixelValues;
    
    /** @return the total number of white pixels in this image.
     *  Postcondition: this image has not been changed.
     */
    public int countWhitePixels()
    {
        int count = 0;
        for(int r = 0; r < pixelValues.length; r++)
        {
            for(int c = 0; c < pixelValues[r].length; r++)
            {
                if(pixelValues[r][c] == WHITE)
                    count++;
            }
        }
        
        return count;
    }

    /** Processes this image in row-major order and decreases the value of each pixel at
     * position (row, col) by the value of the pixel at position (row + 2, col + 2) if it exists.
     * Resulting values that would be less than BLACK are replaced by BLACK.
     * Pixels for which there is no pixel at position (row + 2, col + 2) are unchanged.
     */
    public void processImage()
    {
        for(int r = 0; r < pixelValues.length - 2; r++)
        {
            for(int c = 0; c < pixelValues[r].length - 2; c++)
            {
                int nextVal = pixelValues[r+2][c+2];
                pixelValues[r][c] -= nextVal;
                
                if(pixelValues[r][c] < 0)
                    pixelValues[r][c] = BLACK;
            }
        }
    } 
}
