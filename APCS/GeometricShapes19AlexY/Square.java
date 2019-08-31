/**
 * Square is a Rectangle whose four sides are all of the
 * same length.
 * 
 * @author Alex Yu
 * @version October 26, 2016
 */
public class Square extends Rectangle
{
    /**
     * Constructs a length by length Square with the given name.
     * 
     * @param name      the name given to the Square
     * @param length    the width and length of the Square
     */
    public Square(String name, double length)
    {
        super(name, length, length);
    }
    
    /**
     * Constructs a unit Square with a given name.
     * 
     * @param name      the name given to the Square
     */
    public Square(String name)
    {
        this(name, 1);
    }

    /**
     * Sets the length (and the width) of the Square.
     * 
     * @param l the new width and length of the Square
     * @throws IllegalArgumentException if l <= 0
     */
    public void setLength(double l)
    {
        if(l <= 0)
            throw new IllegalArgumentException("Needs to be positive");
            
        super.setLength(l);
        super.setWidth(l);
    }
    
    /**
     * Sets the width (and the length) of the Square.
     * 
     * @param w the new width and length of the Square
     * @throws IllegalArgumentException if l <= 0
     */
    public void setWidth(double w)
    {
        if(w <= 0)
            throw new IllegalArgumentException("Needs to be positive");
            
        super.setLength(w);
        super.setWidth(w);
    }
}
