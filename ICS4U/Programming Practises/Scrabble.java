// The "Scrabble" class.
// Date: 9/26/2018
// Author: Eric Zhou
// Purpose: To create and show the functionality of the Tile and ScrabbleHand class
// Input: Keyboard
// Output: Console
// ------------------------------------------------------------------------------------
import java.awt.*;
import hsa.Console;
import java.util.Random;

public class Scrabble
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
	c = new Console (25, 128);

	boolean loop = true;
	String rerun = "y";

	while (loop == true)
	{
	    c.clear ();
	    String getOwnValue = "";
	    String userValue = "";
	    boolean getRandomize;

	    //getting 1 user input
	    do
	    {
		c.print ("Would you like to insert your own value? Y/N : ");
		getOwnValue = c.readString ();
	    }
	    while (!(getOwnValue.toUpperCase ().equals ("Y") || getOwnValue.toUpperCase ().equals ("N")));
	    {
		if (getOwnValue.toUpperCase ().equals ("N"))
		{
		    getRandomize = true;
		}
		else
		{
		    getRandomize = false;
		    c.print ("What tile would you like?: ");
		    userValue = c.readString ();
		}
	    }

	    int x = 0; // x cord
	    int y = 200; // y cord
	    int xShift = 77;
	    int yShift = 77;
	    int tileCounter = 0;

	    if (getRandomize == false)
	    {
		Tile myTile = new Tile (userValue);
		c.print ("Letter: " + myTile);
		c.println (", Value: " + myTile.value ());

		//first 13
		for (tileCounter = 0 ; tileCounter <= 14 ; tileCounter++)
		{
		    myTile.print (x + (tileCounter * xShift), y, c);
		}


		//last 13
		for (tileCounter = 0 ; tileCounter <= 14 ; tileCounter++)
		{
		    myTile.print (x + (tileCounter * xShift), y + yShift, c);
		}

	    }

	    if (getRandomize == true)
	    {
		c.println ("Now Randomizing . . .");
		//first 13

		for (tileCounter = 0 ; tileCounter <= 13 ; tileCounter++)
		{
		    Tile myTile = new Tile ();
		    myTile.print (x + (tileCounter * xShift), y, c);
		}
		//last 13
		for (tileCounter = 0 ; tileCounter <= 14 ; tileCounter++)
		{
		    Tile myTile = new Tile ();
		    myTile.print (x + (tileCounter * xShift), y + yShift, c);
		}
	    }

	    //alphabet print

	    String alphabet = "";
	    do
	    {
		c.print ("Would you like to see the alphabet? Y/N: ");
		alphabet = c.readString ();
	    }
	    while (!(alphabet.toUpperCase ().equals ("Y") || alphabet.toUpperCase ().equals ("N")));


	    if (alphabet.toUpperCase ().equals ("Y"))
	    {
		c.clear ();
		char start = 97;
		//first 13
		for (tileCounter = 0 ; tileCounter <= 12 ; tileCounter++, start++)
		{
		    Tile myTile = new Tile (Character.toString (start));
		    myTile.print (x + (tileCounter * xShift), y, c);
		}
		//last 13
		for (tileCounter = 0 ; tileCounter <= 12 ; tileCounter++, start++)
		{
		    Tile myTile = new Tile (Character.toString (start));
		    myTile.print (x + (tileCounter * xShift), y + yShift, c);
		}
	    }
	    String getHand = "";
	    do
	    {
		c.print ("Would you like to input a scrabble hand? Y/N: ");
		getHand = c.readString ();
		getHand = getHand.toUpperCase ();
	    }
	    while (!(getHand.equals ("Y") || getHand.equals ("N")));
	    {
		c.clear ();
	    }

	    if (getHand.equals ("Y"))
	    {
		String letter1 = "YEE";
		String letter2 = "YEE";
		String letter3 = "YEE";
		String letter4 = "YEE";
		String letter5 = "YEE";
		
		//avoiding getting the same letter
		c.print ("First Letter: ");
		letter1 = c.readString ();
		//2nd letter
		c.print ("Second Letter: ");
		letter2 = c.readString ();
		while (letter2.equals (letter1))
		{
		    c.println ("Cannot be the same!");
		    c.print ("Second Letter: ");
		    letter2 = c.readString ();
		}
		//3rd letter
		c.print ("Third Letter: ");
		letter3 = c.readString ();
		while (letter3.equals (letter1) || letter3.equals (letter2))
		{
		    c.println ("Cannot be the same!");
		    c.print ("Third Letter: ");
		    letter3 = c.readString ();
		}
		//4th letter
		c.print ("Fourth Letter: ");
		letter4 = c.readString ();
		while (letter4.equals (letter1) || letter4.equals (letter2) || letter4.equals (letter3))
		{
		    c.println ("Cannot be the same!");
		    c.print ("Fourth Letter: ");
		    letter4 = c.readString ();
		}
		//5th letter
		c.print ("Fifth Letter: ");
		letter5 = c.readString ();
		while (letter5.equals (letter1) || letter5.equals (letter2) || letter5.equals (letter3) || letter5.equals (letter4))
		{
		    c.println ("Cannot be the same!");
		    c.print ("Fifth Letter: ");
		    letter5 = c.readString ();
		}

		Tile tile1 = new Tile (letter1);
		Tile tile2 = new Tile (letter2);
		Tile tile3 = new Tile (letter3);
		Tile tile4 = new Tile (letter4);
		Tile tile5 = new Tile (letter5);
		
		c.clear ();

		//ScrabbleHand INPUT
		ScrabbleHand playerHand = new ScrabbleHand (tile1, tile2, tile3, tile4, tile5);
		c.print ("Player Hand: " + playerHand + " ");

		c.println (playerHand.value ());
		c.println ();
		c.println ();
		c.println ();
		c.println ();
		c.println ();
		c.println ();
		c.println ("All the possible permutations are: ");
		
		String[] permutation = new String [5]; //arraySize = tile: 1,2,3,4,5
		
		permutation [0] = "" + playerHand.tile1;
		permutation [1] = "" + playerHand.tile2;
		permutation [2] = "" + playerHand.tile3;
		permutation [3] = "" + playerHand.tile4;
		permutation [4] = "" + playerHand.tile5;
		
		c.println ();
		playerHand.printPermutations (permutation.length, permutation, c);
		playerHand.print (550, 10, c);
		c.println ();
	    }

	    if (getHand.equals ("N"))
	    {
		//ScrabbleHand RANDOM
		ScrabbleHand playerHand = new ScrabbleHand ();
		c.print ("Player Hand: " + playerHand + " ");

		c.println (playerHand.value ());
		c.println ();
		c.println ();
		c.println ();
		c.println ();
		c.println ();
		c.println ();
		c.println ("All the possible permutations are: ");
		String[] permutation = new String [5];
		permutation [0] = "" + playerHand.tile1;
		permutation [1] = "" + playerHand.tile2;
		permutation [2] = "" + playerHand.tile3;
		permutation [3] = "" + playerHand.tile4;
		permutation [4] = "" + playerHand.tile5;
		c.println ();
		playerHand.printPermutations (5, permutation, c);
		playerHand.print (550, 10, c);
		c.println ();
	    }


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
	} // loop


	// Place your program here.  'c' is the output console
    } // main method
} // Scrabble class.

// The "Title" class.
// Date: 9/26/2018
// Author: Eric Zhou
// Purpose: To hold a field called letter (char variable)
// Data Element(s):
/*
    letter: the letter of the tile
*/
// Constructors: 1. Default Input (randomize) 2. One letter input from user
// Methods:
/*
    toString: Returns PRINT letter only!
    value: returns the value of the letter tile
    print: prints the tile with a given coordinate
*/
// ------------------------------------------------------------------------------------
class Tile
{
    public char letter;
    // Field Letter: It is the letter of a specific title.

    public Tile ()  // Random constructor
    {
	this.letter = (char) ('A' + random.nextInt (26));
	//nextInt gets the next integer of the starting point of A
	//Single quotation becuz its a character
    }


    private static Random random = new Random (); // for different values, if public, same value

    public Tile (String letter)  // User constructor
    {
	letter = letter.toUpperCase ();
	char charLetter = letter.charAt (0);
	String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	boolean trueLetter = false;

	// checks to see if the letter is in the alphabet
	for (int checker = 0 ; checker <= 25 ; checker++)
	{
	    if (charLetter == alphabet.charAt (checker))
	    {
		trueLetter = true;
	    }
	}

	if (trueLetter == true)
	{
	    this.letter = charLetter;
	}

	else
	{
	    this.letter = 32; // a space which i guess is a blank value
	}
    }


    // Date: 9/27/2018
    // Author: Eric Zhou
    // Purpose: Returns the letter of the tile
    // Input: None
    // Output: Letter
    // ------------------------------------------------------
    public String toString ()
    {
	return Character.toString (this.letter);
    }


    // Date: 9/27/2018
    // Author: Eric Zhou
    // Purpose: Returns the value of the letter
    // Input: None
    // Output: Value of the letter
    // ------------------------------------------------------
    public int value ()
    {
	int returnValue;

	if (Character.toString (this.letter).equals ("A") || Character.toString (this.letter).equals ("E") || Character.toString (this.letter).equals ("I") || Character.toString (this.letter).equals ("I")
		|| Character.toString (this.letter).equals ("L") || Character.toString (this.letter).equals ("O") || Character.toString (this.letter).equals ("N") || Character.toString (this.letter).equals ("S")
		|| Character.toString (this.letter).equals ("U") || Character.toString (this.letter).equals ("T") || Character.toString (this.letter).equals ("R"))
	{
	    returnValue = 1;
	}
	else if (Character.toString (this.letter).equals ("D") || Character.toString (this.letter).equals ("G"))
	{
	    returnValue = 2;
	}
	else if (Character.toString (this.letter).equals ("B") || Character.toString (this.letter).equals ("M") || Character.toString (this.letter).equals ("P") || Character.toString (this.letter).equals ("C"))
	{
	    returnValue = 3;
	}
	else if (Character.toString (this.letter).equals ("F") || Character.toString (this.letter).equals ("H") || Character.toString (this.letter).equals ("W") || Character.toString (this.letter).equals ("Y") || Character.toString (this.letter).equals ("V"))
	{
	    returnValue = 4;
	}
	else if (Character.toString (this.letter).equals ("K"))
	{
	    returnValue = 5;
	}
	else if (Character.toString (this.letter).equals ("X") || Character.toString (this.letter).equals ("J"))
	{
	    returnValue = 8;
	}
	else if (Character.toString (this.letter).equals ("Z") || Character.toString (this.letter).equals ("Q"))
	{
	    returnValue = 10;
	}
	else
	{
	    returnValue = 0;
	}
	return returnValue;


    }


    // Date: 9/27/2018
    // Author: Eric Zhou
    // Purpose: Prints the tile of the letter
    // Input: X coordinate, y coordinate, console to print
    // Output: None
    // ------------------------------------------------------
    public void print (int x, int y, Console c)
    {
	// shape
	int width = 75;
	Color scrabble = new Color (255, 234, 180);
	c.setColor (scrabble);
	c.fillRect (x, y, width, width);

	// font
	c.setColor (Color.black);

	//LETTER
	Font letterFont = new Font ("Arial", Font.BOLD, 42);
	c.setFont (letterFont);
	c.drawString ("" + this.letter, x + (width / 5), y + (width / 3) * 2);

	if (value () > 0)
	{
	    //NUMBER (value)
	    Font numFont = new Font ("Arial", Font.BOLD, 21);
	    c.setFont (numFont);
	    c.drawString ("" + value (), (x + (width / 3) * 2), y + (width / 3) * 2);
	}

    }
}
// The "ScrabbleHand" class.
// Date: 9/27/2018
// Author: Eric Zhou
// Purpose: To hold a player scrabble's hand
// Data Element(s):
/*
    tile1: the first tile of the hand
    tile2: the second tile of the hand
    tile3: the third tile of the hand
    tile4: the fourth tile of the hand
    tile5: the fifth tile of the hand
    numOfPermutations: to format the printing of permutations per every 12.
*/
// Constructors: 1. Randomize 2.Player Input
// Methods:
/*
    toString: Returns string scrabble hand
    value: returns the value of the hand
    print: prints the hand of the player's
    printPermutations: Prints the permutation of the current hand
*/
// ------------------------------------------------------------------------------------
class ScrabbleHand
{
    public Tile tile1;
    public Tile tile2;
    public Tile tile3;
    public Tile tile4;
    public Tile tile5;
    public int numOfPermutation;

    public ScrabbleHand ()
    {
	this.tile1 = new Tile ();
	this.tile2 = new Tile ();
	this.tile3 = new Tile ();
	this.tile4 = new Tile ();
	this.tile5 = new Tile ();
    }


    public ScrabbleHand (Tile tile1, Tile tile2, Tile tile3, Tile tile4, Tile tile5)
    {
	this.tile1 = tile1;
	this.tile2 = tile2;
	this.tile3 = tile3;
	this.tile4 = tile4;
	this.tile5 = tile5;
    }


    // Date: 9/28/2018
    // Author: Eric Zhou
    // Purpose: Returns the value of the hand.
    // Input: None
    // Output: Value of the scrabblehand
    // ------------------------------------------------------
    public int value ()
    {
	int value1 = this.tile1.value ();
	int value2 = this.tile2.value ();
	int value3 = this.tile3.value ();
	int value4 = this.tile4.value ();
	int value5 = this.tile5.value ();
	int total = value1 + value2 + value3 + value4 + value5;

	return total;
    }


    // Date: 9/28/2018
    // Author: Eric Zhou
    // Purpose: Prints the hand of the player's
    // Input: None
    // Output: Returns the String of the hand of the player's
    // ------------------------------------------------------
    public String toString ()
    {
	String playerHand = ("" + this.tile1 + ", " + this.tile2 + ", " + this.tile3 + ", " + this.tile4 + ", " + this.tile5);
	return playerHand;
    }


    // Date: 9/28/2018
    // Author: Eric Zhou
    // Purpose: Draws the player's hand
    // Input: None
    // Output: None
    // ------------------------------------------------------
    public void print (int x, int y, Console c)
    {
	int xShift = 77;
	int yShift = 77;
	tile1.print (x, y, c);
	tile2.print (x + xShift, y, c);
	tile3.print (x + xShift * 2, y, c);
	tile4.print (x + xShift * 3, y, c);
	tile5.print (x + xShift * 4, y, c);

	Font valueFont = new Font ("Arial", Font.BOLD, 30);
	c.setColor (Color.black);
	c.setFont (valueFont);

	int handValue = this.value ();
	c.drawString (" (" + value () + ") ", x + xShift * 5, y + (yShift / 3) * 2);
    }


    // Date: 9/28/2018
    // Author: Eric Zhou
    // Purpose: Prints the permutation of the current hand
    // Input: None
    // Output: None
    // ------------------------------------------------------
    public void printPermutations (int arraySize , String[] permutation, Console c)
    {
	// a,b,c,d,e
	// 0,1,2,3,4; index
	if (arraySize == 1)
	{
	    c.print (permutation [0]);
	    c.print (permutation [1]);
	    c.print (permutation [2]);
	    c.print (permutation [3]);
	    c.print (permutation [4]);
	    c.print (" ");

	    this.numOfPermutation += 1;

	    if (this.numOfPermutation % 12 == 0)
	    {
		c.println ();
	    }
	}
	
	else
	{
	    String temp; // to swap a certain part of the index of the array.
	    for (int index = 0 ; index <= (arraySize - 1) ; index++)
	    {
		printPermutations (arraySize - 1, permutation, c);

		if (arraySize % 2 == 0)
		{
		    temp = permutation [index];
		    permutation [index] = permutation [arraySize - 1];
		    permutation [arraySize - 1] = temp;
		}
		else
		{   // for odd swaps
		    temp = permutation [0];
		    permutation [0] = permutation [arraySize - 1];
		    permutation [arraySize - 1] = temp;
		}
	    }
	}
    }
} // ScrabbleHand class

