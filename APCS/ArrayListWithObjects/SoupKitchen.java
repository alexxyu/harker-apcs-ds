import java.util.ArrayList;

public class SoupKitchen
{
    private ArrayList<Pie> listOfPies;

    public SoupKitchen(ArrayList<Pie> pies)
    {
        listOfPies = pies;
    }

    public int getTotalServings()
    {
        int sumOfServings = 0;
        for(Pie p: listOfPies)
            sumOfServings += p.getServings();
            
        return sumOfServings;
    }
    
    public int getTotalCalories()
    {
        int sumOfCalories = 0;
        for(Pie p: listOfPies)
            sumOfCalories += p.getCalories();
            
        return sumOfCalories;
    }
    
    public double getAverageCaloriesPerServing()
    {
        return getTotalCalories()/((double) getTotalServings());
    }
    
    public int getNeededServings(int numOfPeople)
    {
        if(numOfPeople <= getTotalServings())
            return 0;
        
        return numOfPeople - getTotalServings();
    }
}
