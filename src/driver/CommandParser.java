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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Command utilities class. Offers many methods to deal with commands entered by
 * the user.
 */
public class CommandParser {

  /**
   * Cleans the input by removing extra spaces.
   * 
   * @param rawInput The raw string entered by the user.
   * @return The cleaned input without extra spaces.
   */
  public static String getCleanInput(String rawInput) {

    String cleanInput = "";

    String[] splitAtQuotes = rawInput.split("\\\"");

    // there are no quotes in input
    if (splitAtQuotes.length == 1) {
      cleanInput = splitAtQuotes[0].replaceAll("\\s+", " ");
      cleanInput = cleanInput.trim();
    }

    // there are quotes in input
    else {
      cleanInput = "";
      Matcher m = Pattern.compile("\"[^\"]*\"|[^\\s]+").matcher(rawInput);
      while (m.find()) {
        cleanInput = cleanInput + m.group() + " ";
      }
      cleanInput = cleanInput.trim();
    }

    return cleanInput;
  }

  /**
   * Determines the command name given the entire input.
   * 
   * @param input The entire string entered by the user.
   * @return The command name.
   */
  public static String getCmd(String input) {

    String cleanInput = getCleanInput(input);

    int locationOfSpace = cleanInput.indexOf(" ");

    if (locationOfSpace == -1) {
      return cleanInput;
    } else {
      return cleanInput.substring(0, locationOfSpace);
    }
  }

  /**
   * Determines the arguments given the entire input.
   * 
   * @param input The entire string entered by the user.
   * @return The arguments.
   */
  public static String getArg(String input) {

    String cleanInput = getCleanInput(input);

    int locationOfSpace = cleanInput.indexOf(" ");

    if (locationOfSpace == -1) {
      return "";
    } else {
      return cleanInput.substring(locationOfSpace + 1);
    }
  }

  /**
   * Checks if a command contains redirection.
   * 
   * @param input The entire string entered by the user.
   * @return True if the command contains redirections and false otherwise.
   */
  public static boolean containsRedirect(String input) {

    String cleanInput = getCleanInput(input);

    if (cleanInput.matches(".+ (>|>>) [^\\s]+"))
      return true;

    return false;
  }

  /**
   * Gets the type of redirection (> or >>) in the command
   * 
   * @param input The entire string entered by the user.
   * @return The type of redirection (> or >>).
   */
  public static String getTypeOfRedirection(String input) {
    if (input.indexOf(">>") >= 0)
      return ">>";
    else
      return ">";
  }

  /**
   * Gets the path where redirection should occur.
   * 
   * @param input The entire string entered by the user.
   * @return The path of redirection.
   */
  public static String getRedirectionPath(String input) {

    String cleanInput = getCleanInput(input);
    int locationOfRedirect = cleanInput.indexOf(">>");

    if (locationOfRedirect == -1) {
      locationOfRedirect = cleanInput.indexOf(">");
      return cleanInput.substring(locationOfRedirect + 2);
    }

    return cleanInput.substring(locationOfRedirect + 3);

  }

  /**
   * Cleans the input by removing the redirection path.
   * 
   * @param input The entire string entered by the user.
   * @return The command without the redirection path.
   */
  public static String getInputWithoutRedirection(String input) {

    String cleanInput = getCleanInput(input);
    int locationOfRedirect = cleanInput.indexOf(">");

    if (locationOfRedirect == -1)
      return cleanInput;
    else if (locationOfRedirect == 0)
      return "";

    return cleanInput.substring(0, locationOfRedirect - 1);
  }

}
