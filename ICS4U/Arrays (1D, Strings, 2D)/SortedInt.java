// The "SortedInt" class.
/* ---------------------------------------------
Date: Oct 4, 2018
Author: Eric Zhou
Purpose: To create a sortedintgroup class.
Input: Keyboard
Output: Console
------------------------------------------------ */

import java.awt.*;
import hsa.Console;

public class SortedInt
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
	c = new Console ();
	boolean loop = true;
	while (loop == true)
	{
	    int[] array;
	    array = new int [10];
	    //setting preferred values
	    array [0] = 1;
	    array [1] = 1;
	    array [2] = 3;
	    array [3] = 5;
	    array [4] = 7;
	    array [5] = 7;
	    array [6] = 8;
	    array [7] = 10;
	    array [8] = 11;
	    array [9] = 12;

	    int num = (int) (Math.random () * 15) + 1;
	    //generating fixed array elements from 1 to randomSize
	    while (num < 2)
	    {
		num = (int) (Math.random () * 15) + 1;
	    }

	    SortedIntGroup sig = new SortedIntGroup (array);
	    SortedIntGroup sig2 = new SortedIntGroup (num);

	    //originals
	    c.println ("Array #1: " + sig);
	    c.println ("Array #2: " + sig2);
	    c.println ();

	    //run
	    Run longestRun = sig.longestRun ();
	    c.print ("Run (A#1): ");
	    c.print ("start = " + longestRun.start);
	    c.print (",");
	    c.print ("length = " + longestRun.length);

	    //run#2
	    Run longestRun2 = sig2.longestRun ();
	    c.println ();
	    c.print ("Run (A#2): ");
	    c.print ("start = " + longestRun2.start);
	    c.print (",");
	    c.print ("length = " + longestRun2.length);

	    c.println ();

	    //removed dups of originals
	    c.println ();
	    c.println ("Removed Duplicates");
	    sig.dropDups ();
	    sig2.dropDups ();
	    c.println ("Array #3: " + sig);
	    c.println ("Array #4: " + sig2);


	    //run#3 of the removed dups of Array#3
	    Run longestRun3 = sig.longestRun ();
	    c.println ();
	    c.print ("Run (A#3): ");
	    c.print ("start = " + longestRun3.start);
	    c.print (",");
	    c.print ("length = " + longestRun3.length);

	    //run#4 of the removed dups of Array#4
	    Run longestRun4 = sig2.longestRun ();
	    c.println ();
	    c.print ("Run (A#4): ");
	    c.print ("start = " + longestRun4.start);
	    c.print (",");
	    c.print ("length = " + longestRun4.length);
	    c.println ();

	    //merged of the removed dups Array#3 and Array#4
	    c.println ();
	    SortedIntGroup merged = sig.merge (sig2);
	    c.print ("Merged (A#3 + A#4): " + merged);

	    //removed dups of merged
	    c.println ();
	    c.println ();
	    c.println ("Removed Duplicates of merged");
	    merged.dropDups ();
	    c.print ("Merged: " + merged);
	    c.println ();
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
    } // SortedInt class


    /* --------------------------------------------------------
    Date: Oct 4, 2018
    Author: Eric Zhou
    Purpose: To create a SortedIntGroup class.

    Data Elements:
	array: holds an integer array
	size: holds the size of the array
	unique: holds the # of unique numbers in an array (non duplicates)

    Methods:
	toString: Converts the element to a string with the size printed after in brackets.
	sorted: Returns true if the elements is sorted in ascending order.
	dropDups: Removes any duplicate values from element.
	removeZeroes: remove any unneccessary zeroes (suppose to be empty) from the array
	merge: This will merge the current SortedIntGroup with another.
	longestRun: finds the largest run in element.

    ------------------------------------------------------------ */
    class SortedIntGroup
    {
	public int[] array;
	public int size;
	public int uniqueNum;

	public SortedIntGroup (int getSize)
	{
	    this.array = new int [getSize];

	    for (int i = 0 ; i < getSize ; i++)
	    {
		this.array [i] = (i + 1);
	    }

	    this.size = getSize;
	}


	public SortedIntGroup (int[] getArray)
	{
	    this.size = getArray.length;
	    this.array = new int [this.size];
	    for (int i = 0 ; i < this.size ; i++)
	    {
		this.array [i] = getArray [i];
	    }

	    boolean isSorted = sorted ();
	    if (isSorted == false)
	    {
		this.size = 0;
		this.array = new int [0];
	    }
	}


	public SortedIntGroup ()
	{
	    this.size = 0;
	    this.array = new int [0];
	}


	public SortedIntGroup (int[] getArray, int getSize)
	{
	    this.size = getSize;
	    for (int i = 0 ; i < this.size ; i++)
	    {
		this.array [i] = getArray [i];
	    }
	}


	/* ----------------------------------------
	Date: Oct 5, 2018
	Author: Eric Zhou
	Purpose: To print the array within brackets
	Input: None
	Output: The formatted array
	 ----------------------------------------- */
	public String toString ()
	{
	    String statement = "" + this.array [0];

	    for (int index = 1 ; index < this.size ; index++)
	    {
		statement += "," + this.array [index];
	    }
	    return statement;
	}


	/* ----------------------------------------
	Date: Oct 5, 2018
	Author: Eric Zhou
	Purpose: To see if the given array is sorted.
	Input: Integer Array
	Output: Boolean answer
	 ----------------------------------------- */
	private boolean sorted ()
	{
	    boolean isSorted = true;

	    for (int i = 0 ; i < this.size - 1 ; i++)
	    {
		if (this.array [i] > this.array [i + 1] && this.array [i] != 0 && this.array [i + 1] != 0)
		{
		    isSorted = false;
		}
	    }
	    return isSorted;
	}


	/* ----------------------------------------
	Date: Oct 5, 2018
	Author: Eric Zhou
	Purpose: Removes duplicates in the original array.
	Input: None
	Output: None
	 ----------------------------------------- */
	public void dropDups ()
	{
	    //find unique nums, make a array with that num, put the unique num in the array.
	    int indexNum = 0; //
	    this.uniqueNum = 1; //unique array elements

	    for (int i = 0 ; i < this.size - 1 ; i++)
	    {
		if (this.array [i] != this.array [i + 1] && this.array [i] != 0 && this.array [i + 1] != 0)
		{
		    this.uniqueNum++;
		}
	    }

	    int count = 0;
	    while (this.uniqueNum != count)
	    {
		for (int i = 0 ; i < this.size - 1 ; i++)
		{
		    if (this.array [i] == this.array [i + 1] || this.array [i] == 0) // 0 = empty
		    {
			this.array [i] = this.array [i + 1];
			this.array [i + 1] = 0;
		    }
		}
		count += 1;
	    }
	    this.removeZeroes();
	}
	
    /*----------------------------------------------------
    /Author: Eric Zhou
    Date: Oct 11, 2018
    Purpose: Remove unneccessary zeroes which are suppose to be empty in the array
    Input: None
    Output: String
    From Moodle: https://moodle2.yrdsb.ca/pluginfile.php/1652929/mod_resource/content/1/BigInt%20Provided%20Inclusions.txt
    ----------------------------------------------------*/
	public void removeZeroes()
	{
		int[] old;

	int partition = this.size;
	while ((this.array [this.size - 1] == 0) && (this.size > 1))
	{
	    this.size--;

	}
	old = this.array;
	this.array= new int [this.size];
	for (int i = this.size - 1 ; i != -1 ; i--)
	{
	    this.array [i] = old [i];
	}
	}


	/* -----------------------------------------
	Date: Oct 5, 2018
	Author: Eric Zhou
	Purpose: To merge two SortedIntGroups
	Input: SortedIntGroup sig
	Output: A newly merged SortedIntGroup
	 ------------------------------------------- */
	public SortedIntGroup merge (SortedIntGroup sig)  // sig is short for SortedIntGroup
	{
	    int mergeSize = this.uniqueNum + sig.uniqueNum;
	    int[] mergeArr = new int [mergeSize];
	    int thisPar = 0; //partitions
	    int sigPar = 0;

	    if (this.sorted () == true && sig.sorted () == true)
	    {
		for (int mergePar = 0 ; mergePar < mergeSize ; mergePar++)
		{
		    if (thisPar < this.uniqueNum && sigPar < sig.uniqueNum)
		    {
			if (this.array [thisPar] <= sig.array [sigPar])
			{
			    mergeArr [mergePar] = this.array [thisPar];
			    thisPar++;
			}
			else
			{
			    mergeArr [mergePar] = sig.array [sigPar];
			    sigPar++;
			}

		    }
		    // zeroes start to appear after uniqueNum is over
		    if (sigPar >= sig.uniqueNum && thisPar < this.uniqueNum && this.array [thisPar] != 0)
		    {
			mergeArr [mergePar] = this.array [thisPar];
			thisPar++;
		    }

		    if (sigPar < sig.uniqueNum && thisPar >= this.uniqueNum && sig.array [sigPar] != 0)
		    {
			mergeArr [mergePar] = sig.array [sigPar];
			sigPar++;
		    }

		}
	    }

	    SortedIntGroup mergedGroup = new SortedIntGroup (mergeArr);
	    mergedGroup.removeZeroes();
	    return mergedGroup;
	}


	/* -----------------------------------------
	Date: Oct 5, 2018
	Author: Eric Zhou
	Purpose: To find the longest consecutive +1 run in the array
	Input: None
	Output: Run object containing start (index array) and length (how long)
	 ------------------------------------------- */
	public Run longestRun ()
	{
	    int start = 0;
	    int length = 0;
	    int prevLength = 0;
	    int prevStart = 0;

	    boolean continuousRun = false;

	    for (int i = 0 ; i < this.size - 1 ; i++)
	    {
		if (this.array [i] + 1 == this.array [i + 1])
		{
		    if (continuousRun == false)
		    {
			start = i;
		    }
		    length++;
		    continuousRun = true;
		}
		if (this.array [i] + 1 != this.array [i + 1])
		{
		    length++; // once it isnt true, missing 1 length.
		    if (continuousRun == true)
		    {
			if (length > prevLength)
			{
			    prevStart = start;
			    prevLength = length;
			}
		    }
		    length = 0;
		    continuousRun = false;
		}
		if (i == this.size - 2 && this.array [this.size - 2] + 1 == this.array [this.size - 1])
		{
		    length++;
		}
	    }

	    if (prevLength > length) //making sure that prevLength is less than length
	    {
		length = prevLength;
		start = prevStart;
	    }

	    Run getRun = new Run (start, length);

	    return getRun;
	}



    } // sortedintgroup class


    /* -----------------------------------------------------------
    Date: oct 5, 2018
    Author: Eric Zhou
    Purpose: To hold the start and run of the array
    Data Elements:
	start: the index of the start of the array with the run
	length: how long is the array with the run
    -------------------------------------------------------------- */

    class Run
    {
	public int start;
	public int length;

	public Run (int getStart, int getLength)
	{
	    start = getStart;
	    length = getLength;
	}
    } // run class


