// The "Crypto" class.
/* -----------------------------------------------------------------------------------------------
Author: Eric Zhou
Date: Oct 19, 2018
Purpose: To encrypt and decrypt lines from a certain text file and return new files with different extensions.
Input: Keyboard
Output: Console
-------------------------------------------------------------------------------------------------- */
import java.awt.*;
import hsa.Console;
import java.io.*;
import java.util.*;

public class Crypto
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
	c = new Console ();
	boolean loop = true;
	while (loop == true)
	{
	    //getting key
	    c.print ("What KEY would you like: ");
	    String key = c.readString ();
	    Vigenere sub = new Vigenere (key);
	    
	    //getting txt file to encrypt
	    c.print ("What file would you like to encrypt (.txt file): ");
	    String fileName = c.readString ();
	    
	    //Encrypt
	    c.println();
	    String cypFile = sub.encrypt (fileName);
	    c.println("The " + fileName +".txt file has been encrypted!");
	    c.println("Check your " + cypFile + " file!");
	    c.println();
	    
	    //Getting encrypted file
	    c.print ("What file would you like to decrypt (.cyp file): ");
	    String encryptedFile = c.readString ();
	    
	    //Decrypt
	    c.println();
	    String plnFile = sub.decrypt (encryptedFile);
	    c.println("The " + encryptedFile + ".cyp file has been decrypted!");
	    c.println("Check your " + plnFile  + " file!");
	    
	    c.println();
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
} // Crypto class

/* -----------------------------------------------------------------------------------------------
Author: Eric Zhou
Date: Oct 19, 2018
Purpose: To create a Vigenere class that would encrypt and decrypt texts with a certain key.
Data Elements:
    key: holds a String key.
Methods:
    encrypt: Reads a .txt file and sends the line to encryptLine to encrypt the line. Returns file name + extension
    encryptLine: Returns the encrypted line back to encrypt, it's caller.
    decrypt: Reads a .cyp file and sends the line of encrypted lines to decryptLine to decrypt it. Returns file name + extension
    decryptLine: Returns the decrypted line back to decrypt, it's caller.
-------------------------------------------------------------------------------------------------- */
class Vigenere
{
    public String key;

    public Vigenere (String getKey)
    {
	this.key = getKey;
    }
    
    public Vigenere() 
    {
	this.key = "ics4u"; //defaults key to ics4u
    }


    /* -----------------------------------------------------------------------------------------------
    Author: Eric Zhou
    Date: Oct 19, 2018
    Purpose: Reads a .txt file and sends the line to encryptLine to encrypt the line.
    Input: fileName (.txt file)
    Output: fileName + extension (string)
    -------------------------------------------------------------------------------------------------- */
    public String encrypt (String fileName)
    {
	String line = null;
	try
	{
	    FileReader fileReader = new FileReader (fileName + ".txt");
	    BufferedReader in = new BufferedReader (fileReader);
	    BufferedWriter out = new BufferedWriter (new FileWriter (fileName + ".cyp"));

	    while ((line = in.readLine ()) != null)
	    {
		String encrypted = this.encryptLine (line);
		out.write (encrypted);
		out.newLine();
	    }
	    in.close();
	    out.close ();
	}
	catch (FileNotFoundException ex)
	{
	    System.out.println ("Unable to open file.");
	}
	catch (IOException ex)
	{
	    System.out.println ("Error reading file.");
	}
	String cypFile = fileName + ".cyp";
	return cypFile;

    }


    /* -----------------------------------------------------------------------------------------------
    Author: Eric Zhou
    Date: Oct 19, 2018
    Purpose: Returns the encrypted line back to encrypt, it's caller.
    Input: A line of text from the .txt file.
    Output: A String Encrypted Line
    -------------------------------------------------------------------------------------------------- */
    public String encryptLine (String line)
    {
	int size = line.length ();
	int keyIndex = 0;
	String encrypted = "";
	for (int i = 0 ; i < size ; i++)
	{
	    char letter = line.charAt (i);
	    char keyNum = key.charAt (keyIndex);
	    int offset = (int) keyNum - 32;
	    int result = (int) letter + offset;
	    if (result > 126)
	    {
		result += (-126 + 31);
	    }
	    keyIndex++;
	    encrypted += (char) result;
	    if (keyIndex == key.length ())
	    {
		keyIndex = 0;
	    }
	}

	return encrypted;

    }


    /* -----------------------------------------------------------------------------------------------
    Author: Eric Zhou
    Date: Oct 19, 2018
    Purpose: Reads a .cyp file and sends the line of encrypted lines to decryptLine to decrypt it.
    Input: fileName (.txt file)
    Output: fileName + extension (string)
    -------------------------------------------------------------------------------------------------- */
    public String decrypt (String fileName)
    {
	String line = null;
	try
	{
	    FileReader fileReader = new FileReader (fileName + ".cyp");
	    BufferedReader in = new BufferedReader (fileReader);
	    BufferedWriter out = new BufferedWriter (new FileWriter (fileName + ".pln"));

	    while ((line = in.readLine ()) != null)
	    {
		String decrypted = this.decryptLine (line);
		out.write (decrypted);
		out.newLine();
	    }
	    in.close();
	    out.close ();
	}
	catch (FileNotFoundException ex)
	{
	    System.out.println ("Unable to open file.");
	}
	catch (IOException ex)
	{
	    System.out.println ("Error reading file.");
	}
	String plnFile = fileName + ".pln";
	return plnFile;
    }


    /* -----------------------------------------------------------------------------------------------
    Author: Eric Zhou
    Date: Oct 19, 2018
    Purpose:  Returns the decrypted line back to decrypt, it's caller.
    Input: A line of encrypted text
    Output: Decrypted text (string)
    -------------------------------------------------------------------------------------------------- */
    public String decryptLine (String line)
    {
	int size = line.length ();
	int keyIndex = 0;
	String decrypted = "";
	for (int i = 0 ; i < size ; i++)
	{
	    char letter = line.charAt (i);
	    char keyNum = key.charAt (keyIndex);
	    int offset = (int) keyNum - 32;
	    int result = (int) letter - offset;
	    if (result < 32)
	    {
		result += (126 - 31);
	    }
	    keyIndex++;
	    decrypted += (char) result;
	    if (keyIndex == key.length ())
	    {
		keyIndex = 0;
	    }
	}

	return decrypted;
    }
}
