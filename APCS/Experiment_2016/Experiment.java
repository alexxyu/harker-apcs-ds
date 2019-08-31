public class Experiment implements Comparable
{
    private int x;

    public Experiment()
    {
        super();
        x = 0;
    }
    
    public int getX()
    {
        return x;
    }
    
    public void setX(int n)
    {
        x = n;
    }
    
    public String toString()
    {
        return "The value for x = " + getX();
    }
    
    public boolean equals(Object obj)
    {
        if(obj instanceof Experiment)
        {
            Experiment xMan = (Experiment) obj;
            return getX() == xMan.getX();
        }
        
        return false;
    }
    
    public int compareTo(Object obj)
    {
        if(obj instanceof Experiment)
        {
            Experiment xMan = (Experiment) obj;
            return getX() - xMan.getX(); 
        }
        
        throw new IllegalArgumentException("Needs to be an Experiment object");
    }
}
