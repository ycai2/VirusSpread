import java.io.*;

/** JDK 6 or before. */
public class ReadIn {

  protected int n = 0;
  protected int m = 0;
  /**
  * Fetch the entire contents of a text file, and return it in a String.
  * This style of implementation does not throw Exceptions to the caller.
  *
  * @param aFile is a file which already exists and can be read.
  */
  static public String getContents(File aFile) {
    //...checks on aFile are elided
    StringBuilder contents = new StringBuilder();
 
    try {
      //use buffering, reading one line at a time
      //FileReader always assumes default encoding is OK!
      BufferedReader input =  new BufferedReader(new FileReader(aFile));
      try {
        String line = null; //not declared within while loop

        /*
        * readLine is a bit quirky :
        * it returns the content of a line MINUS the newline.
        * it returns null only for the END of the stream.
        * it returns an empty String if two newlines appear in a row.
        */


        while (( line = input.readLine()) != null){
          contents.append(line);
          //contents.append(System.getProperty("line.separator"));
          contents.append("\n");

        }
      }
      finally {
        input.close();
      }
    }
    catch (IOException ex){
      ex.printStackTrace();
    }
    
    return contents.toString();
  }


  /** Simple test harness.   */
  public static void main (String... aArguments) throws IOException {
    System.out.println("User dir is: " + System.getProperty("user.dir") + "\n");
    File testFile = new File(System.getProperty("user.dir") + "/test");
    System.out.println("Original file contents: \n" + getContents(testFile));
  }
} 