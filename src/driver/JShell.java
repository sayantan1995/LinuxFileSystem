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

import java.util.Scanner;

/**
 * Accepts input from the user and exits the program when the user enters exit.
 */
public class JShell {

  /**
   * The main method.
   */
  public static void main(String[] args) {

    // prepares necessary objects
    FileSystem fs = FileSystem.createFileSystemInstance();
    String rawInput;
    String cleanInput;
    CommandExecutor executor = new CommandExecutor();


    boolean exitProgram = false;
    Scanner in = new Scanner(System.in);

    // loops until the user wants to exit the shell
    do {
      System.out.print("<" + fs.getWd() + "> ");
      rawInput = in.nextLine();
      cleanInput = CommandParser.getCleanInput(rawInput);


      // user wishes to exit the program
      if (cleanInput.matches("exit"))
        exitProgram = true;


      // user entered a command that is not blank nor exit
      else if (cleanInput.length() > 0) {
        executor.executeCommand(rawInput);
      }


    } while (!exitProgram);

    in.close();
  }

}
