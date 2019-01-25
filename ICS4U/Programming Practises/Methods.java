// The "Methods" class.
// Date: Sept 15, 2018
// Author: Eric Zhou
// Purpose: To demonstrate the following methods.
// Input: Keyboard
// Output: Screen/Console
// ---------------------------------------------------
import java.awt.*;
import hsa.Console;

public class Methods
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
	c = new Console ();
	boolean reRun = true;

	while (reRun == true)
	{
	    int x = 0;
	    int y = 0;
	    int gap = 0;
	    c.print ("Enter positive (1-200): ");
	    x = c.readInt ();
	    c.print ("Enter another positive number (1-200): "); // x - y = interval
	    y = c.readInt ();

	    c.print ("Enter the gap (1-100): ");
	    gap = c.readInt ();

	    while (x > 200 || y > 200 || x <= 0 || y <= 0 || x == y || gap > 100)
	    {
		c.println ("Invalid Input! Please try again!");
		if (x == y)
		{
		    c.println ("The value of x cannot be the same as y! ");
		}
		c.print ("Enter positive number (1-200): ");
		x = c.readInt ();
		c.print ("Enter another positive number (1-200): ");
		y = c.readInt ();
		c.print ("Enter the gap (1-100): ");
		gap = c.readInt ();
	    }
	    int storeX = x;
	    int storeY = y;

	    // x needs to be smaller than y

	    if (x > y)
	    {
		x = storeY;
		y = storeX;
		storeX = x;
		storeY = y;
	    }

	    c.print ("The interval range is: ");
	    c.print (x);
	    c.print (" - ");
	    c.println (y);

	    Methods getMethod = new Methods ();
	    boolean xIsPrime = (getMethod.isPrime (x));
	    boolean yIsPrime = (getMethod.isPrime (y));

	    if (xIsPrime == true)
	    {
		c.print (x);
		c.println (" is a prime number!");
	    }
	    if (yIsPrime == true)
	    {
		c.print (y);
		c.println (" is a prime number!");
	    }
	    if (xIsPrime == false)
	    {
		c.print (x);
		c.println (" is not prime number!");
	    }
	    if (yIsPrime == false)
	    {
		c.print (y);
		c.println (" is not prime number!");
	    }

	    c.print ("There are ");
	    c.print (getMethod.calcNumOfPrimes (x, y));
	    c.println (" prime numbers within the interval!");
	    c.println ("These are the prime numbers within the interval: ");
	    getMethod.printPrimes (x, y);
	    TwoPrime twoPrime = getMethod.findTwoPrimeGap (gap);
	    c.println ();
	    if (twoPrime.x == 0 || twoPrime.y == 0)
	    {
		c.print ("There are no first two consecutive primes with a gap of ");
		c.print (gap);
		c.print (".");
	    }
	    else
	    {
		c.print ("The first two consecutive primes with a gap of ");
		c.print (gap);
		c.print (" are ");
		c.print (twoPrime.x);
		c.print (" and ");
		c.print (twoPrime.y);
		c.print (".");
	    }
	    c.println ();
	    String reRunStatus = "Y";
	    c.print ("Would you like to rerun the program? Y/N ");
	    reRunStatus = c.readString ();
	    if (reRunStatus.equals ("n") || reRunStatus.equals ("N"))
	    {
		reRun = false;
	    }
	    c.println ();
	} //rerun

	// Place your program here.  'c' is the output console
    } // main method

    // ---------------------------------------------------
    // Date: Sept 13, 2018
    // Author: Eric Zhou
    // Purpose: Determines if the number is a prime number
    // Input: Integer
    // Output: True or False
    // ---------------------------------------------------
    public static boolean isPrime (int x)
    {
	boolean prime = true;

	if (x == 2 || x == 3)
	{
	    prime = true;
	}

	else if (x < 2 || x % 2 == 0)
	{
	    prime = false;
	}

	else
	{
	    for (int divisor = 3 ; (divisor <= Math.sqrt (x)) || (x % divisor == 0) ; divisor += 2)
	    {
		if (x % divisor == 0)
		{
		    prime = false;
		}
	    }
	}
	return prime;
    }


    // ---------------------------------------------------
    // Date: Sept 14, 2018
    // Author: Eric Zhou
    // Purpose: Determines number of prime numbers given a rangfe
    // Input: Two Integers, x and y
    // Output: # of prime numbers in the range
    // ---------------------------------------------------
    public static int calcNumOfPrimes (int x, int y)
    {
	int storeX = x;
	int storeY = y;

	// x needs to be smaller than y

	if (x > y)
	{
	    x = storeY;
	    y = storeX;
	    storeX = x;
	    storeY = y;
	}
	int primeCounter = 0;

	while (storeX != y + 1)
	{
	    boolean primeStatus = isPrime (storeX);

	    if (primeStatus == true)
	    {
		primeCounter++;
	    }
	    storeX++;
	}
	return primeCounter;
    }


    // ---------------------------------------------------
    // Date: Sept 15, 2018
    // Author: Eric Zhou
    // Purpose: Prints prime numbers within the interval
    // Input: Two Integers, x and y
    // Output: The prime numbers in the range
    // ---------------------------------------------------
    public static void printPrimes (int x, int y)
    {
    
	int useX = x;
	while (useX != y + 1)
	{

	    boolean primeStatus = isPrime (useX);
	    if (primeStatus == true)
	    {
		c.print (useX);
		c.print (", ");
	    }
	    useX++;
	}
    }


    // ---------------------------------------------------
    // Date: Sept 15, 2018
    // Author: Eric Zhou
    // Purpose: to find the first 2 consecutive prime numbers within the gap of x.
    // Input: Integer x
    // Output: object TwoPrime
    // ---------------------------------------------------
    public static TwoPrime findTwoPrimeGap (int x)
    {
	// gap is x
	int useNum = 0;
	int otherNum = 0;
	boolean findGap = false;
	boolean findPrimeInGap = false;
	boolean gap = true;
	boolean xIsPrime = isPrime (x);

	if (x % 2 != 0 && x != 1)
	{
	    gap = false;
	}

	while (findGap == false && gap == true)
	{
	    useNum++;
	    otherNum = useNum + x;
	    boolean useNumIsPrime = isPrime (useNum);
	    boolean otherNumIsPrime = isPrime (otherNum);
	    if (useNumIsPrime == true && otherNumIsPrime == true)
	    {
		for (int checkNum = useNum ; checkNum <= otherNum ; checkNum++)
		{
		    boolean checkNumIsPrime = isPrime (checkNum);
		    if (checkNumIsPrime == true && checkNum != useNum && checkNum != otherNum)
		    {
			findPrimeInGap = true;
		    }

		}
		if (findPrimeInGap == false)
		{
		    findGap = true;
		}
		else
		{
		    findPrimeInGap = false;
		}

	    }
	}

	return new TwoPrime (useNum, otherNum);
    }
} // Methods class

// -----------------------------------------------------
// Date: Sept 15, 2018
// Author: Eric Zhou
// Purpose: To hold public fields of x and y (long)
// Input: Long x and long y
// Output: none
// -----------------------------------------------------
class TwoPrime
{
    public long x; // will hold the value of one of the prime
    public long y; // will hold the other value of the prime

    public TwoPrime (long firstPrime, long secondPrime)
    {
	x = firstPrime;
	y = secondPrime;
    }
}
