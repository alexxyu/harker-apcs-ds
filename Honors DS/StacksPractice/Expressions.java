import java.util.*;

/**
 * Expressions can check for parentheses balancing, convert from
 * infix to postfix notation, and evaluate postfix notation.
 * 
 * @author Anu Datar
 * @author Alex Yu
 * @version 11/3/2017
 */

public class Expressions
{
    // parenthesis matching : An expression is said to be balanced if
    // every opener has a corresponding closer, in the right order
    // {, [ or ( are the only types of brackets allowed
    // @param   expression containing operands operators 
    //          and any of the 3 supportedbrackets
    // @return  true is the parenthesis are balanced         
    //          false otherwise
    public static boolean matchParenthesis(String expression)
    {
        Stack<String> openSymbols = new Stack<String>();
        
        Scanner scanner = new Scanner(expression);
        while(!expression.isEmpty())
        {
            String ch = expression.substring(0, 1);
            if(ch.equals("(") || ch.equals("{") || ch.equals("["))
                openSymbols.push(ch);
                
            else if(ch.equals(")") || ch.equals("}") || ch.equals("]"))
            {
                if( openSymbols.isEmpty() ) return false;
                String open = openSymbols.pop();
                if( !areMatchedParentheses(open, ch) ) return false;
            }
            expression = expression.substring(1);
        }
        
        return openSymbols.isEmpty();
    }
    
    // checks to see if given open and closed symbols correspond
    // @param p1    the opening symbol
    // @param p2    the closed symbol
    // @return true if the symbols correspond; else,
    //         false
    private static boolean areMatchedParentheses(String p1, String p2)
    {
        if(p1.equals("(") && p2.equals(")")) return true;
        if(p1.equals("{") && p2.equals("}")) return true;
        if(p1.equals("[") && p2.equals("]")) return true;
        
        return false;
    }
    
    // returns a string in postfix form 
    // if given an expression in infix form as a parameter
    // does this conversion using a Stack
    // @param expr valid expression in infix form
    // @return equivalent expression in postfix form
    public static String infixToPostfix(String expr)
    {
        Stack<String> postFix = new Stack<String>();
        String strPostfix = "";
        
        Scanner scanner = new Scanner(expr);
        while( scanner.hasNext() )
        {
            if(scanner.hasNextInt())
                strPostfix += scanner.nextInt() + " ";
            else
            {
                String op = scanner.next();
                if( !postFix.isEmpty() && (op.equals("+") || op.equals("-")) &&
                         (postFix.peek().equals("*") || postFix.peek().equals("/") ||
                          postFix.peek().equals("%"))  )
                {
                    while( !postFix.isEmpty() )
                        strPostfix += postFix.pop() + " ";
                    postFix.push(op);
                }
                        
                else
                    postFix.push(op);
            }
        }
        
        while( !postFix.isEmpty() )
            strPostfix += postFix.pop() + " ";

        return strPostfix;
    }

    // returns the value of an expression in postfix form
    // does this computation using a Stack
    // @param expr valid expression in postfix form
    // @return value of the expression
    // @precondition postfix expression  
    //               contains numbers and operators + - * / and %
    //               and that operands and operators are separated by spaces
    public static double evalPostfix(String expr)
    {
        Stack<Integer> postfixOperands = new Stack<Integer>();

        Scanner scanner = new Scanner(expr);
        while( scanner.hasNext() )
        {
            if(scanner.hasNextInt())
                postfixOperands.push(scanner.nextInt());
            else
            {
                Integer num1 = postfixOperands.pop();
                Integer num2 = postfixOperands.pop();
                String op = scanner.next();
                if(op.equals("+"))
                    postfixOperands.push( num2 + num1 );
                    
                else if(op.equals("-"))
                    postfixOperands.push( num2 - num1 );
                        
                else if(op.equals("*"))
                    postfixOperands.push( num2 * num1 );
                    
                else if(op.equals("/"))
                    postfixOperands.push( num2 / num1 );
                        
                else if(op.equals("%"))
                    postfixOperands.push( num2 % num1 );
                
                else throw new IllegalArgumentException();
            }
        }
        
        return postfixOperands.pop();
    }

    // Tester to check if infix to postfix and evaluate postfix work well
    public static void main(String[] args)
    {
        String exp = "2 + 3 * 4";
        test(exp, 14);

        exp = "8 * 12 / 2";
        test(exp, 48);

        exp = "5 % 2 + 3 * 2 - 4 / 2";
        test(exp, 5);   

        // test balanced expressions
        testBalanced("{ 2 + 3 } * ( 4 + 3 )", true);
        testBalanced("} 4 + 4 { * ( 4 + 3 )", false);
        testBalanced("[ [ [ ] ]", false);
        testBalanced("{ ( } )", false);
        testBalanced("( ( ( ) ) )", true);
    }

    public static void test(String expr, double expect)
    {
        String post = infixToPostfix(expr);    
        double val = evalPostfix(post);

        System.out.println("Infix: " + expr);
        System.out.println("Postfix: " + post);
        System.out.println("Value: " + val);
        if (val == expect)
        {
            System.out.println("** Success! Great Job **");
        }
        else
        {
            System.out.print("** Oops! Something went wrong. ");
            System.out.println("Check your postfix and eval methods **");
        }
    }

    public static void testBalanced(String ex, boolean expected)
    {
        boolean act = matchParenthesis(ex);
        if (act == expected)
            System.out.println("** Success!: matchParenthesis(" + ex + ") returned " + act);
        else
        {
            System.out.print("** Oops! Something went wrong check : matchParen(" + ex + ")");
            System.out.println(" returned " + act + " but should have returned " + expected);
        }
    }
}
