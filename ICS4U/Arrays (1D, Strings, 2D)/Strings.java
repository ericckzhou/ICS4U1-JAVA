// The "Strings" class.
/*----------------------------------------
Author: Eric Zhou
Date: 10/15/2018
Purpose: To complete String exercise
Input: Keyboard
Output: Console
-----------------------------------------*/
import java.awt.*;
import hsa.Console;

/*----------------------------------------
Author: Eric Zhou
Date: 10/15/2018
Purpose: To make a Strings class
Data Elements: None
Methods:
    replace: replaces all occurrence of old strings in new string.
    permuted: Sees if two strings are permutations of each other.
-----------------------------------------*/
public class Strings
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
	c = new Console ();
	boolean loop = true;

	while (loop == true)
	{
	    c.print ("What text would you like to input: "); //"This is the state of Mississippi."
	    String str = c.readLine ();
	    c.print ("What is the old string?: ");
	    String oldStr = c.readLine (); // is
	    c.print ("What is the new string?: ");
	    String newStr = c.readLine (); //twis
	    c.println ();

	    StringBuffer replacedStr = replace (str, oldStr, newStr);
	    c.println ("The string, '" + str + "', where each '" + oldStr + "' is replaced with '" + newStr + "' is: ");
	    c.println ("" + replacedStr.toString ());
	    c.println ();

	    c.print ("Input a string that you would like to check if it's a permutation!: ");
	    String firstString = c.readLine (); //hello
	    c.print ("Input another string!: ");
	    String secondString = c.readLine (); //loelh
	    c.println ("The string '" + firstString + "' and '" + secondString + "' are permutations of each other: " + permuted (firstString, secondString));
	    c.println ();

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
		if (rerun.toUpperCase().equals("Y"))
		{
		    c.clear();
		}
	    }
	}
	// Place your program here.  'c' is the output console
    } // main method


    /*----------------------------------------
    Author: Eric Zhou
    Date: 10/15/2018
    Purpose: replaces all occurrence of old strings in new string.
    Input: String, old string, new string
    Output: New StringBuffer
    -----------------------------------------*/
    public static StringBuffer replace (String str, String oldStr, String newStr)
    {
	//old string MAY contain new string
	StringBuffer returnString = new StringBuffer ();
	int strIndex = str.indexOf (oldStr);
	int lastIndex = 0;

	if (str.startsWith (oldStr, strIndex) == false)
	{
	    returnString.append (str); //returning original if there are no oldStr in str to be replaced with newStr
	}

	while (str.startsWith (oldStr, strIndex) == true)
	{
	    returnString.append (str.substring (lastIndex, strIndex) + newStr);
	    //appends str up to oldstr but not the oldstr itself and adds the new str as well
	    lastIndex = strIndex + oldStr.length ();
	    strIndex = str.indexOf (oldStr, lastIndex);
	    if (str.startsWith (oldStr, strIndex) == false)
	    {
		returnString.append (str.substring (lastIndex, str.length ()));
		//adds rest of string if there are any left
		//if lastIndex == strSize, it adds nothing
	    }
	}

	return returnString;
    } //replace method


    /*----------------------------------------
    Author: Eric Zhou
    Date: 10/15/2018
    Purpose: Sees if the two strings are permutations of each other.
    Input: String a and String b
    Output: boolean answer if strings are permutations
    -----------------------------------------*/
    public static boolean permuted (String a, String b)
    {
	//hello & lehlo = true, 'hello' and 'hello ' = false
	//manipulating one string to try and make it equal to the other.
	boolean isPermuted = false;

	if (a.length () == b.length ())
	{
	    StringBuffer dupB = new StringBuffer ();
	    dupB.append (b);
	    int strIndex = 0;

	    //removing the sane letters in a from b if there are any
	    //if b == "", they are permuted
	    while (strIndex < a.length ())
	    {
		String letter = a.substring (strIndex, strIndex + 1);
		int findIndex = dupB.toString ().indexOf (letter);
		if (findIndex != -1)
		{
		    dupB.deleteCharAt (findIndex);
		}
		strIndex++;
	    }

	    if (dupB.toString ().equals (""))
	    {
		isPermuted = true;
	    }

	}

	return isPermuted;
    } //permuted method
} // Strings class
