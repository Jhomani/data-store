package handler;
import java.lang.reflect.*;

public class Main {
  // public static void main(String[] args) {
  //   Datas d = new Datas("Carlos", "Mamani", 14);
  //   Datas r = new Datas("Tomas", "Ramirez", 30);
  //   // String[] values= d.get(d);

  //   IntoFile<Datas> test = new IntoFile<>("/home/jhomani/Projects/java/data-manager/build/handler/Test.txt");

  //   try {
  //     test.insertTuple(r);
  //     // test.readData();
  //     // Datas naw= inspect(Datas.class);
  //     // System.out.println(naw.name + " " + naw.lastName + naw.years);
  //   } catch(Exception e) {
  //     System.out.println(e.getMessage());
  //   }

  // }

  public static void main (String[] args) {
      Serialization<Persona> ser= new Serialization<>("/home/jhomani/Projects/java/data-manager/build/handler/Persona.txt");

      try {
          //Permite escribir informaci�n en el archivo
          // Datos2 dato = new Datos2("232","Ramon","Rivero");
          // ser.saveObject(dato);

          // System.out.println("Dos registros escritos en la BD\n");

          // Leer los registros del Archivo
          Persona d;
          d = ser.readData();
          System.out.println(d.apellido + " " + d.nombre);

      } catch (Exception e) {
        System.out.println(e + "   Into Error");
      }
  }

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

    try {
      res = klazz.getConstructor(d).newInstance("Romon", "Santos", 42);
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }


    return res;
  }
}