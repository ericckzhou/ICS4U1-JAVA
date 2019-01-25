// The "Upsidedown" class.
// Date: Sept 10, 2018
// Author: Eric Zhou
// Purpose: To determine numbers that are the same when flipped 180 degrees in a given interval
// Input: Keyboard
// Output: Console/Screen
// ---------------------------------------------------------------------------------------------
import java.awt.*;
import hsa.Console;

public class Upsidedown
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
	c = new Console ();
	int num = -1;
	//getting positive integer from user
	while (num < 0)
	{
	    c.print ("Enter a positive number for the interval: ");
	    num = c.readInt ();
	    if (num < 0)
	    {
		c.println ("The number entered is not positive!");
		c.println ("");
	    }
	    if (num > 0)
	    {
		c.println ("Success!");
		c.println ("");
	    }
	}
	int flippedNum = 0; // 180
	int storeNum = 1;
	int currentNum = 1;
	int flippedStatus = 1; // to determine if the # is worthy of being the same when flipped
	
	//formating
	c.print ("[");
	c.print (storeNum);
	c.print ("-");
	c.print (num);
	c.print ("]: ");
	
	// the loop will go through from 1 - num + 1
	// will not do num if it stops at num thus num + 1
	while (storeNum != num + 1)
	{
	    while (currentNum > 0)
	    {
		// 1,6,8,9,0 works when flipped upsidedown
		if (currentNum % 10 == 6)
		    flippedNum += 9;
		else if (currentNum % 10 == 9)
		    flippedNum += 6;
		else if (currentNum % 10 == 1 || currentNum % 10 == 8 || currentNum % 10 == 0)
		    flippedNum += currentNum % 10;
		else // disable upsidedown if the # has 2,3,4,5,6,7
		    flippedStatus = -1;

		currentNum -= currentNum % 10;
		currentNum /= 10;
		flippedNum *= 10;
	    }
	    // if the flipped # is the same as the number when it isn't flipped
	    // flipped # is being multiplied by an extra 10.
	    if (flippedNum / 10 == storeNum && flippedStatus == 1)
	    {
		c.print (flippedNum / 10);
		c.print (", ");
	    }
	    // reset
	    if (currentNum == 0)
	    {
		storeNum++;
		currentNum = storeNum;
		flippedNum = 0;
		flippedStatus = 1;
	    }
	}
	
	// Place your program here.  'c' is the output console
    } // main method
} // Upsidedown class
