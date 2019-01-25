// The "BinarySearchTreeTest" class.
// Author: Eric Zhou
// Date: 1/15/2019
// Purpose: To complete tree exercise.
// Input: Keyboard
// Output: Console
// -----------------------------------
import java.awt.*;
import hsa.Console;
import java.util.*;

// The "BinarySearchTreeTest" class.
//Input:    None
//Output:   None
//Purpose:  To run given main.
class BinarySearchTreeTest
{
    static Console c = new Console (35, 125);

    public static void main (String[] args)
    {
	boolean loop = true;
	while (loop == true)
	{
	    BinarySearchTree bst = new BinarySearchTree ();
	    int size = -1;
	    do
	    {
		c.print ("Select a number from 1-10 that will fill the tree randomly: ");
		size = c.readInt ();
	    }
	    while (size < 0 || size > 10);
	    int num;
	    for (int i = 0 ; i < size ; i++)
	    {
		num = (int) (Math.random () * 99) + 1;
		bst.add (num);
	    }
	    int action = -1;
	    do
	    {
		c.clear ();
		c.println ();
		if (bst.root != null)
		{
		    bst.printTree (c);
		}
		else
		{
		    c.println ("EMPTY");
		}
		c.println ();
		c.println ("Perfectly Balanced: " + bst.perfectlyBalanced (c));
		c.println ("Height Balanced: " + bst.heightBalanced (c));
		c.println ();
		c.println ("1. printOrders (pre,in,post), 2. numOfNodes, 3. treeHeight, 4. getLevel.");
		c.println ("5. findSibling, 6. breadthFirstOrder, 7. delete, 8. Exit.");
		c.print ("What would user like: ");
		action = c.readInt ();
		if (action == 1)
		{
		    c.println ("preOrder: " + bst.preOrder ());
		    c.println ("postOrder: " + bst.postOrder ());
		    c.println ("InOrder: " + bst.inOrder ());
		}
		if (action == 2)
		{
		    c.println ("Number of Nodes: " + bst.nodes ());
		}
		if (action == 3)
		{
		    c.println ("Height of Tree: " + bst.height ());
		}
		if (action == 4)
		{
		    if (bst.root != null)
		    {
			c.print ("Which Node's level would you like to find: ");
			int userLevel = c.readInt ();
			int findLevel = bst.getLevel (userLevel);
			c.println ("The level of Node " + userLevel + " is " + bst.getLevel (userLevel));
		    }
		    else
		    {
			c.println ("The tree is empty. There is nothing to find.");
		    }
		}
		if (action == 5)
		{
		    if (bst.root != null)
		    {
			c.print ("What is the sibling that you would like to find: ");
			int sibling = c.readInt ();
			Node findSib = bst.find (sibling);
			if (findSib != null)
			{
			    Node foundSibling = bst.findSibling (sibling);
			    if (foundSibling != null)
			    {
				c.println ("The sibling of " + sibling + " is " + bst.findSibling (sibling).data + "");
			    }
			    else
			    {
				c.println (sibling + " has no sibling.");
			    }
			}
			else
			{
			    c.println (sibling + " is not in the tree");
			}
		    }
		    else
		    {
			c.println ("The tree is empty, thus there are no Nodes to find the sibling of.");
		    }
		}
		if (action == 6)
		{
		    c.println ("BreadthFirstOrder: " + bst.breadthFirstOrder ());
		}
		if (action == 7)
		{
		    if (bst.root != null)
		    {
			c.print ("Which value would you like to delete: ");
			int deleteNum = c.readInt ();
			boolean deleted = bst.delete (deleteNum);
			if (deleted == true)
			{
			    c.println ("The number " + deleteNum + " was successfully deleted.");
			}
			else
			{
			    c.println ("The number " + deleteNum + " cannot be deleted.");
			}
		    }
		    else
		    {
			c.println ("The tree is empty. There is nothing to delete.");
		    }
		}
		c.println ();
		c.print ("Press any key to continue...");
		c.readChar ();
	    }
	    while (action != 8);
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

    }
}

//Class Node
//Fields:   int data    -   the information held by the Node
//          Node right  -   the next node to the right of the tree
//          Node left   -   the next node to the left of the tree
class Node
{
    public int data;
    public Node left;
    public Node right;

    public Node (int info)
    {
	this.data = info;
	this.left = null;
	this.right = null;
    }


    public Node ()
    {
	this (0);
    }
}
//Class QNode
//Fields:   Node data   -   the information held by the QNode
//          QNode next  -   the next node in the list
class QNode
{
    public Node data;
    public QNode next;

    public QNode (Node info)
    {
	this.data = info;
	this.next = null;
    }


    public QNode ()
    {
	this (null);
    }
}

//Class Queue
//Fields:   QNode front -   the front of the queue
//          QNode back  -   the back of the queue
//Methods:  add     -   adds a given node to the Queue
//          remove  -   removes a node at the front and returns removed node
//          empty   -   returns true if queue is empty
class Queue
{
    public QNode front;
    public QNode back;

    public Queue ()
    {
	this.front = null;
	this.back = null;
    }


    //add
    //Purpose:      adds a node at the end of the queue
    //Parameters:   Node x   -   the node being added
    //Output:       None
    public void add (Node x)
    {
	if (this.isEmpty ())
	{
	    this.front = new QNode (x);
	    this.back = this.front;
	}
	else
	{
	    this.back.next = new QNode (x);
	    this.back = this.back.next;
	}
    }


    //remove
    //Purpose:      removes a node at the front of the queue
    //Parameters:   None
    //Output:       the node that was removed
    public Node remove ()
    {
	Node ans;

	ans = this.front.data;
	this.front = this.front.next;
	return ans;

    }


    //isEmpty
    //Purpose:      returns true if array is empty
    //Parameters:   None
    //Output:       true or false
    public boolean isEmpty ()
    {
	boolean status = false;
	if (this.front == null)
	    status = true;
	return status;
    }
}
/*-----------------------------------------------------------------------------
Class BinarySearchTree
Author(s): Eric Zhou, Moodle Code Source
Date: 1/15/2019
Purpose: To create a class enable of doing binary search tree tasks.
Fields:   Node root           -   the root of the tree
Methods:
    add: adds a leaf to the tree with given info
    find: returns the node with the given information
    printTree: calls rPrintTree to print tree recursively.
    rPrintTree: prints the binary search tree recursively.
    preOrder: calls and returns rPreOrder with the root.
    rPreOrder: returns the preOrder recursively of the binary search tree.
    postOrder: calls rPostOrder to get the string of the post order of the binary search tree.
    rPostOrder: returns the post order of the binary search tree recursively.
    inOrder: calls rInOrder to get the string of the in order of the binary search tree.
    rInOrder: returns the in order of the binary search tree recursively.
    height: returns the height of the tree.
    rHeight: finds the height of the tree recursively.
    nodes: returns the number of nodes in the tree.
    rNodes: finds how many nodes there are in the tree recursively.
    perfectlyBalanced: returns a boolean statement whether or not the tree is perfectly balanced.
    rPerfectlyBalanced: checks the tree recursively to see if its perfectly balanced.
    heightBalanced: returns a boolean statement whether or not the tree is height balanced.
    rHeightBalanced: checks the tree recursively to see if the tree is height balanced.
    getLevel: returns the level of a given integer value.
    findSibling: finds the sibling of a given integer on the tree (if it exists).
    breadthFirthOrder: Creates a string of the entire tree with the root first, then all Nodes on level 1 (l -> r), etc..
    delete: removes a Node from the exxisting tree given a integer value.
------------------------------------------------------------------------------*/
class BinarySearchTree
{
    protected Node root;

    public BinarySearchTree ()
    {
	root = null;
    }


    //add
    //Purpose:      adds a leaf to the tree
    //Parameters:   int x   -   the info of the leaf being added
    //Output:       true or false
    public boolean add (int info)
    {

	if (this.root == null)
	    this.root = new Node (info);
	else
	{
	    Node ptr = this.root;
	    while (ptr != null)
	    {
		if (info < ptr.data)
		    if (ptr.left != null)
			ptr = ptr.left;
		    else
		    {
			ptr.left = new Node (info);
			return true;
		    }
		else if (info > ptr.data)
		    if (ptr.right != null)
			ptr = ptr.right;
		    else
		    {
			ptr.right = new Node (info);
			return true;
		    }
		else if (info == ptr.data)
		{
		    return false;
		}
	    }
	}
	return true;
    }


    //find
    //Purpose:  finds the node with the given information
    //Parameters:   int x   -   the info of the node that is being looked for
    //Output:       returns the node with the given information
    public Node find (int x)
    {
	Node ptr;
	ptr = this.root;
	while (ptr != null && ptr.data != x)
	    if (x < ptr.data)
		ptr = ptr.left;
	    else
		ptr = ptr.right;
	return ptr;
    }


    /************************************************
    Method:printTree
    Purpose:Prints the tree in a normal way.
	(Can't handle super wide trees.)
	-  Developed by Jonathan Chan, May 1997
	-  Java version by Edmund Wong, Dec 2000
	-  Modified by Ian Halliday, June 2001
    Parameters: The Console
    Return Value: None
    *************************************************/
    public void printTree (Console c)
    {
	c.setCursor
	    (rprintTree (c, root, c.getRow ()), 1);
    }


    /************************************************
    Method: rprintTree
    Purpose: See above.
    Parameters: the console, the node being printed
		and the row to print on
    Return Value: The maximum row reached by
		  printing (allowing for useful
		  cursor placement)
    *************************************************/
    protected int rprintTree (Console c, Node n, int r)
    {
	int maxRow;
	if (n != null)
	{
	    maxRow = rprintTree (c, n.left, r + 2);
	    c.setCursor (r, c.getColumn ());
	    c.print (n.data, 3);
	    maxRow = Math.max (maxRow, rprintTree (c, n.right, r + 2));
	    return maxRow;
	}
	return r - 1;
    }


    /*-------------------------------------------------------
    Author: Eric Zhou
    Date: 1/15/2019
    Purpose: calls and returns rPreOrder with the root.
    Input: None
    Output: Returns the string of the pre order.
    --------------------------------------------------------*/
    public String preOrder ()
    {
	String s;
	if (this.root != null)
	{
	    s = this.rPreOrder (this.root);
	}
	else
	{
	    s = "EMPTY";
	}
	return s;
    }


    /*-------------------------------------------------------
    Author: Eric Zhou
    Date: 1/15/2019
    Purpose: returns the preOrder recursively of the binary search tree.
    Input: Node
    Output: A string
    --------------------------------------------------------*/
    protected String rPreOrder (Node n)
    {
	String s = "";
	if (n != null)
	{
	    s += n.data + " ";
	    s += rPreOrder (n.left);
	    s += rPreOrder (n.right);
	}
	return s;
    }


    /*-------------------------------------------------------
    Author: Eric Zhou
    Date: 1/15/2019
    Purpose: calls rPostOrder to get the string of the post order of the binary search tree.
    Input: None
    Output: A string
    --------------------------------------------------------*/
    public String postOrder ()
    {
	String s;
	if (this.root != null)
	{
	    s = this.rPostOrder (this.root);
	}
	else
	{
	    s = "EMPTY";
	}
	return s;

    }


    /*-------------------------------------------------------
    Author: Eric Zhou
    Date: 1/15/2019
    Purpose: returns the post order of the binary search tree recursively.
    Input: Node
    Output: A string
    --------------------------------------------------------*/
    protected String rPostOrder (Node n)
    {
	String s = "";
	if (n != null)
	{
	    s += rPostOrder (n.left);
	    s += rPostOrder (n.right);
	    s += n.data + " ";
	}
	return s;
    }


    /*-------------------------------------------------------
    Author: Eric Zhou
    Date: 1/15/2019
    Purpose: calls rInOrder to get the string of the in order of the binary search tree.
    Input: None
    Output: A string
    --------------------------------------------------------*/
    public String inOrder ()
    {
	String s;
	if (this.root != null)
	{
	    s = this.rInOrder (this.root);
	}
	else
	{
	    s = "EMPTY";
	}
	return s;
    }


    /*-------------------------------------------------------
    Author: Eric Zhou
    Date: 1/15/2019
    Purpose: returns the in order of the binary search tree recursively.
    Input: Node
    Output: A string
    --------------------------------------------------------*/
    protected String rInOrder (Node n)
    {
	String s = "";
	if (n != null)
	{
	    s += rInOrder (n.left);
	    s += n.data + " ";
	    s += rInOrder (n.right);
	}
	return s;
    }


    /*-------------------------------------------------------
    Author: Eric Zhou
    Date: 1/15/2019
    Purpose: returns the height of the tree.
    Input: None
    Output: The height (integer)
    --------------------------------------------------------*/
    public int height ()
    {
	return rHeight (this.root);
    }


    /*-------------------------------------------------------
    Author: Eric Zhou
    Date: 1/15/2019
    Purpose: finds the height of the tree recursively.
    Input: Node
    Output: A integer (height)
    --------------------------------------------------------*/
    protected int rHeight (Node n)
    {
	int height = 0;
	if (n != null)
	{
	    int leftHeight = 1;
	    int rightHeight = 1;
	    if (n.left != null)
	    {
		leftHeight += rHeight (n.left);
	    }
	    if (n.right != null)
	    {
		rightHeight += rHeight (n.right);
	    }
	    if (leftHeight >= rightHeight)
	    {
		height += leftHeight;
	    }
	    else
	    {
		height += rightHeight;
	    }
	}
	return height;
    }


    /*-------------------------------------------------------
    Author: Eric Zhou
    Date: 1/15/2019
    Purpose: returns the number of nodes in the tree.
    Input: None
    Output: A integer (num of nodes)
    --------------------------------------------------------*/
    public int nodes ()
    {
	return rNodes (this.root);
    }


    /*-------------------------------------------------------
    Author: Eric Zhou
    Date: 1/15/2019
    Purpose: finds how many nodes there are in the tree recursively.
    Input: Node
    Output: A number of nodes in the tree (int)
    --------------------------------------------------------*/
    protected int rNodes (Node n)
    {
	int count = 0;
	if (n != null)
	{
	    count += 1;
	    count += rNodes (n.left);
	    count += rNodes (n.right);
	}
	return count;
    }


    /*-------------------------------------------------------
    Author: Eric Zhou
    Date: 1/15/2019
    Purpose: returns a boolean statement whether or not the tree is perfectly balanced.
    Input: Console c
    Output: Boolean statement
    --------------------------------------------------------*/
    public boolean perfectlyBalanced (Console c)
    {
	return rPerfectlyBalanced (root, true, c);
    }


    /*-------------------------------------------------------
    Author: Eric Zhou
    Date: 1/15/2019
    Purpose: checks the tree recursively to see if its perfectly balanced.
    Input: Node n, boolean balanced, Console c
    Output: Boolean
    --------------------------------------------------------*/
    public boolean rPerfectlyBalanced (Node n, boolean balanced, Console c)
    {
	int leftCount = 0;
	int rightCount = 0;
	if (n != null && balanced == true)
	{
	    if (n.left != null)
	    {
		leftCount += rNodes (n.left);
		balanced = rPerfectlyBalanced (n.left, balanced, c);
	    }
	    if (n.right != null)
	    {
		rightCount += rNodes (n.right);
		balanced = rPerfectlyBalanced (n.right, balanced, c);
	    }
	    if (Math.abs (leftCount - rightCount) > 1)
	    {
		balanced = false;
		c.println ("Tree is not balanced at " + n.data + ".");
	    }

	}
	else
	{
	    balanced = false; //root == null
	}
	return balanced;
    }


    /*-------------------------------------------------------
    Author: Eric Zhou
    Date: 1/15/2019
    Purpose: returns a boolean statement whether or not the tree is height balanced.
    Input: Console c
    Output: Boolean
    --------------------------------------------------------*/
    public boolean heightBalanced (Console c)
    {
	return rHeightBalanced (this.root, true, c);
    }


    /*-------------------------------------------------------
    Author: Eric Zhou
    Date: 1/15/2019
    Purpose: checks the tree recursively to see if the tree is height balanced.
    Input: Node n, boolean balanced, console c
    Output: Boolean
    --------------------------------------------------------*/
    public boolean rHeightBalanced (Node n, boolean balanced, Console c)
    {
	if (n != null && balanced == true)
	{
	    int leftHeight = 0;
	    int rightHeight = 0;

	    if (n.left != null)
	    {
		leftHeight += rHeight (n.left);
		balanced = rHeightBalanced (n.left, balanced, c);
	    }
	    if (n.right != null)
	    {
		rightHeight += rHeight (n.right);
		balanced = rHeightBalanced (n.right, balanced, c);
	    }
	    if (Math.abs (leftHeight - rightHeight) > 1)
	    {
		balanced = false;
		c.println ("Height not balanced at " + n.data + ".");
	    }
	}
	else
	{
	    balanced = false;
	}
	return balanced;
    }


    /*-------------------------------------------------------
    Author: Eric Zhou
    Date: 1/15/2019
    Purpose: returns the level of a given integer value.
    Input: Integer
    Output: Integer
    --------------------------------------------------------*/
    public int getLevel (int val)
    {
	Node findNode = this.find (val);
	int level = -1;
	if (this.root != null && findNode != null && findNode.data == val)
	{
	    level = 0;
	    Node curr = this.root;
	    while (curr.data != val)
	    {
		if (val < curr.data)
		{
		    curr = curr.left;
		}
		else
		{
		    curr = curr.right;
		}
		level++;
	    }
	}
	return level;

    }


    /*-------------------------------------------------------
    Author: Eric Zhou
    Date: 1/15/2019
    Purpose: finds the sibling of a given integer on the tree (if it exists).
    Input: Integer
    Output: Node
    --------------------------------------------------------*/
    public Node findSibling (int val)
    {
	Node sibling = null;
	Node findNode = this.find (val);
	if (this.root != null && findNode != null)
	{
	    sibling = this.root;
	    boolean found = false;
	    while (found == false)
	    {
		if (sibling.left != null && sibling.left.data == val)
		{
		    sibling = sibling.right;
		    found = true;
		}
		else if (sibling.right != null && sibling.right.data == val)
		{
		    sibling = sibling.left;
		    found = true;
		}
		if (found == false)
		{
		    if (val < sibling.data)
		    {
			sibling = sibling.left;
		    }
		    else if (val > sibling.data)
		    {
			sibling = sibling.right;
		    }
		}
	    }
	}
	return sibling;
    }


    /*-------------------------------------------------------
    Author: Eric Zhou
    Date: 1/15/2019
    Purpose: Creates a string of the entire tree with the root first, then all Nodes on level 1 (l -> r), etc..
    Input: None
    Output: String
    --------------------------------------------------------*/
    public String breadthFirstOrder ()
    {
	String s = "";
	if (this.root != null)
	{
	    Queue q = new Queue ();
	    q.add (this.root);
	    while (q.front != null)
	    {
		Node x = q.remove ();
		s += x.data + " ";
		if (x.left != null)
		{
		    q.add (x.left);
		}
		if (x.right != null)
		{
		    q.add (x.right);
		}
	    }
	}
	else
	{
	    s += "Empty";
	}
	return s;
    }


    /*-------------------------------------------------------
    Author: Eric Zhou
    Date: 1/16/2019
    Purpose: removes a Node from the exxisting tree given a integer value.
    Input: Integer
    Output: Boolean
    --------------------------------------------------------*/
    public boolean delete (int val)
    {
	boolean deleted = false;
	if (this.find (val) != null && this.root != null)
	{
	    deleted = true;
	    Node curr = this.root; //current node
	    Node found = this.root; //found left or right == val node
	    Node replace = null; //replacement for the node being deleted.
	    Node beforeReplace = null;
	    if (this.root.data != val)
	    {
		while (curr.data != val)
		{
		    if (val < curr.data)
		    {
			found = curr;
			curr = curr.left;
		    }
		    else
		    {
			found = curr;
			curr = curr.right;
		    }
		}
		if (found.left != null && found.left.data == val)
		{
		    if (curr.left != null && curr.right != null)
		    {
			beforeReplace = curr;
			replace = curr.left;
			boolean next = true;
			while (next == true)
			{
			    if (replace.right != null)
			    {
				beforeReplace = replace;
				replace = replace.right;
			    }
			    if (replace.left != null)
			    {
				beforeReplace = replace;
				replace = replace.left;
			    }
			    if (replace.left == null && replace.right == null)
			    {
				next = false;
			    }
			}
			found.left.data = replace.data;
			if (beforeReplace.left != null && beforeReplace.left.data == replace.data)
			{
			    beforeReplace.left = null;
			}
			else
			{
			    beforeReplace.right = null;
			}

		    }
		    else if (curr.left != null)
		    {
			found.left = curr.left;
		    }
		    else if (curr.right != null)
		    {
			found.left = curr.right;
		    }
		    else
		    {
			found.left = null;
		    }
		}
		else //found.right = curr
		{
		    if (curr.left != null && curr.right != null)
		    {
			beforeReplace = curr;
			replace = curr.right;
			boolean next = true;
			while (next == true)
			{
			    if (replace.left != null)
			    {
				beforeReplace = replace;
				replace = replace.left;
			    }
			    if (replace.right != null)
			    {
				beforeReplace = replace;
				replace = replace.right;
			    }
			    if (replace.left == null && replace.right == null)
			    {
				next = false;
			    }
			}
			found.right.data = replace.data;
			if (beforeReplace.left != null && beforeReplace.left.data == replace.data)
			{
			    beforeReplace.left = null;
			}
			else
			{
			    beforeReplace.right = null;
			}

		    }
		    else if (curr.left != null)
		    {
			found.right = curr.left;
		    }
		    else if (curr.right != null)
		    {
			found.right = curr.right;
		    }
		    else
		    {
			found.right = null;
		    }
		}
	    }
	    else
	    {
		if (curr.left != null && curr.right != null)
		{
		    beforeReplace = curr;
		    replace = curr.left;
		    boolean next = true;
		    while (next == true)
		    {
			if (replace.right != null)
			{
			    beforeReplace = replace;
			    replace = replace.right;
			}
			if (replace.left != null)
			{
			    beforeReplace = replace;
			    replace = replace.left;
			}
			if (replace.left == null && replace.right == null)
			{
			    next = false;
			}
		    }
		    this.root.data = replace.data;
		    if (beforeReplace.left != null && beforeReplace.left.data == replace.data)
		    {
			beforeReplace.left = null;
		    }
		    else
		    {
			beforeReplace.right = null;
		    }
		}
		else if (curr.left != null)
		{
		    this.root = curr.left;
		}
		else if (curr.right != null)
		{
		    this.root = curr.right;
		}
		else
		{
		    this.root = null;
		}
	    }
	}
	return deleted;
    }
}

