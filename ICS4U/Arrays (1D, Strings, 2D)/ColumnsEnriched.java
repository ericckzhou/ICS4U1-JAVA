// The "ColumnsEnriched" class.
/*----------------------------------------
Author: Eric Zhou
Date: 10/16/2018
Purpose: To complete String exercise
Input: Keyboard
Output: Console
-----------------------------------------*/
import java.awt.*;
import hsa.Console;
import java.io.*; //buffered reader
import java.util.*; //tokenizer

public class ColumnsEnriched
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
	c = new Console ();
	boolean loop = true;
	while (loop == true)
	{
	    Columnized getColumnized = new Columnized ();
	    getColumnized.get (c);
	    c.println ();
	    getColumnized.readFile (c);
	    getColumnized.createDoc ();
	    c.println ();
	    //c.println (getColumnized.docList.toString ()); if you want print
	    getColumnized.writeFile (c);
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
} // ColumnsEnriched class

/*----------------------------------------
Author: Eric Zhou
Date: 10/16/2018
Purpose: To columnize text according to user specification
Data Elements:
    wordsList: A string that holds the words of the file.
    docList: A stringbuffer that holds the lines of justified text.
    justification: User inputs a specific justification.
    width: the width of the column.
    column: the number of columns specified by user.
    row: file size divided by width. (how many rows)
    size: size of wordsList
    inputFile: the name of the input file text.
    outputFile: the name of the output file text.
Methods:
    get: To get user input for fields.
    readFile: To read the file name that user inputted
    writeFile: To write the columnized text in output file
    createDoc: To create the doc list
    justifyLeft:  To justify text to the left
    justifyRight:  To justify text to the right
    justifyCentre:  To justify text in the centre
    justifyFully:  To justify text fully
-----------------------------------------*/
class Columnized
{
    public String wordsList;
    public StringBuffer docList;
    public String justification;
    public int width;
    public int column;
    public int row;
    public int size;
    public String inputFile;
    public String outputFile;

    public Columnized ()
    {
	this.wordsList = "";
	this.docList = new StringBuffer ();
	this.justification = "";
	this.width = 0;
	this.column = 0;
	this.row = 0;
	this.size = 0;
	this.inputFile = "";
	this.outputFile = "";
    }

    /*-------------------------------------------
    Author: Eric Zhou
    Date: Oct 16, 2018
    Purpose: To get user input for fields.
    Input: Console c
    Output: None
    -------------------------------------------*/
    public void get (Console c)
    {
	c.print ("What is the name of the input .txt file: ");
	this.inputFile = c.readString ();
	this.inputFile += ".txt";
	c.print ("What is the name of the output .txt file: ");
	this.outputFile = c.readString ();
	this.outputFile += ".txt";
	do
	{
	    c.println ("What justification would you like?");
	    c.print ("'l' = left, 'r' = right, 'c' = centre or 'f' = fully justification: ");
	    this.justification = c.readString ().toLowerCase ();
	    if (!(this.justification.equals ("l") || this.justification.equals ("r")
			|| this.justification.equals ("c") || this.justification.equals ("f")))
	    {
		c.println ("ERROR: You did not choose from the following list of justifications!");
	    }
	}
	while (!(this.justification.equals ("l") || this.justification.equals ("r")
		    || this.justification.equals ("c") || this.justification.equals ("f")));

	do
	{
	    c.print ("How many columns would you like (1-4): "); //mod width = 0
	    this.column = c.readInt ();
	    if (this.column < 1 || this.column > 4)
	    {
		c.println ("ERROR: User must choose from the range 1 - 4 for the column!");
	    }
	}
	while (this.column < 1 || this.column > 4);
	{
	}
	do
	{
	    c.print ("What would you like the column width to be (20 - 50): ");
	    this.width = c.readInt ();
	    if (this.width < 20 || this.width > 50)
	    {
		c.println ("ERROR: User must choose from the range 20 - 50 for the width!");
	    }
	}
	while (this.width < 20 || this.width > 50);
	{
	}

    }


    /*-------------------------------------------
    Author: Eric Zhou
    Date: Oct 16, 2018
    Purpose: To read the file name that user inputted
    Input: Console c
    Output: None
    -------------------------------------------*/
    public void readFile (Console c)
    {
	String line = null;
	try
	{
	    FileReader fileReader = new FileReader (this.inputFile);
	    BufferedReader bufferedReader = new BufferedReader (fileReader);
	    while ((line = bufferedReader.readLine ()) != null)
	    {
		this.wordsList += line;
	    }
	    bufferedReader.close ();
	    c.println ("The file " + this.inputFile + " file has been read!");
	}
	catch (FileNotFoundException ex)
	{
	    System.out.println ("Unable to open file.");
	}
	catch (IOException ex)
	{
	    System.out.println ("Error reading file.");
	}

	StringTokenizer t = new StringTokenizer (this.wordsList);
	int words = t.countTokens ();
	this.size = this.wordsList.length ();
	this.row = Math.round (this.size / this.width); //estimate row size. (height)


    }


    /*-------------------------------------------
    Author: Eric Zhou
    Date: Oct 16, 2018
    Purpose: To write the columnized text in output file
    Input: Console c
    Output: None
    -------------------------------------------*/
    public void writeFile (Console c)
    {
	try
	{
	    BufferedWriter output = new BufferedWriter (new FileWriter (this.outputFile));
	    int[] columnSpace = new int [this.column]; //stores the column space to get from 1 column to another.
	    int linesPerCol = this.row / this.column;
	    int nextRow = this.width + 1;
	    int extraLines = this.row - (linesPerCol * this.column);
	    int increment = 0;
	    //each line's width is width + 1
	    //finding the space to get to each column
	    for (int i = 0 ; i < this.column ; i++)
	    {
		if (extraLines > 0)
		{
		    columnSpace [i] += (this.width + 1);
		    extraLines--;
		}
		columnSpace [i] += (linesPerCol * (this.width + 1));
    
	    }
	    int countRows = 0;
	    int countPrint = 0;
	    int index = 0;
	    while (countRows < this.row)
	    {
		for (int i = 0 ; i < this.column && countRows < this.row ; i++)
		{
		    if ((increment + width + 1) <= this.docList.toString ().length () && countPrint < this.column)
		    {
			output.write (this.docList.toString ().substring (increment, increment + this.width + 1));
			increment += columnSpace [i];
			countPrint++;
			countRows++;
			if (countPrint < this.column)
			{
			    output.write ("     ");
			}
		    }
		    else
		    {
			output.newLine ();
			increment = nextRow;
			nextRow += this.width + 1;
			countPrint = 0;
			output.write (this.docList.toString ().substring (increment, increment + this.width + 1));
			increment += columnSpace [i];
			countPrint++;
			countRows++;
			if (countPrint < this.column)
			{
			    output.write ("     ");
			}
		    }
		}
	    }
	    output.close ();
	    c.println ("Check your " + this.outputFile + " file to see the results!");
	}
	catch (FileNotFoundException ex)
	{
	    System.out.println ("Unable to open file.");
	}
	catch (IOException ex)
	{
	    System.out.println ("Error reading file.");
	}
    }


    /*-------------------------------------------
    Author: Eric Zhou
    Date: Oct 16, 2018
    Purpose: To create the doc list
    Input: None
    Output: None
    -------------------------------------------*/
    public void createDoc ()
    {
	if (this.justification.equals ("r"))
	{
	    this.justifyRight ();
	}
	else if (this.justification.equals ("c"))
	{
	    this.justifyCentre ();
	}

	else if (this.justification.equals ("f"))
	{
	    this.justifyFully ();
	}
	else
	{
	    this.justifyLeft ();
	}
    }


    /*-------------------------------------------
    Author: Eric Zhou
    Date: Oct 16, 2018
    Purpose: To justify text to the left
    Input: None
    Output: None
    -------------------------------------------*/
    public void justifyLeft ()
    {
	StringBuffer currentLine = new StringBuffer ();
	StringTokenizer t = new StringTokenizer (this.wordsList);
	int linesPerColumn = this.row / this.column;
	int words = t.countTokens ();
	int countLines = 0;
	int wordsPerLine = (words / this.row);
	int wordCount = 0;

	while (t.hasMoreTokens ())
	{
	    String word = t.nextToken ();
	    int wordsLeft = t.countTokens ();

	    if (((currentLine.toString () + word + " ").length ()) <= this.width && wordCount < wordsPerLine)
	    {
		currentLine.append (word + " ");
		wordCount++;
	    }
	    else
	    {
		while (currentLine.toString ().length () != this.width)
		{
		    currentLine.insert (currentLine.toString ().length (), " ");
		}
		this.docList.append (currentLine + "\n"); // \n is 1 size
		currentLine.delete (0, currentLine.toString ().length ()); //resetting current line
		currentLine.append (word + " "); //the last word was not appended due to <= width when added.
		countLines++;
		if (countLines > this.row)
		{
		    this.row += 1;
		}
		wordCount = 0;

	    }
	}
	//adding the last sentence since last sentence never gets added.
	currentLine.delete (currentLine.toString ().length () - 1, currentLine.toString ().length ()); //removes extra space added
	while (currentLine.toString ().length () != this.width)
	{
	    currentLine.insert (currentLine.toString ().length (), " ");
	}
	this.docList.append (currentLine + "\n"); // \n is 1 size
	countLines++;
	if (countLines != this.row)
	{
	    this.row++;
	}

    }


    /*-------------------------------------------
    Author: Eric Zhou
    Date: Oct 16, 2018
    Purpose: To justify text to the right
    Input: None
    Output: None
    -------------------------------------------*/
    public void justifyRight ()
    {
	StringBuffer currentLine = new StringBuffer ();
	StringTokenizer t = new StringTokenizer (this.wordsList);
	int linesPerColumn = this.row / column;
	int words = t.countTokens ();
	int wordsPerLine = words / this.row;
	int wordCount = 0;
	int countLines = 0;

	while (t.hasMoreTokens ())
	{
	    String word = t.nextToken ();
	    if (((currentLine.toString () + word + " ").length ()) <= this.width && wordCount < wordsPerLine)
	    {
		currentLine.append (word + " ");
		wordCount++;
	    }
	    else
	    {
		while (currentLine.toString ().length () != this.width)
		{
		    currentLine.insert (0, " ");
		}
		this.docList.append (currentLine + "\n"); // \n is 1 size
		currentLine.delete (0, currentLine.toString ().length ()); //resetting current line
		currentLine.append (word + " "); //the last word was not appended due to <= width when added.
		countLines++;
		if (countLines > this.row)
		{
		    this.row++;
		}
		wordCount = 0;
	    }
	}
	//adding the last sentence since last sentence never gets added.
	currentLine.delete (currentLine.toString ().length () - 1, currentLine.toString ().length ()); //removes extra space added
	while (currentLine.toString ().length () != this.width)
	{
	    currentLine.insert (0, " ");
	}
	this.docList.append (currentLine + "\n"); // \n is 1 sizecountLine++;
	countLines++;
	if (countLines > this.row)
	{
	    this.row++;
	}
    }


    /*-------------------------------------------
    Author: Eric Zhou
    Date: Oct 16, 2018
    Purpose: To justify text to the centre
    Input: None
    Output: None
    -------------------------------------------*/
    public void justifyCentre ()
    {
	StringBuffer currentLine = new StringBuffer ();
	StringTokenizer t = new StringTokenizer (this.wordsList);
	int linesPerColumn = this.row / column;
	int words = t.countTokens ();
	int wordsPerLine = words / this.row;
	int wordCount = 0;
	int countLines = 0;

	while (t.hasMoreTokens ())
	{
	    String word = t.nextToken ();
	    if (((currentLine.toString () + word + " ").length ()) <= this.width && wordCount < wordsPerLine)
	    {
		currentLine.append (word + " ");
		wordCount++;
	    }
	    else
	    {
		while (currentLine.toString ().length () != this.width)
		{
		    currentLine.insert (0, " ");
		    if (currentLine.toString ().length () != this.width)
		    {
			currentLine.insert (currentLine.toString ().length (), " ");
		    }
		}
		this.docList.append (currentLine + "\n"); // \n is 1 size
		currentLine.delete (0, currentLine.toString ().length ()); //resetting current line
		currentLine.append (word + " "); //the last word was not appended due to <= width when added.
		countLines++;
		if (countLines > this.row)
		{
		    this.row++;
		}
		wordCount = 0;
	    }
	}
	//adding the last sentence since last sentence never gets added.
	currentLine.delete (currentLine.toString ().length () - 1, currentLine.toString ().length ()); //removes extra space added
	while (currentLine.toString ().length () != this.width)
	{
	    currentLine.insert (0, " ");
	    if (currentLine.toString ().length () != this.width)
	    {
		currentLine.insert (currentLine.toString ().length (), " ");
	    }
	}
	this.docList.append (currentLine + "\n"); // \n is 1 size
	countLines++;
	if (countLines > this.row)
	{
	    this.row++;
	}
    }


    /*-------------------------------------------
    Author: Eric Zhou
    Date: Oct 16, 2018
    Purpose: To justify text fully
    Input: None
    Output: None
    -------------------------------------------*/
    public void justifyFully ()
    {
	StringBuffer currentLine = new StringBuffer ();
	StringTokenizer t = new StringTokenizer (this.wordsList);
	int findSpace = 0; //index
	int lastSpace = 0; //index
	int linesPerColumn = this.row / column;
	int words = t.countTokens ();
	int wordsPerLine = (words / this.row);
	int wordCount = 0;
	int countLines = 0;

	while (t.hasMoreTokens ())
	{
	    String word = t.nextToken ();
	    if (((currentLine.toString () + word + " ").length ()) <= this.width && wordCount < wordsPerLine)
	    {
		currentLine.append (word + " ");
		wordCount++;
	    }
	    else
	    {
		currentLine.delete (currentLine.toString ().length () - 1, currentLine.toString ().length ()); //removes extra space added
		if (currentLine.toString ().length () <= this.width)
		{
		    while (currentLine.toString ().length () < this.width)
		    {
			findSpace = currentLine.toString ().indexOf (" ", lastSpace + 2);
			if (findSpace == -1) //no more space found after current space, reset
			{
			    findSpace = currentLine.toString ().indexOf (" ");  //first occurrence of a space
			    if (findSpace == -1) // no actual space found
			    {
				findSpace = 0;
				lastSpace = currentLine.toString ().length ();
			    }
			    else
			    {
				lastSpace = findSpace;
			    }

			}
			currentLine.insert (findSpace, " ");
			lastSpace = findSpace;
		    }
		}
		this.docList.append (currentLine + "\n");
		currentLine.delete (0, currentLine.toString ().length ()); //resetting current line
		currentLine.append (word + " "); //the last word was not appended due to <= width when added.
		countLines++;
		if (countLines > this.row)
		{
		    this.row++;
		}
		wordCount = 0;
	    }
	}
	//adding the last sentence since last sentence never gets added.
	currentLine.delete (currentLine.toString ().length () - 1, currentLine.toString ().length ()); //removes extra space added
	if (currentLine.toString ().length () <= width)
	{
	    while (currentLine.toString ().length () < width)
	    {
		findSpace = currentLine.toString ().indexOf (" ", lastSpace + 2);
		if (findSpace == -1) //no more space found, reset
		{
		    findSpace = currentLine.toString ().indexOf (" "); //first occurrence of a space
		    if (findSpace == -1) // no actual space found
		    {
			findSpace = 0;
			lastSpace = currentLine.toString ().length ();
		    }
		    else
		    {
			lastSpace = findSpace;
		    }

		}
		currentLine.insert (findSpace, " ");
		lastSpace = findSpace;
	    }
	}
	this.docList.append (currentLine + "\n");
	countLines++;
	if (countLines > this.row)
	{
	    this.row++;
	}
    }
} //class block
