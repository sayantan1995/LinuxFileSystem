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
 * File object that has a name and content.
 */
public class File {

  String content;
  String name;

  /**
   * Class constructor.
   * 
   * @param name The name of the file in the file-system.
   */
  public File(String name) {
    this.content = "";
    this.name = name;
  }

  /**
   * Overwrites the content of a file.
   * 
   * @param overwrite Use true if you want to overwrite the content and false if
   *        you want to append the content.
   * @param newContent The content that needs to be stored in the file.
   */
  public void setContent(boolean overwrite, String newContent) {
    if (overwrite)
      this.content = newContent;
    else {
      if (this.content.equals(""))
        this.content = newContent;
      else
        this.content = this.content + "\n" + newContent;
    }
  }

  /**
   * Gets the name of this file.
   * 
   * @return Name of the file.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets the content of this file.
   * 
   * @return Content of the file.
   */
  public String getContent() {
    return this.content;
  }

}


