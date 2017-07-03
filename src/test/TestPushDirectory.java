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

import driver.PushDirectory;
import driver.TooFewArgumentsException;
import driver.TooManyArgumentsException;
import driver.FileSystem;
import driver.IllegalPathCharactersException;
import driver.MakeDirectory;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestPushDirectory {
  
  FileSystem fs;
  PushDirectory pushd;
  
	/**
	 * Sets up for the tests.
	 */
	@Before
	public void setUp(){
		this.fs = FileSystem.createFileSystemInstance();
		this.pushd = new PushDirectory();
	} 
	
	/**
	  * Ensures that the pushd command gives a TooFewArgumentsException when no arguments are given.
	   */

	@Test (expected = TooFewArgumentsException.class)
    public void testPushWithNoArgument() {
	  pushd.executeCommand(""); 
      //output should be an error since an argument is needed
      assertEquals("Error: The 'pushd' command requires 1 argument. "
          + "You've entered 0 argument(s).\n", fs.getOutput()); 
      // checked to see if expected value is actual value
    
  }
	/**
	   * Ensures that the pushd command gives a TooManyArgumentsException when more than 1 arguments are given.
	   */
  
	@Test (expected = TooManyArgumentsException.class) 
    public void testPushWithManyArguments() {
	  pushd.executeCommand("movies tv"); 
	  //output should be an error since only one argument is needed
      assertEquals("Error: The 'pushd' command requires 1 argument. "
          + "You've entered 2 argument(s).\n", fs.getOutput()); 
      // checked to see if expected value is actual value
    
  }
	/**
	   * Ensures that the pushd command gives a IllegalPathCharactersException when argument with illegal characters are given.
	   */
  
	@Test (expected = IllegalPathCharactersException.class)
    public void testPushWithInvalidArgument() {
	  //new fileSystem
      pushd.executeCommand("$@"); 
      //output should be an error since a valid argument is needed
      assertEquals("Error: '$@' is an invalid path. Characters including "
          + "\"!@$&*()?:[]\"<>'`|={}\\/\" are not allowed in paths.\n"
          , fs.getOutput()); 
      // checked to see if expected value is actual value
    
  }
 

  /**
   * Ensures that the pushd command gives the correct output when presented with valid arguments.
   */
 
    @Test //test if proper argument is entered 
    public void testPushWithCorrectArgument1() {
      MakeDirectory dir = new MakeDirectory();
      dir.executeCommand("folder2");
      pushd.executeCommand("folder2"); 
      assertEquals("",fs.getOutput()); 
      // checked to see if expected value is actual value
    
  }   
  /**
   * Ensures that the pushd command gives the correct output when presented with two arguments.
   */
 
    @Test //test if proper argument is entered 
    public void testPushWithCorrectArgument2() {
      MakeDirectory dir = new MakeDirectory();
      dir.executeCommand("folder1 folder2");
      pushd.executeCommand("folder2"); 
      assertEquals("",fs.getOutput()); 
      // checked to see if expected value is actual value
    
  }  
  
  
}
