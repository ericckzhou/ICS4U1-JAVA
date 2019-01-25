// The "CDLLTest" class.
//----------------------------------------------------------------------//
//Author: Eric Zhou
//Date: 12/7/2018
//Purpose: To create a Node and CircularList class with a header node.
//Input: Keyboard
//Output: Console
//----------------------------------------------------------------------//
import java.awt.*;
import hsa.Console;
import java.util.*;

public class CDLLTest
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
	c = new Console (30, 125);
	boolean loop = true;
	while (loop == true)
	{

	    int size = -1;
	    do //size
	    {
		c.print ("What size would you like for your circular double linked list (1-20): ");
		size = c.readInt ();
		if (size < 1 || size > 20)
		{
		    c.println ("The size must be within the range of 1-20!");
		}
	    }
	    while (size < 1 || size > 20);
	    CircularList cl = new CircularList (size);
	    c.println ("Your list has been randomized with " + size + " Nodes!");
	    c.println ();
	    boolean continuing = true;
	    int action = -1;
	    do
	    {
		c.println ("Action(s): 1. numNodes, 2. insertLeft, 3. delete, 4. toStringLeft, 5. toStringRight, 6. splitInTwo, 7. exit");
		c.print ("What would you like to do: ");
		action = c.readInt ();
		if (action == 1) //print num of nodes
		{
		    c.println ("There are " + cl.numNodes () + " Nodes in the circular double linked list!");
		}
		else if (action == 2) //insert
		{
		    int insertNum = -1;
		    int before = - 1;
		    do
		    {
			c.print ("What number would you like to insert: ");
			insertNum = c.readInt ();
			c.print ("Where do you want to insert it before: ");
			before = c.readInt ();
			if (insertNum < 0 || before < 0)
			{
			    c.println ("Both numbers must be positive.");
			}
		    }
		    while (insertNum < 0 || before < 0);
		    cl.insertLeft (insertNum, before);
		    c.println ("Inserted: " + insertNum + " to the list.");
		}
		else if (action == 3) //delete
		{
		    if (cl.header.data > 0)
		    {
			int deleteNum = -1;
			do
			{
			    c.print ("What number would you like to delete: ");
			    deleteNum = c.readInt ();
			    if (deleteNum < 0)
			    {
				c.println ("The number must be positive.");
			    }
			}
			while (deleteNum < 0);
			boolean deleted = cl.delete (deleteNum);
			if (deleted == true)
			{
			    c.println ("The Node with the data of " + deleteNum + " has been deleted.");
			}
			else
			{
			    c.println ("The Node with the data of " + deleteNum + " could not be deleted.");
			}
		    }
		    else
		    {
			c.println ("The list is empty, thus cannot delete.");
		    }
		}
		else if (action == 4) //print left
		{
		    c.clear ();
		    c.println ("toStringLeft: " + cl.toStringLeft () + " [" + cl.header.data + "]");
		}
		else if (action == 5) //print right
		{
		    c.clear ();
		    c.println ("toStringRight: " + cl.toStringRight () + " [" + cl.header.data + "]" );
		}
		else if (action == 6) //splitIntoTwo
		{
		    if (cl.header.data % 2 == 0 && cl.header.data > 0)
		    {
			CircularList secondHalf = cl.splitInTwo ();
			c.print ("First Half: " + cl.toStringLeft ());
			c.println (", Second Half: " + secondHalf.toStringLeft ());
		    }
		    else if (cl.header.data % 2 != 0 && cl.header.data > 0)
		    {
			c.println ("*Could not split into two due to size not even!*");
		    }
		    else
		    {
			c.println ("*There are no nodes in the list to split!*");
		    }
		}
		else if (action == 7) //exit
		{
		    continuing = false;
		}
		if (action < 1 || action > 7)
		{
		    c.println ("Please choose an action within the range (1-7): ");
		}
		c.println ();
	    }
	    while (action != 7);
	    //rerun
	    c.println ();
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
} // CDLLTest class
/*---------------------------------------------------
Author: Eric Zhou
Date: 12/7/2018
Purpose: Holds a node.
Data Elements:
    data: a Integer value.
    prev: a reference pointer to the previous node.
    next: a reference pointer to the next node.
Methods:
    None
-----------------------------------------------------*/
class Node
{
    public int data;
    public Node prev;
    public Node next;

    public Node (int val)  //constructor
    {
	this.data = val;
	this.prev = null;
	this.next = null;
    }


    public Node ()  //constructor
    {
	this.data = 0;
	this.prev = null;
	this.next = null;
    }
}
/*---------------------------------------------------
Author: Eric Zhou
Date: 12/7/2018
Purpose: Holds a Circular list of Nodes
Data Elements:
    header: contains the number of nodes in the list as well as the ref to the list of Nodes.
Methods:
    numNodes: return the value of header back.
    insertLeft: inserts a Node given a value to the left of another given value.
    If other Node is not found, insert to left of header's node.
    delete: deletes a Node given a value.
    toStringLeft: creates a String of the list values, going left.
    toStringRight: creates a String of the list values going right.
    splitInTwo: split the current list, provided it has a even # of Nodes into two list.
    current list loses half of its Nodes and return ref of other half.
-----------------------------------------------------*/
class CircularList
{
    public Node header;

    public CircularList (int val)
    {
	if (val >= 0)
	{
	    this.header = new Node (val);
	    this.header.prev = this.header;
	    this.header.next = this.header;
	    if (val > 0)
	    {
		int randNum = (int) (Math.random () * 8) + 1;
		Node firstNode = new Node (randNum);
		this.header.next = firstNode;
		Node prevNode = firstNode;
		for (int i = 1 ; i < val ; i++)
		{
		    randNum = (int) (Math.random () * 8) + 1;
		    Node rand = new Node (randNum);
		    prevNode.next = rand;
		    rand.prev = prevNode;
		    prevNode = rand;
		}
		firstNode.prev = prevNode;
		prevNode.next = firstNode;

	    }
	}
    }


    public CircularList ()
    {
	new CircularList (0);
    }


    /*---------------------------------------------------
    Author: Eric Zhou
    Date: 12/7/2018
    Purpose: Returns the number of Nodes in list.
    Input: None
    Output: Number of Nodes
    -----------------------------------------------------*/
    public int numNodes ()
    {
	return this.header.data;
    }


    /*---------------------------------------------------
    Author: Eric Zhou
    Date: 12/7/2018
    Purpose: inserts a Node given a value to the left of another given value.
    Input: Value to add, the data to be inserted before. (two integers)
    Output: None
    -----------------------------------------------------*/
    public void insertLeft (int val, int before)
    {
	Node userNode = new Node (val);
	Node find = this.header.next;
	boolean found = false;
	for (int i = 0 ; i < this.header.data && found == false ; i++)
	{
	    if (find.data == before)
	    {
		found = true;
	    }
	    else
	    {
		find = find.next;
	    }
	}
	//find = before data
	Node prevNode = find.prev;
	userNode.next = find;
	prevNode.next = userNode;
	userNode.prev = prevNode;
	find.prev = userNode;
	if (found == false || this.header.next.data == find.data)
	{
	    this.header.next = userNode;
	}
	this.header.data++;
    }


    /*---------------------------------------------------
    Author: Eric Zhou
    Date: 12/7/2018
    Purpose: deletes a Node given a value.
    Input: value to delete (integer)
    Output: boolean statement if deleted or not.
    -----------------------------------------------------*/
    public boolean delete (int val)
    {
	//finding
	Node current = this.header.next;
	boolean foundNode = false;
	boolean deleted = false;
	int count = 1;
	//does not run if first
	while (current.data > 0 && current.data != val && count != this.header.data && foundNode == false)
	{
	    if (current.data == val)
	    {
		foundNode = true;
	    }
	    else
	    {
		current = current.next;
		count++;
	    }
	}
	if (current.data == val) //first
	{
	    foundNode = true;
	}
	if (foundNode == true)
	{
	    Node prevNode = current.prev;
	    Node nextNode = current.next;
	    prevNode.next = nextNode;
	    nextNode.prev = prevNode;
	    if (this.header.next.data == val)
	    {
		this.header.next = nextNode;
	    }
	    this.header.data--;
	    deleted = true;
	}

	return deleted;

    }


    /*---------------------------------------------------
    Author: Eric Zhou
    Date: 12/7/2018
    Purpose: creates a String of the list values, going left.
    Input: None
    Output: String of the list values to the left.
    -----------------------------------------------------*/
    public String toStringLeft ()
    {
	String s = "( ";
	if (this.header.data != 0)
	{
	    int numOfNodes = this.numNodes ();
	    Node current = this.header.next;
	    for (int i = 0 ; i < numOfNodes ; i++)
	    {
		s += current.data + " ";
		current = current.next;
	    }
	}
	else
	{
	    s += "EMPTY ";
	}
	s += ")";
	return s;
    }


    /*---------------------------------------------------
    Author: Eric Zhou
    Date: 12/7/2018
    Purpose: creates a String of the list values, going right.
    Input: None
    Output: String of the list values to the right
    -----------------------------------------------------*/
    public String toStringRight ()
    {
	String left = this.toStringLeft ();
	StringTokenizer t = new StringTokenizer (left);
	StringBuffer s = new StringBuffer ();
	while (t.hasMoreTokens ())
	{
	    String str = t.nextToken ();
	    if (str.equals ("("))
	    {
		str = ")";
	    }
	    else if (str.equals (")"))
	    {
		str = "(";
	    }
	    s.insert (0, str + " ");
	}
	return s.toString ();
    }


    /*---------------------------------------------------
    Author: Eric Zhou
    Date: 12/7/2018
    Purpose: split the current list, provided it has a even # of Nodes into two list.
    Input: None
    Output: Other half of CircularList
    -----------------------------------------------------*/
    public CircularList splitInTwo ()
    {
	//main checks if size is even
	CircularList cl;
	//first half
	cl = new CircularList (this.header.data / 2);
	int count = 0;
	Node first = this.header.next;
	Node current = first;
	while (count != this.header.data / 2)
	{
	    current = current.next;
	    count++;
	}
	Node firstHalfLast = current.prev;
	firstHalfLast.next = first;
	first.prev = firstHalfLast;
	this.header.data /= 2;

	//second half
	Node otherFirst = current;
	cl.header.next = otherFirst;
	count = 0;
	while (count != cl.header.data)
	{
	    Node prev = current;
	    current = current.next;
	    prev.next = current;
	    current.prev = prev;
	    count++;
	}
	current.next = otherFirst;
	otherFirst.prev = current;

	return cl;
    }
}
