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

/**
 * Adds the working directory to the directory stack and changes the working
 * directory to the given directory.
 */
public class PushDirectory extends Command {

  /**
   * Adds the working directory to the directory stack and changes the working
   * directory to the given directory.
   * 
   * @param arguments The arguments entered by the user.
   */

  public void executeCommand(String arguments) {

    this.prepareToExecute(arguments, "pushd");
    String wd = this.fs.getWd();
    this.fs.setWd(this.arguments);
    this.fs.stackPush(wd);
    this.finishExecute();


  }

}
