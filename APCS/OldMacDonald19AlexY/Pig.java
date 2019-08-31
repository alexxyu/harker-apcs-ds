/**
 * Pig is an Animal that oinks. Its latin name is Sus
 * scrofa domesticus.
 * 
 * @author Alex Yu
 * @version November 3, 2016
 */
public class Pig extends Animal
{
    /**
     * Creates an object of class Pig.
     */
    public Pig()
    {
        super("Sus scrofa domesticus", "pig");
    }
    
    /**
     * Returns the sound of a Pig.
     * 
     * @return the sound of a Pig
     */
    public String speak()
    {
        return "oink";
    }
}
