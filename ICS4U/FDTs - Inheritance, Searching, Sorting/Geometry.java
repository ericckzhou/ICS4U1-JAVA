// The "Geometry" class.
/*-----------------------------------------------
Author: Eric Zhou
Date: Nov 01, 2018
Purpose: To complete inheritance exercise.
Input: Keyboard
Output: Console
------------------------------------------------*/
import java.awt.*;
import hsa.Console;

public class Geometry
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
	c = new Console (25, 100);
	boolean loop = true;
	while (loop == true)
	{
	    int size = 1;
	    Shape[] shape = new Shape [size];
	    boolean addShape = true;
	    //adding shape from user to database "shape"
	    while (addShape == true)
	    {
		c.println ("1. Circle \n2. Rectangle \n3. Triangle \n4. Regular Pentagon \n5. Rhombus");
		c.println ("6. Regular Hexagon \n7. Regular Octagon \n8. Sphere \n9. Cube \n10. Cylinder");
		c.print ("Select the shape's number you would like to create: ");
		int shapeNum = c.readInt ();
		//determining shape user wants
		if (shapeNum == 1)
		{
		    shape [size - 1] = new Circle ();
		}
		else if (shapeNum == 2)
		{
		    shape [size - 1] = new Rectangle ();
		}
		else if (shapeNum == 3)
		{
		    shape [size - 1] = new Triangle ();
		}
		else if (shapeNum == 4)
		{
		    shape [size - 1] = new Pentagon ();
		}
		else if (shapeNum == 5)
		{
		    shape [size - 1] = new Rhombus ();
		}
		else if (shapeNum == 6)
		{
		    shape [size - 1] = new Hexagon ();
		}
		else if (shapeNum == 7)
		{
		    shape [size - 1] = new Octagon ();
		}
		else if (shapeNum == 8)
		{
		    shape [size - 1] = new Sphere ();
		}
		else if (shapeNum == 9)
		{
		    shape [size - 1] = new Cube ();
		}
		else if (shapeNum == 10)
		{
		    shape [size - 1] = new Cylinder ();
		}

		shape [size - 1].get (c);
		Shape[] old = shape;
		String userAdd = "Y";
		do
		{
		    c.print ("Would you like to continue adding shapes? Y/N: ");
		    userAdd = c.readLine ();
		}
		while (!(userAdd.toUpperCase ().equals ("Y") || userAdd.toUpperCase ().equals ("N")));
		{
		    if (userAdd.toUpperCase ().equals ("N"))
		    {
			addShape = false;
		    }
		    if (userAdd.toUpperCase ().equals ("Y"))
		    {
			size++;
			shape = new Shape [size];
			for (int i = 0 ; i < size - 1 ; i++)
			{
			    shape [i] = old [i];
			}
			c.clear ();
		    }
		}
	    }
	    c.println ();
	    c.clear ();
	    //displaying contents of each shape
	    boolean displayLoop = true;
	    while (displayLoop == true)
	    {
		int displayNum = -1;
		do
		{
		    c.println ("Total shape(s) created: " + size);
		    c.print ("Which shape (number) would you like to display? (1 - total): ");
		    displayNum = c.readInt ();
		    if (displayNum <= 0 || displayNum > size)
		    {
			c.println ("Invalid shape number!");
		    }
		}
		while (displayNum <= 0 || displayNum > size);
		{
		}
		c.clear ();
		shape [displayNum - 1].put (c);
		c.println ();
		String displayRun = "Y";
		do
		{
		    c.print ("Would you like to display another shape's contents? Y/N: ");
		    displayRun = c.readLine ();
		}
		while (!(displayRun.toUpperCase ().equals ("Y") || displayRun.toUpperCase ().equals ("N")));
		{
		    if (displayRun.toUpperCase ().equals ("N"))
		    {
			displayLoop = false;
		    }
		    if (displayRun.toUpperCase ().equals ("Y"))
		    {
			c.clear ();
		    }
		}
	    }

	    //rerun
	    String rerun = "Y";
	    do
	    {
		c.print ("Would you like to rerun the program Y/N: ");
		rerun = c.readLine ();
	    }
	    while (!(rerun.toUpperCase ().equals ("Y") || rerun.toUpperCase ().equals ("N")));
	    {
		if (rerun.toUpperCase ().equals ("N"))
		{
		    loop = false;
		}
		if (rerun.toUpperCase ().equals ("Y"))
		{
		    c.clear ();
		}
	    }
	}
	// Place your program here.  'c' is the output console
    } // main method
} // Geometry class
/*--------------------------------------------------
Class: Shape
Author: Eric Zhou
Date: Nov 01, 2018
Purpose: The parent of all the other shapes.
Data Element:
    color: a color object (if i decide to draw)
    strColor: the color in string.
Methods:
    get: gets the color from user.
    put: To display the color to the user.
-----------------------------------------------------*/
abstract class Shape
{
    protected Color color;
    protected String strColor;

    public Shape ()  //default
    {
	this.color = Color.black;
	this.strColor = "black";
    }
    public Shape (String getColor)
    {
	this.color = Color.getColor(getColor);
	this.strColor = getColor;
    }

    /*----------------------------------
    Author: Eric Zhou
    Date: Nov 01, 2018
    Purpose: To get the color from user.
    Input: Console
    Output: None
    -----------------------------------*/
    public void get (Console c)
    {
	String getColor = "1";
	do
	{
	    c.println ();
	    c.println ("Colors: [Red, Orange, Yellow, Green, Blue, Magenta]");
	    c.print ("Choose a color from the list above: ");
	    getColor = c.readLine ();
	    getColor = getColor.toLowerCase ();
	    if (!(getColor.equals ("red") || getColor.equals ("orange") || getColor.equals ("yellow") || getColor.equals ("green") || getColor.equals ("blue") || getColor.equals ("magenta")))
	    {
		c.println ("Invalid color chosen!");
	    }

	}
	while (!(getColor.equals ("red") || getColor.equals ("orange") || getColor.equals ("yellow") || getColor.equals ("green") || getColor.equals ("blue") || getColor.equals ("magenta")));
	this.strColor = getColor;
	this.color = Color.getColor (this.strColor);
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 01, 2018
    Purpose: To display color to the user.
    Input: Console
    Output: None
    -----------------------------------------------*/
    public void put (Console c)
    {
	c.setColor (this.color);
	c.println ("Color: " + this.strColor);
    }
} //class Shape
/*--------------------------------------------------
Class: D2shape
Author: Eric Zhou
Date: Nov 01, 2018
Purpose: To hold contents of 2Dshape
Data Element:
    locX: location of 2dshape in x axis
    locY: location of 2dshape in y axis
    PI: 3.14...
Methods:
    perimeter: calculates the perimeter of the 2dshape
    area: calculates the area of the 2dshape
    get: gets the necessary information for class fields from user
    put: To display class fields and method
-----------------------------------------------------*/
abstract class D2shape extends Shape
{
    protected int locX, locY;
    final static double PI = 3.141592654; //constant

    public D2shape ()
    {
	super ();
    }
    public D2shape (int getLocX, int getLocY)
    {
	super();
	this.locX = getLocX;
	this.locY = getLocY;
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 01, 2018
    Purpose: calculates the perimeter of the 2dshape
    Input: None
    Output: Perimeter
    -----------------------------------------------*/
    abstract public double perimeter ();
    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 01, 2018
    Purpose: calculates the area of the 2dshape
    Input: None
    Output: area
    -----------------------------------------------*/
    abstract public double area ();
    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 01, 2018
    Purpose: gets the necessary information from user
    Input: Console
    Output: None
    -----------------------------------------------*/
    public void get (Console c)
    {
	super.get (c);
	//can be negative
	c.print ("What is the location of X?: ");
	this.locX = c.readInt ();
	c.print ("What is the location of Y?: ");
	this.locY = c.readInt ();
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 01, 2018
    Purpose: Display the locX and locY values
    Input: Console
    Output: None
    -----------------------------------------------*/
    public void put (Console c)
    {
	super.put (c);
	c.println ("Location of X: " + this.locX);
	c.println ("Location of Y: " + this.locY);
    }
} //class D2shape
/*--------------------------------------------------
Class: Circle
Author: Eric Zhou
Date: Nov 01, 2018
Purpose: Holding circle contents.
Data Element:
    radius: the radius of the circle
Methods:
    perimeter: calculates the perimeter of the circle (circumference)
    area: calculates the area of the circle
    get: gets the necessary information for class fields from user
    put: To display class fields and method
-----------------------------------------------------*/
class Circle extends D2shape
{
    protected double radius;

    public Circle ()
    {
	super ();
    }
    public Circle (double getRadius)
    {
	super();
	this.radius = getRadius;
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 01, 2018
    Purpose: calculates the perimeterof the circle
    Input: None
    Output: perimeter
    -----------------------------------------------*/
    public double perimeter ()  //circumference
    {
	double calcPer = 2 * this.PI * this.radius;
	return calcPer;
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 01, 2018
    Purpose: calculates the area of the circle
    Input: None
    Output: area
    -----------------------------------------------*/
    public double area ()
    {
	double calcArea = this.PI * Math.pow (this.radius, 2);
	return calcArea;
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 01, 2018
    Purpose: gets the necessary information for class fields from user
    Input: Console
    Output: None
    -----------------------------------------------*/
    public void get (Console c)
    {
	super.get (c);
	do
	{
	    c.print ("What is the radius of the circle: ");
	    this.radius = c.readDouble ();
	    if (this.radius < 0)
	    {
		c.println ("Radius must be positive!");
	    }
	}
	while (this.radius < 0);
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 01, 2018
    Purpose: Display class fields and methods
    Input: Console
    Output: None
    -----------------------------------------------*/
    public void put (Console c)
    {
	c.println ("Circle");
	super.put (c);
	c.println ("Radius: " + this.radius + " units.");
	c.println ("The circumference of the circle is " + this.perimeter () + " units.");
	c.println ("The area of the circle is " + this.area () + " units squared.");
    }
}
/*--------------------------------------------------
Class: Rectangle
Author: Eric Zhou
Date: Nov 01, 2018
Purpose: Holding Rectangle contents.
Data Element:
    width: the width of rectangle
    height: the height of rectangle
Methods:
    perimeter: calculates the perimeter of the rectangle
    area: calculates the area of the rectangle
    get: gets the necessary information for class fields from user
    put: To display class fields and method
-----------------------------------------------------*/
class Rectangle extends D2shape
{
    protected double width, height;

    public Rectangle ()
    {
	super ();
    }
    public Rectangle(double getWid, double getHeight)
    {
	super();
	this.width = getWid;
	this.height = getHeight;
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 01, 2018
    Purpose: calculates the perimeter of the rectangle
    Input: None
    Output: perimeter
    -----------------------------------------------*/
    public double perimeter ()
    {
	double calcPer = (this.width * 2) + (this.height * 2);
	return calcPer;
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 01, 2018
    Purpose: calculates the area of the rectangle
    Input: None
    Output: area
    -----------------------------------------------*/
    public double area ()
    {
	double calcArea = this.width * this.height;
	return calcArea;
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 01, 2018
    Purpose: gets the necessary information for class fields from user
    Input: Console c
    Output: None
    -----------------------------------------------*/
    public void get (Console c)
    {
	super.get (c);
	do
	{
	    c.print ("What is the width of the rectangle: ");
	    this.width = c.readDouble ();
	    c.print ("What is the height of the rectangle: ");
	    this.height = c.readDouble ();
	    if (this.width < 0 || this.height < 0)
	    {
		c.println ("One of the entered dimension is invalid!");
		c.println ("The dimensions must be greater than 0!");
	    }
	}
	while (this.width < 0 || this.height < 0);
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 01, 2018
    Purpose: display class fields and methods
    Input: Console c
    Output: None
    -----------------------------------------------*/
    public void put (Console c)
    {
	c.println ("Rectangle");
	super.put (c);
	c.println ("Width: " + this.width + " units.");
	c.println ("Height: " + this.height + " units.");
	c.println ("The perimeter of the rectangle is " + this.perimeter () + " units.");
	c.println ("The area of the rectangle is " + this.area () + " units squared.");
    }
} //class Rectangle
/*--------------------------------------------------
Class: Triangle
Author: Eric Zhou
Date: Nov 01, 2018
Purpose: Holding Triangle contents.
Data Element:
    sideA: 1st length of the triangle
    sideB: 2nd length of the triangle
    sideC: 3rd of the length of the triangle
Methods:
    perimeter: calculates the perimeter of the Triangle
    area: calculates the area of the Triangle
    get: gets the sidelengths of the triangle
    put: displays sidelengths, area, perimeter of the triangle
-----------------------------------------------------*/
class Triangle extends D2shape
{
    protected double sideA, sideB, sideC;

    public Triangle ()
    {
	super ();
    }
    public Triangle(double a, double b, double c)
    {
	this.sideA = a;
	this.sideB = b;
	this.sideC = c;
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 01, 2018
    Purpose: calculates the perimeter of the triangle
    Input: None
    Output: perimeter
    -----------------------------------------------*/
    public double perimeter ()
    {
	double calcPer = this.sideA + this.sideB + this.sideC;
	return calcPer;
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 01, 2018
    Purpose: calculates the area of the triangle
    Input: None
    Output: area
    -----------------------------------------------*/
    public double area ()
    {
	double s = (this.sideA + this.sideB + this.sideC) / 2;
	double calcArea = Math.sqrt (s * (s - this.sideA) * (s - this.sideB) * (s - this.sideC));
	return calcArea;
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 01, 2018
    Purpose: gets the sidelengths of the triangle
    Input: Console c
    Output: None
    -----------------------------------------------*/
    public void get (Console c)
    {
	super.get (c);
	do
	{
	    c.print ("What is first length of triangle: ");
	    this.sideA = c.readDouble ();
	    c.print ("What is second length of triangle: ");
	    this.sideB = c.readDouble ();
	    c.print ("What is third length of triangle: ");
	    this.sideC = c.readDouble ();
	    if (this.sideC < 0 || this.sideB < 0 || this.sideC < 0 || this.sideA >= (this.sideB + this.sideC) || this.sideB >= (this.sideC + this.sideA) || this.sideC >= (this.sideA + this.sideB))
	    {
		c.println ();
		c.println ("ERROR: Reasons why the error occurred is listed below:");
		c.println ("1. One of the entered dimension is invalid!");
		c.println ("    The dimensions must be greater than 0!");
		c.println ("2. Any sidelengths cannot be greater than or equal to the other two added!");
		c.println ();
	    }
	}
	while (this.sideA < 0 || this.sideB < 0 || this.sideC < 0 || this.sideA >= (this.sideB + this.sideC) || this.sideB >= (this.sideC + this.sideA) || this.sideC >= (this.sideA + this.sideB));
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 01, 2018
    Purpose: displays sidelengths, area, perimeter of the triangle
    Input: Console c
    Output: None
    -----------------------------------------------*/
    public void put (Console c)
    {
	c.println ("Triangle");
	super.put (c);
	c.println ("Dimensions of triangle: " + this.sideA + " units" + " by " + this.sideB + " units" + " by " + this.sideC + " units.");
	c.println ("The perimeter of the triangle is " + this.perimeter () + " units.");
	c.println ("The area of the triangle is " + this.area () + " units squared.");
    }
} //class Triangle
/*--------------------------------------------------
Class: Pentagon
Author: Eric Zhou
Date: Nov 01, 2018
Purpose: Holding Pentagon contents.
Data Element:
    length: length of one side of the pentagon
Methods:
    perimeter: calculates the perimeter of the pentagon
    area: calculates the area of the pentagon
    get: gets the necessary information for class fields from user
    put: To display class fields and method
-----------------------------------------------------*/
class Pentagon extends D2shape
{
    protected double length; //other sides same

    public Pentagon ()
    {
	super ();
    }
    public Pentagon(double sideLength)
    {
	super();
	this.length = sideLength;
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 01, 2018
    Purpose: calculates the perimeter of the pentagon
    Input: None
    Output: perimeter
    -----------------------------------------------*/
    public double perimeter ()
    {
	double calcPer = length * 5;
	return calcPer;
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 01, 2018
    Purpose: calculates the area of the pentagon
    Input: None
    Output: area
    -----------------------------------------------*/
    public double area ()
    {
	double calcArea = (0.25 * (Math.sqrt (5 * (5 + 2 * (Math.sqrt (5)))))) * Math.pow (this.length, 2);
	return calcArea;
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 01, 2018
    Purpose: gets the necessary information for class fields from user
    Input: Console c
    Output: None
    -----------------------------------------------*/
    public void get (Console c)
    {
	super.get (c);
	do
	{
	    c.print ("What is one of the sidelength of the pentagon: ");
	    this.length = c.readDouble ();
	    if (this.length < 0)
	    {
		c.println ("The sidelength entered is invalid.  It must be greater than 0!");
	    }
	}
	while (this.length < 0);
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 01, 2018
    Purpose: display class fields and methods
    Input: Console c
    Output: None
    -----------------------------------------------*/
    public void put (Console c)
    {
	c.println ("Pentagon");
	super.put (c);
	c.println ("Sidelength: " + this.length + " units.");
	c.println ("The perimeter of the Pentagon is " + this.perimeter () + " units.");
	c.println ("The area of the Pentagon is " + this.area () + " units squared.");
    }
}
/*--------------------------------------------------
Class: Rhombus
Author: Eric Zhou
Date: Nov 01, 2018
Purpose: Holding Rhombus contents.
Data Element:
    base: the base length of the rhombus
    height: the height length of the rhombus
Methods:
    perimeter: calculates the perimeter of the rhombus
    area: calculates the area of the rhombus
    get: gets the necessary information for class fields from user
    put: display rhombus fields and methods
-----------------------------------------------------*/
class Rhombus extends D2shape
{
    protected double base, height;

    public Rhombus ()
    {
	super ();
    }
    public Rhombus(double getBase, double getHeight)
    {
	super();
	this.base = getBase;
	this.height = getHeight;
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 01, 2018
    Purpose:  calculates the perimeter of the rhombus
    Input: None
    Output: perimeter
    -----------------------------------------------*/
    public double perimeter ()
    {
	double calcPer = this.base * 4;
	return calcPer;
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 01, 2018
    Purpose: calculates the area of the rhombus
    Input: None
    Output: Area
    -----------------------------------------------*/
    public double area ()
    {
	double calcArea = this.base * this.height;
	return calcArea;
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 01, 2018
    Purpose: gets the necessary information for class fields from user
    Input: Console c
    Output: None
    -----------------------------------------------*/
    public void get (Console c)
    {
	super.get (c);
	do
	{
	    c.print ("What is base of your rhombus: ");
	    this.base = c.readDouble ();
	    c.print ("What is height of your rhombus: ");
	    this.height = c.readDouble ();
	    if (this.base < 0 || this.height < 0)
	    {
		c.println ("The base or height (or both) entered is invalid. Must be greater than 0!");
	    }
	}
	while (this.base < 0 || this.height < 0);
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 01, 2018
    Purpose: display class fields and methods
    Input: Console c
    Output: None
    -----------------------------------------------*/
    public void put (Console c)
    {
	c.println ("Rhombus");
	super.put (c);
	c.println ("Sidelength: " + this.base + " units" + ", Height: " + this.height + " units.");
	c.println ("The perimeter of the Rhombus is " + this.perimeter () + " units.");
	c.println ("The area of the Rhombus is " + this.area () + " units squared.");
    }
}


/*--------------------------------------------------
Class: Hexagon
Author: Eric Zhou
Date: Nov 01, 2018
Purpose: Holding Hexagon contents.
Data Element:
    length: one sidelength of the hexagon
Methods:
    perimeter: calculates the perimeter of the hexagon
    area: calculates the area of the hexagon
    get: gets the necessary information for class fields from user
    put: To display class fields and method
-----------------------------------------------------*/
class Hexagon extends D2shape
{
    protected double length;
    public Hexagon ()
    {
	super ();
    }
    public Hexagon(double getLength)
    {
	super();
	this.length = getLength;
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 01, 2018
    Purpose:  calculates the perimeter of the hexagon
    Input: None
    Output: perimeter
    -----------------------------------------------*/
    public double perimeter ()
    {
	double calcPer = this.length * 6;
	return calcPer;
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 01, 2018
    Purpose:  calculates the area of the hexagon
    Input: None
    Output: Area
    -----------------------------------------------*/
    public double area ()
    {
	double calcArea = (3 * Math.sqrt (3) / 2) * Math.pow (this.length, 2);
	return calcArea;
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 01, 2018
    Purpose: gets the necessary information for class fields from user
    Input: Console c
    Output: None
    -----------------------------------------------*/
    public void get (Console c)
    {
	super.get (c);
	do
	{
	    c.print ("What is one of the sidelength for your hexagon?: ");
	    this.length = c.readDouble ();
	    if (this.length < 0)
	    {
		c.println ("The sidelength entered is invalid. It must be greater than 0!");
	    }
	}
	while (this.length < 0);
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 01, 2018
    Purpose: display class fields and methods
    Input: Console c
    Output: None
    -----------------------------------------------*/
    public void put (Console c)
    {
	c.println ("Hexagon");
	super.put (c);
	c.println ("Sidelength: " + this.length + " units.");
	c.println ("The perimeter of the Hexagon is " + this.perimeter () + " units.");
	c.println ("The area of the Hexagon is " + this.area () + " units squared.");
    }
}


/*--------------------------------------------------
Class: Octagon
Author: Eric Zhou
Date: Nov 01, 2018
Purpose: Holding Octagon contents.
Data Element:
    length: one sidelength of the Octagon
Methods:
    perimeter: calculates the perimeter of the Octagon
    area: calculates the area of the Octagon
    get: gets the necessary information for class fields from user
    put: To display class fields and method
-----------------------------------------------------*/
class Octagon extends D2shape
{
    protected double length;
    public Octagon ()
    {
	super ();
    }
    public Octagon(double getLength)
    {
	super();
	this.length = getLength;
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 01, 2018
    Purpose:  calculates the perimeter of the octagon
    Input: None
    Output: perimeter
    -----------------------------------------------*/
    public double perimeter ()
    {
	double calcPer = this.length * 8;
	return calcPer;
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 01, 2018
    Purpose:  calculates the area of the octagon
    Input: None
    Output: Area
    -----------------------------------------------*/
    public double area ()
    {
	double calcArea = 2 * (1 + Math.sqrt (2)) * Math.pow (this.length, 2);
	return calcArea;
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 01, 2018
    Purpose: gets the necessary information for class fields from user
    Input: Console c
    Output: None
    -----------------------------------------------*/
    public void get (Console c)
    {
	super.get (c);
	do
	{
	    c.print ("What is one of the sidelength of the octagon: ");
	    this.length = c.readDouble ();
	    if (this.length < 0)
	    {
		c.println ("The entered sidelength is invalid. It must be greater than 0!");
	    }
	}
	while (this.length < 0);
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 01, 2018
    Purpose: display class fields and methods
    Input: Console c
    Output: None
    -----------------------------------------------*/
    public void put (Console c)
    {
	c.println ("Octagon");
	super.put (c);
	c.println ("Sidelength: " + this.length + " units.");
	c.println ("The perimeter of the Octagon is " + this.perimeter () + " units.");
	c.println ("The area of the Octagon is " + this.area () + " units squared.");
    }
}


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*--------------------------------------------------
Class: D3shape
Author: Eric Zhou
Date: Nov 02, 2018
Purpose: To hold contents of a 3D shape
Data Element:
    locX: x location of the 3d shape
    locY: y location of the 3d shape
    locZ: z location of the 3d shape
    PI: constant 3.14...
Methods:
    volume: calculates and returns the volume of the 3d shape
    surfaceArea: calculates and returns the surface area of the 3d shape
-----------------------------------------------------*/
abstract class D3shape extends Shape //for locationx and locationy
{
    protected int locX;
    protected int locY;
    protected int locZ; //3 dimensional
    final static double PI = 3.141592654; //constant

    public D3shape ()
    {
	super ();
    }
    public D3shape(int x, int y, int z)
    {
	super();
	this.locX = x;
	this.locY = y;
	this.locZ = z;
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 02, 2018
    Purpose: calculates and returns the volume of the 3d shape
    Input: None
    Output: Volume
    -----------------------------------------------*/
    abstract public double volume ();
    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 02, 2018
    Purpose: calculates and returns the surface area of the 3d shape
    Input: None
    Output: surface area
    -----------------------------------------------*/
    abstract public double surfaceArea ();

    public void get (Console c)
    {
	super.get (c);
	c.print ("What is the location of X: ");
	this.locX = c.readInt ();
	c.print ("What is the location of Y: ");
	this.locY = c.readInt ();
	c.print ("What is the location of Z: ");
	this.locZ = c.readInt ();
    }


    public void put (Console c)
    {
	super.put (c);
	c.println ("Location of X: " + this.locX);
	c.println ("Location of Y: " + this.locY);
	c.println ("Location of Z: " + this.locZ);
    }
}


/*-----------------------------------------------------
Class: Sphere
Author: Eric Zhou
Date: Nov 02, 2018
Purpose: Holding Sphere contents.
Data Element:
    radius: the radius of the sphere
Methods:
    volume: calculates and returns the volume of the sphere
    surfaceArea: calculates and returns the surface area of the sphere
    get: gets the radius of the sphere.
    put: outputs the contents of the sphere
-----------------------------------------------------*/
class Sphere extends D3shape
{
    protected double radius;
    public Sphere ()
    {
	super ();
    }
    public Sphere(double getRad)
    {
	super();
	this.radius = getRad;
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 02, 2018
    Purpose: calculates and returns the volume of the sphere
    Input: None
    Output: volume sphere
    -----------------------------------------------*/
    public double volume ()
    {
	double calcVol = ((double) 4.0 / 3.0) * this.PI * Math.pow (this.radius, 3);
	return calcVol;
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 02, 2018
    Purpose: calculates and returns the surface area of the sphere
    Input: None
    Output: surface area
    -----------------------------------------------*/
    public double surfaceArea ()
    {
	double surArea = 4 * this.PI * Math.pow (this.radius, 2);
	return surArea;
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 02, 2018
    Purpose: gets radius of sphere
    Input: Console c
    Output: None
    -----------------------------------------------*/
    public void get (Console c)
    {
	super.get (c);
	do
	{
	    c.print ("What is the radius of your sphere: ");
	    this.radius = c.readDouble ();
	    if (this.radius < 0)
	    {
		c.println ("The entered radius is invalid. The radius must be greater than 0!");
	    }
	}
	while (this.radius < 0);
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 02, 2018
    Purpose: Prints the sphere's contents (fields and methods)
    Input: Console c
    Output: None
    -----------------------------------------------*/
    public void put (Console c)
    {
	c.println ("Sphere");
	super.put (c);
	c.println ("Radius: " + this.radius + " units.");
	c.println ("The volume of the sphere is " + this.volume () + " units cubed.");
	c.println ("The surface area of the sphere is " + this.surfaceArea () + " units squared.");

    }
}


/*-----------------------------------------------------
Class: Cube
Author: Eric Zhou
Date: Nov 02, 2018
Purpose: Holding Cube contents.
Data Element:
    width: one width of the cube
Methods:
    volume: calculates and returns the volume of the cube
    surfaceArea: calculates and returns the surface area of the cube
    get: gets the width of the cube
    put: outputs the contents of the cube
-----------------------------------------------------*/
class Cube extends D3shape
{
    protected double width;
    public Cube ()
    {
	super ();
    }
    public Cube(double wid)
    {
	super();
	this.width = wid;
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 02, 2018
    Purpose: calculates and returns the volume of the cube
    Input: None
    Output: Volume
    -----------------------------------------------*/
    public double volume ()
    {
	double calcVol = Math.pow (this.width, 3);
	return calcVol;
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 02, 2018
    Purpose: calculates and returns the surface area of the cube
    Input: None
    Output: surface area
    -----------------------------------------------*/
    public double surfaceArea ()
    {
	double surArea = (this.width * this.width) * 6;
	return surArea;
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 02, 2018
    Purpose: gets width of the cube
    Input: Console c
    Output: None
    -----------------------------------------------*/
    public void get (Console c)
    {
	super.get (c);
	do
	{
	    c.print ("What is the width of your cube: ");
	    this.width = c.readDouble ();
	    if (this.width < 0)
	    {
		c.println ("The entered width is invalid. The width must be greater than 0!");
	    }
	}
	while (this.width < 0);
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 02, 2018
    Purpose: Prints the cube's contents (fields and methods)
    Input: Console c
    Output: None
    -----------------------------------------------*/
    public void put (Console c)
    {
	c.println ("Cube");
	super.put (c);
	c.println ("Width of cube: " + this.width + " units.");
	c.println ("The volume of the cube is " + this.volume () + " units cubed.");
	c.println ("The surface area of the cube is " + this.surfaceArea () + " units squared.");

    }
}


/*-----------------------------------------------------
Class: Cylinder
Author: Eric Zhou
Date: Nov 02, 2018
Purpose: Holding Cylinder contents.
Data Elements:
    radius: the radius of the cylinder
    height: the height of the cylinder
Methods:
    volume: calculates and returns the volume of the cylinder
    surfaceArea: calculates and returns the surface area of the cylinder
    get: gets the radius and height of the cylinder
    put: outputs the contents of the cylinder
-----------------------------------------------------*/
class Cylinder extends D3shape
{
    protected double radius, height;
    public Cylinder ()
    {
	super ();
    }
    public Cylinder(double rad, double getHeight)
    {
	super();
	this.radius = rad;
	this.height = getHeight;
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 02, 2018
    Purpose: calculates and returns the volume of the cylinder
    Input: None
    Output: Volume
    -----------------------------------------------*/
    public double volume ()
    {
	double calcVol = this.PI * Math.pow (this.radius, 2) * this.height;
	return calcVol;
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 02, 2018
    Purpose: calculates and returns the surface area of the cylinder
    Input: None
    Output: surface area
    -----------------------------------------------*/
    public double surfaceArea ()
    {
	double surArea = (2 * this.PI * this.radius * this.height) + (2 * this.PI * Math.pow (this.radius, 2));
	return surArea;
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 02, 2018
    Purpose: Gets the cylinder's contents (fields)
    Input: Console c
    Output: None
    -----------------------------------------------*/
    public void get (Console c)
    {
	super.get (c);
	do
	{
	    c.print ("What is the radius of your cylinder: ");
	    this.radius = c.readDouble ();
	    c.print ("What is the height of your cylinder: ");
	    this.height = c.readDouble ();
	    if (this.radius < 0 || this.height < 0)
	    {
		c.println ("One of the entered radius/height (or both) is invalid. The dimensions must be greater than 0!");
	    }
	}
	while (this.radius < 0 || this.height < 0);
    }


    /*---------------------------------------------
    Author: Eric Zhou
    Date: Nov 02, 2018
    Purpose: Prints the cylinder's contents (fields and methods)
    Input: Console c
    Output: None
    -----------------------------------------------*/
    public void put (Console c)
    {
	c.println ("Cylinder");
	super.put (c);
	c.println ("Radius: " + this.radius + " units.");
	c.println ("Height: " + this.height + " units.");
	c.println ("The volume of the cylinder is " + this.volume () + " units cubed.");
	c.println ("The surface of the cylinder is " + this.surfaceArea () + " units squared.");

    }
}
