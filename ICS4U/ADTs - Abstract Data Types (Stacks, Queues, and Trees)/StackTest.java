// ------------------------------------------------------ //
// The "StackTest" class.
// Author: Eric Zhou
// Date: 12/18/2018
// Purpose: To complete stack exercise.
// Input: Keyboard
// Output: Console
// ------------------------------------------------------ //

import java.awt.*;
import hsa.Console;
import java.util.*;
import java.io.*;
import hsa.*;

public class StackTest
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
	c = new Console ();
	boolean quit = false;
	int action = -1;
	boolean loop = true;
	while (loop == true)
	{
	    while (quit == false)
	    {
		Parenthesis p = new Parenthesis (c);
		do
		{
		    c.println ("1. stringBracketCheck, 2. fileBracketCheck, 3. Quit");
		    c.print ("What do you want to do: ");
		    action = c.readInt ();

		    if (action < 1 || action > 3)
		    {
			c.println ("User must choose from 1 - 3.");
		    }
		}
		while (action < 1 || action > 3);
		c.println ();
		if (action == 1)
		{
		    p.getString ();
		    c.println ("User entered: " + p.str);
		    c.println (p.stringBracketCheck (p.str));
		}
		else if (action == 2)
		{
		    p.findFile ();
		    p.readFromFile ();
		}
		else if (action == 3)
		{
		    quit = true;
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
		    quit = false;
		}
	    }
	}



	// Place your program here.  'c' is the output console
    } // main method
} // StackTest class
/*---------------------------------------------------
Class: Node
Author: Eric Zhou
Date: 12/5/2018
Purpose: Holds a node.
Data Elements:
    data: a String value of the data.
    next: a reference pointer to the next node.
Methods:
    toString: returns all the data currently held in the linked list in a string.
----------------------------------------------------*/
class Node
{
    public String data;
    public Node next;

    public Node (String info)
    {
	this.data = info;
	this.next = null;
    }


    public Node ()
    {
	this.data = "";
	this.next = null;
    }


    /*---------------------------------------------------
    Author: Eric Zhou
    Date: 12/5/2018
    Purpose: Returns the data and the next node reference (if there is) in a String.
    Input: None
    Output: A string (all data)
    -----------------------------------------------------*/

    public String toString ()
    {
	String s = "";
	s += this.data + " ";

	if (this.next != null)
	{
	    s += this.next.toString ();
	}
	return s;
    }
} //Node class
/*---------------------------------------------------
Class: Stack
Author: Eric Zhou
Date: 12/5/2018
Purpose: To create a Singly Linked Stack List
Data Elements:
    list: contains a Node reference to all the Nodes in the list.
Methods:
    isEmpty: sees if the list is empty.
    toString: returns data in the list.
    push: pushes a string on top of the stack list.
    pop: pops the string at the very top of the stack list, if there is a string.
-----------------------------------------------------*/
class Stack
{
    protected Node list;

    public Stack ()
    {
	this.list = null;
    }


    public Stack (Node getList)
    {
	this.list = getList;
    }


    /*---------------------------------------
    Author: Eric Zhou
    Date: 12/5/2018
    Purpose: Sees if the list is empty.
    Input: None
    Output: Boolean
    ----------------------------------------*/
    public boolean isEmpty ()
    {
	boolean empty = false;
	if (this.list == null)
	{
	    empty = true;
	}
	return empty;
    }


    /*---------------------------------------
    Author: Eric Zhou
    Date: 12/5/2018
    Purpose: Returns the list as a String
    Input: None
    Output: The list in string
    ----------------------------------------*/
    public String toString ()
    {
	String s = "";
	if (this.list == null)
	{
	    s += "Empty";
	}
	else
	{
	    s += list.toString ();
	}
	return s;
    }


    /*---------------------------------------
    Author: Eric Zhou
    Date: 12/18/2018
    Purpose: pushes a string on top of the stack list.
    Input: A string to push
    Output: None
    ----------------------------------------*/
    public void push (String str)
    {
	Node newNode = new Node (str);
	if (this.list != null)
	{
	    Node curr = this.list;
	    newNode.next = curr;
	    this.list = newNode;
	}
	else
	{
	    this.list = newNode;
	}
    }


    /*---------------------------------------
    Author: Eric Zhou
    Date: 12/18/2018
    Purpose: pops the string at the very top of the stack list, if there is a string.
    Input: None
    Output: None
    ----------------------------------------*/
    public String pop ()
    {
	String s = "";
	if (this.list != null)
	{
	    Node curr = this.list;
	    s = curr.data;
	    Node next = curr.next;
	    this.list = next;
	}
	return s;

    }
} //class SinglyLinkedList
/*-----------------------------------------------------------
Class: Parenthesis
Author: Eric Zhou
Date: 12/18/2018
Purpose: To create a parenthesis class.
Data Elements:
    str: a string field used by methods in this class.
    c: a console field
    stack: a stack field.
Methods:
    getString: gets a string for the str field. Also used to store file name if fileBracketCheck is called.
    findFile: asks user for a text file, containing a equation on each line.
    readFromFile: reads the content of the file.
    stackReset: resets the stack list after every equation to remove leftovers.
    stringBracketCheck: returns a string message with "Good Structure" or an appropriate message
    depending if a given string uses brackets correctly.
    fileBracketCheck: calls stringBracketCheck to check a line (string) from file. Returns an appropriate message of the line.
-------------------------------------------------------------*/
class Parenthesis
{
    public String str;
    public Console c;
    public Stack stack;

    public Parenthesis (Console console)
    {
	this.str = "";
	this.c = console;
	this.stack = new Stack ();
    }


    public Parenthesis (Console console, Stack s)
    {
	this.str = "";
	this.c = console;
	this.stack = s;
    }


    public Parenthesis ()
    {
	this.str = "";
	this.c = new Console ();
	this.stack = new Stack ();
    }


    /*----------------------------------------------
    Author: Eric Zhou
    Date: 12/20/2018
    Purpose: To get a string for the str field.
    Input: None
    Output: None
    ---------------------------------------------*/
    public void getString ()
    {
	this.c.print ("Enter a string to be checked: ");
	this.str = c.readLine ();

    }


    /*----------------------------------------------
    Author: Eric Zhou
    Date: 12/20/2018
    Purpose: Gets the file name from user.
    Input: None
    Output: None
    ---------------------------------------------*/
    public void findFile ()
    {
	this.c.print ("What is the file name: ");
	this.str = c.readLine () + ".txt";
    }


    /*----------------------------------------------
    Author: Eric Zhou
    Date: 12/20/2018
    Purpose: Reads the content of the file.
    Input: None
    Output: None
    ---------------------------------------------*/
    public void readFromFile ()
    {
	TextInputFile in = new TextInputFile (this.str);
	while (!(in.eof ()))
	{
	    String line = in.readLine ();
	    this.c.println ("Read from file: " + line);
	    this.c.println (fileBracketCheck (line));
	    this.c.print ("Press any key to continue...");
	    this.c.readChar();
	    this.c.println();
	}
    }


    /*----------------------------------------------
    Author: Eric Zhou
    Date: 12/20/2018
    Purpose: Resets the stack list.
    Input: None
    Output: None
    ---------------------------------------------*/
    public void stackReset ()
    {
	this.stack.list = null;
	
    }


    /*----------------------------------------------
    Author: Eric Zhou
    Date: 12/20/2018
    Purpose: returns a string message with "Good Structure" or an appropriate message
    depending if a given string uses brackets correctly.
    Input: A string
    Output: A message of the structure of the brackets used.
    ---------------------------------------------*/
    public String stringBracketCheck (String s)
    {
	boolean errorFound = false;
	String msg = "Good Structure.";
	String bracket = "";
	int lastOpen = 0;
	for (int i = 0 ; i < s.length () && errorFound == false ; i++)
	{
	    String letter = s.substring (i, i + 1);
	    String otherBracket = "";
	    if (i < s.length () - 1)
	    {
		otherBracket = s.substring (i, i + 2);
	    }
	    if (letter.equals ("(") || letter.equals ("[") || letter.equals ("{")) //open bracket
	    {
		stack.push (letter);
		lastOpen = i;
	    }
	    else if (otherBracket.equals ("/*"))
	    {
		stack.push (otherBracket);
		lastOpen = i;
	    }
	    else if (letter.equals (")") || letter.equals ("]") || letter.equals ("}") || otherBracket.equals ("*/")) // close bracket
	    {
		if (stack.list != null)
		{
		    bracket = stack.pop ();
		    if (bracket.equals ("(") && letter.equals (")"))
		    {
			errorFound = false;
		    }
		    else if (bracket.equals ("{") && letter.equals ("}"))
		    {
			errorFound = false;
		    }
		    else if (bracket.equals ("[") && letter.equals ("]"))
		    {
			errorFound = false;
		    }
		    else if (bracket.equals ("/*") && otherBracket.equals ("*/"))
		    {
			errorFound = false;
		    }
		    else
		    {
			errorFound = true;
		    }
		    if (errorFound == true)
		    {
			msg = "Mismatch of close bracket detected at Position " + i + ".";
			errorFound = true;
		    }
		}
		else
		{
		    msg = "Too many close bracket detected at Position " + i + ".";
		    errorFound = true;
		}

	    }
	}
	if (stack.list != null && errorFound == false)
	{
	    msg = "Too many open brackets detected at " + lastOpen + ".";
	}

	this.stackReset (); //resets the stack list to remove any leftovers.
	return msg;

    }


    /*----------------------------------------------
    Author: Eric Zhou
    Date: 12/20/2018
    Purpose: Calls stringBracketCheck to check a line from a file.
    Input: A string
    Output: A message of the structure of the brackets used.
    ---------------------------------------------*/
    public String fileBracketCheck (String s)
    {
	String msg = this.stringBracketCheck (s);
	return msg;
    }
}


