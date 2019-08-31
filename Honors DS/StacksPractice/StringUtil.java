import java.util.Stack;

/**
 * StringUtil can reverse a String using a Stack and check if 
 * a given String is a palindrome.
 * 
 * @author Anu Datar
 * @author Alex Yu
 * @version 11/3/2017
 */
public class StringUtil
{
    //reverse a string using a Stack and substring not charAt
    public static String reverseString(String str)
    {
        Stack stack = new Stack();
        String reversed = "";
        while(str.length() > 0)
        {
            stack.push( str.substring(0, 1) );
            str = str.substring(1);
        }
        
        while(!stack.isEmpty())
            reversed += stack.pop();
        
        System.out.println(reversed);
        return reversed;
    }

    // must use reverse written above
    public static boolean isPalindrome(String s)
    {
        return s.equals( reverseString(s) );
    }

    // The tester for checking that reverse and isPalindrome work well.
    public static void main(String[] args)
    {
        String test =  "racecar";
        String test2 = "notapalindrome";

        if ( !("".equalsIgnoreCase(reverseString(""))) )
            System.out.println("** Oops Something went wrong. Check your reverse method **");
         
        if ( !("a".equalsIgnoreCase(reverseString("a"))) )
            System.out.println("** Oops Something went wrong. Check your reverse method **");

        if (!test.equalsIgnoreCase(reverseString(test)))
            System.out.println("** Oops Something went wrong. Check your reverse method **");
        else
            System.out.println("Success " + test + " matched " + reverseString(test));
            
        if (test2.equalsIgnoreCase(reverseString(test2)))
            System.out.println("** Oops Something went wrong. Check your reverse method **");

    }
}
