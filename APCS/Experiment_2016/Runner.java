public class Runner
{
    public static void main(String[] args)
    {
        Object obj = new Object();
        System.out.println(obj);
        System.out.println(obj.toString());
        
        Object str = new String("Hello World");
        System.out.println(str);
        System.out.println(str.toString());
        int index = ((String) str).indexOf("ell");
        
        Experiment scientist = new Experiment();
        ((Experiment) scientist).setX(7);
        System.out.println(scientist);
        System.out.println(scientist.toString());
     
        Experiment madScientist = new SuperExperiment();
        madScientist.setX(17);
        ((SuperExperiment)madScientist).setZ(0.01);
        System.out.println(madScientist);
        System.out.println(madScientist.toString());
        
        System.out.println("scientist equals obj = " + 
                            scientist.equals(obj));
        System.out.println("scientist equals scientist = " + 
                            scientist.equals(scientist));
        System.out.println("scientist equals madScientist = " + 
                            scientist.equals(madScientist)); 
                            
        System.out.println("scientist compareTo obj = " + 
                            scientist.compareTo(obj));
        System.out.println("scientist compareTo scientist = " + 
                            scientist.compareTo(scientist));
        System.out.println("scientist compareTo madScientist = " + 
                            scientist.compareTo(madScientist));   

        System.out.println("madScientist compareTo obj = " + 
                            madScientist.compareTo(obj));
        System.out.println("madScientist compareTo scientist = " + 
                            madScientist.compareTo(scientist));
        System.out.println("madScientist compareTo madScientist = " + 
                            madScientist.compareTo(madScientist));                      
    }
}
