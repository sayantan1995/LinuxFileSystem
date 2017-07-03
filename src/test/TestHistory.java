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

import driver.*;
import org.junit.Test;

public class TestHistory {
	
	FileSystem fs;
	History hs;
	
	/**
	 * Sets up for the tests.
	 */
	@Before
	public void setUp(){
		this.fs = FileSystem.createFileSystemInstance();
		this.hs = new History();
	}	
	
	/**
	 * Ensures that the history command gives a TooManyArgumentsException when
	 *  given more than 1 argument.
	 */
	@Test(expected = TooManyArgumentsException.class)
	public void testTooManyArguments(){
		this.hs.executeCommand("arg1 arg2 arg3");	
	}
	
	/**
	 * Tests that the command correctly executes.
	 */	
	@Test
	  public void testOneArgument() { 
		  fs.addToHistory("pwd");
		  fs.addToHistory("popd");	  
		  hs.executeCommand("1");
		  assertEquals("2 popd", fs.getOutput()); 
	  }
	
	/**
	 * Tests that the command correctly executes with test 0 as an optional
	 * argument.
	 */		
	  @Test 
	  public void testZeroArgument() { 
	    fs.addToHistory("pwd");
	    fs.addToHistory("popd");
	    assertEquals("",fs.getOutput()); 
	  }
	
 }  

