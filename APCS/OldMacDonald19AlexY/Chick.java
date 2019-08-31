/**
 * Chick is a type of Chicken that peeps.
 * 
 * @author Alex Yu
 * @version November 3, 2016
 */
public class Chick extends Chicken
{
    /**
     * Creates an object of class Chick.
     */
    public Chick()
    {
        super("chick");
    }
    
    /**
     * Returns the sound of a Chick.
     * 
     * @return the sound of a Chick
     */
    public String speak()
    {
        return "peep";
    }
}
