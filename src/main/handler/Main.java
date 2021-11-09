package handler;
import java.lang.reflect.*;

public class Main {
  public static void main(String[] args) {
    Datas d = new Datas("Antonio", "Mamani", 14);
    Datas r = new Datas("Luis", "Ramirez", 30);

    IntoFile test = new IntoFile("/home/jhomani/Projects/java/data-manager/build/handler/Test.txt");

    try {
      // test.saveData(d);
      // test.saveData(r);
      // Datas lastO = test.readData(Datas.class);
      // Datas naw= inspect(Datas.class);
      // System.out.println(lastO.name + " " + lastO.lastName + " " +lastO.years);
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }

  }

  // public static void main (String[] args) {
  //     Serialization<Persona> ser= new Serialization<>("/home/jhomani/Projects/java/data-manager/build/handler/Persona.txt");

  //     try {
  //         // Permite escribir informaci�n en el archivo
  //         // Persona per = new Persona("232","Ramon","Rivero");
  //         Persona per4 = new Persona("42345","Antonio","Volanos");
  //         Persona per5 = new Persona("i4354","Ramon","Ramos");
  //         ser.saveObject(per4);
  //         ser.saveObject(per5);
  //         // ser.saveObject(per2);

  //         // System.out.println("Dos registros escritos en la BD\n");

  //         // Leer los registros del Archivo
  //         Persona d;
  //         d = ser.readData();
  //         System.out.println(d.nombre + " " + d.apellido);

  //     } catch (Exception e) {
  //       System.out.println(e + "   Into Error");
  //     }
  // }

  static <T> T inspect(Class<T> klazz) {
    T res = null; 
    Class<?>[] d = new Class<?>[3];

    Field[] fields = klazz.getDeclaredFields();
    System.out.printf("%d fields:%n", fields.length);

    int c = 0;
    for (Field field : fields) {
      d[c] = field.getType();
      System.out.printf("%s %s %s %d %n",
        Modifier.toString(field.getModifiers()),
        field.getType().getSimpleName(),
        field.getName(),
        field.getModifiers()
      );
      c++;
    }

    Object[] values = {"Romon", "Santos", 42};

    try {
      res = klazz.getConstructor(d).newInstance(values);
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }


    return res;
  }
}