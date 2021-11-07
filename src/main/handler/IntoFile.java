package handler;

import java.io.*;
import java.util.Scanner;

public class IntoFile {
  private String path;
  
  public IntoFile (String path) {
    this.path = path; 
  }

  public void insertTuple(SuperData model) throws IOException {
    String header;
    String[] valuesVec;
    String values;
    FileWriter file;
    PrintWriter pw;

    valuesVec = model.getValues(model);
    values = Utils.stringify(valuesVec); 

    header = isEmpty(model);

    if(header != null) {
      file = new FileWriter(path);   

      file.write(header + "\n" + values+"\n");
    } else {
      file = new FileWriter(path, true);   
      pw = new PrintWriter(file);                  

      pw.println(values);
    }

    file.close();
  }                                                       


  public String isEmpty(SuperData model) throws IOException {
    String[] header = model.getHeader(model);
    String firstLine = getFirstLine();
    String res = Utils.stringify(header);

    if(res.equals(firstLine)) 
      res = null;

    return res;
  }                                                       

  public String getFirstLine() throws IOException, FileNotFoundException {
    BufferedReader buffer;
    FileReader input; 
    File file;
    String res;

    file = new File(path);
    input = new FileReader(file);
    buffer = new BufferedReader(input);

    res = buffer.readLine();

    buffer.close();

    return res;
  }

  public boolean resetFile() throws IOException {
    boolean res = false;
    File file;

    file = new File(path);

    if(file.delete()) {
      file = new File(path);
      file.createNewFile();

      res = true;
    }

    return res;
  }

  public <T> T readData(
    Class<T> klazz
  ) throws IOException, FileNotFoundException {
    String line = "";
    File file;
    T res = null;
    int lines = 0;

    file = new File(path);
    Scanner reader = new Scanner(file);

    while (reader.hasNextLine()) {
      line = reader.nextLine();
      lines++;
    }

    reader.close();

    if(lines > 1) res = Utils.toObjectInstace(klazz, line);

    return res;
  }

  public <T> T readData(
    Class<T> klazz, int nLine
  ) throws IOException, FileNotFoundException {
    String line = "";
    File file;
    T res = null;

    if(nLine > 0) {
      int lines = 0;
      file = new File(path);
      Scanner reader = new Scanner(file);

      nLine++;

      while(reader.hasNextLine()) {
        lines++;
        line = reader.nextLine();

        if(lines == nLine) break;
      }

      reader.close();

      if(lines > 1) res = Utils.toObjectInstace(klazz, line);
    }

    return res;
  }

  public void setPath(String path) {
    this.path = path; 
  }
}