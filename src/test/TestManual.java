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

import driver.*;

public class TestManual {
	Manual man;
	FileSystem fs;

	/**
	 * Sets up for the tests.
	 */
	@Before
	public void setUp(){
		this.fs = FileSystem.createFileSystemInstance();
		this.man = new Manual();
	}  
	
	/**
	 * Tests if the user does not enter any argument.
	 */
	@Test (expected = TooFewArgumentsException.class)
	public void testManualNoArguments() {
	  this.man.executeCommand("");  
	  }	

	/**
	 * Tests if the user enters more than one argument.
	 */	
	  @Test(expected = TooManyArgumentsException.class)  
	  public void testManualMultipleArguments() { 
	    this.man.executeCommand("mkdir cd");
	    // executed man command but entered more than one argument
	    assertEquals("Error: The 'manual' command needs 1 argument. You've entered "
	        + "2 arguments. \n", fs.getOutput());
	    // checked to see if expected value is actual value
	  }
	  
	    /**
		 * Tests if the user enters an invalid argument.
		 */
		 
	  @Test (expected = InvalidArgumentException.class)
	  public void testManuaInvalidArguments() { 
	    this.man.executeCommand("mdr");
	  }	  
	  
	    /**
		 * Tests if the user enters exit command as an argument.
		 */		  
	  @Test 
	  public void testManualExit() {
	    this.man.executeCommand("exit");
	    // executed man command with exit command
	    assertEquals("Quits the program.", fs.getOutput());
	  }

	    /**
		 * Tests if the user enters mkdir command as an argument.
		 */		  
	  
	  @Test
	  public void testManualMakeDirectory() {
	    man.executeCommand("mkdir");
	    assertEquals("Creates a directory", fs.getOutput());
	  }	  
	    /**
		 * Tests if the user enters cd command as an argument.
		 */		  
	  @Test
	  public void testManualChangeDirectory() {
	    man.executeCommand("cd");
	    assertEquals("Changes the current working directory to the given directory "
	        + "name.", fs.getOutput());
	  }	
	    /**
		 * Tests if the user enters ls command as an argument.
		 */		  
	  @Test 
	  public void testManualListConent() {
	    man.executeCommand("ls");
	    assertEquals("If there is no path given, then a list of the contents of the"
	        + " current working directory is given." +  
	        "If there is a path given, then it prints a list of the contents of the"
	        + " specified directory.", fs.getOutput());
	  }	  
	    /**
		 * Tests if the user enters pwd command as an argument.
		 */		 	  
	  @Test 
	  public void testManualPrintWorkingDirectory() {
	    man.executeCommand("pwd");
	    assertEquals("Print current directory path;", fs.getOutput());
	  }
	  
	    /**
		 * Tests if the user enters pushd command as an argument.
		 */		

	  @Test 
	  public void testManualPushDirectory() {
	    man.executeCommand("pushd");
	    assertEquals("Saves the current directory specified;", fs.getOutput());
	  }	
	  
	    /**
		 * Tests if the user enters popd command as an argument.
		 */	
	  @Test 
	  public void testManualPopDirectory() { 
	    man.executeCommand("popd");
	    assertEquals("Deletes the latest saved directory.", fs.getOutput());
	  }
	    /**
		 * Tests if the user enters history command as an argument.
		 */	
	  
	  @Test 
	  public void testManualHistory() {
	    man.executeCommand("history");
	    assertEquals("Prints out the previous commands entered.The number specified"
	        + " is the number of previous commands printed started from the latest "
	        + "command entered.", fs.getOutput());
	  }	  	
	    /**
		 * Tests if the user enters cat command as an argument.
		 */	
	  @Test 
	  public void testManualDisplayContent() { 
	    man.executeCommand("cat");
	    assertEquals("Displays the contents of the file on the shell."
	    		+ "If there is more then one file then the contents of"
	    		+ " all the files on the shell", fs.getOutput());
	  }	  
	  
	    /**
		 * Tests if the user enters echo command as an argument.
		 */		  
	  @Test 
	  public void testManualEcho() {
	    man.executeCommand("echo");
	    assertEquals("If there is no file name given, then the string given is "
	        + "printed on the shell." + "The file consists the given string.", 
	        fs.getOutput());
	  }	  
	  
	    /**
		 * Tests if the user enters man command as an argument.
		 */		
	  @Test 
	  public void testManualMan() { 
	    man.executeCommand("man");
	    assertEquals("Formats and displays the annual pages on the given command."
	        , fs.getOutput());
	  }	  
	  
	  /**
	   * Tests if the user enters grep command as an argument.
	   */
	  @Test
	  public void testManualGrep(){
		  man.executeCommand("grep");
		  assertEquals("Prints any lines containing REGEX in PATH, which"
    		+ "must be a file. ", fs.getOutput());
	  }
	  
	  /**
	   * Tests if the user enters grep command as an argument.
	   */
	  @Test
	  public void testManualNumber() {
		  man.executeCommand("!number");
		  assertEquals("Recalls and executes the command that occured"
    		+ "at the given history number", fs.getOutput());
	  }
	  
	  /**
	   * Tests if the user enters get command as an argument.
	   */	  
	  @Test
	  public void testManualGet() {
		  man.executeCommand("get");
		  assertEquals("Retrieves the file at the given URL address.", 
				  fs.getOutput());
	  }
	  /**
	   * Tests if the user enters cp command as an argument.
	   */	
	  @Test
	  public void testManualCP() {
		  man.executeCommand("cp");
		  assertEquals("Copy the contents of the given oldpath and add it to"
		    		+ "the given new path. The contents are copied "
		    		+ "recursively.", fs.getOutput());
	  }	  
}


