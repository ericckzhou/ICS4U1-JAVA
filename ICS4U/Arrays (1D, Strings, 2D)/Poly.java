// The "Poly" class.
/* -------------------------------------------------------------
Date: Oct 8, 2018
Author: Eric Zhou
Purpose: To create a polynomial class with functioning methods.
Input: Keyboard
Output: Console
 --------------------------------------------------------------- */
import java.awt.*;
import hsa.Console;

public class Poly
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
	c = new Console ();
	boolean loop = true;
	while (loop == true)
	{

	    Polynomial myPoly = new Polynomial ();
	    myPoly.get (c);
	    c.println ("" + myPoly);
	    c.print ("What x-value would you like to input: ");
	    double getX = c.readDouble ();
	    c.println ("The value of the polynomial when you insert " + getX + " is " + myPoly.value (getX));
	    if (myPoly.degree >= 1)
	    {
		Polynomial derivPoly = myPoly.derivative ();
		c.println ("The derivative of the polynomial is");
		c.println ("" + derivPoly);
		c.println ("A root of the polynomial function is: " + myPoly.root (c));
	    }


	    //Loop Check
	    String rerun = "y";
	    do
	    {
		c.print ("Would you like to rerun the program? Y/N: ");
		rerun = c.readString ();
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
} // Poly class

/* ------------------------------------------------------------------------------------------------
The "Polynomial" Class
Date: Oct 8, 2018
Author: Eric Zhou
Purpose: To create a Polynomial class capable of storing a polynomial
Data Elements:
    coeff: holds the coefficient values of the polynomial in a double array.
    degree: holds the degree of the polynomial. (size of array = degree + 1)
    Eg x^4-2x^3-7x^2+8x+12 - degree 4 but holds 5 partitions
Methods:
    toString: prints a String of the polynomial in the correct form.
    get: allows user to enter degree & coefficient values.
    value: calculates the value of the polynomial given a value of x. (consider horner's rule)
    derivative: returns the derivative of the current polynomial.
 ------------------------------------------------------------------------------------------------- */

class Polynomial
{
    public double[] coeff;
    public int degree;

    public Polynomial (int getDegree)
    {
	this.degree = getDegree;
	this.coeff = new double [(this.degree + 1)];
    }


    public Polynomial ()
    {
    }


    /* ------------------------------------------------------
    Date: Oct 8, 2018
    Author: Eric Zhou
    Purpose: To print the polynomial in its correct form
    Input: None
    Output: The correct form (String)
     -------------------------------------------------------- */
    public String toString ()  // check to see if it must be protected method.
    {
	String poly = "";
	double coefficient = 0;


	for (int dummyDegree = this.degree ; dummyDegree != -1 ; dummyDegree--) //-1 stops.
	{
	    String signCoeff = "";
	    String degreeSign = "";
	    String degreeNum = "";
	    String printX = "";
	    String displayCoeff = "";
	    coefficient = this.coeff [dummyDegree];


	    if (coefficient > 0 && this.degree != dummyDegree)
	    {
		signCoeff = "+";
	    }

	    if (coefficient < 0)
	    {
		signCoeff = "-";
	    }

	    if (dummyDegree > 1)
	    {
		degreeSign = "^";
		degreeNum = "" + dummyDegree;
	    }

	    if (dummyDegree > 0 && coefficient != 0)
	    {
		printX = "x";
	    }

	    if (coefficient != 1 && coefficient != -1 && coefficient != 0)
	    {
		displayCoeff = "" + Math.abs (coefficient);
	    }
	    if (coefficient == 0)
	    {
		displayCoeff = "";
	    }
	    if (dummyDegree == 0 && coefficient != 0)
	    {
		displayCoeff = "" + Math.abs (coefficient);
	    }

	    poly += signCoeff + " " + displayCoeff + printX + degreeSign + degreeNum + " ";

	}

	return poly;
    }


    /* ------------------------------------------------------
    Date: Oct 8, 2018
    Author: Eric Zhou
    Purpose: Gets user value of the polynomial
    Input: Console c
    Output: None
     -------------------------------------------------------- */
    public void get (Console c)
    {
	// Degree
	int getDegree = -1;
	while (getDegree <= 0)
	{
	    c.print ("What is the highest degree of the polynomial?: ");
	    getDegree = c.readInt ();
	    if (getDegree <= 0)
	    {
		c.println ("The degree of the polynomial must be greater than 0!");
	    }
	}
	this.degree = getDegree;
	this.coeff = new double [this.degree + 1];

	// Coefficients (from right to left of an equation)
	c.println (" * User must input " + (getDegree + 1) + " coefficient values. * ");

	double coefficient = 0;
	int partition = 0;
	while (partition != (getDegree + 1))
	{
	    if (partition == 0)
	    {
		c.print ("What is the constant value?: ");
	    }
	    if (partition > 0)
	    {
		c.print ("What is the coefficient value (from least degree to greatest degree): ");
	    }
	    coefficient = c.readDouble ();
	    this.coeff [partition] = coefficient;
	    partition++;
	}
    }


    /* ------------------------------------------------------
    Date: Oct 8, 2018
    Author: Eric Zhou
    Purpose: Returns the value of the polynomial when x is inputted using Horner's rule.
    Input: x - double
    Output: Double value of the polynomial
     -------------------------------------------------------- */
    public double value (double x)
    {
	double total = 0; // coefficient * x
	for (int n = this.degree ; n != -1 ; n--)
	{
	    total = total * x + this.coeff [n];
	}

	return total;
    }


    /* ------------------------------------------------------
    Date: Oct 8, 2018
    Author: Eric Zhou
    Purpose: Returns the derivative polynomial of the current one.
    Input: None
    Output: Derivative of the current polynomial
     -------------------------------------------------------- */
    public Polynomial derivative ()
    {
	Polynomial derivPoly = new Polynomial (this.degree - 1);
	int partition = 0;
	for (int i = 1 ; i <= this.degree ; i++) // i is the degree
	{
	    derivPoly.coeff [partition] = this.coeff [i] * i;
	    partition++;
	}

	return derivPoly;
    }


    /* ------------------------------------------------------
    Date: Oct 9, 2018
    Author: Eric Zhou
    Purpose: Finds a root of a polynomial using Newton's method
    Input: None
    Output: A root (double)
    Reference: https://en.wikipedia.org/wiki/Newton%27s_method
     -------------------------------------------------------- */
    protected double newton (Console c)
    {

	double x0 = 1; // initial value
	Polynomial derivPoly = this.derivative ();
	double tolerance = Math.pow (10, -7);
	double epsilon = Math.pow (10, -14);
	int maxIterations = 20;
	boolean solution = false;
	double root = 0;


	for (int i = 1 ; i <= maxIterations && solution == false ; i++)
	{
	    double y = this.value (x0);
	    double yDeriv = derivPoly.value (x0);
	    double x1 = x0 - (y / yDeriv); //newton

	    if (Math.abs (yDeriv) < epsilon || yDeriv == 0)
	    {
		solution = true; //exit, no solutions
		System.out.println ("No roots were found for this function. ");
		System.out.println("Root set to 0");
	    }

	    if (Math.abs (x1 - x0) <= (tolerance * Math.abs (x1)))
	    {
		root = x1;
		solution = true; //found root, thus exit
	    }
	    if (maxIterations == i && solution == false)
	    {
		solution = true;
		System.out.println ("No roots were found for this function. ");
		System.out.println("Root set to 0");
	    }
	    else
	    {
		x0 = x1;
	    }
	}

	return root;

    }


    /* ------------------------------------------------------
    Date: Oct 9, 2018
    Author: Eric Zhou
    Purpose: Calls newton's method to find the root
    Input: None
    Output: A root (double)
     -------------------------------------------------------- */
    public double root (Console c)
    {
	// odd = min 1 zero; max n -1
	// even = min 0 zeroes; max n -1
	double root = this.newton (c);
	return root;
    }
} // Polynomial Class
