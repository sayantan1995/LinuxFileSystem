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

/**
 * Tests the popd class.
 */
public class TestPopDirectory {
    
    FileSystem fs;
    PopDirectory popd;
    MakeDirectory mkdir;
    PushDirectory pushd;
    
    /**
     * Sets up for the tests.
     */
    @Before
    public void setUp(){
        this.fs = FileSystem.createFileSystemInstance();
        this.popd = new PopDirectory();
        this.mkdir = new MakeDirectory();
        this.pushd = new PushDirectory();
        
        
    }
    
    /**
     * Ensures that the popd command gives a TooManyArgumentsException when given more than 1 argument.
     */
    @Test(expected = TooManyArgumentsException.class)
    public void testTooManyArguments(){
        this.popd.executeCommand("arg1"); 
    }
    /**
     * Ensures that the popd command functions correctly when no argument is given.
     */
    
    @Test //popd when stack is not empty
    public void testPopOnNonEmptyStack1() {
      this.mkdir.executeCommand("Movies");
      this.pushd.executeCommand("Movies");
      // creates a stack that is now not empty
      this.popd.executeCommand("");
      assertEquals("/",fs.getWd());
      // check to make sure working directory is changed to what was popped
      
    }
    /**
     * Ensures that the popd command functions correctly when no argument is given.
     */
    @Test //another nonempty stack test for popd
    public void testPopOnNonEmptyStack2() {
      this.mkdir.executeCommand("PulpFiction");
      this.pushd.executeCommand("PulpFiction");
      // creates a stack that is now not empty
      this.popd.executeCommand("");
      assertEquals("/",fs.getWd());
      
    }
    
  }
  

