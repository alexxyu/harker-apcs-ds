import java.util.ArrayList;
/**
 * Test the various GeometricShape classes.  
 * Modified to pass CheckStyle.
 * 
 * @author Susan King
 * @version November 3, 2014
 */
public class GeometricShapeTester2014
{

    /**
     * Tests the GeometricShapes logic.
     * 
     * @param args information past via the command line
     */
    public static void main (String [ ] args)
    {
        int errorCounter = 0;
        try 
        {
            Triangle betty = new Triangle("betty", 1 , 2, 10);
            errorCounter ++;
        } 
        catch (IllegalArgumentException e) 
        {
            System.out.println( "\nFirst: " +  e );
            System.out.println("\tCorrectly identified a triangle in "
                + "which the longest side > sum of other two sides");
        }

        try 
        {
            Rectangle fred = new Rectangle("Mr.Negative", -3 , -1.5);
            errorCounter ++;
        } 
        catch (IllegalArgumentException e)
        {
            System.out.println("\nSecond: " +   e );
            System.out.println("\tCorrectly identified negative sides for a rectangle");            
        }

        try 
        {
            Rectangle zero = new Rectangle("zero", 3 , 0);
            errorCounter ++;
        } 
        catch (IllegalArgumentException e)
        {
            System.out.println("\nThird " +  e );
            System.out.println("\tCorrectly identified a length of 0 for a rectangle");            
        } 

        try 
        {
            Triangle fred = new Triangle("Mr.Negative", -3 , -1.5 , -2);
            errorCounter ++;
        } 
        catch (IllegalArgumentException e)
        {
            System.out.println("\nFourth " +   e );
            System.out.println("\tCorrectly identified negative sides for a triangle");            
        }

        try 
        {
            Triangle zero = new Triangle("zero", 3 , 1.5 , 0);
            errorCounter ++;
        } 
        catch (IllegalArgumentException e)
        {
            System.out.println( "\nFifth " +  e );
            System.out.println("\tCorrectly identified a length of 0");            
        } 

        try 
        {
            IsoscelesRightTriangle one = new IsoscelesRightTriangle("one", 1 );
            one.setSideC(Math.sqrt(2));
            if (one.isNearlyEqual((Double)one.getSideA(), (Double)2.0) && 
                one.isNearlyEqual((Double)one.getSideB(), (Double)Math.sqrt(2)))
            {
                System.out.println("\nIsosceles right triangle correctly processed setSideC");
            }
            else
            {
                errorCounter++;
                System.out.println("\nIsosceles right triangle did not correctly process " +
                    "setSideC");
            }
        } 
        catch (IllegalArgumentException e)
        {
            System.out.println(e);            
        } 

        try 
        {
            Triangle two = new Equilateral("two", 2 );
            two.setSideB(8);
            if (two.isNearlyEqual((Double)two.getSideA(), (Double) 8.0) && 
                two.isNearlyEqual((Double)two.getSideB(), (Double) 8.0) &&
                two.isNearlyEqual((Double)two.getSideC(), (Double) 8.0))
            {
                System.out.println("\nEquilateral triangle correctly processed setSideB");
            }
            else
            {
                errorCounter++;
                System.out.println("\nEquilaterial triangle did not correctly process setSideB");
            }
        } 
        catch (IllegalArgumentException e)
        {    
            System.out.println( e );
        } 

        Triangle alex = new Triangle("alex",4.0001, 5, 6.);
        try 
        {
            Object testObject = new Object( );
            int x = alex.compareTo(testObject);
            if (x != -999)
                errorCounter ++;
            System.out.println("\nalex.compareTo(testObject) should return -999; it returned " + x);
        } 
        catch (IllegalArgumentException e)
        {
            System.out.println( e );
        }

        Rectangle rex = new Square ("rex", 7.0898154);
        try 
        {
            Object testObject = new Object( );
            int x = rex.compareTo(testObject);
            if (x != -999)
                errorCounter ++;
            System.out.println("\nrex.compareTo(testObject) should return -999;" +
                " it returned " + x);
        } 
        catch (IllegalArgumentException e)
        {
            System.out.println( e );
        }

        Circle round = new Circle("round",4);
        try 
        {
            System.out.println("\n\n" + alex);
            System.out.println(round);
            int y= alex.compareAnotherWay(round);
            System.out.println("\talex.compareAnotherWay(round) should" +
                " return a negative number; it returned " + y);
            if (y >= 0)
                errorCounter ++;
        } 
        catch (IllegalArgumentException e)
        {
            System.out.println( e );
        }

        try 
        {
            System.out.println("\n\n" + rex);
            System.out.println(round);
            int y= rex.compareAnotherWay(round);
            System.out.println("\trex.compareAnotherWay(round) should return a 0; " +
                "it returned " + y);
            if (y != 0)
                errorCounter ++;
        } 
        catch (IllegalArgumentException e)
        {
            System.out.println( e );
        }

        ArrayList<GeometricShape> geo = new ArrayList<GeometricShape>( );
        geo.add(new Triangle("alex",4.0001, 5, 6.));
        geo.add(new Triangle("almost", 4.00011,5,6));
        geo.add(new Equilateral("bob", 7));
        geo.add(new IsoscelesRightTriangle("cathy", 6.5142336));
        geo.add(new Triangle("derek", 6.5142336, 6.5142336, 9.21252));
        geo.add(new Rectangle("edward", 6.3, 4.5));
        geo.add(new Rectangle("fred", 4.5 , 6.3));
        geo.add(new Rectangle("gail", 6.3 , 4.5));
        geo.add(new Rectangle("helena", 14.17963, 14.17963));
        geo.add(new Square("iris", 5.077706));
        geo.add(new Rectangle("jerome", 5,5.15665));
        geo.add(new Circle("kate", 8));
        System.out.println("\n\n");
        for (GeometricShape g: geo)
        {
            System.out.println("\n\n\n");
            System.out.println(g);

            String classNm = g.getClass().getName();
            if (classNm.equals("IsoscelesRightTriangle")) classNm = "IsoscelesRt";
            System.out.printf("%-10s\t%-15s\t%-10s\t%-7s %s\n", 
                g.getName( ),g.getClass().getName(),
                ".compareTo",".equals", " .compareAnotherWay");
            for (GeometricShape s: geo)
            {
                String className = s.getClass().getName();
                if (className.equals("IsoscelesRightTriangle")) className = "IsoscelesRt";
                System.out.printf("  %-8s\t%-15s\t%4d\t\t%-6b\t%4d\n",
                    s.getName( ),
                    className,
                    g.compareTo(s),
                    g.equals(s),
                    g.compareAnotherWay(s) );
            }
        }

        System.out.println("\n\nError count = " + errorCounter);
    }
}
