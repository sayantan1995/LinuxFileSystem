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

import java.util.List;

/**
 * Outputs the history of commands entered by the user.
 */
public class History extends Command {

  /**
   * Outputs the history of the commands entered by the user. If a number is
   * given, it outputs n events, else outputs the whole history.
   * 
   * @param arguments The arguments entered by the user.
   */
  public void executeCommand(String arguments) {

    this.prepareToExecute(arguments, "history");

    // no arguments given - output all history
    if (this.numberOfArguments == 0)
      this.formatHistoryOutput(this.fs.getHistorySize());

    else {

      // argument is a valid number >=0
      if (this.arguments.matches("\\d+"))
        this.formatHistoryOutput(Math.min(Integer.parseInt(this.arguments),
            this.fs.getHistorySize()));
      else
        throw new InvalidArgumentException("history: " + this.arguments
            + ": numeric argument >=0 required\nTry 'man history' "
            + "for more information.");
    }

    this.finishExecute();
  }

  // formats the history into the appropriate output
  private void formatHistoryOutput(int entries) {

    List<String> history = this.fs.getHistory(entries);

    for (int i = 0; i < history.size(); i++) {
      this.output += (this.fs.getHistorySize() - history.size() + 1 + i) + " ";
      this.output += (history.get(i) + "\n");
    }

    // removes unncessary lines
    if (this.output.length() >= 1)
      this.output = this.output.substring(0, this.output.length() - 1);

  }

}
