package handler;

import java.io.*;
import java.util.Scanner;

public class IntoFile<T> {
  private String[] suportFiles;
  private String path;
  
  public IntoFile (String path) {
    this.path = path; 
    suportFiles = new String[]{"csv", "txt", ""};
  }

  public void insertTuple(SuperData model) throws IOException {
    String header;
    String[] valuesVec;
    String values;
    FileWriter file;
    PrintWriter pw;

    valuesVec = model.getValues(model);
    values = stringify(valuesVec); 

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

  public String stringify(String[] vector) {
    String res = vector[0]; 

    for(int i=1; i < vector.length; i++)
      res += ", " + vector[i];

    return res;
  }

  public String isEmpty(SuperData model) throws IOException {
    String[] header = model.getHeader(model);
    String firstLine = getFirstLine();
    String res = stringify(header);

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

  public void readDatas() throws IOException, FileNotFoundException {
    String line = "";
    // BufferedReader buffer;
    // FileReader input; 
    File file;

    file = new File(path);
    Scanner reader = new Scanner(file);
    // input = new FileReader(file);
    // buffer = new BufferedReader(input);

    while (reader.hasNextLine()) {
      line = reader.nextLine();
    }

    System.out.println(line);

    // do {
    //   // line = buffer.readLine();
    //   System.out.println(line);
    // } while(line != null);

    // buffer.close();
    reader.close();
  }

  public void setPath(String path) {
    this.path = path; 
  }
}