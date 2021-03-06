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
 * Lists the files and directories in the paths that are given. Can enter -r to
 * list recursively.
 */
public class ListContent extends Command {

  /**
   * Lists the files and directories in the paths that are given. Can enter -r
   * to list recursively.
   * 
   * @param arguments The number of arguments a user has entered
   */
  public void executeCommand(String arguments) {

    this.prepareToExecute(arguments, "ls");

    // depending on the number/type of args, decides to list wd/other files
    // and recursive/non-recursive
    if (this.numberOfArguments == 0)
      this.output = this.fs.getDirectoryContent(this.fs.getWd(), false);
    else if (this.numberOfArguments == 1
        && this.args.get(0).equalsIgnoreCase("-r"))
      this.output = this.fs.getDirectoryContent(this.fs.getWd(), true);
    else if (!this.args.get(0).equalsIgnoreCase("-r"))
      this.formatOutput(false);
    else
      this.formatOutput(true);

    this.finishExecute();
  }

  // formats the specific output depending on whether it is recursive or not.
  private void formatOutput(boolean recursion) {

    int startLocation = 0;

    if (recursion)
      startLocation = 1;

    // prepares the output for each path that was given
    for (String arg : this.args.subList(startLocation, this.args.size())) {

      try {
        String content = this.fs.getDirectoryContent(arg, recursion);
        if (this.fs.fileExists(arg))
          output = output + content + "\n\n";
        else
          output = output + arg + ":\n" + content + "\n\n";
      } catch (Exception e) {
        this.addError(e.getMessage());
      }

    }

    // removes unnecessary empty lines at the end of the output
    if (this.output.length() >= 2)
      this.output = this.output.substring(0, this.output.length() - 2);
  }
}
