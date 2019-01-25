// The "Parabola" class.
// Date: Sept 17, 2018
// Author: Eric Zhou
// Purpose: To find the area under a parabola/curve
// Input: Keyboard
// Output: Screen
// --------------------------------

import java.awt.*;
import hsa.Console;

public class Parabola
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
	c = new Console ();
	boolean loop = true;

	while (loop == true)
	{
	    double aValue = -42;
	    double bValue = 1;
	    double cValue = -3;
	    double startX = 0;
	    double finishX = 0;

	    //getting a,b,c values & checking
	    // if b^2 -4ac < 0, there are no real roots
	    while (Math.pow (bValue, 2) - (4 * aValue * cValue) < 0)
	    {
		c.print ("What is the value of a: ");
		aValue = c.readDouble ();
		c.print ("What is the value of b: ");
		bValue = c.readDouble ();
		c.print ("What is the value of c: ");
		cValue = c.readDouble ();
		if (Math.pow (bValue, 2) - (4 * aValue * cValue) < 0)
		{
		    c.println ("The entered a, b and c values are invalid!");

		}
	    }
	    //getting domain & checking
	    while (finishX - startX > 10 || finishX - startX < -10 || finishX == startX)
	    {
		c.print ("What is the value of start domain x: ");
		startX = c.readDouble ();
		c.print ("What is the value of finish domain x: ");
		finishX = c.readDouble ();

		double firstX = startX;
		double secondX = finishX;
		if (finishX - startX < 10 || finishX - startX > -10)
		{
		    if (finishX < startX)
		    {
			finishX = firstX;
			startX = secondX;
		    }
		}
		if (finishX - startX > 10 || finishX - startX < -10)
		{
		    c.println ("The startX and finishX's difference must be <10 or >-10");
		}
		if (finishX == startX)
		{
		    c.println ("The starting x cannot equal to the ending x. ");
		}
	    }

	    double parabolaArea = calcParabolaArea (aValue, bValue, cValue, startX, finishX);

	    // prints
	    c.println ("Therefore, the area of the parabola with the domain of ");
	    c.print ("{x | ");
	    c.print (startX);
	    c.print (" <= x <= ");
	    c.print (finishX);
	    c.print ("; xER}");
	    c.print (" is about ");
	    c.print (parabolaArea);
	    c.print (" units squared. ");

	    //rerun
	    String rerun = "y";
	    do
	    {
		c.println ();
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


    // --------------------------------
    // Date: Sept 18, 2018
    // Author: Eric Zhou
    // Purpose: Finds the height of the parabola/curve
    // Input: a,b,c values of a quadratics, x value
    // Output: height
    // --------------------------------
    public static double findNewHeight (double a, double b, double c, double x)
    {

	double height = (a * (x * x) + b * x + c);

	return height;
    }


    // --------------------------------
    // Date: Sept 18, 2018
    // Author: Eric Zhou
    // Purpose: To find the area under a parabola/curve
    // Input: a,b,c values of a quadratic, and the domain (startX and finishX)
    // Output: Area
    // --------------------------------
    public static double calcParabolaArea (double a, double b, double c2, double startX, double finishX)
    {
	int n = 50; // iteration
	double x = 0;
	double prevArea = 0;
	double area = 1;
	double firstX = startX;
	double secondX = finishX;
	if (finishX < startX)
	{
	    startX = secondX;
	    finishX = firstX;
	}
	double width = (finishX - startX) / n;
	;
	double counter = 0; // to ensure it compares the prevArea to area


	while (((area - prevArea) < -0.00005) || (area - prevArea) > 0.00005 || counter < 2)
	{
	    prevArea = area;
	    area = 0;

	    for (x = startX ; x <= finishX ; x = x + width)
	    {
		area += +(findNewHeight (a, b, c2, x) * width);
	    }

	    n *= 2;
	    width = (finishX - startX) / n;
	    counter += 1;
	}

	return area;
    }
} // Parabola class
