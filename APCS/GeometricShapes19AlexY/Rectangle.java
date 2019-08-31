/**
 * Rectangle is a four-sided Polygon that has a width and a length.
 * It can calculate its area and perimeter. Its compareTo method
 * compares the length and width of two Rectangles.
 * 
 * @author Alex Yu
 * @version October 26, 2016
 */
public class Rectangle extends Polygon
{
    private double length;
    private double width;
    
    /**
     * Constructs a Rectangle with a given name and given lengths
     * for its length and width.
     * 
     * @param name  Rectangle's given name
     * @param l     the length of the Rectangle
     * @param w     the width of the Rectangle
     */
    public Rectangle(String name, double l, double w)
    {
        super(name);
        setLength(l);
        setWidth(w);
    }

    /**
     * Sets the length of the Rectangle.
     * 
     * @param l the new length of the Rectangle
     * @throws IllegalArgumentException if l <= 0
     */
    public void setLength(double l)
    {
        if(l <= 0)
            throw new IllegalArgumentException("Length is not legal for a rectangle");
        
        length = l;
    }
    
    /**
     * Sets the width of the Rectangle.
     * 
     * @param w the new width of the Rectangle
     * @throws IllegalArgumentException if w <= 0
     */
    public void setWidth(double w)
    {
        if(w <= 0)
            throw new IllegalArgumentException("Width is not legal for a rectangle");
        
        width = w;
    }
    
    /**
     * Retrieves the length of the Rectangle.
     * 
     * @return the length of the Rectangle
     */
    public double getLength()
    {
        return length;
    }
    
    /**
     * Retrieves the width of the Rectangle.
     * 
     * @return the width of the Rectangle
     */
    public double getWidth()
    {
        return width;
    }
    
    /**
     * Calculates the area of the Rectangle.
     * 
     * @return the area of the Rectangle
     */
    public double getArea()
    {
        return length * width;
    }
    
    /**
     * Calculates the perimeter of the Rectangle.
     * 
     * @return the perimeter of the Rectangle
     */
    public double getPerimeter()
    {
        return 2*length + 2*width;
    }
    
    /**
     * Retrieves the number of sides that Rectangle has.
     * 
     * @return 4
     */
    public int numSides()
    {
        return 4;
    }
    
    /**
     * Checks to see if "this" Rectangle is regular or not.
     * 
     * @return whether Rectangle is regular
     */
    public boolean isRegular()
    {
        return (isNearlyEqual(width, length));
    }
    
    /**
     * Compares the current Rectangle obj with the Object obj using
     * their lengths and widths.
     * 
     * @param obj   the Rectangle object to compare
     * @return  0   if the two Rectangles have the same lengths and widths
     *              within an EPSILON, which is defined within GeometricShape
     *        > 0   if "this" length or width is greater than the other Rectangle's
     *        < 0   if "this" length or width is less than the other Rectangle's
     *        -999  if obj is not a Rectangle
     */
    public int compareTo(Object obj)
    {
        if(obj instanceof Rectangle)
        {         
            if(equals(obj))
                return 0;
            
            Rectangle rect = (Rectangle) obj;
            
            Double thisLength = getLength();
            Double rectLength = rect.getLength();
            
            Double thisWidth = getWidth();
            Double rectWidth = rect.getWidth();
                
            if(isNearlyEqual(thisLength, rectLength))
            {
                if(thisWidth > rectWidth)
                    return 1;
                    
                else
                    return -1;
            }
            
            else if(thisLength > rectLength)
                return 1;

            return -1;
        }
        
        return -999;
    }
    
    /**
     * Tests to see if two Rectangles have the same lengths and widths
     * within an EPSILON, which is defined in GeometricShape.
     * 
     * @param obj   the Rectangle obj to compare
     * @return true if the two Rectangles have "nearly equal" lengths
     *              and widths; otherwise,
     *         false
     */
    public boolean equals(Object obj)
    {
        if(obj instanceof Rectangle)
        {
            Rectangle rect = (Rectangle) obj;
            
            Double thisLength = getLength();
            Double rectLength = rect.getLength();
            
            Double thisWidth = getWidth();
            Double rectWidth = rect.getWidth();
            
            return isNearlyEqual(thisLength, rectLength) && isNearlyEqual(thisWidth, rectWidth);
        }
        
        return false;
    }
    
    /**
     * Formats the class name, the Rectangle's name, area, perimeter, 
     * as well as its width and length. In addition, it includes
     * whether "this" Rectangle is regular and how many sides it has.
     * 
     * @return a formatted line about "this" Rectangle
     */
    public String toString()
    {
        String str = "\n\t\t\twidth = %8.5f\tlength = %8.5f" +
                     "\n\t\t\tregular: %b\tside count = %d";
        return super.toString() + 
               String.format(str,
                             getWidth(),
                             getLength(),
                             isRegular(),
                             numSides());
    }
}
