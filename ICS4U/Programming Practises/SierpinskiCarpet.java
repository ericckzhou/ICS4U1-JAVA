// The "SierpinskiCarpet" class.
// Date: Sept 18, 2018
// Author: Eric Zhou
// Purpose: To draw squares
// Input: Keyboard
// Output: Screen
// --------------------------------
import java.awt.*;
import hsa.Console;

public class SierpinskiCarpet
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
	c = new Console (50, 150);
	boolean loop = true;
	
	while (loop == true)
	{
	c.clear();
	int x = -1;
	int y = -1;
	int r = -1;
	int level = -1;
	String rerun = "Y";
	
	while (x < 0 || y < 0 || r < 100 || x >400 || y > 400 || r > 1000 || level < 0 && level <10)
	{
	    c.print ("Enter the x-coordinate (1-400) : ");
	    x = c.readInt ();
	    
	    c.print ("Enter the y-coordinate:(1-400) ");
	    y = c.readInt ();
	    
	    c.print ("Enter the size (100-400) : ");
	    r = c.readInt ();
	    
	    c.print ("Enter the level (1-9): ");
	    level = c.readInt ();
	    
	}
	displaySierpinskiCarpet (x, y, r, level);
	
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
		    c.clear();
		} 
	    }
	}

	// Place your program here.  'c' is the output console
    } // main method

// -------------------------------
// Date: Sept 18, 2018
// Author: Eric Zhou
// Purpose: To call the method, recursiveSierpinskiCarpet in order to display it.
// Input: x,y coords, r = size, and level
// Output: Void
// --------------------------------
    public static void displaySierpinskiCarpet (int x, int y, int r, int level)
    {
	recursiveSierpinskiCarpet (x, y, r, level);
    }

// ---------------------------------
// Date: Sept 19, 2018
// Author: Eric Zhou
// Purpose: Draws squares recursively
// Input: x and y coords, r - size, level
// Output: void
// --------------------------------
    public static void recursiveSierpinskiCarpet (int x, int y, int r, int level)
    {
	if (level > 0)
	{
	    //8x called
	    
	    //Top left
	    recursiveSierpinskiCarpet (x, y, r /3, level -1);

	    //Top center
	    recursiveSierpinskiCarpet (x + (r /3), y, r /3, level -1);

	    //Top right
	    recursiveSierpinskiCarpet (x + (r /3) * 2, y, r /3, level -1);

	    //Middle left
	    recursiveSierpinskiCarpet (x, y + (r /3), r /3, level -1);

	    //Middle right
	    recursiveSierpinskiCarpet (x + (r /3) * 2, y + (r /3), r /3, level -1);

	    //Bottom left
	    recursiveSierpinskiCarpet (x, y + (r /3) * 2, r /3, level -1);

	    //Bottom center
	    recursiveSierpinskiCarpet (x + (r /3), y + (r /3) * 2, r /3, level -1);

	    //Bottom right
	    recursiveSierpinskiCarpet (x + (r /3) * 2, y + (r /3) * 2, r /3, level -1);
	    
	    
	    //Color for certain levels
	    if (level == 1)
	    {
		c.setColor(Color.black);
	    }
	    if (level == 2)
	    {
		c.setColor(Color.cyan);
	    }
	    if (level == 3)
	    {
		c.setColor(Color.gray);
	    }
	    if (level == 4)
	    {
		c.setColor(Color.blue);
	    }
	    if (level == 5)
	    {
		c.setColor(Color.green);
	    }
	    if (level == 6)
	    {
		c.setColor(Color.red);
	    }
	    if (level == 7)
	    {
		c.setColor(Color.magenta);
	    }
	    if (level == 8)
	    {
		c.setColor(Color.orange);
	    }
	    if (level == 9)
	    {
		c.setColor(Color.yellow);
	    }

	    //Creating the square
	    c.fillRect (x + r / 3, y + r / 3, r / 3, r / 3);
	}
    }
    
} // SierpinskiCarpet class
