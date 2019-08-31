public class Pie
{
    private String typeOfPie;
    private int numOfServings;
    private int numOfCalories;

    public Pie(String type, int servings, int calories)
    {
        typeOfPie = type;
        numOfServings = servings;
        numOfCalories = calories;
    }

    public String getType()
    {
        return typeOfPie;
    }
    
    public int getServings()
    {
        return numOfServings;
    }
    
    public int getCalories()
    {
        return numOfCalories;
    }
}
