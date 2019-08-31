/**
 * Determines if two numbers are close enough to each other
 * to be considered equal.
 * 
 * @author Susan King
 * @version October 22, 2012
 */
interface Nearable
{
    /**
     * Determines if two numbers are within an epsilon difference
     * of each other.
     * 
     * @param  obj1     an object to be tested as close to another object
     * @param  obj2     an object to be tested as close to another object
     * @return true     if the numbers are close to each other; otherwise
     *         false
     */
    boolean isNearlyEqual(Object obj1, Object obj2);
}
