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

public class TestChangeDirectory {
	
	FileSystem fs;
	ChangeDirectory cd;
	MakeDirectory mkdir;
	PushDirectory pushd;
	@Before
    public void setUp(){
        this.fs = FileSystem.createFileSystemInstance();
        this.cd = new ChangeDirectory();
        this.mkdir = new MakeDirectory();
        this.pushd = new PushDirectory();
    }
	/**
	   * Ensures that the cd command gives an InvalidPathException when no invalid path is given.
	   */
	
	@Test (expected = InvalidPathException.class)
	public void testChangeDirectoryWithManyArguments() {
	    fs.setWd("/folder1/folder2/file1");
	    cd.executeCommand("cd folder10");
	    assertEquals("Error: The 'cd' command can only have 1 command. "
	    		+ "You've entered 2 arguments.\n", fs.getOutput());   		
	}
	/**
	   * Ensures that the cp command gives an IllegalPathCharacterException when an invalid character is used in a path.
	   */
	
	 @Test (expected = IllegalPathCharactersException.class)
	  public void testChangeDirectoryWithInvalidArgument() {
	    cd.executeCommand("$@"); 
	    //output should be an error since a valid argument is needed
	    assertEquals("Error: '$@' is an invalid path. Characters including "
	        + "\"!@$&*()?:[]\"<>'`|={}\\/\" are not allowed in paths.\n"
	        , fs.getOutput()); 
	    // checked to see if expected value is actual value
	    
	  }
	 /**
	   * Ensures that the cp command works properly when correct arguments are given.
	   */
	@Test
	public void testChangeToParentDirectory() {
	  mkdir.executeCommand("Showbiz");
      pushd.executeCommand("Showbiz");
      mkdir.executeCommand("Movies");
      pushd.executeCommand("Movies");
	  cd.executeCommand("..");
	  assertEquals("/Showbiz", fs.getWd());
	// check to see if expected value is actual value
	}
	/**
	   * Ensures that the cp command gives a InvalidPathException when no invalid path is given.
	   */
	@Test (expected = InvalidPathException.class)
	public void testChangeToDirectory() {
		fs.setWd("/Users/file1");
		cd.executeCommand("/Users/file1/Desktop/random");
		assertEquals("", fs.getOutput());
	// check to see if expected value is actual value
	

	}
	
}