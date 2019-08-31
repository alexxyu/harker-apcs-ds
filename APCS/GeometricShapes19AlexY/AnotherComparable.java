/**
 * Gives objects another way to be compared to other objects
 * beyond just the Comparable method compareTo.
 * 
 * @author Susan King
 * @version October 22, 2012
 */
public interface AnotherComparable
{
    /**
     * Compares two objects in a different way than compareTo does.
     * 
     * @param obj   the other object
     * 
     * @return  0 if two objects are sufficiently equal,
     *        > 0 if "this" object is determined to be greater than obj,
     *        < 0 if "this" object is determined to be less than obj
     */
    int compareAnotherWay(Object obj);
}
