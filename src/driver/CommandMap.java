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

import java.util.HashMap;

/**
 * Maps the commands to the index of their instance in the command array.
 */
public class CommandMap {

  private HashMap<String, Integer> map;

  /**
   * Class Constructor
   */
  public CommandMap() {

    map = new HashMap<String, Integer>();

    map.put("history", 0);
    map.put("pwd", 1);
    map.put("popd", 2);
    map.put("pushd", 3);
    map.put("man", 4);
    map.put("mkdir", 5);
    map.put("cd", 6);
    map.put("echo", 7);
    map.put("cat", 8);
    map.put("cp", 9);
    map.put("ls", 10);
    map.put("get", 11);
    map.put("mv", 12);
  }


  /**
   * Given a command name, this function returns the corresponding number
   * associated with the command in the map. This will return -1 if the command
   * is not found in the map.
   * 
   * @param commandName The name of the command.
   * @return The number associated with the command. Returns -1 if the command
   *         is not found.
   */
  public int getCmdNumber(String commandName) {

    int commandNumber = -1;

    // finds which of the commands the entered command matches with
    for (String c : map.keySet()) {
      if (commandName.equals(c)) {
        commandNumber = map.get(c);
        break;
      }
    }

    return commandNumber;

  }
}
