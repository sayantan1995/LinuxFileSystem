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
 * Allows for a command from history to be rerun.
 */
public class Factorial extends Command {

  CommandToolbox toolbox; // tool-box containing the commands

  /**
   * Class Constructor.
   */
  public Factorial() {
    toolbox = new CommandToolbox();
    this.fs = FileSystem.createFileSystemInstance();
  }

  /**
   * Reruns a command from history.
   * 
   * @param input The full string entered by the user when calling the factorial
   *        command.
   */
  public void executeCommand(String input) {

    String command = CommandParser.getCmd(input);
    String arguments = CommandParser.getArg(input);

    // invalid command call
    if (!command.matches("![1-9]+") || command.matches("!"))
      throw new InvalidArgumentException(
          "factorial only works when called in the form !x, where x is a number >= 1");

    if (!arguments.matches("(>||>>) [^\\s]+") && !arguments.trim().equals(""))
      throw new InvalidArgumentException(
          "factorial doesn't accept any arguments except redirection");


    int eventNumber = Integer.parseInt(command.substring(1));

    if (eventNumber > fs.getHistorySize())
      throw new InvalidArgumentException(command + ": event not found");

    // valid command
    else {
      String rawInput = fs.getHistoryEvent(eventNumber);

      if (CommandParser.containsRedirect(rawInput)
          && CommandParser.containsRedirect(input))
        throw new InvalidArgumentException("multiple redirections not allowed");

    }
  }
}
