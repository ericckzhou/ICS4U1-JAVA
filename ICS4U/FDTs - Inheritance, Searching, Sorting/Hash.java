// The "Hash" class.
/*--------------------------------------
Author: Eric Zhou
Date: 11/19/2018
Purpose: To complete hashing exercise.
Input: Keyboard
Output: Console
---------------------------------------*/
import java.awt.*;
import hsa.Console;
import hsa.*;
import java.util.*;

public class Hash
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
	boolean loop = true;
	c = new Console (35, 125);
	while (loop == true)
	{
	    int size = 0;
	    do
	    {
		c.print ("Please select your desired size (prime) for your data base: ");
		size = c.readInt ();
		if (size < 5)
		{
		    c.println ("The size must be greater than equal to 5.");
		}
	    }
	    while (size < 5);

	    HashDataBase db = new HashDataBase (size);
	    c.println ("The size of your data base is (set to nearest biggest prime): " + db.physicalSize);
	    c.print ("What is the name of the text file you would like to add: ");
	    String fileName = c.readString ();
	    db.textInput = new TextInputFile (fileName + ".txt");
	    c.println ();
	    boolean continuing = true;
	    while (continuing == true)
	    {
		String action = "";
		do
		{
		    c.println ("Choose from the following: add, find, peek, delete, change, exit");
		    c.print ("What would user like to do: ");
		    action = c.readLine ().toLowerCase ();
		    if (!(action.equals ("add") || action.equals ("find") || action.equals ("peek") || action.equals ("delete") || action.equals ("change") || action.equals ("exit")))
		    {
			c.println ("Invalid entry. Please choose from the following: add, find, peek, delete, change, exit");
		    }
		    c.println ();
		}
		while (!(action.equals ("add") || action.equals ("find") || action.equals ("peek") || action.equals ("delete") || action.equals ("change") || action.equals ("exit")));
		if (action.equals ("exit"))
		{
		    continuing = false;
		}
		else if (action.equals ("add"))
		{
		    db.add (c);
		}
		else if (action.equals ("find"))
		{
		    if (db.logicalSize <= 0)
		    {
			c.println ("There are currently no records in the database.");
		    }
		    else
		    {
			db.find (c);
		    }
		}
		else if (action.equals ("peek"))
		{
		    db.peek (c);
		}
		else if (action.equals ("delete"))
		{
		    if (db.logicalSize <= 0)
		    {
			c.println ("There are no records in the database to delete!");
		    }
		    if (db.logicalSize > 0)
		    {
			int index = -1;
			int deleteId = 0;
			c.print ("What is the id you would like to delete: ");
			deleteId = c.readInt ();
			index = db.search (deleteId);
			while (index == -1)
			{
			    c.println ("The record with the id of " + deleteId + " was not found.");
			    c.println ("Choose a different id to delete!");
			    c.print ("What is the id you would like to delete: ");
			    deleteId = c.readInt ();
			    index = db.search (deleteId);
			    c.println ();

			}
			db.delete (deleteId, c);
			c.clear ();
			c.println ("The record with the id of " + deleteId + " was deleted.");
		    }
		}
		else if (action.equals ("change"))
		{
		    if (db.logicalSize <= 0)
		    {
			c.println ("There are no records in the database to change!");
		    }
		    if (db.logicalSize > 0)
		    {
			int changeId = 0;
			int index = -1;
			do
			{
			    c.print ("What id would you like to change: ");
			    changeId = c.readInt ();
			    index = db.search (changeId);
			    if (index == -1)
			    {
				c.println ("The record with the id of " + changeId + " was not found.");
				c.println ("Choose a different id to change!");
				c.println ();
			    }
			}
			while (index == -1);

			c.print ("" + db.dataBase [index]);
			c.println ();
			boolean changing = true;
			int newId = changeId;
			String newTitle = db.dataBase [index].title;
			String newType = db.dataBase [index].type;
			double newCost = db.dataBase [index].cost;
			String newDist = db.dataBase [index].dist;
			String newDate = db.dataBase [index].date;

			while (changing == true)
			{
			    String changes = "";
			    do
			    {
				c.println ("Id, Title, Type, Cost, Dist, Date");
				c.print ("What would you like to change about the current record?: ");
				changes = c.readLine ().toLowerCase ();
				if (changes.equals ("id"))
				{
				    c.print ("What is the new id: ");
				    newId = c.readInt ();
				    while (newId < 0)
				    {
					c.println ("The id entered must be positive!");
					c.print ("What is the new id: ");
					newId = c.readInt ();
				    }
				}
				else if (changes.equals ("title"))
				{
				    c.print ("What is the new title: ");
				    newTitle = c.readLine ();
				}
				else if (changes.equals ("type"))
				{
				    c.print ("What is the new type: ");
				    newType = c.readLine ();
				}
				else if (changes.equals ("cost"))
				{
				    c.print ("What is the new cost: ");
				    newCost = c.readDouble ();
				    while (newCost < 0)
				    {
					c.println ("The cost entered must be positive!");
					c.print ("What is the new cost: ");
					newCost = c.readDouble ();
				    }
				}
				else if (changes.equals ("dist"))
				{
				    c.print ("What is the new dist: ");
				    newDist = c.readLine ();
				}
				else if (changes.equals ("date"))
				{
				    c.print ("What is the new date DD/MM/YYYY : ");
				    newDate = c.readLine ();
				}
				c.println ();
			    }
			    while (!(changes.equals ("id") || changes.equals ("title") || changes.equals ("type") || changes.equals ("dist") || changes.equals ("date")));

			    String stillChanging = "Y";
			    do
			    {
				c.print ("Would you like to change anything else? Y/N: ");
				stillChanging = c.readLine ().toUpperCase ();
				if (stillChanging.equals ("N"))
				{
				    changing = false;
				}
			    }
			    while (!(stillChanging.equals ("Y") || stillChanging.equals ("N")));
			}
			boolean sameId = false;
			for (int i = 0 ; i < db.physicalSize ; i++)
			{
			    if (db.dataBase [i] != null)
			    {
				if (db.dataBase [i].id == newId)
				{
				    sameId = true;
				}
			    }
			}
			while (sameId == true)
			{
			    int old = newId;
			    do
			    {
				c.println ("The new id chosen already exists within the database.");
				c.print ("Please choose another id!: ");
				newId = c.readInt ();

			    }
			    while (old == newId);
			    sameId = false;
			    for (int i = 0 ; i < db.physicalSize ; i++)
			    {
				if (db.dataBase [i] != null)
				{
				    if (db.dataBase [i].id == newId && db.dataBase [i].id == changeId)
				    {
					sameId = true;
				    }
				}
			    }
			}
			db.change (changeId, newId, newTitle, newType, newCost, newDist, newDate, c);
			c.clear ();
			c.println ("You have successfully changed the record!");
		    }
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
} // Hash class


/*---------------------------------------------------------
Class: DBRecord
Author: Eric Zhou
Date: 11/19/2018
Purpose: To create a movie record containing an id, title, type, cost, distance, and date.
Data Elements:
    id: record id
    title: record title
    type: type of movie
    cost: cost to purchase
    dist: distance to travel
    date: date of record
Methods:
    constructor: creates a DBRecord given all the information.
    toString: aligns the information in a string.
    rightPad (static, class method): pads a string with spaces to the leftto a certain width.
-----------------------------------------------------------*/
class DBRecord
{
    protected int id;
    protected String title;
    protected String type;
    protected double cost;
    protected String dist;
    protected String date;

    /*---------------------------------------
    Author: Eric Zhou
    Date: 11/19/2018
    Purpose: To construct a empty record
    Input: None
    Output: None
    ----------------------------------------*/
    public DBRecord ()
    {
	this.id = 0;
	this.title = "";
	this.type = "";
	this.cost = 0.0;
	this.dist = "";
	this.date = "";
    }


    /*---------------------------------------
    Author: Eric Zhou
    Date: 11/19/2018
    Purpose: To construct a DBRecord given all information
    Input: id, title, type, cost, dist, date
    Output: None
    ----------------------------------------*/
    public DBRecord (int getId, String getTitle, String getType, double getCost, String getDist, String getDate)
    {
	this.id = getId;
	this.title = getTitle;
	this.type = getType;
	this.cost = getCost;
	this.dist = getDist;
	this.date = getDate;
    }


    /*---------------------------------------
    Author: Eric Zhou
    Date: 11/19/2018
    Purpose: To align the DBRecord's information in a string
    Input: None
    Output: String containing DBRecord information.
    ----------------------------------------*/
    public String toString ()
    {
	return (rightPad ("" + this.id, 8) + rightPad (this.title, 50) + rightPad (type, 10) + rightPad ("" + this.cost, 8) + rightPad (this.dist, 6) + rightPad (this.date, 12));
    }


    /*---------------------------------------
    Author: Eric Zhou
    Date: 11/19/2018
    Purpose: To pad a string with spaces on the right to a certain width.
    Input: string to pad, width
    Output: Padded string with the correct number of characters.
    ----------------------------------------*/
    public static String rightPad (String x, int w)
    {
	String s = "" + x;
	while (s.length () < w)
	{
	    s += " ";
	}
	return s;
    }
}


/*-----------------------------------------------------------------------------------------------------------------------------
Class: HashDataBase
Author: Eric Zhou
Date: 11/19/2018
Purpose: To hold a Hash Table database of DBRecords.
Data Elements:
    textInput: holds a TextInputFile.
    dataBase: holds an array of DBRecords.
    physicalSize: the size of the dataBase array.
    logicalSize: the number of records in the array.
Methods:
    constructor: creates the array given a size value. (if not prime, set to next biggest prime).
    default constructor: sets physicalSize to 0.
    isPrime: checks if a given integer is a prime number.
    isFull: returns true if logicalSize / physicalSize > 70%.
    rehash: creates a NEW table with a prime size approx. 20% larger and inserts all the old non-deleted records back into it.
    peek: prints the complete hash table, showing partition indices and empty/null locations and records.
    insert: adds a given DBRecord to the dataBase using double hashing techniques. (Read more in exercise sheet).
    search: returns the location of a given id in the table (or -1 if not found), using double hashing.
    add: reads a line from the file and inserts the record to the table.
    find: asks the user for an id and displays the complete record.
    delete: asks for an id and if found, will delete it.
    change: asks for an id and display record, then asks for new information.
    If id remains same, simply change the record, if id changes, delete old and insert new.
-------------------------------------------------------------------------------------------------------------------------------*/
class HashDataBase
{
    protected TextInputFile textInput;
    protected DBRecord[] dataBase;
    protected int physicalSize;
    protected int logicalSize;

    public HashDataBase (int getSize)
    {
	if (this.isPrime (getSize) == false)
	{
	    while (this.isPrime (getSize) == false)
	    {
		getSize++;
	    }
	}
	this.physicalSize = getSize;
	this.dataBase = new DBRecord [this.physicalSize];
    }


    public HashDataBase ()
    {
	this.physicalSize = 0;
    }


    /*---------------------------------------
    Author: Eric Zhou
    Date: 11/19/2018
    Purpose: checks if a given integer is a prime number.
    Input: size
    Output: boolean statement
    ----------------------------------------*/
    public boolean isPrime (int size)
    {
	boolean prime = true;
	if (size == 2 || size == 3)
	{
	    prime = true;
	}
	else if (size < 2 || size % 2 == 0)
	{
	    prime = false;
	}
	else
	{
	    for (int divisor = 3 ; (divisor <= Math.sqrt (size)) || (size % divisor == 0) ; divisor += 2)
	    {
		if (size % divisor == 0)
		{
		    prime = false;
		}
	    }
	}
	return prime;
    }


    /*---------------------------------------
    Author: Eric Zhou
    Date: 11/19/2018
    Purpose: returns true if logicalSize / physicalSize > 70%.
    Input: None
    Output: boolean statement
    ----------------------------------------*/
    public boolean isFull ()
    {
	boolean full = false;
	double difference = ((double) (this.logicalSize) / (double) (this.physicalSize)) * 100;
	if (difference > 70)
	{
	    full = true;
	}
	return full;
    }


    /*---------------------------------------
    Author: Eric Zhou
    Date: 11/19/2018
    Purpose: creates a NEW table with a prime size approx. 20% larger and inserts all the old non-deleted records back into it.
    Input: None
    Output: None
    ----------------------------------------*/
    public void rehash ()
    {
	DBRecord[] old = this.dataBase;
	int newSize = this.physicalSize + 1;
	double difference = ((double) (newSize) / (double) (this.physicalSize)) * 100;
	while (this.isPrime (newSize) == false || difference < 120)
	{
	    //finding right size
	    newSize++;
	    difference = ((double) (newSize) / (double) (this.physicalSize)) * 100.00;

	}
	this.dataBase = new DBRecord [newSize];
	int oldSize = this.physicalSize;
	this.physicalSize = newSize;
	//reinserting back to new database
	for (int i = 0 ; i < oldSize ; i++)
	{
	    if (old [i] != null)
	    {
		int counter = 0;
		int index = this.h (old [i].id, counter);
		while (this.dataBase [index] != null)
		{
		    counter++;
		    index = this.h (old [i].id, counter);
		}
		this.dataBase [index] = old [i];
	    }
	}
    }


    /*---------------------------------------
    Author: Eric Zhou
    Date: 11/19/2018
    Purpose: prints the complete hash table, showing partition indices and empty/null locations and records.
    Input: Console c
    Output: None
    ----------------------------------------*/
    public void peek (Console c)
    {
	for (int i = 0 ; i < this.physicalSize ; i++)
	{
	    StringBuffer strNum = new StringBuffer (i + "");
	    while (strNum.toString ().length () < 4)
	    {
		strNum.insert (0, " ");
	    }
	    c.println (strNum + ". " + this.dataBase [i]);
	    if (i % 20 == 0 && i != 0)
	    {
		//to avoid bad form
		if (this.physicalSize - i >= 20)
		{
		    c.print ("Press any key to print the next 20!");
		}
		else
		{
		    c.print ("Press any key to print the next " + (this.physicalSize - (i + 1)) + "!");
		}
		//
		char getChar = c.getChar ();
		c.println ();
		c.clear ();
	    }
	}
    }


    /*---------------------------------------
    Author: Eric Zhou
    Date: 11/19/2018
    Purpose: adds a given DBRecord to the dataBase using double hashing techniques.
    Input: DBRecord, Console
    Output: None
    ----------------------------------------*/
    public void insert (DBRecord record, Console c)
    {
	int collision = 0;
	int rehashes = 0;
	if (this.isFull () == true)
	{
	    this.rehash ();
	    rehashes++;
	}
	int counter = 0;
	int index = this.h (record.id, counter);
	while (this.dataBase [index] != null)
	{
	    counter++;
	    collision++;
	    index = this.h (record.id, counter);

	}
	//checking for existing records in database w/ same id
	boolean sameId = false;
	for (int i = 0 ; i < this.physicalSize ; i++)
	{
	    if (this.dataBase [i] != null)
	    {
		if (this.dataBase [i].id == record.id)
		{
		    sameId = true;
		}
	    }
	}

	if (sameId == false)
	{
	    this.dataBase [index] = record;
	    c.println ("" + this.dataBase [index]);
	    c.println ("Index: " + index + ", The number of rehashes: " + rehashes + ", The number of collisions: " + collision);
	    this.logicalSize++;
	}
	else
	{
	    c.println ("*There is a current record in the database with the same id.*");
	    c.println ("*The record was not added in order to maintain uniquity.*");
	}
    }


    /*---------------------------------------
    Author: EricZhou
    Date:11/19/2018
    Purpose: returns the location of a given id in the table (or -1 if not found), using double hashing.
    Input: id
    Output: location of the id (index)
    ----------------------------------------*/
    public int search (int id)
    {
	int counter = 0;
	int index = -1;
	while (this.dataBase [this.h (id, counter)] != null)
	{
	    if (this.dataBase [this.h (id, counter)].id == id)
	    {
		index = this.h (id, counter);
	    }
	    counter++;
	}

	return index;
    }


    /*---------------------------------------
    Author: Eric Zhou
    Date: 11/19/2018
    Purpose: reads a line from the file and inserts the record to the table.
    Input: Console c
    Output: None
    ----------------------------------------*/
    public void add (Console c)
    {
	String line = this.textInput.readLine ();
	StringTokenizer t = new StringTokenizer (line, ",");
	int id = 0;
	String title = "";
	String type = "";
	double cost = 0;
	String dist = "";
	String date = "";
	int count = 0;
	while (t.hasMoreTokens ())
	{
	    String next = t.nextToken ();
	    if (count == 0)
	    {
		id = Integer.parseInt (next);
	    }
	    else if (count == 1)
	    {
		title = next;
	    }
	    else if (count == 2)
	    {
		type = next;
	    }
	    else if (count == 3)
	    {
		cost = Double.parseDouble (next);
	    }
	    else if (count == 4)
	    {
		dist = next;
	    }
	    else if (count == 5)
	    {
		date = next;
	    }
	    count++;
	}
	DBRecord record = new DBRecord (id, title, type, cost, dist, date);
	this.insert (record, c);
	if (this.textInput.eof ())
	{
	    this.textInput.close ();
	}
    }


    /*---------------------------------------
    Author: Eric Zhou
    Date: 11/19/2018
    Purpose: asks the user for an id and displays the complete record.
    Input: Console c
    Output: None
    ----------------------------------------*/
    public void find (Console c)
    {
	int userId = 0;
	int index = -1;
	c.print ("What is the id: ");
	userId = c.readInt ();
	index = this.search (userId);
	if (index == -1)
	{
	    c.println ("The id, " + userId + " is not in the database!");
	    c.println ("Enter another id instead!");
	}
	else
	{
	    c.println (this.dataBase [index] + "");
	}
    }


    /*---------------------------------------
    Author: Eric Zhou
    Date: 11/19/2018
    Purpose: asks for an id and if found, will delete it.
    Input: Id you want to delete, Console c
    Output: None
    ----------------------------------------*/
    public void delete (int deleteId, Console c)
    {
	int counter = 0;
	boolean foundIndex = false;
	//making counter equal to where deleteId is.
	while (foundIndex == false)
	{
	    if (this.dataBase [this.h (deleteId, counter)] != null)
	    {
		if (this.dataBase [this.h (deleteId, counter)].id == deleteId)
		{
		    foundIndex = true;
		}
		else
		{
		    counter++;
		}
	    }
	    else
	    {
		counter++;
	    }
	}
	this.dataBase [this.h (deleteId, counter)] = null;
	HashDataBase newDB = new HashDataBase (this.physicalSize); //dummy
	//rehashing each record
	for (int i = 0 ; i < this.physicalSize ; i++)
	{
	    if (this.dataBase [i] != null)
	    {
		newDB.insert (this.dataBase [i], c);
		c.println ();
	    }
	}
	this.dataBase = newDB.dataBase;
	this.logicalSize--;

    }


    /*---------------------------------------
    Author: Eric Zhou
    Date: 11/19/2018
    Purpose: asks for an id and display record, then asks for new information.
    Input: Id you want to change, the new id, new title, new type, new cost, new dist, new date, Console c
    Output: None
    ----------------------------------------*/
    public void change (int changeId, int newId, String newTitle, String newType, double newCost, String newDist, String newDate, Console c)
    {
	if (newId == changeId) //changeId is the id that user wants to change. newId is the id that user wants the changeId to be.
	{
	    int index = this.search (newId);
	    this.dataBase [index].title = newTitle;
	    this.dataBase [index].type = newType;
	    this.dataBase [index].cost = newCost;
	    this.dataBase [index].dist = newDist;
	    this.dataBase [index].date = newDate;
	}

	else
	{
	    this.delete (changeId, c);
	    DBRecord newRecord = new DBRecord (newId, newTitle, newType, newCost, newDist, newDate);
	    this.insert (newRecord, c);
	}

    }


    /*---------------------------------------
    Author: Eric Zhou
    Date: 11/19/2018
    Purpose: Returns the hash f()
    Input: id, counter
    Output: hash function
    ----------------------------------------*/
    public int h (int k, int i)
    {
	return (k % this.physicalSize + i * h2 (k)) % this.physicalSize;
    }


    /*---------------------------------------
    Author: Eric Zhou
    Date: 11/19/2018
    Purpose: Return the double hashing f()
    Input: id
    Output: double hash function
    ----------------------------------------*/
    public int h2 (int k)
    {
	return (this.physicalSize - 2) - (k % (this.physicalSize - 2));
    }
} //HashDataBase
