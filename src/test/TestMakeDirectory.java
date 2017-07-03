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
import driver.FileSystem;
import driver.InvalidArgumentException;
import driver.InvalidPathException;
import driver.MakeDirectory;
import driver.TooFewArgumentsException;

public class TestMakeDirectory {

  FileSystem fs;
  MakeDirectory mkdir;
  
  @Before
  public void setUp(){
      this.fs = FileSystem.createFileSystemInstance();
      this.mkdir = new MakeDirectory();
  }
  /**
   * Ensures that the mkdir command gives a TooFewArgumentsException when no arguments are given.
   */
  
  @Test (expected = TooFewArgumentsException.class)
  public void testMakeDirectoryNoArguments() {
      mkdir.executeCommand("");
      assertEquals("Error: The 'mkdir' command needs atleast 1 argument. "
              + "You've entered 0 arguments.\n", fs.getOutput());         
  }
  /**
   * Ensures that the mkdir command gives a InvalidException when invalid arguments are given.
   */
  
  
   @Test (expected = InvalidArgumentException.class)
    public void testMakeDirectoryWithInvalidArgument() {
      mkdir.executeCommand("$@"); 
      //output should be an error since a valid argument is needed
      assertEquals("Error: '$@' is an invalid path. Characters including "
          + "\"!@$&*()?:[]\"<>'`|={}\\/\" are not allowed in paths.\n"
          , fs.getOutput()); 
      // checked to see if expected value is actual value
      
    }
   /**
    * Ensures that the mkdir command gives a InvalidPathException when invalid path is given.
    */
   
   @Test (expected = InvalidPathException.class)
    public void testMakeDirectoryWithInvalidPath() {
      fs.setWd("/Users/abbas/Desktop/temp/b");
      mkdir.executeCommand("../temp/207"); 
      //output should be an error since a valid path is needed
      String str = new String("Error: '" + "../temp/207"
              + "' is an invalid path. Characters including"
              + " \"!@$&*()?:[]\"<>'`|={}\\/\" are not allowed in paths.\n");
      assertEquals(str,fs.getOutput()); 
      // checked to see if expected value is actual value
      
   }
 
  /**
   * Ensures that the mkdir command gives the correct output when presented with valid arguments.
   */
  @Test //test if proper argument is entered 
  public void testCorrectArgument1() {
    
    mkdir.executeCommand("folder2"); 
    assertEquals("/",fs.getWd()); 
    // checked to see if expected value is actual value
    
  } 
  /**
   * Ensures that the mkdir command gives the correct output when presented with valid arguments.
   */
  @Test //test if proper argument is entered 
  public void testCorrectArgument2() {
    
    mkdir.executeCommand("folder1"); 
    assertEquals("/",fs.getWd()); 
    // checked to see if expected value is actual value
    
  } 
  /**
   * Ensures that the mkdir command gives the correct output when presented with valid arguments.
   */
  @Test //test if proper argument is entered 
  public void testCorrectArgument3() {
    
    mkdir.executeCommand("folder3"); 
    assertEquals("/",fs.getWd()); 
    // checked to see if expected value is actual value
    
  } 
  
}
