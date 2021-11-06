
package handler;

import java.io.*;

public class Serialization<T> {
  private String path;
	private FileOutputStream  archivosalida;
	private FileInputStream  archivoentrada;
	private ObjectOutputStream salida;
	private ObjectInputStream entrada;

  public Serialization(String path) {
    this.path = path;
  }
	
  @SuppressWarnings("unchecked")
	public T readData() {
		T d = null;
		
    try {
      abrirentrada();

      while(true)
        d = (T) entrada.readObject();
    } 
    catch(EOFException e) { } 
    catch(IOException e) { } 
    catch(ClassNotFoundException e) { }

    try {
      cerrarentrada();
    } catch(IOException e){}

		return d;
	}

  @SuppressWarnings("unchecked")
	public T readData(int numTuple) throws IOException, ClassNotFoundException {
    T aux = null;
		T d = null;
    int count = 1;
		
		if (entrada != null) {
      while(d != null && count == numTuple) { // numtuple = 2;
        d = (T) entrada.readObject();
        count++;
      }

      aux = (T) entrada.readObject();

      if(aux == null) aux = d;
    }

		return aux;
	}
	
	public void abrirsalida() throws IOException {
		archivosalida = new FileOutputStream(path);
		// archivosalida = new FileOutputStream("ejemplo.txt", false);  to add more data
		salida = new ObjectOutputStream(archivosalida);
	}
	
	public void abrirentrada() throws IOException {
		archivoentrada = new FileInputStream(path);
		entrada = new ObjectInputStream(archivoentrada);
	}
	
	public void cerrarsalida() throws IOException {
		if (salida!=null) salida.close();
	}
	
	public void cerrarentrada() throws IOException {
		if (entrada!=null) entrada.close();
	}
	
	public void saveObject(T d) throws IOException {
    abrirsalida();
		if (salida!=null) salida.writeObject(d);
    cerrarsalida();
	}
}
