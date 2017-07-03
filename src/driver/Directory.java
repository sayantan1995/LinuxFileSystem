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
 * Directory class which contains inner directories and files and allows
 * manipulation of parts of the directory.
 */
public class Directory {

  String name;
  ArrayList<Directory> directories;
  ArrayList<File> files;

  /**
   * Class constructor
   * 
   * @param name The name of the directory in the file-system
   */
  public Directory(String name) {
    this.directories = new ArrayList<Directory>();
    this.files = new ArrayList<File>();
    this.name = name;
  }

  /**
   * Makes a file or directory in this directory.
   * 
   * @param FileOrDirectory Use a 0 to make a directory and anything else to
   *        make a file.
   * @param path The path of the directory or file you want to make.
   * @param wd The current working directory of the file-system.
   */
  public void makeFileOrDirectory(int FileOrDirectory, String path, String wd) {
    List<String> pathList = PathParser.getAbsolutePathList(path, wd);

    String name = pathList.get(0);

    // file or folder in root
    if (pathList.size() == 1) {
      if (FileOrDirectory == 0)
        this.directories.add(new Directory(name));
      else
        this.files.add(new File(name));
    }

    // making it in child of root
    else {
      pathList = pathList.subList(1, pathList.size());
      String newPath = PathParser.getAbsolutePathString(pathList);
      int childIndex = this.determineChildIndex(0, name);
      this.directories.get(childIndex).makeFileOrDirectory(FileOrDirectory,
          newPath, wd);
    }
  }

  /**
   * Checks if a given file or directory exist in this directory.
   * 
   * @param fileOrDirectory Use a 0 to check a directory and anything else to
   *        check a file.
   * @param path The path of the directory or file you want to check.
   * @param wd The current working directory of the file-system.
   * @return True if the file/directory exist and false if they don't
   */
  public boolean fileOrDirectoryExists(int fileOrDirectory, String path,
      String wd) {

    List<String> pathList = PathParser.getAbsolutePathList(path, wd);

    // root folder itself
    if (pathList.size() == 0)
      return fileOrDirectory == 0;

    // child of root
    else if (pathList.size() == 1)
      return !(this.determineChildIndex(fileOrDirectory,
          pathList.get(0)) == -1);

    // lower depth than child of root
    else {
      String name = pathList.get(0);
      pathList = pathList.subList(1, pathList.size());
      String newPath = PathParser.getAbsolutePathString(pathList);
      int childIndex = this.determineChildIndex(0, name);

      if (childIndex == -1)
        return false;
      else
        return this.directories.get(childIndex)
            .fileOrDirectoryExists(fileOrDirectory, newPath, wd);
    }
  }

  // private helper method that determines the index of the file/directory
  // with a certain name
  private int determineChildIndex(int fileOrDirectory, String name) {

    int childIndex = -1;

    // loops through the child directories to see if a name matches
    if (fileOrDirectory == 0) {
      for (int i = 0; i < this.directories.size(); i++) {
        if (name.equalsIgnoreCase(this.directories.get(i).getName())) {
          childIndex = i;
          break;
        }
      }
    }

    // loops through the child files to see if a name matches
    else {
      for (int i = 0; i < this.files.size(); i++) {
        if (name.equalsIgnoreCase(this.files.get(i).getName())) {
          childIndex = i;
          break;
        }
      }

    }

    return childIndex;
  }

  /**
   * Gets the name of this directory.
   * 
   * @return Name of this directory.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets the contents of a file in this directory.
   * 
   * @param path The path of the file you want to get the contents of.
   * @param wd The current working directory of the file-system.
   * @return The contents of the chosen file.
   */
  public String getFileContent(String path, String wd) {

    List<String> pathList = PathParser.getAbsolutePathList(path, wd);

    // child of root
    if (pathList.size() == 1) {
      int child = this.determineChildIndex(1, pathList.get(0));
      return this.files.get(child).getContent();
    }

    // deeper depth than child of root
    else {
      int child = this.determineChildIndex(0, pathList.get(0));
      pathList = pathList.subList(1, pathList.size());
      String newPath = PathParser.getAbsolutePathString(pathList);
      return this.directories.get(child).getFileContent(newPath, wd);
    }
  }

  /**
   * Sets the contents of a file in this directory.
   * 
   * @param overwrite Use true if you want to overwrite the content and false if
   *        you want to append the content.
   * @param path The path of the file you want to set the content to.
   * @param wd The current working directory of the file-system.
   * @param content The string to put as the content of the chosen file.
   */
  public void setFileContent(boolean overwrite, String path, String wd,
      String content) {

    List<String> pathList = PathParser.getAbsolutePathList(path, wd);

    if (pathList.size() == 1) {
      int child = this.determineChildIndex(1, pathList.get(0));
      this.files.get(child).setContent(overwrite, content);
    } else {
      int child = this.determineChildIndex(0, pathList.get(0));
      pathList = pathList.subList(1, pathList.size());
      String newPath = PathParser.getAbsolutePathString(pathList);
      this.directories.get(child).setFileContent(overwrite, newPath, wd,
          content);
    }
  }

  /**
   * Lists the contents of the given directory. Allows for recursive listing.
   * 
   * @param path The path to the directory to list.
   * @param wd The current working directory of the file-system.
   * @param recursive Whether or not you want a recursive listing.
   * @return A listing of the given directory.
   */
  public String listContent(String path, String wd, boolean recursive) {

    List<String> pathList = PathParser.getAbsolutePathList(path, wd);

    // listing the root folder
    if (pathList.size() == 0) {
      return this.formatListOutput(recursive, 0);
    }

    // listing child of root folder
    else {
      int child = this.determineChildIndex(0, pathList.get(0));
      pathList = pathList.subList(1, pathList.size());
      String newPath = PathParser.getAbsolutePathString(pathList);
      return this.directories.get(child).listContent(newPath, wd, recursive);
    }
  }

  // private helper method used by the list content method to format the
  // listing
  private String formatListOutput(boolean recursive, int depth) {

    String content = "";
    String indentation = new String(new char[depth]).replace("\0", "    ");

    // adds the directory listing - including recursive content if needed
    for (Directory dir : this.directories) {
      content = content + indentation + dir.getName() + "\n";
      if (recursive) {
        String recursiveOutput = dir.formatListOutput(true, depth + 1) + "\n";
        if (!recursiveOutput.trim().equals(""))
          content = content + recursiveOutput;
      }
    }

    // adds the file listing
    for (File file : this.files)
      content = content + indentation + "FILE: " + file.getName() + "\n";

    if (content.length() > 0)
      return content.substring(0, content.length() - 1);
    else
      return content;

  }

  /**
   * Deletes the given file or directory from this directory.
   * 
   * @param fileOrDirectory Use a 0 to delete a directory and anything else to
   *        delete a file.
   * @param path The path of the directory or file you want to delete.
   * @param wd The current working directory of the file-system.
   */
  public void delete(int fileOrDirectory, String path, String wd) {

    List<String> pathList = PathParser.getAbsolutePathList(path, wd);

    // deleting child of root
    if (pathList.size() == 1) {

      int child = this.determineChildIndex(fileOrDirectory, pathList.get(0));

      if (fileOrDirectory == 0)
        this.directories.remove(child);
      if (fileOrDirectory == 1)
        this.files.remove(child);
    }

    // deleting lower depth than root child
    else if (pathList.size() > 1) {
      int child = this.determineChildIndex(0, pathList.get(0));
      pathList = pathList.subList(1, pathList.size());
      String newPath = PathParser.getAbsolutePathString(pathList);
      this.directories.get(child).delete(fileOrDirectory, newPath, wd);
    }
  }

  /**
   * Gets a child directory from this directory.
   * 
   * @param path The path of the directory you want to get.
   * @param wd The current working directory of the file-system.
   * @return A child directory.
   */
  public Directory getDirectory(String path, String wd) {
    List<String> pathList = PathParser.getAbsolutePathList(path, wd);

    // want to get child of root
    if (pathList.size() == 1) {
      int child = this.determineChildIndex(0, pathList.get(0));
      return this.directories.get(child);
    }

    // want to get folder in lower depth than root children
    else {
      int child = this.determineChildIndex(0, pathList.get(0));
      pathList = pathList.subList(1, pathList.size());
      String newPath = PathParser.getAbsolutePathString(pathList);
      return this.directories.get(child).getDirectory(newPath, wd);

    }
  }

  /**
   * Gets a child file from this directory.
   * 
   * @param path The path of the file you want to get.
   * @param wd The current working directory of the file-system.
   * @return A child file.
   */
  public File getFile(String path, String wd) {

    List<String> pathList = PathParser.getAbsolutePathList(path, wd);

    // want to get a child file of root
    if (pathList.size() == 1) {
      int child = this.determineChildIndex(1, pathList.get(0));
      return this.files.get(child);
    }

    // want to get a file from deeper depth than root children
    else {
      int child = this.determineChildIndex(0, pathList.get(0));
      pathList = pathList.subList(1, pathList.size());
      String newPath = PathParser.getAbsolutePathString(pathList);
      return this.directories.get(child).getFile(newPath, wd);
    }
  }

  /**
   * Adds the given file to the given directory.
   * 
   * @param path The path to the directory where the file is to be added.
   * @param wd The current working directory of the file-system.
   * @param file The file to add.
   */
  public void addFile(String path, String wd, File file) {

    List<String> pathList = PathParser.getAbsolutePathList(path, wd);

    // want to add a file to the root folder
    if (pathList.size() == 0) {
      int child = this.determineChildIndex(1, file.getName());
      if (!(child == -1))
        this.files.remove(child);
      if (!(this.determineChildIndex(0, file.getName()) == -1))
        throw new AlreadyExistsException(
            file.getName() + ": a directory with the same name already exists");
      this.files.add(file);
    }

    // want to add a file lower than the root folder
    else {
      int child = this.determineChildIndex(0, pathList.get(0));
      pathList = pathList.subList(1, pathList.size());
      String newPath = PathParser.getAbsolutePathString(pathList);
      this.directories.get(child).addFile(newPath, wd, file);
    }
  }


  /**
   * Adds a directory to the given directory.
   * 
   * @param path The path to the directory where the directory is to be added.
   * @param wd The current working directory of the file-system.
   * @param dir The directory to add.
   */
  public void addDirectory(String path, String wd, Directory dir) {

    List<String> pathList = PathParser.getAbsolutePathList(path, wd);

    // want to add a directory to the root folder
    if (pathList.size() == 0) {
      int child = this.determineChildIndex(0, dir.getName());
      if (!(child == -1))
        this.directories.remove(child);
      if (!(this.determineChildIndex(1, dir.getName()) == -1))
        throw new AlreadyExistsException(
            dir.getName() + ": a file with the same name already exists");
      this.directories.add(dir);
    }

    // want to add a file lower than the root folder
    else {
      int child = this.determineChildIndex(0, pathList.get(0));
      pathList = pathList.subList(1, pathList.size());
      String newPath = PathParser.getAbsolutePathString(pathList);
      this.directories.get(child).addDirectory(newPath, wd, dir);
    }
  }

}
