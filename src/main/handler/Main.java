package handler;
import java.lang.reflect.*;

public class Main {
  public static void main(String[] args) {
    Datas d = new Datas("CArlos", "Mamani", 14);
    // String[] values= d.get(d);

    IntoFile<Datas> test = new IntoFile<>("/home/jhomani/Projects/java/data-manager/build/handler/Test.txt");

    try {
      // test.insertTuple(d);
      test.readDatas();
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }

  }

  static <T> void inspect(Class<T> klazz) {
    Field[] fields = klazz.getDeclaredFields();
    System.out.printf("%d fields:%n", fields.length);

    for (Field field : fields) {
      System.out.printf("%s %s %s %d %n",
        Modifier.toString(field.getModifiers()),
        field.getType().getSimpleName(),
        field.getName(),
        field.getModifiers()
      );
    }
  }
}