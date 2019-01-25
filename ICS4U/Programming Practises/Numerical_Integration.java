// The "Numerical_Integration" class.
// ---------------------------------------------------------
// Date: Sept 14, 2018
// Author: Eric Zhou
// Purpose: Calculate area under a curve by making rectangles
// Input: Keyboard
// Output: Screen
// ---------------------------------------------------------
import java.awt.*;
import hsa.Console;

public class Numerical_Integration
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
	c = new Console ();
	boolean loop = true;
	String rerun = "Y";
	while (loop == true)
	{
	    double r = -1;
	    while (r <= 0)
	    {
		c.print ("What is the value of the radius (r): ");
		r = c.readDouble ();
		if (r <= 0)
		{
		    c.println ();
		    c.print ("Invalid Input! r must be greater than 0!");
		    c.println ();
		}
	    }
	    double getArea = findArea (r);
	    c.print ("The semicircle with the radius of ");
	    c.print (r);
	    c.println (" has an area of ");
	    c.print (getArea);
	    c.print (" units square. ");
	    c.println ();
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


    // ---------------------------------------------------------
    // Date: Sept 14, 2018
    // Author: Eric Zhou
    // Purpose: To find the height of the semi-circle
    // Input: radius, r & x-value
    // Output: Height of the semi circle
    // ---------------------------------------------------------
    public static double findHeight (double x, double r)
    {
	double height = Math.sqrt ((Math.pow (r, 2)) - (Math.pow ((x - r), 2)));
	return height;
    }


    // ---------------------------------------------------------
    // Date: Sept 15, 2018
    // Author: Eric Zhou
    // Purpose: Finding the area of the semi-circle using numerical integration.
    // Input: The radius, r
    // Output: Area of the semi-circle
    // ---------------------------------------------------------
    public static double findArea (double r)
    {
	double n = 50; // iteration
	double width = ((2 * r) / n);
	double x = 0;
	double height = findHeight (x, r);
	double area = 0;
	double prevArea = -1;

	while ((area - prevArea) > 0.00005 || (area - prevArea) < -0.00005 && r != 0)
	{
	    prevArea = area;
	    area = 0;

	    for (x = 0 ; x <= ((n - 1) * width) ; x += width)
	    {
		height = findHeight (x, r);
		area = area + (height * width);
	    }

	    n *= 2;
	    width = ((2 * r) / n);
	}
	return area;

    }
} // Numerical_Integration class
