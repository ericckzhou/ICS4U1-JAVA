// -----------------------------------------------------------
// The "Digit_Sum" class.
// Date: Sept 6, 2018
// Author: Eric Zhou
// Purpose: To find the digit sum of an entered number
// Inputs: Keyboard
// Outputs: Screen/Console
// -----------------------------------------------------------

import java.awt.*;
import hsa.Console;

public class Digit_Sum
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
	c = new Console ();
    
	int number = 0;
	c.println ("Finding the Digit Sum!");
	c.print ("Enter a positive number: "); 
	number = c.readInt ();
	// Check for positive #
	if (number < 0)
	{
	    c.println("The number you have entered is invalid.");
	    c.print("Enter a positive number: ");
	    number = c.readInt ();
	}
	int digitSum = 0;
	
	//finding the digit sum
	while (number >0) 
	{
	    digitSum += number % 10;
	    number -= number % 10;
	    number /= 10;
	}
	int total = 0;
	// Check to see if sum is 2 digits or more.
	if (digitSum >= 10)
	{
	    total += digitSum % 10;
	    digitSum -= digitSum % 10;
	    digitSum /= 10;
	    total += digitSum;
	    digitSum -= digitSum %10;
	}
	else
	{
	    total = digitSum;
	}
	c.print ("The digit sum of the number entered is: ");
	c.print (total);
	c.print(".");

	
	// Place your program here.  'c' is the output console
    } // main method
} // Digit_Sum class
