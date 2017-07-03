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

package driver;

import java.util.ArrayList;

/**
 * A class created simply for the benefits of polymorphism. Executes particular
 * commands on a file-system and returns the modified file-system.
 */
public abstract class Command {

  // instance methods
  private ArgumentsParser argHandler = new ArgumentsParser();
  protected int numberOfArguments;
  protected ArrayList<String> args;
  protected String arguments;
  protected String output;
  protected FileSystem fs;
  protected String errorMessage;

  /**
   * Executes a command and modifies the file-system in the necessary way.
   * 
   * @param arguments The arguments entered by the user.
   */
  public void executeCommand(String arguments) {}


  /**
   * Grabs the instance of the file-system, sets the output to a blank output
   * and gets list of arguments.
   * 
   * @param arguments The arguments entered by the user.
   * @param commandName The name of the command being executed.
   */
  protected void prepareToExecute(String arguments, String commandName) {

    this.output = "";
    this.errorMessage = "";
    this.fs = FileSystem.createFileSystemInstance();
    this.fs.setOutput("");
    this.arguments = arguments;
    this.args = this.argHandler.prepareListOfArguments(arguments, commandName);
    this.numberOfArguments = this.args.size();

  }

  /**
   * Adds an error message to the error message instance variable.
   * 
   * @param error The error message.
   */
  protected void addError(String error) {
    this.errorMessage = this.errorMessage + error + "\n";
  }

  /**
   * Sets the output of the command and raises an error if need be.
   */
  protected void finishExecute() {

    if (this.output.trim() == "")
      this.fs.setOutput("");
    else
      this.fs.setOutput(this.output);
    if (!(this.errorMessage == ""))
      throw new InvalidArgumentException(this.errorMessage.trim());
  }
}
