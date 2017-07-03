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

import driver.DisplayContents;
import driver.FileSystem;
import driver.InvalidArgumentException;
import driver.TooFewArgumentsException;

/**
 * Tests the echo class.
 */
public class TestDisplayContents {
	
	FileSystem fs;
	DisplayContents cat;
	
	/**
	 * Sets up for the tests.
	 */
	@Before
	public void setUp(){
		this.fs = FileSystem.createFileSystemInstance();
		this.cat = new DisplayContents();
	}

	/**
	 * Ensures that the cat command gives a TooFewArgumentsException when no arguments are given.
	 */
	@Test(expected = TooFewArgumentsException.class)
	public void testNoArguments(){
		this.cat.executeCommand("");
	}
	
	/**
	 * Ensures that the cat command gives a TooManyArgumentsException when given more than 1 argument.
	 */
	@Test(expected = TooFewArgumentsException.class)
	public void testTooFewArguments(){
		this.cat.executeCommand("");	
	}
	/**
	 * Ensures that the cat command gives the invalid arg exception.
	 */
	@Test(expected = InvalidArgumentException.class)
	public void testValidArguments(){
		this.cat.executeCommand("arg");
		
	}

	@Test (expected = InvalidArgumentException.class) 
	  public void testDisplayContentWithManyArguments() {
	    cat.executeCommand("movies tv"); 
	    //output should be an error since only one argument is needed
	    assertEquals("movies: no such file"
	            + "\ntv: no such file", fs.getOutput()); 
	    // checked to see if expected value is actual value
	    
	  }
	  
	@Test (expected = InvalidArgumentException.class)
	  public void testDisplayContentWithInvalidArgument() {
	    cat.executeCommand("$@"); 
	    //output should be an error since a valid argument is needed
	    assertEquals("Error: '$@' is an invalid path. Characters including "
	        + "\"!@$&*()?:[]\"<>'`|={}\\/\" are not allowed in paths.\n"
	        , fs.getOutput());     
	 }
	  
	@Test (expected = InvalidArgumentException.class)
	  public void testDisplayContentNoFile() {
	      cat.executeCommand("hello");
	      assertEquals("hello: no such file", fs.getOutput());
	  }
	  
	}

		