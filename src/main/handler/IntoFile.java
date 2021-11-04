package handler;

import java.io.*;

public class IntoFile<T> {
  private String[] suportFiles;
  private String path;
  
  public IntoFile (String path) {
    this.path = path; 
    suportFiles = new String[]{"csv", "txt", ""};
  }

  public void insertTuple(T models) throws IOException {
     FileWriter file = null;
     PrintWriter pw = null;

    file = new FileWriter(path,true);   
    pw = new PrintWriter(file);                  


    if(isEmpty()) {
      // intertar la cabesera
    } else {
      // inter value
    }

    // for (double i = 0; i < 1000; i++)               
    //   pw.println("Linea " + i);

    file.close();
  }                                                       

  public boolean isEmpty() throws IOException {
    return true;
  }                                                       




  public void readDatas() throws IOException, FileNotFoundException {
    File file;
    BufferedReader buffer;
    FileReader input; 
    String line;

    file = new File(path);
    input = new FileReader(file);
    buffer = new BufferedReader(input);

    do {
      line = buffer.readLine();
    } while(line != null);

    buffer.close();
  }

  public void setPath(String path) {
    this.path = path; 
  }
}