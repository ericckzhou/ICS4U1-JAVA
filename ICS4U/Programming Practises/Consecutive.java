// The "Consecutive" class.
// Date: Sept 8, 2018
// Author: Eric Zhou
// Purpose: To find series of consecutive numbers that add up to number requested.
// Input: Keyboard
// Output: Console/Screen
// -----------------------------------------------------------------------------

import java.awt.*;
import hsa.Console;

public class Consecutive
{
    static Console c;           // The output console
    
    public static void main (String[] args)
    {
	c = new Console ();
	
	int firstNum = 0;
	int secondNum = 0;
	int numStatus = -1;
	
	// Getting number
	while (numStatus == -1)
	{
	    c.print("Select the first number for your range: ");
	    firstNum = c.readInt();
	    c.print("Select the second number for your range: ");
	    secondNum = c.readInt();
	    // Check if its positive
	    if (firstNum < 0 || secondNum < 0)
	    {
		c.println("One or both of the numbers entered was not greater than 0!");
		c.println("Please try again!");
	    }
	    if (firstNum > 0 && secondNum > 0)
	    {
		numStatus = 1;
		c.println("Success!");
	    }
	}
	
	int storeFirstNum = firstNum;
	int storeSecondNum = secondNum;
	
	// First number = smallest, second number = biggest
	if (firstNum > secondNum)
	{
	    firstNum = storeSecondNum;
	    secondNum = storeFirstNum;
	}
	
	storeFirstNum = firstNum; //the first number being used/changed
	storeSecondNum = secondNum; // the second number being used/changed
	
	int addNum = 0; // number being added towards totalSum
	int storeNum = 1; //starting number
	int totalSum = 0;
	int printStatus = 1; //once all possibilities for a number is checked, the print status will change
	int seriesCounter = 0; // finds the #(s) of consec series for a #
	
	c.print("You have chosen the range: ");
	c.print(firstNum);
	c.print(" - ");
	c.print(secondNum);
	
	// Finding the consecutive series for each number in the range
	while (storeFirstNum != storeSecondNum +1)  
	{
	    // Prints the current # it is finding consecutive possibilites for.
	    if (printStatus == 1)
	    {
		c.println("");
		c.print(storeFirstNum);
		c.print(": ");
		printStatus = -1;
	    }
	    // Will keep adding until it is greater than first #
	    if (totalSum < storeFirstNum)
	    {
		totalSum += addNum;
		addNum++;
	    }
	    // NO possibilities found within the current "storeNum"
	    if (totalSum > storeFirstNum)
	    {
		totalSum = 0;
		storeNum++;
		addNum = storeNum;
	    }
	    // Consecutive series found that equals to the number in range
	    if (totalSum == storeFirstNum)
	    {
		totalSum = 0;
		c.print(storeNum);
		c.print("-");
		c.print(addNum -1); //addNum was added by 1 before printed
		c.print(" | ");
		seriesCounter++;
		storeNum++;
	    }
	    // All the possibilities are found for a number
	    // A reset is needed to find the possibilities for the next #
	    if (storeNum == storeFirstNum)
	    {
		totalSum = 0;
		storeNum = 1;
		addNum = 0;
		c.print("(");
		c.print(seriesCounter);
		c.print(")");
		printStatus = 1;
		seriesCounter = 0;
		storeFirstNum++;
	    }
	}
	
	// Place your program here.  'c' is the output console
    } // main method
} // Consecutive class
