import java.io.*;

/**
 * Models hurricane information, including categories.  
 * Works with HurricaneOrganizer, provides object and comparison skeletons.
 * 
 * @author Susan King
 * @author Alex Yu
 * @version January 5, 2017
 */
public class Hurricane
{
    private int year;
    private int pressure;
    private int speed;
    private int category;
    
    private String name;
    private String month;
    
    /**
     * Initializes a Hurricane object with no information.
     */
    public Hurricane( )
    {

    }

    /**
     * Initializes a Hurricane object with historical information.
     * 
     * @param year      year the hurricane took place
     * @param month     month in String format
     * @param pressure  hurricane's pressure
     * @param speed     hurricane's speed in knots
     * @param name      hurricane's name
     */
    public Hurricane(int year, String month, 
    int pressure, int speed, String name)
    {
        this.year = year;
        this.month = month;
        this.pressure = pressure;
        this.speed = speed;
        this.name = name;
        
        category = determineCategory(speed);
    }

    /**
     * Based upon Saffir/Simpson Hurricane Scale, figures out
     * the category using wind speed in knots.
     * 
     * @param knots     wind speed in knots
     * @return Saffir/Simpson Hurricane Scale category
     */
    public int determineCategory(int knots)
    {
        if(knots >= 64 && knots <= 82)
            category = 1;
        else if(knots >= 83 && knots <= 95)
            category = 2;
        else if(knots >= 96 && knots <= 112)
            category = 3;
        else if(knots >= 113 && knots <= 136)
            category = 4;
        else
            category = 5;
            
        return category;
    }

    //Getters

    /**
     * Retrieves the name of the hurricane.
     * 
     * @return the hurricane's name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Retrieves the month of the hurricane.
     * 
     * @return the hurricane's month
     */
    public String getMonth()
    {
        return month;
    }

    /**
     * Retrieves the pressure of the hurricane.
     * 
     * @return the hurricane's pressure
     */
    public int getPressure()
    {
        return pressure;
    }

    /**
     * Retrieves the speed of the hurricane.
     * 
     * @return the hurricane's speed
     */
    public int getSpeed()
    {
        return speed;
    }

    /**
     * Retrieves the year of the hurricane.
     * 
     * @return the hurricane's year
     */
    public int getYear()
    {
        return year;
    }

    /**
     * Retrieves the category of the hurricane.
     * 
     * @return the hurricane's category
     */
    public int getCategory()
    {
        return category;
    }

    /**
     * Prints information about the Hurricane.
     */
    public void print()
    {
        System.out.println(toString( ));
    }

    /**
     * Formats a string that includes information about the Hurricane, 
     * including data such as its category and speed.
     * 
     * @return a formatted string about the hurricane
     */
    public String toString()
    {
        return String.format("%-4d %-5s %-15s %-5d %5d %5d ", 
              year, month, name, category, speed, pressure);
    }

    /**
     * Compares "this" hurricane's year with another hurricane's year.
     * 
     * @param h the hurricane to compare years with
     * @return 0   if their years are the same
     *        >0   if "this" hurricane came after the other hurricane
     *        <0   if "this" hurricane came before the other hurricane
     */
    public int compareYearTo(Hurricane h)
    {
        return year - h.getYear();
    }

    /**
     * Compares "this" hurricane's name with another hurricane's name alphabetically.
     * 
     * @param h the hurricane to compare names with
     * @return 0    if their names are the same
     *        >0    if "this" hurricane's name is alphabetically before the other's name
     *        <0    if "this" hurricane's name is alphabetically after the other's name
     */
    public int compareNameTo(Hurricane h)
    {
        return name.compareTo(h.getName());
    }

    /**
     * Compares "this" hurricane's pressure with another hurricane's pressure.
     * 
     * @param h the hurricane to compare pressures with
     * @return 0    if their pressures are the same
     *        >0    if "this" hurricane's pressure is greater than the other's pressure
     *        <0    if "this" hurricane's pressure is less than the other's pressure
     */
    public int comparePressureTo(Hurricane h)
    {
        return pressure - h.getPressure();
    }

    /**
     * Compares "this" hurricane's speed with another hurricane's speed.
     * 
     * @param h the hurricane to compare speeds with
     * @return 0    if their speeds are the same
     *        >0    if "this" hurricane is faster than the other hurricane
     *        <0    if "this" hurricane is slower than the other hurricane
     */
    public int compareSpeedTo(Hurricane h)
    {
        return speed - h.getSpeed();
    }

    /**
     * Compares "this" hurricane's category with another hurricane's category.
     * 
     * @param h the hurricane to compare category with
     * @return 0    if their categories are the same
     *        >0    if "this" hurricane has a greater category than the other
     *        <0    if "this" hurricane has a lower category than the other
     */
    public int compareCategoryTo(Hurricane h)
    {
        return category - h.getCategory();
    }
}
