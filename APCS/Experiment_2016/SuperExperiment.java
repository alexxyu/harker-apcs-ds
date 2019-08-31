public class SuperExperiment extends Experiment
{
    private double z;
    
    public static final double EPSILON = 0.005;
    /**
     * Constructor for objects of class SuperExperiment
     */
    public SuperExperiment()
    {
        super();
    }
    
    public void setZ(double n)
    {
        z = n;
    }
    
    public double getZ()
    {
        return z;
    }
    
    public String toString()
    {
        return "I am the best of the best. " + 
                super.toString() + " The value of z = " + getZ();
    }
    
    public boolean equals(Object obj)
    {
        if(obj instanceof SuperExperiment)
        {
            SuperExperiment superMan = (SuperExperiment) obj;
            double diff = this.getZ() - superMan.getZ();
            if(super.equals(superMan) && Math.abs(diff) < EPSILON)
            {
                return true;
            }
        }
        
        return false;
    }
    
    public int compareTo(Object obj)
    {
        if(obj instanceof SuperExperiment)
        {
            SuperExperiment superMan = (SuperExperiment) obj;
            int compareX = super.compareTo(superMan);
            double compareZ = this.getZ() - superMan.getZ();
            
            if(this.equals(superMan))
            {
                return 0;
            }
            
            if(compareX != 0)
            {
                return compareX;
            }
            
            // they are not equal, but their X's are equal
            
            if(compareZ > EPSILON)
            {
                return 1;
            }
            
            return -1;
        }
        
        throw new IllegalArgumentException("Needs to be a SuperExperiment");
    }
}
