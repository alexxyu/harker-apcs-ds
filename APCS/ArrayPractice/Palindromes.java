import java.util.ArrayList;
/**
 * Write a description of class Palindromes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Palindromes
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class Palindromes
     */
    public Palindromes()
    {
        System.out.println(reverseNumber(804));
        
        ArrayList<Integer> p = getListOfPalindromeInRange(99, 224);
        for(int i=0; i<p.size(); i++)
        {
            System.out.println("Number at "+i+": "+p.get(i));
        }
        
        System.out.println();
        int[] pals = getArrayOfPalindromeInRange(99, 224);
        for(int i=0; i<pals.length; i++)
        {
            System.out.println("Number at "+i+": "+pals[i]);
        }
    }

    public boolean isPalindrome(int num)
    {
        if(num == reverseNumber(num))
            return true;
        
        return false;
    }
    
    public int reverseNumber(int num)
    {
        int reverse = 0;
        while(num != 0)
        {
            reverse = reverse * 10 + num % 10;
            num /= 10;
        }
        
        return reverse;
    }
    
    public int countPalindromeInRange(int begin, int end)
    {
        int count = 0;
        for(int i=begin; i<=end; i++)
        {
            if(isPalindrome(i))
                count++;
        }
        
        return count;
    }
    
    public int[] getArrayOfPalindromeInRange(int start, int end)
    {
        int[] palindromes = new int[countPalindromeInRange(start, end)];
        
        int count = 0;
        for(int i=start; i<=end; i++)
        {
            if(isPalindrome(i))
            {
                palindromes[count] = i;
                count++;
            }
        }
        
        return palindromes;
    }
    
    public ArrayList<Integer> getListOfPalindromeInRange(int start, int end)
    {
        ArrayList<Integer> palindromes = new ArrayList<Integer>();
        
        for(int i=start; i<=end; i++)
        {
            if(isPalindrome(i))
                palindromes.add(i);
        }
        
        return palindromes;
    }
    
    public static void main(String[] args)
    {
        new Palindromes();
    }
}
