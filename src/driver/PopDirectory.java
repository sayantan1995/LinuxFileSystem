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
 * Changes the current working directory to the directory on the top of the
 * directory stack.
 */
public class PopDirectory extends Command {

  /**
   * Changes the current working directory to the directory on the top of the
   * directory stack.
   * 
   * @param arguments The arguments entered by the user.
   */
  public void executeCommand(String arguments) {

    this.prepareToExecute(arguments, "popd");

    String poppedDirectory = this.fs.stackPop();
    if (!this.fs.directoryExists(poppedDirectory))
      throw new InvalidPathException(
          poppedDirectory + ": directory no longer exists");

    this.fs.setWd(poppedDirectory);
    this.finishExecute();

  }
}
