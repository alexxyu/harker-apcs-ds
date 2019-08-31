/**
 * Rooster is a kind of Chicken that crows.
 * 
 * @author Alex Yu
 * @version November 3, 2016
 */
public class Rooster extends Chicken
{
    /**
     * Constructs an object of class Rooster.
     */
    public Rooster()
    {
        super("rooster");
    }

    /**
     * Returns the sound of a Rooster.
     * 
     * @return the sound of a Rooster
     */
    public String speak()
    {
        return "cock-a-doodle-do";
    }
}
