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

import driver.Copy;
import driver.FileSystem;
import driver.TooFewArgumentsException;
import driver.TooManyArgumentsException;

public class TestCopy {

  FileSystem fs;
  Copy cp;
  
  @Before
  public void setUp(){
      this.fs = FileSystem.createFileSystemInstance();
      this.cp = new Copy();
  }
  
  /**
   * Ensures that the cp command gives a TooFewArgumentsException when no arguments are given.
   */
  @Test(expected = TooFewArgumentsException.class)
  public void testNoArguments(){
      this.cp.executeCommand("");
  }
  /**
   * Ensures that the cp command gives a TooFewArgumentsException when 1 arguments are given.
   */
  @Test(expected = TooFewArgumentsException.class)
  public void testSingleArguments(){
      this.cp.executeCommand("");
  }
  
  /**
   * Ensures that the mv command gives a TooManyArgumentsException when more than 2 arguments are given.
   */
    @Test(expected = TooManyArgumentsException.class)
    public void testMoreThanTwoArguments(){
        this.cp.executeCommand("folder1 folder2 folder3");
    }
}

  