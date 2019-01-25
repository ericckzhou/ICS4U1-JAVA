// -----------------------------------------------------------------
// The "Mono" class.
// Date: 9/25/2018
// Author: Eric Zhou
// Purpose: To create a monomial class with the expression cx^e.
// Input: Keyboard
// Output: Console
// -----------------------------------------------------------------

import java.awt.*;
import hsa.Console;

public class Mono // main class
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
	c = new Console ();

	boolean loop = true;
	String rerun = "y";

	while (loop == true)
	{
	    //Getting the first Monomial
	    Monomial mono = new Monomial ();
	    mono.get (c);
	    c.print ("First Monomial: ");
	    c.print (mono.toString ());
	    c.println ();
	    c.println ();

	    //Second Monomial
	    Monomial otherMono = new Monomial ();
	    otherMono.get (c);
	    c.print ("Second Monomial: ");
	    c.print (otherMono.toString ());
	    c.println ();
	    c.println ();

	    //Check if it can be added / divided
	    boolean canBeAdded = (mono.canAdd (otherMono));
	    boolean canBeDivided = (mono.canDivide (otherMono));

	    //Add & Subtract Result
	    if (canBeAdded == true)
	    {
		Monomial addedMono = mono.add (otherMono);
		c.print ("" + addedMono);
		c.println (" :Added Monomials!");
		Monomial subtractedMono = mono.sub (otherMono);
		c.print ("" + subtractedMono);
		c.println (" :Subtracted Monomials!");
	    }

	    //Multiply Result
	    Monomial multipliedMono = mono.multiply (otherMono);
	    c.print ("" + multipliedMono);
	    c.println (" :Multiplied Monomials!");

	    //Divide Result
	    if (canBeDivided == true)
	    {
		Monomial dividedMono = mono.divide (otherMono);
		c.print ("" + dividedMono);
		c.println (" :Divided Monomials!");
		c.println ();
	    }

	    //Powered Result
	    c.print ("How high to the power of, would you like to raise the first Monomial?: ");
	    int getPower = c.readInt ();
	    c.println ();
	    Monomial poweredMono = mono.pow (getPower);
	    Monomial poweredOtherMono = otherMono.pow (getPower);
	    c.print ("The new powered first Monomial is ");
	    c.print ("" + poweredMono);
	    c.println ();
	    c.print ("The new powered second Monomial is ");
	    c.print ("" + poweredOtherMono);
	    c.println ();

	    //Testing value
	    c.println ();
	    c.print ("What x-value would you like to input (original)?: ");
	    double getValue = c.readDouble ();
	    Monomial firstValue = mono.value (getValue); // first monomial value
	    Monomial secondValue = otherMono.value (getValue); // second monomial value
	    c.print ("The value from the first Monomial is " + firstValue);
	    c.println ();
	    c.print ("The value from the second Monomial is " + secondValue);
	    c.println ();
	    c.println ();

	    //Equal-To Result
	    boolean equalTo = mono.equal (otherMono);
	    c.println ("The two Monomials are equal to each other: " + equalTo);
	    c.println ();


	    //Loop Check
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
} // Mono class

// ------------------------------------------------------------------
// Class Name: Monomial
// Date: 9/25/2018
// Author: Eric Zhou
// Purpose: To create a monomial class with the expression cx^e.
//
// Data Elements:
//      coefficient: holds the c (coefficient) value in the expression cx^e
//      exponent: holds the e (exponential) value in the expression cx^e
//
// Constructors:
//      currently have 3 constructors, 1. coefficient, 2. none, 3. both exponent and coefficient.
//
// Methods:
/*
	simplify: Defaults the coefficient and exponential values
	get: To get user inputted values of coefficient and exponent.
	toString: To format the Monomial in the form of cx^e
	canAdd: To find out if this monomial can be added with another.
	canDivide: To find out if this monomial can be divided with another.
	add: Adding the 2 monomials together.
	subtract: Subtracting the 2 monomials together.
	multiply: Multiplying the 2 monomials together.
	divide: Dividing the 2 monomials together.
	value: Returns the value of the Monomial given a value for x.
	pow: Raises a monomial to a given power.
	equal: Sees if the two monomials are the same/equal to each other.
*/
// -----------------------------------------------------------------
class Monomial
{
    public double coefficient = 0;
    // Coefficient holds the c (coefficient) value in the expression cx^e
    public int exponent = 0;
    // Exponent holds the e (exponential) value in the expression cx^e

    public Monomial (double c, int e)  // 1st constructor
    {
	coefficient = c;
	exponent = e;
	this.simplify ();
    }

    public Monomial (double c) // 2nd constructor
    {
	coefficient = c;
	exponent = 0;
    }

    public Monomial () // default constructor
    {
	coefficient = 0;
	exponent = 0;
    }


    // Date: 9/25/2018
    // Author: Eric Zhou
    // Purpose: Defaults the coefficient and exponential values
    // Input: None
    // Output: None
    // -----------------------------------------------------------------
    public void simplify ()
    {
	if (coefficient == 0)
	{
	    exponent = 0;
	}
    }


    // Date: 9/25/2018
    // Author: Eric Zhou
    // Purpose: To get user inputted values of coefficient and exponent.
    // Input: Console
    // Output: None
    // -----------------------------------------------------------------
    public void get (Console c)
    {
	c.print ("What is the coefficient value: ");
	coefficient = c.readDouble ();
	c.print ("What is the expoential value: ");
	exponent = c.readInt ();
	this.simplify ();

    }


    // Date: 9/25/2018
    // Author: Eric Zhou
    // Purpose: To format the Monomial in the form of cx^e
    // Input: None
    // Output: cx^e (string)
    // -----------------------------------------------------------------
    public String toString ()
    {
	String displayCoeff = "";
	String displayExp = "";
	String expSign = "";
	String x = "x";

	if (coefficient != 0 && coefficient != 1)
	{
	    displayCoeff = Double.toString (coefficient);
	}
	if (exponent != 1 && exponent != 0 && coefficient != 0)
	{
	    expSign = "^";
	    displayExp = Integer.toString (exponent);
	}
	if (exponent == 0)
	{
	    x = "";
	}
	if (coefficient == 0)
	{
	    displayCoeff = "0";
	    x = "";
	}
	if (coefficient == 1 && exponent == 0)
	{
	    displayCoeff = "1";


	}


	String statement;
	return statement = (displayCoeff + x + expSign + displayExp);
    }


    // Date: 9/25/2018
    // Author: Eric Zhou
    // Purpose: To find out if this monomial can be added with another.
    // Input: Other monomial
    // Output: True or False (boolean)
    // -----------------------------------------------------------------
    public boolean canAdd (Monomial mono)
    {
	boolean canBeAdded = true;
	if (this.exponent != mono.exponent)
	{
	    canBeAdded = false;
	}

	return canBeAdded;
    }


    // Date: 9/25/2018
    // Author: Eric Zhou
    // Purpose: To find out if this monomial can be divided with another.
    // Input: Other monomial
    // Output: True or False (boolean)
    // -----------------------------------------------------------------
    public boolean canDivide (Monomial mono)
    {
	boolean canBeDivided = true;
	if (mono.coefficient == 0)
	{
	    canBeDivided = false;
	}
	return canBeDivided;
    }


    // Date: 9/25/2018
    // Author: Eric Zhou
    // Purpose: Adding the 2 monomials together.
    // Input: Other monomial
    // Output: New Added Monomial
    // -----------------------------------------------------------------
    public Monomial add (Monomial mono)
    {
	double newCoefficient = this.coefficient + mono.coefficient;
	Monomial newMonomial = new Monomial (newCoefficient, exponent);
	newMonomial.simplify ();

	return newMonomial;
    }


    // Date: 9/25/2018
    // Author: Eric Zhou
    // Purpose: Subtracting the 2 monomials together.
    // Input: Other monomial
    // Output: New Subtracted Monomial
    // -----------------------------------------------------------------
    public Monomial sub (Monomial mono)
    {
	double newCoefficient = this.coefficient - mono.coefficient;
	Monomial newMonomial = new Monomial (newCoefficient, exponent);
	newMonomial.simplify ();

	return newMonomial;
    }


    // Date: 9/25/2018
    // Author: Eric Zhou
    // Purpose: Multiplying the 2 monomials together.
    // Input: Other monomial
    // Output: New Multiplied Monomial
    // -----------------------------------------------------------------
    public Monomial multiply (Monomial mono)
    {
	double newCoefficient = this.coefficient * mono.coefficient;
	int newExponent = this.exponent + mono.exponent;
	Monomial newMonomial = new Monomial (newCoefficient, newExponent);
	newMonomial.simplify ();

	return newMonomial;
    }


    // Date: 9/25/2018
    // Author: Eric Zhou
    // Purpose: Dividing the 2 monomials together.
    // Input: Other monomial
    // Output: New Multiplied Monomial
    // -----------------------------------------------------------------
    public Monomial divide (Monomial mono)
    {
	double newCoefficient = this.coefficient / mono.coefficient;
	int newExponent = this.exponent - mono.exponent;
	Monomial newMonomial = new Monomial (newCoefficient, newExponent);
	newMonomial.simplify ();

	return newMonomial;
    }


    // Date: 9/25/2018
    // Author: Eric Zhou
    // Purpose: Returns the value of the Monomial given a value for x.
    // Input: X value
    // Output: A New Monomial with x-value
    // -----------------------------------------------------------------
    public Monomial value (double x)
    {
	double getValue = this.coefficient * (Math.pow (x, this.exponent));
	Monomial newMonomial = new Monomial (getValue);

	return newMonomial;
    }


    // Date: 9/25/2018
    // Author: Eric Zhou
    // Purpose: Raises a monomial to a given power.
    // Input: Power
    // Output: New Monomial raised to a certain power
    // -----------------------------------------------------------------
    public Monomial pow (int power)
    {
	double newCoefficient = Math.pow (coefficient, power);
	int newExponent = exponent * power;
	Monomial newMonomial = new Monomial (newCoefficient, newExponent);

	return newMonomial;
    }


    // Date: 9/25/2018
    // Author: Eric Zhou
    // Purpose: Sees if two monomials are the same
    // Input: Other monomial
    // Output: boolean answer (true/false)
    // -----------------------------------------------------------------
    public boolean equal (Monomial mono)
    {
	boolean equalTo = true;
	if (this.coefficient != mono.coefficient || this.exponent != mono.exponent)
	{
	    equalTo = false;
	}


	return equalTo;
    }
} // Monomial CLass

