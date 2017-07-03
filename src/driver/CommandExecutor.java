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
 * Depending on the input of the user, calls the correct command. Also outputs
 * the result and any errors.
 */
public class CommandExecutor {

  FileSystem fs;
  CommandToolbox toolbox;
  Factorial factorial;
  String errorMessage = "";
  Redirect redirect;

  /**
   * Class constructor.
   */
  public CommandExecutor() {
    this.fs = FileSystem.createFileSystemInstance();
    this.toolbox = new CommandToolbox();
    this.factorial = new Factorial();
    this.redirect = new Redirect();
  }

  /**
   * Calls the correct command and redirects output if necessary.
   * 
   * @param rawInput The exact string entered by the user.
   */
  public void executeCommand(String rawInput) {

    // tries to execute the command while catching any necessary exceptions
    try {
      String cleanInput = CommandParser.getCleanInput(rawInput);
      this.errorMessage = "";
      this.fs.setOutput("");

      // user wants to redo command from history
      if (cleanInput.matches("!.+")) {
        this.factorial.executeCommand(rawInput);
        int eventNumber =
            Integer.parseInt(CommandParser.getCmd(rawInput).substring(1));
        rawInput = this.fs.getHistoryEvent(eventNumber) + " "
            + CommandParser.getArg(cleanInput);
        this.toolbox.executeCommand(rawInput);
      } else
        this.toolbox.executeCommand(rawInput);

    } catch (Exception e) {
      this.errorMessage = e.getMessage();
    }

    // checks to see if the user wants to redirect the output
    if (CommandParser.containsRedirect(rawInput)) {
      this.redirect(rawInput);
      this.outputError();
    } else {
      this.outputResult();
      this.outputError();
    }
  }

  // private redirect helper methods
  private void redirect(String rawInput) {

    String type = CommandParser.getTypeOfRedirection(rawInput);
    String path = CommandParser.getRedirectionPath(rawInput);

    // as long as there is some output, redirects it
    if (fs.getOutput().length() > 0) {
      try {
        this.redirect.execute(type, path, fs.getOutput());
      } catch (Exception e) {
        this.errorMessage = e.getMessage();
      }
    }
  }

  // helper method
  private void outputError() {

    if (this.errorMessage.length() > 0)
      System.out.println(this.errorMessage);

  }

  // helper method
  private void outputResult() {

    if (fs.getOutput().length() > 0)
      System.out.println(fs.getOutput());

  }
}
