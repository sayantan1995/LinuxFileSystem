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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Goes through the user input and prepares a list of arguments. Raises errors
 * if too many arguments or too few arguments are used for a command.
 */
public class ArgumentsParser {

  HashMap<String, Integer> maxNumberOfArguments;
  HashMap<String, Integer> minNumberOfArguments;

  /**
   * Class constructor.
   */
  public ArgumentsParser() {
    this.maxNumberOfArguments = new HashMap<String, Integer>();
    this.minNumberOfArguments = new HashMap<String, Integer>();
    this.prepareMaxMap();
    this.prepareMinMap();

  }

  // prepares the max number of arguments each command can have
  private void prepareMaxMap() {
    this.maxNumberOfArguments.put("cd", 1);
    this.maxNumberOfArguments.put("echo", 1);
    this.maxNumberOfArguments.put("history", 1);
    this.maxNumberOfArguments.put("mkdir", -1);
    this.maxNumberOfArguments.put("man", 1);
    this.maxNumberOfArguments.put("popd", 0);
    this.maxNumberOfArguments.put("pushd", 1);
    this.maxNumberOfArguments.put("pwd", 0);
    this.maxNumberOfArguments.put("cat", -1);
    this.maxNumberOfArguments.put("cp", 2);
    this.maxNumberOfArguments.put("mv", 2);
    this.maxNumberOfArguments.put("ls", -1);
    this.maxNumberOfArguments.put("get", 1);
    this.maxNumberOfArguments.put("!", 0);
  }

  // prepares the min number of arguments each command can have
  private void prepareMinMap() {
    this.minNumberOfArguments.put("cd", 1);
    this.minNumberOfArguments.put("echo", 1);
    this.minNumberOfArguments.put("history", 0);
    this.minNumberOfArguments.put("mkdir", 1);
    this.minNumberOfArguments.put("man", 1);
    this.minNumberOfArguments.put("popd", 0);
    this.minNumberOfArguments.put("pushd", 1);
    this.minNumberOfArguments.put("pwd", 0);
    this.minNumberOfArguments.put("cat", 1);
    this.minNumberOfArguments.put("cp", 2);
    this.minNumberOfArguments.put("mv", 2);
    this.minNumberOfArguments.put("ls", 0);
    this.minNumberOfArguments.put("get", 1);
    this.minNumberOfArguments.put("!", 0);
  }

  /**
   * Given the text of arguments, this creates a list with each entry
   * representing an argument. Also, raises an error if there are too many or
   * too few arguments.
   * 
   * @param arguments The full string of arguments.
   * @param commandName The command for which the arguments were given.
   * @return A list of arguments with each entry representing an argument.
   */
  public ArrayList<String> prepareListOfArguments(String arguments,
      String commandName) {

    ArrayList<String> listOfArguments = new ArrayList<String>();

    // uses regex matching to find the arguments
    Matcher m = Pattern.compile("\"[^\"]*\"|[^\\s]+").matcher(arguments);
    while (m.find()) {
      listOfArguments.add(m.group());
    }

    int numberOfArguments = listOfArguments.size();

    // raises error if too many or too few arguments are used
    if (numberOfArguments > this.maxNumberOfArguments.get(commandName)
        && !(this.maxNumberOfArguments.get(commandName) == -1))
      throw new TooManyArgumentsException(commandName);
    else if (numberOfArguments < this.minNumberOfArguments.get(commandName))
      throw new TooFewArgumentsException(commandName);

    return listOfArguments;

  }

}
