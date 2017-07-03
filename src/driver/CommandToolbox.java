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
 * A tool-box containing the different commands in the shell.
 */
public class CommandToolbox {

  private Command[] commands;
  private CommandMap map;
  private FileSystem fs;

  /**
   * Class constructor.
   */
  public CommandToolbox() {

    map = new CommandMap();
    commands = new Command[15];
    fs = FileSystem.createFileSystemInstance();

    commands[0] = new History();
    commands[1] = new PrintWorkingDirectory();
    commands[2] = new PopDirectory();
    commands[3] = new PushDirectory();
    commands[4] = new Manual();
    commands[5] = new MakeDirectory();
    commands[6] = new ChangeDirectory();
    commands[7] = new Echo();
    commands[8] = new DisplayContents();
    commands[9] = new Copy();
    commands[10] = new ListContent();
    commands[11] = new Get();
    commands[12] = new Move();

  }

  /**
   * Calls the appropriate command or raises error if the command isn't found.
   * 
   * @param rawInput The exact string entered by the user.
   */
  public void executeCommand(String rawInput) {

    String cleanInput = CommandParser.getCleanInput(rawInput);
    String command = CommandParser.getCmd(cleanInput);
    String arguments = CommandParser.getArg(cleanInput);

    fs.addToHistory(rawInput);

    // calls the command or raises an error
    if (map.getCmdNumber(command) == -1)
      throw new NoSuchCommandException(command + ": command not found");
    else
      commands[map.getCmdNumber(command)]
          .executeCommand(CommandParser.getInputWithoutRedirection(arguments));

  }
}
