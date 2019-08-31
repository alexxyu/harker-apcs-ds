import java.io.*;
import java.util.*;

/**
 * Models hurricane information, works with Hurricane class
 * and the user to manipulate an array of hurricane data. 
 *
 * @author Susan King 
 * @author Alex Yu
 * @version February 1, 2017
 */
public class HurricaneOrganizerArrayYu
{
    private Hurricane [] hurricanes;

    /**
     * Creates an instance of this class to oversee the manipulation of 
     * the available hurricane data.
     * 
     * @param filename  the name of the file containing hurricane data
     * @throws IOException  if file with the hurricane information cannot be found
     */
    public HurricaneOrganizerArrayYu(String filename) throws IOException
    {
        readFile(filename);   
    }

    /**
     * Finds the number of lines in the file containing the hurricane data.
     * 
     * @param filename  the name of the file
     * @throws IOException  if file with the hurricane information cannot be found
     * @return the number of lines in the file
     */
    private static int determineFileLength(String filename) throws IOException
    {
        Scanner inFile = new Scanner(new File(filename));
        int counter = 0;

        while(inFile.hasNextLine())
        {
            counter++;
            inFile.nextLine();
        }
        inFile.close();
        return counter;
    }

    /**
     * Reads the hurricane data in the file and stores the info in an array.
     * 
     * @param filename  the name of the file
     * @throws IOException  if file with the hurricane information cannot be found
     */
    public void readFile(String filename) throws IOException
    {
        hurricanes = new Hurricane [determineFileLength(filename)];
        int hurYear, hurPressure, hurSpeed;
        String hurName, hurMonth;
        Scanner inFile = new Scanner(new File(filename));

        for(int i = 0; i < hurricanes.length; i++)
        {
            hurYear = inFile.nextInt();
            hurMonth = inFile.next();
            hurPressure = inFile.nextInt();
            hurSpeed = inFile.nextInt();
            String tempName = inFile.nextLine();
            hurName = "";
            for(int k = 0; k < tempName.length(); k++)
            {
                char c = tempName.charAt(k);
                if(('a' <= c && c <= 'z') || ('A' <= c && c <='Z'))
                    hurName += c;
            }
            Hurricane h = new Hurricane(hurYear, hurMonth, hurPressure, hurSpeed, hurName);
            hurricanes [i] = h;
        }
        inFile.close();
    }

    /**
     * Determines the greatest speed of all the hurricanes.
     * 
     * @return the maximum speed of all hurricanes
     */
    public int findMaxWindSpeed( )
    {
        int max = hurricanes[0].getSpeed();
        for(int i=1; i<hurricanes.length; i++)
        {
            max = Math.max(hurricanes[i].getSpeed(), max);
        }
        
        return max;
    }

    /**
     * Determines the greatest pressure of all the hurricanes.
     * 
     * @return the maximum pressure of all hurricanes
     */
    public int findMaxPressure( )
    {
        int max = hurricanes[0].getPressure();
        for(int i=1; i<hurricanes.length; i++)
        {
            max = Math.max(hurricanes[i].getPressure(), max);
        }
        
        return max;
    }

    /**
     * Determines the lowest speed of all the hurricanes.
     * 
     * @return the minimum speed of all hurricanes
     */
    public int findMinWindSpeed( )
    {
        int min = hurricanes[0].getSpeed();
        for(int i=1; i<hurricanes.length; i++)
        {
            min = Math.min(hurricanes[i].getSpeed(), min);
        }
        
        return min;
    }

    /**
     * Determines the lowest pressure of all the hurricanes.
     * 
     * @return the minimum pressure of all hurricanes
     */
    public int findMinPressure( )
    {
        int min = hurricanes[0].getPressure();
        for(int i=1; i<hurricanes.length; i++)
        {
            min = Math.min(hurricanes[i].getPressure(), min);
        }
        
        return min;
    }

    /**
     * Calculates the average wind speed of all the hurricanes.
     * 
     * @return the average speed of the hurricanes
     */
    public double calculateAverageWindSpeed( )
    {
        int sum = 0;
        for(int i=0; i<hurricanes.length; i++)
        {
            sum += hurricanes[i].getSpeed();
        }
        
        return ((double) sum) / hurricanes.length;
    }

    /**
     * Calculates the average pressure of all the hurricanes.
     * 
     * @return the average pressure of the hurricanes
     */
    public double calculateAveragePressure( )
    {
        int sum = 0;
        for(int i=0; i<hurricanes.length; i++)
        {
            sum += hurricanes[i].getPressure();
        }
        
        return ((double) sum) / hurricanes.length;
    }

    /**
     * Calculates the average category of all the hurricanes.
     * 
     * @return the average category of all hurricanes
     */
    public double calculateAverageCategory( )
    {
        int sum = 0;
        for(int i=0; i<hurricanes.length; i++)
        {
            sum += hurricanes[i].getCategory();
        }
        
        return ((double) sum) / hurricanes.length;
    }

    /**
     * Sorts ascending based upon the hurricanes' years,
     * The algorithm is selection sort.
     */
    public void sortYears()
    {
        int mindex;
        for(int outer = 0; outer < hurricanes.length-1; outer++)
        {
            mindex = outer;
            for(int inner = outer+1; inner < hurricanes.length; inner++)
            {
                if(hurricanes[inner].compareYearTo(hurricanes[mindex]) < 0)
                    mindex = inner;
            }
            Hurricane temp = hurricanes[mindex];
            hurricanes[mindex] = hurricanes[outer];
            hurricanes[outer] = temp;
        }
    }

    /**
     * Lexicographically sorts hurricanes based on the hurricanes' name, 
     * using insertion sort.
     */
    public void sortNames()
    {
        for(int outer = 1; outer < hurricanes.length; outer++)
        {
            Hurricane temp = hurricanes[outer];
            int index = outer - 1;
            while(index >= 0 && temp.compareNameTo(hurricanes[index]) < 0)
            {
                hurricanes[index+1] = hurricanes[index];
                index--;
            }
            
            hurricanes[index+1] = temp;
        }
    }

    /**
     * Sorts descending based upon the hurricanes' categories,
     * using selection sort.
     */
    public void sortCategories()
    {
        int mindex;
        for(int outer = 0; outer < hurricanes.length - 1; outer++)
        {
            mindex = outer;
            for(int inner = outer + 1; inner < hurricanes.length; inner++)
            {
                if(hurricanes[inner].compareCategoryTo(hurricanes[mindex]) > 0)
                    mindex = inner;
            }
            
            Hurricane temp = hurricanes[mindex];
            hurricanes[mindex] = hurricanes[outer];
            hurricanes[outer] = temp;
        }
    }  

    /**
     * Sorts descending based upon pressures using a non-recursive merge sort.
     */
    public void sortPressures()
    {
        int len = hurricanes.length;
        int mid = len / 2;
        
        sortPressuresHelper(0, mid);
        sortPressuresHelper(mid, len);
        
        int findex = 0;
        int sindex = mid;
        Hurricane[] sorted = new Hurricane[len];
        
        for(int index = 0; index < len; index++)
        {
            if(findex >= mid)
            {
                sorted[index] = hurricanes[sindex];
                sindex++;
            }
            
            else if(sindex >= len)
            {
                sorted[index] = hurricanes[findex];
                findex++;
            }
            
            else if(hurricanes[findex].comparePressureTo(hurricanes[sindex]) >= 0)
            {
                sorted[index] = hurricanes[findex];
                findex++;
            }
            
            else
            {
                sorted[index] = hurricanes[sindex];
                sindex++;
            }
        }
        
        hurricanes = sorted;
    }
    
    /**
     * Sorts descending a portion of array based upon pressure, 
     * using selection sort.
     * 
     * @param   start   the first index to start the sort
     * @param   end     one past the last index to sort; hence, end position
     *                  is excluded in the sort
     */
    private void sortPressuresHelper (int start, int end)
    {
        int maxIndex;
        for(int outer = start; outer < end - 1; outer++)
        {
            maxIndex = outer;
            for(int inner = outer + 1; inner < end; inner++)
            {
                if(hurricanes[inner].comparePressureTo(hurricanes[maxIndex]) > 0)
                    maxIndex = inner;
            }
            
            Hurricane temp = hurricanes[maxIndex];
            hurricanes[maxIndex] = hurricanes[outer];
            hurricanes[outer] = temp;
        }
    }

    /**
     * Sorts ascending based upon wind speeds using a recursive merge sort. 
     * 
     * @param low   the starting index of one part of the array.
     *              This index is included in the first part.
     * @param high  
     */
    public void sortWindSpeeds(int low, int high)
    {
        // base case
        if(low == high)
            return;
        
        int mid = (high + low)/2;
        sortWindSpeeds(low, mid);
        sortWindSpeeds(mid+1, high); 
        
        mergeWindSpeedsSortHelper(low, mid+1, high);
    }

    /**
     * Merges two consecutive parts of an array, using wind speed as a criteria
     * and a temporary array.  The merge results in an ascending sort between
     * the two given indices.
     * 
     * @precondition the two parts are sorted ascending based upon wind speed
     * 
     * @param low   the starting index of one part of the array.
     *              This index is included in the first half.
     * @param mid   the starting index of the second part of the array.
     *              This index is included in the second half.
     * @param high  the ending index of the second part of the array.  
     *              This index is included in the merge.
     */
    private void mergeWindSpeedsSortHelper(int low, int mid, int high)
    {
        Hurricane[] merged = new Hurricane[high - low + 1];
        int findex = low;
        int sindex = mid;
        
        for(int index = 0; index < merged.length; index++)
        {
            if(findex >= mid)
            {
                merged[index] = hurricanes[sindex];
                sindex++;
            }
            
            else if(sindex > high)
            {
                merged[index] = hurricanes[findex];
                findex++;
            }
            
            else if(hurricanes[findex].compareSpeedTo(hurricanes[sindex]) <= 0)
            {
                merged[index] = hurricanes[findex];
                findex++;
            }
            
            else
            {
                merged[index] = hurricanes[sindex];
                sindex++;
            }
        }
        
        for(int index = 0; index < merged.length; index++)
        {
            hurricanes[low + index] = merged[index];
        }
    }

    /**
     * Sequential search for all the hurricanes in a given year.
     * 
     * @param   year    the hurricane year being searched
     * @return  an array of objects in Hurricane that occured in
     *          the parameter year
     */
    public Hurricane[] searchYear(int year)
    {
        int counter = 0;
        for(int i=0; i<hurricanes.length; i++)
        {
            if(hurricanes[i].getYear() == year)
                counter++;
        }

        Hurricane[] h = new Hurricane[counter];
        counter = 0;
        for(int i=0; i<hurricanes.length; i++)
        {
            if(hurricanes[i].getYear() == year)
            {
                h[counter] = hurricanes[i];
                counter++;
            }
        }
        return h;
    }     

    /**
     * Binary search for a hurricane name.
     * 
     * @param  name   hurricane name being searched
     * @return a Hurricane array of all objects in hurricanes with specified name. 
     *         Returns null if there are no matches
     */
    public Hurricane[ ] searchHurricaneName(String name)
    {
        sortNames();
        return searchHurricaneNameHelper(name, 0, hurricanes.length - 1);
    }

    /**
     * Recursive binary search for a hurricane name.  This is the helper
     * for searchHurricaneName.
     * 
     * @precondition  the array must be presorted by the hurricane names
     * 
     * @param   name  hurricane name to search for
     * @param   low   the smallest index that needs to be checked
     * @param   high  the highest index that needs to be checked
     * @return  a Hurricane array of all Hurricane objects with a specified name. 
     *          Returns null if there are no matches
     */
    private Hurricane[ ] searchHurricaneNameHelper(String name, int low , int high)
    {
        // Test for the base case when a match is not found
        if(low > high)
            return null;

        // Test for match
        int mid = (high + low) / 2;
        if(hurricanes[mid].getName().equals(name))
            return retrieveMatchedNames(name, mid);
        
        // Determine if the potential match is in the 
        // "first half" of the considered items in the array
        if(hurricanes[mid].getName().compareTo(name) > 0)
            return searchHurricaneNameHelper(name, low, mid - 1);
        
        // The potential match must be in the
        // "second half" of the considered items in the array
        return searchHurricaneNameHelper(name, mid + 1, high);
    }

    /**
     * Supports Binary Search method to get the full range of matches.
     * 
     * @precondition  the array must be presorted by the hurricane names
     * 
     * @param   name hurricane name being search for
     * @param   index  the index where a match was found
     * @return  a Hurricane array with objects from hurricanes with specified name. 
     *          Returns null if there are no matches
     */
    private Hurricane[ ] retrieveMatchedNames (String name, int index)
    {
        // Find the start where the matches start:
        int findex = index - 1;
        while(findex >= 0 && hurricanes[findex].getName().equals(name))
            findex--;
        findex++;
        
        // Find the end of the matches:
        int lindex = index + 1;
        while(lindex < hurricanes.length && hurricanes[lindex].getName().equals(name))
            lindex++;
        lindex--;
        
        // Copy the objects whose names match:
        Hurricane[] matched = new Hurricane[lindex - findex + 1];
        for(int i=0; i<matched.length; i++)
        {
            matched[i] = hurricanes[i + findex];
        }
        
        return matched;
    }

    /**
     * Prints the header for the hurricane data, including various measurements
     * such as knots and pressure.
     */
    public void printHeader()
    {
        System.out.println("\n\n");
        System.out.printf("%-4s %-5s %-15s %-5s %-5s %-5s \n", 
            "Year", "Mon.", "Name", "Cat.", "Knots", "Pressure");
    }

    /**
     * Prints the set of all the hurricanes.
     */
    public void printHurricanes()
    {
        printHurricanes(hurricanes);
    }

    /**
     * Prints a specified set of hurricanes along with the header.
     * 
     * @param hurs  the array of Hurricane objects with data to print
     */
    public void printHurricanes(Hurricane[] hurs)
    {
        if(hurs == null || hurs.length == 0)
        {
            System.out.println("\nVoid of hurricane data.");
            return;
        }
        printHeader();
        for(Hurricane h: hurs)
        {
            System.out.println(h);
        }
    }

    /**
     * Prints the menu that presents choices for the user involving
     * hurricane data.
     */
    public void printMenu()
    {
        System.out.println("\n\nEnter option: ");
        System.out.println("\t 1 - Print all hurricane data \n" +
            "\t 2 - Print maximum and minimum data \n" +
            "\t 3 - Print averages \n" +
            "\t 4 - Sort hurricanes by year \n" +
            "\t 5 - Sort hurricanes by name \n" +
            "\t 6 - Sort hurricanes by category, descending \n" +
            "\t 7 - Sort hurricanes by pressure, descending \n" +
            "\t 8 - Sort hurricanes by speed \n" + 
            "\t 9 - Search for hurricanes for a given year \n" +
            "\t10 - Search for a given hurricane by name \n" +
            "\t11 - Quit \n");
    }

    /**
     * Prints out the maximum and minimum wind speeds and pressures among
     * all hurricanes.
     */
    public void printMaxAndMin( )
    {
        System.out.println("Maximum wind speed is " + 
            findMaxWindSpeed( ) +
            " knots and minimum wind speed is " + 
            findMinWindSpeed( ) + " knots.");
        System.out.println("Maximum pressure is " + 
            findMaxPressure( ) +
            " and minimum pressure is " + 
            findMinPressure( ) + ".");
    }

    /**
     * Prints the average wind speed, pressure, and category omong
     * all hurricanes.
     */
    public void printAverages( )
    {
        System.out.printf("Average wind speed is %5.2f knots. \n" , 
            calculateAverageWindSpeed( ));
        System.out.printf("Average pressure is %5.2f. \n" , 
            calculateAveragePressure( ));
        System.out.printf("Average category is %5.2f. \n" , 
            calculateAverageCategory( ));
    }

    /**
     * Interacts with the user by printing the menu and performing the
     * chosen action.
     * 
     * @return true if the user wants to quit the program; otherwise,
     *         false
     */
    public boolean interactWithUser( )
    {
        Scanner in = new Scanner(System.in);
        boolean done = false;
        printMenu();
        int choice = 0;
        try
        {
            choice = in.nextInt();
        }
        catch(InputMismatchException e)
        {
            e.printStackTrace();
        }
        // clear the input buffer
        in.nextLine();

        if(choice == 1)
        {
            printHurricanes( ); 
        }
        else if (choice == 2)
        {
            printMaxAndMin( );
        }
        else if (choice == 3)
        {
            printAverages( );
        }
        else if(choice == 4)
        {
            sortYears();
            printHurricanes( );
        }
        else if(choice == 5)
        {
            sortNames();
            printHurricanes( );
        }
        else if(choice == 6)
        {
            sortCategories();
            printHurricanes( );
        }
        else if(choice == 7)
        {
            sortPressures();
            printHurricanes( );
        }
        else if(choice == 8)
        {
            sortWindSpeeds(0, hurricanes.length - 1);
            printHurricanes( );
        }
        else if(choice == 9)
        {
            System.out.print("\n\tWhich year do you want to search for?\n\t");
            int year = in.nextInt();
            printHurricanes(searchYear(year));
        }
        else if(choice == 10)
        {
            System.out.print("\n\tWhich name do you want to search for?\n\t");
            String name = in.next();
            printHurricanes(searchHurricaneName(name));
        }
        else if (choice == 11)
        {
            done = true;
        }  
        return done;
    }

    /**
     * Oversees the interaction with the user until the user quits the program.
     * 
     * @param args  user's information from the command line
     * 
     * @throws IOException  if file with the hurricane information cannot be found
     */
    public static void main (String [] args) throws IOException
    {
        HurricaneOrganizerArrayYu cane = new HurricaneOrganizerArrayYu("hurricanedata.txt");
        boolean areWeDoneYet = false;
        while ( ! areWeDoneYet)
        {
            areWeDoneYet = cane.interactWithUser( );    
        }
    }
}
