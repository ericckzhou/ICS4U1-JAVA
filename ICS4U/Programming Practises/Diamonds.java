// ------------------------------------------------
// The "Diamonds" class.
// Date: Sept 6, 2018
// Author: Eric Zhou
// Purpose: To generate an diamond with *s (odd size)
// Inputs: Keyboard
// Outputs: Screen/Console
// ------------------------------------------------

import java.awt.*;
import hsa.Console;

public class Diamonds
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
	c = new Console ();

	int shape = 0;
	while (shape != 1 && shape != -1)
	{
	    c.println("Diamond = 1 or Hourglass = -1");
	    c.print("Select 1 of the following shape above: ");
	    shape = c.readInt();

	    if (shape != 1 && shape != -1)
	    {
		c.println();
		c.println("Invalid shape input. Choose either 1 or -1 please.");
	    }
	}
	c.println();
	int shapeSize = 0;
	c.print ("Enter your shape's size (odd # 1-21): ");
	shapeSize = c.readInt ();

	while (shapeSize % 2 == 0 || shapeSize > 21 || shapeSize < 0)
	{
	    c.println ("");
	    c.println ("The number you have entered is invalid.");
	    if (shapeSize % 2 == 0)
		c.println ("The number you have entered is not an odd number");
	    else if (shapeSize > 21 || shapeSize < 0)
		c.println ("The number you have entered is not within the range 1 - 25");
	    
	    c.println("");
	    c.print ("Enter your diamond's size (odd # 1-21): ");
	    shapeSize = c.readInt ();
	}
	int hollow = 0;
	while (hollow != 1 && hollow != -1)
	{
	    c.print("Hollow Shape: 1 = Yes, -1 = No: ");
	    hollow = c.readInt();
	    if (hollow != 1 && hollow != -1)
	    {
		c.println();
		c.println("Invalid Hollow Number!");
	    }
	}
      

	    int space = 0;
	    int spaceCounter = 0;
	    int starChecker = 0;
	    int sizeDetect = 0;
	    int rowDetect = 0;
	    int starCounter = 0;
	    c.println();
	    if (shape == 1) // diamond initial setup
	    {
		space = ((shapeSize / 2) + 1); // initial space
		spaceCounter = 0;
		starCounter = 1; //first row is always 1 star
		starChecker = 0;
		// 2 rows to get to middle size = 1
		// 3 rows to get to middle size = 3

		// determining the # of rows to reach center of diamond
		sizeDetect = 1; //dummy size being used/changed
		rowDetect = 2; //starting row = 2 for it to reach the center for size = 1
	    }
	    if (shape == -1)
	    { // hourglass initial setup
		space = 0;
		spaceCounter = 0;
		starCounter = shapeSize + 2;
		starChecker = 0;
		sizeDetect = 1;
		rowDetect = 2;
	    }

	    while (sizeDetect != shapeSize)
	    {
		sizeDetect += 2; // getting the dummy size to equal shapeSize
		rowDetect += 1; //  determining the # of rows there are
	    }
	    int row = 0;
	    //first half of the star
	    while (row != rowDetect)
	    {
		while (spaceCounter != space)
		{
		//PRINTS the required space
		    if (space >= 0)
		    {
			c.print ("  ");
			spaceCounter++;
		    }
		}

		if (spaceCounter == space) // if required space is met, prints star
		{
		    while (starChecker != starCounter)
		    {
			if (hollow == -1)
			{
			    c.print ("* ");
			    starChecker++;
			}
			if (hollow == 1)
			{
			    if (starChecker == 0 || starChecker == starCounter -1)
			    {
				c.print ("* "); // place star around the perimeter of the star
				starChecker++;
			    }
			    else if (row == 0 || row == rowDetect -1 && shape == -1)
			    // for hourglass, u must print all stars
			    {
				c.print("* ");
				starChecker++;
			    }
			    else if (row != 0 || row != rowDetect -1)
			    {
				c.print("  "); // print space between the stars
				starChecker++;
			    }
			     
			}
		    }
		    if (shape == 1)
		    {
			row++;
			space--;
			spaceCounter = 0;
			starCounter += 2; //increase of 2 per row for first half
			starChecker = 0;
			c.println ("");
		    }
		    if (shape == -1) 
		    {
			row++;
			space++;
			spaceCounter = 0;
			starCounter -= 2;// per row, star - 2 for second half of hourglass
			starChecker = 0;
			c.println("");
		    }
		}
	    }
	    //second half of the star
	    if (shape == 1)
	    {
		space = 1; // reset on space
		starCounter -= 4; // setting to correct # of stars
	    }
	    if (shape == -1)
	    {
		space = (shapeSize / 2);
		starCounter = ((shape / 2 + 1) + 2);
	    }
	    while (row != sizeDetect + 2)
	    {
		while (spaceCounter != space)
		{
		    if (space >= 0)
		    {
		       c.print ("  ");
			spaceCounter++;
		    }
		}
    // To see if the # of space needed is correct so a star can be printed
		if (spaceCounter == space)
		{
		    while (starChecker != starCounter)
		    {
			if (hollow == -1)
			{
			    c.print ("* ");
			    starChecker++;
			}
			if (hollow == 1) // hollow true
			{
			    if (starChecker == 0 || starChecker == starCounter -1) // place star for the ends
			    {
				c.print("* ");
				starChecker++;
			    }
			    else if (row == sizeDetect +1 && shape == -1) // for hourglass, u must print all stars
			    {
				c.print("* ");
				starChecker++;
			    }
			    else if (row != sizeDetect +1)// place space in between stars
			    {
				c.print("  ");
				starChecker++;
			    }
			}
		    }
		    if (shape == 1)
		    {
			row++;
			space++;
			spaceCounter = 0;
			starCounter -= 2; //decrease of 2 per row for 2nd half
			starChecker = 0;
			c.println ("");
		    }
		    if (shape == -1)
		    {
			row++;
			space--;
			spaceCounter = 0;
			starCounter+=2;
			starChecker = 0;
			c.println("");
		    }
		}
	    }
	
	// Place your program here.  'c' is the output console
    } // main method
} // Diamonds class
