// The "Recursion" class.
// Date: Sept 18, 2018
// Author: Eric Zhou
// Purpose: Learn the ways of Recursion
// Input: Keyboard
// Output: Screen/Console
// ---------------------------------------------------------------

import java.awt.*;
import hsa.Console;

public class Recursion
{
    static Console c;           // The output console

    public static void main (String[] args)
    {            
	c = new Console ();
	boolean loop = true;
	while (loop == true)
	{
	int countdownNum = -1;
	int m = -1;
	int n = -1;
	while (m <= 0 || n < 0 || countdownNum <=1)
	{
	    c.print("What is the first number (m > 0): ");
	    m = c.readInt();
	    c.print("What is the second number (n): ");
	    n = c.readInt();
	    c.print("What number do you want to count down from (up to 100): ");
	    countdownNum = c.readInt();
	    if (m <= 0 || n < 0 || countdownNum <=1 || countdownNum > 100)
	    {
		c.println("Invalid input!");
		if (m <= 0 || n < 0)
		{
		    c.println("The value of m or n is less than or equal to 0!");
		}
		if (countdownNum <=1 || countdownNum > 100)
		{
		    c.println("The countdown number must be in the range of 2 - 100!");
		}
		c.println();
	    }
	}
	long gcd = calcGCD (m, n);
	c.print("The greatest common divisor is ");
	c.print (gcd);
	c.println();
	c.print("Countdown: ");
	displayCountdown(countdownNum);
	String rerun = "y";
	do
	    {
		c.print ("Would you like to rerun the program? Y/N :");
		rerun = c.readString ();
	    }
	    while (!(rerun.equals ("y") || rerun.equals ("n") || rerun.equals ("Y") || rerun.equals ("N")));
	    {
		if (rerun.equals ("n") || rerun.equals ("N"))
		{
		    loop = false;
		} 
	    }
	}
	// Place your program here.  'c' is the output console
    } // main method

    // ----------------------------------------------------
    // Date: Sept 19, 2018
    // Author: Eric Zhou
    // Purpose: To find the greatest common divisor.
    // Input: long m and n values.
    // Output: The GCD
    // ----------------------------------------------------
    public static long calcGCD (long m, long n)
    {
	if (n == 0)
	{
	    return m;
	}
	else
	{
	    long gcd = calcGCD(n, m % n);
	    return gcd;
	}

    }
    
    // ----------------------------------------------------
    // Date: Sept 19, 2018
    // Author: Eric Zhou
    // Purpose: Counts down from n to 1
    // Input: integer n
    // Output: None
    // ----------------------------------------------------
     public static void displayCountdown(int n)
     {
	if (n == 1)
	{
	    c.println(1);
	}
	else
	{
	    c.print(n);
	    c.print(", ");
	    displayCountdown(n-1);
	}
     }
     

} // Recursion class
