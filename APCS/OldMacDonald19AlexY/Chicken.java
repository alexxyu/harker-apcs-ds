/**
 * Chicken is an Animal that bawks. Its latin name is
 * Gallus gallus domesticus.
 * 
 * @author Alex Yu
 * @version November 3, 2016
 */
public class Chicken extends Animal
{
    /**
     * Constructs an object of class Chicken
     */
    public Chicken()
    {
        this("chicken");
    }
    
    /**
     * Constructs a Chicken of a certain type with its latin name.
     * 
     * @param chickenType   the kind of Chicken
     */
    public Chicken(String chickenType)
    {
        super("Gallus gallus domesticus", chickenType);
    }
    
    /**
     * Returns the sound of a Chicken.
     * 
     * @return the sound of a Chicken
     */
    public String speak()
    {
        return "bawk";
    }
}
