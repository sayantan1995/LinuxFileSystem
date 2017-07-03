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

import driver.PrintWorkingDirectory;
import driver.FileSystem;
import driver.TooManyArgumentsException;
import driver.MakeDirectory;
import driver.PushDirectory;

/**
 * Tests the echo class.
 */
public class TestPrintWorkingDirectory {
    
    FileSystem fs;
    PrintWorkingDirectory pwd;
    MakeDirectory mkdir;
    PushDirectory pushd;
    
    /**
     * Sets up for the tests.
     */
    @Before
    public void setUp(){
        this.fs = FileSystem.createFileSystemInstance();
        this.pwd = new PrintWorkingDirectory();
        this.mkdir = new MakeDirectory();
        this.pushd = new PushDirectory();
    }
    /**
     * Ensures that the pwd command gives a TooManyArgumentsException when an argument is given.
     */
  @Test (expected = TooManyArgumentsException.class)//testing command when user enters arguments
  public void testPrintCorrectWdWithArgs(){
    this.pwd.executeCommand("argument1"); 
   
  }
  /**
   * Ensures that the pwd command functions correctly  when no arguments are given.
   */
  
  @Test //testing command when user enters no arguments
  public void testPrintCorrectWdWithNoArgs() {
    this.mkdir.executeCommand("movies");
    this.pushd.executeCommand("movies");
    this.pwd.executeCommand(""); 
    assertEquals("/movies", this.fs.getWd()); 
    //made sure the output was equal to what I expected
      
  }
  /**
   * Ensures that the cp command gives a TooFewArgumentsException when no arguments are given.
   */
  
  @Test //another test if user enters no arguments
  public void testPrintCorrectWdWithNoArgs1() {
    this.mkdir.executeCommand("PulpFiction");
    this.pushd.executeCommand("PulpFiction");
    this.pwd.executeCommand(""); 
    //executed the pwd command
    assertEquals("/movies/PulpFiction", this.fs.getOutput()); 
    //made sure the output was equal to what I expected
      
  }
  /**
   * Ensures that the cp command gives a TooFewArgumentsException when no arguments are given.
   */
  
  @Test //another test if user enters no arguments
  public void testPrintCorrectWdWithNoArgs3() {
    this.mkdir.executeCommand("LionKing");
    this.pushd.executeCommand("LionKing");
    this.pwd.executeCommand(""); 
    //executed the pwd command
    assertEquals("/movies/PulpFiction/LionKing", this.fs.getOutput()); 
    //made sure the output was equal to what I expected
  }
  /**
   * Ensures that the cp command gives a TooFewArgumentsException when no arguments are given.
   */
  @Test //another test if user enters no arguments
  public void testPrintCorrectWdWithNoArgs4() {
    this.mkdir.executeCommand("ShawshankRedemption");
    this.pushd.executeCommand("ShawshankRedemption");
    this.pwd.executeCommand(""); 
    //executed the pwd command
    assertEquals("/movies/PulpFiction/LionKing/ShawshankRedemption", this.fs.getOutput()); 
    //made sure the output was equal to what I expected 
 
      
  }

}


