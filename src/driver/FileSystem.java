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
import java.util.Stack;

/**
 * This class handles all the components of a singleton file-system.
 */
public class FileSystem {

  // necessary instance variables
  private static FileSystem singleReference = null;
  private String wd;
  private String output;
  private Stack<String> directoryStack;
  private ArrayList<String> history;
  private Directory root;
  private String legalCharacters;

  private FileSystem() {
    this.wd = "/";
    this.output = "";
    this.history = new ArrayList<String>();
    this.directoryStack = new Stack<String>();
    this.root = new Directory("root");
    this.legalCharacters = "[A-Za-z/0-9.\\-]+";
  }

  /**
   * Checks whether the given path contains the legal characters allowed in this
   * file-system. Raises an error if legal characters are not used.
   * 
   * @param path The path to check for legal characters.
   */
  public void legalCharactersCheck(String path) {
    String cleanPath = PathParser.getAbsolutePathString(path, this.wd);
    if (!(cleanPath.matches(this.legalCharacters)))
      throw new IllegalPathCharactersException(
          path + ": path contains illegal characters");
  }

  /**
   * Static method which allows the creation of the file-system object.
   * Preserves the singleton design.
   * 
   * @return The single instance of the file-system.
   */
  public static FileSystem createFileSystemInstance() {

    if (singleReference == null) {
      singleReference = new FileSystem();
    }

    return singleReference;

  }

  /**
   * Changes the working directory of the file-system.
   * 
   * @param wd The path representing the working directory to change to.
   */
  public void setWd(String path) {
    this.legalCharactersCheck(path);
    String cleanPath = PathParser.getAbsolutePathString(path, this.wd);

    if (this.fileExists(cleanPath))
      throw new InvalidPathException(path + ": not a directory");

    if (this.directoryExists(cleanPath))
      this.wd = cleanPath;
    else
      throw new InvalidPathException(path + ": no such directory");
  }

  /**
   * Sets the output message after a command has been executed.
   * 
   * @param output The output message.
   */
  public void setOutput(String output) {
    this.output = output;
  }

  /**
   * Adds the given directory to the directory stack. Raises any necessary
   * errors.
   * 
   * @param path The path of the directory to add to the stack.
   */
  public void stackPush(String path) {

    this.legalCharactersCheck(path);
    String cleanPath = PathParser.getAbsolutePathString(path, this.wd);

    // raises errors or adds to the stack
    if (this.fileExists(cleanPath))
      throw new InvalidPathException(path + ": not a directory");

    if (this.directoryExists(cleanPath))
      this.directoryStack.push(cleanPath);
    else
      throw new InvalidPathException(path + ": no such directory");
  }

  /**
   * Gets the size of the directory stack.
   * 
   * @return Size of directory stack.
   */
  public int getStackSize() {
    return this.directoryStack.size();
  }

  /**
   * Gets the directory on the top of the directory stack.
   * 
   * @return The path of the directory on the top of the directory stack.
   */
  public String stackPop() {
    if (this.directoryStack.size() == 0)
      throw new EmptyDirectoryStackException("popd: directory stack empty");
    else
      return this.directoryStack.pop();
  }

  /**
   * Gets the output message of the last command that was executed.
   * 
   * @return output The output of the last command.
   */
  public String getOutput() {
    return this.output;
  }

  /**
   * Gets the current working directory.
   * 
   * @return The working directory.
   */
  public String getWd() {
    return this.wd;
  }

  /**
   * Returns a list of the last n commands in the history.
   * 
   * @param n The number of commands required.
   * @return A list of commands in the history.
   */
  public List<String> getHistory(int n) {
    return this.history.subList(history.size() - n, history.size());
  }

  /**
   * Gets the number of elements in history.
   * 
   * @return Number of elements in history.
   */
  public int getHistorySize() {
    return this.history.size();
  }

  /**
   * Adds an entry to the history,
   * 
   * @param entry The entry to be added to the history object.
   */
  public void addToHistory(String entry) {
    this.history.add(entry);
  }

  /**
   * Checks to see if a directory exists in the root folder.
   * 
   * @param path The path to check.
   * @return True if the directory exists and false otherwise.
   */
  public boolean directoryExists(String path) {
    return this.root.fileOrDirectoryExists(0, path, this.wd);
  }

  /**
   * Checks to see if a file exists in the root folder.
   * 
   * @param path The path to check.
   * @return True if the file exists and false otherwise.
   */
  public boolean fileExists(String path) {
    return this.root.fileOrDirectoryExists(1, path, this.wd);
  }

  /**
   * Makes a directory.
   * 
   * @param path The path to the directory to make.
   */
  public void makeDirectory(String path) {
    this.makeFileOrDirectory(0, path);
  }

  /**
   * Makes a file.
   * 
   * @param path The path to the file to make.
   */
  public void makeFile(String path) {
    this.makeFileOrDirectory(1, path);
  }

  // helper method for making files and directories
  private void makeFileOrDirectory(int fileOrDirectory, String path) {

    this.legalCharactersCheck(path);
    String cleanPath = PathParser.getAbsolutePathString(path, this.wd);
    List<String> pathParts = PathParser.getAbsolutePathList(path, this.wd);

    if (pathParts.size() == 0)
      throw new InvalidPathException(path + ": cannot create root folder");

    // checks for naming errors
    if (this.fileExists(cleanPath))
      throw new AlreadyExistsException(
          path + ": file with same name already exists");
    if (this.directoryExists(cleanPath))
      throw new AlreadyExistsException(
          path + ": directory with same name already exists");

    // creating in root folder
    if (pathParts.size() == 1)
      this.root.makeFileOrDirectory(fileOrDirectory, path, this.wd);

    // creating in child of root folder
    else {
      String subPath = PathParser
          .getAbsolutePathString(pathParts.subList(0, pathParts.size() - 1));
      if (!(this.directoryExists(subPath)))
        throw new InvalidPathException(path.substring(0, path.lastIndexOf("/"))
            + ": no such file or directory");
      else
        this.root.makeFileOrDirectory(fileOrDirectory, path, this.wd);
    }
  }

  /**
   * Gets the contents of a file.
   * 
   * @param path The path of the file to get the contents of.
   * @return The content of the given file.
   */
  public String getFileContent(String path) {
    this.legalCharactersCheck(path);
    String cleanPath = PathParser.getAbsolutePathString(path, this.wd);

    // gets file content or raise error if the file doesn't exist
    if (this.fileExists(cleanPath))
      return this.root.getFileContent(path, this.wd);
    else
      throw new InvalidPathException(path + ": no such file");
  }

  /**
   * Gets a particular event from the history object.
   * 
   * @param event The number of the event to get.
   * @return The command stored in the history object.
   */
  public String getHistoryEvent(int event) {
    return this.history.get(event - 1);
  }

  /**
   * Gets the contents of a directory.
   * 
   * @param path The path of the directory to get the contents of.
   * @return The content of the given directory.
   */
  public String getDirectoryContent(String path, boolean recursive) {

    this.legalCharactersCheck(path);
    String cleanPath = PathParser.getAbsolutePathString(path, this.wd);

    // gets directory content or raise error if the directory doesn't exist
    if (!this.fileExists(cleanPath) && !this.directoryExists(cleanPath))
      throw new InvalidPathException(path + ": no such file or directory");
    else {
      if (this.fileExists(cleanPath))
        return path;
      else
        return this.root.listContent(path, this.wd, recursive);
    }

  }

  /**
   * Sets the content of the given file.
   * 
   * @param overwrite Use true if you want to overwrite the content and false if
   *        you want to append the content.
   * @param path The path to the file to set content of.
   * @param content The content to store in the file.
   */
  public void setFileContent(boolean overwrite, String path, String content) {

    this.legalCharactersCheck(path);
    String cleanPath = PathParser.getAbsolutePathString(path, this.wd);

    // sets contents if file exists or raises error if the file doesn't exist.
    if (this.fileExists(cleanPath))
      this.root.setFileContent(overwrite, path, this.wd, content);
    else
      throw new InvalidPathException(path + ": no such file");
  }

  /**
   * Deletes a file or directory from the root folder.
   * 
   * @param path The path of the file or directory to delete.
   */
  public void delete(String path) {

    this.legalCharactersCheck(path);
    String cleanPath = PathParser.getAbsolutePathString(path, this.wd);

    // deletes the file or directory if they exist or raises error if do not
    // exist
    if (!this.fileExists(cleanPath) && !this.directoryExists(cleanPath))
      throw new InvalidPathException(path + ": no such file or directory");
    else {
      if (this.fileExists(cleanPath))
        this.root.delete(1, path, this.wd);
      else
        this.root.delete(0, path, this.wd);
    }
  }

  /**
   * Gets a particular directory from the file-system.
   * 
   * @param path The path of the directory to get.
   * @return The requested directory.
   */
  public Directory getDirectory(String path) {

    this.legalCharactersCheck(path);
    String cleanPath = PathParser.getAbsolutePathString(path, this.wd);

    // retrives the directory if it exists
    if (!this.directoryExists(cleanPath))
      throw new InvalidPathException(path + ": no such directory");
    else {
      return this.root.getDirectory(path, this.wd);
    }
  }

  /**
   * Gets a particular file from the file-system.
   * 
   * @param path The path of the file to get.
   * @return The requested file.
   */
  public File getFile(String path) {

    this.legalCharactersCheck(path);
    String cleanPath = PathParser.getAbsolutePathString(path, this.wd);

    // retrives the directory if it exists
    if (!this.fileExists(cleanPath))
      throw new InvalidPathException(path + ": no such file");
    else {
      return this.root.getFile(path, this.wd);
    }
  }

  /**
   * Adds a file to the given directory.
   * 
   * @param path The path to the directory where the file is to be added.
   * @param file The file to add.
   */
  public void addFile(String path, File file) {

    this.legalCharactersCheck(path);
    String cleanPath = PathParser.getAbsolutePathString(path, this.wd);

    // adds the file to the directory if the directory exists
    if (!this.directoryExists(cleanPath))
      throw new InvalidPathException(path + ": no such directory");
    else {
      this.root.addFile(path, this.wd, file);
    }
  }

  /**
   * Adds a directory to the given directory.
   * 
   * @param path The path to the directory where the directory is to be added.
   * @param file The directory to add.
   */
  public void addDirectory(String path, Directory dir) {

    this.legalCharactersCheck(path);
    String cleanPath = PathParser.getAbsolutePathString(path, this.wd);

    // adds the file to the directory if the directory exists
    if (!this.directoryExists(cleanPath))
      throw new InvalidPathException(path + ": no such directory");
    else {
      this.root.addDirectory(path, this.wd, dir);
    }
  }
}
