 /**
 * Triangle is a Polygon that has 3 sides. It can calculate
 * its area and its perimeter. The compareTo method compares
 * the lengths of its sides to another Triangle's.
 * 
 * @author Alex Yu
 * @version October 22, 2016
 */
public class Triangle extends Polygon
{
    private double sideA;
    private double sideB;
    private double sideC;

    /**
     * Constructs a Triangle with a name and three side lengths.
     * 
     * @param name  the name given to the Triangle
     * @param a     one of the side lengths of the Triangle
     * @param b     one of the side lengths of the Triangle
     * @param c     one of the side lengths of the Triangle
     */
    public Triangle(String name, double a, double b, double c)
    {
        super(name);
        orderSides(a, b, c);
    }

    /**
     * Retrieves the length of the longest side of the Triangle.
     * 
     * @return the length of the longest side of the Triangle
     */
    public double getSideA()
    {
        return sideA;
    }

    /**
     * Retrieves the length of the middle side of the Triangle.
     * 
     * @return the length of the middle side of the Triangle.
     */
    public double getSideB()
    {
        return sideB;
    }

    /**
     * Retrieves the length of the shortest side of the Triangle.
     * 
     * @return the length of the shortest side of the Triangle.
     */
    public double getSideC()
    {
        return sideC;
    }

    /**
     * Sets the length of one of the Triangle's sides.
     * 
     * @param a the new length
     */
    public void setSideA(double a)
    {
        orderSides(a, sideB, sideC);
    }

    /**
     * Sets the length of one of the Triangle's sides.
     * 
     * @param b the new length
     */
    public void setSideB(double b)
    {
        orderSides(sideA, b, sideC);
    }

    /**
     * Sets the length of one of the Triangle's sides.
     * 
     * @param c the new length
     */
    public void setSideC(double c)
    {
        orderSides(sideA, sideB, c);
    }

    /**
     * Orders three given lengths in descending order so that sideA
     * is the longest side and sideC is the shortest side.
     * 
     * @param a         the new length of a side
     * @param b         the new length of a side
     * @param c         the new length of a side
     * 
     * @throws IllegalArgumentException if a, b, or c <= 0
     * @throws IllegalArgumentException if the sum of any two side lengths is 
     *                                  less than the length of the third side
     * 
     * @postcondition   sideA >= sideB >= sideC
     */
    public void orderSides(double a, double b, double c)
    {
        sideA = Math.max(a, Math.max(b, c));
        sideC = Math.min(a, Math.min(b, c));
        sideB = a + b + c - sideA - sideC;
        
        if(sideA >= sideB + sideC || sideC <= 0)
            throw new IllegalArgumentException("Invalid side lengths");
    }

    /**
     * Calculates the area of the Triangle using Heron's formula.
     * 
     * @return the area of the Triangle
     */
    public double getArea()
    {
        double s = (sideA + sideB + sideC)/2;
        return Math.sqrt(s * (s-sideA) * (s-sideB) * (s-sideC));
    }

    /**
     * Calculates the perimeter of the Triangle.
     * 
     * @return the perimeter of the Triangle
     */
    public double getPerimeter()
    {
        return sideA + sideB + sideC;
    }

    /**
     * Checks if two Triangles have the same corresponding side lengths within
     * an EPSILON, which is defined in GeometricShape.
     * 
     * @param obj   Triangle object to compare side lengths with
     * @return true if each of the corresponding side lengths are equal; otherwise,
     *         false
     */
    public boolean equals(Object obj)
    {
        if(obj instanceof Triangle)
        {
            Triangle tri = (Triangle) obj;
            Double triA = tri.getSideA();
            Double triB = tri.getSideB();
            Double triC = tri.getSideC();

            return isNearlyEqual(sideA, triA) &&
                   isNearlyEqual(sideB, triB) &&
                   isNearlyEqual(sideC, triC);
        }
        return false;
    }

    /**
     * Compares the current Triangle with an Object.
     * 
     * @param obj   the Triangle object to compare sides with
     * @return 0    if the two Triangles have the same side lengths
     *       > 0    determined by comparing the longest sides of the Triangles;
     *              if equal, compare next longest sides;
     *              if equal, compare the shortest sides until current Triangle
     *              has a longer length than obj's comparable length
     *       < 0    determined by comparing the two longest sides; 
     *              if equal, compare next longest sides;
     *              if equal, compare the shortest sides until current Triangle
     *              has a shorter length than obj's comparable length
     *       -999   if obj is not a Triangle
     */
    public int compareTo(Object obj)
    {
        if(obj instanceof Triangle)
        {
            Triangle tri = (Triangle) obj;
            if(equals(tri))
                return 0;
            
            Double triA = tri.getSideA();
            Double triB = tri.getSideB();
            Double triC = tri.getSideC();

            if(!isNearlyEqual(sideA, triA))
            {
                if(sideA < triA)
                    return -1;
                    
                return 1;
            }
            
            if(!isNearlyEqual(sideB, triB))
            {
                if(sideB < triB)
                    return -1;
                    
                return 1;
            }
            
            if(sideC < triC)
                return -1;
            return 1;
        }

        return -999;
    }

    /**
     * Checks to see if Triangle is regular by comparing the lengths of
     * its sides.
     * 
     * @return true if the Triangle is regular; otherwise,
     *         false
     */
    public boolean isRegular()
    {
        return (isNearlyEqual(sideA, sideB) && isNearlyEqual(sideB, sideC));
    }

    /**
     * Returns the amount of sides the Triangle has.
     * 
     * @return 3
     */
    public int numSides()
    {
        return 3;
    }

    /**
     * Formats the class name, the Triangle's name, area, perimeter,
     * lengths of sideA, sideB, and sideC as well as whether the Triangle
     * is regular and that it has three sides.
     * 
     * @return a formatted line about the Triangle
     */
    public String toString()
    {
        String str = "\n\t\t\ta = %8.5f\tb = %8.5f\tc = %8.5f" +
                     "\n\t\t\tregular: %b\tside count = %d";
        return super.toString() + 
               String.format(str, 
                       getSideA(), 
                       getSideB(), 
                       getSideC(), 
                       isRegular(), 
                       numSides());
    }
}
