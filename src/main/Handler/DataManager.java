package Handler;

import Handler.SqlManager;

public class DataManager {

  public void sayHello() {
    System.out.println("heloo");

  }


  public static void main(String[] args) {
      DataManager inst = new DataManager(); 

      inst.sayHello();
  }
}