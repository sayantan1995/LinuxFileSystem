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
import java.util.List;

/**
 * This class takes in a path entered by the user and the current working
 * directory and determines the path relative to the root folder.
 */
public class PathParser {

  /**
   * Takes in the current working directory and the path entered by the user and
   * determines the path to follow from the root.
   * 
   * @param path The path the user entered. Can be relative or absolute.
   * @param wd The current working directory
   * @return The path relative to the root.
   */
  public static List<String> getAbsolutePathList(String path, String wd) {

    // user enters the root directory - returns blank path
    if (path.equals("/")) {
      return new ArrayList<String>();
    }

    // user entered absolute path
    if (path.substring(0, 1).equalsIgnoreCase("/")) {
      return prepareAbsolutePathParts(path, wd);
    }

    // user entered relative path
    else {
      if (wd.substring(wd.length() - 1).equals("/"))
        return prepareAbsolutePathParts(wd + path, wd);
      else
        return prepareAbsolutePathParts(wd + "/" + path, wd);
    }
  }

  public static String getAbsolutePathString(String path, String wd) {

    List<String> pathParts = getAbsolutePathList(path, wd);
    return getAbsolutePathString(pathParts);

  }

  public static String getAbsolutePathString(List<String> pathParts) {

    if (pathParts.size() == 0)
      return "/";
    else {
      String totalPath = "/";

      for (int i = 0; i < pathParts.size(); i++) {
        totalPath = totalPath + pathParts.get(i) + "/";
      }
      return totalPath.substring(0, totalPath.length() - 1);
    }

  }

  // returns back a list of strings representing the path to follow from root
  private static List<String> prepareAbsolutePathParts(String path, String wd) {

    ArrayList<String> finalPath = new ArrayList<String>();

    String[] parts = path.substring(1).split("/");

    // loops over the parts of the path individually
    for (int i = 0; i < parts.length; i++) {

      // responds to the .. command
      if (parts[i].equals("..")) {
        if (!finalPath.isEmpty()) {
          finalPath.remove(finalPath.size() - 1);
        }
      } else if (!parts[i].equals("."))
        finalPath.add(parts[i]);
    }
    return finalPath;
  }
}
