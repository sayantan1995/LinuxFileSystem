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
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import driver.Get;
import driver.InvalidArgumentException;
import driver.TooFewArgumentsException;
import driver.TooManyArgumentsException;
import driver.FileSystem;

/**
 * Testing the Get Class
 */
public class TestGet {
	

	FileSystem fs;
	Get get;
	
	/**
	 * Sets up for the tests.
	 */
	@Before
	public void setUp(){
		this.fs = FileSystem.createFileSystemInstance();
		this.get = new Get();
	}
	
	/**
	 * Ensures that the get command gives a TooFewArgumentsException when no arguments are given.
	 */
	@Test(expected = TooFewArgumentsException.class)
	public void testNoArguments(){
		this.get.executeCommand("");
	}
	
	/**
	 * Ensures that the get command gives a TooManyArgumentsException when given more than 1 argument.
	 */
	@Test(expected = TooManyArgumentsException.class)
	public void testTooManyArguments(){
		this.get.executeCommand("arg1 arg2 arg3");	
	}
	
	/**
	 * Ensures that the get command gives a InvalidArgumentException when given an invalid url.
	 */
	@Rule public ExpectedException thrown= ExpectedException.none();
	@Test
	public void testInvalidURl(){
		
		thrown.expect(InvalidArgumentException.class);
	    thrown.expectMessage("www.blah.txt: no such url found");
		this.get.executeCommand("www.blah.txt");	
	}
	
	/**
	 * Ensures that the get command functions properly when given valid arguments.
	 */
	@Test
	public void testValidArguments(){
		
		this.get.executeCommand("http://www.cs.cmu.edu/~spok/grimmtmp/073.txt");
		String list = this.fs.getDirectoryContent("/", false);
		assertEquals("FILE: 073.txt", list);
		
		this.get.executeCommand("https://www.dropbox.com/s/n3gtz6lh0b1m0m7/testfile.txt");
		list = this.fs.getDirectoryContent("/", false);
		assertEquals("FILE: 073.txt\nFILE: testfile.txt", list);
		
		String content = this.fs.getFileContent("/073.txt");
		content = content.substring(0, content.indexOf("\n"));
		assertEquals("There was once a king who had an illness, and no one believed that he", content);
		
	}

}

