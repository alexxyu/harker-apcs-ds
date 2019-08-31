/**
 * Abstract class Polygon is a GeometricShape that focuses on
 * the shape's regularity and number of sides.
 * 
 * @author Alex Yu
 * @version October 21, 2016
 */
public abstract class Polygon extends GeometricShape
{
    /**
     * Constructs a Polygon with a name.
     * 
     * @param name  the name given to a particular Polygon
     */
    public Polygon(String name)
    {
        super(name);
    }
    
    /**
     * Checks to see if the shape is a polygon.
     * 
     * @return true
     */
    public boolean isPolygon()
    {
        return true;
    }
    
    /**
     * Checks to see if the Polygon is regular.
     * 
     * @return true if the Polygon is regular; otherwise,
     *         false
     */
    public abstract boolean isRegular();
    
    /**
     * Retreives the number of sides of the Polygon.
     * 
     * @return the number of sidesof the Polygon
     */
    public abstract int numSides();
}
