/**
 * Abstract class Animal focuses on its latin name as well
 * as its common name.
 * 
 * @author Alex Yu
 * @version November 3, 2016
 */
public abstract class Animal implements Comparable
{
    private String latinName;
    private String commonName;
    
    /**
     * Creates an Animal with the given latin name and given
     * common name.
     * 
     * @param latin the Animal's latin name
     * @param common the Animal's common name
     */
    public Animal(String latin, String common)
    {
        latinName = latin;
        commonName = common;
    }
    
    /**
     * Sets the Animal's latin name.
     * 
     * @param s the Animal's new latin name
     */
    public void setLatinName(String s)
    {
        latinName = s;
    }
    
    /**
     * Sets the Animal's common name.
     * 
     * @param s the Animal's new common name
     */
    public void setCommonName(String s)
    {
        commonName = s;
    }
    
    /**
     * Retrieves the Animal's latin name.
     * 
     * @return the Animal's latin name
     */
    public String getLatinName()
    {
        return latinName;
    }
    
    /**
     * Retrieves the Animal's common name.
     * 
     * @return the Animal's common name
     */
    public String getCommonName()
    {
        return commonName;
    }
    
    /**
     * Returns the Animal's sound for the song.
     * 
     * @return the Animal's specific sound
     */
    public abstract String speak();
    
    /**
     * Compares lexigraphically the current object's common name with
     * the other's common name.
     * 
     * @param obj   the obj being compared to
     * 
     * @return  0   if the two names are lexicographically equal;
     *         >0   if this object's name is alphabetically
     *              earlier than the obj's name; otherwise,
     *         <0   if this object's name is alphabetically
     *              later than the obj's name
     * @throws IllegalArgumentException if an Animal object is not used
     */
    public int compareTo(Object obj)
    {
        if(obj instanceof Animal)
        {
            Animal a = (Animal) obj;
            return this.getCommonName().compareTo(a.getCommonName());
        }
        throw new IllegalArgumentException("Object does not belong to the Animal class");
    }
}
