// **********************************************************
// Assignment2:
// Student1: Sayantan Chattopadhyay
// UTOR user_name:chatto14
// UT Student #:1000818145
// Author:Sayantan Chattopadhyay
//
// Student2: Leah Furyk
// UTOR user_name: furyklea
// UT Student #: 1001308373
// Author: Leah Furyk
//
// Student3: Shrey Jain
// UTOR user_name: jainshre
// UT Student #: 999835558
// Author: Shrey Jain
//
// Student4: Anandi Patel
// UTOR user_name: patela65
// UT Student #: 1001558897
// Author: Anandi Patel
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC 207 and understand the consequences.
// *********************************************************

package test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import driver.Echo;
import driver.FileSystem;
import driver.InvalidArgumentException;
import driver.TooFewArgumentsException;
import driver.TooManyArgumentsException;

/**
 * Tests the echo class.
 */
public class TestEcho {
	
	FileSystem fs;
	Echo echo;
	
	/**
	 * Sets up for the tests.
	 */
	@Before
	public void setUp(){
		this.fs = FileSystem.createFileSystemInstance();
		this.echo = new Echo();
	}

	/**
	 * Ensures that the echo command gives a TooFewArgumentsException when no 
	 * arguments are given.
	 */
	@Test(expected = TooFewArgumentsException.class)
	public void testNoArguments(){
		this.echo.executeCommand("");

	}
	
	/**
	 * Ensures that the echo command gives a TooManyArgumentsException when given more than 1 argument.
	 */
	@Test(expected = TooManyArgumentsException.class)
	public void testTooManyArguments(){
		this.echo.executeCommand("arg1 arg2 arg3");	
	}
	
	/**
	 * Ensures that the echo command gives an InvalidArgumentException when presented with argument that is not in quotes.
	 */
	@Test(expected = InvalidArgumentException.class)


	public void testInvalidArgument(){
		this.echo.executeCommand("argnotinquotes");	
	}
	
	/**
	 * Ensures that the echo command gives the correct output when presented with valid arguments.
	 */
	@Test
	public void testValidArguments(){
		this.echo.executeCommand("\"arginquotes\"");
		assertEquals("arginquotes", this.fs.getOutput());
		
		this.echo.executeCommand("\"  lots of spaces\"");
		assertEquals("  lots of spaces", this.fs.getOutput());
		
		this.echo.executeCommand("\"   spaces   on   both   sides   \"");
		assertEquals("   spaces   on   both   sides   ", this.fs.getOutput());
		
		this.echo.executeCommand("\"   wooooo, this definately works...even with punctuation!! ahhhhh    \"");
		assertEquals("   wooooo, this definately works...even with punctuation!! ahhhhh    ", this.fs.getOutput());
	}

}
