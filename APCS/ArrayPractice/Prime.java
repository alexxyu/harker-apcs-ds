import java.util.ArrayList;

public class Prime
{
    public Prime()
    {
        ArrayList<Integer> numbers = getListOfPrimesInRange(3, 11);
        
        for (int i=0; i<numbers.size(); i++)
        {
            System.out.println("numbers\' " + i + " element = " + numbers.get(i));
        }
        
        int[] primes = getArrayOfPrimesInRange(3, 11);
        
        for(int i=0; i<primes.length; i++)
        {
            System.out.println("numbers\' " + i + " element = " + primes[i]);
        }
    }
    
    public static void main(String[] args)
    {
        new Prime();
    }
    
    public ArrayList<Integer> getListOfPrimesInRange(int low, int high)
    {
        ArrayList<Integer> primes = new ArrayList<Integer>();
        
        for(int i=low; i<=high; i++)
        {
            if(isPrime(i))
                primes.add(i);
        }
        
        return primes;
    }
    
    public boolean isPrime(int num)
    {
        for(int i=2; i<=num/2; i++)
        {
            if(num % i == 0)
                return false;
        }
        
        return true;
    }
    
    public int countPrimesInRange(int begin, int end)
    {
        int count = 0;
        
        for(int i=begin; i<=end; i++)
        {
            if(isPrime(i))
                count++;
        }
        
        return count;
    }
    
    public int[] getArrayOfPrimesInRange(int start, int end)
    {
        int[] primes = new int[countPrimesInRange(start, end)];
        
        int index = 0;
        for(int j=start; j<=end; j++)
        {
            if(isPrime(j))
            {
                primes[index]=j;
                index++;
            }
        }
        
        return primes;
    }
}
