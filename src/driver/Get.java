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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Gets the contents of a txt or html from the web and stores it in the current
 * working directory.
 */
public class Get extends Command {

  /**
   * Gets the contents of an online file and stores it in the current working
   * directory.
   * 
   * @param arguments The arguments entered by the user.
   */
  public void executeCommand(String arguments) {

    this.prepareToExecute(arguments, "get");

    String content = this.getURLContent();
    String fileName =
        this.args.get(0).substring(this.args.get(0).lastIndexOf("/") + 1);

    // makes the file if needed and then sets the content to the content of the
    // online file
    if (this.fs.fileExists(fileName))
      this.fs.setFileContent(true, fileName, content);
    else {
      this.fs.makeFile(fileName);
      this.fs.setFileContent(true, fileName, content);
    }
  }

  // gets the url content from the file entered by the user
  private String getURLContent() {

    String url = this.args.get(0);
    String fileType = url.substring(url.lastIndexOf(".") + 1);

    // not a txt or html file
    if (!fileType.equalsIgnoreCase("txt") && !fileType.equalsIgnoreCase("html"))
      throw new InvalidArgumentException(
          url + ": get command can only get txt or html files");

    // makes a connection to the file and gets the contents - raises error if
    // invalid url or can't connect
    try {

      URL urlObject = new URL(url);
      URLConnection urlCon = urlObject.openConnection();
      BufferedReader in =
          new BufferedReader(new InputStreamReader(urlCon.getInputStream()));

      String fullContent = "";
      String line;

      while ((line = in.readLine()) != null)
        fullContent = fullContent + line + "\n";

      in.close();
      return fullContent.substring(0, fullContent.length() - 1);

    } catch (MalformedURLException e) {
      throw new InvalidArgumentException(url + ": no such url found");
    } catch (IOException e) {
      throw new InvalidArgumentException(
          url + ": unable to connect to given url");
    }
  }

}
