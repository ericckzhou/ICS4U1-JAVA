// The "MatrixTest" class.
/*-----------------------------------------------
Author: Eric Zhou
Date: 10/23/2018
Purpose: To complete 2D exercise
Input: Keyboard
Output: Console
-------------------------------------------------*/
import java.awt.*;
import hsa.Console;
import hsa.*;
import java.io.*;
import java.util.*;

public class MatrixTest
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
	c = new Console (45, 200, "Matrix");
	boolean loop = true;
	while (loop == true)
	{
	    c.println ("------------------------------------------------------------------------------------------------");
	    c.println ("To avoid getting print errors, please do not go higher than 6 rows or 6 columns.");
	    c.println ("Thank you. ");
	    c.println ("------------------------------------------------------------------------------------------------");
	    Matrix myMatrix = new Matrix ();
	    myMatrix.get (c);
	    c.println ();
	    c.print ("SYSTEM: Select a row # to remove: ");
	    int removeRow = c.readInt ();
	    c.print ("SYSTEM: Select a column # to remove: ");
	    int removeCol = c.readInt ();
	    c.print ("SYSTEM: Select a column # to replace with random column: ");
	    int replaceCol = c.readInt ();
	    c.print ("SYSTEM: Select a column # to split: ");
	    int splitColumn = c.readInt ();
	    c.print ("SYSTEM: Select a row to print at (1-10): ");
	    int getRow = c.readInt ();
	    while (getRow > 10 || getRow < 0)
	    {
		c.println ("SYSTEM ERROR: Matrix row print is not within the range of (1-10)!");
		c.print ("SYSTEM: Choose a row to print at (1-10): ");
		getRow = c.readInt ();
	    }
	    c.print ("SYSTEM: Select a column to print at (1-30): ");
	    int getCol = c.readInt ();
	    while (getCol < 0 || getCol > 30)
	    {
		c.println ("SYSTEM ERROR: Matrix column print is not within the range of (1-30)!");
		c.print ("SYSTEM: Choose a column to print at (1-30): ");
		getCol = c.readInt ();
	    }

	    c.clear ();

	    int startRow = getRow;
	    int startCol = getCol;
	    Matrix randMatrix = new Matrix (myMatrix.rows, myMatrix.cols);
	    int countRow = 0;
	    while (countRow < randMatrix.rows)
	    {
		for (int i = 0 ; i < randMatrix.cols ; i++)
		{
		    randMatrix.element [countRow] [i] = (double) ((Math.random () * 999) + 1);
		}
		countRow++;
	    }

	    //-------------------------------
	    //Matrix Identifications
	    //-------------------------------
	    //1st Column (Left to Right)
	    //First Matrix - Original
	    //Second Matrix - Randomized
	    //Third Matrix - Added (Original + Randomized)

	    //2nd Column
	    //First Matrix - Subtracted (Original - Randomized)
	    //Second Matrix - Column Average Of Original
	    //Third Matrix - Minor Matrix of Original

	    //3rd Column
	    //First Matrix - Transpose Matrix of Original
	    //Second Matrix - Substitute Matrix
	    //Third Matrix - Split Matrix of Original

	    //4th Column
	    //First Matrix - Concatenate

	    //Determinant statement
	    //------------------------------

	    //Original
	    startCol += myMatrix.cols * 4;
	    c.setCursor (startRow, startCol);
	    c.print ("Original");
	    startCol += myMatrix.cols * 9;
	    c.setCursor (startRow, startCol);
	    c.print ("Random");
	    startCol += myMatrix.cols * 9;
	    c.setCursor (startRow, startCol);
	    c.print ("Added");

	    startCol = getCol;
	    startRow++;
	    myMatrix.print (startRow, startCol, c);
	    startCol += myMatrix.cols * 9;

	    //Randomly Generated Matrix
	    randMatrix.print (startRow, startCol, c);
	    startCol += randMatrix.cols * 9;


	    //Added
	    Matrix added = myMatrix.add (randMatrix);
	    added.print (startRow, startCol, c);
	    startCol += added.cols * 9;

	    //NextRow
	    startRow += myMatrix.rows + 1;
	    startCol = getCol + myMatrix.cols * 4;
	    c.setCursor (startRow, startCol);
	    c.print ("Subbed");
	    startCol += myMatrix.cols * 9;
	    c.setCursor (startRow, startCol);
	    c.print ("Column Average");
	    startCol += myMatrix.cols * 9;
	    c.setCursor (startRow, startCol);
	    c.print ("Minor");
	    startRow++;
	    startCol = getCol;

	    //Subtracted
	    Matrix subbed = myMatrix.subtract (randMatrix);
	    subbed.print (startRow, startCol, c);
	    startCol += myMatrix.cols * 9;


	    //Column Average
	    Matrix colAvg = myMatrix.columnAverage ();
	    colAvg.print (startRow, startCol, c);
	    startCol += myMatrix.cols * 9;

	    //Minor
	    Matrix minor = myMatrix.minor (removeRow - 1, removeCol - 1); //index of row/col = -1 of user input
	    minor.print (startRow, startCol, c);
	    startCol += myMatrix.cols * 9;

	    //NextRow
	    startRow += myMatrix.rows + 1;
	    startCol = getCol + myMatrix.cols * 4;
	    c.setCursor (startRow, startCol);
	    c.print ("Transpose");
	    startCol += myMatrix.cols * 9;
	    c.setCursor (startRow, startCol);
	    c.print ("Substitute");
	    startCol += myMatrix.cols * 9;

	    startRow++;
	    startCol = getCol;

	    //transpose
	    Matrix transposed = myMatrix.transpose ();
	    transposed.print (startRow, startCol, c);
	    startCol += myMatrix.cols * 9;

	    //Substitute
	    Matrix oneCol = new Matrix (myMatrix.rows, 1);
	    for (int i = 0 ; i < myMatrix.rows ; i++)
	    {
		oneCol.element [i] [0] = (double) (Math.random () * 10) + 1;
	    }
	    Matrix subs = myMatrix.substitute (oneCol, replaceCol - 1); //arrayNum will be 1 less
	    subs.print (startRow, startCol, c);
	    startCol += myMatrix.cols * 9;

	    //Split
	    Matrix splited = myMatrix.split (splitColumn);
	    splited.print (startRow, startCol, c);
	    c.setCursor (startRow - 1, startCol + (splited.cols * 4));
	    c.print ("Split");

	    //NextRow
	    startRow += myMatrix.rows + 1;
	    startCol = getCol + (myMatrix.cols + randMatrix.cols) * 7 / 2;
	    c.setCursor (startRow, startCol);
	    c.print ("Concatenate");
	    startRow++;
	    startCol = getCol;

	    //Concatenate
	    Matrix concatenated = myMatrix.concatenate (randMatrix);
	    concatenated.print (startRow, startCol, c);
	    startCol += myMatrix.cols * 9;


	    //Determinant
	    Matrix cloneMatrix = new Matrix ();
	    try
	    {
		cloneMatrix = (Matrix) myMatrix.clone ();
	    }
	    catch (CloneNotSupportedException ex)
	    {
	    }
	    double num = myMatrix.determinant (cloneMatrix, myMatrix.rows);
	    c.println ();
	    c.print ("SYSTEM: The determinant is: " + num);
	    c.println ();

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
} // MatrixTest class

/*-----------------------------------------------
Class: Matrix
Author: Eric Zhou
Date: 10/23/2018
Purpose: Matrix manipulation
Data elements:
    element: dynamic double 2d array
    rows: integer value of the number of rows
    cols: integer value of the number of columns
Methods:
    clone: creates a duplicate of the object.
    get: will get values from user regarding to the values of the element, rows and cols.
    print (enrichment opp): prints the matrix given a Console and a starting screen row and screen column.
    getFromFile: passed TextInputFile will read rows and cols and then the elements from the file.
    columnAverage: will return a 1xcols Matrix of the averages of each column in the Matrix.
    sameSize: returns true if a given matrix is the same size as the current one.
    square: returns true if the current matrix is a square matrix where rows = cols.
    add: add the current matrix with a given matrix.
    subtract: subtract the current matrix with a given matrix.
    minor: return a minor of the current matrix.
    *A minor matrix is the matrix with the given row and column removed.*
    transpose: return a Matrix which is the transpose of the current Matrix.
    *transpose is a new matrix whose rows = cols and cols = rows of the original.*
    determinant: will return the determinant of the current matrix.
    |a| if it is a square Matrix. Return 0 if not square.
    substitute (enrichment): will substitute one column in the current matrix with a given rowsx1 Matrix
    and the column number, producing new Matrix.
    concatenate (enrichment): will create a new Matrix given another matrix with the same number of rows.
    split (enrichment): will create a new Matrix starting at a given column;
-------------------------------------------------*/
class Matrix implements Cloneable
{
    public double[] [] element;
    public int rows;
    public int cols;

    public Matrix ()
    {
	this.rows = 0;
	this.cols = 0;
	this.element = new double [0] [0];
    }


    public Matrix (Matrix getMatrix)
    {
	this.rows = getMatrix.rows;
	this.cols = getMatrix.cols;
	this.element = new double [this.rows] [this.cols];
	int countRow = 0;
	while (countRow < this.rows)
	{
	    for (int i = 0 ; i < this.cols ; i++)
	    {
		this.element [countRow] [i] = getMatrix.element [countRow] [i];
	    }
	    countRow++;
	}
    }


    public Matrix (int getRow, int getCol)
    {
	this.rows = getRow;
	this.cols = getCol;
	this.element = new double [this.rows] [this.cols];
    }


    /*-----------------------------------------------
    Author: Eric Zhou
    Date: 10/23/2018
    Purpose: creates a duplicate of the object.
    Input: None
    Output: Cloned object
    -------------------------------------------------*/
    public Object clone () throws CloneNotSupportedException
    {
	Matrix cloned = (Matrix) super.clone (); //primitive copied
	cloned.element = new double [this.rows] [];
	//must always cast type when cloning
	for (int i = 0 ; i < this.rows ; i++)
	{
	    cloned.element [i] = (double[]) this.element [i].clone ();
	}
	return cloned;
    }


    /*-----------------------------------------------
    Author: Eric Zhou
    Date: 10/23/2018
    Purpose: will get the file's name.
    Input: Console c
    Output: None
    -------------------------------------------------*/
    public void get (Console c)
    {
	c.println ("SYSTEM: Creating User's Matrix...");
	c.print ("SYSTEM: Enter text (.txt) file name: ");
	String fileName = c.readLine ();
	fileName += ".txt";
	TextInputFile in = new TextInputFile (fileName);
	this.getFromFile (in);
    }


    /*-----------------------------------------------
    Author: Eric Zhou
    Date: 10/23/2018
    Purpose: prints the matrix given a Console and a starting screen row and screen column.
    Input: Console c
    Output: None
    -------------------------------------------------*/
    public void print (int startRow, int startCol, Console c)
    {
	int largest = 7; // -999.99 = 7 length
	int countRow = 0;
	int setCol = 0;
	StringBuffer printNum = new StringBuffer ();

	while (countRow < this.rows)
	{
	    for (int i = 0 ; i < this.cols ; i++)
	    {
		double num = this.element [countRow] [i];
		int size = Double.toString (num).length ();
		printNum.append (Double.toString (num));
		while (printNum.toString ().length () < largest)
		{
		    printNum.insert (printNum.toString ().length (), " ");
		}
		while (!(printNum.toString ().indexOf (".") == printNum.toString ().length () - 3))
		{
		    printNum.delete (printNum.toString ().length () - 1, printNum.toString ().length ()); //making it 2 decimal
		}
		//adding space if it does not equal to 6 length.
		while (printNum.toString ().length () < largest)
		{
		    printNum.insert (0, " ");
		}
		//reset
		printNum.insert (0, " "); // 8 length
		if (printNum.toString ().indexOf (" ", 7) == 7)
		{
		    printNum.insert (7, "0"); //keeping 2 decimals
		}
		c.setCursor (startRow + (countRow), startCol + (i * 8));
		c.print (printNum.toString ());
		printNum.delete (0, printNum.toString ().length ());

	    }
	    countRow++;
	    c.println (); //next row
	}
    }


    /*-----------------------------------------------
    Author: Eric Zhou
    Date: 10/23/2018
    Purpose: passed TextInputFile will read rows and cols and then the elements from the file.
    Input: TextInputFile
    Output: None
    -------------------------------------------------*/
    public void getFromFile (TextInputFile in)
    {
	int countRow = 0;
	int countCol = 0;
	this.rows = 0;
	this.cols = 0;

	boolean foundRowCol = false;
	while (!(in.eof ())) //end of file
	{
	    String line = in.readLine ();
	    StringTokenizer t = new StringTokenizer (line);
	    int count = t.countTokens ();
	    if (foundRowCol == false)
	    {
		while (t.hasMoreTokens ())
		{
		    if (this.rows == 0)
		    {
			this.rows = Integer.parseInt (t.nextToken ());
		    }
		    else
		    {
			this.cols = Integer.parseInt (t.nextToken ());
			foundRowCol = true;
			this.element = new double [this.rows] [this.cols];
		    }
		}
	    }
	    else if (foundRowCol == true)
	    {
		while (t.hasMoreTokens ())
		{

		    this.element [countRow] [countCol] = Double.parseDouble (t.nextToken ());
		    countCol++;

		}
		while (countCol < this.cols)
		{
		    this.element [countRow] [countCol] = 0; //filled with 0
		    countCol++;
		}
		if (countCol == this.cols)
		{
		    countCol = 0;
		    countRow++;
		}
	    }
	}
	in.close ();

    }


    /*-----------------------------------------------
    Author: Eric Zhou
    Date: 10/23/2018
    Purpose: will return a 1xcols Matrix of the averages of each column in the Matrix.
    Input: None
    Output:  Matrix 1xcol average
    -------------------------------------------------*/
    public Matrix columnAverage ()
    {
	//return average for ea column
	int currentCol = 0; //first col
	double sum = 0;
	Matrix colAvg = new Matrix (1, this.cols);
	while (currentCol < this.cols)
	{
	    for (int i = 0 ; i < this.rows ; i++)
	    {
		sum += this.element [i] [currentCol];
	    }
	    colAvg.element [0] [currentCol] = sum / this.rows;
	    sum = 0;
	    currentCol++;
	}

	return colAvg;
    }


    /*-----------------------------------------------
    Author: Eric Zhou
    Date: 10/23/2018
    Purpose: returns true if a given matrix is the same size as the current one.
    Input: Another Matrix
    Output: Boolean
    -------------------------------------------------*/
    public boolean sameSize (Matrix matrix)
    {
	boolean sameMatrix = true;
	if (this.rows != matrix.rows)
	{
	    sameMatrix = false;
	}
	else if (this.cols != matrix.cols)
	{
	    sameMatrix = false;
	}
	return sameMatrix;

    }


    /*-----------------------------------------------
    Author: Eric Zhou
    Date: 10/23/2018
    Purpose: returns true if the current matrix is a square matrix where rows = cols.
    Input: None
    Output: Boolean
    -------------------------------------------------*/
    public boolean square ()
    {
	boolean isSquare = false;
	if (this.rows == this.cols)
	{
	    isSquare = true;
	}
	return isSquare;
    }


    /*-----------------------------------------------
    Author: Eric Zhou
    Date: 10/23/2018
    Purpose: add the current matrix with a given matrix.
    Input: Matrix x
    Output: New added Matrix
    -------------------------------------------------*/
    public Matrix add (Matrix matrix)
    {
	Matrix added = new Matrix ();
	try
	{
	    added = (Matrix) this.clone ();
	    int countRow = 0;
	    boolean sizeSame = this.sameSize (matrix);
	    if (sizeSame == true)
	    {
		while (countRow < this.rows)
		{
		    //assuming same size
		    for (int i = 0 ; i < this.cols ; i++)
		    {
			if ((added.element [countRow] [i] + matrix.element [countRow] [i]) > 1000 || (added.element [countRow] [i] + matrix.element [countRow] [i]) < -1000)
			{
			    added.element [countRow] [i] = 0;
			}
			else
			{
			    added.element [countRow] [i] += matrix.element [countRow] [i];

			}

		    }
		    countRow++;
		}
	    }
	    if (sizeSame == false)
	    {
		while (countRow < this.rows)
		{
		    for (int i = 0 ; i < added.cols ; i++)
		    {
			added.element [countRow] [i] = 0;
		    }
		    countRow++;
		}
	    }
	}
	catch (CloneNotSupportedException ex)
	{
	    System.out.println ("SYSTEM: The current Matrix cannot be cloned!");
	}
	return added;
    }


    /*-----------------------------------------------
    Author: Eric Zhou
    Date: 10/23/2018
    Purpose: subtract the current matrix with a given matrix.
    Input: Matrix x
    Output: New subtracted Matrix
    -------------------------------------------------*/
    public Matrix subtract (Matrix matrix)
    {
	Matrix subbed = new Matrix ();
	try
	{
	    subbed = (Matrix) this.clone ();
	    boolean sizeSame = this.sameSize (matrix);
	    int countRow = 0;
	    if (sizeSame == true)
	    {
		while (countRow < this.rows)
		{
		    //assuming same size
		    for (int i = 0 ; i < this.cols ; i++)
		    {
			if ((subbed.element [countRow] [i] - matrix.element [countRow] [i]) > 1000 || (subbed.element [countRow] [i] - matrix.element [countRow] [i]) < -1000)
			{
			    subbed.element [countRow] [i] += 0;
			}
			else
			{
			    subbed.element [countRow] [i] -= matrix.element [countRow] [i];
			}

		    }
		    countRow++;
		}
	    }
	    if (sizeSame == false)
	    {
		while (countRow < this.rows)
		{
		    for (int i = 0 ; i < subbed.cols ; i++)
		    {
			subbed.element [countRow] [i] = 0;
		    }
		    countRow++;
		}
	    }
	}
	catch (CloneNotSupportedException ex)
	{
	    System.out.println ("SYSTEM: The current Matrix cannot be cloned!");
	}
	return subbed;
    }


    /*-----------------------------------------------
    Author: Eric Zhou
    Date: 10/23/2018
    Purpose: return a minor of the current matrix given a row, and column to remove.
    Input: Two integers, a row and column number to remove.
    Output: New Matrix
    -------------------------------------------------*/
    public Matrix minor (int removeRow, int removeCol)
    {
	//check if there is that number of row and column!
	Matrix minorMatrix = new Matrix (this.rows - 1, this.cols - 1); //if any = 1, minor = 0;
	boolean valid = true;
	int minusRow = 0; //minus 1 from row
	int minusCol = 0; //minus 1 from col
	int countRow = 0;
	if (removeRow < 0 || removeRow >= this.rows || removeCol < 0 || removeCol >= this.cols) //when inputting, i minused 1
	{
	    valid = false;
	}
	while (countRow < this.rows && valid == true) //go over the whole rows
	{
	    if (countRow == removeRow)
	    {
		countRow++;
		minusRow = 1;
	    }
	    else
	    {
		for (int i = 0 ; i < this.cols ; i++)
		{
		    if (i == removeCol)
		    {
			minusCol = 1;
		    }
		    else
		    {
			minorMatrix.element [countRow - minusRow] [i - minusCol] = this.element [countRow] [i];
		    }
		}
		minusCol = 0;
		if (countRow < this.rows)
		{
		    countRow++;
		}
	    }
	}
	if (valid == false)
	{
	    for (int r = 0 ; r < minorMatrix.rows ; r++)
	    {
		for (int c = 0 ; c < minorMatrix.cols ; c++)
		{
		    minorMatrix.element [r] [c] = 0;
		}
	    }
	}
	return minorMatrix;
    }


    /*-----------------------------------------------
    Author: Eric Zhou
    Date: 10/23/2018
    Purpose: return a Matrix which is the transpose of the current Matrix.
    Input: None
    Output: New Matrix
    -------------------------------------------------*/
    public Matrix transpose ()
    {
	//newRow = original col, newCol = original row
	Matrix transposed = new Matrix (this.cols, this.rows);
	int countRow = 0;
	while (countRow < transposed.rows)
	{
	    for (int i = 0 ; i < transposed.cols ; i++)
	    {
		transposed.element [countRow] [i] = this.element [i] [countRow];
	    }
	    countRow++;
	}

	return transposed;

    }


    /*-----------------------------------------------
    Author: Eric Zhou
    Date: 10/23/2018
    Purpose: will return the determinant of the current matrix.
    Input: Matrix (clone of the current), n value (this.rows)
    Output: integer value
    -------------------------------------------------*/
    public double determinant (Matrix matrix, int n)
    {
	boolean isSquare = this.square ();
	double sum = 0;
	int s = 1;
	if (isSquare == true && n > 1)
	{
	    if (n == 2)
	    {
		sum += ((matrix.element [0] [0] * matrix.element [1] [1]) - (matrix.element [1] [0] * matrix.element [0] [1]));
	    }
	    else if (n == 3)
	    {
		for (int j = 0 ; j < n ; j++)
		{
		    Matrix minored = matrix.minor (0, j);
		    double a = matrix.element [0] [j];
		    sum += s * a * ((minored.element [0] [0] * minored.element [1] [1]) - (minored.element [0] [1] * minored.element [1] [0]));
		    s *= -1;
		}
	    }
	    else
	    {
		for (int j = 0 ; j < n ; j++)
		{
		    Matrix minored = matrix.minor (0, j);
		    double a = matrix.element [0] [j];
		    sum += (s * a * (determinant (minored, n - 1)));
		    s *= -1;
		}
	    }

	}
	return sum;

    }


    /*-----------------------------------------------
    Author: Eric Zhou
    Date: 10/23/2018
    Purpose: will substitute one column in the current matrix with a given rowsx1 Matrix
    and the column number, producing new Matrix.
    Input: Another Matrix
    Output: new Matrix
    -------------------------------------------------*/
    public Matrix substitute (Matrix matrix, int colNum)
    {
	//matrix rowsx1
	Matrix subs = new Matrix ();
	try
	{
	    subs = (Matrix) this.clone ();
	    boolean valid = true;
	    int countRow = 0;
	    if (colNum >= this.cols)
	    {
		valid = false;
	    }
	    while (countRow < subs.rows && valid == true)
	    {
		subs.element [countRow] [colNum] = matrix.element [countRow] [0];
		countRow++;
	    }
	    if (valid == false)
	    {
		for (int r = 0 ; r < this.rows ; r++)
		{
		    for (int c = 0 ; c < this.cols ; c++)
		    {
			subs.element [r] [c] = 0;
		    }
		}
	    }
	}
	catch (CloneNotSupportedException ex)
	{
	    System.out.println ("SYSTEM: The current Matrix cannot be cloned!");
	}
	return subs;
    }


    /*-----------------------------------------------
    Author: Eric Zhou
    Date: 10/23/2018
    Purpose: will create a new Matrix given another matrix with the same number of rows.
    Input: Another Matrix
    Output: new Matrix
    -------------------------------------------------*/
    public Matrix concatenate (Matrix matrix)
    {
	Matrix newMatrix = new Matrix (this.rows, (this.cols + matrix.cols));
	int countRow = 0;
	boolean valid = true;
	if (this.rows != matrix.rows)
	{
	    valid = false;
	}
	while (countRow < this.rows && valid == true)
	{
	    for (int i = 0 ; i < newMatrix.cols ; i++)
	    {
		if (i < this.cols)
		{
		    newMatrix.element [countRow] [i] = this.element [countRow] [i];
		}
		else
		{
		    newMatrix.element [countRow] [i] = matrix.element [countRow] [(i - this.cols)];
		}
	    }
	    countRow++;
	}
	return newMatrix;
    }


    /*-----------------------------------------------
    Author: Eric Zhou
    Date: 10/23/2018
    Purpose: will create a new Matrix starting at a given column;
    Input: integer column
    Output: new Matrix
    -------------------------------------------------*/
    public Matrix split (int getColumn)
    {
	Matrix splitMatrix = new Matrix ();
	try
	{
	    splitMatrix = (Matrix) this.clone (); //just wanna return 0s

	}
	catch (CloneNotSupportedException ex)
	{
	    System.out.println ("Could not be cloned!");
	}
	boolean valid = true;
	if (getColumn >= this.cols || getColumn < 0) //cannot split at last column (nothing)
	{
	    valid = false;
	}
	if (valid == false)
	{
	    for (int r = 0 ; r < splitMatrix.rows ; r++)
	    {
		for (int c = 0 ; c < splitMatrix.cols ; c++)
		{
		    splitMatrix.element [r] [c] = 0;
		}
	    }
	}
	if (valid == true)
	{
	    int countRow = 0;
	    splitMatrix = new Matrix (this.rows, (this.cols - getColumn));
	    while (countRow < this.rows)
	    {
		for (int i = getColumn, index = 0 ; i < this.cols ; i++, index++)
		{
		    splitMatrix.element [countRow] [index] = this.element [countRow] [i];
		}
		countRow++;
	    }
	}
	return splitMatrix;
    }
} //Matrices class
