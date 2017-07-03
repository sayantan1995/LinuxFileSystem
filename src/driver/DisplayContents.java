// *********************************************************
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
 * Displays the content of the given files.
 */
public class DisplayContents extends Command {

  /**
   * Displays the content of the given files.
   * 
   * @param arguments The arguments entered by the user.
   */
  public void executeCommand(String arguments) {

    this.prepareToExecute(arguments, "cat");

    // prepares the output for each file that was given
    for (String arg : this.args) {

      try {
        this.output =
            this.output + arg + ":\n" + this.fs.getFileContent(arg) + "\n\n";
      } catch (Exception e) {
        this.addError(e.getMessage());
      }
    }

    // removes unnecessary empty lines at the end of the output
    if (this.output.length() >= 2)
      this.output = this.output.substring(0, this.output.length() - 2);

    this.finishExecute();
  }

}
