// The "SLLTest" class.
/*---------------------------------------------------
Author: Eric Zhou
Date: 12/5/2018
Purpose: To complete Single Linked Lists exercise.
Input: Keyboard
Output: Console
-----------------------------------------------------*/

import java.awt.*;
import hsa.Console;

public class SLLTest
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
	c = new Console (35, 125);
	boolean loop = true;
	while (loop == true)
	{
	    //random list
	    SinglyLinkedList randomList = new SinglyLinkedList ();
	    for (int i = 0 ; i < 10 ; i++)
	    {
		int num = (int) (Math.random () * 9) + 1;
		randomList.insert (num + "", num + "");
	    }
	    //user input list
	    SinglyLinkedList sll = new SinglyLinkedList ();
	    int userAction = -1;
	    boolean continuing = true;
	    while (continuing == true)
	    {
		c.println ();
		c.println ("Current List: " + sll.list);
		c.println ();
		do
		{
		    c.println ("Action: 1. Find, 2. Insert, 3. Delete, 4. printOdd, 5. selfOrganizingSearch, 6. Concatenate, 7. Exchange, 8. Reverse, 9. Exit");
		    c.print ("What do you want to do (1-9): ");
		    userAction = c.readInt ();
		    if (userAction < 1 || userAction > 9)
		    {
			c.println ("Must select from the action list.");
		    }
		}
		while (userAction < 1 || userAction > 9);
		if (userAction == 1) //find
		{
		    if (sll.isEmpty () == false)
		    {
			c.print ("What do you want to find: ");
			String findStr = c.readLine ();
			Node findNode = sll.find (findStr);
			if (findNode == null)
			{
			    c.println ("Found: " + sll.list.data);
			}
			else if (findNode != null && findNode.next != null)
			{
			    c.println ("Found: " + findNode.next.data);
			}
			else if (findNode != null && findNode.next == null)
			{
			    c.println ("The string could not be found in the list.");
			}
		    }
		    else
		    {
			c.println("There are no Nodes in the list.");
		    }

		}
		else if (userAction == 2) //insertleft
		{
		    c.print ("What do you want to insert: ");
		    String insertStr = c.readLine ();
		    c.print ("Where do you want to insert it before: ");
		    String insertBefore = c.readLine ();
		    sll.insert (insertStr, insertBefore);
		    c.println ("Successfully Inserted!");

		}
		else if (userAction == 3) //delete
		{
		    if (sll.isEmpty () == false)
		    {
			c.print ("What is the string you want to delete: ");
			String deleteStr = c.readLine ();
			boolean deleted = sll.delete (deleteStr);
			if (deleted == true)
			{
			    c.println ("The string has been successfully deleted!");
			}
			else
			{
			    c.println ("The string entered could not be deleted!");
			}
		    }
		    else
		    {
			c.println ("There are no Nodes in the list to delete.");
		    }
		}
		else if (userAction == 4) //printOdd
		{
		    if (sll.isEmpty () == false)
		    {
			c.print ("printOdd: ");
			sll.printOdd (c);
			c.println ();
		    }
		    else
		    {
			c.println ("There are no Nodes in the list to print!");
		    }
		}
		else if (userAction == 5) //selfOrganizingSearch
		{
		    if (sll.isEmpty () == false)
		    {
			c.print ("Which String would you like to move to the front: ");
			String frontStr = c.readLine ();
			Node frontNode = sll.selfOrganizingSearch (frontStr);
			if (frontNode != null)
			{
			    c.println ("Moved " + frontNode.data + " to the front!");
			}
			else
			{
			    c.println ("Could not find string!");
			}
		    }
		    else
		    {
			c.println ("There are no Nodes to move to the front!");
		    }
		}
		else if (userAction == 6)//Concatenate
		{
		    c.println ("Concatenating current list with random list.");
		    c.println ("Current List: " + sll.list);
		    c.println ("Random List: " + randomList);
		    SinglyLinkedList combined = sll.concatenate (randomList);
		    c.println ("Combined List: " + combined);
		}
		else if (userAction == 7)//Exchange
		{
		    if (sll.isEmpty () == false)
		    {
			c.print ("What is the string that you want to be exchanged: ");
			String exchangeStr = c.readLine ();
			c.print ("What do you want the first String to be exchanged with: ");
			String exchangeStr2 = c.readLine ();
			sll.exchange (exchangeStr, exchangeStr2);
			c.println ("The two strings will be exchanged if both exists!");
		    }
		    else
		    {
			c.println ("There are no Nodes to exchange with in the list!");
		    }


		}
		else if (userAction == 8)//Reverse
		{
		    if (sll.isEmpty () == false)
		    {
			c.println ("Now reversing the list..");
			sll.reverse ();
			c.println ("Reversed List: " + sll.list);
		    }
		    else
		    {
			c.println ("There are no Nodes in the list to reverse.");
		    }
		}
		else if (userAction == 9)
		{
		    continuing = false;
		}
	    } //continue loop

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
} // SLLTest class
/*---------------------------------------------------
Class: Node
Author: Eric Zhou
Date: 12/5/2018
Purpose: Holds a node.
Data Elements:
    data: a String value of the data.
    next: a reference pointer to the next node.
Methods:
    clone: will return a newly cloned object of itself.
    toString: returns all the data currently held in the linked list in a string.
-----------------------------------------------------*/
class Node implements Cloneable
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
    Purpose: Clones the current node
    Input: None
    Output: Newly cloned node object
    -----------------------------------------------------*/

    public Object clone () throws CloneNotSupportedException
    {
	Node newNode = (Node) super.clone (); //clones primitives
	if (this.next == null)
	{
	    newNode.next = null;
	}
	else
	{
	    newNode.next = (Node) next.clone ();
	}
	return newNode;
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
Class: SinglyLinkedList
Author: Eric Zhou
Date: 12/5/2018
Purpose: To create a Singly Linked List of Nodes
Data Elements:
    list: contains a Node reference to all the Nodes in the list.
Methods:
    isEmpty: sees if the lsit is empty.
    clone: clones the primitives and the list (if not null).
    toString: returns data in the list.
    find: returns the node before the node containing a given String (returns last node if not found; null if first node).
    insert: inserts a given String before a given second String. (If second string not found, insert at end.)
    delete: deletes a Node from the list given a String. Boolean statement indicating if Node was found/deleted or not.
    printOdd: create a string with the odd (1st, 3rd, 5th,..) Node data.
    selfOrganizingSearch: return Node (or null if not found), given a String. If Node was found, should be moved to beginning of the list.
    concatenate: attaches a given list onto the end of the current list creating a third list. (Take caution for null lists).
    exchange: exchanges two Nodes in the list, given Two Strings.
    reverse: reverses the complete list.
-----------------------------------------------------*/
class SinglyLinkedList implements Cloneable
{
    protected Node list;

    public SinglyLinkedList ()
    {
	this.list = null;
    }


    public SinglyLinkedList (Node getList)
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
    Purpose: Clones the current list and returns a cloned version
    Input: None
    Output: Cloned list
    ----------------------------------------*/
    public Object clone () throws CloneNotSupportedException
    {
	SinglyLinkedList sll = (SinglyLinkedList) super.clone ();
	if (this.list != null)
	{
	    sll.list = (Node) this.list.clone ();
	}
	else
	{
	    sll.list = null;
	}
	return sll;
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
    Date: 12/5/2018
    Purpose: Sees if a String is within the list.
    Input: String (info of the string to be found)
    Output: The Node before it.
    ----------------------------------------*/
    public Node find (String info)
    {
	info = info.toLowerCase();
	Node current = this.list;
	if (this.isEmpty () == true || current.data.toLowerCase().equals (info))
	{
	    current = null;
	}
	else
	{
	    boolean findBefore = false;
	    while (current.next != null && findBefore == false)
	    {
		if (current.next.data.toLowerCase().equals (info))
		{
		    findBefore = true;
		}
		else
		{
		    current = current.next;
		}
	    }
	}
	return current;
    }


    /*---------------------------------------
    Author: Eric Zhou
    Date: 12/5/2018
    Purpose: inserts a given String before a given second String.
    Input: the String's content (info), the String where to insert it before.
    Output: None
    ----------------------------------------*/
    public void insert (String info, String info2)
    {
	Node newNode = new Node (info);
	Node current = this.find (info2);
	if (current == null)
	{
	    newNode.next = this.list;
	    this.list = newNode;
	}
	else
	{
	    newNode.next = current.next; //to not lose the rest of list
	    current.next = newNode;
	}
    }


    /*---------------------------------------
    Author: Eric Zhou
    Date: 12/5/2018
    Purpose: deletes a Node from the list given a String. Boolean statement indicating if Node was found/deleted or not.
    Input: String to be deleted
    Output: Boolean statement if deleted or not.
    ----------------------------------------*/
    public boolean delete (String info)
    {
	boolean deleted = false;
	Node prev = this.find (info), current;
	if (prev == null)
	{
	    current = this.list;
	}
	else
	{
	    current = prev.next;
	}
	if (current != null) // if found
	{
	    deleted = true;
	    if (prev == null) //if a is first on list
	    {
		this.list = current.next;
	    }
	    else
	    {
		Node next = current.next;
		prev.next = next;
	    }
	}

	return deleted;
    }


    /*---------------------------------------
    Author: Eric Zhou
    Date: 12/5/2018
    Purpose: create a string with the odd (1st, 3rd, 5th,..) Node data.
    Input: Console c
    Output: None
    ----------------------------------------*/
    public void printOdd (Console c)
    {
	Node current = this.list;
	int count = 1;
	boolean endOfList = false;
	while (endOfList == false)
	{
	    if (count % 2 != 0)
	    {
		c.print (current.data + " ");
	    }
	    if (current.next == null)
	    {
		endOfList = true;
	    }
	    else
	    {
		current = current.next;
	    }
	    count++;
	}
    }


    /*---------------------------------------
    Author: Eric Zhou
    Date: 12/5/2018
    Purpose: return Node (or null if not found), given a String. If Node was found, should be moved to beginning of the list.
    Input: String (info)
    Output: Node of the string
    ----------------------------------------*/
    public Node selfOrganizingSearch (String info)
    {

	Node findNode = this.find (info); //prev
	Node infoNode = null;
	if (findNode != null && findNode.next != null && findNode.next.data.toLowerCase().equals (info))
	{
	    infoNode = findNode.next; //actual info ref stored
	    findNode.next = findNode.next.next; //changing the next ref pointers
	    infoNode.next = findNode;
	    infoNode.next = this.list; //setting ref pointer of the current to the list.
	    this.list = infoNode;
	}
	if (findNode == null)
	{
	    infoNode = this.list;
	}

	return infoNode;
    }


    /*---------------------------------------
    Author: Eric Zhou
    Date: 12/5/2018
    Purpose: attaches a given list onto the end of the current list creating a third list.
    Input: Node otherList
    Output: Combined SinglyLinkedList list
    ----------------------------------------*/
    public SinglyLinkedList concatenate (SinglyLinkedList otherList)
    {
	SinglyLinkedList combinedList = new SinglyLinkedList ();
	if (this.isEmpty () == false)
	{
	    try
	    {
		combinedList = (SinglyLinkedList) this.clone ();

	    }
	    catch (CloneNotSupportedException ex)
	    {
		System.out.println ("Cloning Error!");
	    }

	    Node current = combinedList.list;
	    while (current.next != null)
	    {
		current = current.next;
	    }
	    current.next = otherList.list;
	}
	else //if empty or one is empty
	{
	    combinedList.list = otherList.list;
	}
	return combinedList;
    }


    /*---------------------------------------
    Author: Eric Zhou
    Date: 12/6/2018
    Purpose: exchanges two Nodes in the list, given Two Strings.
    Input: The two strings user wants to switch
    Output: None
    ----------------------------------------*/
    public void exchange (String a, String b)
    {
	if (!(a.equals (b))) //switch if not same
	{
	    //finding prev loc of a, and its current depending on prev.
	    Node prevA = this.find (a);
	    Node currA;
	    if (prevA == null)
	    {
		currA = this.list;
	    }
	    else
	    {
		currA = prevA.next;
	    }

	    Node prevB = this.find (b), currB;
	    if (prevB == null)
	    {
		currB = this.list;
	    }
	    else
	    {
		currB = prevB.next;
	    }

	    if (currA != null && currB != null) //both must be found to exchange
	    {
		//swapping pointers
		if (prevA != null)
		{
		    prevA.next = currB;
		}
		else //if a is first on list
		{
		    this.list = currB;
		}

		if (prevB != null)
		{
		    prevB.next = currA;
		}
		else
		{
		    this.list = currA;
		}
		Node temp = currA.next;
		currA.next = currB.next;
		currB.next = temp;
	    }
	}
    }


    /*---------------------------------------
    Author: Eric Zhou
    Date: 12/6/2018
    Purpose: reverses the complete list.
    Input: None
    Output: None
    ----------------------------------------*/
    public void reverse ()
    {
	Node first = this.list; //the first of reverse which will be 5 in the ex, last
	Node prevNode = null;
	Node current = this.list;
	Node nextNode = null;
	while (current != null)
	{
	    nextNode = current.next; 
	    current.next = prevNode; //changing the current ref pointer to its previous node instead of its next.
	    //changing prev to current to move onto next node.
	    prevNode = current;
	    current = nextNode;
	}
	first = prevNode; //last becomes first in linked list
	this.list = first;

    }
}
