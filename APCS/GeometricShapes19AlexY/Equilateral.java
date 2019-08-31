/**
 * Equilateral is a regular Triangle. In other words, Equilateral
 * has three sides with the same length.
 * 
 * @author Alex Yu
 * @version October 24, 2016
 */
public class Equilateral extends Triangle
{
    /**
     * Constructs an Equilateral with the given length and given name
     * 
     * @param name          the name given to Equilateral
     * @param sideLength    the length of each of Equilateral's sides
     */
    public Equilateral(String name, double sideLength)
    {
        super(name, sideLength, sideLength, sideLength);
    }

    /**
     * Sets each of the sides of Equilateral to the same length.
     * 
     * @param l the new length of each side
     */
    public void setSideA(double l)
    {
        orderSides(l, l, l);
    }
    
    /**
     * Sets each of the sides of Equilateral to the same length.
     * 
     * @param l the new length of each side
     */
    public void setSideB(double l)
    {
        orderSides(l, l, l);
    }
    
    /**
     * Sets each of the sides of Equilateral to the same length.
     * 
     * @param l the new length of each side
     */
    public void setSideC(double l)
    {
        orderSides(l, l, l);
    }
}
