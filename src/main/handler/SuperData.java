package handler;

import java.lang.reflect.Field;
import java.util.ArrayList;

abstract public class SuperData {

  public <T> String[] getValues(T klazz) {
    Field[] fields = klazz.getClass().getDeclaredFields();
    ArrayList<String> values = new ArrayList<>();

    try {
      for (Field field : fields) {
        int accMod = field.getModifiers(); //  def 0, pub 1, pri 2, pro 4

        if(accMod != 2)
          values.add(field.get(this) + "");
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    String[] arrValues = new String[values.size()];

    return values.toArray(arrValues);
  }

  public <T> String[] getHeader(T model) { 
    Class<?> klazz = model.getClass();
    
    return getHeader(klazz);
  }
  
  public <T> String[] getHeader(Class<T> klazz) { 
    Field[] fields = klazz.getDeclaredFields();
    ArrayList<String> header = new ArrayList<>();

    for (Field field : fields) {
      int accMod = field.getModifiers();

      if(accMod != 2)
        header.add(field.getName());
    }

    String[] arrHead = new String[header.size()];

    return header.toArray(arrHead);
  }
  
}