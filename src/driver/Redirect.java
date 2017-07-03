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
 * Redirects a given string into a file.
 */
public class Redirect {

  private FileSystem fs;

  /**
   * Class constructor.
   */
  public Redirect() {
    fs = FileSystem.createFileSystemInstance();
  }

  /**
   * Makes a file if needed and then calls the redirect method to add the
   * content.
   * 
   * @param type Whether it is > or >>.
   * @param path The path to the file that will be redirected to.
   * @param content The content to be redirected.
   */
  public void execute(String type, String path, String content) {

    if (this.fs.fileExists(path)) {
      this.redirect(type, path, content);
    } else {
      this.fs.makeFile(path);
      this.redirect(type, path, content);
    }

  }

  // adds the content to the given file
  private void redirect(String type, String path, String content) {

    if (type.equals(">")) {
      this.fs.setFileContent(true, path, content);
    } else {
      this.fs.setFileContent(false, path, content);
    }
  }

}
