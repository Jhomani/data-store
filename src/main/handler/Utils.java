package handler;

import java.lang.reflect.*;

public class Utils {
  static public String stringify(String[] vector) {
    String res = vector[0]; 

    for(int i=1; i < vector.length; i++)
      res += ", " + vector[i];

    return res;
  }

  static public String[] vectory(String token,int size) {
    String[] res = new String[size]; 
    String collector = "";
    int j=0;

    int length = token.length();
    for(int i=0; i < length; i++) {
      if(token.charAt(i) == ',') {
        if(token.charAt(i+1) == ' ') {
          res[j] = collector;
          collector = "";
          i++; j++;
        }
      } else
        collector += token.charAt(i);
    }

    if(collector != "") res[j] = collector; 

    return res;
  }

  static public <T> T toObjectInstace(Class<T> klazz, String token) {
    T res = null; 
    Class<?>[] classType; 
    Object[] parameters;

    int size = getAttrSize(klazz);
    String[] values = vectory(token, size);  
    Field[] fields = klazz.getDeclaredFields();

    classType = new Class<?>[size];
    parameters = new Object[size];

    int j = 0;
    for (int i=0; i < size; i++) {
      int accMod = fields[i].getModifiers();

      if(accMod != 2) {
        classType[j] = fields[i].getType();
        parameters[j] = getTyped(values[j], classType[j].getSimpleName());
        j++;
      }
    }

    try {
      res = klazz.getConstructor(classType).newInstance(parameters);
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }

    return res;
  }

  static public Object getTyped(String value, String type) {
    Object res = null;

    switch(type) {
      case "int": res = Integer.parseInt(value); break;
      case "float": res = Float.parseFloat(value); break;
      case "double": res = Double.parseDouble(value); break;
      case "boolean": res = Boolean.parseBoolean(value); break;
      case "char": res = value.charAt(0); break;
      default: res = value; break;
    }
    return res;
  }

  static public <T> int getAttrSize(Class<T> klazz) { 
    int res = 0;
    Field[] fields = klazz.getDeclaredFields();

    for (Field field : fields) {
      int accMod = field.getModifiers();

      if(accMod != 2) res++;
    }

    return res;
  }
}
