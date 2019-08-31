/**
 * A Circle object has a radius that is valid.
 * Perimeter and area can be calculated. Comparable's method
 * compareTo compares two radii.
 * 
 * @author Alex Yu
 * @version October 23, 2016
 */
public class Circle extends GeometricShape
{
    private double radius;

    /**
     * Constructor for objects of class Circle.
     * 
     * @param name  the name given to the Circle
     * @param r     the radius of the Circle
     * @throws IllegalArgumentException if r is less than or equal to 0
     */
    public Circle(String name, double r)
    {
        super(name);
        setRadius(r);
    }
    
    /**
     * Constructor for unit circle object of class Circle.
     * "this" circle has a radius of length one.
     * 
     * @param name  the name given to the Circle
     */
    public Circle(String name)
    {
        this(name, 1);
    }

    /**
     * Sets the length of the radius.
     * 
     * @param r the new length of the radius and r > 0
     * @throws IllegalArgumentException if r <= 0
     */
    public void setRadius(double r)
    {
        if(r<=0)
        {
            throw new IllegalArgumentException("Needs to be positive");
        }
        radius = r;
    }
    
    /**
     * Retrieves the length of the radius.
     * 
     * @return the length of the radius
     */
    public double getRadius()
    {
        return radius;
    }

    /**
     * Calculates the circumference of the Circle.
     * 
     * @return the circumference of the Circle
     */
    public double getPerimeter()
    {
        return 2.0 * Math.PI * radius;
    }
    
    /**
     * Calculates the area of the Circle.
     * 
     * @return the area of the Circle
     */
    public double getArea()
    {
        return Math.PI * radius * radius;
    }
    
    /**
     * Tests if the two Circles have radii that are equal
     * within an EPSILON, which is defined in GeometricShape.
     * 
     * @param obj   Circle object to test if its radius is "nearly equal"
     *              to "this" radius
     * @return true if the radii have "nearly equal" lengths; otherwise,
     *         false
     */
    public boolean equals(Object obj)
    {
        return this.compareTo(obj) == 0;
    }
    
    /**
     * Checks to see if Circle is a polygon.
     * 
     * @return false
     */
    public boolean isPolygon()
    {
        return false;
    }
    
    /**
     * Compares the current Circle with the Object obj.
     * 
     * @param obj   the Circle object with which to compare
     * @return  0   if the two Circles have the same radii within an
     *              EPSILON, which is defined in GeometricShape
     *        > 0   if "this" radius is greater than the obj's radius
     *        < 0   if "this" radius is less than the obj's radius
     *        -999  if obj is not a Circle
     */
    public int compareTo(Object obj)
    {
        if(obj instanceof Circle)
        {
            Circle circle = (Circle) obj;
            Double thisRadius = getRadius();
            Double circleRadius = circle.getRadius();

            if(isNearlyEqual(thisRadius, circleRadius))
                return 0;
            else if(thisRadius > circleRadius)
                return 1;
            else
                return -1;
        }
       
        return -999;
    }
    
    /**
     * Formats the class name, the Circle's name, area, perimeter,
     * and radius for the Circle.
     * 
     * @return a formatted line about the Circle
     */
    public String toString()
    {
        return super.toString() + 
               String.format("\n\t\t\tradius = %10.5f", radius);
    }
}
