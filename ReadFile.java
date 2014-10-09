package textfiles;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class ReadFile{
  private String path;

  public ReadFile(String file_path) {
    path = file_path;
  }

  public String[] OpenFile() throws IOException{
    FileReader fr = new FileReader(path);
    BufferedReader textReader = new BufferedReader(fr);

    int numLines = 3;
    String[] textData = new String[numLines];

    int i;

    for (i=0; i < numLines; i++){
      textData[i] = textReader.readLine();
    }

    textReader.close();
    return textData;

  }

  int readLines() throws IOException{
    FileReader file_to_read = new Fi
  }
}