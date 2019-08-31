/**
 * Abstract class GeometricShape focuses on the shape's
 * name, perimeter, and area.
 * 
 * @author Alex Yu
 * @version October 17, 2016
 */
public abstract class GeometricShape implements Comparable,
                                                AnotherComparable,
                                                Nearable
{
    /**
     * The acceptable episilon (aka delta) constant
     */
    public static final double EPSILON = 1.0e-5;
    
    /**
     * Name of the geometric shape. Some clients assign a familiar name such
     * as "John"; others might use the names of the vertices such as "ABC"
     */
    private String name;

    /**
     * Constructs a GeometricShape, storing a name.
     * 
     * @param name  the name given to a particular geometric shape
     */
    public GeometricShape(String name)
    {
        super();
        this.name = name;
    }
    
    /**
     * Gets the name of the geometric shape.
     * 
     * @return name for the object
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Sets the name of the geometric shape.
     * 
     * @param name  name for the object
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * Calculates the perimeter of a geometric shape.
     * 
     * @return the perimeter of the geometric shape
     */
    public abstract double getPerimeter();
    
    /**
     * Calculates the area of a geometric shape.
     * 
     * @return the area of the geometric shape
     */
    public abstract double getArea();
    
    /**
     * Determines if two numbers are within an epsilon difference.
     * 
     * @param x Double object to be tested to be close to another Double object
     * @param y Double object to be tested to be close to another Double object
     * @return true if the numbers are close to each other; otherwise, 
     *         false
     * @throws IllegalArgumentException if x or y is not a Double object
     */
    public boolean isNearlyEqual(Object x, Object y)
    {
        if(x instanceof Double && y instanceof Double)
        {
            double xD = (Double) x;
            double yD = (Double) y;
            double diff = Math.abs(xD - yD);
            return diff <= (EPSILON * Math.abs(xD+yD)/2.0);
        }
        else
            throw new IllegalArgumentException("Needs to be a Double");
    }
    
    /**
     * Compares current geometric shape with the object obj. The
     * comparison depends upon the geometric shape. For example, circles
     * are compared by their radii.
     * 
     * @param obj   GeometricShape object with which to compare
     * @return  0   if the two geometric shapes are basically the
     *              same within an EPSILON tolerance
     *        > 0   if "this" object > than obj's object.
     *        < 0   if "this" object < than obj's object.
     */
    public abstract int compareTo(Object obj);
    
    /**
     * Compares two geometric shapes based upon the areas of
     * the geometric shapes.
     * 
     * @param obj   the geometric shape being compared
     * 
     * @return  0 if two shapes have the same area,
     *        > 0 if "this" shape has an area greater than obj, or
     *        < 0 if "this" shape has an area less than obj
     * @throws IllegalArgumentException if obj is not a GeometricShape object
     */
    public int compareAnotherWay(Object obj)
    {
        if(obj instanceof GeometricShape)
        {
            double thisArea = this.getArea();
            double objArea = ( (GeometricShape) obj ).getArea();
            double diff = thisArea - objArea;
            boolean test = isNearlyEqual(thisArea, objArea);
            if(test)
                return 0;
            else if(diff < 0)
                return -1;
            else
                return 1;
        }
        else
            throw new IllegalArgumentException("Needs to be a GeometricShape");
    }
    
    /**
     * Determines if a geometric shape is a polygon
     * 
     * @return true if the geometric shape is a polygon; otherwise,
     *         false
     */
    public abstract boolean isPolygon();
    
    /**
     * Formats the geometric object's class name, the object's name,
     * its perimeter and area.
     * 
     * @return a formatted line about the geometric object
     */
    public String toString()
    {
        return String.format("%-15s %-7s perimeter = %10.5f area = %10.5f",
                              getClass().getName(),
                              getName(),
                              getPerimeter(),
                              getArea());
    }
}
