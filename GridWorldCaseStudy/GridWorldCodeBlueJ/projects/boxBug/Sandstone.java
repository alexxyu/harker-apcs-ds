import info.gridworld.actor.Rock;
import java.awt.Color;

/**
 * Sandstone is a type of rock that erodes over time and then
 * eventually disappears from the grid altogether.
 * 
 * @author Alex Yu
 * @version September 26, 2016
 */
public class Sandstone extends Rock
{
    /**
     * The default amount that Sandstone gets lighter
     */
    public static final int DEFAULT_DECOMPOSING_FACTOR = 10;
    
    /**
     * The maximum amount of rgb value the color that the Sandstone can reach
     */
    public static final int RGB_MAX = 255;

    private int lightenFactor;
    
    /**
     * Constructs Sandstone with given color and given decomposing factor.
     * 
     * @param color the color of sandstone
     * @param decompose the amount the sandstone lightens
     */
    public Sandstone(Color color, int decompose)
    {
        super(color);
        lightenFactor = decompose;
    }
    
    /**
     * Constructs Sandstone with the default black color and given decomposing factor.
     * 
     * @param decompose the amount the sandstone lightens
     */
    public Sandstone(int decompose)
    {
        this(Color.BLACK, decompose);
    }
    
    /**
     * Constructs Sandstone with given color and default decomposing factor.
     * 
     * @param color the color of sandstone
     */
    public Sandstone(Color color)
    {
        this(color, DEFAULT_DECOMPOSING_FACTOR);
    }
    
    /**
     * Constructs Sandstone with the default black color and default decomposing factor.
     */
    public Sandstone()
    {
        this(Color.BLACK, DEFAULT_DECOMPOSING_FACTOR);
    }
    
    /**
     * Becomes lighter and lighter until completely white.
     */
    public void lighten()
    {
        Color currentColor = getColor();
        
        int red = currentColor.getRed() + lightenFactor;
        red = Math.min(red, RGB_MAX);
        
        int green = currentColor.getGreen() + lightenFactor;
        green = Math.min(green, RGB_MAX);
        
        int blue = currentColor.getBlue() + lightenFactor;
        blue = Math.min(blue, RGB_MAX);
        
        Color newColor = new Color(red, green, blue);
        setColor(newColor);
    }
    
    /**
     * Oversees the decomposing and then removes itself from grid
     *  after it turns completely white.
     */
    public void act()
    {
        lighten();
        
        Color currentColor = getColor();
        if(currentColor.equals(Color.WHITE))
        {
            removeSelfFromGrid();
        }
    }
}
