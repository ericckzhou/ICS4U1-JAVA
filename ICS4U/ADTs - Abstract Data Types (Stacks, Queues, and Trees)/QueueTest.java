// The "QueueTest" class.
// ------------------------------------
// Author: Eric Zhou
// Date: 01/03/2019
// Purpose: To complete queue exercise.
// Input: Keyboard
// Output: Console
// ------------------------------------
import java.awt.*;
import hsa.Console;

public class QueueTest
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
	c = new Console (35, 125);
	boolean loop = true;
	while (loop == true)
	{
	    int size = -1;
	    do
	    {
		c.print ("What initial size would you like the queue to be (array only): ");
		size = c.readInt ();
		if (size < 0 || size > 30)
		{
		    c.println ("The initial size of the queue must be within the range 1 - 30.");
		}
		c.println ();
	    }
	    while (size < 0 || size > 30);
	    Queue[] queueList = new Queue [4];
	    queueList [0] = new SLineArr (size);
	    queueList [1] = new ShiftRArr (size);
	    queueList [2] = new ShiftFArr (size);
	    queueList [3] = new CircleArr (size);
	    LLQueue llq = new LLQueue ();
	    int queueSelect = -1;
	    boolean changeArr = true;
	    while (queueSelect != 6)
	    {
		c.clear ();
		do
		{
		    c.println ("Queues: 1. Straight-Line Array, 2. Shifted Array (when removed), 3. Shifted Array (when full)");
		    c.println ("        4. Circular Array, 5. Linked List, 6. Exit");
		    c.print ("Which queue would you like to change: ");
		    queueSelect = c.readInt ();
		    if (queueSelect < 0 || queueSelect > 6)
		    {
			c.println ("Number chosen must be within 1-6.");
		    }

		}
		while (queueSelect < 0 || queueSelect > 6);
		c.println ();
		int action = -1;
		boolean added = false, removed = false;
		Queue queue = null;
		if (queueSelect == 1)
		{
		    queue = queueList [0];
		}
		else if (queueSelect == 2)
		{
		    queue = queueList [1];
		}
		else if (queueSelect == 3)
		{
		    queue = queueList [2];
		}
		else if (queueSelect == 4)
		{
		    queue = queueList [3];
		}
		if (queueSelect < 5)
		{
		    while (action != 5)
		    {
			if (queueSelect == 1)
			{
			    c.println ("Straight-Line Array Queue");
			}
			else if (queueSelect == 2)
			{
			    c.println ("Shifted Array Queue (shift when remove)");
			}
			else if (queueSelect == 3)
			{
			    c.println ("Shifted Array Queue (shift when full)");
			}
			else if (queueSelect == 4)
			{
			    c.println ("Circular Array Queue");
			}

			c.println (queue + "");
			c.println ();
			do
			{
			    c.println ("1.Add, 2. Remove, 3. Reset, 4. Expand, 5. Exit");
			    c.print ("Action: What would you like to do: ");
			    action = c.readInt ();
			    if (action < 0 || action > 5)
			    {
				c.println (" * Action must be within the range 1-5.");
			    }
			}
			while (action < 0 || action > 5);
			if (action == 1)
			{
			    added = queue.add ();
			    if (added == false)
			    {
				c.println (" * Fail to add due to full queue.");
			    }
			    else
			    {
				c.println (" * Successfully added.");
			    }
			}
			else if (action == 2)
			{
			    removed = queue.remove ();
			    if (removed == false)
			    {
				c.println (" * Fail to remove due to empty queue.");
			    }
			    else
			    {
				c.println (" * Successfully removed.");
			    }
			}
			else if (action == 3)
			{
			    queue.reset ();
			    c.println (" * Queue successfully resetted!");
			}
			else if (action == 4)
			{
			    int expand = -1;
			    do
			    {
				c.print ("How much would you like to expand the queue by (1-10): ");
				expand = c.readInt ();
			    }
			    while (expand < 0 || expand > 10);
			    queue.expand (expand);
			    c.print (" * Successfully expanded the queue!");
			}
			c.println ();
		    }
		}
		if (queueSelect == 5)
		{
		    while (action != 4)
		    {
			c.println ("Linked List Queue");
			c.println (llq + "");
			c.println ();
			do
			{
			    c.println ("1.Add, 2. Remove, 3. Reset, 4. Exit");
			    c.print ("Action: What would you like to do: ");
			    action = c.readInt ();
			    if (action < 0 || action > 4)
			    {
				c.println (" * Action must be within the range 1-4.");
			    }
			}
			while (action < 0 || action > 4);
			if (action == 1)
			{
			    llq.add ();
			    c.println ("Successfully added to the linked list queue.");
			}
			else if (action == 2)
			{
			    removed = llq.remove ();
			    if (removed == false)
			    {
				c.println ("The linked list queue is empty.");
			    }
			    else
			    {
				c.println ("Successfully removed the first in the linked list queue!");
			    }
			}
			else if (action == 3)
			{
			    llq.reset ();
			    c.println ("Successfully resetted the linked list queue.");
			}
			c.println ();
		    }
		}
	    }

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
} // QueueTest class
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
/*--------------------------------------------------------/
Class: Queue
Author: Eric Zhou
Date: 01/03/2019
Purpose: The parent of all queue implementations.
Data Elements:
    size: the size of the queue determined by user. (changeable)
    arr: the integer array
    front: the beginning of the queue
    back: the end of the queue
Methods:
    add: adds a integer to the queue.
    remove: removes a integer from the queue.
    reset: resets the queue.
    toString: returns the queue in string. (show queue)
    isFull: Sees if array is full.
    isEmpty: Sees if array is empty.
    expand: Expands the current array size.
/--------------------------------------------------------*/
abstract class Queue
{
    protected int number;
    public int size;
    public int[] arr;
    public int front;
    public int back;

    public Queue (int getSize)
    {
	this.number = 1;
	this.size = getSize;
	this.arr = new int [this.size];
	this.front = 0;
	this.back = 0;
    }


    public Queue ()
    {
	this (0);
    }


    /*------------------------------------
    Author: Eric Zhou
    Date: 01/03/2019
    Purpose: Adds an integer to the queue.
    Input: None
    Output: boolean statement if added or not.
    -------------------------------------*/
    abstract public boolean add ();
    /*------------------------------------
    Author: Eric Zhou
    Date: 01/03/2019
    Purpose: Removes the first integer from the queue.
    Input: None
    Output: Boolean statement if deleted or not.
    -------------------------------------*/
    abstract public boolean remove ();
    /*------------------------------------
    Author: Eric Zhou
    Date: 01/03/2019
    Purpose: Resets the queue.
    Input: None
    Output: None
    -------------------------------------*/
    abstract public void reset ();
    /*------------------------------------
    Author: Eric Zhou
    Date: 01/03/2019
    Purpose: Returns the queue elements in a string. Also shows front and back.
    Input: None
    Output: The queue in string.
    -------------------------------------*/
    abstract public String toString ();
    /*------------------------------------
    Author: Eric Zhou
    Date: 01/07/2019
    Purpose: Sees if the array is full.
    Input: None
    Output: A boolean statement.
    -------------------------------------*/
    abstract public boolean isFull ();
    /*------------------------------------
    Author: Eric Zhou
    Date: 01/07/2019
    Purpose: Sees if the array is empty.
    Input: None
    Output: A boolean statement.
    -------------------------------------*/
    abstract public boolean isEmpty ();
    /*------------------------------------
    Author: Eric Zhou
    Date: 01/08/2019
    Purpose: Expands the current array.
    Input: A integer to expand by
    Output: None
    -------------------------------------*/
    abstract public void expand (int val);

}
/*----------------------------------------------------------------------------
Class: SLineArr (Straight Line Array Implemenation)
Author: Eric Zhou
Date: 01/07/2019
Purpose: To implement queue with SL Array.
Data Elements:
    Data Fields from parent class, Queue.
Methods:
    add: adds an integer to the queue SL Array.
    remove: removes an integer from the SL Array queue.
    reset: resets the queue
    toString: prints the array queue.
    isFull: sees if array is full.
    isEmpty: sees if array is empty.
    expand: Expands the current array size.
----------------------------------------------------------------------------*/
class SLineArr extends Queue
{
    public SLineArr (int getSize)
    {
	super (getSize);
    }


    public SLineArr ()
    {
	this (0);
    }


    /*--------------------------------------------------------
    Author: Eric Zhou
    Date: 01/07/2019
    Purpose: Adds an integer to the straight line array queue.
    Input: None
    Output: Boolean statement if added or not.
    ---------------------------------------------------------*/
    public boolean add ()  //check if full
    {
	boolean added = false;
	if (this.isFull () == false)
	{
	    added = true;
	    this.arr [this.back] = this.number;
	    this.back++;
	    this.number++;
	}
	return added;
    }


    /*--------------------------------------------------------
    Author: Eric Zhou
    Date: 01/07/2019
    Purpose: Removes an integer from the straight line array queue.
    Input: An integer to remove.
    Output: Boolean statement if removed or not.
    ---------------------------------------------------------*/
    public boolean remove ()  //check if empty
    {
	boolean deleted = false;
	if (this.isEmpty () == false)
	{
	    deleted = true;
	    this.arr [this.front] = 0;
	    this.front++;
	}
	return deleted;
    }


    /*--------------------------------------------------------
    Author: Eric Zhou
    Date: 01/07/2019
    Purpose: Resets the queue.
    Input: None
    Output: None
    ---------------------------------------------------------*/
    public void reset ()
    {
	this.arr = new int [this.size];
	this.front = 0;
	this.back = 0;
	this.number = 1;
    }


    /*--------------------------------------------------------
    Author: Eric Zhou
    Date: 01/07/2019
    Purpose: Returns the queue.
    Input: None
    Output: A string of the queue.
    ---------------------------------------------------------*/
    public String toString ()
    {
	String s = "";
	if (this.isEmpty () == true)
	{
	    s += "Empty";
	}
	else
	{
	    s += "F = " + this.front + " | ";
	    for (int i = 0 ; i < this.size ; i++)
	    {
		if (this.arr [i] > 0)
		{
		    s += this.arr [i] + " ";
		}
		else
		{
		    s += "- ";
		}
	    }
	    s += "| B = " + this.back;
	}
	return s;
    }


    /*--------------------------------------------------------
    Author: Eric Zhou
    Date: 01/07/2019
    Purpose: Sees if the array is full.
    Input: None
    Output: Boolean statement if full or not.
    ---------------------------------------------------------*/
    public boolean isFull ()
    {
	return this.back == this.size;
    }


    /*--------------------------------------------------------
    Author: Eric Zhou
    Date: 01/07/2019
    Purpose: Sees if the array is empty.
    Input: None
    Output: Boolean statement if empty or not.
    ---------------------------------------------------------*/
    public boolean isEmpty ()
    {
	return this.front == this.back;
    }


    /*--------------------------------------------------------
    Author: Eric Zhou
    Date: 01/08/2019
    Purpose: Expands the current array size.
    Input: A integer value to expand by.
    Output: None
    ---------------------------------------------------------*/
    public void expand (int val)
    {
	int oldSize = this.size;
	this.size += val;
	int[] temp = this.arr;
	this.arr = new int [this.size];
	for (int i = 0 ; i < oldSize ; i++)
	{
	    this.arr [i] = temp [i];
	}
    }
}
/*--------------------------------------------------------------------
Class: ShiftRArr(Shifted Array when removed)
Author: Eric Zhou
Date: 01/08/2019
Purpose: To implement queue in a shifted array.
Data Elements:
Methods:
    remove: removes a element from a queue array and shifts the array.
--------------------------------------------------------------------*/
class ShiftRArr extends SLineArr
{
    public ShiftRArr (int getSize)
    {
	super (getSize);
    }


    public ShiftRArr ()
    {
	this (0);
    }


    /*--------------------------------------------------------
    Author: Eric Zhou
    Date: 01/08/2019
    Purpose: Removes a integer from the queue. (FIFO)
    Input: None
    Output: A boolean statement if removed or not.
    ---------------------------------------------------------*/
    public boolean remove ()
    {
	boolean deleted = false;
	if (this.isEmpty () == false)
	{
	    deleted = true;
	    for (int i = 0 ; i < this.size - 1 ; i++)
	    {
		this.arr [i] = this.arr [i + 1];
	    }
	    this.arr [this.back - 1] = 0;
	    this.back--;
	}
	return deleted;
    }
}
/*--------------------------------------------------------------------
Class: ShiftFArr(Shifted Array when full)
Author: Eric Zhou
Date: 01/08/2019
Purpose: To implement queue in a shifted array.
Data Elements:
Methods:
    add: adds an integer to the shifted array queue. shift when full.
--------------------------------------------------------------------*/
class ShiftFArr extends SLineArr
{
    public ShiftFArr (int getSize)
    {
	super (getSize);
    }


    public ShiftFArr ()
    {
	this (0);
    }


    /*--------------------------------------------------------
    Author: Eric Zhou
    Date: 01/08/2019
    Purpose: Adds an integer to the shifted array queue.
    Input: None
    Output: Boolean statement if added or not.
    ---------------------------------------------------------*/
    public boolean add ()  //check if full
    {
	boolean added = false;
	if (this.isFull () == true)
	{
	    int[] temp = new int [this.size];
	    int index = 0;
	    for (int i = 0 ; i < this.size ; i++)
	    {
		if (this.arr [i] > 0)
		{
		    temp [index] = this.arr [i];
		    index++;
		}
	    }
	    this.arr = temp;
	    this.front = 0;
	    this.back = index;
	}

	if (this.isFull () == false)
	{
	    added = true;
	    this.arr [this.back] = this.number;
	    this.back++;
	    this.number++;
	}
	return added;
    }
}
/*------------------------------------------
Class: CircleArr (Circular Array)
Author: Eric Zhou
Date: 01/08/2019
Purpose: Sees if the queue array is empty.
Data Elements:
Methods:
    add: adds an integer to the queue array and also checks for boundaries.
    remove: removes an integer from the queue array and also checks for boundaries.
    reset: resets the queue.
    isFull: sees if the circular array is full
-------------------------------------------*/
class CircleArr extends SLineArr
{
    public CircleArr (int getSize)
    {
	super (getSize + 1);
	this.front = 1;
	this.back = 1;
    }


    public CircleArr ()
    {
	this (1);
    }


    /*--------------------------------------------------------
    Author: Eric Zhou
    Date: 01/08/2019
    Purpose: Adds an integer to the circular queue.
    Input: None
    Output: Boolean statement if added or not.
    ---------------------------------------------------------*/
    public boolean add ()
    {
	boolean added = false;
	if (this.isFull () == false)
	{
	    added = true;
	    this.arr [this.back] = this.number;
	    this.back++;
	    if (this.back >= this.size)
	    {
		this.back = 0;
	    }
	    this.number++;
	}
	return added;
    }


    /*--------------------------------------------------------
    Author: Eric Zhou
    Date: 01/08/2019
    Purpose: Removes an integer from the circular queue.
    Input: None
    Output: Boolean statement if removed or not.
    ---------------------------------------------------------*/
    public boolean remove ()
    {
	boolean removed = false;
	if (this.isEmpty () == false)
	{
	    removed = true;
	    this.arr [this.front] = 0;
	    this.front++;
	    if (this.front >= this.size)
	    {
		this.front = 0;
	    }
	}
	return removed;
    }


    /*--------------------------------------------------------
    Author: Eric Zhou
    Date: 01/08/2019
    Purpose: Resets the circular queue.
    Input: None
    Output: None
    ---------------------------------------------------------*/
    public void reset ()
    {
	this.arr = new int [this.size];
	this.front = 1;
	this.back = 1;
	this.number = 1;
    }


    /*--------------------------------------------------------
    Author: Eric Zhou
    Date: 01/08/2019
    Purpose: Sees if the circular queue is full.
    Input: None
    Output: Boolean statement if full or not.
    ---------------------------------------------------------*/
    public boolean isFull ()
    {
	return (this.back + 1 == this.front);

    }


    /*--------------------------------------------------------
    Author: Eric Zhou
    Date: 01/086/2019
    Purpose: Returns the circular queue.
    Input: None
    Output: A string of the queue.
    ---------------------------------------------------------*/
    public String toString ()
    {
	String s = "";
	if (this.isEmpty () == true)
	{
	    s += "Empty";
	}
	else
	{
	    s += "F = " + this.front + " | ";
	    for (int i = 0 ; i < this.size ; i++)
	    {
		if (i == this.front - 1) // the / is for knowing if the circular array queue is full or not!
		{
		    s += "/ ";
		}
		else if (this.arr [i] > 0)
		{
		    s += this.arr [i] + " ";
		}
		else
		{
		    s += "- ";
		}
	    }
	    s += "| B = " + this.back;
	}
	return s;
    }
}
/*----------------------------------------------------------------------------
Class: LLQueue (Linked List Queue)
Author: Eric Zhou
Date: 1/7/2019
Purpose: To create a linked list queue class. (Circular doubly)
Data Elements:
    number: a starter number that starts at 1;
    header: a header node which contains the number of Nodes in the list.
Methods:
    add: adds a integer to the queue linked list.
    remove: removes a integer from the queue linked list.
    reset: resets the queue linked list.
    toString: returns the queue linked list in string. (show queue)
-----------------------------------------------------------------------------*/
class LLQueue
{
    public int number;
    public Node header;
    public Node front;
    public Node back;

    public LLQueue (int getSize)
    {
	this.header = new Node ();
	this.header.prev = this.header;
	this.header.next = null;
	this.header.data = 0;
	this.number = 1;
	this.front = null;
	this.back = null;
    }


    public LLQueue ()
    {
	this (0);
    }


    /*--------------------------------------------------
    Author: Eric Zhou
    Date: 1/7/2019
    Purpose: adds a integer to the queue linked list.
    Input: None
    Output: None
    ---------------------------------------------------*/
    public void add ()
    {
	Node addNode = new Node (this.number); //if not full
	if (this.header.next != null)
	{
	    Node current = this.header.next;
	    Node prev = current.prev;
	    addNode.prev = prev;
	    addNode.next = current;
	    current.prev = addNode;
	    prev.next = addNode;
	    this.back = addNode;
	}
	else
	{
	    this.header.next = addNode;
	    this.front = addNode;
	    this.back = addNode;
	    addNode.prev = addNode;
	    addNode.next = addNode;
	}
	this.header.data++;
	this.number++;
    }


    /*---------------------------------------------------
    Author: Eric Zhou
    Date: 1/7/2019
    Purpose: removes a integer from the queue linked list.
    Input: None
    Output: boolean statement if empty or not.
    ---------------------------------------------------*/
    public boolean remove ()
    {
	boolean removed = false;
	if (this.header.data > 0) //if not empty
	{
	    removed = true;
	    Node current = this.header.next;
	    Node prev = current.prev;
	    this.header.next = current.next;
	    prev.next = this.header.next;
	    this.header.next.prev = prev;
	    this.header.data--;
	    this.back = this.header.next.prev;
	}
	return removed;

    }


    /*---------------------------------------------------
    Author: Eric Zhou
    Date: 1/7/2019
    Purpose: resets the queue linked list.
    Input: None
    Output: None
    ---------------------------------------------------*/
    public void reset ()
    {
	this.header.next = null;
	this.header.data = 0;
	this.number = 1;
    }


    /*---------------------------------------------------
    Author: Eric Zhou
    Date: 1/7/2019
    Purpose: returns the queue linked list in string. (show queue)
    Input: None
    Output: String queue
    ---------------------------------------------------*/
    public String toString ()
    {
	String s = "";
	if (this.header.next == null)
	{
	    s += "Empty";
	}
	else
	{
	    s += "F = " + this.front.data + " | ";
	    Node current = this.header.next;
	    for (int i = 0 ; i < this.header.data ; i++)
	    {
		s += current.data + " ";
		current = current.next;
	    }
	    s += "| B = " +  this.back.data;
	}
	return s;
    }
}
