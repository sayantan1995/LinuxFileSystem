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
 * Copies a directory/file to another directory/file.
 */
public class Copy extends Command {

  /**
   * Copies a directory/file to another directory/file.
   * 
   * @param arguments The arguments entered by the user.
   */
  public void executeCommand(String arguments) {

    this.prepareToExecute(arguments, "cp");

    String path1 = this.args.get(0);
    String path2 = this.args.get(1);

    this.fs.legalCharactersCheck(path1);
    this.fs.legalCharactersCheck(path2);

    // calls the appropriate helper method depending on whether the paths given
    // are files or directories
    if (this.fs.fileExists(path1) && this.fs.fileExists(path2))
      this.fileAndFile(path1, path2);
    else if (this.fs.fileExists(path1) && this.fs.directoryExists(path2))
      this.fileAndDirectory(path1, path2);
    else if (this.fs.directoryExists(path1) && this.fs.fileExists(path2))
      throw new InvalidArgumentException(
          "a directory cannot be copied into a file");
    else if (this.fs.directoryExists(path1) && this.fs.directoryExists(path2))
      this.directoryAndDirectory(path1, path2);
    else
      throw new InvalidArgumentException(
          path1 + ", " + path2 + ": one or both are invalid paths");
  }


  // private helper method to copy a file
  private void fileAndFile(String firstPath, String secondPath) {

    String content = this.fs.getFileContent(firstPath);
    this.fs.setFileContent(true, secondPath, content);
  }

  // private helper method to copy a file into a directory
  private void fileAndDirectory(String firstPath, String secondPath) {

    File file = this.fs.getFile(firstPath);
    this.fs.addFile(secondPath, file);
  }

  // helper method to copy directory into directory
  private void directoryAndDirectory(String firstPath, String secondPath) {

    List<String> path1 =
        PathParser.getAbsolutePathList(firstPath, this.fs.getWd());
    List<String> path2 =
        PathParser.getAbsolutePathList(secondPath, this.fs.getWd());

    // checks to make sure directory is not being moved into a sub-directory
    if (path2.size() >= path1.size()) {
      if (path2.subList(0, path1.size()).equals(path1))
        throw new InvalidArgumentException(
            "cannot copy/move '" + firstPath + "' to a subdirectory of itself");
    }

    Directory toBeCopied = this.fs.getDirectory(firstPath);
    this.fs.addDirectory(secondPath, toBeCopied);

  }

}
