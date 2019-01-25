// The "DSLLTest" class.
/*----------------------------------------------------------------------------------------------
Author: Eric Zhou
Date: 12/08/2018
Purpose: To create a list which stores both names and number with BOTH sorted at the same time.
Input: Keyboard
Output: Console
------------------------------------------------------------------------------------------------*/
import java.awt.*;
import hsa.Console;

public class DSLLTest
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
	c = new Console (35, 125);
	boolean loop = true;
	while (loop == true)
	{
	    DoubleSortedLL dsll = new DoubleSortedLL ();
	    String name;
	    int number;
	    boolean run = true;
	    boolean empty = false;
	    int nameOrNum;
	    while (run == true)
	    {

		String listName = dsll.byName ();
		String listNum = dsll.byNumber ();
		if (listName.equals ("Empty") || listNum.equals ("Empty"))
		{
		    empty = true;
		}
		c.println ("Name List (alphabetical): " + listName);
		c.println ("Number List (asecending): " + listNum);
		c.println ();
		int userAction = -1;
		do
		{
		    c.println ("What would user like to do: ");
		    c.print ("1. Insert, 2. Find, 3. Delete, 4. Exit: ");
		    userAction = c.readInt ();
		    if (userAction < 1 && userAction > 4)
		    {
			c.println ("Must choose from 1-4!");
		    }

		}
		while (userAction < 1 || userAction > 4);

		if (userAction == 1) //insert
		{
		    c.print ("What name would you like to insert: ");
		    name = c.readLine ();
		    c.print ("What number would you like to insert: ");
		    number = c.readInt ();
		    dsll.insert (name, number);
		    c.print ("Successfully inserted!");
		    c.println ();
		    empty = false;

		}
		else if (userAction == 2 && empty == false) //find
		{
		    Node findNode;
		    do
		    {
			c.print ("Would you like to find: 1.Name, 2.Number: ");
			nameOrNum = c.readInt ();
			if (nameOrNum != 1 && nameOrNum != 2)
			{
			    c.println ("Choose from 1.Name or 2.Number for finding.");
			}
		    }
		    while (!(nameOrNum == 1 || nameOrNum == 2));
		    if (nameOrNum == 1) //name
		    {
			c.print ("What is the name you would like to find: ");
			name = c.readLine ();
			findNode = dsll.find (name);
		    }
		    else
		    {
			c.print ("What is the number you would like to find: ");
			number = c.readInt ();
			findNode = dsll.find (number);
		    }
		    if (findNode == null)
		    {
			c.println ("The name/number you are looking for cannot be found!");
		    }
		    else
		    {
			name = findNode.name;
			number = findNode.number;
			c.println ("Found Name: " + name);
			c.println ("Found Number: " + number);
		    }
		}
		else if (userAction == 3 && empty == false) //delete
		{
		    Node deleteNode;
		    do
		    {
			c.print ("Would you like to delete (num): 1. Name, 2. Number: ");
			nameOrNum = c.readInt ();
			if (nameOrNum != 1 && nameOrNum != 2)
			{
			    c.println ("Choose from 1.Name or 2.Number for delete.");
			}
		    }
		    while (!(nameOrNum == 1 || nameOrNum == 2));

		    if (nameOrNum == 1) //name
		    {
			c.print ("What is the name you would like to delete: ");
			name = c.readLine ();
			deleteNode = dsll.find (name);
			if (deleteNode != null)
			{
			    dsll.delete (name);
			}
		    }
		    else
		    {
			c.print ("What is the number you would like to delete: ");
			number = c.readInt ();
			deleteNode = dsll.find (number);
			if (deleteNode != null)
			{
			    dsll.delete (number);
			}
		    }
		    if (deleteNode == null)
		    {
			c.println ("The name/number you are looking for cannot be found, thus was not deleted!");
		    }
		    else
		    {
			c.println ("Successfully deleted " + deleteNode.name + "," + deleteNode.number + "!");
		    }
		}
		else if (userAction == 4) //quit
		{
		    run = false;
		}

		if (empty == true && userAction != 1 && userAction != 4)
		{
		    c.println ("The linked list is EMPTY. Try inserting Nodes to find/delete.");
		}
		c.println ();
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
} // DSLLTest class
/*-------------------------------------------------
Author: Eric Zhou
Date: 12/08/2018
Purpose: To hold a Node.
Data Elements:
    name: a String name
    number: a Integer number
    nextName: Reference to the next name.
    nextNumber: Reference to the next number.
Methods:
    byName (recursion): returns the current name and number in alphabetical order
    byNumber (recursion): returns the current name and number in numerical ascending order.
-------------------------------------------------*/
class Node
{
    public String name;
    public int number;
    public Node nextName;
    public Node nextNumber;

    public Node (String getName, int getNum)
    {
	this.name = getName;
	this.number = getNum;
	this.nextName = null;
	this.nextNumber = null;
    }


    public Node ()
    {
	this.name = "";
	this.number = 0;
	this.nextName = null;
	this.nextNumber = null;
    }


    /*-------------------------------------------------------------------
    Author: Eric Zhou
    Date: 12/11/2018
    Purpose: returns the current name and number in alphabetical order
    Input: String str, Node curr
    Output: The string (nodes) in alphabetical order.
    -------------------------------------------------------------------*/
    public String byName (String str, Node curr)
    {
	if (curr == null)
	{
	    str += "Empty";
	}
	else if (curr.nextName == null)
	{
	    str += curr.name + "," + curr.number + "";
	}
	else
	{
	    str += curr.name + "," + curr.number + " -> ";
	}
	if (curr == null || curr.nextName == null)
	{
	    return str;
	}
	else
	{
	    return this.byName (str, curr.nextName);
	}
    }


    /*-------------------------------------------------------------------
    Author: Eric Zhou
    Date: 12/11/2018
    Purpose: returns the current name and number in numerical order
    Input: String str, Node curr
    Output: The string (nodes) in numerical order.
    -------------------------------------------------------------------*/
    public String byNumber (String str, Node curr)
    {
	if (curr == null)
	{
	    str += "Empty";
	}
	else if (curr.nextNumber == null)
	{
	    str += curr.number + "," + curr.name + "";
	}
	else
	{
	    str += curr.number + "," + curr.name + " -> ";
	}
	if (curr == null || curr.nextNumber == null)
	{
	    return str;
	}
	else
	{
	    return this.byNumber (str, curr.nextNumber);
	}
    }
} //class Node
/*----------------------------------------------------------
Author: Eric Zhou
Date: 12/08/2018
Purpose: To hold a double sorted linked list
Data Elements:
    listName: holds a Node linked list of names sorted
    listNumber: holds a Node linked list of numbers sorted
Methods:
    byName: returns a string byName
    byNumber: returns a string byNumber
    findBefore(name): return the node before where a given name is.
    findBefore(number): return the node before where a given number is.
    insert: inserts a given name and number into the list (twice).
    find (name): return the Node with a given name.
    find (number): return the Node with a given number.
    delete (name): deletes a Node with a givenName (string) from the listName.
    delete (number): deletes a Node with a givenNumber (int) from the listNumber.
------------------------------------------------------------*/
class DoubleSortedLL
{
    public Node listName;
    public Node listNumber;

    public DoubleSortedLL ()
    {
	this.listName = null;
	this.listNumber = null;
    }


    public DoubleSortedLL (Node name, Node number)
    {
	this.listName = name;
	this.listNumber = number;
    }


    /*-------------------------------------------------------------------
    Author: Eric Zhou
    Date: 12/11/2018
    Purpose: returns a string byName
    Input: None
    Output: The string (nodes) in alphabetical order.
    -------------------------------------------------------------------*/
    public String byName ()
    {
	String s = "";
	if (this.listName != null)
	{
	    s += this.listName.byName ("", this.listName);
	}
	else
	{
	    s += "Empty";
	}
	return s;
    }


    /*-------------------------------------------------------------------
    Author: Eric Zhou
    Date: 12/11/2018
    Purpose: returns the current name and number in numerical order.
    Input: None
    Output: The string (nodes) in numerical order.
    -------------------------------------------------------------------*/
    public String byNumber ()
    {
	String s = "";
	if (this.listName != null)
	{
	    s += this.listNumber.byNumber ("", this.listNumber);
	}
	else
	{
	    s += "Empty";
	}
	return s;
    }


    /*-------------------------------------------------------------------
    Author: Eric Zhou
    Date: 12/11/2018
    Purpose: return the node before given a name in listName
    Input: String findName
    Output: The Node found before
    -------------------------------------------------------------------*/
    public Node findBefore (String findName)
    {
	Node current = listName;
	if (current.name.compareToIgnoreCase (findName) >= 0) //if findName comes first alphabetically
	{
	    current = null;
	}
	else
	{
	    while (current.nextName != null && current.nextName.name.compareToIgnoreCase (findName) < 0) //loop when the next name comes first alphabetically
	    {
		current = current.nextName;
	    }
	}
	return current;

    }


    /*-------------------------------------------------------------------
    Author: Eric Zhou
    Date: 12/11/2018
    Purpose: return the node before given number in listNumber.
    Input: Integer findNumber
    Output: The Node found before
    -------------------------------------------------------------------*/
    public Node findBefore (int findNumber)
    {
	Node current = listNumber;
	if (findNumber <= current.number)
	{
	    current = null;
	}
	else
	{
	    while (current.nextNumber != null && findNumber > current.nextNumber.number)
	    {
		current = current.nextNumber;
	    }
	}
	return current;
    }


    /*-------------------------------------------------------------------
    Author: Eric Zhou
    Date: 12/11/2018
    Purpose: inserts a given name and number into the list (once to each list).
    Input: String givenName,Integer givenInt
    Output: None
    -------------------------------------------------------------------*/
    public void insert (String givenName, int givenInt)
    {
	Node add = new Node (givenName, givenInt);
	if (this.listName == null) //first Node inserted
	{
	    this.listName = add;
	}
	else
	{
	    Node prevName = this.findBefore (givenName);
	    if (prevName == null) //first
	    {
		Node store = this.listName;
		this.listName = add;
		add.nextName = store;
	    }
	    else
	    {
		Node nextName = prevName.nextName;
		prevName.nextName = add;
		add.nextName = nextName;
	    }
	}
	if (this.listNumber == null) // first Node inserted
	{
	    this.listNumber = add;
	}
	else
	{
	    Node prevNumber = this.findBefore (givenInt);
	    if (prevNumber == null) //first
	    {
		Node store = this.listNumber;
		this.listNumber = add;
		add.nextNumber = store;
	    }
	    else
	    {
		Node nextNumber = prevNumber.nextNumber;
		prevNumber.nextNumber = add;
		add.nextNumber = nextNumber;
	    }
	}
    }


    /*-------------------------------------------------------------------
    Author: Eric Zhou
    Date: 12/11/2018
    Purpose: return the reference to a Node with a given name in listName
    Input: String givenName
    Output: Node with givenName
    -------------------------------------------------------------------*/
    public Node find (String givenName)
    {
	givenName = givenName.toLowerCase ();
	Node prev = this.findBefore (givenName);
	Node findNode = new Node ();
	if (prev == null) //before first
	{
	    findNode = this.listName;
	    if (!(findNode.name.toLowerCase ().equals (givenName)))
	    {
		findNode = null;
	    }
	}
	else if (prev.nextName == null)
	{
	    findNode = null;
	}
	else if (!(prev.nextName.name.toLowerCase ().equals (givenName)))
	{
	    findNode = null;
	}
	else
	{
	    findNode = prev.nextName;
	}
	return findNode;
    }


    /*-------------------------------------------------------------------
    Author: Eric Zhou
    Date: 12/11/2018
    Purpose: return the reference to a Node with a given number in listNumber
    Input: Integer givenNumber
    Output: Node with givenNumber
    -------------------------------------------------------------------*/
    public Node find (int givenNumber)
    {
	Node prev = this.findBefore (givenNumber);
	Node findNode = new Node ();
	if (prev == null)
	{
	    findNode = this.listNumber;
	    if (findNode.number != givenNumber)
	    {
		findNode = null;
	    }
	}
	else if (prev.nextNumber == null)
	{
	    findNode = null;
	}
	else if (!(prev.nextNumber.number == givenNumber))
	{
	    findNode = null;
	}
	else
	{
	    findNode = prev.nextNumber;
	}
	return findNode;
    }


    /*-------------------------------------------------------------------
    Author: Eric Zhou
    Date: 12/11/2018
    Purpose: deletes a Node with a givenName from the listName.
    Input: String givenName
    Output: None
    -------------------------------------------------------------------*/
    public void delete (String givenName)
    {
	givenName = givenName.toString ();
	Node prev = this.findBefore (givenName);
	Node current, next;
	if (prev == null)
	{
	    current = this.listName;
	}
	else
	{
	    current = prev.nextName;
	}
	if (current != null) //if found
	{
	    if (prev == null) //first
	    {
		this.listName = current.nextName;
	    }
	    else
	    {
		next = current.nextName;
		prev.nextName = next;
	    }
	}

	Node prevNumber = this.findBefore (current.number);
	Node currentNumber;
	if (prevNumber == null)
	{
	    currentNumber = this.listNumber;
	}
	else
	{
	    currentNumber = prevNumber.nextNumber;
	}
	if (currentNumber != current)
	{
	    currentNumber = this.listNumber;
	    while (currentNumber != current)
	    {
		prevNumber = currentNumber;
		currentNumber = currentNumber.nextNumber;
	    }
	}
	if (prevNumber == null)
	{
	    this.listNumber = currentNumber.nextNumber;
	}
	else
	{
	    prevNumber.nextNumber = current.nextNumber;
	}
    }


    /*-------------------------------------------------------------------
    Author: Eric Zhou
    Date: 12/11/2018
    Purpose: deletes a Node with a givenNumber from the listNumber.
    Input: Integer givenNumber
    Output: None
    -------------------------------------------------------------------*/
    public void delete (int givenInt)
    {
	Node prev = this.findBefore (givenInt);

	Node current, next;
	if (prev == null)
	{
	    current = this.listNumber;
	}
	else
	{
	    current = prev.nextNumber;
	}
	if (current != null) //if found
	{
	    if (prev == null) //first
	    {
		this.listNumber = current.nextNumber;
	    }
	    else
	    {
		next = current.nextNumber;
		prev.nextNumber = next;
	    }
	}
	Node prevName = this.findBefore (givenInt);
	Node currentName;
	if (prevName == null)
	{
	    currentName = this.listName;
	}
	else
	{
	    currentName = prevName.nextName;
	}
	if (currentName != current)
	{
	    currentName = this.listName;
	    while (currentName != current)
	    {
		prevName = currentName;
		currentName = currentName.nextName;
	    }
	}
	if (prevName == null)
	{
	    this.listName = currentName.nextName;
	}
	else
	{
	    prevName.nextName = currentName.nextName;
	}
    }
}
