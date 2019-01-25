// The "BigIntTest" class.
/*
Author: Eric Zhou
Date: Oct 10, 2018
Purpose: To build a big int test file
Input: Keyboard
Output: Console
*/
import java.awt.*;
import hsa.Console;

public class BigIntTest
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
	c = new Console ();
	boolean loop = true;
	while (loop == true)
	{
	    //User Input Number 
	    c.println ("Enter a number! (Unlimited digits)");
	    BigPositiveInt myInt = new BigPositiveInt ();
	    myInt.get (c);
	    c.println ("Your Number: " + myInt);
	    c.println();
	    
	    //User Input Integer to add to User Input Number 
	    c.print ("Enter an integer (max 10 digits) to add to your number: ");
	    int num = c.readInt ();
	    c.println ();
	    BigPositiveInt addedInt = myInt.add (num);
	    c.println ("Generating the sum of: " + myInt + " and " + num + " . . .");
	    c.println ("The sum is: " + addedInt);
	    c.println ();
	    c.println ("Input another big positive integer (unlimited digits) to add with " + myInt);
	    
	    //User Input BigPositiveInt to add to User Input Number 
	    BigPositiveInt otherInt = new BigPositiveInt ();
	    otherInt.get (c);
	    c.println ();
	    BigPositiveInt totalInt = myInt.add (otherInt);
	    c.println ("Generating sum of: " + myInt + " and " + otherInt + " . . .");
	    c.println ("The sum is: " + totalInt);
	    c.println ();
	    
	    //User Input Integer to subtract from User Input Number
	    c.print ("Enter an integer (max 10 digits) to subtract from your number: ");
	    int subNum = c.readInt ();
	    BigInt subInt = myInt.subtract (subNum);
	    c.println ("Generating the difference of: " + myInt + " and " + subNum + " . . .");
	    c.println ("The difference is: " + subInt);
	    c.println ();
	    
	    //User Input BigPositiveInteger to subtract from User Input Number
	    c.println ("Input another big integer (unlimited digits) to subtract from " + myInt);
	    BigPositiveInt subBigInt = new BigPositiveInt ();
	    subBigInt.get (c);
	    c.println ();
	    BigInt subbedInt = myInt.subtract (subBigInt);
	    c.println ("Generating difference of: " + myInt + " and " + subBigInt + " . . .");
	    c.println ("The difference is: " + subbedInt);
	    c.println ();
	    
	    //User Input Integer to multiply the User Input Number
	    c.print ("Enter an integer (max 10 digits) to multiply from your number: ");
	    int mulNum = c.readInt ();
	    BigInt mulInt = myInt.multiply (mulNum);
	    c.println ("Generating the product of: " + myInt + " and " + mulNum + " . . .");
	    c.println ("The product is: " + mulInt);
	    c.println ();
	    
	    //User Input BigPositiveInteger to multiply the User Input Number
	    c.println ("Input another big integer (unlimited digits) to multiply from " + myInt);
	    BigPositiveInt mulBigInt = new BigPositiveInt ();
	    mulBigInt.get (c);
	    c.println ();
	    BigPositiveInt multiedInt = myInt.multiply (mulBigInt);
	    c.println ("Generating product of: " + myInt + " and " + mulBigInt + " . . .");
	    c.println ("The product is: " + multiedInt);
	    c.println ();

	    //User Input AddFront Number
	    int addFront = 0;
	    String sAddFront = "";
	    do
	    {
	    c.print("Enter a digit (0-9) to add it to the front: ");
	    addFront = c.readInt();
	    sAddFront = "" + addFront;
	    }
	    while (sAddFront.length()>1);
	    {
	    }
	    
	    myInt.addDigitFront(addFront);
	    c.println("Your new Number: " + myInt);
	    c.println();
	    
	    //User Input AddBack Number
	    int addBack = 0;
	    String sAddBack = "";
	    do
	    {
	    c.print("Enter a digit (0-9) to add it to the back: ");
	    addBack = c.readInt();
	    sAddBack = "" + addBack;
	    }
	    while (sAddBack.length() > 1);
	    {
	    }
	    myInt.addDigitBack(addBack);
	    c.println("Your new Number: " + myInt);
	    c.println();
	    
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
} // BigIntTest class

/* -----------------------------------------------------------------------------------------
Author: Eric Zhou
Date: Oct 10, 2018
Purpose: To hold a big positive integer.
Data Elements:
    digits: an array of digits from 0-9.
    size: the number of digits
    sign: + sign; will remain + for this class.
Methods:
    get: Gets a BigPositiveInt from user.
    removeLeadingZeroes: removing the leading zeroes of the array
    addDigitFront: this will add a digit to the front (pos 0).
    addDigitBack: this will add a digit to the back (pos size).
    toString: create a string of BigInt in correct format.
    add: this will add a given Integer to a BigPositiveInt, creating a new BigPositiveInt.
    add(overload): add a given BigPositiveInt with another, creating a new BigPositiveInt.
    subtract: subtracts a given integer from a bigpositiveint, creating a new bigint.
    subtract(overload):subtracts a bigpositiveint from another bigpositiveint creating a bigint
    multiply: multiplies a given integer to a bigpositiveint, creating a new bigint.
    multiply(overload): multiplies a bigpositiveint to another bigpositiveint creating a bigint

 -------------------------------------------------------------------------------------------- */
class BigPositiveInt
{
    public int[] digits;
    public int size = 0;
    public String sign = "+";

    public BigPositiveInt (int getSize, int value)
    {
	this.size = getSize;
	this.digits = new int [this.size];
	for (int i = 0 ; i < this.size ; i++)
	{
	    this.digits [i] = value;

	}
	this.removeLeadingZeroes ();
    }


    public BigPositiveInt (int getSize)
    {
	new BigPositiveInt (getSize, 0);
    }


    public BigPositiveInt ()
    {
    }


    public BigPositiveInt (int getSize, int[] arrayOfValues)
    {
	this.size = getSize;
	this.digits = new int [getSize];
	for (int i = this.size - 1 ; i != -1 ; i--)
	{
	    digits [i] = arrayOfValues [i];
	}
    }


    /*----------------------------------------------------
    /Author: Eric Zhou
    Date: Oct 11, 2018
    Purpose: To get a big integer from user
    Input: Console
    Output: None
    ----------------------------------------------------*/
    public void get (Console c)  // always positive
    {
	c.print ("What is your big positive integer?: ");
	String userInt = c.readString ();
	int index = 0;
	this.size = userInt.length ();
	this.digits = new int [this.size];
	int partition = this.size - 1;


	for (int i = index ; i < this.size ; i++)
	{
	    this.digits [partition] = (int) (userInt.charAt (i) - '0');
	    partition--; //number stored, eg 1234, stored 4321 1 = 4th index (last index)
	}

	this.removeLeadingZeroes ();
    }


    /*----------------------------------------------------
    /Author: Eric Zhou
    Date: Oct 11, 2018
    Purpose: Remove leading zeroes that are infront of the number
    Input: None
    Output: None
    From Moodle: https://moodle2.yrdsb.ca/pluginfile.php/1652929/mod_resource/content/1/BigInt%20Provided%20Inclusions.txt
    ----------------------------------------------------*/
    public void removeLeadingZeroes ()
    {
	int[] old;

	int partition = this.size;
	while ((this.digits [this.size - 1] == 0) && (this.size > 1))
	{
	    this.size--;

	}
	old = this.digits;
	this.digits = new int [this.size];
	for (int i = this.size - 1 ; i != -1 ; i--)
	{
	    this.digits [i] = old [i];
	}

    }


    /*----------------------------------------------------
    /Author: Eric Zhou
    Date: Oct 11, 2018
    Purpose: Add digits to the Back
    Input: Added integer
    Output: None
    From Moodle: https://moodle2.yrdsb.ca/pluginfile.php/1652929/mod_resource/content/1/BigInt%20Provided%20Inclusions.txt
    ----------------------------------------------------*/

    public void addDigitBack (int added)
    {
	int[] oldDigits;
	oldDigits = this.digits;
	this.size++;
	this.digits = new int [this.size];

	for (int i = 1 ; i < this.size ; i++)
	{
	    this.digits [i] = oldDigits [i - 1];
	}
	this.digits [0] = added;
	this.removeLeadingZeroes();
    }


    /*----------------------------------------------------
    /Author: Eric Zhou
    Date: Oct 11, 2018
    Purpose: Adds digits to the Front
    Input: None
    Output: None
    From Moodle: https://moodle2.yrdsb.ca/pluginfile.php/1652929/mod_resource/content/1/BigInt%20Provided%20Inclusions.txt
    ----------------------------------------------------*/
    public void addDigitFront (int added)
    {
	int[] oldDigits = this.digits;
	this.size++;
	this.digits = new int [this.size];
	for (int i = 0 ; i < this.size - 1 ; i++)
	{
	    this.digits [i] = oldDigits [i];
	}
	this.digits [this.size - 1] = added;
	this.removeLeadingZeroes();
    }


    /*----------------------------------------------------
    /Author: Eric Zhou
    Date: Oct 11, 2018
    Purpose: Add a digit to the back of the existing bigpositiveint (position size)
    Input: None
    Output: String
    ----------------------------------------------------*/
    public String toString ()
    {
	String stringInt = "";
	for (int i = this.size - 1 ; i != -1 ; i--)
	{
	    stringInt += "" + this.digits [i];
	}
	return stringInt;
    }


    /*----------------------------------------------------
    /Author: Eric Zhou
    Date: Oct 11, 2018
    Purpose: Add a given integer to  the current big positive int
    Input: integer number
    Output: new bigpositiveint
    ----------------------------------------------------*/

    public BigPositiveInt add (int num)
    {
	String stringNum = "" + num;
	int newSize = 0;

	if (this.size > stringNum.length ())
	{
	    newSize = this.size;
	}
	else
	{
	    newSize = stringNum.length ();
	}

	int[] array = new int [newSize];
	int thisIndex = 0;
	int leftovers = 0;
	int numSize = stringNum.length ();
	int sum = 0;
	int numIndex = numSize - 1;

	for (int i = 0 ; i < newSize ; i++)
	{
	    int numDigit = 0;
	    int thisDigit = 0;

	    if (numIndex != -1)
	    {
		numDigit = stringNum.charAt (numIndex) - '0';
	    }

	    if (thisIndex < this.size)
	    {
		thisDigit = this.digits [thisIndex];
	    }

	    sum = numDigit + thisDigit;

	    if (leftovers > 0)
	    {
		sum++;
		leftovers--;
	    }

	    if (sum > 9)
	    {
		leftovers++;
		sum -= 10;
	    }


	    array [i] = sum;

	    if (thisIndex < this.size)
	    {
		thisIndex++;
	    }

	    if (numIndex != -1)
	    {
		numIndex--;
	    }
	    if (array [i] == 0 && leftovers == 1 && i == newSize - 1)
	    {
		int[] old = array;
		newSize++;
		array = new int [newSize];
		for (int j = 0 ; j < newSize - 1 ; j++)
		{
		    array [j] = old [j];
		}
		array [newSize - 1] = 1;
	    }

	}
	BigPositiveInt total = new BigPositiveInt (newSize, array);

	return total;
    }


    /*----------------------------------------------------
    /Author: Eric Zhou
    Date: Oct 11, 2018
    Purpose: Add a big positive int to  the current big positive int
    Input: bigpositiveint
    Output: new bigpositiveint
    ----------------------------------------------------*/
    public BigPositiveInt add (BigPositiveInt bpi)
    {
	int newSize = 0;

	if (this.size > bpi.size)
	{
	    newSize = this.size;
	}
	else
	{
	    newSize = bpi.size;
	}

	int[] array = new int [newSize];
	int thisIndex = 0;
	int bpiIndex = 0;
	int sum = 0;
	int leftovers = 0;
	for (int i = 0 ; i < newSize ; i++)
	{
	    int bpiDigit = 0;
	    int thisDigit = 0;

	    if (thisIndex < this.size)
	    {
		thisDigit = this.digits [thisIndex];
	    }
	    if (bpiIndex < bpi.size)
	    {
		bpiDigit = bpi.digits [bpiIndex];
	    }

	    sum = thisDigit + bpiDigit;

	    if (leftovers > 0)
	    {
		sum++;
		leftovers--;
	    }

	    if (sum > 9)
	    {
		leftovers++;
		sum -= 10;
	    }

	    array [i] = sum;

	    if (bpiIndex < bpi.size)
	    {
		bpiIndex++;
	    }
	    if (thisIndex < this.size)
	    {
		thisIndex++;
	    }
	    if (array [i] == 0 && leftovers == 1 && i == newSize - 1)
	    {
		int[] old = array;
		newSize++;
		array = new int [newSize];
		for (int j = 0 ; j < newSize - 1 ; j++)
		{
		    array [j] = old [j];
		}
		array [newSize - 1] = 1;
	    }
	}


	BigPositiveInt total = new BigPositiveInt (newSize, array);

	return total;

    }


    /*----------------------------------------------------
    /Author: Eric Zhou
    Date: Oct 11, 2018
    Purpose: Subtracts a big positive int with an integer
    Input: integer
    Output: new bigint
    ----------------------------------------------------*/
    public BigInt subtract (int num)
    {
	String stringNum = "" + num;
	int newSize = 0;
	String signOfDiff = "";
	boolean greaterThan = true;

	if (this.size >= stringNum.length ())
	{
	    newSize = this.size;
	    signOfDiff = "+";
	    greaterThan = true;
	}
	else
	{
	    newSize = stringNum.length ();
	    signOfDiff = "-";
	    greaterThan = false;
	}

	int[] array = new int [newSize];
	int thisIndex = 0;
	int takeOne = 0;
	int numSize = stringNum.length ();
	int difference = 0;
	int numIndex = numSize - 1;

	for (int i = 0 ; i < newSize ; i++)
	{
	    int numDigit = 0;
	    int thisDigit = 0;

	    if (numIndex != -1)
	    {
		numDigit = stringNum.charAt (numIndex) - '0';
	    }

	    if (thisIndex < this.size)
	    {
		thisDigit = this.digits [thisIndex];
	    }

	    if (takeOne > 0)
	    {
		if (greaterThan == true)
		{
		    thisDigit--;
		}
		else
		{
		    numDigit--;
		}
		takeOne--;

	    }
	    if (greaterThan == false)
	    {
		difference = numDigit - thisDigit;
	    }
	    else
	    {
		difference = thisDigit - numDigit;
	    }

	    if (difference < 0)
	    {
		takeOne++;
		difference += 10;
	    }


	    array [i] = difference;

	    if (thisIndex < this.size)
	    {
		thisIndex++;
	    }

	    if (numIndex != -1)
	    {
		numIndex--;
	    }

	}
	BigInt diff = new BigInt (signOfDiff, newSize, array);

	return diff;
    }


    /*----------------------------------------------------
    Author: Eric Zhou
    Date: Oct 11, 2018
    Purpose: Subtracts a big positive int with another big positive int
    Input: big positive int
    Output: new bigint
    ----------------------------------------------------*/
    public BigInt subtract (BigPositiveInt bpi)
    {
	int newSize = 0;
	String signOfDiff = "";
	boolean greaterThan = true;

	if (this.size >= bpi.size)
	{
	    newSize = this.size;
	    signOfDiff = "+";
	    greaterThan = true;
	}
	else
	{
	    newSize = bpi.size;
	    signOfDiff = "-";
	    greaterThan = false;
	}

	int[] array = new int [newSize];
	int thisIndex = 0;
	int takeOne = 0;
	int difference = 0;
	int bpiIndex = 0;

	for (int i = 0 ; i < newSize ; i++)
	{
	    int thisDigit = 0;
	    int bpiDigit = 0;

	    if (bpiIndex < bpi.size)
	    {
		bpiDigit = bpi.digits [bpiIndex];
	    }

	    if (thisIndex < this.size)
	    {
		thisDigit = this.digits [thisIndex];
	    }

	    if (takeOne > 0)
	    {
		if (greaterThan == true)
		{
		    thisDigit--;
		}
		else
		{
		    bpiDigit--;
		}
		takeOne--;

	    }
	    if (greaterThan == false)
	    {
		difference = bpiDigit - thisDigit;
	    }
	    else
	    {
		difference = thisDigit - bpiDigit;
	    }

	    if (difference < 0)
	    {
		takeOne++;
		difference += 10;
	    }


	    array [i] = difference;

	    if (thisIndex < this.size)
	    {
		thisIndex++;
	    }

	    if (bpiIndex < bpi.size)
	    {
		bpiIndex++;
	    }

	}
	BigInt diff = new BigInt (signOfDiff, newSize, array);

	return diff;
    }


    /*----------------------------------------------------
     /Author: Eric Zhou
     Date: Oct 11, 2018
     Purpose: Multiply a big positive int with an integer
     Input: integer
     Output: new bigint
     ----------------------------------------------------*/
    public BigInt multiply (int num) //should be BigInt instead of BigPositiveInt because the num can be negative.
    {
	String signResult = "+";

	if (num < 0)
	{
	    signResult = "-";
	    num = Math.abs (num);
	}

	String stringNum = "" + num;
	int numSize = stringNum.length ();
	int newSize = this.size + numSize - 1; // one digit num = size stays same, 2 digits = size +1
	int[] array = new int [newSize];
	int startingIndex = 0;
	int saveIndex = startingIndex;
	int numIndex = numSize - 1; //starting index for #
	int leftover = 0;
	int countNumSize = 0;


	boolean allMultiplied = false; // used to toggle to the next multiplier

	while (countNumSize != numSize)
	{
	    int multiplier = stringNum.charAt (numIndex) - '0';
	    for (int i = 0 ; i < this.size ; i++)
	    {
		int product = this.digits [i] * multiplier;
		while (leftover > 0)
		{
		    product++;
		    leftover--;
		}
		while (product > 9)
		{
		    leftover++;
		    product -= 10;
		}

		array [startingIndex] += product;

		while (array [startingIndex] > 9)
		{
		    leftover++;
		    array [startingIndex] -= 10;
		}

		startingIndex++;
		if (i == this.size - 1)
		{
		    allMultiplied = true;
		    while (leftover > 0)// add leftovers b4 moving on or inaccurate
		    {
			array [startingIndex] += 1;
			leftover--;
		    }
		    startingIndex = 0;
		}
	    }
	    if (allMultiplied == true && numIndex != -1)
	    {
		numIndex--;
		allMultiplied = false;
		countNumSize++;
		saveIndex++;
		startingIndex = saveIndex;
	    }
	}
	BigInt multiplied = new BigInt (signResult, newSize, array);

	return multiplied;
    }


    /*----------------------------------------------------
    /Author: Eric Zhou
    Date: Oct 11, 2018
    Purpose: Multiply a big positive int with an integer
    Input: integer
    Output: new bigint
    ----------------------------------------------------*/
    public BigPositiveInt multiply (BigPositiveInt bpi)
    {
	int newSize = this.size + bpi.size - 1;
	int[] array = new int [newSize];
	int startingIndex = 0;
	int bpiIndex = 0;
	int saveIndex = startingIndex;
	int leftover = 0;
	int countNumSize = 0;
	int multiplier = 0; 

	boolean allMultiplied = false; //  used to toggle to the next multiplier

	while (countNumSize != bpi.size)
	{
	    multiplier = bpi.digits [bpiIndex];
	    for (int i = 0 ; i < this.size ; i++)
	    {
		int product = this.digits [i] * multiplier;

		while (leftover > 0)
		{
		    product++;
		    leftover--;
		}
		while (product > 9)
		{
		    leftover++;
		    product -= 10;
		}

		array [startingIndex] += product;
		while (array [startingIndex] > 9)
		{
		    leftover++;
		    array [startingIndex] -= 10;
		}

		startingIndex++;
		if (i == this.size - 1)
		{
		    allMultiplied = true;
		    while (leftover > 0) // add leftovers b4 moving on or inaccurate
		    {
			array [startingIndex] += 1;
			leftover--;
		    }
		    startingIndex = 0;
		}
	    }
	    if (allMultiplied == true && bpiIndex < bpi.size)
	    {
		bpiIndex++;
		allMultiplied = false;
		countNumSize++;
		saveIndex++;
		startingIndex = saveIndex;
	    }
	}
	BigPositiveInt multiplied = new BigPositiveInt (newSize, array);

	return multiplied;
    }
} //BigPositiveInt
/* -----------------------------------------------------------------------------------------
Author: Eric Zhou
Date: Oct 11, 2018
Purpose: To hold a big integer negative or positive.
Data Elements:
    digits: an array of positive digits from 0-9.
    size: the number of digits
    sign: +/- sign.
Methods:
    toString: create a string of BigInt in correct format.
    removeLeadingZeroes: removing the leading zeroes of the array

 -------------------------------------------------------------------------------------------- */
class BigInt
{
    public String sign;
    public int[] digits;
    public int size;

    public BigInt (String getSign, int getSize, int[] getDigits)
    {
	this.sign = getSign;
	this.digits = getDigits;
	this.size = getSize;
	this.removeLeadingZeroes ();
    }


    public String toString ()
    {
	String stringInt = "";
	if (this.sign.equals ("-"))
	{
	    stringInt = "-";
	}

	for (int i = this.size - 1 ; i != -1 ; i--)
	{
	    stringInt += "" + this.digits [i];
	}
	return stringInt;
    }


    /*----------------------------------------------------
    /Author: Eric Zhou
    Date: Oct 11, 2018
    Purpose: Remove leading zeroes that are infront of the number
    Input: None
    Output: String
    From Moodle: https://moodle2.yrdsb.ca/pluginfile.php/1652929/mod_resource/content/1/BigInt%20Provided%20Inclusions.txt
    ----------------------------------------------------*/
    public void removeLeadingZeroes ()
    {
	int[] old;

	int partition = this.size;
	while ((this.digits [this.size - 1] == 0) && (this.size > 1))
	{
	    this.size--;

	}
	old = this.digits;
	this.digits = new int [this.size];
	for (int i = this.size - 1 ; i != -1 ; i--)
	{
	    this.digits [i] = old [i];
	}

    }
}
