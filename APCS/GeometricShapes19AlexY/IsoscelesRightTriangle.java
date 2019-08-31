/**
 * IsoscelesRightTriangle is a Triangle that has two legs with equal
 * length. Its hypotenuse is equal to the sqrt(2) * length of leg.
 * 
 * @author Alex Yu
 * @version October 26, 2016
 */
public class IsoscelesRightTriangle extends Triangle
{
    /**
     * Constructs an IsoscelesRightTriangle with a given name
     * and leg length.
     * 
     * @param name  the name given to the IsoscelesRightTriangle
     * @param leg   the length of its legs
     */
    public IsoscelesRightTriangle(String name, double leg)
    {
        super(name, Math.sqrt(2)*leg, leg, leg);
    }

    /**
     * Sets the hypotenuse to the given value and then sets the legs
     * to the correct length based on the Pythagorean Theorem.
     * 
     * @param a the new length of the hypotenuse
     */
    public void setSideA(double a)
    {
        orderSides(a, Math.sqrt(a*a/2), Math.sqrt(a*a/2));
    }
    
    /**
     * Sets the leg to the given value and then sets the hypotenuse
     * to the correct length based on the Pythagorean Theorem.
     * 
     * @param b the new length of the legs
     */
    public void setSideB(double b)
    {
        orderSides(Math.sqrt(2.0)*b, b, b);
    }
    
    /**
     * Sets the leg to the given value and then sets the hypotenuse
     * to the correct length based on the Pythagorean Theorem.
     * 
     * @param c the new length of the legs
     */
    public void setSideC(double c)
    {
        setSideB(c);
    }
}
