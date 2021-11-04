package handler;

public class DataManager {
  private String name;

  public DataManager(String name) {
    this.name = name;
  }

  public void sayHello() {
    System.out.println("heloo " + name);
  }
}