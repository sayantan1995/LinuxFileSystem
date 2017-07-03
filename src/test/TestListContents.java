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

public class TestListContents{
	
	FileSystem fs;
	ListContent ls;
	MakeDirectory mkdir;
	/**
	 * Sets up for the tests.
	 */
	@Before
	public void setUp(){
		this.fs = FileSystem.createFileSystemInstance();
		this.ls = new ListContent();
	} 	
	
	@Test (expected = InvalidArgumentException.class)
	public void testListContentsWithIncorrectPath() {
	    //new fileSystem
	    ls.executeCommand("/error");
	    // executed ls command but did not enter any argument
	    assertEquals("Error: The 'ListContents' command requires that the path exists. You've entered "
	        + "a non-existent path. \n", fs.getOutput()); 	 
	}

	
	@Test (expected = InvalidArgumentException.class)//Checks to see if the program works correctly on an empty directory.
	public void testListContentsInvalidArguement() {
	    //Creates a new fileSystem
	    ls.executeCommand("ls once");
	    assertEquals("Error: The 'ls' command needs atleast 1 argument. You've entered "
	            + "an incorrect argument. \n"
	            , fs.getOutput());
	        // Checks to see if expected value is actual value
	    this.ls.executeCommand("ls once");
	    
	}
	
	@Test (expected = InvalidArgumentException.class)	  
	public void testListContentsExit() {
	    ls.executeCommand("exit");
	    this.ls.executeCommand("exit");
	    // executed man command with exit command
	    assertEquals("Quits the program.\n", this.fs.getOutput());
	    // Checks to see if expected value is actual value		
	}
	
	@Test (expected = InvalidPathException.class)
	public void testListContentsOnExistingPath() {
	    //Creates a new fileSystem
	    fs.setWd("/folder1/folder2/file1");
	    ls.executeCommand("ls folder1");
	    assertEquals("", fs.getOutput());
	}
	
	@Test (expected = InvalidArgumentException.class)
	public void testListContentsOnCurrentDirectory() { 
	    //Creates a new fileSystem
	    ls.executeCommand("ls c");	
	    mkdir.executeCommand("a");
	    assertEquals("Checks the contents of the current directory", fs.getOutput());	    
	    this.fs.setWd("/folder1/folder2/file1");
	    this.ls.executeCommand("ls folder1");
	    assertEquals("", this.fs.getOutput());
	}
	@Test (expected = InvalidArgumentException.class)//Checks to see if the program works correctly on an empty directory.
    public void testInvalidArguement() {
        this.ls.executeCommand("ls once@@$@!");
   }
}