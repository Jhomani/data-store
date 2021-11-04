package handler;

import java.lang.reflect.Field;
import java.util.ArrayList;

abstract public class SuperData {

  public <T> String[] getDatas(T klazz) {
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
}