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
 * Moves a particular file/directory to another file/directory.
 */
public class Move extends Command {

  private Copy copy;

  /**
   * Class constructor.
   */
  public Move() {
    this.copy = new Copy();
  }

  /**
   * Moves a directory/file to another directory/file.
   * 
   * @param arguments The arguments entered by the user.
   */
  public void executeCommand(String arguments) {

    this.prepareToExecute(arguments, "mv");

    this.copy.executeCommand(arguments);
    this.fs.delete(this.args.get(0));

  }

}
