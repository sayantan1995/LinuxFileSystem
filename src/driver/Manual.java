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

import java.util.HashMap;

/**
 * Outputs the manual for a particular command.
 */
public class Manual extends Command {

  private HashMap<String, String> manual;

  /**
   * Class constructor
   */
  public Manual() {
    manual = new HashMap<String, String>();
    this.prepareManual();
  }

  /**
   * Outputs the manual for a particular command.
   * 
   * @param arguments The arguments entered by the user.
   */
  public void executeCommand(String arguments) {

    this.prepareToExecute(arguments, "man");

    this.output = this.manual.get(this.arguments);

    // command not found in manual
    if (this.output == null)
      throw new InvalidArgumentException(
          "no manual entry for " + this.arguments);

    this.finishExecute();
  }

  // Description of the commands
  private void prepareManual() {

    this.manual.put("exit", "Quits the program.");
    this.manual.put("mkdir", "Creates a directory");
    this.manual.put("cd",
        "Changes the current working directory to the given directory"
            + " name.");
    this.manual.put("ls",
        "If there is no path given, then a list of the contents of the "
            + "current working directory is given."
            + "If there is a path given, then it prints a list of "
            + "the contents of the specified directory.");
    this.manual.put("pwd", "Print current directory path;");
    this.manual.put("pushd", "Saves the current directory specified;");
    this.manual.put("popd", "Deletes the latest saved directory.");
    this.manual.put("history",
        "Prints out the previous commands entered."
            + "The number specified is the number of previous commands "
            + "printed started from the latest command entered.");
    this.manual.put("cat",
        "Displays the contents of the file on the shell."
            + "If there is more then one file then the contents of all the "
            + "files on the shell");
    this.manual.put("echo",
        "If there is no file name given, then the string given is "
            + "printed on the shell." + "The file consists the given string.");
    this.manual.put("man",
        "Formats and displays the annual pages on the given command.");
    this.manual.put("mv",
        "Moves an item from a given oldpath to a given new" + "path");
    this.manual.put("cp", "Copy the contents of the given oldpath and add it to"
        + "the given new path. The contents are copied recursively.");
    this.manual.put("get", "Retrieves the file at the given URL address.");
    this.manual.put("!number", "Recalls and executes the command that occured"
        + "at the given history number");
    this.manual.put("grep", "Prints any lines containing REGEX in PATH, which"
        + "must be a file. ");

  }

}
