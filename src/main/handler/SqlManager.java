package handler;

import java.sql.*;  
public class SqlManager {

  public void connect() {
    try{  
      String userName = "root";
      String password = "Test1235";
      String database = "umss";
      String url = "jdbc:mysql://localhost:3306/" + database;

      Connection con = DriverManager.getConnection(url,userName,password);  

      Statement stmt = con.createStatement();  
      ResultSet rs= stmt.executeQuery("SELECT * FROM alumno");  

      while(rs.next()) {
        System.out.println(rs.getString(1));  
      }  
      con.close();  
    } catch(Exception e) { 
      System.out.println(e);
    }  
  }
}