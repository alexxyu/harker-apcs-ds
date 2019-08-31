
/**
 * A word and its frequency in a book.
 * 
 * Note to students:  you need to add code as well methods to the
 *                    class to complete the project.
 * 
 * @author  Susan King
 * @author  Alex Yu
 * @version February 21, 2017
 */
public class Word implements Comparable
{
    // instance variables 
    private String word;  
    private int frequency;  // how often word appears in a text file

    /**
     * Constructor for objects of class Word.
     * 
     * @param text the string of characters of the word
     */
    public Word(String text)
    {
        // initialise frequency to 1 because the text has
        // appeared the first time in the file
        frequency = 1;
        word = text;
    }

    /**
     * The "word" has already appeared in the file, so 1 is 
     * added to frequence since it as appeared again.
     */
    public void addOne( )
    {
        frequency++;
    }
    
    /**
     * Retrieves the text string word.
     * 
     * @return the text of the current Word object
     */
    public String getWord( )
    {
        return word;
    }
    
    /**
     * Retrieves the frequency of the word. 
     * 
     * @return the number of occurences of the Word in the text
     */
    public int getFrequency()
    {
        return frequency;
    }
    
    /**
     * Compares lexicalgraphically this current Word object and 
     * the Word obj.  
     * 
     * @param obj  the object to compare the current Word object to
     * 
     * @return   0 if this current Word object's word has equal to obj's word;
     *         < 0 if this current Word object's word comes earlier
     *             in the alphabet than obj's word;
     *         > 0 if this current Word object's word comes later 
     *             in the alphabet that obj's word
     */
    public int compareTo(Object obj)
    {
        return getWord().compareTo( ((Word) obj).getWord() ) ;
    }
    
    /**
     * Compares this current Word object to another Word object using 
     * the number of their occurences.
     * 
     * @param obj   the object to compare the current Word object to
     * 
     * @return  0 if the two Word objects occur the same number of times
     *        < 0 if this current Word object occurs less than the obj
     *        > 0 if this current Word object occurs more than the obj
     */
    public int compareFrequencyTo(Object obj)
    {
        return getFrequency() - ((Word) obj).getFrequency();
    }
    
    /**
     * Returns the text of the word and its frequency as a String.
     * 
     * @return text and frequency
     */
    public String toString( )
    {
        return String.format("%-15s %6d", word, frequency);
    }
    
}
