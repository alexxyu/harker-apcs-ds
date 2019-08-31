import java.util.ArrayList;
public class Fibonacci
{
    public Fibonacci()
    {
        ArrayList<Integer> nums = getListOfFibonacciNumbers(5);
        for(int i=0; i<nums.size(); i++)
        {
            System.out.println(nums.get(i));
        }
    }
    
    public static void main(String[] args)
    {
        new Fibonacci();
    }
    
    public int[] getArrayOfFibonacciNumbers(int numberOfTerms)
    {
        int[] nums = new int[numberOfTerms];
        
        nums[0] = 1;
        nums[1] = 1;
        
        for(int i=2; i<numberOfTerms; i++)
        {
            nums[i] = nums[i-2] + nums[i-1];
        }
        
        return nums;
    }
    
    public ArrayList<Integer> getListOfFibonacciNumbers(int numberOfTerms)
    {
        ArrayList<Integer> nums = new ArrayList<Integer>();
        
        
        
        nums.add(1);
        nums.add(1);
        
        for(int i=2; i<numberOfTerms; i++)
        {
            int next = nums.get(i-2) + nums.get(i-1);
            nums.add(next);
        }
        
        return nums;
    }
}
