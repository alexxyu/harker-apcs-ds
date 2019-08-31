import java.util.Scanner;

/**
 * ChangeMaker calculates the change the user is due in standard dollar format as well as 
 * in terms of coins. The program then prompts the user if they would like to make 
 * another transaction.
 * 
 * @author Alex Yu
 * @version September 6, 2016
 */
public class ChangeMaker
{
    private Scanner in;

    /**
     * Constructor for objects of class ChangeMaker
     */
    public ChangeMaker()
    {
        in = new Scanner(System.in);
    }

    /**
     * Asks the user for the purchase price.
     * 
     * @return the purchase price
     */
    public double getPurchasePrice()
    {
        System.out.print("Enter purchase price: $"); 
        return in.nextDouble();
    }

    /**
     * Asks the user for the amount of cash tendered .
     * 
     * @return the amount of cash tendered
     */
    public double getCashTendered()
    {
        System.out.print("Enter cash tendered: $");
        return in.nextDouble();
    }

    /**
     * Calculates and prints the amount of change in terms of cents that the user should receive.
     * 
     * @param cashTendered the user's given amount of cash
     * @param purchasePrice the payment amount
     * 
     * @return the amount of change
     */
    public int giveChange(double cashTendered, double purchasePrice)
    {
        int change = (int) ((cashTendered - purchasePrice + 0.0001) * 100);
        System.out.printf("Your change is $%.2f. \n", change / 100.0);
        return change;
    }

    /**
     * Checks if the user wants to make another transaction.
     * 
     * @return the user's reply
     */
    public boolean checkForNewTransaction()
    {
        System.out.print("Would you like to make another transaction (0 = no, 1 = yes)? ");
        if(in.nextInt()==1)
        {
            System.out.println("\n");
            return true;
        }

        System.out.println("Ok, have a nice day!");
        return false;
    }

    /**
     * Calculates and prints the change owed to the user in terms of coins.
     * 
     * @param change the amount of change in terms of cents
     */
    public void giveCoins(int change)
    {
        // calculate the amount of dollars
        int dollars = change / 100;

        // calculate the amount of each type of coin
        int coinChange = change % 100;
        int quarters = coinChange / 25;
        coinChange %= 25;
        int dimes = coinChange / 10;
        coinChange %= 10;
        int nickels = coinChange / 5;
        int pennies = coinChange;

        // print the calculated amounts
        System.out.println("Or "+dollars+" dollars, "+quarters+" quarters, "+dimes+" dimes, "
            +nickels+" nickels, and "+pennies+ " pennies.");
    }

    /**
     * Interacts with the user and displays the change that is owed for one transaction
     */
    public void processTransaction()
    {
        // ask user for purchase price and amount of cash tendered
        double purchasePrice = getPurchasePrice();
        double cashTendered = getCashTendered();

        // check if the user did not provide enough amount of cash
        if(cashTendered<purchasePrice)
        {
            System.out.println("Error: not enough cash was paid");
            return;
        }

        // print the change in standard format and give amount of coins
        int change = giveChange(cashTendered, purchasePrice);
        giveCoins(change);
    }

    /**
     * Oversees the transaction(s)
     */
    public void processMultipleTransactions()
    {
        processTransaction();
        
        while(checkForNewTransaction())
        {   
            processTransaction();
        }
    }

    /**
     * The main program for ChangeMaker 
     * 
     * @param args information read from the command line
     */
    public static void main(String[] args)
    {
        ChangeMaker changeMaker = new ChangeMaker();
        changeMaker.processMultipleTransactions();
    }
}
