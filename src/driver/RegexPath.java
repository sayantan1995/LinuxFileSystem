// *********************************************************
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

import java.util.regex.*;

public class RegexPath extends Command {

  /**
   * The purpose of this method is to return the FileSystem. As input parameters
   * it takes in the number of arguments that have been entered. Then it returns
   * the given FileSystem object.
   * 
   * @param arguments The number of arguments a user has entered
   */
  public void executeCommand(String arguments) {

    this.prepareToExecute(arguments, "grep");

    if (!this.args.get(0).equalsIgnoreCase("-r")) {
      if (this.fs.directoryExists(this.args.get(1)))
        this.formatOutput(false);
      else
        this.formatOutput(true);
    } else {
      Pattern p = Pattern.compile(this.args.get(0));
      Matcher m = p.matcher(this.fs.getFileContent(this.args.get(1)));
      if (m.find()) {
        this.output = m.group();
      } else
        throw new InvalidArgumentException(
            "grep: does not contain " + "the following regax in path");


      this.finishExecute();
    }
  }

  private void formatOutput(boolean recursion) {

    Pattern p = Pattern.compile(this.args.get(0));
    Matcher m =
        p.matcher(this.fs.getDirectoryContent(this.args.get(1), recursion));
    if (m.find()) {
      output = output + this.args.get(1) + ":\n" + m.group() + "\n";
    } else
      throw new InvalidArgumentException(
          "grep: does not contain " + "the following regax in path");


  }

  private void formatRecursion() {



    this.finishExecute();



  }
}


